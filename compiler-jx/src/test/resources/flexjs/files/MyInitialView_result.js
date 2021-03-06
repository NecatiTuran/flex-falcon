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
 * MyInitialView
 *
 * @fileoverview
 *
 * @suppress {checkTypes|accessControls}
 */

goog.provide('MyInitialView');

goog.require('org.apache.flex.core.ViewBase');
goog.require('org.apache.flex.html.Label');
goog.require('org.apache.flex.binding.SimpleBinding');
goog.require('org.apache.flex.html.TextButton');
goog.require('org.apache.flex.html.List');
goog.require('org.apache.flex.binding.ConstantBinding');
goog.require('org.apache.flex.html.TextArea');
goog.require('org.apache.flex.html.TextInput');
goog.require('org.apache.flex.html.CheckBox');
goog.require('org.apache.flex.html.RadioButton');
goog.require('org.apache.flex.html.DropDownList');
goog.require('org.apache.flex.html.ComboBox');
goog.require('org.apache.flex.events.CustomEvent');
goog.require('org.apache.flex.events.Event');
goog.require('org.apache.flex.events.MouseEvent');
goog.require('org.apache.flex.utils.Timer');



/**
 * @constructor
 * @extends {org.apache.flex.core.ViewBase}
 */
MyInitialView = function() {
  MyInitialView.base(this, 'constructor');
  
  /**
   * @private
   * @type {org.apache.flex.html.Label}
   */
  this.lbl_;
  
  /**
   * @private
   * @type {org.apache.flex.binding.SimpleBinding}
   */
  this.$ID0_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextButton}
   */
  this.$ID1_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextButton}
   */
  this.$ID2_;
  
  /**
   * @private
   * @type {org.apache.flex.html.Label}
   */
  this.timerLabel_;
  
  /**
   * @private
   * @type {org.apache.flex.html.List}
   */
  this.cityList_;
  
  /**
   * @private
   * @type {org.apache.flex.binding.ConstantBinding}
   */
  this.$ID3_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextArea}
   */
  this.$ID5_;
  
  /**
   * @private
   * @type {org.apache.flex.binding.SimpleBinding}
   */
  this.$ID4_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextInput}
   */
  this.input_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextButton}
   */
  this.$ID6_;
  
  /**
   * @private
   * @type {org.apache.flex.html.CheckBox}
   */
  this.checkbox_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID7_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID8_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID9_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID10_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID11_;
  
  /**
   * @private
   * @type {org.apache.flex.html.RadioButton}
   */
  this.$ID12_;
  
  /**
   * @private
   * @type {org.apache.flex.html.DropDownList}
   */
  this.list_;
  
  /**
   * @private
   * @type {org.apache.flex.binding.ConstantBinding}
   */
  this.$ID13_;
  
  /**
   * @private
   * @type {org.apache.flex.html.TextButton}
   */
  this.$ID14_;
  
  /**
   * @private
   * @type {org.apache.flex.html.ComboBox}
   */
  this.comboBox_;
  
  /**
   * @private
   * @type {org.apache.flex.binding.ConstantBinding}
   */
  this.$ID15_;
  
  /**
   * @private
   * @type {Array}
   */
  this.mxmldd;
  
  /**
   * @private
   * @type {Array}
   */
  this.mxmldp;
};
goog.inherits(MyInitialView, org.apache.flex.core.ViewBase);


/**
 * Metadata
 *
 * @type {Object.<string, Array.<Object>>}
 */
MyInitialView.prototype.FLEXJS_CLASS_INFO = { names: [{ name: 'MyInitialView', qName: 'MyInitialView' }] };


/**
 * Prevent renaming of class. Needed for reflection.
 */
goog.exportSymbol('MyInitialView', MyInitialView);



/**
 * Reflection
 *
 * @return {Object.<string, Function>}
 */
