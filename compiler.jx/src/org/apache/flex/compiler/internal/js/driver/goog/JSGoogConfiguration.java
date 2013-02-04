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

package org.apache.flex.compiler.internal.js.driver.goog;

import org.apache.flex.compiler.clients.JSConfiguration;
import org.apache.flex.compiler.clients.MXMLJSC;
import org.apache.flex.compiler.config.ConfigurationValue;
import org.apache.flex.compiler.exceptions.ConfigurationException;
import org.apache.flex.compiler.internal.config.annotations.Config;
import org.apache.flex.compiler.internal.config.annotations.Mapping;

/**
 * The {@link JSGoogConfiguration} class holds all compiler arguments needed for
 * compiling ActionScript to JavaScript the 'goog' way.
 * <p>
 * Specific flags are implemented here for the configuration to be loaded by the
 * configure() method of {@link MXMLJSC}.
 * <p>
 * This class inherits all compiler arguments from the MXMLC compiler.
 * 
 * @author Erik de Bruin
 */
public class JSGoogConfiguration extends JSConfiguration
{
    public JSGoogConfiguration()
    {
    }

    //
    // 'closure-lib'
    //

    private String closureLib;

    public String getClosureLib()
    {
        return closureLib;
    }

    @Config
    @Mapping("closure-lib")
    public void setClosureLib(ConfigurationValue cv, String value)
            throws ConfigurationException
    {
        closureLib = value;
    }

    //
    // 'vanilla-sdk-lib'
    //

    private String vanillaSDKLib;

    public String getVanillaSDKLib()
    {
        return vanillaSDKLib;
    }

    @Config
    @Mapping("vanilla-sdk-lib")
    public void setVanillaSDKLib(ConfigurationValue cv, String value)
            throws ConfigurationException
    {
        vanillaSDKLib = value;
    }

}