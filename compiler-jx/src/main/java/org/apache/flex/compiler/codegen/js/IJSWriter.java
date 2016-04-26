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

package org.apache.flex.compiler.codegen.js;

import java.io.File;
import java.io.OutputStream;

import org.apache.flex.compiler.codegen.as.IASWriter;

/**
 * A JavaScript writer that outputs cross compiled string data to the output
 * stream.
 * 
 * @author Michael Schmalle
 */
public interface IJSWriter extends IASWriter
{
    /**
     * Write JS file and source map.
     *
     * @param jsOut JS output stream
     * @param sourceMapOut Source map file
     */
    void writeTo(OutputStream jsOut, File sourceMapOut);

}