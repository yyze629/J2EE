package com.yinyang.sellerpay.dao.impl.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Md5;
import com.dhgate.common.util.Page;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowQueryDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowTaskDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.impl.SellerPayDaoImpl;
import com.yinyang.sellerpay.dao.impl.bean.validate.SellerPayServiceBeanValidate;
import com.yinyang.sellerpay.dao.po.TdPayFlow;
//import com.yinyang.sellerpay.dao.util.AesUtil;
import com.yinyang.sellerpay.dao.util.CapitalReturnUtil;
import com.yinyang.sellerpay.dao.util.CapitalVerifySignUtil;
import com.yinyang.sellerpay.dao.util.DateUtil;
import com.yinyang.sellerpay.dao.util.SellerPayConstant;
import com.yinyang.sellerpay.dao.xmlparse.PayEaseXmlParse;

@Service
public class SellerPayServiceBean {

	private static final Log log = LogFactory.getLogger(SellerPayServiceBean.class);
	
	@Autowired
	SellerPayDaoImpl sellerPayDao;
	
	/**
	 * 增加支付账户。
	 * @param dto
	 * @return
	 */
	public ResultDto<Long> createPayAccount(PayAccountDTO dto){
		ResultDto<Long> resultDto = new ResultDto<Long>();
		SellerPayServiceBeanValidate.createPayAccountValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("CreatePayAccount param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayAccount(dto);
		resultDto.data(dto.getAccountId());
		
		return resultDto;
	}
	
	/**
	 * 增加业务信息。
	 * @param dto
	 */
	public ResultDto<Long> createPayBusiness(PayBusinessDTO dto){
		ResultDto<Long> resultDto = new ResultDto<Long>();
		SellerPayServiceBeanValidate.createPayBusinessValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("createPayBusiness param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayBusiness(dto);
		resultDto.data(dto.getBusinessId());
		
		return resultDto;
	}
	/**
	 * 增加渠道信息。
	 * @param dto
	 */
	public ResultDto<String> createPayChannel(PayChannelDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		SellerPayServiceBeanValidate.createPayChannelValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("createPayChannel param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayChannel(dto);
		resultDto.data(dto.getChannelId());
		
		return resultDto;
	}
	/**
	 * 增加支付订单信息。
	 * @param dto
	 */
	public ResultDto<Long> createPayOrder(PayOrderDTO dto){
		ResultDto<Long> resultDto = new ResultDto<Long>();
		SellerPayServiceBeanValidate.createPayOrderValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("createPayOrder param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayOrder(dto);
		resultDto.data(dto.getOrderId());
		
		return resultDto;
	}
	/**
	 * 增加支付订单流水信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<String> createPayFlow(PayFlowDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		SellerPayServiceBeanValidate.createPayFlowValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("createPayFlow param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayFlow(dto);
		resultDto.data(dto.getPayFlowId());
		
		return resultDto;
	}
	/**
	 * 增加支付订单流水对账信息。
	 * @param dto
	 */
	public ResultDto<String> createPayReconciliation(PayReconciliationDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		SellerPayServiceBeanValidate.createPayReconciliationValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("createPayReconciliation param error !");
			return resultDto;
		}
		this.sellerPayDao.createPayReconciliation(dto);
		resultDto.data(dto.getReconciliationId());
		
		return resultDto;
	}
	/**
	 * 根据账户ID，取得支付账户信息。
	 * @param accountId
	 * @return
	 */
	public PayAccountDTO getPayAccountById(Long accountId){
		PayAccountDTO dto = null;
		if(accountId == null){
			return dto;
		}
		dto = this.sellerPayDao.getPayAccountById(accountId);
		return dto;
	}
	/**
	 * 根据业务ID，查询业务信息。
	 * @param businessId
	 * @return
	 */
	public PayBusinessDTO getPayBusinessById(Long businessId){
		PayBusinessDTO dto = null;
		if(businessId == null){
			return dto;
		}
		dto = this.sellerPayDao.getPayBusinessById(businessId);
		return dto;
	}
	/**
	 * 根据订单ID查询订单信息。
	 * @param orderId
	 * @return
	 */
	public PayOrderDTO getPayOrderById(Long orderId){
		PayOrderDTO dto = null;
		if(orderId == null){
			return dto;
		}
		dto = this.sellerPayDao.getPayOrderById(orderId);
		return dto;
	}
	/**
	 * 根据业务编号查询业务信息。
	 * @param businessNumber
	 * @return
	 */
	public PayBusinessDTO getPayBusinessByNumber(String businessNumber){
		PayBusinessDTO dto = null;
		if(StringUtils.isBlank(businessNumber)){
			return dto;
		}
		dto = this.sellerPayDao.getPayBusinessByNumber(businessNumber);
		return dto;
	}
	/**
	 * 根据渠道ID，取得渠道信息。
	 * @param channelId
	 * @return
	 */
	public PayChannelDTO getPayChannelById(String channelId){
		PayChannelDTO dto = null;
		if(StringUtils.isBlank(channelId)){
			return dto;
		}
		dto = this.sellerPayDao.getPayChannelById(channelId);
		return dto;
	}
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderByBusiAndOrderNumber(String supplierId,String businessNumber,String orderNumber){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || StringUtils.isBlank(orderNumber)){
			return dto;
		}
		
		dto = this.sellerPayDao.getPayOrderByBusiAndOrderNumber(supplierId, businessNumber, orderNumber);
		return dto;
	}
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderInfo(String supplierId,String businessNumber,String orderNumber){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || StringUtils.isBlank(orderNumber)){
			return dto;
		}
		
		dto = this.sellerPayDao.getPayOrderByBusiAndOrderNumber(supplierId, businessNumber, orderNumber);
		return dto;
	}
	/**
	 * 根据订单ID，更新订单状态信息。
	 * @param orderId
	 * @param dto
	 */
	public ResultDto<Integer> updatePayOrderStatusById(Long orderId,PayOrderDTO dto){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		SellerPayServiceBeanValidate.updatePayOrderStatusByIdValidate(resultDto, orderId,dto);
		if(resultDto.isAck() == false){
			log.error("updatePayOrderStatusById param error !");
			return resultDto;
		}
		this.sellerPayDao.updatePayOrderStatusById(orderId, dto);
		resultDto.data(1);
		
		return resultDto;
	}
	
	/**
	 * 根据支付流水ID，获取支付流水信息。
	 * @param VOid
	 * @return
	 */
	public PayFlowDTO getPayFlowByVOid(String VOid){
		PayFlowDTO dto = null;
		if(StringUtils.isBlank(VOid)){
			return dto;
		}
		dto = this.sellerPayDao.getPayFlowByVOid(VOid);
		return dto;
	}
	
	/**
	 * 是否存在相同的订单。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public Boolean isExistSamePayOrder(String supplierId,String businessNumber,String orderNumber){
		Boolean hasPayOrder = true;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || StringUtils.isBlank(orderNumber)){
			return hasPayOrder;
		}
		PayOrderDTO dto = this.sellerPayDao.getPayOrderByBusiAndOrderNumber(supplierId, businessNumber, orderNumber);
		if(dto == null){
			hasPayOrder = false;
		}
		return hasPayOrder;
	}
	
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、更新支付订单流水信息；2、更新支付订单信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<Integer> updatePayStatusForPayEaseCallback(PayFlowDTO dto){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		SellerPayServiceBeanValidate.updatePayStatusForPayEaseCallbackValidate(resultDto, dto);
		if(resultDto.isAck() == false){
			log.error("updatePayStatusForPayEaseCallback param error !");
			return resultDto;
		}
		
		this.sellerPayDao.updatePayStatusForPayEaseCallback(dto);
		resultDto.data(1);
		
		return resultDto;
	}
	
	/**
	 * 验证支付订单信息的数字签名是否正确。
	 * 用于防止篡改。
	 * @param payBusinessDTO
	 * @param dto
	 * @return
	 */
	public Boolean validatePayOrderSign(PayBusinessDTO payBusinessDTO,PayOrderDTO dto){
		boolean isConfirmed = false;
		if(dto == null || payBusinessDTO == null || StringUtils.isBlank(payBusinessDTO.getSignKey()) 
				|| StringUtils.isBlank(dto.getSupplierId()) || StringUtils.isBlank(dto.getBusinessNumber()) 
				|| StringUtils.isBlank(dto.getOrderNumber()) || dto.getPayAmount() == null 
				|| StringUtils.isBlank(dto.getPayMd5Info())){
			return isConfirmed;
		}
		log.info("validatePayOrderSign:{SupplierId:"+dto.getSupplierId()+",OrderNumber:"+dto.getOrderNumber()
				+",PayMd5Info:"+dto.getPayMd5Info()+"}");
		StringBuilder sb = new StringBuilder();
		sb.append(dto.getSupplierId());
		sb.append(dto.getBusinessNumber());
		sb.append(dto.getOrderNumber());
		sb.append(dto.getPayAmount().toString());
		try {
			/*String businessSignKey = AesUtil.aesDecrypt(payBusinessDTO.getSignKey(), SellerPayConstant.SensitiveInfoSignKey);
			log.info("validatePayOrderSign:{SupplierId:"+dto.getSupplierId()+",OrderNumber:"+dto.getOrderNumber()
					+",businessSignKey:"+businessSignKey+",text:"+sb.toString()+"}");
			String confirmMsg = AesUtil.aesEncrypt(sb.toString(), businessSignKey);
			String md5Msg =  Md5.getMd5(confirmMsg);
			log.info("validatePayOrderSign:{SupplierId:"+dto.getSupplierId()+",OrderNumber:"+dto.getOrderNumber()
					+",businessSignKey:"+businessSignKey+",md5Msg:"+md5Msg+"}");
			if(md5Msg.equals(dto.getPayMd5Info())){
				isConfirmed = true;
			}*/
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error(e);
		}
		return isConfirmed;
	}
	/**
	 * 取得最近一条订单支付流水信息。
	 * @param businessId
	 * @param orderId
	 * @return
	 */
	public PayFlowDTO getPayFlowLastForOrder(Long businessId,Long orderId){
		PayFlowDTO dto = null;
		if(businessId == null || orderId == null){
			return dto;
		}
		
		dto = this.sellerPayDao.getPayFlowLastForOrder(businessId, orderId);
		
		return dto;
	}
	/**
	 * 生成首信易支付流水订单号。
	 * @param businessId
	 * @return
	 */
	public String generatePayEaseVOid(Long businessId){
		String payEaseVoid = null;
		if(businessId == null){
			return payEaseVoid;
		}
		PayBusinessDTO payBusinessDTO = this.getPayBusinessById(businessId);
		if(payBusinessDTO != null && payBusinessDTO.getAccountId() != null){
			PayAccountDTO payAccountDTO = this.getPayAccountById(payBusinessDTO.getAccountId());
			if(payAccountDTO != null && StringUtils.isNotBlank(payAccountDTO.getAccountNumber())){
				String voidSeq = this.sellerPayDao.getPayFlowVOidSeq();
				if(StringUtils.isNotBlank(voidSeq)){
					StringBuffer sb = new StringBuffer();
					sb.append(DateUtil.getDateyyyyMMddStr(new Date(System.currentTimeMillis())));
					sb.append("-"+payAccountDTO.getAccountNumber());
					sb.append("-SP"+businessId);
					sb.append("-"+voidSeq);
					payEaseVoid = sb.toString();
				}
			}
		}
		return payEaseVoid;
	}
	/**
	 * 生成首信易支付流水数据校验信息。
	 * @param supplierId
	 * @param dto
	 * @return
	 */
	public String generatePayFlowMd5info(String supplierId,PayFlowDTO dto){
		String md5Info = null;
		if(dto == null || StringUtils.isBlank(supplierId) || dto.getBusinessId() == null || StringUtils.isBlank(dto.getVMoneytype()) 
				|| StringUtils.isBlank(dto.getVYmd()) || dto.getVAmount() == null || StringUtils.isBlank(dto.getVOid())
				|| dto.getVMid() == null || StringUtils.isBlank(dto.getVUrl())){
			return md5Info;
		}
		PayBusinessDTO payBusinessDTO = this.getPayBusinessById(dto.getBusinessId());
		if(payBusinessDTO != null && payBusinessDTO.getAccountId() != null){
			PayAccountDTO payAccountDTO = this.getPayAccountById(payBusinessDTO.getAccountId());
			if(payAccountDTO != null && StringUtils.isNotBlank(payAccountDTO.getSignKey())){
				StringBuffer text = new StringBuffer();
				text.append(dto.getVMoneytype());
				text.append(dto.getVYmd());
				text.append(dto.getVAmount());
				text.append(supplierId);
				text.append(dto.getVOid());
				text.append(dto.getVMid());
				text.append(dto.getVUrl());
		        /*try {
		        	String signKey = AesUtil.aesDecrypt(payAccountDTO.getSignKey(), SellerPayConstant.SensitiveInfoSignKey);
		        	md5Info = CapitalVerifySignUtil.generateDHBVerifySign(text.toString(), signKey);
		 		} catch (Exception e) {
		 			log.error("产生首信易订单校验数据时出错");
		 			log.error(e);
		 		}*/
			}
		}
		return md5Info;
	}
	/**
	 * 保存订单支付流水信息，并且更新订单状态为：已提交。
	 * @param supplierid
	 * @param dto
	 */
	public ResultDto<String> savePayFlow(String supplierid,PayFlowDTO dto){
		ResultDto<String> resultDto = new ResultDto<String>();
		SellerPayServiceBeanValidate.savePayFlowValidate(resultDto,supplierid, dto);
		if(resultDto.isAck() == false){
			log.error("savePayFlow param error !");
			return resultDto;
		}
		this.sellerPayDao.savePayFlow(supplierid, dto);
		resultDto.data(dto.getPayFlowId());
		
		return resultDto;
	}
	
	/**
	 * 校验首信易返回信息是否正确。
	 * @param dto
	 * @return
	 */
	public boolean checkPayEaseReturnSign(PayFlowDTO dto){
		boolean isCorrect = false;
		if(dto == null || StringUtils.isBlank(dto.getVOid()) || StringUtils.isBlank(dto.getVPstatus())
				|| StringUtils.isBlank(dto.getVAmountActualStr())|| StringUtils.isBlank(dto.getVMoneytypeActual()) 
				|| StringUtils.isBlank(dto.getVSign())){
			return isCorrect;
		}
		isCorrect = CapitalReturnUtil.checkPayReturn(dto.getVOid(),dto.getVPstatus(),dto.getVAmountActualStr(),dto.getVMoneytypeActual(),dto.getVSign());
		return isCorrect;
	}
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、保存对账信息；2、更新支付订单流水信息；3、更新支付订单信息。
	 * 
	 * @param updateDTO
	 */
	public ResultDto<Integer> updatePayStatusForReconciliation(PayReconciliationDTO updateDTO){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		SellerPayServiceBeanValidate.updatePayStatusForReconciliationValidate(resultDto, updateDTO);
		if(resultDto.isAck() == false){
			log.error("updatePayStatusForReconciliation param error !");
			return resultDto;
		}
		
		this.sellerPayDao.updatePayStatusForReconciliation(updateDTO);
		resultDto.data(1);
		
		return resultDto;
	}
	/**
	 * 根据订单ID数组，查询订单信息。
	 * 同时包含支付流水信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumberArray
	 * @return
	 */
	public List<PayOrderDTO> getPayOrderList(String supplierId,String businessNumber,String[] orderNumberArray){
		List<PayOrderDTO> resultList = new ArrayList<PayOrderDTO>();
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || orderNumberArray == null || orderNumberArray.length <= 0){
			return resultList;
		}
		resultList = this.sellerPayDao.getPayOrderList(supplierId, businessNumber, orderNumberArray);
		if(resultList != null && resultList.size() > 0){
			
			List<Long> orderIdList = new ArrayList<Long>();
			Long businessId = null;
			for(PayOrderDTO dto : resultList){
				if(dto != null && dto.getOrderId() != null){
					orderIdList.add(dto.getOrderId());
					if(dto.getBusinessId() != null){
						businessId = dto.getBusinessId();
					}
				}
			}
			if(orderIdList.size() > 0){
				Map<Long,List<PayFlowDTO>> payOrderMap = new HashMap<Long,List<PayFlowDTO>>();
				List<PayFlowDTO> list = this.sellerPayDao.getPayFlowList(businessId, orderIdList.toArray(new Long[orderIdList.size()]));
				if(list != null && list.size() > 0){
					for(PayFlowDTO payFlowDTO : list){
						if(payFlowDTO != null && payFlowDTO.getOrderId() != null){
							List<PayFlowDTO> payFlowList = payOrderMap.get(payFlowDTO.getOrderId());
							if(payFlowList != null){
								payFlowList.add(payFlowDTO);
							}else{
								payFlowList = new ArrayList<PayFlowDTO>();
								payFlowList.add(payFlowDTO);
							}
							
							payOrderMap.put(payFlowDTO.getOrderId(), payFlowList);
						}
					}
					for(PayOrderDTO dto : resultList){
						if(dto != null && dto.getOrderId() != null){
							
							List<PayFlowDTO> payFlowList = payOrderMap.get(dto.getOrderId());
							dto.setPayFlowList(payFlowList);
						}
					}
				}
			}
		}
		return resultList;
	}
	/**
	 * 分页查询订单流水信息。
	 * @param queryDTO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page findPayFlowPage(PayFlowQueryDTO queryDTO,int pageNo, int pageSize) {
		if(queryDTO == null || queryDTO.getPayFlowDTO() == null){
			return null;
		}
		Page result = this.sellerPayDao.findPayFlowPage(queryDTO, pageNo, pageSize);
		if(result != null && result.getResult() != null && result.getResult().size() > 0){
			List<TdPayFlow> list = result.getResult();
			List<PayFlowDTO> dtoList = new ArrayList<PayFlowDTO>();
			for(TdPayFlow po : list){
				if(po != null){
					PayFlowDTO dto = po.po2dto();
					dtoList.add(dto);
				}
			}
			
			result.setResult(dtoList);
		}
		return result;
	}
	/**
	 * 分页查询订单流水信息。
	 * @param queryDTO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page findPayFlowTaskPage(PayFlowQueryDTO queryDTO,int pageNo, int pageSize) {
		if(queryDTO == null || queryDTO.getPayFlowDTO() == null){
			return null;
		}
		Page result = this.sellerPayDao.findPayFlowPage(queryDTO, pageNo, pageSize);
		if(result != null && result.getResult() != null && result.getResult().size() > 0){
			List<TdPayFlow> list = result.getResult();
			List<PayFlowTaskDTO> dtoList = new ArrayList<PayFlowTaskDTO>();
			Set<String> accountNumberSet = new HashSet<String>();
			for(TdPayFlow po : list){
				if(po != null && StringUtils.isNotBlank(po.getVOid()) && po.getVMid() != null){
					PayFlowTaskDTO payFlowTaskDTO = new PayFlowTaskDTO();
					PayFlowDTO dto = po.po2dto();
					payFlowTaskDTO.setPayFlowDTO(dto);
					dtoList.add(payFlowTaskDTO);
					
					accountNumberSet.add(po.getVMid().toString());
				}
			}
			List<PayAccountDTO> accountList = this.sellerPayDao.getPayAccountListByNumbers(accountNumberSet.toArray(new String[accountNumberSet.size()]));
			if(accountList != null && accountList.size() > 0){
				Map<Long,PayAccountDTO> acmp = new HashMap<Long,PayAccountDTO>();
				for(PayAccountDTO payAccountDTO : accountList){
					if(payAccountDTO != null && StringUtils.isNotBlank(payAccountDTO.getAccountNumber()) && StringUtils.isNotBlank(payAccountDTO.getSignKey())){
						acmp.put(Long.parseLong(payAccountDTO.getAccountNumber()), payAccountDTO);
					}
				}
				for(PayFlowTaskDTO payFlowTaskDTO : dtoList){
					if(payFlowTaskDTO != null && payFlowTaskDTO.getPayFlowDTO() != null && payFlowTaskDTO.getPayFlowDTO().getVMid() != null){
						PayAccountDTO payAccountDTO = acmp.get(payFlowTaskDTO.getPayFlowDTO().getVMid());
						payFlowTaskDTO.setPayAccountDTO(payAccountDTO);
					}
				}
			}
			
			result.setResult(dtoList);
		}
		return result;
	}
	
	/**
	 * 校验首信易返回信息是否正确。
	 * @param dto
	 * @return
	 */
	public boolean checkPayEaseReconciliationSign(PayReconciliationDTO dto){
		boolean isCorrect = false;
		if(dto == null || StringUtils.isBlank(dto.getVOid()) || StringUtils.isBlank(dto.getVPstatus())
				|| StringUtils.isBlank(dto.getVAmountActualStr())|| StringUtils.isBlank(dto.getVMoneytypeActual()) 
				|| StringUtils.isBlank(dto.getVSign())){
			return isCorrect;
		}
		isCorrect = CapitalReturnUtil.checkPayReturn(dto.getVOid(),dto.getVPstatus(),dto.getVAmountActualStr(),dto.getVMoneytypeActual(),dto.getVSign());
		return isCorrect;
	}
	
