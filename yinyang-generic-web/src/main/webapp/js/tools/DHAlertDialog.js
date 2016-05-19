(function($){

	var root = window.DH = window.DH || {
		DOMAIN : 'dhgate.com',
		Widget : {}
	};

	var _hasprop = Object.hasOwnProperty;

	var _spliter = /^(\S+)\s*(.*)$/;

	var Base = {
		bind : function(ev, callback){
			this.ev[ev] = callback;
			return this;
		},
		unbind : function(ev){
			this.ev[ev] && (delete this.ev[ev]);
			return this;
		},
		trigger : function(ev){
			var rtn = true;
			if(this.ev[ev]){
				rtn = this.ev[ev].apply(this, [].slice.call(arguments, 1));
			}
			return rtn;
		},
		/**
		 * 事件代理
		 */
		proxy : function(){
			if(arguments.length === 0) return;

			var args = arguments,
				fn = args[0],
				context = args.length === 1 ? this : (typeof args[1] === 'object' && args[1])
				;

			return function(){
				return fn.apply(context, args.length > 2 ? [].concat.call([].slice.call(args, 2), [].slice.call(arguments)) : arguments);
			}
		},
		_supper : function(options){
			this.options = options = options || {};
			this.el = options.el ? (typeof(options.el) === 'string' ? $(options.el) : options.el) : $(document);
			this.ev = {};

			this._bind();
		},
		/**
		 * 缓存节点&绑定初始事件
		 */
		_bind : function(){

			this.elements && ~function(self){
				for(var item in self.elements){
					_hasprop.call(self.elements, item) && (self[self.elements[item]] = self.el.find(item));
				}
				self.elements = null;
				delete self.elements;
			}(this);

			this.events && ~function(self){
				for(var item in self.events){					
					_hasprop.call(self.events, item) && 
					~function(){
						var _ev = _spliter.exec(item)[1],
							_se = _spliter.exec(item)[2],
							_fn = self.events[item]
							;
						_ev && _se && _fn && self[_fn] && self.el.delegate(_se, _ev, self.proxy(self[_fn]));
					}();
				}
				self.events = null;
				delete self.events;
			}(this);

		},
		_create : function(){
			function f(){};
			f.prototype = this;
			return new f;
		},
		/**
		 * 类模式
		 */
		create : function(extend){
			var Result;

			Result = (function(){
				return function(){
					Base._supper.apply(this, arguments);
					this.init && typeof(this.init === 'function') && this.init.apply(this, arguments);
				}
			})();

			Result.prototype = this._create();

			extend && typeof(extend) === 'object' && $.extend(Result.prototype, extend);

			return Result;
		},
		/**
		 * 单例模式
		 */
		single : function(extend){
			var Result;

			Result = this._create();

			extend && typeof(extend) === 'object' && $.extend(Result, extend);

			Base._supper.apply(Result, [extend]);

			Result.init && typeof(Result.init === 'function') && Result.init.apply(Result, [extend]);

			return Result;
		},
		/**
		 * 模板操作
		 */
		tmpl : (function(){
			var cache = {};
 
			function tmpl(str, data){
				// Figure out if we're getting a template, or if we need to
				// load the template - and be sure to cache the result.
				var fn = !/\W/.test(str) ?
				  cache[str] = cache[str] ||
				    tmpl(document.getElementById(str).innerHTML) :
				 
				  // Generate a reusable function that will serve as a template
				  // generator (and which will be cached).
				  new Function("obj",
				    "var p=[],print=function(){p.push.apply(p,arguments);};" +
				   
				    // Introduce the data as local variables using with(){}
				    "with(obj){p.push('" +
				   
				    // Convert the template into pure JavaScript
				    str
				      .replace(/[\r\t\n]/g, " ")
				      .split("<%").join("\t")
				      .replace(/((^|%>)[^\t]*)'/g, "$1\r")
				      .replace(/\t=(.*?)%>/g, "',$1,'")
				      .split("\t").join("');")
				      .split("%>").join("p.push('")
				      .split("\r").join("\\'")
				  + "');}return p.join('');");

				// Provide some basic currying to the user
				return data ? fn( data ) : fn;
			};

			return tmpl;
		})()
	};

	root.Base = Base;

})(window.jQuery);
/**path:common/dialog.js**/
/**
 * dialog.js
 * @author liujiankang@dhgate.com
 * @date 2013-12-13
 */
(function($){

	var Dialog = DH.Base.create({
		type : null,
		minWidth : 400,
		zIndex : 100000,
		opacity : 0.5, // mask opacity
		template : {
			common : [
					'<table class="noshade-pop-table j-dialog-container">',
				    	'<tbody><tr>',
				        '<td class="t-lt"></td>',
				        '<td class="t-mid"></td>',
				        '<td class="t-ri"></td>',
				    	'</tr>',
				    	'<tr>',
				        '<td class="m-lt"></td>',
				        '<td class="m-mid">',
				         '<div class="mid-warp">',
				              '<div class="noshade-pop-content">',
				                    '<div class="noshade-pop-title j-dialog-title-container">',
				                        '<span class="j-dialog-title">操作提示</span>',
				                    '</div>',
				                    '<div class="noshade-pop-inner j-dialog-inner-container">',
				                    '</div>',
				                    '<div class="noshade-pop-bot j-dialog-bot-container"></div>',
				                '</div>',
				                '<a href="javascript:;" class="noshade-pop-close j-dialog-closer"></a>',
				            '</div>',
				        '</td>',
				        '<td class="m-ri"></td>',
				    '</tr>',
				    '<tr>',
				        '<td class="b-lt"></td>',
				        '<td class="b-mid"></td>',
				        '<td class="b-ri"></td>',
				    '</tr>',
					'</tbody></table>'
				].join(''),
			btn : '<span class="<%=className%>"><input type="button" value="<%=text%>"></span>',
			ac : [
					'<div class="box1 align-center clearfix">',
	                '<p class="j-dialog-msg"></p>',
	                '<div class="align-center pop-button clearfix j-dialog-btns">',
	                '</div>',	                
                    '</div>'
				].join(''),
			loading : [
					'<div class="box1 align-center clearfix">',
					'<p>',
	                '<span class="noshade-pop-loading"></span>',
	                '<span class="j-dialog-msg"></span>',
	            	'</p>',
	            	'</div>'
				].join('')
		},
		isIE6 : /msie\s*6.0/.test(navigator.userAgent.toLowerCase()),
		init : function(){
			this.initEl();
			this.initEv();
			return this;
		},
		initEl : function(){
			var self = this,
				rnd = new Date - 0
				;

			this.pev = {}; // cache the default show or hide callback

			this.el = $('<div>');

			this.code = rnd;

			this.el
				.addClass('dh-dialog-' + rnd)
				.html(this.template.common)
				.hide()
				;

			this.csDialogC = '.j-dialog-container';
			this.csTitleC = '.j-dialog-title-container';
			this.csTitle = '.j-dialog-title';
			this.csInnerC = '.j-dialog-inner-container';
			this.csCloser = '.j-dialog-closer';
			this.csBotC = '.j-dialog-bot-container';

			this.csMsg = '.j-dialog-msg';
			this.csBtns = '.j-dialog-btns';

			Dialog.records = Dialog.records || {}; // 记录当前弹层数
			Dialog.records[this.code] = this;

			Dialog.showRecords = Dialog.showRecords || []; // 记录当前显示的弹层

			$(function(){
				if(self.options.isClass !== true){
					$(document.body).append(self.el);
				}
			});
		},
		initEv : function(){
			var self = this;
			this.el.delegate('.j-dialog-closer', 'click', $.proxy(this.hide, this));
			$(window).bind('resize', $.proxy(this._setPos, this));
			//按esc键关闭层
			if(!Dialog.isBindEsc){
				$(document).bind('keyup', function(e){
					if(27 === e.keyCode){
						if(Dialog.showRecords.length === 0) return;
						Dialog.showRecords[Dialog.showRecords.length - 1].hide();
					}
				});
				Dialog.isBindEsc = true;
			}
		},
		_reset : function(){
			var self = this,
				options = this._options
				;

			this.el.find(this.csCloser).is(':hidden') && this.el.find(this.csCloser).show();
			this.el.find(this.csTitleC).is(':hidden') && this.el.find(this.csTitleC).show();
			this.el.find(this.csBtns).is(':hidden') && this.el.find(this.csBtns).show();
			this.el.find(this.csBotC).is(':hidden') && this.el.find(this.csBotC).show();

			this.el.find(this.csTitle).text(options.title || '操作提示');

			if(this.el.find(this.csBtns).length){
				if(options.btns && options.btns.length){
					$(options.btns).each(function(m, n){
						var _btn = $(self.tmpl(self.template.btn, {
							text : n.text,
							className : n.className
						}));
						n.fn && _btn.bind('click', $.proxy(n.fn, self));
						self.el.find(self.csBtns).append(_btn);
					});
				}else if(this.type === 'alert'){
					var _btn = $(self.tmpl(self.template.btn, {
						text : '确定',
						className : 'yellowBtn valmiddle'
					}));
					_btn.bind('click', $.proxy(this.hide, this));
					this.el.find(this.csBtns).append(_btn);
				}else{
					this.el.find(this.csBtns).hide();
				}
			}

			//保存默认show callback
			if(this.ev['show'] && !this.ev['defaultShow']){
				this.ev['defaultShow'] = this.ev['show'];
			}

			//保存默认hide callback
			if(this.ev['hide'] && !this.ev['defaultHide']){
				this.ev['defaultHide'] = this.ev['hide'];
			}

			//show or hide
			this.ev['show'] = options.show;

			this.ev['hide'] = options.hide;
		},
		loading : function(msg){
			var self = this;
			this.type = 'loading';
			this._options = {};
			this._reset();
			this.show();
			this.el.find(this.csCloser).hide();
			this.el.find(this.csTitleC).hide();
			this.el.find(this.csBtns).hide();
			this.el.find(this.csBotC).hide();
			this.el.find(this.csInnerC).html(this.template.loading);
			this.el.find(this.csMsg).html(msg);
		},
		alert : function(msg, options){
			var self = this;
			this.type = 'alert';
			this._options = options || {};
			this.el.find(this.csInnerC).html(this.template.ac);
			this.el.find(this.csMsg).html(msg);
			this._reset();
			this.show();
		},
		confirm : function(msg, options){
			var self = this;
			this.type = 'confirm';
			this._options = options || {};
			this.el.find(this.csInnerC).html(this.template.ac);
			this.el.find(this.csMsg).html(msg);
			this._reset();
			this.show();
		},
		html : function(selector, options){
			var self = this;
			this.type = 'html';
			this._options = options || {};
			this.selector = typeof selector === 'string' ? $(selector) : selector;
			this.el.find(this.csInnerC).empty().append(this.selector.show());
			this._reset();			
			this.show();
		},
		show : function(){
			var self = this,
				masker,
				dialog = this.el,
				timeout = this._options.timeout
				;

			this.timeout && (clearTimeout(this.timeout), (this.timeout = null));

			if(!dialog) return;

			if(!this.elMasker){
				masker = this.elMasker = $("<div>");
				masker
					.css({
						position : 'fixed',
						width : '100%',
						height : '100%',
						left : 0,
						top : 0,
						backgroundColor : '#000',
						opacity : this.opacity,
						zIndex : this.zIndex
					})
					;

				//fix ie6
				if(this.isIE6){
					masker
						.css({
							position : 'absolute',
							width : $(window).width(),
							height : Math.max($(window).height(), $(document.body).height())
						})
						;
												
					var _iframe = $("<iframe>");
					_iframe
						.css({
							position : 'absolute',
							width : $(window).width(),
							height : Math.max($(window).height(), $(document.body).height()),
							left : 0,
							top : 0,
							opacity : 0
						})
						.attr({
							src : 'about:blank'
						})
						;
					masker.append(_iframe);
				}

				this.el.before(masker);
			}else{
				this.elMasker.show();
			}

			dialog
				.show()
				.css({
					position : 'absolute',					
					zIndex : this.zIndex
				})
				;

			this._setCss();

			this._setPos();

			this.trigger('show');

			this.trigger('defaultShow');

			delete this.ev['show'];

			timeout && !isNaN(timeout) && (this.timeout = setTimeout(function(){
				self.el.is(':visible') && self.hide();
			}, timeout));

			Dialog.showRecords.push(this);
		},
		hide : function(){
			if(this.el.is(':hidden')) return;
			this.el.hide();
			this.elMasker.hide();
			this.type === 'html' && $(document.body).append(this.selector.hide()); // 把html还原到boby上
			this.trigger('hide');
			this.trigger('defaultHide');
			delete this.ev['hide'];
			Dialog.showRecords.pop();
		},
		destory : function(){
			this.el && this.el.remove();
			this.elMasker && this.elMasker.remove();
			this.trigger('destory');
			delete Dialog.records[this.code];
		},
		_setCss : function(){
			var w = this.el.find(this.csInnerC).children().eq(0).outerWidth(true);

			this.el.find(this.csDialogC).children().eq(0).css('width', Math.max(w, this.minWidth));

			w = this.el.find(this.csDialogC).children().eq(0).outerWidth(true);

			this.el.find(this.csDialogC).css('width', Math.max(w, this.minWidth));

			this.el.css('width', this.el.find(this.csDialogC).outerWidth(true));
		},
		_setPos : function(){
			if(this.el.is(':hidden')) return;

			var h = this.el.height(),
				w = this.el.width()
				;

			this.el
				.css({
					top : $(window).height() / 2 - h / 2 + $(window).scrollTop() + 'px',
					left : $(window).width() / 2 - w / 2 + 'px'
				})
				;

			if(this.elMasker){
				if(this.isIE6){
					this.elMasker
						.css({
							width : $(window).width(),
							height : Math.max($(window).height(), $(document.body).height())
						})
						;
				}else{					
					this.elMasker
						.css({
							width : '100%',
							height : '100%'
						})
						;
				}
			}
		}
	});

	DH.Widget.Dialog = Dialog;

	//var dialog = new DH.Widget.Dialog();
	//dialog.alert|confirm|html(string|$, options);

})(window.jQuery);