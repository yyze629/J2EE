package com.yinyang.sellerpay.web.service;

import java.io.Serializable;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.dhgate.merchant.api.MerchantService;
import com.yinyang.sellerpay.dao.api.GenericService;
import com.yinyang.sellerpay.dao.api.SellerPayService;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.TdLoginDTO;
import com.yinyang.sellerpay.dao.impl.GenericServiceImpl;
import com.yinyang.sellerpay.dao.impl.GenericServiceImplTest;
import com.yinyang.sellerpay.web.validate.PayEaseValidate;

@Service("loginDelegate")
public class LoginDelegate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7575416619733209076L;
	private static final Log logger = LogFactory.getLogger(LoginDelegate.class);
	
	//private SellerPayService  sellerPayService = ApsarasServiceFactory.getSellerPayService();
	//@Resource(name = "GenericService")
	
	//@Autowired
	
	@Autowired
	GenericService genericService;
	
	public Boolean isGetLoginOn(String loginName,String loginPassword){
		TdLoginDTO login = genericService.getLogin(loginName, loginPassword);
		if(login == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 根据业务编号查询业务信息。
	 * @param businessNumber
	 * @return
	 *//*
	public PayBusinessDTO getPayBusinessByNumber(String businessNumber){
		PayBusinessDTO dto = null;
		if(StringUtils.isBlank(businessNumber)){
			return dto;
		}
		dto = this.sellerPayService.getPayBusinessByNumber(businessNumber);
		return dto;
	}
	*//**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 *//*
	public PayOrderDTO getPayOrderInfo(String supplierId,String businessNumber,String orderNumber){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || StringUtils.isBlank(orderNumber)){
			return dto;
		}
		
		dto = this.sellerPayService.getPayOrderByBusiAndOrderNumber(supplierId, businessNumber, orderNumber);
		return dto;
	}
	
	*//**
	 * 验证支付订单信息的数字签名是否正确。
	 * 用于防止篡改。
	 * @param payBusinessDTO
	 * @param dto
	 * @return
	 *//*
	public Boolean validatePayOrderSign(PayBusinessDTO payBusinessDTO,PayOrderDTO payOrderDTO){
		boolean isConfirmed = false;
		if(payOrderDTO == null || payBusinessDTO == null || StringUtils.isBlank(payBusinessDTO.getSignKey()) 
				|| StringUtils.isBlank(payOrderDTO.getSupplierId()) || StringUtils.isBlank(payOrderDTO.getBusinessNumber()) 
				|| StringUtils.isBlank(payOrderDTO.getOrderNumber()) || payOrderDTO.getPayAmount() == null 
				|| StringUtils.isBlank(payOrderDTO.getPayMd5Info())){
			return isConfirmed;
		}
		isConfirmed = this.sellerPayService.validatePayOrderSign(payBusinessDTO, payOrderDTO);
		return isConfirmed;
	}
	*//**
	 * 增加支付订单信息。
	 * @param dto
	 *//*
	public ResultDto<Long> createPayOrder(PayOrderDTO dto){
		ResultDto<Long> resultDto = new ResultDto<Long>();
		PayEaseValidate.createPayOrderValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			logger.error("createPayOrder param error !");
			return resultDto;
		}
		resultDto = this.sellerPayService.createPayOrder(dto);
		
		return resultDto;
	}
	*//**
	 * 根据渠道ID，取得渠道信息。
	 * @param channelId
	 * @return
	 *//*
	public PayChannelDTO getPayChannelById(String channelId){
		PayChannelDTO dto = null;
		if(StringUtils.isBlank(channelId)){
			return dto;
		}
		dto = this.sellerPayService.getPayChannelById(channelId);
		return dto;
	}
	*//**
	 * 根据账户ID，取得支付账户信息。
	 * @param accountId
	 * @return
	 *//*
	public PayAccountDTO getPayAccountById(Long accountId){
		PayAccountDTO dto = null;
		if(accountId == null){
			return dto;
		}
		dto = this.sellerPayService.getPayAccountById(accountId);
		return dto;
	}
	*//**
	 * 根据业务ID，查询业务信息。
	 * @param businessId
	 * @return
	 *//*
	public PayBusinessDTO getPayBusinessById(Long businessId){
		PayBusinessDTO dto = null;
		if(businessId == null){
			return dto;
		}
		dto = this.sellerPayService.getPayBusinessById(businessId);
		return dto;
	}
	*//**
	 * 根据订单ID查询订单信息。
	 * @param orderId
	 * @return
	 *//*
	public PayOrderDTO getPayOrderById(Long orderId){
		PayOrderDTO dto = null;
		if(orderId == null){
			return dto;
		}
		dto = this.sellerPayService.getPayOrderById(orderId);
		return dto;
	}
	*//**
	 * 取得最近一条订单支付流水信息。
	 * @param businessId
	 * @param orderId
	 * @return
	 *//*
	public PayFlowDTO getPayFlowLastForOrder(Long businessId,Long orderId){
		PayFlowDTO dto = null;
		if(businessId == null || orderId == null){
			return dto;
		}
		
		dto = this.sellerPayService.getPayFlowLastForOrder(businessId, orderId);
		
		return dto;
	}
	
	*//**
	 * 根据订单ID，更新订单状态信息。
	 * @param orderId
	 * @param dto
	 *//*
	public ResultDto<Integer> updatePayOrderStatusById(Long orderId,PayOrderDTO dto){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		PayEaseValidate.updatePayOrderStatusByIdValidate(resultDto, orderId,dto);
		if(resultDto.isAck() == false){
			logger.error("updatePayOrderStatusById param error !");
			return resultDto;
		}
		resultDto = this.sellerPayService.updatePayOrderStatusById(orderId, dto);
		
		return resultDto;
	}
	*//**
	 * 生成首信易支付流水订单号。
	 * @param businessId
	 * @return
	 *//*
	public String generatePayEaseVOid(Long businessId){
		String payEaseVoid = null;
		if(businessId == null){
			return payEaseVoid;
		}
		payEaseVoid = this.sellerPayService.generatePayEaseVOid(businessId);
		return payEaseVoid;
	}
	*//**
	 * 增加支付订单流水信息。
	 * @param dto
	 * @return
	 *//*
	public ResultDto<String> createPayFlow(PayFlowDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		PayEaseValidate.createPayFlowValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			logger.error("createPayFlow param error !");
			return resultDto;
		}
		resultDto = this.sellerPayService.createPayFlow(dto);
		
		return resultDto;
	}
	*//**
	 * 生成首信易支付流水数据校验信息。
	 * @param supplierId
	 * @param dto
	 * @return
	 *//*
	public String generatePayFlowMd5info(String supplierId,PayFlowDTO dto){
		String md5Info = null;
		if(dto == null || StringUtils.isBlank(supplierId) || dto.getBusinessId() == null || StringUtils.isBlank(dto.getVMoneytype()) 
				|| StringUtils.isBlank(dto.getVYmd()) || dto.getVAmount() == null || StringUtils.isBlank(dto.getVOid())
				|| dto.getVMid() == null || StringUtils.isBlank(dto.getVUrl())){
			return md5Info;
		}
		md5Info = this.sellerPayService.generatePayFlowMd5info(supplierId, dto);
		
		return md5Info;
	}
	*//**
	 * 保存订单支付流水信息，并且更新订单状态为：已提交。
	 * @param supplierid
	 * @param dto
	 *//*
	public ResultDto<String> savePayFlow(String supplierid,PayFlowDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		PayEaseValidate.savePayFlowValidate(resultDto,supplierid, dto);
		if(resultDto.isAck() == false){
			logger.error("savePayFlow param error !");
			return resultDto;
		}
		resultDto = this.sellerPayService.savePayFlow(supplierid, dto);
		
		return resultDto;
	}
	*//**
	 * 校验首信易返回信息是否正确。
	 * @param dto
	 * @return
	 *//*
	public boolean checkPayEaseReturnSign(PayFlowDTO dto){
		boolean isCorrect = false;
		if(dto == null || StringUtils.isBlank(dto.getVOid()) || StringUtils.isBlank(dto.getVPstatus())
				|| dto.getVAmountActual() == null || StringUtils.isBlank(dto.getVMoneytypeActual()) 
				|| StringUtils.isBlank(dto.getVSign())){
			return isCorrect;
		}
		isCorrect = this.sellerPayService.checkPayEaseReturnSign(dto);
		return isCorrect;
	}
	*//**
	 * 根据支付流水ID，获取支付流水信息。
	 * @param VOid
	 * @return
	 *//*
	public PayFlowDTO getPayFlowByVOid(String VOid){
		PayFlowDTO dto = null;
		if(StringUtils.isBlank(VOid)){
			return dto;
		}
		dto = this.sellerPayService.getPayFlowByVOid(VOid);
		return dto;
	}
	*//**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、更新支付订单流水信息；2、更新支付订单信息。
	 * @param dto
	 * @return
	 *//*
	public ResultDto<Integer> updatePayStatusForPayEaseCallback(PayFlowDTO dto){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		PayEaseValidate.updatePayStatusForPayEaseCallbackValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			logger.error("updatePayStatusForPayEaseCallback param error !");
			return resultDto;
		}
		resultDto = this.sellerPayService.updatePayStatusForPayEaseCallback(dto);
		
		return resultDto;
	}*/
	
}