MyInitialView.prototype.FLEXJS_REFLECTION_INFO = function () {
  return {
    variables: function () {
      return {
      };
    },
    accessors: function () {
      return {
        'symbol': { type: 'String', declaredBy: 'MyInitialView'},
        'city': { type: 'String', declaredBy: 'MyInitialView'},
        'inputText': { type: 'String', declaredBy: 'MyInitialView'},
        'comboBoxValue': { type: 'String', declaredBy: 'MyInitialView'},
        'lbl': { type: 'org.apache.flex.html.Label', declaredBy: 'MyInitialView'},
        'timerLabel': { type: 'org.apache.flex.html.Label', declaredBy: 'MyInitialView'},
        'cityList': { type: 'org.apache.flex.html.List', declaredBy: 'MyInitialView'},
        'input': { type: 'org.apache.flex.html.TextInput', declaredBy: 'MyInitialView'},
        'checkbox': { type: 'org.apache.flex.html.CheckBox', declaredBy: 'MyInitialView'},
        'list': { type: 'org.apache.flex.html.DropDownList', declaredBy: 'MyInitialView'},
        'comboBox': { type: 'org.apache.flex.html.ComboBox', declaredBy: 'MyInitialView'}
      };
    },
    methods: function () {
      return {
        'startTimer': { type: 'void', declaredBy: 'MyInitialView'},
        'timerHandler': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH0': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH1': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH2': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH3': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH4': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH5': { type: 'void', declaredBy: 'MyInitialView'},
        '$EH6': { type: 'void', declaredBy: 'MyInitialView'}
      };
    }
  };
};



/**
 * @private
 * @type {org.apache.flex.utils.Timer}
 */
MyInitialView.prototype.timer;


;


;


;


;


/**
 * @export
 * @param {org.apache.flex.events.Event} event
 */
MyInitialView.prototype.startTimer = function(event) {
  this.timer = new org.apache.flex.utils.Timer(1000);
  this.timer.addEventListener('timer', org.apache.flex.utils.Language.closure(this.timerHandler, this, 'timerHandler'));
  this.timer.start();
};


/**
 * @export
 * @param {org.apache.flex.events.Event} event
 */
MyInitialView.prototype.timerHandler = function(event) {
  this.timerLabel.text = this.timer.currentCount.toString();
};





Object.defineProperties(MyInitialView.prototype, /** @lends {MyInitialView.prototype} */ {
/** @export */
symbol: {
get: /** @this {MyInitialView} */ function() {
  return org.apache.flex.utils.Language.as(this.list.selectedItem, String);
}},
/** @export */
city: {
get: /** @this {MyInitialView} */ function() {
  return org.apache.flex.utils.Language.as(this.cityList.selectedItem, String);
}},
/** @export */
inputText: {
get: /** @this {MyInitialView} */ function() {
  return this.input.text;
}},
/** @export */
comboBoxValue: {
get: /** @this {MyInitialView} */ function() {
  return String(this.comboBox.selectedItem);
}}}
);/**
 * @export
 * @param {org.apache.flex.events.MouseEvent} event
 */
MyInitialView.prototype.$EH0 = function(event)
{
  this.startTimer(event);
};


/**
 * @export
 * @param {org.apache.flex.events.MouseEvent} event
 */
MyInitialView.prototype.$EH1 = function(event)
{
  this.timer.removeEventListener('timer', org.apache.flex.utils.Language.closure(this.timerHandler, this, 'timerHandler'));
  this.timer.stop();
};


/**
 * @export
 * @param {org.apache.flex.events.Event} event
 */
MyInitialView.prototype.$EH2 = function(event)
{
  this.dispatchEvent(new org.apache.flex.events.CustomEvent('cityListChanged'));
};


/**
 * @export
 * @param {org.apache.flex.events.MouseEvent} event
 */
MyInitialView.prototype.$EH3 = function(event)
{
  this.dispatchEvent(new org.apache.flex.events.CustomEvent('transferClicked'));
};


/**
 * @export
 * @param {org.apache.flex.events.Event} event
 */
MyInitialView.prototype.$EH4 = function(event)
{
  this.dispatchEvent(new org.apache.flex.events.CustomEvent('listChanged'));
};


/**
 * @export
 * @param {org.apache.flex.events.MouseEvent} event
 */
