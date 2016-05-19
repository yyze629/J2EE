package com.yinyang.sellerpay.web.validate;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.web.form.SelectBankForm;
import com.yinyang.sellerpay.web.form.payease.PaymentForm;
import com.yinyang.sellerpay.web.util.ValidateUtil;

@Service("payEaseValidate")
public class PayEaseValidate implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1773814395994063998L;
	private static final Log logger = LogFactory.getLogger(PayEaseValidate.class);
	
	private static final String ERROR_KEY = "PARAM_ERRORKEY";
	
	public static ValidateResult selectBankParamsValidate(SelectBankForm selectBankForm){
		ValidateResult result = new ValidateResult();
		Set<Boolean> set = new HashSet<Boolean>();
		if(selectBankForm == null){
			result.addMsg(" selectBankForm is null ");
			result.setIsSuccess(false);
		}
		if(StringUtils.isBlank(selectBankForm.getSupplierId())){
			set.add(false);
			result.addMsg(" SupplierId is null ");
		}
		if(StringUtils.isBlank(selectBankForm.getBusinessNumber())){
			set.add(false);
			result.addMsg(" BusinessNumber is null ");
		}
		if(StringUtils.isBlank(selectBankForm.getOrderNumber())){
			set.add(false);
			result.addMsg(" OrderNumber is null ");
		}
		if(selectBankForm.getPayAmount() == null){
			set.add(false);
			result.addMsg(" PayAmount is null ");
		}
		if(StringUtils.isBlank(selectBankForm.getPayDesc())){
			set.add(false);
			result.addMsg(" PayDesc is null ");
		}
		if(StringUtils.isBlank(selectBankForm.getPayMd5Info())){
			set.add(false);
			result.addMsg(" PayMd5Info is null ");
		}
		if(!set.contains(false)){
			if(!selectBankForm.getCurrentSupplierId().equals(selectBankForm.getSupplierId())){
				set.add(false);
				result.addMsg(" SupplierId is not correct {CurrentSupplierId:"+selectBankForm.getCurrentSupplierId()+",SupplierId:"+selectBankForm.getSupplierId()+"} ");
			}
		}
		if(set.contains(false)){
			logger.info("selectBankParamsValidate:"+result.getMsg());
			result.setIsSuccess(false);
		}
		return result;
	}
	/**
	 * 创建支付订单信息.
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayOrderValidate(ResultDto resultDto,PayOrderDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayOrder Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayOrderDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "supplierId", dto.getSupplierId()));
		set.add(ValidateUtil.required(errorMsg, "businessNumber", dto.getBusinessNumber()));
		set.add(ValidateUtil.required(errorMsg, "orderNumber", dto.getOrderNumber()));
		set.add(ValidateUtil.required(errorMsg, "paymentType", dto.getPaymentType()));
		set.add(ValidateUtil.required(errorMsg, "payAmount", dto.getPayAmount()));
		set.add(ValidateUtil.required(errorMsg, "payDesc", dto.getPayDesc()));
		set.add(ValidateUtil.required(errorMsg, "status", dto.getStatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			logger.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	public static ValidateResult paymentParamsValidate(PaymentForm paymentForm){
		ValidateResult result = new ValidateResult();
		Set<Boolean> set = new HashSet<Boolean>();
		if(paymentForm == null){
			result.addMsg(" paymentForm is null ");
			result.setIsSuccess(false);
		}
		if(paymentForm.getOrderId() == null){
			set.add(false);
			result.addMsg(" OrderId is null ");
		}
		if(StringUtils.isBlank(paymentForm.getChannelId())){
			set.add(false);
			result.addMsg(" ChannelId is null ");
		}
		if(StringUtils.isBlank(paymentForm.getPmode())){
			set.add(false);
			result.addMsg(" Pmode is null ");
		}
		if(set.contains(false)){
			logger.info("paymentParamsValidate:"+result.getMsg());
			result.setIsSuccess(false);
		}
		return result;
	}
	public static ValidateResult payCompleteParamsValidate(PaymentForm paymentForm){
		ValidateResult result = new ValidateResult();
		Set<Boolean> set = new HashSet<Boolean>();
		if(paymentForm == null){
			result.addMsg(" paymentForm is null ");
			result.setIsSuccess(false);
		}
		if(paymentForm.getOrderId() == null){
			set.add(false);
			result.addMsg(" OrderId is null ");
		}
		if(StringUtils.isBlank(paymentForm.getChannelId())){
			set.add(false);
			result.addMsg(" ChannelId is null ");
		}
		if(set.contains(false)){
			logger.info("payCompleteParamsValidate:"+result.getMsg());
			result.setIsSuccess(false);
		}
		return result;
	}
	/**
	 * 根据订单ID，更新订单状态信息。
	 * @param resultDto
	 * @param orderId
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void updatePayOrderStatusByIdValidate(ResultDto resultDto,Long orderId,PayOrderDTO dto){
		StringBuilder errorMsg = new StringBuilder(" updatePayOrderStatusById Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayOrderDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "orderId", orderId));
		set.add(ValidateUtil.required(errorMsg, "status", dto.getStatus()));
		
		if(set.contains(false)){
			logger.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	
	/**
	 * 创建支付订单流水信息.
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayFlowValidate(ResultDto resultDto,PayFlowDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayFlow Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayFlowDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "channelId", dto.getChannelId()));
		set.add(ValidateUtil.required(errorMsg, "orderId", dto.getOrderId()));
		set.add(ValidateUtil.required(errorMsg, "businessId", dto.getBusinessId()));
		set.add(ValidateUtil.required(errorMsg, "VMid", dto.getVMid()));
		set.add(ValidateUtil.required(errorMsg, "VOid", dto.getVOid()));
		set.add(ValidateUtil.required(errorMsg, "VPmode", dto.getVPmode()));
		set.add(ValidateUtil.required(errorMsg, "VYmd", dto.getVYmd()));
		set.add(ValidateUtil.required(errorMsg, "VAmount", dto.getVAmount()));
		set.add(ValidateUtil.required(errorMsg, "VMoneytype", dto.getVMoneytype()));
		set.add(ValidateUtil.required(errorMsg, "VUrl", dto.getVUrl()));
		set.add(ValidateUtil.required(errorMsg, "VPstatus", dto.getVPstatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			logger.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	/**
	 * 创建支付订单流水信息.
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void savePayFlowValidate(ResultDto resultDto,String supplierid,PayFlowDTO dto){
		StringBuilder errorMsg = new StringBuilder(" savePayFlow Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayFlowDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		set.add(ValidateUtil.required(errorMsg, "supplierid", supplierid));
		set.add(ValidateUtil.required(errorMsg, "channelId", dto.getChannelId()));
		set.add(ValidateUtil.required(errorMsg, "orderId", dto.getOrderId()));
		set.add(ValidateUtil.required(errorMsg, "businessId", dto.getBusinessId()));
		set.add(ValidateUtil.required(errorMsg, "VMid", dto.getVMid()));
		set.add(ValidateUtil.required(errorMsg, "VOid", dto.getVOid()));
		set.add(ValidateUtil.required(errorMsg, "VPmode", dto.getVPmode()));
		set.add(ValidateUtil.required(errorMsg, "VYmd", dto.getVYmd()));
		set.add(ValidateUtil.required(errorMsg, "VAmount", dto.getVAmount()));
		set.add(ValidateUtil.required(errorMsg, "VMoneytype", dto.getVMoneytype()));
		set.add(ValidateUtil.required(errorMsg, "VUrl", dto.getVUrl()));
		set.add(ValidateUtil.required(errorMsg, "VPstatus", dto.getVPstatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			logger.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	public static ValidateResult payEaseCallbackParamsValidate(String v_oid,String v_pstatus,String v_amount,String v_moneytype,String v_sign){
		ValidateResult result = new ValidateResult();
		Set<Boolean> set = new HashSet<Boolean>();
		if(StringUtils.isBlank(v_oid)){
			set.add(false);
			result.addMsg(" v_oid is null ");
		}
		if(StringUtils.isBlank(v_pstatus)){
			set.add(false);
			result.addMsg(" v_pstatus is null ");
		}
		if(StringUtils.isBlank(v_amount)){
			set.add(false);
			result.addMsg(" v_amount is null ");
		}
		if(StringUtils.isBlank(v_moneytype)){
			set.add(false);
			result.addMsg(" v_moneytype is null ");
		}
		if(StringUtils.isBlank(v_sign)){
			set.add(false);
			result.addMsg(" v_sign is null ");
		}
		if(set.contains(false)){
			logger.info("payEaseCallbackParamsValidate:"+result.getMsg());
			result.setIsSuccess(false);
		}
		return result;
	}
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * @param resultDto
	 * @param orderId
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void updatePayStatusForPayEaseCallbackValidate(ResultDto resultDto,PayFlowDTO dto){
		StringBuilder errorMsg = new StringBuilder(" updatePayStatusForPayEaseCallback Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayFlowDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "VOid", dto.getVOid()));
		set.add(ValidateUtil.required(errorMsg, "VAmountActual", dto.getVAmountActual()));
		set.add(ValidateUtil.required(errorMsg, "VMoneytypeActual", dto.getVMoneytypeActual()));
		set.add(ValidateUtil.required(errorMsg, "VPstatus", dto.getVPstatus()));
		
		if(set.contains(false)){
			logger.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
}
