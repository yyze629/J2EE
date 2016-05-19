var alertDialog = new DH.Widget.Dialog();

var ProdBatchUpate = function(options) {
	this.SetOptions(options);
	
	this.empty = "";
	this.blank = " ";
	this.perPack = 5;
	this.baifenhao = "%";
	this.meiyuanLogo = "$";
	this.customAttr = "cval";
	this.leadingtimeUnit = "天";
	this.meiyuan = "美元";
	this.inputNullTipMsg = "输入不能为空";
	this.maxSellerPrice = 999999;
	this.minSellerPrice = 0.01;
	this.priceIllegalMsg = "对不起，产品价格区间应为[0.01~999999.00]，此产品修改后，价格已超出规定区间，不能修改价格。";
	this.retailPriceLogo="<span class=\"retail\"></span>";
	this.wholesalePriceLogo="<span class=\"wholesale\"></span>";
	this.isDisplayAttrValue_True = "true";
	this.isDisplayAttrValue_False = "false";
	this.isDisplayAttrName = "isDisplay";
	this.productInfo = "tr[name='productInfo']";
	this.closeXInDiv = "a[name='divCloseX']";
	this.cancelButtonInDiv = "input[name='divCancelButton']";
	this.batchDivAhref = "a[name='batchDivAhref']";
	this.delSourceProductAhref = "delSourceProductAhref";
	this.bBatchProductsQuantity = '#bBatchProductsQuantity';
	
	this.addMoreButton = "input[name='addMoreButton']";
	this.addMoreIsRunning = false;
	this.addMoreUrl = "/prodmanage/batchupdate/getMoreProductList.do";
	this.excludeProductParamName = "batchUpateForm.excludeProdItemcodes";
	this.addMoreOpenConfig = "height=" + (window.screen.availHeight - this.reverseHeight) + "px, width="+ (window.screen.availWidth - this.reverseWidth) +"px, top=" + (this.reverseHeight - 30) / 2 + "px, left="+ (this.reverseWidth - 10) / 2 + "px, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=n o, status=no";
	this.batchProductsTable = "#batchProductsTable";
	
	this.lastUpdateType_Default = "0";/** 未修改 */
	this.lastUpdateType_UpdatePrice = "1";/** 批量修改价格 */
	this.lastUpdateType_UpdatePrice_Config = "2";/** 批量设置价格 */
	this.lastUpdateType_Leadingtime = "3";/** 批量设置备货期 */
	this.lastUpdateType_ShippingTemplate = "4";/** 批量设置运费模板 */
	this.lastUpdateType_UpdatePrice_Config_Wholesale = "5";/** 批量设置价格,只是批发 */
	this.lastUpdateType_Discount_Config = "6";/** 批量设置折扣 */
	
	
	
	this.priceUpdateType_Amount_Cfg = "1";/** 按金额批量统一设置价格 */
	this.priceUpdateType_Amount_Add = "2";/** 按金额批量增加价格 */
	this.priceUpdateType_Amount_Reduce = "3";/** 按金额批量减少价格 */
	this.priceUpdateType_Percent_Add = "4";/** 按百分比批量增加价格 */
	this.priceUpdateType_Percent_Reduce = "5";/** 按百分比批量减少价格 */
	this.priceUpdateType_Amount_Cfg_Wholesale = "6";/** 按金额批量统一设置价格，只批发不零售。 */
	this.priceUpdateType_Amount_Cfg_Retail = "7";/** 按金额批量统一设置价格，批发+零售。 */
	this.priceUpdateType_Discount_Cfg = "8";/** 统一设置折扣信息。 */
	
	
	this.retailPriceRangeErrorInfo = "价格输入范围为0.01~999999";
	
	//==================运费模板====start============
	this.bShippingTemplate = "#bShippingTemplate";
	this.bShippingTemplateDiv = "#bShippingTemplateDiv";
	this.batchShippingTemplateConfirmButton = "#batchShippingTemplateConfirmButton";
	this.batchShippingTemplateList = "#batchShippingTemplateList";
	this.shippingTemplateTd = "shippingTemplateTd";
	this.selectedText = "option:selected";
	//==================运费模板====end============
	
	this.bPriceBulkOperations = "#bPriceBulkOperations";
	this.bPriceBulkoperationsLayer = "#bPriceBulkoperationsLayer";
	this.bPriceUpdate = "#bPriceUpdate";
	this.productPrice = "productPrice";
	this.bPriceUpdateDiv = "#bPriceUpdateDiv";
	this.bPriceUpdateConfigValue = "#bPriceUpdateConfigValue";
	this.bPriceUpdateConfigType = "#bPriceUpdateConfigType";
	this.bPriceUpdateDivErr = "#bPriceUpdateDivErr";
	this.bPriceUpdateConfirmButton = "#bPriceUpdateConfirmButton";
	this.bPriceUpdateConfigType_percentAdd = "percentAdd";
	this.bPriceUpdateConfigType_percentReduce = "percentReduce";
	this.bPriceUpdateConfigType_update = "update";
	this.bPriceUpdateConfigType_priceAdd = "priceAdd";
	this.bPriceUpdateConfigType_priceReduce = "priceReduce";
	this.bPriceUpdateConfigValueUnit = "#bPriceUpdateConfigValueUnit";
	
	
	//this.bPriceConfig = "#bPriceConfig";
	//this.bPriceConfigDiv = "#bPriceConfigDiv";
	//this.bPriceConfigWholesaleNum = "#bPriceConfigWholesaleNum";
	//this.bPriceConfigWholesaleFirstRangeNum = "#bPriceConfigWholesaleFirstRangeNum";
	//this.bPriceConfigWholesaleFirstRangePrice = "#bPriceConfigWholesaleFirstRangePrice";
	//this.bPriceConfigRangeNum = "input[name='bPriceConfigRangeNum']";
	//this.bPriceConfigRetailValue = "#bPriceConfigRetailValue";
	//this.bPriceConfigRangeDiscount = "input[name='bPriceConfigRangeDiscount']";
	//this.bPriceConfigRetailFirstRangeDiscount = "#bPriceConfigRetailFirstRangeDiscount";
	//this.bPriceConfigWholesaleCheckboxId = "#bPriceConfigWholesaleCheckbox";
	//this.bPriceConfigWholesaleCheckbox = "#bPriceConfigWholesaleCheckbox";
	//this.bPriceConfigWholesaleP = "#bPriceConfigWholesaleP";
	//this.bPriceConfigWholesaleFirstRangeP = "#bPriceConfigWholesaleFirstRangeP";
	//this.bPriceConfigWholesaleSecondRangeP = "#bPriceConfigWholesaleSecondRangeP";
	//this.bPriceConfigWholesaleThirdRangeP = "#bPriceConfigWholesaleThirdRangeP";
	//this.bPriceConfigRetailP = "#bPriceConfigRetailP";
	//this.bPriceConfigRetailFirstRangeP = "#bPriceConfigRetailFirstRangeP";
	//this.bPriceConfigRetailSecondRangeP = "#bPriceConfigRetailSecondRangeP";
	//this.bPriceConfigRetailThirdRangeP = "#bPriceConfigRetailThirdRangeP";
	//this.addMorePriceConfig = "#addMorePriceConfig";
	//this.bPriceConfigRetailSecondRangeDel = "#bPriceConfigRetailSecondRangeDel";
	//this.bPriceConfigWholesaleSecondRangeDel = "#bPriceConfigWholesaleSecondRangeDel";
	//this.deletePriceConfig = "a[name='deletePriceConfig']";
	//this.bPriceConfigConfirmButton = "#bPriceConfigConfirmButton";
	
	//discount  start
	this.bDiscountConfig = "#bDiscountConfig";
	this.bDiscountConfigDiv = "#bDiscountConfigDiv";
	this.bDiscountConfigRangeNum = "input[name='bDiscountConfigRangeNum']";
	this.bDiscountConfigRangeDiscount = "input[name='bDiscountConfigRangeDiscount']";
	this.bDiscountConfigSecondRangeDel = "#bDiscountConfigSecondRangeDel";
	this.bDiscountConfigThirdRangeDel = "#bDiscountConfigThirdRangeDel";
	this.addMoreDiscountConfig = "#addMoreDiscountConfig";
	this.bDiscountConfigSecondRangeP = "#bDiscountConfigSecondRangeP";
	this.bDiscountConfigThirdRangeP  = "#bDiscountConfigThirdRangeP";
	this.bDiscountConfigConfirmButton = "#bDiscountConfigConfirmButton";
	this.bDiscountConfigMinWholesaleQtyNum = "#bDiscountConfigMinWholesaleQtyNum";
	//discount  end
	
	//==================备货期====start============
	this.bLeadingtime = "#bLeadingtime";
	this.bLeadingtimeDiv = "#bLeadingtimeDiv";
	this.bLeadingtimeConfigValue = "#bLeadingtimeConfigValue";
	this.bLeadingtimeDivErr = "#bLeadingtimeDivErr";
	this.bLeadingtimeConfirmButton = "#bLeadingtimeConfirmButton";
	this.bLeadingtimeConfigType = "#bLeadingtimeConfigType";
	this.productLeadingtime = "productLeadingtime";
	this.bLeadingtimeConfigType_add = "add";
	this.bLeadingtimeConfigType_reduce = "reduce";
	//==================备货期====end============
	
	this.batchModifyConfirmButton = "#batchModifyConfirmButton";
	this.batchReleaseButtonDiv = '#batchReleaseButtonDiv';
	this.batchModifyRmd = '#batchModifyRmd';
	this.saveProductsUrl = "/prodmanage/batchupdate/batchSaveProdUpdate.do";
	this.batchModifyDiv = '#batchModifyDiv';
	this.saveProductsButton = "input[name='saveProductsButton']";
	
	this.InitProdBatchUpate();
};
ProdBatchUpate.prototype = {
	SetOptions : function(options) {
		this.options = {};
		$.extend(this.options, options || {});
	},
	SaveUpdateProducts : function(){
		var _this = this;
		$(_this.batchModifyConfirmButton).click(function(){
			var cnt = 0;
			$(_this.batchReleaseButtonDiv).hide();
			$(_this.batchModifyRmd).text("正在保存修改，请您耐心等待……");
			var ary = _this.AssembleProdBatchUpdateBizDTO();
			if (ary.length > 0) {
				var productArrayPack = [];
				var productArrayPackTest ;
				for ( var i = 0; i < ary.length; i++) {
					productArrayPack[i % _this.perPack] = JSON.stringify(ary[i]);
					if (((i + 1) % _this.perPack == 0) || ((i + 1) == ary.length)) {
						productArrayPackTest =  productArrayPack.join("#");
						$.ajax({
							type : "POST",
							async : false,
							url : _this.saveProductsUrl,
							data : {
								"batchUpateForm.productArray" : productArrayPack,
								"batchUpateForm.productArrayStr" : productArrayPack.join("#")
							},
							dataType : "json",
							timeout : 60000,
							success : function(data) {
								cnt = cnt + parseInt(data.cnt);
							},
							error : function(data, status, e) {
							}
						});
						productArrayPack = [];
					}
				}
				$(_this.batchReleaseButtonDiv).hide();
				$("#batchReleaseButtonAfterDiv").show();
				$(_this.batchModifyRmd).text("您已经成功保存对产品的修改").show();
				window.opener.document.location.reload();
			}else{
				$(_this.batchReleaseButtonDiv).hide();
				$("#batchReleaseButtonAfterDiv").show();
				$(_this.batchModifyRmd).text("您当前没有需要保存修改的产品");
			}
		});
		$("#batchModifySuccessBtn").click(function(){
			var ids = "";
			$(".proline").each(function(){ids += $(this).attr("id") + ",";});
			if(ids.length > 0){
				ids = ids.substring(0,ids.length - 1);
			}
			var url = "http://seller.dhgate.com/prodmanage/batchupdate/prodBatchUpate.do?batchUpateForm.prodItemcodes=" + ids;
			window.location.href = url;
			return true;
		});
		$(_this.saveProductsButton).click(function(){
			var leadingtimeModifyFailedCheck = _this.LeadingtimeModifyFailedCheck();
			if(leadingtimeModifyFailedCheck){
				alertDialog.alert('对不起，当前存在修改备货期失败的产品，不能提交修改，<br>请按备货期设置规则调整后，再进行“保存”操作。');
				return ;
			}
			var hasModifyPriceFaileCheck = _this.PriceModifyFailedCheck();
			if(hasModifyPriceFaileCheck){
				alertDialog.alert('对不起，当前存在修改价格失败的产品，不能提交修改，<br>请按价格设置规则调整后，再进行“保存”操作。');
				return ;
			}
			var isDataCorrect = _this.SaveUpdateProductCheck();
			if(isDataCorrect == false){
				alertDialog.alert('对不起，您当前没有需要保存修改的产品。');
				return ;
			}
			$(_this.batchModifyDiv).show().modal();
			$(_this.batchReleaseButtonDiv).show();
			$(_this.batchModifyRmd).text("确认保存修改吗？");
		});
	},
	LeadingtimeModifyFailedCheck : function(){
		var _this = this;
		var hasModify = false;
		var hasModifyFailed = false;
		var products = $("tr[name='productInfo']");
		if (_this.isValidElements(products)) {
			if (products.length > 0) {
				for ( var i = 0; i < products.length; i++) {
					var prodItemcode = $(products[i]).attr(_this.customAttr);
					var LtLastUpdateType = $("#lt_"+prodItemcode).attr("lastUpdateType");
					if(_this.isValidElements(LtLastUpdateType)){
						if(LtLastUpdateType != _this.lastUpdateType_Default){
							hasModify = true;
							break;
						}
					}
				}
				if(hasModify){
					for ( var i = 0; i < products.length; i++) {
						var prodItemcode = $(products[i]).attr(_this.customAttr);
						var LtLastUpdateType = $("#lt_"+prodItemcode).attr("lastUpdateType");
						if(_this.isValidElements(LtLastUpdateType)){
							if(LtLastUpdateType == "0"){
								hasModifyFailed = true;
								$("#err_info_"+prodItemcode).text("有库存产品，备货期输入范围为1~2天");
								$("#err_"+prodItemcode).show();
							}
						}
					}
				}
			}
		}
		return hasModifyFailed;
	},
	PriceModifyFailedCheck : function(){
		var _this = this;
		var hasModifyPrice = false;
		var hasModifyPriceFaile = false;
		var products = $("tr[name='productInfo']");
		if (_this.isValidElements(products)) {
			if (products.length > 0) {
				for ( var i = 0; i < products.length; i++) {
					var prodItemcode = $(products[i]).attr(_this.customAttr);
					var lastUpdateType = $("#pe_"+prodItemcode).attr("lastUpdateType");
					if(_this.isValidElements(lastUpdateType)){
						if(lastUpdateType == "1"){
							hasModifyPrice = true;
							break;
						}
					}
				}
				if(hasModifyPrice){
					for ( var i = 0; i < products.length; i++) {
						var prodItemcode = $(products[i]).attr(_this.customAttr);
						var lastUpdateType = $("#pe_"+prodItemcode).attr("lastUpdateType");
						if(_this.isValidElements(lastUpdateType)){
							if(lastUpdateType == "0"){
								$("#err_info_"+prodItemcode).text("此产品未进行价格调整，不能提交修改，请按价格设置规则调整后，再进行“保存”操作。");
								$("#err_"+prodItemcode).show();
								$("#pe_"+prodItemcode).attr({
									lastUpdateType : _this.lastUpdateType_Default
								});
								hasModifyPriceFaile = true;
							}
						}
					}
				}
			}
		}
		return hasModifyPriceFaile;
	},
	SaveUpdateProductCheck : function(){
		var _this = this;
		var isDataCorrect = true;
		var products = $("tr[name='productInfo']");
		if (_this.isValidElements(products)) {
			if (products.length > 0) {
				for ( var i = 0; i < products.length; i++) {
					var prodItemcode = $(products[i]).attr(_this.customAttr);
					var shippingmodeLastUpdateType = $("#st_"+prodItemcode).attr("lastUpdateType");
					if(_this.isValidElements(shippingmodeLastUpdateType)){
						if(shippingmodeLastUpdateType != _this.lastUpdateType_Default){
							var shippingmodelid = $("#st_"+prodItemcode).attr("cval");
							if(!_this.isValidElements(shippingmodelid) || shippingmodelid == "" ){
								$("#err_info_"+prodItemcode).text("运费模板不能为空");
								$("#err_"+prodItemcode).show();
								$("#st_"+prodItemcode).attr({
									lastUpdateType : _this.lastUpdateType_Default
								});
								isDataCorrect = false;
							}
						}
					}
					var lastUpdateType = $("#pe_"+prodItemcode).attr("lastUpdateType");
					if(_this.isValidElements(lastUpdateType)){
						if(lastUpdateType != "0" && lastUpdateType != "6"){
							var retailPrice = $("#updatePrice_"+prodItemcode).val();
							//var minWholesalePrice = $("#minWholesalePrice_"+prodItemcode).val();
							var updateTypeRetail = $("#updatePrice_"+prodItemcode).attr("updateType");
							//var updateTypeWholesale = $("#minWholesalePrice_"+prodItemcode).attr("updateType");
							if(!_this.isValidElements(retailPrice) && !_this.isValidElements(updateTypeRetail)){
								$("#err_info_"+prodItemcode).text("价格不能为空");
								$("#err_"+prodItemcode).show();
								$("#pe_"+prodItemcode).attr({
									lastUpdateType : _this.lastUpdateType_Default
								});
								isDataCorrect = false;
							}/*else{
								if(updateTypeWholesale != "6" && (retailPrice == "-1" || retailPrice == "" || retailPrice == "0")){
									$("#err_info_"+prodItemcode).text("价格不能为空");
									$("#err_"+prodItemcode).show();
									$("#pe_"+prodItemcode).attr({
										lastUpdateType : _this.lastUpdateType_Default
									});
									isDataCorrect = false;
								}
								if(updateTypeWholesale == "6" && (minWholesalePrice == "-1" || minWholesalePrice == "" || minWholesalePrice == "0")){
									$("#err_info_"+prodItemcode).text("价格不能为空");
									$("#err_"+prodItemcode).show();
									$("#pe_"+prodItemcode).attr({
										lastUpdateType : _this.lastUpdateType_Default
									});
									isDataCorrect = false;
								}
							}*/
						}else if(lastUpdateType == "6"){
							var minWholesaleQty = $("#minWholesaleQtyCfg_"+prodItemcode).val();
							if(minWholesaleQty == null || minWholesaleQty == "" || minWholesaleQty == "0"){
								$("#err_info_"+prodItemcode).text("最小销售数量不能为空");
								$("#err_"+prodItemcode).show();
								$("#pe_"+prodItemcode).attr({
									lastUpdateType : _this.lastUpdateType_Default
								});
								isDataCorrect = false;
							}
							var firstDis = $("#firstDis_"+prodItemcode).val();
							var firstDisNum = $("#firstDis_"+prodItemcode).attr("startQty");
							if(firstDis == null || firstDis =="0" || firstDisNum == null || firstDisNum =="0"){
								$("#err_info_"+prodItemcode).text("折扣不能为空");
								$("#err_"+prodItemcode).show();
								$("#pe_"+prodItemcode).attr({
									lastUpdateType : _this.lastUpdateType_Default
								});
								isDataCorrect = false;
							}
						}/*else if(lastUpdateType == "0"){
							$("#err_info_"+prodItemcode).text("此产品未进行价格调整，不能提交修改，请从当前列表中移除，继续在进行“保存”操作。");
							$("#err_"+prodItemcode).show();
							$("#pe_"+prodItemcode).attr({
								lastUpdateType : _this.lastUpdateType_Default
							});
							isDataCorrect = false;
						}*/
					}
					
					var leadingtime = $("#lt_"+prodItemcode).attr("cval");
					if(!_this.isValidElements(leadingtime) || leadingtime == "" ){
						$("#err_info_"+prodItemcode).text("备货期不能为空");
						$("#err_"+prodItemcode).show();
						$("#lt_"+prodItemcode).attr({
							lastUpdateType : _this.lastUpdateType_Default
						});
						isDataCorrect = false;
					}
				}
				if(isDataCorrect){
					var hasShippingmodeMod = false;
					var hasPriceMod = false;
					var hasLeadingtimeMod = false;
					for ( var i = 0; i < products.length; i++) {
						var prodItemcode = $(products[i]).attr(_this.customAttr);
						var shippingmodeLastUpdateType = $("#st_"+prodItemcode).attr("lastUpdateType");
						if(_this.isValidElements(shippingmodeLastUpdateType)){
							if(shippingmodeLastUpdateType != _this.lastUpdateType_Default){
								hasShippingmodeMod = true;
							}
						}
						var lastUpdateType = $("#pe_"+prodItemcode).attr("lastUpdateType");
						if(_this.isValidElements(lastUpdateType)){
							if(lastUpdateType != _this.lastUpdateType_Default){
								hasPriceMod = true;
							}
						}
						var LtLastUpdateType = $("#lt_"+prodItemcode).attr("lastUpdateType");
						if(_this.isValidElements(LtLastUpdateType)){
							if(LtLastUpdateType != _this.lastUpdateType_Default){
								hasLeadingtimeMod = true;
							}
						}
					}
					if(!hasShippingmodeMod && !hasPriceMod && !hasLeadingtimeMod){
						isDataCorrect = false;
					}
				}
			}
		}
		return isDataCorrect;
	},
	AddMore : function() {
		var _this = this;
		$(_this.addMoreButton).click(function() {
			if (!_this.addMoreIsRunning) {
				_this.addMoreIsRunning = true;
				if(_this.Wnd && !_this.Wnd.closed){
					_this.Wnd.focus();
				}else{
					//_this.Wnd = window.open(_this.addMoreUrl + _this.GetExcludeProducts(), "_blank", _this.addMoreOpenConfig);	
					_this.Wnd = window.open (_this.addMoreUrl + _this.GetExcludeProducts(),'newwindow','height=600,width=900,top=100,left='+(screen.width/2-450)+',toolbar=no,menubar=no,scrollbars=yes, resizable=yes,location=no, status=no');
				}
				_this.addMoreIsRunning = false;
			}
		});
	},
	AppendSelectedProduct : function(rsArray){
		var _this = this; 
		if (rsArray != null && rsArray.length > 0) {
			for ( var i = 0; i < rsArray.length; i++) { 
				$(_this.batchProductsTable).append($(rsArray[i][0]).removeAttr("style")).append($(rsArray[i][1]));
			}
		}
		$(_this.bBatchProductsQuantity).text(($(_this.productInfo)).length);
	},
	GetExcludeProducts : function(){
		var _this = this;
		var excludeProducts = "";
		var excludeProductObjs =  $(_this.productInfo);
		if (_this.isValidElements(excludeProductObjs)) {
			if (excludeProductObjs.length > 0) {
				var excludeProductsParam = "";
				for ( var i = 0; i < excludeProductObjs.length; i++) {
					if (i > 0) {
						excludeProductsParam += ",";
					}
					excludeProductsParam += $(excludeProductObjs[i]).attr(_this.customAttr);
				}
				if(excludeProductsParam.length > 0){
					excludeProducts = "?" + _this.excludeProductParamName + "=" + excludeProductsParam;
				}
			}
		}
		return excludeProducts;
	},
	PriceBulkOperationsMouse : function(){
		var _this = this;
		$(_this.bPriceBulkOperations).mouseenter(function(){
			$(_this.bPriceBulkoperationsLayer).show();
		});
		$(_this.bPriceBulkOperations).mouseleave(function(){
			$(_this.bPriceBulkoperationsLayer).hide();
		});
	},
	DeleteSourceProduct : function() {
		var _this = this;
		$("a[name='" + this.delSourceProductAhref + "']").live("click", function() {
			$("#" + $(this).attr(_this.customAttr)).remove();
			$("#err_" + $(this).attr(_this.customAttr)).remove();
			$(_this.bBatchProductsQuantity).text(($(_this.productInfo)).length);
		});
	},
	ShowBatchDiv : function() {
		var _this = this;
		$(_this.closeXInDiv).click(function() {
			$.modal.close();
			$("#" + $(this).attr(_this.customAttr)).hide();
			if (_this.isValidElements($("#" + $(this).attr(_this.customAttr) + "Err"))) {
				$("#" + $(this).attr(_this.customAttr) + "Err").html("").hide();
			}
		});
		$(_this.cancelButtonInDiv).click(function() {
			$.modal.close();
			$("#" + $(this).attr(_this.customAttr)).hide();
			if (_this.isValidElements($("#" + $(this).attr(_this.customAttr) + "Err"))) {
				$("#" + $(this).attr(_this.customAttr) + "Err").html("").hide();
			}
		});
		$(_this.batchDivAhref).click(function() {
			$("#" + $(this).attr("id") + "Div").show().modal();
		});
	},
	ShippingTemplate : function(){
		var _this = this;
		$(_this.bShippingTemplate).click(function(){
			$(_this.bShippingTemplateDiv).show().modal();
			var products = $("tr[name='productInfo']");
			if (_this.isValidElements(products)) {
				if (products.length > 0) {
					for ( var i = 0; i < products.length; i++) {
						var prodItemcode = $(products[i]).attr("cval");
						$("#err_"+prodItemcode).hide();
					}
				}
			}
		}); 
		$(_this.batchShippingTemplateConfirmButton).click(function(){
			if ($(_this.batchShippingTemplateList + ' option:selected').val() != null) {
				var shippingTemplateTds = $("td[name='" + _this.shippingTemplateTd + "']");
				var itemcodes = [];
				if (_this.isValidElements(shippingTemplateTds)) {
					if (shippingTemplateTds.length > 0) {
						for ( var i = 0; i < shippingTemplateTds.length; i++) {
							var prodItemcode = $(shippingTemplateTds[i]).attr("prodItemcode");
							itemcodes.push(prodItemcode);
						}
					}
				}
				var shippingmodelid = $(_this.batchShippingTemplateList + ' option:selected').val();
				$.ajax({
					type : "POST",
					async : false,
					url : "/prodmanage/batchupdate/checkProdInvenLocation.do?ver=" + new Date().getTime().toString(36),
					data : {
						"itemcodes":itemcodes.join(","),
						"shippingmodelid":shippingmodelid
					},
					dataType : "json",
					timeout : 60000,
					success : function(data) {
						if(data.flag == "1"){
							if (_this.isValidElements(shippingTemplateTds)) {
								if (shippingTemplateTds.length > 0) {
									for ( var i = 0; i < shippingTemplateTds.length; i++) {
										$(shippingTemplateTds[i]).attr({
											cval : $(_this.batchShippingTemplateList + ' option:selected').val()
										}).text($(_this.batchShippingTemplateList).find(_this.selectedText).text());
										$(shippingTemplateTds[i]).attr({
											lastUpdateType : _this.lastUpdateType_ShippingTemplate
										});
										var prodItemcode = $(shippingTemplateTds[i]).attr("prodItemcode");
										$("#"+prodItemcode).attr({isCommitUpdate:"false"});
									}
								}
							}
						}else{
							var failedProducts = data.FailedProducts;
							for (var i = 0; i < failedProducts.length; i++) {
								var failedProduct = failedProducts[i];
								$("#err_"+failedProduct.itemcode).show();
								$("#err_info_"+failedProduct.itemcode).text(failedProduct.message);
							}
						}
					},
					error : function(data, status, e) {
					}
				});
			}
			$.modal.close();
			$(_this.bShippingTemplateDiv).hide();
		});
		
	},
	Leadingtime : function(){
		var _this = this;
		$(_this.bLeadingtime).click(function(){
			$(_this.bLeadingtimeDiv).show().modal();;
		});
		$(_this.bLeadingtimeConfigValue).keyup(function(){
			$(_this.bLeadingtimeConfigValue).val($(_this.bLeadingtimeConfigValue).val().replace(/^[0]|\D/g, _this.empty));
			var ltValue = $(_this.bLeadingtimeConfigValue).val();
			if(parseInt(ltValue) > 60){
				var subLtValue = ltValue.substring(0,1);
				if(parseInt(subLtValue) > 6){
					$(_this.bLeadingtimeConfigValue).val(ltValue.substring(0,1));
				}else if(parseInt(subLtValue) == 6){
					var subLtValueZero = ltValue.substring(1,2);
					if(parseInt(subLtValueZero) > 0){
						$(_this.bLeadingtimeConfigValue).val(ltValue.substring(0,1));
					}else{
						$(_this.bLeadingtimeConfigValue).val(ltValue.substring(0,2));
					}
				}else{
					$(_this.bLeadingtimeConfigValue).val(ltValue.substring(0,2));
				}
			}
			$(_this.bLeadingtimeDivErr).hide();
		});
		$(_this.bLeadingtimeConfirmButton).click(function(){
			var ltConfigValueValid = $.trim($(_this.bLeadingtimeConfigValue).val());
			if(ltConfigValueValid.length == 0 || ltConfigValueValid == null){
				$(_this.bLeadingtimeDivErr).html(_this.inputNullTipMsg).show();
			}else{
				if($(_this.bLeadingtimeConfigType).val() != null && $(_this.bLeadingtimeConfigValue).val() != null){
					var ltConfigType = $(_this.bLeadingtimeConfigType).val();
					var ltConfigValue = $(_this.bLeadingtimeConfigValue).val();
					if(_this.isValidElements(ltConfigType) && _this.isValidElements(ltConfigValue)){
						var productLeadingtimes = $("td[name='" + _this.productLeadingtime + "']");
						if (_this.isValidElements(productLeadingtimes)) {
							if (productLeadingtimes.length > 0) {
								for ( var i = 0; i < productLeadingtimes.length; i++) {
									var prodItemcode =$(productLeadingtimes[i]).attr("prodItemcode");
									if(_this.isValidElements(prodItemcode)){
										$("#err_"+prodItemcode).hide();
									}
									var updateLeadingtimeValue = $(productLeadingtimes[i]).attr("defaultLeadingtime");
									var updateMaxLeadingtimeValue = $(productLeadingtimes[i]).attr("defaultMaxLeadingtime");
									var updateMinLeadingtimeValue = $(productLeadingtimes[i]).attr("defaultMinLeadingtime");
									
									if(isNaN(parseInt(updateLeadingtimeValue))){
										updateLeadingtimeValue = 0;
									}
									if(ltConfigType == _this.bLeadingtimeConfigType_add){
										updateLeadingtimeValue =  parseInt(updateMaxLeadingtimeValue) + parseInt(ltConfigValue);
									}else if(ltConfigType == _this.bLeadingtimeConfigType_reduce){
										updateLeadingtimeValue = parseInt(updateMinLeadingtimeValue) - parseInt(ltConfigValue);
									}else{
										updateLeadingtimeValue = parseInt(ltConfigValue);
									}
									var inventoryStatus = $(productLeadingtimes[i]).attr("inventoryStatus");
									if(_this.isValidElements(inventoryStatus)){
										if(inventoryStatus == "1"){
											if(parseInt(updateLeadingtimeValue) >=1 && parseInt(updateLeadingtimeValue) <= 2){
												$(productLeadingtimes[i]).attr({
													cval : updateLeadingtimeValue
												}).text(updateLeadingtimeValue + _this.leadingtimeUnit);
												$(productLeadingtimes[i]).attr({
													lastUpdateType : _this.lastUpdateType_Leadingtime
												});
											}else{
												$("#err_info_"+prodItemcode).text("有库存产品，备货期输入范围为1~2天");
												$("#err_"+prodItemcode).show();
												$(productLeadingtimes[i]).attr({
													lastUpdateType : _this.lastUpdateType_Default
												});
											}
										}else{
											if(parseInt(updateLeadingtimeValue) >=1 && parseInt(updateLeadingtimeValue) <= 60){
												$(productLeadingtimes[i]).attr({
													cval : updateLeadingtimeValue
												}).text(updateLeadingtimeValue + _this.leadingtimeUnit);
												$(productLeadingtimes[i]).attr({
													lastUpdateType : _this.lastUpdateType_Leadingtime
												});
											}else{
												$("#err_info_"+prodItemcode).text("无库存产品，备货期输入范围为1~60天");
												$("#err_"+prodItemcode).show();
												$(productLeadingtimes[i]).attr({
													lastUpdateType : _this.lastUpdateType_Default
												});
											}
										}
									}
									$("#"+prodItemcode).attr({isCommitUpdate:"false"});
									
								}
							}
						}
					}
				}
				$.modal.close();
				$(_this.bLeadingtimeDiv).hide();
			}
		});
	},
	CanUpatePrice : function(){
		var _this = this;
		var canUpdatePrice = true;
		var productPrices = $("td[name='" + _this.productPrice + "']");
		if (_this.isValidElements(productPrices)) {
			for ( var i = 0; i < productPrices.length; i++) {
				var prodItemcode = $(productPrices[i]).attr("prodItemcode");
				if(_this.isValidElements(prodItemcode)){
					$("#err_"+prodItemcode).hide();
				}
				var hasSkuMark = $("#hasSku_"+prodItemcode).val();
				if(_this.isValidElements(hasSkuMark)){
					if(hasSkuMark == 0){
						$("#err_info_"+prodItemcode).text("系统预计在24小时之内，完成该商品新价格的设置，请耐心等待！您也可以使用'产品管理'中的修改价格，设置该商品的新价格。");
						$("#err_"+prodItemcode).show();
						canUpdatePrice = false;
					}
				}else{
					$("#err_info_"+prodItemcode).text("系统预计在24小时之内，完成该商品新价格的设置，请耐心等待！您也可以使用'产品管理'中的修改价格，设置该商品的新价格。");
					$("#err_"+prodItemcode).show();
					canUpdatePrice = false;
				}
				var isPromotion = $("#pe_"+prodItemcode).attr("isPromotion");
				if(_this.isValidElements(isPromotion)){
					if(isPromotion == 1){
						$("#err_info_"+prodItemcode).text("您的产品参加了促销，不允许修改产品价格。");
						$("#err_"+prodItemcode).show();
						canUpdatePrice = false;
					}
				}
			}
		}
		return canUpdatePrice;
	},
	PriceUpdate : function(){
		var _this = this;
		$(_this.bPriceUpdate).click(function(){
			if(!_this.CanUpatePrice()){
				return;
			}
			$(_this.bPriceUpdateDiv).show().modal();
		});
		$(_this.bPriceUpdateConfigValue).keyup(function(){
			var priceUpdateConfigType = $(_this.bPriceUpdateConfigType).val();
			if(_this.isValidElements(priceUpdateConfigType)){
				if(priceUpdateConfigType == _this.bPriceUpdateConfigType_percentAdd || priceUpdateConfigType == _this.bPriceUpdateConfigType_percentReduce){
					$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
					var priceValue = $(this).val();
					if(_this.isValidElements(priceValue)){
						if(priceValue.length >= 2){
							if(parseInt(priceValue.substring(0,2)) == 0){
								$(this).val(_this.empty);
							}else{
								$(this).val(parseInt(priceValue.substring(0,2)));
							}
						}
					}
				}else{
					var priceValue = $(this).val();
					$(this).val($(this).val().replace(/^\D|[^0-9\.]|[\.]{2}/g, _this.empty));
					
					if(priceValue.indexOf(".") > 0 && priceValue.indexOf(".") < priceValue.length-1){
						$(this).val($(this).val().replace(/[\.]$/g, _this.empty));
						if(priceValue.match(/^[0-9]+[\.][0-9]{3}$/g) != null){
							$(this).val(priceValue.substring(0,priceValue.length-1));
						}
					}
					if(priceValue.match(/^[0][^\.]/g) != null){
						$(this).val(priceValue.substring(1,priceValue.length));
					}
					$(this).val($(this).val().replace(/^[0][\.][0]{2}/g, _this.empty));
					if(priceValue.indexOf(".") > 0){
						var intValue = priceValue.split(".")[0];
						var dcecimalValue = priceValue.split(".")[1];
						if(intValue.length > 6){
							$(this).val(intValue.substring(0,6));
						}
					}else{
						var intValue = $(this).val();
						if(intValue.length >= 6){
							$(this).val(intValue.substring(0,6));
						}
					}
					
				}
				$(_this.bPriceUpdateDivErr).hide();
			}
		});
		$(_this.bPriceUpdateConfirmButton).click(function(){
			var priceConfigValueValid = $.trim($(_this.bPriceUpdateConfigValue).val());
			if(priceConfigValueValid.length == 0 || priceConfigValueValid == null){
				$(_this.bPriceUpdateDivErr).html(_this.inputNullTipMsg).show();
			}else if(isNaN(parseFloat(priceConfigValueValid)) || parseFloat(priceConfigValueValid) <= 0  || parseFloat(priceConfigValueValid) > 999999){
				$(_this.bPriceUpdateDivErr).html("价格输入范围为0.01~999999").show();
			}else{
				var priceConfigValue = $.trim($(_this.bPriceUpdateConfigValue).val());
				var priceUpdateConfigType = $(_this.bPriceUpdateConfigType).val();
				if(_this.isValidElements(priceUpdateConfigType) && _this.isValidElements(priceConfigValue)){
					var productPrices = $("td[name='" + _this.productPrice + "']");
					if (_this.isValidElements(productPrices)) {
						for ( var i = 0; i < productPrices.length; i++) {
							var prodItemcode = $(productPrices[i]).attr("prodItemcode");
							if(_this.isValidElements(prodItemcode)){
								$("#err_"+prodItemcode).hide();
							}
							if(priceUpdateConfigType == _this.bPriceUpdateConfigType_update){
								_this.PriceUpdateTogetherUpdate(prodItemcode, priceConfigValue);
								
							}else if(priceUpdateConfigType == _this.bPriceUpdateConfigType_priceAdd){
								_this.PriceUpdatePriceAdd(prodItemcode, priceConfigValue);
								
							}else if(priceUpdateConfigType == _this.bPriceUpdateConfigType_priceReduce){
								_this.PriceUpdatePriceReduce(prodItemcode, priceConfigValue);
								
							}else if(priceUpdateConfigType == _this.bPriceUpdateConfigType_percentAdd){
								_this.PriceUpdatePercentAdd(prodItemcode, priceConfigValue);
								
							}else if(priceUpdateConfigType == _this.bPriceUpdateConfigType_percentReduce){
								_this.PriceUpdatePercentReduce(prodItemcode, priceConfigValue);
								
							}
						}
					}
				}
				$.modal.close();
				$(_this.bPriceUpdateDiv).hide();
			}
		});
		$(_this.bPriceUpdateConfigType).change(function(){
			var priceUpdateConfigType = $(_this.bPriceUpdateConfigType).val();
			if(_this.isValidElements(priceUpdateConfigType)){
				if(priceUpdateConfigType == _this.bPriceUpdateConfigType_percentAdd || priceUpdateConfigType == _this.bPriceUpdateConfigType_percentReduce){
					$(_this.bPriceUpdateConfigValueUnit).text(_this.baifenhao);
				}else{
					$(_this.bPriceUpdateConfigValueUnit).text(_this.meiyuan);
				}
			}
			$(_this.bPriceUpdateConfigValue).val(_this.empty);
		});
		
	},
	PriceUpdatePercentReduce : function(prodItemcode,updatePercent){
		var _this = this;
		_this.PriceUpdateOptionClean(prodItemcode);
		var maxDiscount = $("#DiscountRange_"+prodItemcode).attr("maxDiscount");
		var retailPrice = $("#retailPriceDisplay_"+prodItemcode);
		var lowestRetailPrice = $("#retailPrice_"+prodItemcode).attr("lowestRetailPrice");
		var highestRetailPrice = $("#retailPrice_"+prodItemcode).attr("highestRetailPrice");
		var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
		var minRevenuePrice = _this.CalculateMinRevenuePrice(_this.CalculateReducePercent(lowestRetailPrice,updatePercent), maxDiscount);
		var maxRevenuePrice = _this.CalculateReducePercent(highestRetailPrice,updatePercent);
		
		if(parseFloat(minRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(minRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		var minCatePubLimitPrice = $("#minCatePubLimitPrice_"+prodItemcode).val();
		if(parseFloat(minRevenuePrice) < parseFloat(minCatePubLimitPrice)){
			var msg = "对不起，产品最低价格不能低于所在类目允许的最低价格["+parseFloat(minCatePubLimitPrice)+"]，请重新设置价格！";
			$("#err_info_"+prodItemcode).text(msg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(parseFloat(maxRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(maxRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		
		if(minRevenuePrice == maxRevenuePrice){
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
		}else{
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
		}
		
		var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
		
		var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
		var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
		var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
		var maxBuyerPrice = _this.CalculateBuyerPrice(maxRevenuePrice, prodItemcode,minWholesaleQty);
		if(minBuyerPrice == maxBuyerPrice){
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
		}else{
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
		}
		
		$("#updatePrice_"+prodItemcode).val(updatePercent);
		$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
		$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Percent_Reduce});
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice});
		$("#"+prodItemcode).attr({isCommitUpdate:"false"});
	},
	PriceUpdatePercentAdd : function(prodItemcode,updatePercent){
		var _this = this;
		_this.PriceUpdateOptionClean(prodItemcode);
		var maxDiscount = $("#DiscountRange_"+prodItemcode).attr("maxDiscount");
		var retailPrice = $("#retailPriceDisplay_"+prodItemcode);
		var lowestRetailPrice = $("#retailPrice_"+prodItemcode).attr("lowestRetailPrice");
		var highestRetailPrice = $("#retailPrice_"+prodItemcode).attr("highestRetailPrice");
		var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
		var minRevenuePrice = _this.CalculateMinRevenuePrice(_this.CalculateAddPercent(lowestRetailPrice,updatePercent), maxDiscount);
		var maxRevenuePrice = _this.CalculateAddPercent(highestRetailPrice,updatePercent);
		
		if(parseFloat(minRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(minRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		var minCatePubLimitPrice = $("#minCatePubLimitPrice_"+prodItemcode).val();
		if(parseFloat(minRevenuePrice) < parseFloat(minCatePubLimitPrice)){
			var msg = "对不起，产品最低价格不能低于所在类目允许的最低价格["+parseFloat(minCatePubLimitPrice)+"]，请重新设置价格！";
			$("#err_info_"+prodItemcode).text(msg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(parseFloat(maxRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(maxRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		
		if(minRevenuePrice == maxRevenuePrice){
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
		}else{
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
		}
		
		var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
		
		var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
		var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
		var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
		var maxBuyerPrice = _this.CalculateBuyerPrice(maxRevenuePrice, prodItemcode,minWholesaleQty);
		if(minBuyerPrice == maxBuyerPrice){
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
		}else{
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
		}
		
		$("#updatePrice_"+prodItemcode).val(updatePercent);
		$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
		$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Percent_Add});
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice});
		$("#"+prodItemcode).attr({isCommitUpdate:"false"});
	},
	PriceUpdatePriceReduce : function(prodItemcode,updatePrice){
		var _this = this;
		_this.PriceUpdateOptionClean(prodItemcode);
		var maxDiscount = $("#DiscountRange_"+prodItemcode).attr("maxDiscount");
		var retailPrice = $("#retailPriceDisplay_"+prodItemcode);
		var lowestRetailPrice = $("#retailPrice_"+prodItemcode).attr("lowestRetailPrice");
		var highestRetailPrice = $("#retailPrice_"+prodItemcode).attr("highestRetailPrice");
		var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
		var minRevenuePrice = _this.CalculateMinRevenuePrice(_this.CalculateReducePrice(lowestRetailPrice,updatePrice), maxDiscount);
		var maxRevenuePrice = _this.CalculateReducePrice(highestRetailPrice,updatePrice);
		
		if(parseFloat(minRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(minRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		var minCatePubLimitPrice = $("#minCatePubLimitPrice_"+prodItemcode).val();
		if(parseFloat(minRevenuePrice) < parseFloat(minCatePubLimitPrice)){
			var msg = "对不起，产品最低价格不能低于所在类目允许的最低价格["+parseFloat(minCatePubLimitPrice)+"]，请重新设置价格！";
			$("#err_info_"+prodItemcode).text(msg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(parseFloat(maxRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(maxRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		
		if(minRevenuePrice == maxRevenuePrice){
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
		}else{
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
		}
		
		var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
		
		var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
		var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
		var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
		var maxBuyerPrice = _this.CalculateBuyerPrice(maxRevenuePrice, prodItemcode,minWholesaleQty);
		if(minBuyerPrice == maxBuyerPrice){
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
		}else{
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
		}
		
		$("#updatePrice_"+prodItemcode).val(updatePrice);
		$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
		$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Amount_Reduce});
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice});
		$("#"+prodItemcode).attr({isCommitUpdate:"false"});
	},
	PriceUpdatePriceAdd : function(prodItemcode,updatePrice){
		var _this = this;
		_this.PriceUpdateOptionClean(prodItemcode);
		var maxDiscount = $("#DiscountRange_"+prodItemcode).attr("maxDiscount");
		var retailPrice = $("#retailPriceDisplay_"+prodItemcode);
		var lowestRetailPrice = $("#retailPrice_"+prodItemcode).attr("lowestRetailPrice");
		var highestRetailPrice = $("#retailPrice_"+prodItemcode).attr("highestRetailPrice");
		var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
		var minRevenuePrice = _this.CalculateMinRevenuePrice(_this.CalculateAddPrice(lowestRetailPrice,updatePrice), maxDiscount);
		var maxRevenuePrice = _this.CalculateAddPrice(highestRetailPrice,updatePrice);
		if(parseFloat(minRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(minRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		var minCatePubLimitPrice = $("#minCatePubLimitPrice_"+prodItemcode).val();
		if(parseFloat(minRevenuePrice) < parseFloat(minCatePubLimitPrice)){
			var msg = "对不起，产品最低价格不能低于所在类目允许的最低价格["+parseFloat(minCatePubLimitPrice)+"]，请重新设置价格！";
			$("#err_info_"+prodItemcode).text(msg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(parseFloat(maxRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(maxRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(minRevenuePrice == maxRevenuePrice){
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
		}else{
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
		}
		
		var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
		
		var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
		var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
		var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
		var maxBuyerPrice = _this.CalculateBuyerPrice(maxRevenuePrice, prodItemcode,minWholesaleQty);
		if(minBuyerPrice == maxBuyerPrice){
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
		}else{
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
		}
		
		$("#updatePrice_"+prodItemcode).val(updatePrice);
		$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
		$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Amount_Add});
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice});
		$("#"+prodItemcode).attr({isCommitUpdate:"false"});
	},
	PriceUpdateTogetherUpdate : function(prodItemcode,updatePrice){
		var _this = this;
		_this.PriceUpdateOptionClean(prodItemcode);
		var maxDiscount = $("#DiscountRange_"+prodItemcode).attr("maxDiscount");
		$("#revenueMark_"+prodItemcode).show();
		$("#buyerMark_"+prodItemcode).show();
		var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
		
		var minRevenuePrice = _this.CalculateMinRevenuePrice(updatePrice, maxDiscount);
		var maxRevenuePrice = parseFloat(updatePrice).toFixed(2);
		
		if(parseFloat(minRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(minRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		var minCatePubLimitPrice = $("#minCatePubLimitPrice_"+prodItemcode).val();
		if(parseFloat(minRevenuePrice) < parseFloat(minCatePubLimitPrice)){
			var msg = "对不起，产品最低价格不能低于所在类目允许的最低价格["+parseFloat(minCatePubLimitPrice)+"]，请重新设置价格！";
			$("#err_info_"+prodItemcode).text(msg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		if(parseFloat(maxRevenuePrice) < parseFloat(_this.minSellerPrice) || parseFloat(maxRevenuePrice) > parseFloat(_this.maxSellerPrice)){
			$("#err_info_"+prodItemcode).text(_this.priceIllegalMsg);
			$("#err_"+prodItemcode).show();
			$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
			return ;
		}
		
		if(minRevenuePrice == maxRevenuePrice){
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
		}else{
			revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
		}
		var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
		
		var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
		var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
		var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
		var maxBuyerPrice = _this.CalculateBuyerPrice(updatePrice, prodItemcode,minWholesaleQty);
		
		if(minBuyerPrice == maxBuyerPrice){
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
		}else{
			buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
		}
		
		$("#updatePrice_"+prodItemcode).val(updatePrice);
		$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
		$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Amount_Cfg});
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice});
		$("#"+prodItemcode).attr({isCommitUpdate:"false"});
	},
	PriceUpdateOptionClean : function(prodItemcode){
		var _this = this;
		$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Default});
		var hasPrice = $("#hasPrice_"+prodItemcode).val();
		if(hasPrice == "true"){
			$("#revenueMark_"+prodItemcode).show();
			$("#buyerMark_"+prodItemcode).show();
		}else{
			$("#revenueMark_"+prodItemcode).hide();
			$("#buyerMark_"+prodItemcode).hide();
		}
		//var retailDefaultDisplay = $("#retailPriceDisplay_"+prodItemcode).attr("defaultDisplay");
		//if(retailDefaultDisplay == "true"){
		//	$("#retailPriceDisplay_"+prodItemcode).attr({isDisplay:true});
		//	$("#retailPriceDisplay_"+prodItemcode).show();
		//}else{
		//	$("#retailPriceDisplay_"+prodItemcode).attr({isDisplay:false});
		//	$("#retailPriceDisplay_"+prodItemcode).hide();
		//}
		//var wholesaleDefaultDisplay = $("#wholesalePriceDisplay_"+prodItemcode).attr("defaultDisplay");
		//if(wholesaleDefaultDisplay == "true"){
		//	$("#wholesalePriceDisplay_"+prodItemcode).attr({isDisplay:true});
		//	$("#wholesalePriceDisplay_"+prodItemcode).show();
		//}else{
		//	$("#wholesalePriceDisplay_"+prodItemcode).attr({isDisplay:false});
		//	$("#wholesalePriceDisplay_"+prodItemcode).hide();
		//}
		$("#updatePrice_"+prodItemcode).val("0");
		$("#updatePrice_"+prodItemcode).attr({isUpdate:false});
		$("#updatePrice_"+prodItemcode).attr({updateType:"0"});
		
		//$("#minWholesalePrice_"+prodItemcode).val("0");
		//$("#minWholesalePrice_"+prodItemcode).attr({isUpdate:false});
		//$("#minWholesalePrice_"+prodItemcode).attr({updateType:"0"});
		
		$("#minWholesaleQtyCfg_"+prodItemcode).val("0");
		$("#firstDis_"+prodItemcode).val("0");
		$("#firstDis_"+prodItemcode).attr({startQty:"0"});
		$("#firstDis_"+prodItemcode).attr({endQty:"0"});
		
		$("#secondDis_"+prodItemcode).val("0");
		$("#secondDis_"+prodItemcode).attr({startQty:"0"});
		$("#secondDis_"+prodItemcode).attr({endQty:"0"});
		
		$("#thirdDis_"+prodItemcode).val("0");
		$("#thirdDis_"+prodItemcode).attr({startQty:"0"});
		$("#thirdDis_"+prodItemcode).attr({endQty:"0"});
	},
	/*PriceConfig : function(){
		var _this = this;
		$(_this.bPriceConfig).click(function(){
			if(!_this.CanUpatePrice()){
				return ;
			}
			$(_this.bPriceConfigDiv).show().modal();
		});
		$(_this.bPriceConfigWholesaleNum).blur(function(){
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(!isNaN(parseInt(wholesaleNum))){
					if(parseInt(wholesaleNum) == 1){
						$(this).val( _this.empty);
						$(_this.bPriceConfigWholesaleFirstRangeNum).val( _this.empty);
					}
				}
			}
		});
		$(_this.bPriceConfigWholesaleNum).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(wholesaleNum.length >= 5){
					if(parseInt(wholesaleNum) == 100000 ){
						$(this).val(wholesaleNum.substring(0,5));
						$(_this.bPriceConfigWholesaleFirstRangeNum).val($(this).val());
						return ;
					}else if(parseInt(wholesaleNum) > 10000 ){
						$(this).val(wholesaleNum.substring(0,4));
						$(_this.bPriceConfigWholesaleFirstRangeNum).val($(this).val());
					}else{
						if(parseInt(wholesaleNum.substring(0,5)) == 0){
							$(this).val(_this.empty);
							$(_this.bPriceConfigWholesaleFirstRangeNum).val($(this).val());
						}
					}
				}
			}
			$(_this.bPriceConfigWholesaleFirstRangeNum).val($(this).val());
		});
		$(_this.bPriceConfigWholesaleFirstRangePrice).blur(function(){
			var wholesaleFirstRangePrice = $(this).val();
			if(_this.isValidElements(wholesaleFirstRangePrice)){
				if(!isNaN(parseFloat(wholesaleFirstRangePrice))){
					if(parseFloat(wholesaleFirstRangePrice) == 0){
						$(this).val( _this.empty);
					}
				}
			}
		});
		$(_this.bPriceConfigWholesaleFirstRangePrice).keyup(function(){
			var priceValue = $(this).val();
			$(this).val($(this).val().replace(/^\D|[^0-9\.]|[\.]{2}/g, _this.empty));

			if(priceValue.indexOf(".") > 0 && priceValue.indexOf(".") < priceValue.length-1){
				$(this).val($(this).val().replace(/[\.]$/g, _this.empty));
				if(priceValue.match(/^[0-9]+[\.][0-9]{3}$/g) != null){
					$(this).val(priceValue.substring(0,priceValue.length-1));
				}
			}
			if(priceValue.match(/^[0][^\.]/g) != null){
				$(this).val(priceValue.substring(1,priceValue.length));
			}
			$(this).val($(this).val().replace(/^[0][\.][0]{2}/g, _this.empty));
			if(priceValue.indexOf(".") > 0){
				var intValue = priceValue.split(".")[0];
				var dcecimalValue = priceValue.split(".")[1];
				if(intValue.length > 6){
					$(this).val(intValue.substring(0,6));
				}
			}else{
				var intValue = $(this).val();
				if(intValue.length >= 6){
					$(this).val(intValue.substring(0,6));
				}
			}
		});
		$(_this.bPriceConfigRangeNum).blur(function(){
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(!isNaN(parseInt(wholesaleNum))){
					if(parseInt(wholesaleNum) == 1){
						$(this).val( _this.empty);
					}
				}
			}
		});
		$(_this.bPriceConfigRangeNum).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(wholesaleNum.length >= 5){
					if(parseInt(wholesaleNum.substring(0,5)) == 100000 ){
						$(this).val(parseInt(wholesaleNum.substring(0,5)));
						return ;
					}else if(parseInt(wholesaleNum.substring(0,5)) > 10000 ){
						$(this).val(parseInt(wholesaleNum.substring(0,4)));
					}else{
						if(parseInt(wholesaleNum.substring(0,5)) == 0){
							$(this).val(_this.empty);
						}else{
							$(this).val(parseInt(wholesaleNum.substring(0,5)));
						}
					}
				}
			}
		});
		$(_this.bPriceConfigRetailValue).blur(function(){
			var retailValuePrice = $(this).val();
			if(_this.isValidElements(retailValuePrice)){
				if(!isNaN(parseFloat(retailValuePrice))){
					if(parseFloat(retailValuePrice) == 0){
						$(this).val( _this.empty);
					}
				}
			}
		});
		$(_this.bPriceConfigRetailValue).keyup(function(){
			var priceValue = $(this).val();
			$(this).val($(this).val().replace(/^\D|[^0-9\.]|[\.]{2}/g, _this.empty));

			if(priceValue.indexOf(".") > 0 && priceValue.indexOf(".") < priceValue.length-1){
				$(this).val($(this).val().replace(/[\.]$/g, _this.empty));
				if(priceValue.match(/^[0-9]+[\.][0-9]{3}$/g) != null){
					$(this).val(priceValue.substring(0,priceValue.length-1));
				}
			}
			if(priceValue.match(/^[0][^\.]/g) != null){
				$(this).val(priceValue.substring(1,priceValue.length));
			}
			$(this).val($(this).val().replace(/^[0][\.][0]{2}/g, _this.empty));
			if(priceValue.indexOf(".") > 0){
				var intValue = priceValue.split(".")[0];
				var dcecimalValue = priceValue.split(".")[1];
				if(intValue.length > 6){
					$(this).val(intValue.substring(0,6));
				}
			}else{
				var intValue = $(this).val();
				if(intValue.length >= 6){
					$(this).val(intValue.substring(0,6));
				}
			}
		});
		$(_this.bPriceConfigRangeDiscount).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var discountValue = $(this).val();
			if(_this.isValidElements(discountValue)){
				if(discountValue.length >= 2){
					if(parseInt(discountValue.substring(0,2)) == 0){
						$(this).val(_this.empty);
						$("#"+$(this).attr(_this.customAttr)).text(0);
					}else{
						$(this).val(discountValue.substring(0,2));
						var discountDisplay = (100-parseInt(discountValue.substring(0,2)))/10.0;
						$("#"+$(this).attr(_this.customAttr)).text(discountDisplay);
					}
					
				}else if(discountValue.length > 0){
					var discountDisplay = (100-parseInt(discountValue))/10.0;
					$("#"+$(this).attr(_this.customAttr)).text(discountDisplay);
				}
			}
		});
		$(_this.bPriceConfigRetailFirstRangeDiscount).keyup(function(){
			$(_this.bPriceConfigRetailFirstRangeDiscount).val($(_this.bPriceConfigRetailFirstRangeDiscount).val().replace(/\D/g, _this.empty));
			$(_this.bPriceConfigRetailFirstRangeDiscount).val($(_this.bPriceConfigRetailFirstRangeDiscount).val().replace(/^[0]/g, _this.empty));
			var discountValue = $(_this.bPriceConfigRetailFirstRangeDiscount).val();
			if(_this.isValidElements(discountValue)){
				if(!isNaN(parseInt(discountValue))){
					if(parseInt(discountValue) > 99){
						$(_this.bPriceConfigRetailFirstRangeDiscount).val(discountValue.substring(0,2));
					}
				}
			}
		});
		$(_this.bPriceConfigWholesaleCheckbox).click(function(){
			var wholesaleCheckboxValue = $(_this.bPriceConfigWholesaleCheckboxId).attr("checked");
			if(_this.isValidElements(wholesaleCheckboxValue)){
				if(wholesaleCheckboxValue == true ){
					var pcfgwholesale = $(_this.bPriceConfigWholesaleP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgwholesale) && pcfgwholesale == "1"){
						$(_this.bPriceConfigWholesaleP).show();
					}else{
						$(_this.bPriceConfigWholesaleP).hide();
					}
					var pcfgwholesaleFirst = $(_this.bPriceConfigWholesaleFirstRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgwholesaleFirst) && pcfgwholesaleFirst == "1"){
						$(_this.bPriceConfigWholesaleFirstRangeP).show();
					}else{
						$(_this.bPriceConfigWholesaleFirstRangeP).hide();
					}
					var pcfgwholesaleSecond = $(_this.bPriceConfigWholesaleSecondRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgwholesaleSecond) && pcfgwholesaleSecond == "1"){
						$(_this.bPriceConfigWholesaleSecondRangeP).show();
					}else{
						$(_this.bPriceConfigWholesaleSecondRangeP).hide();
					}
					var pcfgwholesaleThird = $(_this.bPriceConfigWholesaleThirdRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgwholesaleThird) && pcfgwholesaleThird == "1"){
						$(_this.bPriceConfigWholesaleThirdRangeP).show();
						$(_this.addMorePriceConfig).hide();
					}else{
						$(_this.bPriceConfigWholesaleThirdRangeP).hide();
						$(_this.addMorePriceConfig).show();
					}
					$(_this.bPriceConfigRetailP).hide();
					$(_this.bPriceConfigRetailFirstRangeP).hide();
					$(_this.bPriceConfigRetailSecondRangeP).hide();
					$(_this.bPriceConfigRetailThirdRangeP).hide();
				}else{
					$(_this.bPriceConfigWholesaleP).hide();
					$(_this.bPriceConfigWholesaleFirstRangeP).hide();
					$(_this.bPriceConfigWholesaleSecondRangeP).hide();
					$(_this.bPriceConfigWholesaleThirdRangeP).hide();
					
					var pcfgRetail = $(_this.bPriceConfigRetailP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgRetail) && pcfgRetail == "1"){
						$(_this.bPriceConfigRetailP).show();
					}else{
						$(_this.bPriceConfigRetailP).hide();
					}
					var pcfgRetailFirst = $(_this.bPriceConfigRetailFirstRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgRetailFirst) && pcfgRetailFirst == "1"){
						$(_this.bPriceConfigRetailFirstRangeP).show();
					}else{
						$(_this.bPriceConfigRetailFirstRangeP).hide();
					}
					var pcfgRetailSecond = $(_this.bPriceConfigRetailSecondRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgRetailSecond) && pcfgRetailSecond == "1"){
						$(_this.bPriceConfigRetailSecondRangeP).show();
					}else{
						$(_this.bPriceConfigRetailSecondRangeP).hide();
					}
					var pcfgRetailThird = $(_this.bPriceConfigRetailThirdRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgRetailThird) && pcfgRetailThird == "1"){
						$(_this.bPriceConfigRetailThirdRangeP).show();
						$(_this.addMorePriceConfig).hide();
					}else{
						$(_this.bPriceConfigRetailThirdRangeP).hide();
						$(_this.addMorePriceConfig).show();
					}
				}
			}
		});
		$(_this.addMorePriceConfig).click(function(){
			var wholesaleCheckboxValue = $(_this.bPriceConfigWholesaleCheckboxId).attr("checked");
			if(_this.isValidElements(wholesaleCheckboxValue)){
				if(wholesaleCheckboxValue == true ){
					var pcfgwholesaleSecond = $(_this.bPriceConfigWholesaleSecondRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgwholesaleSecond) && pcfgwholesaleSecond == "1"){
						$(_this.bPriceConfigWholesaleThirdRangeP).show();
						$(_this.bPriceConfigWholesaleThirdRangeP).attr({cval:1});
						$(_this.bPriceConfigWholesaleSecondRangeDel).hide();
						$(_this.addMorePriceConfig).hide();
					}else{
						$(_this.bPriceConfigWholesaleSecondRangeP).show();
						$(_this.bPriceConfigWholesaleSecondRangeP).attr({cval:1});
					}
					
				}else{
					var pcfgRetailSecond = $(_this.bPriceConfigRetailSecondRangeP).attr(_this.customAttr);
					if(_this.isValidElements(pcfgRetailSecond) && pcfgRetailSecond == "1"){
						$(_this.bPriceConfigRetailThirdRangeP).show();
						$(_this.bPriceConfigRetailThirdRangeP).attr({cval:1});
						$(_this.bPriceConfigRetailSecondRangeDel).hide();
						$(_this.addMorePriceConfig).hide();
					}else{
						$(_this.bPriceConfigRetailSecondRangeP).show();
						$(_this.bPriceConfigRetailSecondRangeP).attr({cval:1});
					}
					
				}
			}
		});
		$(_this.deletePriceConfig).click(function(){
			_this.PriceConfigHideErrorDiv();
			$("#" + $(this).attr(_this.customAttr)).attr({cval:0});
			$("#" + $(this).attr(_this.customAttr)).hide();
			var pcfgwholesaleThird = $(_this.bPriceConfigWholesaleThirdRangeP).attr(_this.customAttr);
			var pcfgRetailThird = $(_this.bPriceConfigRetailThirdRangeP).attr(_this.customAttr);
			if(_this.isValidElements(pcfgwholesaleThird) || _this.isValidElements(pcfgRetailThird)){
				if(pcfgwholesaleThird == "0" || pcfgRetailThird == "0"){
					$(_this.addMorePriceConfig).show();
				}
				if(pcfgwholesaleThird == "0"){
					$(_this.bPriceConfigWholesaleSecondRangeDel).show();
				}
				if(pcfgRetailThird == "0"){
					$(_this.bPriceConfigRetailSecondRangeDel).show();
				}
			}
		});
		$(_this.bPriceConfigConfirmButton).click(function(){
			_this.PriceConfigHideErrorDiv();
			var wholesaleCheckboxValue = $(_this.bPriceConfigWholesaleCheckboxId).attr("checked");
			if(_this.isValidElements(wholesaleCheckboxValue)){
				if(wholesaleCheckboxValue == true ){
					//只批发
					var isCorrect = _this.PriceConfigCheckWholesaleConfig();
					if(!isCorrect){
						return ;
					}
					var configWholesaleNum =  $("#bPriceConfigWholesaleNum").val();
					var configWholesaleFirstRangePrice =  $("#bPriceConfigWholesaleFirstRangePrice").val();
					var configWholesaleSecondRangeNum = 0;
					var configWholesaleSecondRangeDiscount = 0;
					var configWholesaleThirdRangeNum = 0;
					var configWholesaleThirdRangeDiscount = 0;
					var configWholesaleSecondRangeP = $("#bPriceConfigWholesaleSecondRangeP").attr("cval");
					if(_this.isValidElements(configWholesaleSecondRangeP)){
						if(configWholesaleSecondRangeP == "1"){
							configWholesaleSecondRangeNum = $("#bPriceConfigWholesaleSecondRangeNum").val();
							configWholesaleSecondRangeDiscount = $("#bPriceConfigWholesaleSecondRangeDiscount").val();
						}
					}
					var configWholesaleThirdRangeP = $("#bPriceConfigWholesaleThirdRangeP").attr("cval");
					if(_this.isValidElements(configWholesaleThirdRangeP)){
						if(configWholesaleThirdRangeP == "1"){
							configWholesaleThirdRangeNum = $("#bPriceConfigWholesaleThirdRangeNum").val();
							configWholesaleThirdRangeDiscount = $("#bPriceConfigWholesaleThirdRangeDiscount").val();
						}
					}
					var productPrices = $("td[name='" + _this.productPrice + "']");
					if (_this.isValidElements(productPrices)) {
						for ( var i = 0; i < productPrices.length; i++) {
							var prodItemcode = $(productPrices[i]).attr("prodItemcode");
							if(_this.isValidElements(prodItemcode)){
								$("#err_"+prodItemcode).hide();
							}
							_this.PriceUpdateOptionClean(prodItemcode);
							$("#retailPriceDisplay_"+prodItemcode).hide();
							var wholesalePriceDisplay = $("#wholesalePriceDisplay_"+prodItemcode);
							wholesalePriceDisplay.show();
							if(configWholesaleThirdRangeDiscount != 0){
								var discount = parseInt(configWholesaleThirdRangeDiscount)/100;
								var lowestWholesalePrice = _this.CalculateDiscountPrice(configWholesaleFirstRangePrice, discount);
								var highestWholesalePrice = parseFloat(configWholesaleFirstRangePrice);
								highestWholesalePrice = highestWholesalePrice.toFixed(2);
								if(lowestWholesalePrice == highestWholesalePrice){
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
								}else{
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice + "~" + _this.meiyuanLogo + highestWholesalePrice);
								}
							}else if(configWholesaleSecondRangeDiscount != 0){
								var discount = parseInt(configWholesaleSecondRangeDiscount)/100;
								var lowestWholesalePrice = _this.CalculateDiscountPrice(configWholesaleFirstRangePrice, discount);
								var highestWholesalePrice = parseFloat(configWholesaleFirstRangePrice);
								highestWholesalePrice = highestWholesalePrice.toFixed(2);
								if(lowestWholesalePrice == highestWholesalePrice){
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
								}else{
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice + "~" + _this.meiyuanLogo + highestWholesalePrice);
								}
							}else{
								var lowestWholesalePrice = parseFloat(configWholesaleFirstRangePrice);
								lowestWholesalePrice = lowestWholesalePrice.toFixed(2);
								wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
							}
							
							$("#minWholesaleQtyCfg_"+prodItemcode).val(configWholesaleNum);
							$("#minWholesalePrice_"+prodItemcode).val(configWholesaleFirstRangePrice);
							$("#minWholesalePrice_"+prodItemcode).attr({isUpdate:true});
							$("#minWholesalePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Amount_Cfg_Wholesale});
							if(configWholesaleSecondRangeNum != 0 && configWholesaleSecondRangeDiscount != 0){
								var discount = parseInt(configWholesaleSecondRangeDiscount)/100;
								$("#secondDis_"+prodItemcode).val(discount);
								$("#secondDis_"+prodItemcode).attr({startQty:configWholesaleSecondRangeNum});
							}
							if(configWholesaleThirdRangeNum != 0 && configWholesaleThirdRangeDiscount != 0){
								var discount = parseInt(configWholesaleThirdRangeDiscount)/100;
								$("#thirdDis_"+prodItemcode).val(discount);
								$("#thirdDis_"+prodItemcode).attr({startQty:configWholesaleThirdRangeNum});
							}
							$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice_Config_Wholesale});
							$("#"+prodItemcode).attr({isCommitUpdate:"false"});
						}
					}
				}else{
					//批发+零售
					var isCorrect = _this.PriceConfigCheckRetailConfig();	
					if(!isCorrect){
						return ;
					}
					var configRetailValue =  $("#bPriceConfigRetailValue").val();
					var configRetailFirstRangeNum = $("#bPriceConfigRetailFirstRangeNum").val();
					var configRetailFirstRangeDiscount = $("#bPriceConfigRetailFirstRangeDiscount").val();
					var configRetailSecondRangeNum = 0;
					var configRetailSecondRangeDiscount = 0;
					var configRetailThirdRangeNum = 0;
					var configRetailThirdRangeDiscount = 0;
					var configRetailSecondRangeP = $("#bPriceConfigRetailSecondRangeP").attr("cval");
					if(_this.isValidElements(configRetailSecondRangeP)){
						if(configRetailSecondRangeP == "1"){
							configRetailSecondRangeNum = $("#bPriceConfigRetailSecondRangeNum").val();
							configRetailSecondRangeDiscount = $("#bPriceConfigRetailSecondRangeDiscount").val();
						}
					}
					var configRetailThirdRangeP = $("#bPriceConfigRetailThirdRangeP").attr("cval");
					if(_this.isValidElements(configRetailThirdRangeP)){
						if(configRetailThirdRangeP == "1"){
							configRetailThirdRangeNum = $("#bPriceConfigRetailThirdRangeNum").val();
							configRetailThirdRangeDiscount = $("#bPriceConfigRetailThirdRangeDiscount").val();
						}
					}
					var productPrices = $("td[name='" + _this.productPrice + "']");
					if (_this.isValidElements(productPrices)) {
						for ( var i = 0; i < productPrices.length; i++) {
							var prodItemcode = $(productPrices[i]).attr("prodItemcode");
							if(_this.isValidElements(prodItemcode)){
								$("#err_"+prodItemcode).hide();
							}
							_this.PriceUpdateOptionClean(prodItemcode);
							var retailPriceDisplay = $("#retailPriceDisplay_"+prodItemcode);
							retailPriceDisplay.show();
							var retailPriceFormat = parseFloat(configRetailValue);
							retailPriceFormat = retailPriceFormat.toFixed(2);
							retailPriceDisplay.html(_this.retailPriceLogo + _this.meiyuanLogo + retailPriceFormat);
							var wholesalePriceDisplay = $("#wholesalePriceDisplay_"+prodItemcode);
							wholesalePriceDisplay.show();
							if(configRetailThirdRangeDiscount != 0){
								var discount = parseInt(configRetailThirdRangeDiscount)/100;
								var lowestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discount);
								var discountHighest = parseInt(configRetailFirstRangeDiscount)/100;
								var highestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discountHighest);
								if(lowestWholesalePrice == highestWholesalePrice){
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
								}else{
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice + "~" + _this.meiyuanLogo + highestWholesalePrice);
								}
							}else if(configRetailSecondRangeDiscount != 0){
								var discount = parseInt(configRetailSecondRangeDiscount)/100;
								var lowestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discount);
								var discountHighest = parseInt(configRetailFirstRangeDiscount)/100;
								var highestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discountHighest);
								if(lowestWholesalePrice == highestWholesalePrice){
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
								}else{
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice + "~" + _this.meiyuanLogo + highestWholesalePrice);
								}
							}else if(configRetailFirstRangeDiscount != 0){
								var discount = parseInt(configRetailFirstRangeDiscount)/100;
								var lowestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discount);
								var highestWholesalePrice = _this.CalculateDiscountPrice(configRetailValue, discount);
								if(lowestWholesalePrice == highestWholesalePrice){
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
								}else{
									wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice + "~" + _this.meiyuanLogo + highestWholesalePrice);
								}
							}else{
								var lowestWholesalePrice =  parseFloat(configRetailValue);
								lowestWholesalePrice = lowestWholesalePrice.toFixed(2);
								wholesalePriceDisplay.html(_this.wholesalePriceLogo + _this.meiyuanLogo + lowestWholesalePrice);
							}
							
							
							$("#updatePrice_"+prodItemcode).val(configRetailValue);
							$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
							$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Amount_Cfg_Retail});
							if(configRetailFirstRangeNum != 0 && configRetailFirstRangeDiscount != 0){
								$("#minWholesaleQtyCfg_"+prodItemcode).val("1");
								var discount = parseInt(configRetailFirstRangeDiscount)/100;
								$("#firstDis_"+prodItemcode).val(discount);
								$("#firstDis_"+prodItemcode).attr({startQty:configRetailFirstRangeNum});
							}
							if(configRetailSecondRangeNum != 0 && configRetailSecondRangeDiscount != 0){
								var discount = parseInt(configRetailSecondRangeDiscount)/100;
								$("#secondDis_"+prodItemcode).val(discount);
								$("#secondDis_"+prodItemcode).attr({startQty:configRetailSecondRangeNum});
							}
							if(configRetailThirdRangeNum != 0 && configRetailThirdRangeDiscount != 0){
								var discount = parseInt(configRetailThirdRangeDiscount)/100;
								$("#thirdDis_"+prodItemcode).val(discount);
								$("#thirdDis_"+prodItemcode).attr({startQty:configRetailThirdRangeNum});
							}
							$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_UpdatePrice_Config});
							$("#"+prodItemcode).attr({isCommitUpdate:"false"});
						}
					}
				}
			}
			$.modal.close();
			$(_this.bPriceConfigDiv).hide();
		});
	},*/
	/*PriceConfigCheckRetailConfig : function(){
		var _this = this;
		var isCorrect = true;
		var configRetailValue =  $("#bPriceConfigRetailValue").val();
		if(!_this.isValidElements(configRetailValue) || configRetailValue == ""){
			$("#bPriceConfigRetailErrTip").text("零售价不能为空");
			$("#bPriceConfigRetailErr").show();
			isCorrect = false;
		}
		if(isNaN(parseFloat(configRetailValue)) || parseFloat(configRetailValue) <= 0  || parseFloat(configRetailValue) > 999999){
			$("#bPriceConfigRetailErrTip").text("价格输入范围为0.01~999999");
			$("#bPriceConfigRetailErr").show();
			isCorrect = false;
		}
		var configRetailFirstRangeNum = $("#bPriceConfigRetailFirstRangeNum").val();
		var configRetailFirstRangeDiscount = $("#bPriceConfigRetailFirstRangeDiscount").val();
		if(!_this.isValidElements(configRetailFirstRangeNum) || configRetailFirstRangeNum == "" || !_this.isValidElements(configRetailFirstRangeDiscount) || configRetailFirstRangeDiscount == ""){
			var errorMsg = "";
			if((!_this.isValidElements(configRetailFirstRangeNum) || configRetailFirstRangeNum == "") && (!_this.isValidElements(configRetailFirstRangeDiscount) || configRetailFirstRangeDiscount == "")){
				errorMsg = "起批量和折扣不能为空";
			}else if(!_this.isValidElements(configRetailFirstRangeNum) || configRetailFirstRangeNum == ""){
				errorMsg = "起批量不能为空";
			}else if(!_this.isValidElements(configRetailFirstRangeDiscount) || configRetailFirstRangeDiscount == ""){
				errorMsg = "折扣不能为空";
			}
			$("#bPriceConfigRetailFirstRangeErrTip").text(errorMsg);
			$("#bPriceConfigRetailFirstRangeErr").show();
			isCorrect = false;
		}
		var configRetailSecondRangeP = $("#bPriceConfigRetailSecondRangeP").attr("cval");
		if(_this.isValidElements(configRetailSecondRangeP)){
			if(configRetailSecondRangeP == "1"){
				var configRetailSecondRangeNum = $("#bPriceConfigRetailSecondRangeNum").val();
				var configRetailSecondRangeDiscount = $("#bPriceConfigRetailSecondRangeDiscount").val();
				if(_this.isValidElements(configRetailSecondRangeNum) || _this.isValidElements(configRetailSecondRangeDiscount)){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(configRetailSecondRangeNum))){
						configRetailSecondRangeNum = "0";
					}
					
					if(isNaN(parseInt(configRetailSecondRangeDiscount))){
						configRetailSecondRangeDiscount = "0";
					}
					
					if((parseInt(configRetailSecondRangeNum) <= parseInt(configRetailFirstRangeNum)) 
							&& (parseInt(configRetailSecondRangeDiscount) < parseInt(configRetailFirstRangeDiscount))){
						errorMsg = "不能小于上个区间的起批量和折扣";
						isError = true;
					}else if(parseInt(configRetailSecondRangeNum) <= parseInt(configRetailFirstRangeNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(parseInt(configRetailSecondRangeDiscount) <= parseInt(configRetailFirstRangeDiscount)){
						errorMsg = "不能小于上个区间的折扣";
						isError = true;
					}

					if(isError){
						$("#bPriceConfigRetailSecondRangeErrTip").text(errorMsg);
						$("#bPriceConfigRetailSecondRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		var configRetailThirdRangeP = $("#bPriceConfigRetailThirdRangeP").attr("cval");
		if(_this.isValidElements(configRetailThirdRangeP)){
			if(configRetailThirdRangeP == "1"){
				var configRetailSecondRangeNum = $("#bPriceConfigRetailSecondRangeNum").val();
				var configRetailSecondRangeDiscount = $("#bPriceConfigRetailSecondRangeDiscount").val();
				var configRetailThirdRangeNum = $("#bPriceConfigRetailThirdRangeNum").val();
				var configRetailThirdRangeDiscount = $("#bPriceConfigRetailThirdRangeDiscount").val();
				if((_this.isValidElements(configRetailSecondRangeNum) && _this.isValidElements(configRetailSecondRangeDiscount)) 
						&& (_this.isValidElements(configRetailThirdRangeNum) || _this.isValidElements(configRetailThirdRangeDiscount))){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(configRetailThirdRangeNum)) ){
						configRetailThirdRangeNum = "0";
					}
					if(isNaN(parseInt(configRetailThirdRangeDiscount))){
						configRetailThirdRangeDiscount = "0";
					}
					if((parseInt(configRetailThirdRangeNum) <= parseInt(configRetailSecondRangeNum))
							&& (parseInt(configRetailThirdRangeDiscount) <= parseInt(configRetailSecondRangeDiscount))){
						errorMsg = "不能小于上个区间的起批量和折扣";
						isError = true;
					}else if(parseInt(configRetailThirdRangeNum) <= parseInt(configRetailSecondRangeNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(parseInt(configRetailThirdRangeDiscount) <= parseInt(configRetailSecondRangeDiscount)){
						errorMsg = "不能小于上个区间的折扣";
						isError = true;
					}
					if(isError){
						$("#bPriceConfigRetailThirdRangeErrTip").text(errorMsg);
						$("#bPriceConfigRetailThirdRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		return isCorrect ;
	},*/
	/*PriceConfigCheckWholesaleConfig : function(){
		var _this = this;
		var isCorrect = true;
		var configWholesaleNum =  $("#bPriceConfigWholesaleNum").val();
		if(!_this.isValidElements(configWholesaleNum) || configWholesaleNum == ""){
			$("#bPriceConfigWholesaleErrTip").text("最小起批量不能为空");
			$("#bPriceConfigWholesaleErr").show();
			isCorrect = false;
		}
		var configWholesaleFirstRangePrice =  $("#bPriceConfigWholesaleFirstRangePrice").val();
		if(!_this.isValidElements(configWholesaleFirstRangePrice) || configWholesaleFirstRangePrice == ""){
			$("#bPriceConfigWholesaleFirstRangeErrTip").text("批发价不能为空");
			$("#bPriceConfigWholesaleFirstRangeErr").show();
			isCorrect = false;
		}
		var configWholesaleSecondRangeP = $("#bPriceConfigWholesaleSecondRangeP").attr("cval");
		if(_this.isValidElements(configWholesaleSecondRangeP)){
			if(configWholesaleSecondRangeP == "1"){
				var configWholesaleSecondRangeNum = $("#bPriceConfigWholesaleSecondRangeNum").val();
				var configWholesaleSecondRangeDiscount = $("#bPriceConfigWholesaleSecondRangeDiscount").val();
				if(_this.isValidElements(configWholesaleSecondRangeNum) || _this.isValidElements(configWholesaleSecondRangeDiscount)){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(configWholesaleSecondRangeNum))){
						configWholesaleSecondRangeNum = "0";
					}
					
					if((parseInt(configWholesaleSecondRangeNum) <= parseInt(configWholesaleNum)) 
							&& (configWholesaleSecondRangeDiscount == "")){
						errorMsg = "起批量不能小于上个区间,折扣不能为空";
						isError = true;
					}else if(parseInt(configWholesaleSecondRangeNum) <= parseInt(configWholesaleNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(configWholesaleSecondRangeDiscount == ""){
						errorMsg = "折扣不能为空";
						isError = true;
					}

					if(isError){
						$("#bPriceConfigWholesaleSecondRangeErrTip").text(errorMsg);
						$("#bPriceConfigWholesaleSecondRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		var configWholesaleThirdRangeP = $("#bPriceConfigWholesaleThirdRangeP").attr("cval");
		if(_this.isValidElements(configWholesaleThirdRangeP)){
			if(configWholesaleThirdRangeP == "1"){
				var configWholesaleSecondRangeNum = $("#bPriceConfigWholesaleSecondRangeNum").val();
				var configWholesaleSecondRangeDiscount = $("#bPriceConfigWholesaleSecondRangeDiscount").val();
				var configWholesaleThirdRangeNum = $("#bPriceConfigWholesaleThirdRangeNum").val();
				var configWholesaleThirdRangeDiscount = $("#bPriceConfigWholesaleThirdRangeDiscount").val();
				if((_this.isValidElements(configWholesaleSecondRangeNum) && _this.isValidElements(configWholesaleSecondRangeDiscount)) 
						&& (_this.isValidElements(configWholesaleThirdRangeNum) || _this.isValidElements(configWholesaleThirdRangeDiscount))){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(configWholesaleThirdRangeNum)) ){
						configWholesaleThirdRangeNum = "0";
					}
					if(isNaN(parseInt(configWholesaleThirdRangeDiscount))){
						configWholesaleThirdRangeDiscount = "0";
					}
					if((parseInt(configWholesaleThirdRangeNum) <= parseInt(configWholesaleSecondRangeNum))
							&& (parseInt(configWholesaleThirdRangeDiscount) <= parseInt(configWholesaleSecondRangeDiscount))){
						errorMsg = "不能小于上个区间的起批量和折扣";
						isError = true;
					}else if(parseInt(configWholesaleThirdRangeNum) <= parseInt(configWholesaleSecondRangeNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(parseInt(configWholesaleThirdRangeDiscount) <= parseInt(configWholesaleSecondRangeDiscount)){
						errorMsg = "不能小于上个区间的折扣";
						isError = true;
					}
					if(isError){
						$("#bPriceConfigWholesaleThirdRangeErrTip").text(errorMsg);
						$("#bPriceConfigWholesaleThirdRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		return isCorrect ;
	},*/
	/*PriceConfigHideErrorDiv : function(){
		var _this = this;
		$("#bPriceConfigRetailErr").hide();
		$("#bPriceConfigRetailFirstRangeErr").hide();
		$("#bPriceConfigRetailSecondRangeErr").hide();
		$("#bPriceConfigRetailThirdRangeErr").hide();
		$("#bPriceConfigWholesaleErr").hide();
		$("#bPriceConfigWholesaleFirstRangeErr").hide();
		$("#bPriceConfigWholesaleSecondRangeErr").hide();
		$("#bPriceConfigWholesaleThirdRangeErr").hide();
	},*/
	DiscountConfig : function(){
		var _this = this;
		$(_this.bDiscountConfig).click(function(){
			if(!_this.CanConfigDiscount()){
				return;
			}
			$(_this.bDiscountConfigDiv).show().modal();
		});
		$(_this.bDiscountConfigMinWholesaleQtyNum).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var minWholesaleQtyNum = $(this).val();
			if(_this.isValidElements(minWholesaleQtyNum)){
				if(minWholesaleQtyNum.length >= 5){
					if(parseInt(minWholesaleQtyNum.substring(0,5)) == 100000 ){
						$(this).val(parseInt(minWholesaleQtyNum.substring(0,5)));
						return ;
					}else if(parseInt(minWholesaleQtyNum.substring(0,5)) > 10000 ){
						$(this).val(parseInt(minWholesaleQtyNum.substring(0,4)));
					}else{
						if(parseInt(minWholesaleQtyNum.substring(0,5)) == 0){
							$(this).val(_this.empty);
						}else{
							$(this).val(parseInt(minWholesaleQtyNum.substring(0,5)));
						}
					}
				}
			}
		});
		$(_this.bDiscountConfigRangeNum).blur(function(){
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(!isNaN(parseInt(wholesaleNum))){
					if(parseInt(wholesaleNum) == 1){
						$(this).val( _this.empty);
					}
				}
			}
		});
		$(_this.bDiscountConfigRangeNum).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var wholesaleNum = $(this).val();
			if(_this.isValidElements(wholesaleNum)){
				if(wholesaleNum.length >= 5){
					if(parseInt(wholesaleNum.substring(0,5)) == 100000 ){
						$(this).val(parseInt(wholesaleNum.substring(0,5)));
						return ;
					}else if(parseInt(wholesaleNum.substring(0,5)) > 10000 ){
						$(this).val(parseInt(wholesaleNum.substring(0,4)));
					}else{
						if(parseInt(wholesaleNum.substring(0,5)) == 0){
							$(this).val(_this.empty);
						}else{
							$(this).val(parseInt(wholesaleNum.substring(0,5)));
						}
					}
				}
			}
		});
		$(_this.bDiscountConfigRangeDiscount).keyup(function(){
			$(this).val($(this).val().replace(/^[0]|\D/g, _this.empty));
			var discountValue = $(this).val();
			if(_this.isValidElements(discountValue)){
				if(discountValue.length >= 2){
					if(parseInt(discountValue.substring(0,2)) == 0){
						$(this).val(_this.empty);
						$("#"+$(this).attr(_this.customAttr)).text(0);
					}else{
						$(this).val(discountValue.substring(0,2));
						var discountDisplay = (100-parseInt(discountValue.substring(0,2)))/10.0;
						$("#"+$(this).attr(_this.customAttr)).text(discountDisplay);
					}
					
				}else if(discountValue.length > 0){
					var discountDisplay = (100-parseInt(discountValue))/10.0;
					$("#"+$(this).attr(_this.customAttr)).text(discountDisplay);
				}
			}
		});
		$(_this.bDiscountConfigSecondRangeDel).click(function(){
			_this.DiscountConfigHideErrorDiv();
			$("#"+$(this).attr(_this.customAttr)).attr({cval:"0"});
			$("#"+$(this).attr(_this.customAttr)).hide();
		});
		$(_this.bDiscountConfigThirdRangeDel).click(function(){
			_this.DiscountConfigHideErrorDiv();
			$("#"+$(this).attr(_this.customAttr)).attr({cval:"0"});
			$("#"+$(this).attr(_this.customAttr)).hide();
			$(_this.bDiscountConfigSecondRangeDel).show();
			$(_this.addMoreDiscountConfig).show();
		});
		$(_this.addMoreDiscountConfig).click(function(){
			_this.DiscountConfigHideErrorDiv();
			var secondDisplay = $(_this.bDiscountConfigSecondRangeP).attr(_this.customAttr);
			var thirdDispaly = $(_this.bDiscountConfigThirdRangeP).attr(_this.customAttr);
			if(secondDisplay == "0"){
				$(_this.bDiscountConfigSecondRangeP).show();
				$(_this.bDiscountConfigSecondRangeP).attr({cval:"1"});
			}else{
				if(thirdDispaly == "0"){
					//var isCanAddThirdDiscount = _this.CanAddThirdDiscount();
					var isCanAddThirdDiscount = true;
					if(isCanAddThirdDiscount){
						$(_this.bDiscountConfigThirdRangeP).show();
						$(_this.bDiscountConfigThirdRangeP).attr({cval:"1"});
						$(_this.bDiscountConfigSecondRangeDel).hide();
						$(this).hide();
					}else{
						$("#bDiscountConfigSecondRangeErrTip").text("您所选商品中存在只批发商品，只批发商品允许重新再设置两个批发区间。有批有零的情况，允许重新设置三个批发区间。");
						$("#bDiscountConfigSecondRangeErr").show();
					}
					
				}
			}
		});
		$(_this.bDiscountConfigConfirmButton).click(function(){
			_this.DiscountConfigHideErrorDiv();
			var isCorrect = _this.DiscountConfigCheckConfig();	
			if(isCorrect){
				/*var isPassCheckCanAddThirdDiscount = _this.CheckCanAddThirdDiscount();
				if(!isPassCheckCanAddThirdDiscount){
					$.modal.close();
					$(_this.bDiscountConfigDiv).hide();
					return;
				}*/
				
				var minWholesaleQtyNum = $("#bDiscountConfigMinWholesaleQtyNum").val();
				var discountConfigFirstRangeNum = $("#bDiscountConfigFirstRangeNum").val();
				var discountConfigFirstRangeDiscount = $("#bDiscountConfigFirstRangeDiscount").val();
				var maxDiscount = discountConfigFirstRangeDiscount;
				var discountConfigSecondRangeNum = 0;
				var discountConfigSecondRangeDiscount = 0;
				var discountConfigThirdRangeNum = 0;
				var discountConfigThirdRangeDiscount = 0;
				var discountConfigSecondRangeP = $("#bDiscountConfigSecondRangeP").attr("cval");
				if(_this.isValidElements(discountConfigSecondRangeP)){
					if(discountConfigSecondRangeP == "1"){
						discountConfigSecondRangeNum = $("#bDiscountConfigSecondRangeNum").val();
						discountConfigSecondRangeDiscount = $("#bDiscountConfigSecondRangeDiscount").val();
						maxDiscount = discountConfigSecondRangeDiscount;
					}
				}
				var discountConfigThirdRangeP = $("#bDiscountConfigThirdRangeP").attr("cval");
				if(_this.isValidElements(discountConfigThirdRangeP)){
					if(discountConfigThirdRangeP == "1"){
						discountConfigThirdRangeNum = $("#bDiscountConfigThirdRangeNum").val();
						discountConfigThirdRangeDiscount = $("#bDiscountConfigThirdRangeDiscount").val();
						maxDiscount = discountConfigThirdRangeDiscount;
					}
				}
				var productPrices = $("td[name='" + _this.productPrice + "']");
				maxDiscount = parseFloat(maxDiscount)/100;
				maxDiscount = maxDiscount.toFixed(2);
				if (_this.isValidElements(productPrices)) {
					for ( var i = 0; i < productPrices.length; i++) {
						var prodItemcode = $(productPrices[i]).attr("prodItemcode");
						if(_this.isValidElements(prodItemcode)){
							$("#err_"+prodItemcode).hide();
						}
						_this.PriceUpdateOptionClean(prodItemcode);
						
						var minPriceValue = $("#retailPrice_"+prodItemcode).attr("lowestRetailPrice");
						var maxPriceValue = $("#retailPrice_"+prodItemcode).attr("highestRetailPrice");
						
						var revenuePriceHtml = $("#revenuePrice_"+prodItemcode);
						var minRevenuePrice = _this.CalculateMinRevenuePrice(minPriceValue, maxDiscount);
						var maxRevenuePrice = maxPriceValue;
						if(minRevenuePrice == maxRevenuePrice){
							revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice);
						}else{
							revenuePriceHtml.html(_this.meiyuanLogo + minRevenuePrice + "~" + maxRevenuePrice);
						}
						
						var buyerPriceHtml = $("#buyerPrice_"+prodItemcode);
						
						var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
						var maxSaleQty = $("#maxSaleQty_"+prodItemcode).val();
						var minBuyerPrice = _this.CalculateBuyerPrice(minRevenuePrice, prodItemcode,maxSaleQty);
						var maxBuyerPrice = _this.CalculateBuyerPrice(maxRevenuePrice, prodItemcode,minWholesaleQty);
						if(minBuyerPrice == maxBuyerPrice){
							buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice);
						}else{
							buyerPriceHtml.html(_this.meiyuanLogo + minBuyerPrice + "~" + maxBuyerPrice);
						}

						$("#revenueMark_"+prodItemcode).show();
						$("#buyerMark_"+prodItemcode).show();
						
						$("#minWholesaleQtyCfg_"+prodItemcode).val(minWholesaleQtyNum);
						
						$("#updatePrice_"+prodItemcode).attr({isUpdate:true});
						$("#updatePrice_"+prodItemcode).attr({updateType:_this.priceUpdateType_Discount_Cfg});
						if(discountConfigFirstRangeNum != 0 && discountConfigFirstRangeDiscount != 0){
							var discount = parseInt(discountConfigFirstRangeDiscount)/100;
							$("#firstDis_"+prodItemcode).val(discount);
							$("#firstDis_"+prodItemcode).attr({startQty:discountConfigFirstRangeNum});
						}
						if(discountConfigSecondRangeNum != 0 && discountConfigSecondRangeDiscount != 0){
							var discount = parseInt(discountConfigSecondRangeDiscount)/100;
							$("#secondDis_"+prodItemcode).val(discount);
							$("#secondDis_"+prodItemcode).attr({startQty:discountConfigSecondRangeNum});
						}
						if(discountConfigThirdRangeNum != 0 && discountConfigThirdRangeDiscount != 0){
							var discount = parseInt(discountConfigThirdRangeDiscount)/100;
							$("#thirdDis_"+prodItemcode).val(discount);
							$("#thirdDis_"+prodItemcode).attr({startQty:discountConfigThirdRangeNum});
						}
						$("#pe_"+prodItemcode).attr({lastUpdateType:_this.lastUpdateType_Discount_Config});
						$("#"+prodItemcode).attr({isCommitUpdate:"false"});
					}
				}
				$.modal.close();
				$(_this.bDiscountConfigDiv).hide();
			}
		});
	},
	/*CheckCanAddThirdDiscount : function(){
		var _this = this;
		var isAddThirdDiscount = true;
		var discountConfigFirstRangeNum = $("#bDiscountConfigFirstRangeNum").val();
		var productPrices = $("td[name='" + _this.productPrice + "']");
		if (_this.isValidElements(productPrices)) {
			for ( var i = 0; i < productPrices.length; i++) {
				var prodItemcode = $(productPrices[i]).attr("prodItemcode");
				var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
				if (_this.isValidElements(minWholesaleQty)) {
					if(parseInt(discountConfigFirstRangeNum) <= parseInt(minWholesaleQty)){
						isAddThirdDiscount = false;
						$("#err_info_"+prodItemcode).text("您设置的批发区间数量不允许小于起批量。");
						$("#err_"+prodItemcode).show();
						$("#pe_"+prodItemcode).attr({
							lastUpdateType : _this.lastUpdateType_Default
						});
					}
				}
				
			}
		}
		return isAddThirdDiscount;
	},*/
	DiscountConfigCheckConfig : function(){
		var _this = this;
		var isCorrect = true;
		var minWholesaleQtyNum = $(_this.bDiscountConfigMinWholesaleQtyNum).val();
		if(!_this.isValidElements(minWholesaleQtyNum) || minWholesaleQtyNum == ""){
			var errorMsg = "最小销售数量不能为空";
			$("#bDiscountConfigMinWholesaleQtyErrTip").text(errorMsg);
			$("#bDiscountConfigMinWholesaleQtyErr").show();
			isCorrect = false;
		}
		var discountConfigFirstRangeNum = $("#bDiscountConfigFirstRangeNum").val();
		var discountConfigFirstRangeDiscount = $("#bDiscountConfigFirstRangeDiscount").val();
		if(!_this.isValidElements(discountConfigFirstRangeNum) || discountConfigFirstRangeNum == "" || !_this.isValidElements(discountConfigFirstRangeDiscount) || discountConfigFirstRangeDiscount == ""){
			var errorMsg = "";
			if((!_this.isValidElements(discountConfigFirstRangeNum) || discountConfigFirstRangeNum == "") && (!_this.isValidElements(discountConfigFirstRangeDiscount) || discountConfigFirstRangeDiscount == "")){
				errorMsg = "起批量和折扣不能为空";
			}else if(!_this.isValidElements(discountConfigFirstRangeNum) || discountConfigFirstRangeNum == ""){
				errorMsg = "起批量不能为空";
			}else if(!_this.isValidElements(discountConfigFirstRangeDiscount) || discountConfigFirstRangeDiscount == ""){
				errorMsg = "折扣不能为空";
			}
			$("#bDiscountConfigFirstRangeErrTip").text(errorMsg);
			$("#bDiscountConfigFirstRangeErr").show();
			isCorrect = false;
		}
		if(parseInt(discountConfigFirstRangeNum) <= parseInt(minWholesaleQtyNum)){
			var errorMsg = "不能小于最小销售数量";
			$("#bDiscountConfigFirstRangeErrTip").text(errorMsg);
			$("#bDiscountConfigFirstRangeErr").show();
			isCorrect = false;
		} 
		
		var discountConfigSecondRangeP = $("#bDiscountConfigSecondRangeP").attr("cval");
		if(_this.isValidElements(discountConfigSecondRangeP)){
			if(discountConfigSecondRangeP == "1"){
				var discountConfigSecondRangeNum = $("#bDiscountConfigSecondRangeNum").val();
				var discountConfigSecondRangeDiscount = $("#bDiscountConfigSecondRangeDiscount").val();
				if(_this.isValidElements(discountConfigSecondRangeNum) || _this.isValidElements(discountConfigSecondRangeDiscount)){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(discountConfigSecondRangeNum))){
						discountConfigSecondRangeNum = "0";
					}
					
					if(isNaN(parseInt(discountConfigSecondRangeDiscount))){
						discountConfigSecondRangeDiscount = "0";
					}
					
					if((parseInt(discountConfigSecondRangeNum) <= parseInt(discountConfigFirstRangeNum)) 
							&& (parseInt(discountConfigSecondRangeDiscount) < parseInt(discountConfigFirstRangeDiscount))){
						errorMsg = "不能小于上个区间的起批量和折扣";
						isError = true;
					}else if(parseInt(discountConfigSecondRangeNum) <= parseInt(discountConfigFirstRangeNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(parseInt(discountConfigSecondRangeDiscount) <= parseInt(discountConfigFirstRangeDiscount)){
						errorMsg = "不能小于上个区间的折扣";
						isError = true;
					}

					if(isError){
						$("#bDiscountConfigSecondRangeErrTip").text(errorMsg);
						$("#bDiscountConfigSecondRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		var discountConfigThirdRangeP = $("#bDiscountConfigThirdRangeP").attr("cval");
		if(_this.isValidElements(discountConfigThirdRangeP)){
			if(discountConfigThirdRangeP == "1"){
				var discountConfigSecondRangeNum = $("#bDiscountConfigSecondRangeNum").val();
				var discountConfigSecondRangeDiscount = $("#bDiscountConfigSecondRangeDiscount").val();
				var discountConfigThirdRangeNum = $("#bDiscountConfigThirdRangeNum").val();
				var discountConfigThirdRangeDiscount = $("#bDiscountConfigThirdRangeDiscount").val();
				if((_this.isValidElements(discountConfigSecondRangeNum) && _this.isValidElements(discountConfigSecondRangeDiscount)) 
						&& (_this.isValidElements(discountConfigThirdRangeNum) || _this.isValidElements(discountConfigThirdRangeDiscount))){
					var isError = false;
					var errorMsg = "";
					if(isNaN(parseInt(discountConfigThirdRangeNum)) ){
						discountConfigThirdRangeNum = "0";
					}
					if(isNaN(parseInt(discountConfigThirdRangeDiscount))){
						discountConfigThirdRangeDiscount = "0";
					}
					if((parseInt(discountConfigThirdRangeNum) <= parseInt(discountConfigSecondRangeNum))
							&& (parseInt(discountConfigThirdRangeDiscount) <= parseInt(discountConfigSecondRangeDiscount))){
						errorMsg = "不能小于上个区间的起批量和折扣";
						isError = true;
					}else if(parseInt(discountConfigThirdRangeNum) <= parseInt(discountConfigSecondRangeNum)){
						errorMsg = "不能小于上个区间的起批量";
						isError = true;
					}else if(parseInt(discountConfigThirdRangeDiscount) <= parseInt(discountConfigSecondRangeDiscount)){
						errorMsg = "不能小于上个区间的折扣";
						isError = true;
					}
					if(isError){
						$("#bDiscountConfigThirdRangeErrTip").text(errorMsg);
						$("#bDiscountConfigThirdRangeErr").show();
						isCorrect = false;
					}
				}
			}
		}
		return isCorrect ;
	},
	CanConfigDiscount : function(){
		var _this = this;
		var canUpdatePrice = true;
		var productPrices = $("td[name='" + _this.productPrice + "']");
		if (_this.isValidElements(productPrices)) {
			for ( var i = 0; i < productPrices.length; i++) {
				var prodItemcode = $(productPrices[i]).attr("prodItemcode");
				if(_this.isValidElements(prodItemcode)){
					$("#err_"+prodItemcode).hide();
				}
				var hasSkuMark = $("#hasSku_"+prodItemcode).val();
				if(_this.isValidElements(hasSkuMark)){
					if(hasSkuMark == 0){
						$("#err_info_"+prodItemcode).text("系统预计在24小时之内，完成该商品新价格的设置，请耐心等待！您也可以使用'产品管理'中的修改价格，设置该商品的新价格和折扣。");
						$("#err_"+prodItemcode).show();
						canUpdatePrice = false;
					}
				}else{
					$("#err_info_"+prodItemcode).text("系统预计在24小时之内，完成该商品新价格的设置，请耐心等待！您也可以使用'产品管理'中的修改价格，设置该商品的新价格和折扣。");
					$("#err_"+prodItemcode).show();
					canUpdatePrice = false;
				}
				var isPromotion = $("#pe_"+prodItemcode).attr("isPromotion");
				if(_this.isValidElements(isPromotion)){
					if(isPromotion == 1){
						$("#err_info_"+prodItemcode).text("您的产品参加了促销，不允许修改产品价格和折扣。");
						$("#err_"+prodItemcode).show();
						canUpdatePrice = false;
					}
				}
			}
		}
		return canUpdatePrice;
	},
	DiscountConfigHideErrorDiv : function(){
		var _this = this;
		$("#bDiscountConfigMinWholesaleQtyErr").hide();
		$("#bDiscountConfigFirstRangeErr").hide();
		$("#bDiscountConfigSecondRangeErr").hide();
		$("#bDiscountConfigThirdRangeErr").hide();
	},
	CanAddThirdDiscount : function(){
		var _this = this;
		var isAddThirdDiscount = true;
		var productPrices = $("td[name='" + _this.productPrice + "']");
		if (_this.isValidElements(productPrices)) {
			for ( var i = 0; i < productPrices.length; i++) {
				var prodItemcode = $(productPrices[i]).attr("prodItemcode");
				var minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
				if (_this.isValidElements(minWholesaleQty)) {
					if(parseInt(minWholesaleQty) >= 2){
						isAddThirdDiscount = false;
						break;
					}
				}
				
			}
		}
		return isAddThirdDiscount;
	},
	CalculateReducePercent : function(price,reducePercent){
		var disPrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(reducePercent))){
			disPrice = "-1";
		}else{
			disPrice = Math.round((parseFloat(price)-parseFloat(price)*(parseFloat(reducePercent)/100))*100)/100; 
		}
		return disPrice.toFixed(2);
	},
	CalculateAddPercent : function(price,addPercent){
		var disPrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(addPercent))){
			disPrice = "-1";
		}else{
			disPrice = Math.round((parseFloat(price)+parseFloat(price)*(parseFloat(addPercent)/100))*100)/100; 
		}
		return disPrice.toFixed(2);
	},
	CalculateReducePrice : function(price,reducePrice){
		var disPrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(reducePrice))){
			disPrice = "false";
		}else{
			disPrice = Math.round((parseFloat(price)-parseFloat(reducePrice))*100)/100; 
		}
		return disPrice.toFixed(2);
	},
	CalculateAddPrice : function(price,addPrice){
		var disPrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(addPrice))){
			disPrice = "-1";
		}else{
			disPrice = Math.round((parseFloat(price)+parseFloat(addPrice))*100)/100; 
		}
		return disPrice.toFixed(2);
	},
	CalculateDiscountPrice : function(price,discount){
		var disPrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(discount))){
			disPrice = "-1";
		}else{
			disPrice = Math.round(parseFloat(price)*(1-parseFloat(discount))*100)/100; 
		}
		return disPrice.toFixed(2);
	},
	CalculateMinRevenuePrice : function(price,discount){
		var minRevenuePrice; 
		if(isNaN(parseFloat(price)) || isNaN(parseFloat(discount))){
			minRevenuePrice = "-1";
		}else{
			minRevenuePrice = Math.round(parseFloat(price)*(1-parseFloat(discount))*100)/100; 
		}
		return minRevenuePrice.toFixed(2);
	},
	CalculateBuyerPrice : function(price,itemcode,saleQty){
		/**
		 * buyer最低价格：
		 * 		seller最低价格 * 最大折扣 * 最大销售数量 ==>> 佣金率A
		 * 		buyer最低价格 = seller最低价格 / ( 1 - 佣金率A )
		 * buyer最大价格：
		 *		seller最大价格 * 最小销售数量 ==>> 佣金率B
		 * 		buyer最大价格 = seller最大价格 / ( 1 - 佣金率B )
		 * 此处的seller最低价格应该为已经乘完折扣的价格。
		 */
		var buyerPrice; 
		if(isNaN(parseFloat(price))){
			buyerPrice = "-1";
		}else{
			var calculateRatePrice = parseFloat(price) * parseInt(saleQty);
			var commissionRate = this.GetCommissionRate(calculateRatePrice, itemcode);
			buyerPrice = parseFloat(price) / ( 1- parseFloat(commissionRate)); 
		}
		return buyerPrice.toFixed(2);
	},
	GetCommissionRate : function(price,itemcode){
		var _this = this;
		var commissionRate = 1.0;
		var commissionRateStr = $("#commissionRateList_"+itemcode).val();
		if(_this.isValidElements(commissionRateStr)){
			var commissionRateArray = commissionRateStr.split(";");
			for(var i=0;i<commissionRateArray.length;i++){
				var commissionRateRangeStr = commissionRateArray[i];
				if(_this.isValidElements(commissionRateRangeStr)){
					var commissionRateRangeArray = commissionRateRangeStr.split(",");
					if(commissionRateRangeArray.length < 3){
						continue;
					}
					var leftPriceBoundStr = commissionRateRangeArray[0];
					var rightPriceBoundStr = commissionRateRangeArray[1];
					var rate = commissionRateRangeArray[2];
					if(_this.isValidElements(leftPriceBoundStr) && _this.isValidElements(rightPriceBoundStr) && _this.isValidElements(rate)){
						var leftPriceBoundArray = leftPriceBoundStr.split("_");
						var rightPriceBoundArray = rightPriceBoundStr.split("_");
						if(leftPriceBoundArray.length < 2 || rightPriceBoundArray.length < 2){
							continue;
						}
						var leftPrice = leftPriceBoundArray[0];
						var leftBound = leftPriceBoundArray[1];
						var rightPrice = rightPriceBoundArray[0];
						var rightBound = rightPriceBoundArray[1];
						if(leftBound == "1"){
							if(rightBound == "1"){
								if(price >= leftPrice && price <= rightPrice){
									commissionRate = rate;
									break;
								}
							}else{
								if(price >= leftPrice && price < rightPrice){
									commissionRate = rate;
									break;
								}
							}
						}else{
							if(rightBound == "1"){
								if(price > leftPrice && price <= rightPrice){
									commissionRate = rate;
									break;
								}
							}else{
								if(price > leftPrice && price < rightPrice){
									commissionRate = rate;
									break;
								}
							}
						}
					}
				}
			}
		}
		return parseFloat(commissionRate).toFixed(6);
		
	},
	isValidElements : function(obj) {
		return (obj != undefined && obj != 'undefined' && obj != null);
	},
	AssembleProdBatchUpdateBizDTO : function() {
		var _this = this;
		var cpVoAry = [];
		var products = $("tr[name='productInfo']");
		if (_this.isValidElements(products)) {
			if (products.length > 0) {
				for ( var i = 0; i < products.length; i++) {
					var isCommitUpdate = $(products[i]).attr("isCommitUpdate");
					if(isCommitUpdate == "false"){
						var prodItemcode = $(products[i]).attr(_this.customAttr);
						cpVoAry.push(new _this.ProdBatchUpdateBizDTO());
						cpVoAry[cpVoAry.length - 1].productId = $(products[i]).attr("productId");
						cpVoAry[cpVoAry.length - 1].itemcode = prodItemcode;
						cpVoAry[cpVoAry.length - 1].catePubId = $("#catePubId_"+prodItemcode).val();
						var shippingmodeLastUpdateType = $("#st_"+prodItemcode).attr("lastUpdateType");
						if(shippingmodeLastUpdateType != _this.lastUpdateType_Default){
							cpVoAry[cpVoAry.length - 1].shippingmodelid = $("#st_"+prodItemcode).attr("cval");
						}
						var leadingtimeLastUpdateType = $("#lt_"+prodItemcode).attr("lastUpdateType");
						if(leadingtimeLastUpdateType != _this.lastUpdateType_Default){
							var leadingtime = $("#lt_"+prodItemcode).attr("cval");
							if(leadingtime == null || leadingtime == ""){
								cpVoAry[cpVoAry.length - 1].leadingtime =0;
							}else{
								cpVoAry[cpVoAry.length - 1].leadingtime = $("#lt_"+prodItemcode).attr("cval");
							}
						}
						var lastUpdateType = $("#pe_"+prodItemcode).attr("lastUpdateType");
						if(lastUpdateType != _this.lastUpdateType_Default){
							if(lastUpdateType == _this.lastUpdateType_UpdatePrice_Config_Wholesale){
								cpVoAry[cpVoAry.length - 1].priceUpdate = $("#minWholesalePrice_"+prodItemcode).val();
								cpVoAry[cpVoAry.length - 1].priceUpdateType = $("#minWholesalePrice_"+prodItemcode).attr("updateType");
							}else{
								cpVoAry[cpVoAry.length - 1].priceUpdate = $("#updatePrice_"+prodItemcode).val();
								cpVoAry[cpVoAry.length - 1].priceUpdateType = $("#updatePrice_"+prodItemcode).attr("updateType");
								cpVoAry[cpVoAry.length - 1].priceUpdatePercent = $("#updatePrice_"+prodItemcode).val();
							}
							/*if(lastUpdateType == _this.lastUpdateType_Discount_Config){
								cpVoAry[cpVoAry.length - 1].minWholesaleQty = $("#minWholesaleQty_"+prodItemcode).val();
							}else{
								cpVoAry[cpVoAry.length - 1].minWholesaleQty = $("#minWholesaleQtyCfg_"+prodItemcode).val();
							}*/
							cpVoAry[cpVoAry.length - 1].minWholesaleQty = $("#minWholesaleQtyCfg_"+prodItemcode).val();
							
							var firstDis = $("#firstDis_"+prodItemcode);
							cpVoAry[cpVoAry.length - 1].firstDisStartQty = firstDis.attr("startQty");
							cpVoAry[cpVoAry.length - 1].firstDiscount = firstDis.val();
							var secondDis = $("#secondDis_"+prodItemcode);
							cpVoAry[cpVoAry.length - 1].secondDisStartQty = secondDis.attr("startQty");
							cpVoAry[cpVoAry.length - 1].secondDiscount = secondDis.val();
							var thirdDis = $("#thirdDis_"+prodItemcode);
							cpVoAry[cpVoAry.length - 1].thirdDisStartQty = thirdDis.attr("startQty");
							cpVoAry[cpVoAry.length - 1].thirdDiscount = thirdDis.val();
							$(products[i]).attr({isCommitUpdate:"true"});
						}
						$("#st_"+prodItemcode).attr({lastUpdateType:"0"});
						$("#pe_"+prodItemcode).attr({lastUpdateType:"0"});
						$("#lt_"+prodItemcode).attr({lastUpdateType:"0"});
						$(products[i]).attr({isCommitUpdate:"true"});
					}
					
				}
			}
		}
		return cpVoAry;
	},
	ProdBatchUpdateBizDTO : function(){
		this["class"] = "com.dhgate.prodbiz.dto.ProdBatchUpdateBizDTO";
		this.productId;
		this.itemcode;
		this.catePubId;
		this.shippingmodelid;
		this.leadingtime;
		this.priceUpdate;
		this.priceUpdatePercent;
		this.priceUpdateType;
		this.isOldProduct;
		this.minWholesaleQty;
		this.firstDisStartQty;
		this.firstDisEndQty;
		this.firstDiscount;
		this.secondDisStartQty;
		this.secondDisEndQty;
		this.secondDiscount;
		this.thirdDisStartQty;
		this.thirdDisEndQty;
		this.thirdDiscount;
	},
	InitProdBatchUpate : function() {
		this.PriceBulkOperationsMouse();
		this.DeleteSourceProduct();
		this.ShippingTemplate();
		this.ShowBatchDiv();
		this.Leadingtime();
		this.PriceUpdate();
		//this.PriceConfig();
		this.DiscountConfig();
		this.AddMore();
		this.SaveUpdateProducts();
		
			
	}
};
$(document).ready(function() {
	$.ajaxSetup({
		cache : false
	});
	var prodBatchUpate = new ProdBatchUpate({});
	window.callbacks = function(rsArray){
		prodBatchUpate.AppendSelectedProduct(rsArray);
	};
	
});