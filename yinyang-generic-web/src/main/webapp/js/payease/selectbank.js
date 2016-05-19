var SelectBank = function(options) {
	this.SetOptions(options);
	this.empty = "";
	this.jPaySubmit = ".j-pay-submit";
	this.jPayConfirm = ".j-pay-confirm";
	this.jPayConfirmClose = ".j-pay-confirm-close";
	this.orderId = "#orderId";
	this.channelId = "#channelId";
	this.jPayBank = ".j-pay-bank";
	this.jPayBankRadio = ".j-pay-bank-radio";
	this.bandRadio = "input[name='bank']";
	this.bandRadioChecked = "input[name='bank']:checked";
	this.jPayComplete = ".j-pay-complete";
	this.jPayFailed = ".j-pay-failed";
	this.paySubmitUrl = "http://seller.dhgate.com/sellerpay/payease/payment.do";
	this.payCompleteUrl = "http://seller.dhgate.com/sellerpay/payease/payComplete.do";
	
	this.InitSelectBank();
	
	
};
SelectBank.prototype = {
	SetOptions : function(options) {
		this.options = {};
		$.extend(this.options, options || {});
	},
	SelectBankOptionManage :function(){
		var _this = this;
		$(_this.jPaySubmit).click(function(){
			$(_this.jPayConfirm).show().modal();
			var orderId = $(_this.orderId).val();
			var channelId = $(_this.channelId).val();
			var bank = $(_this.bandRadioChecked).val();
			var url = _this.paySubmitUrl + "?paymentForm.orderId="+orderId+"&paymentForm.channelId="+channelId+"&paymentForm.pmode="+bank;
			window.open(url);
		});
		$(_this.jPayComplete).click(function(){
			var orderId = $(_this.orderId).val();
			var channelId = $(_this.channelId).val();
			$.modal.close();
			$(_this.jPayConfirm).hide();
			var url = _this.payCompleteUrl + "?paymentForm.orderId="+orderId+"&paymentForm.channelId="+channelId;
			window.location.href = url;
			return true;
		});
		$(_this.jPayFailed).click(function(){
			var orderId = $(_this.orderId).val();
			var channelId = $(_this.channelId).val();
			$.modal.close();
			$(_this.jPayConfirm).hide();
			var url = _this.payCompleteUrl + "?paymentForm.orderId="+orderId+"&paymentForm.channelId="+channelId;
			window.location.href = url;
			return true;
		});
		$(_this.jPayConfirmClose).click(function(){
			$.modal.close();
			$(_this.jPayConfirm).hide();
		});
		$(_this.jPayBank).click(function(){
			var bankRadioId = $(this).attr("for");
			$(_this.jPayBankRadio).removeClass("WellRadioH");
			$("#"+bankRadioId).closest(_this.jPayBankRadio).addClass("WellRadioH");
			$(_this.jPayBank).removeClass("ls-border");
			$(this).addClass("ls-border");
			$(_this.bandRadio).attr("checked",false);
			$("#"+bankRadioId).attr("checked","checked"); 
		});
		$(_this.jPayBankRadio).click(function(){
			$(_this.jPayBankRadio).removeClass("WellRadioH");
			$(this).addClass("WellRadioH");
			$(_this.jPayBank).removeClass("ls-border");
			$(this).next().addClass("ls-border");
			$(_this.bandRadio).attr("checked",false);
			$(this).find(_this.bandRadio).attr("checked","checked"); 
		});
	},
	InitSelectBank : function() {
		this.SelectBankOptionManage();
	}
};

$(document).ready(function(){
	$.ajaxSetup({
		cache : false
	});
	var selectBank = new SelectBank({});
});