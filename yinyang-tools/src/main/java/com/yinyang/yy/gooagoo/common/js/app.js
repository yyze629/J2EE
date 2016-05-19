/**
 * 请求app的js方法的入口
 * @param type
 * @param data
 * @returns
 */
function screenRequest(type,data){
	
	return window.epad.clickOnAndroid(type,data);//正式环境使用
	
	//一下代码为测试使用
//	try{
//		ascreenReceive(type,data);
//	}catch(e){
//	}
//	try{
//		cscreenReceive(type,data)
//	}catch(e){
//	}
//	return "1";
}

/**
 * app调用内屏web的js的入口
 * @param type
 * @param data
 */
function ascreenReceive(type,data){
   var obj = {
      T04 : function(){scanGoodsNA(data);},
      T07 : function(){scanCouponResultNA(data);},
      T09 : function(){payResultNA(data);},
      T12 : function(){printBillNA(data);},
      T15 : function(){initDeviceNA(data);},
      A01 : function(){customerInfoNA(data);},
      A03 : function(){custAddGoodsNA(data);},
      A06 : function(){useCouponNA(data);},
      A07 : function(){custCancelCouponNA(data);},
      A11 : function(){custSelectCouponNA(data);},
      A14 : function(){payTypeNoteNA(data);},
      A16 : function(){scanBillNoteNA(data);}
   }
   eval('obj.'+type+'();');
}

/**
 * app调用外屏web的js的入口
 * @param type
 * @param data
 */
function cscreenReceive(type,data){
   var obj = {
      T00 : function(){initDeviceNC(data);},
      T01 : function(){loginNC(data);},
      T03 : function(){loginResultNC(data);},
      T14 : function(){orderNR(data);},
      T10 : function(){payResultNC(data);},
      T13 : function(){printBillNC(data);},
      A02 : function(){goodsDetailNC(data);},
      A04 : function(){custAddGoodsResultNC(data);},
      A05 : function(){assiSuggGoodsNC(data);},
      A08 : function(){assiCancelCouponNC(data);},
      A09 : function(){useCouponResultNC(data);},
      A10 : function(){payTypeNC(data);},
      A12 : function(){assiSelectCouponNC(data);},
      A13 : function(){validCouponNC(data);},
      A15 : function(){payTypeNoteNC(data);}
   }
   eval('obj.'+type+'();');
}
function switchScreenPanel(panelIndex){
	var panels = $(".ScreenPanel");
	panels.css("display","none");
	panels.eq(panelIndex).css("display","block");
}
function freashTotalPrice(){
	if(isEmpty(orderInfo.goodsList)||orderInfo.goodsList.length<1){
		return;
	}
	var tp = 0;
	var tdp = 0;
	for(var i=0; i < orderInfo.goodsList.length;i++){
		tp += orderInfo.goodsList[i].price;
	}
	if(!isEmpty(orderInfo.couponList)&&orderInfo.couponList.length>0){
		for(var j=0; j < orderInfo.couponList.length;j++){
			tdp += orderInfo.couponList[j].amnt;
		}
	}
	orderInfo.tprice = tp;
	orderInfo.tdisprice = tdp;
	orderInfo.tprice2 = tp-tdp;
	var tpriceSpans = $("#tpriceDiv").find("span");
	tpriceSpans.eq(0).html("￥"+orderInfo.tprice);
	tpriceSpans.eq(1).html("￥"+orderInfo.tdisprice);
	tpriceSpans.eq(2).html("￥"+orderInfo.tprice2);
}
