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

package org.apache.flex.compiler.problems;

import org.apache.flex.compiler.common.ISourceLocation;
import org.apache.flex.compiler.internal.parsing.as.ASToken;

/**
 * Compiler problem that indicates a file was not found.
 */
public final class FileNotFoundProblem extends CompilerProblem
{
    public static String DESCRIPTION =
        "File not found: ${file}";
   
    public static final int errorCode = 1457;
    
    public FileNotFoundProblem(ISourceLocation site, String filePath)
    {
        super(site);
        file = filePath;
    }
     
    public FileNotFoundProblem(ASToken site, String filePath)
    {
        super(site);
        file = filePath;
    }
     
    public FileNotFoundProblem(String filePath)
    {
        super();
        file = filePath;
    }
    
    /// Path to the file that was not found.
    public final String file;
}
