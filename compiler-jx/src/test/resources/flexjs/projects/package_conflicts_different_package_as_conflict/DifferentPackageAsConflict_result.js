/**
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
/**
 * DifferentPackageAsConflict
 *
 * @fileoverview
 *
 * @suppress {checkTypes|accessControls}
 */

goog.provide('DifferentPackageAsConflict');

goog.require('mypackage.TestClass');



/**
 * @constructor
 */
DifferentPackageAsConflict = function() {
  this.testClass = new mypackage.TestClass();
};


/**
 * @private
 * @type {mypackage.TestClass}
 */
DifferentPackageAsConflict.prototype.testClass;


/**
 * Metadata
 *
 * @type {Object.<string, Array.<Object>>}
 */
DifferentPackageAsConflict.prototype.FLEXJS_CLASS_INFO = { names: [{ name: 'DifferentPackageAsConflict', qName: 'DifferentPackageAsConflict'}] };


/**
 * Prevent renaming of class. Needed for reflection.
 */
goog.exportSymbol('DifferentPackageAsConflict', DifferentPackageAsConflict);



/**
 * Reflection
 *
 * @return {Object.<string, Function>}
 */
DifferentPackageAsConflict.prototype.FLEXJS_REFLECTION_INFO = function () {
  return {
    variables: function () {
      return {
      };
    },
    accessors: function () {
      return {
      };
    },
    methods: function () {
      return {
        'DifferentPackageAsConflict': { type: '', declaredBy: 'DifferentPackageAsConflict'}
      };
    }
  };
};