	/**
	 * 处理首信易对账操作。
	 * @param payFlowDTO
	 * @param payAccountDTO
	 * @return
	 */
	/*public ResultDto<Integer> handlePayEaseReconciliation(PayAccountDTO payAccountDTO , PayFlowDTO payFlowDTO){
		ResultDto<Integer> resultDto = new ResultDto<Integer>();
		SellerPayServiceBeanValidate.handlePayEaseReconciliationValidate(resultDto, payAccountDTO , payFlowDTO);
		if(resultDto.isAck() == false){
			log.error("handlePayEaseReconciliation param error !");
			return resultDto;
		}
		
		*//**1、从配置账户中得到首信易账户秘钥。*//*
		String signKey = "";
		try {
			signKey = AesUtil.aesDecrypt(payAccountDTO.getSignKey(), SellerPayConstant.SensitiveInfoSignKey);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("1、解密首信易账户秘钥失败  !{PayAccountDTO.SignKey:"+payAccountDTO.getSignKey()+",AesUtilKey:"+SellerPayConstant.SensitiveInfoSignKey+"}");
			log.error(e);
		}
		if(StringUtils.isBlank(signKey)){
			log.error("1、获取首信易账户秘钥失败  !{PayAccountDTO.SignKey:"+payAccountDTO.getSignKey()+",AesUtilKey:"+SellerPayConstant.SensitiveInfoSignKey+"}");
			resultDto.data(0);
			
			return resultDto;
		}
		log.info("1、获取首信易账户秘钥成功  !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
		
		*//**2、发起查询情况，得到首信易返回的XML形式的查询结果。*//*
		String xmlContent = PayEaseHttpClient.getReconciliationXmlContent(payAccountDTO.getAccountNumber(), payFlowDTO.getVOid(), signKey);
		log.info("2、首信易对账返回XML:{xmlContent:"+xmlContent+"}");
		if(StringUtils.isBlank(xmlContent)){
			log.error("2、首信易对账返回XML结果为空 !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
			resultDto.data(0);
			
			return resultDto;
		}*/
		
