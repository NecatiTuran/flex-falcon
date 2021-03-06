/*
 *
 *  Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package org.apache.flex.compiler.internal.units;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import org.apache.flex.abc.ABCConstants;
import org.apache.flex.abc.ABCEmitter;
import org.apache.flex.abc.instructionlist.InstructionList;
import org.apache.flex.abc.semantics.Name;
import org.apache.flex.compiler.common.DependencyType;
import org.apache.flex.compiler.common.Multiname;
import org.apache.flex.compiler.definitions.IDefinition;
import org.apache.flex.compiler.definitions.INamespaceDefinition;
import org.apache.flex.compiler.definitions.references.INamespaceReference;
import org.apache.flex.compiler.definitions.references.IResolvedQualifiersReference;
import org.apache.flex.compiler.definitions.references.ReferenceFactory;
import org.apache.flex.compiler.internal.abc.ClassGeneratorHelper;
import org.apache.flex.compiler.internal.definitions.ClassDefinition;
import org.apache.flex.compiler.internal.embedding.EmbedData;
import org.apache.flex.compiler.internal.projects.CompilerProject;
import org.apache.flex.compiler.internal.projects.FlexProject;
import org.apache.flex.compiler.internal.projects.DefinitionPriority.BasePriority;
import org.apache.flex.compiler.internal.scopes.ASFileScope;
import org.apache.flex.compiler.internal.scopes.ASProjectScope;
import org.apache.flex.compiler.internal.units.requests.ABCBytesRequestResult;
import org.apache.flex.compiler.internal.units.requests.FileScopeRequestResultBase;
import org.apache.flex.compiler.internal.units.requests.SWFTagsRequestResult;
import org.apache.flex.compiler.internal.units.requests.SyntaxTreeRequestResult;
import org.apache.flex.compiler.problems.ICompilerProblem;
import org.apache.flex.compiler.problems.InternalCompilerProblem;
import org.apache.flex.compiler.scopes.IASScope;
import org.apache.flex.compiler.units.ICompilationUnit;
import org.apache.flex.compiler.units.requests.IABCBytesRequestResult;
import org.apache.flex.compiler.units.requests.IFileScopeRequestResult;
import org.apache.flex.compiler.units.requests.IOutgoingDependenciesRequestResult;
import org.apache.flex.compiler.units.requests.ISWFTagsRequestResult;
import org.apache.flex.compiler.units.requests.ISyntaxTreeRequestResult;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

/**
 * The main compilation unit for a Resource module project. It can compile one
 * or more ResourceBundle files into a class to load them at runtime. This
 * compilation unit type is created in order to setup the correct dependencies
 * in the target.
 */
public class ResourceModuleCompilationUnit extends CompilationUnitBase
{
    /**
     * Qualified name for the module processed by this compilation unit
     */
    private final String qname;
    
    /**
     * List of resource bundle compilation units to include in the generated module
     */
    private final Collection<ICompilationUnit> resourceBundleCompUnits;

    /**
     * Create a {@code ResourceModuleCompilationUnit}.
     * 
     * @param project owner project.
     * @param resourceBundleCompUnits list of resource bundle compilation units
     * to include in this resource module
     * @param basePriority base priority.
     */
    public ResourceModuleCompilationUnit(CompilerProject project, String qname, 
            Collection<ICompilationUnit> resourceBundleCompUnits, BasePriority basePriority)
    {
        super(project, null, basePriority, qname);
        this.resourceBundleCompUnits = resourceBundleCompUnits;
        this.qname = qname;
    }

    @Override
    public UnitType getCompilationUnitType()
    {
        return UnitType.RESOURCE_UNIT;
    }

    /**
     * {@code ResourceModuleCompilationUnit} does not provide an AST.
     */
    @Override
    protected ISyntaxTreeRequestResult handleSyntaxTreeRequest() throws InterruptedException
    {
        return new SyntaxTreeRequestResult(-1, Collections.<ICompilerProblem>emptyList());
    }

    /**
     * Synthesize a file scope with a public definition of module main class.
     */
    @Override
    protected IFileScopeRequestResult handleFileScopeRequest() throws InterruptedException
    {
        startProfile(Operation.GET_FILESCOPE);
        
        try 
        {
            final ASFileScope fileScope = new ASFileScope(getProject().getWorkspace(), null);

            Multiname mname = Multiname.crackDottedQName(getProject(), qname);
            INamespaceDefinition packageNS = Iterables.getOnlyElement(mname.getNamespaceSet());

            final ClassDefinition classDefinition = new ClassDefinition(mname.getBaseName(), 
                    (INamespaceReference)packageNS);
            fileScope.addDefinition(classDefinition);

            return new FileScopeRequestResultBase(Collections.<ICompilerProblem> emptySet(), 
                    ImmutableSet.<IASScope> of(fileScope));
        } 
        finally 
        {
            stopProfile(Operation.GET_FILESCOPE);
        }
    }
    
