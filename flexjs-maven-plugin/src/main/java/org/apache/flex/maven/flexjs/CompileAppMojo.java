/*
 * Copyright 2001-2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flex.maven.flexjs;

import edu.emory.mathcs.backport.java.util.Arrays;
import org.apache.flex.tools.FlexTool;
import org.apache.maven.artifact.Artifact;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * goal which compiles a project into a flexjs sef application.
 */
@Mojo(name="compile-app",defaultPhase = LifecyclePhase.PROCESS_SOURCES)
public class CompileAppMojo
    extends BaseMojo
{

    @Parameter
    private String mainClass;

    @Parameter(defaultValue = "${project.artifactId}-${project.version}.swf")
    private String outputFileName;

    @Parameter(defaultValue = "namespaces")
    protected String namespaceDirectory;

    @Override
    protected String getToolGroupName() {
        return "Falcon";
    }

    @Override
    protected String getFlexTool() {
        return FlexTool.FLEX_TOOL_MXMLC;
    }

    @Override
    protected String getConfigFileName() {
        return "compile-app-config.xml";
    }

    @Override
    protected File getOutput() {
        return new File(outputDirectory, outputFileName);
    }

    @Override
    protected List<String> getCompilerArgs(File configFile) throws MojoExecutionException {
        if(mainClass == null) {
            throw new MojoExecutionException("The mainClass has to be declared for SWF type modules.");
        }
        String mainClassPath = getSourcePath(mainClass);
        if(mainClassPath == null) {
            throw new MojoExecutionException("Could not find main class");
        }
        List<String> args = super.getCompilerArgs(configFile);
        args.add("-define=COMPILE::AS3,true");
        args.add("-define=COMPILE::JS,false");
        args.add(mainClassPath);
        return args;
    }

    @Override
    public void execute() throws MojoExecutionException {
        super.execute();

        if(getOutput().exists()) {
            // Attach the file created by the compiler as artifact file to maven.
            project.getArtifact().setFile(getOutput());
        }
    }

    /**
     * @return list of the explicitly defined as well as the automatically detected namespaces.
     */
    /*@Override
    @SuppressWarnings("unchecked")
    protected Namespace[] getNamespaces() {
        File namespaceDir = new File(outputDirectory, namespaceDirectory);
        if(namespaceDir.exists()) {
            File[] namespaceFiles = namespaceDir.listFiles();
            if(namespaceFiles != null) {
                List<Namespace> autoNamespaces = new ArrayList<Namespace>(namespaceFiles.length);
                // Read the namespace-uri attribute of each file and add them to the namespaces.
                for(File namespaceFile : namespaceFiles) {
                    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
                    try {
                        DocumentBuilder builder = factory.newDocumentBuilder();
                        Document namespaceDoc = builder.parse(namespaceFile);
                        String namespaceUri = namespaceDoc.getDocumentElement().getAttribute("namespace-uri");
                        Namespace namespace = new Namespace();
                        namespace.setUri(namespaceUri);
                        namespace.setManifest(namespaceFile.getPath());
                        autoNamespaces.add(namespace);
                    } catch (SAXException e) {
                        e.printStackTrace();
                    } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if(!autoNamespaces.isEmpty()) {
                    List<Namespace> namespaces;
                    Namespace[] manualNamespaces = super.getNamespaces();
                    if(manualNamespaces != null) {
                        namespaces = new ArrayList<Namespace>(Arrays.asList(manualNamespaces));
                        namespaces.addAll(autoNamespaces);
                    } else {
                        namespaces = autoNamespaces;
                    }
                    return namespaces.toArray(new Namespace[0]);
                }
            }
        }
        return super.getNamespaces();
    }*/

    @Override
    protected boolean includeLibrary(Artifact library) {
        return !"extern".equalsIgnoreCase(library.getClassifier());
    }

}
