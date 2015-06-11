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

package org.apache.flex.compiler.internal.codegen.externals.emit;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.flex.compiler.internal.codegen.externals.reference.BaseReference;
import org.apache.flex.compiler.internal.codegen.externals.reference.ClassReference;
import org.apache.flex.compiler.internal.codegen.externals.reference.ConstantReference;
import org.apache.flex.compiler.internal.codegen.externals.reference.FunctionReference;
import org.apache.flex.compiler.internal.codegen.externals.reference.ReferenceModel;

public class ReferenceEmitter
{
    private ReferenceModel model;

    public ReferenceEmitter(ReferenceModel model)
    {
        this.model = model;
    }

    public void emit() throws IOException
    {
        final File asRoot = model.getConfiguration().getAsRoot();
        asRoot.mkdirs();

        File asClassRoot = new File(asRoot, "classes");
        File asInterfacesRoot = new File(asRoot, "interfaces");
        File asFunctionRoot = new File(asRoot, "functions");
        File asConstantRoot = new File(asRoot, "constants");
        File asTypeDefRoot = new File(asRoot, "typedefs");

        for (ClassReference reference : model.getClasses())
        {
            if (model.isExcludedClass(reference) != null)
                continue;

            if (reference.isInterface())
                continue;

            StringBuilder sb = new StringBuilder();

            emit(reference, sb);

            File sourceFile = reference.getFile(asClassRoot);
            FileUtils.write(sourceFile, sb.toString());
        }

        for (ClassReference reference : model.getClasses())
        {
            StringBuilder sb = new StringBuilder();

            if (model.isExcludedClass(reference) != null)
                continue;

            if (!reference.isInterface())
                continue;

            emit(reference, sb);

            File sourceFile = reference.getFile(asInterfacesRoot);
            FileUtils.write(sourceFile, sb.toString());
        }

        // TODO figure out how to resolve/emit @typedef
        for (ClassReference reference : model.getTypedefs())
        {
            if (model.isExcludedClass(reference) != null)
                continue;

            StringBuilder sb = new StringBuilder();

            emit(reference, sb);

            File sourceFile = reference.getFile(asTypeDefRoot);
            FileUtils.write(sourceFile, sb.toString());
        }

        for (FunctionReference reference : model.getFunctions())
        {
            StringBuilder sb = new StringBuilder();

            emit(reference, sb);

            File sourceFile = reference.getFile(asFunctionRoot);
            FileUtils.write(sourceFile, sb.toString());
        }

        for (ConstantReference reference : model.getConstants())
        {
            StringBuilder sb = new StringBuilder();

            emit(reference, sb);

            File sourceFile = reference.getFile(asConstantRoot);
            FileUtils.write(sourceFile, sb.toString());
        }

        //        StringBuilder sb = new StringBuilder();
        //        sb.append("package {\n");
        //        for (Entry<String, ConstantReference2> set : constants.entrySet())
        //        {
        //            ConstantReference2 reference = set.getValue();
        //            emit(reference, sb);
        //        }
        //        sb.append("\n}");
        //        File sourceFile = new File(asRoot, "constants.as");
        //        FileUtils.write(sourceFile, sb.toString());
    }

    public void emit(BaseReference reference, StringBuilder sb)
    {
        reference.emit(sb);
    }

    public String emit(BaseReference reference)
    {
        StringBuilder sb = new StringBuilder();
        reference.emit(sb);
        return sb.toString();
    }
}