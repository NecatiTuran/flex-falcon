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

package org.apache.flex.compiler.as.codegen;

import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;

/**
 * An ActionScript writer that outputs cross compiled string data to the
 * output stream.
 * 
 * @author Michael Schmalle
 */
public interface IASWriter extends Closeable
{
    /**
     * Start writing to output stream.
     * 
     * @param out output stream
     */
    void writeTo(OutputStream out);

    /**
     * Start writing to a file.
     * 
     * @param out The output {@link File}.
     * @return The number of bytes written.
     */
    int writeTo(File out) throws FileNotFoundException, IOException;

}