    /**
     * Manually add dependencies to all the classes/interfaces used in the module class 
     * that will be generated for this compilation unit.
     */
    @Override
    protected IOutgoingDependenciesRequestResult handleOutgoingDependenciesRequest () throws InterruptedException
    {
        startProfile(Operation.GET_SEMANTIC_PROBLEMS);
        
        final Collection<ICompilerProblem> problems = Collections.emptyList();
        try
        {
            final FlexProject flexProject = (FlexProject)getProject();
            
            //flex.compiler.support.ResourceModuleBase
            ASProjectScope scope = flexProject.getScope();
            IDefinition def = scope.findDefinitionByName(flexProject.getResourceModuleBaseClass());
            ICompilationUnit resourceModuleBaseCompUnit = scope.getCompilationUnitForDefinition(def);
            flexProject.addDependency(this, resourceModuleBaseCompUnit, DependencyType.INHERITANCE,
                    def.getQualifiedName());
            
            //Add dependency to all the resource bundle compilation units we want to 
            //include in the resource module SWF
            for(ICompilationUnit compUnit : resourceBundleCompUnits)
            {
                flexProject.addDependency(this, compUnit, DependencyType.EXPRESSION);
            }
        }
        catch (Throwable t)
        {
            problems.add(new InternalCompilerProblem(t));
        }
        finally
        {
            stopProfile(Operation.GET_SEMANTIC_PROBLEMS);
        }

        return new IOutgoingDependenciesRequestResult()
        {
            @Override
            public ICompilerProblem[] getProblems()
            {
                return problems.toArray(new ICompilerProblem[0]);
            }
        };
    }

    /**
     * Get the ABC byte code generated from this module class. The generated class looks like the following:
     * 
     * --------------------------
     *   package
     *   {
     *       import flex.compiler.support.ResourceModuleBase;
     *       
     *       public class GeneratedResourceModule extends ResourceModuleBase
     *       {
     *           public function GeneratedResourceModule()
     *           {
     *               super(["en_US$core_properties", "en_US$effects_properties"]);
     *           }
     *       }
     *   }
     * -------------------------------------
     */
    @Override
    protected IABCBytesRequestResult handleABCBytesRequest() throws InterruptedException
    {
        startProfile(Operation.GET_ABC_BYTES);
        try
        {
            final FlexProject flexProject = (FlexProject)getProject();
            final Collection<ICompilerProblem> problems = new LinkedList<ICompilerProblem>();
            final ABCEmitter emitter = new ABCEmitter();
            
            byte[] generatedBytes = null;
            try
            {
                //this class extends "flex.compiler.support.ResourceModuleBase"
                IResolvedQualifiersReference resourceModuleBaseRef = ReferenceFactory.packageQualifiedReference(
                        flexProject.getWorkspace(), flexProject.getResourceModuleBaseClass());
                
                //Create constructor instruction list
                InstructionList constructorInstructionList = new InstructionList();
                constructorInstructionList.addInstruction(ABCConstants.OP_getlocal0);
                constructorInstructionList.addInstruction(ABCConstants.OP_pushscope);  
                
                int resourceBundleCount = 0;
                for(ICompilationUnit compUnit : resourceBundleCompUnits)
                {
                    for(IDefinition def : compUnit.getDefinitionPromises())
                    {
                        constructorInstructionList.addInstruction(ABCConstants.OP_pushstring, def.getQualifiedName());
                        resourceBundleCount++;
                    }
                }
                
                constructorInstructionList.addInstruction(ABCConstants.OP_newarray, resourceBundleCount);
                constructorInstructionList.addInstruction(ABCConstants.OP_constructsuper, 1);
                constructorInstructionList.addInstruction(ABCConstants.OP_returnvoid);
                
                ClassGeneratorHelper classGen = new ClassGeneratorHelper(flexProject, emitter,
                        new Name(qname),
                        (ClassDefinition)resourceModuleBaseRef.resolve(flexProject),
                        Collections.<Name> emptyList(), 
                        constructorInstructionList);

                classGen.finishScript();
                generatedBytes = emitter.emit();
            }
            catch (Throwable ex)
            {
                problems.add(new InternalCompilerProblem(ex));
            }

            return new ABCBytesRequestResult(generatedBytes, problems.toArray(new ICompilerProblem[0]), Collections.<EmbedData>emptySet());
        }
        finally
        {
            stopProfile(Operation.GET_ABC_BYTES);
        }
    }

    @Override
    protected ISWFTagsRequestResult handleSWFTagsRequest() throws InterruptedException
    {
        startProfile(Operation.GET_SWF_TAGS);        
        try 
        {
            final IABCBytesRequestResult abcResult = getABCBytesRequest().get();

            return new SWFTagsRequestResult(abcResult.getABCBytes(), qname);
        } 
        finally
        {
            stopProfile(Operation.GET_SWF_TAGS);
        }
    }

}