MyInitialView.prototype.$EH5 = function(event)
{
  this.dispatchEvent(new org.apache.flex.events.CustomEvent('buttonClicked'));
};


/**
 * @export
 * @param {org.apache.flex.events.Event} event
 */
MyInitialView.prototype.$EH6 = function(event)
{
  this.dispatchEvent(new org.apache.flex.events.CustomEvent('comboBoxChanged'));
};


Object.defineProperties(MyInitialView.prototype, /** @lends {MyInitialView.prototype} */ {
/** @export */
    lbl: {
    /** @this {MyInitialView} */
    get: function() {
      return this.lbl_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.lbl_) {
        this.lbl_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'lbl', null, value));
      }
    }
  },
  /** @export */
    timerLabel: {
    /** @this {MyInitialView} */
    get: function() {
      return this.timerLabel_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.timerLabel_) {
        this.timerLabel_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'timerLabel', null, value));
      }
    }
  },
  /** @export */
    cityList: {
    /** @this {MyInitialView} */
    get: function() {
      return this.cityList_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.cityList_) {
        this.cityList_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'cityList', null, value));
      }
    }
  },
  /** @export */
    input: {
    /** @this {MyInitialView} */
    get: function() {
      return this.input_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.input_) {
        this.input_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'input', null, value));
      }
    }
  },
  /** @export */
    checkbox: {
    /** @this {MyInitialView} */
    get: function() {
      return this.checkbox_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.checkbox_) {
        this.checkbox_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'checkbox', null, value));
      }
    }
  },
  /** @export */
    list: {
    /** @this {MyInitialView} */
    get: function() {
      return this.list_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.list_) {
        this.list_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'list', null, value));
      }
    }
  },
  /** @export */
    comboBox: {
    /** @this {MyInitialView} */
    get: function() {
      return this.comboBox_;
    },
    /** @this {MyInitialView} */
    set: function(value) {
      if (value != this.comboBox_) {
        this.comboBox_ = value;
        this.dispatchEvent(org.apache.flex.events.ValueChangeEvent.createUpdateEvent(this, 'comboBox', null, value));
      }
    }
  },
  'MXMLDescriptor': {
    /** @this {MyInitialView} */
    get: function() {
      {
        if (this.mxmldd == undefined)
        {
          /** @type {Array} */
          var arr = org.apache.flex.utils.Language.superGetter(MyInitialView,this, 'MXMLDescriptor');
          /** @type {Array} */
          var data = [
      org.apache.flex.html.Label,
4,
'id',
true,
'lbl',
'x',
true,
100,
'y',
true,
25,
'beads',
null, [org.apache.flex.binding.SimpleBinding, 5, '_id', true, '$ID0', 'eventName', true, 'labelTextChanged', 'sourceID', true, 'applicationModel', 'sourcePropertyName', true, 'labelText', 'destinationPropertyName', true, 'text', 0, 0, null],
0,
0,
null,
org.apache.flex.html.TextButton,
4,
'_id',
true,
'$ID1',
'text',
true,
'Let\'s Start Timer',
'x',
true,
100,
'y',
true,
75,
0,
1,
'click',
this.$EH0,
null,
org.apache.flex.html.TextButton,
4,
'_id',
true,
'$ID2',
'text',
true,
'Stop Timer',
'x',
true,
100,
'y',
true,
100,
0,
1,
'click',
this.$EH1,
null,
org.apache.flex.html.Label,
3,
'id',
true,
'timerLabel',
'x',
true,
100,
'y',
true,
125,
0,
0,
null,
org.apache.flex.html.List,
6,
'id',
true,
'cityList',
'x',
true,
200,
'y',
true,
75,
'width',
true,
100,
'height',
true,
75,
'beads',
null, [org.apache.flex.binding.ConstantBinding, 4, '_id', true, '$ID3', 'sourceID', true, 'applicationModel', 'sourcePropertyName', true, 'cities', 'destinationPropertyName', true, 'dataProvider', 0, 0, null],
0,
1,
'change',
this.$EH2,
null,
org.apache.flex.html.TextArea,
6,
'_id',
true,
'$ID5',
'x',
true,
320,
'y',
true,
25,
'width',
true,
150,
'height',
true,
75,
'beads',
null, [org.apache.flex.binding.SimpleBinding, 5, '_id', true, '$ID4', 'eventName', true, 'labelTextChanged', 'sourceID', true, 'applicationModel', 'sourcePropertyName', true, 'labelText', 'destinationPropertyName', true, 'text', 0, 0, null],
0,
0,
null,
org.apache.flex.html.TextInput,
3,
'id',
true,
'input',
'x',
true,
320,
'y',
true,
110,
0,
0,
null,
org.apache.flex.html.TextButton,
4,
'_id',
true,
'$ID6',
'text',
true,
'Transfer',
'x',
true,
320,
'y',
true,
138,
0,
1,
'click',
this.$EH3,
null,
org.apache.flex.html.CheckBox,
4,
'id',
true,
'checkbox',
'x',
true,
320,
'y',
true,
170,
'text',
true,
'Check Me',
0,
0,
null,
org.apache.flex.html.RadioButton,
6,
'_id',
true,
'$ID7',
'groupName',
true,
'group1',
'text',
true,
'Apples',
'value',
true,
0,
'x',
true,
100,
'y',
true,
150,
0,
0,
null,
org.apache.flex.html.RadioButton,
7,
'_id',
true,
'$ID8',
'groupName',
true,
'group1',
'text',
true,
'Oranges',
'value',
true,
1,
'x',
true,
100,
'y',
true,
170,
'selected',
true,
true,
0,
0,
null,
org.apache.flex.html.RadioButton,
6,
'_id',
true,
'$ID9',
'groupName',
true,
'group1',
'text',
true,
'Grapes',
'value',
true,
2,
'x',
true,
100,
'y',
true,
190,
0,
0,
null,
org.apache.flex.html.RadioButton,
7,
'_id',
true,
'$ID10',
'groupName',
true,
'group2',
'text',
true,
'Red',
'value',
true,
16711680,
'x',
true,
100,
'y',
true,
250,
'selected',
true,
true,
0,
0,
null,
org.apache.flex.html.RadioButton,
6,
'_id',
true,
'$ID11',
'groupName',
true,
'group2',
'text',
true,
'Green',
'value',
true,
32768,
'x',
true,
100,
'y',
true,
270,
0,
0,
null,
org.apache.flex.html.RadioButton,
6,
'_id',
true,
'$ID12',
'groupName',
true,
'group2',
'text',
true,
'Blue',
'value',
true,
255,
'x',
true,
100,
'y',
true,
290,
0,
0,
null,
org.apache.flex.html.DropDownList,
6,
'id',
true,
'list',
'x',
true,
200,
'y',
true,
200,
'width',
true,
100,
'height',
true,
24,
'beads',
null, [org.apache.flex.binding.ConstantBinding, 4, '_id', true, '$ID13', 'sourceID', true, 'applicationModel', 'sourcePropertyName', true, 'strings', 'destinationPropertyName', true, 'dataProvider', 0, 0, null],
0,
1,
'change',
this.$EH4,
null,
org.apache.flex.html.TextButton,
4,
'_id',
true,
'$ID14',
'text',
true,
'OK',
'x',
true,
200,
'y',
true,
230,
0,
1,
'click',
this.$EH5,
null,
org.apache.flex.html.ComboBox,
5,
'id',
true,
'comboBox',
'x',
true,
320,
'y',
true,
200,
'width',
true,
100,
'beads',
null, [org.apache.flex.binding.ConstantBinding, 4, '_id', true, '$ID15', 'sourceID', true, 'applicationModel', 'sourcePropertyName', true, 'cities', 'destinationPropertyName', true, 'dataProvider', 0, 0, null],
0,
1,
'change',
this.$EH6,
null
      ];
        
          if (arr)
            this.mxmldd = arr.concat(data);
          else
            this.mxmldd = data;
        }
        return this.mxmldd;
      }
      }
    }
  });
  
