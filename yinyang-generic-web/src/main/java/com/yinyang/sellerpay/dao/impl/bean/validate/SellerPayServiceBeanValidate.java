package com.yinyang.sellerpay.dao.impl.bean.validate;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.util.ValidateUtil;

/**
 *验证
 */
public class SellerPayServiceBeanValidate {

	private static final Log log = LogFactory.getLogger(SellerPayServiceBeanValidate.class);
	
	private static final String ERROR_KEY = "PARAM_ERRORKEY";
	
	/**
	 * 创建支付账户
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayAccountValidate(ResultDto resultDto,PayAccountDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayAccount Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayAccountDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "accountType", dto.getAccountType()));
		set.add(ValidateUtil.required(errorMsg, "accountNumber", dto.getAccountNumber()));
		set.add(ValidateUtil.required(errorMsg, "signKey", dto.getSignKey()));
		set.add(ValidateUtil.required(errorMsg, "status", dto.getStatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	/**
	 * 创建支付业务
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayBusinessValidate(ResultDto resultDto,PayBusinessDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayBusiness Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayBusinessDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "accountId", dto.getAccountId()));
		set.add(ValidateUtil.required(errorMsg, "businessNumber", dto.getBusinessNumber()));
		set.add(ValidateUtil.required(errorMsg, "signKey", dto.getSignKey()));
		set.add(ValidateUtil.required(errorMsg, "reconciliationUrl", dto.getReconciliationUrl()));
		set.add(ValidateUtil.required(errorMsg, "callbackUrl", dto.getCallbackUrl()));
		set.add(ValidateUtil.required(errorMsg, "status", dto.getStatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	/**
	 * 创建渠道信息
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayChannelValidate(ResultDto resultDto,PayChannelDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayChannel Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayChannelDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "payUrl", dto.getPayUrl()));
		set.add(ValidateUtil.required(errorMsg, "callbackUrl", dto.getCallbackUrl()));
		set.add(ValidateUtil.required(errorMsg, "status", dto.getStatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
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
			log.error(errorMsg.toString());
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
			log.error(errorMsg.toString());
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
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	
	/**
	 * 增加支付订单流水对账信息。
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void createPayReconciliationValidate(ResultDto resultDto,PayReconciliationDTO dto){
		StringBuilder errorMsg = new StringBuilder(" createPayReconciliation Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayReconciliationDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "VOid", dto.getVOid()));
		set.add(ValidateUtil.required(errorMsg, "VStatus", dto.getVStatus()));
		set.add(ValidateUtil.required(errorMsg, "VAmountActual", dto.getVAmountActual()));
		set.add(ValidateUtil.required(errorMsg, "VMoneytypeActual", dto.getVMoneytypeActual()));
		set.add(ValidateUtil.required(errorMsg, "VPstatus", dto.getVPstatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
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
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
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
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	
	/**
	 * 增加支付订单流水对账信息。
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void updatePayStatusForReconciliationValidate(ResultDto resultDto,PayReconciliationDTO dto){
		StringBuilder errorMsg = new StringBuilder(" updatePayStatusForReconciliation Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayReconciliationDTO dto", dto) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "VOid", dto.getVOid()));
		set.add(ValidateUtil.required(errorMsg, "VStatus", dto.getVStatus()));
		set.add(ValidateUtil.required(errorMsg, "VAmountActual", dto.getVAmountActual()));
		set.add(ValidateUtil.required(errorMsg, "VMoneytypeActual", dto.getVMoneytypeActual()));
		set.add(ValidateUtil.required(errorMsg, "VPstatus", dto.getVPstatus()));
		set.add(ValidateUtil.required(errorMsg, "createDate", dto.getCreateDate()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	
	/**
	 * 增加支付订单流水对账信息。
	 * @param resultDto
	 * @param dto
	 */
	@SuppressWarnings("rawtypes")
	public static void handlePayEaseReconciliationValidate(ResultDto resultDto,PayAccountDTO payAccountDTO , PayFlowDTO payFlowDTO){
		StringBuilder errorMsg = new StringBuilder(" handlePayEaseReconciliation Valide failed: ");
		Set<Boolean> set = new HashSet<Boolean>();
		
		if(ValidateUtil.required(errorMsg, "PayFlowDTO dto", payFlowDTO) == false 
				|| ValidateUtil.required(errorMsg, "PayAccountDTO dto", payAccountDTO) == false){
			errorMsg.append(" dto is null");
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
			return;
		}
		
		set.add(ValidateUtil.required(errorMsg, "PayAccountDTO AccountNumber", payAccountDTO.getAccountNumber()));
		set.add(ValidateUtil.required(errorMsg, "PayAccountDTO SignKey", payAccountDTO.getSignKey()));
		set.add(ValidateUtil.required(errorMsg, "PayFlowDTO VOid", payFlowDTO.getVOid()));
		
		if(set.contains(false)){
			log.error(errorMsg.toString());
			resultDto.paramError(ERROR_KEY, errorMsg.toString());
		}
		
	}
	
}