		/**----------测试代码---------start--------------------------**/
		/*xmlContent = PayEaseXmlParse.getTestPayEaseXmlContent();*/
		/**----------测试代码---------end--------------------------**/
		
		/**3、解析得到首信易返回的XML形式的查询结果。*/
		/*PayReconciliationDTO dto = PayEaseXmlParse.reconciliationParse(xmlContent);
		if(dto == null){
			log.error("3、首信易对账返回XML解析失败 !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"},{XmlContent:"+xmlContent+"}");
			resultDto.data(0);
			
			return resultDto;
		}
		log.info("3、首信易对账返回XML解析成功  !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
		
		*//**4、校验首信易返回的数字签名。*//*
		boolean isCorrect = this.checkPayEaseReconciliationSign(dto);
		if(!isCorrect){
			log.error("4、校验首信易对账返回数字签名失败 !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"},{XmlContent:"+xmlContent+"}");
			resultDto.data(0);
			
			return resultDto;
		}
		log.info("4、校验首信易对账返回数字签名成功  !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
		
		*//**----------测试代码---------start--------------------------**//*
		dto.setVOid(payFlowDTO.getVOid());
		dto.setVPmodeDesc("Pmode_TEST");
		dto.setVAmountActualStr(payFlowDTO.getVAmount().toString());
		dto.setVMoneytypeActual(payFlowDTO.getVMoneytype());
		*//**----------测试代码---------end--------------------------**//*
		
		*//**5、保存首信易对账结果。*//*
		dto.setChannelId(payFlowDTO.getChannelId());
		dto.setPayFlowId(payFlowDTO.getPayFlowId());
		resultDto = this.updatePayStatusForReconciliation(dto);
		if(resultDto.isAck() == false){
			log.error("5、保存首信易对账结果失败  !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
			return resultDto;
		}else{
			log.info("5、保存首信易对账结果成功  !{AccountNumber:"+payAccountDTO.getAccountNumber()+",VOid:"+payFlowDTO.getVOid()+"}");
		}
		
		return resultDto;
	}*/
	/**
	 * 查询给定的支付流水的最大值。
	 * @param payFlowIdArray
	 * @return
	 */
	public String getMaxPayFlowIdForJob(String[] payFlowIdArray) {
		String maxPayFlowId = null;
		if(payFlowIdArray == null || payFlowIdArray.length <= 0){
			return maxPayFlowId;
		}
		maxPayFlowId = this.sellerPayDao.getMaxPayFlowIdForJob(payFlowIdArray);
		return maxPayFlowId;
	}
	
}
