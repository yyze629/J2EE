package com.yinyang.sellerpay.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.dhgate.common.util.Page;
import com.dhgate.framework.service.dao.GenericDao;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowQueryDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.po.TdPayAccount;
import com.yinyang.sellerpay.dao.po.TdPayBusiness;
import com.yinyang.sellerpay.dao.po.TdPayChannel;
import com.yinyang.sellerpay.dao.po.TdPayFlow;
import com.yinyang.sellerpay.dao.po.TdPayOrder;
import com.yinyang.sellerpay.dao.po.TdPayReconciliation;
import com.yinyang.sellerpay.dao.util.SellerPayConstant;

@Repository("sellerPayDao")
public class SellerPayDaoImpl {
	
	private static final Log log = LogFactory.getLogger(SellerPayDaoImpl.class);
	
	private static Set<String> queryPayFlowOrderbySet= new HashSet<String>();
	static{
		queryPayFlowOrderbySet.add("payFlowId");
		queryPayFlowOrderbySet.add("orderId");
		queryPayFlowOrderbySet.add("businessId");
		queryPayFlowOrderbySet.add("VOid");
		queryPayFlowOrderbySet.add("createDate");
	}
	
	@Resource(name = "dhSellerPayDao")
	public GenericDao dao;
	
	@Transactional
	public void createPayAccount(PayAccountDTO dto){
		if(dto == null || StringUtils.isBlank(dto.getAccountNumber()) || StringUtils.isBlank(dto.getAccountType()) || StringUtils.isBlank(dto.getSignKey())){
			return ;
		}
		
		TdPayAccount po = new TdPayAccount();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setAccountId(po.getAccountId());
		
	}
	
	@Transactional(readOnly = true)
	public PayAccountDTO getPayAccountById(Long accountId){
		PayAccountDTO dto = null;
		if(accountId == null){
			return dto;
		}
		TdPayAccount po = this.dao.get(TdPayAccount.class, accountId);
		if(po != null){
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional(readOnly = true)
	public PayAccountDTO getPayAccountByNumber(String accountNumber){
		PayAccountDTO dto = null;
		if(StringUtils.isBlank(accountNumber)){
			return dto;
		}
		StringBuilder hql = new StringBuilder("from TdPayAccount t where t.accountNumber= :accountNumber ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNumber", accountNumber);

		List<TdPayAccount> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayAccount po = list.get(0);
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional
	public void deletePayAccountById(Long accountId){
		if(accountId == null){
			return ;
		}
		Query query = dao.createQuery("delete from TdPayAccount t where t.accountId=:accountId",null);
		query.setLong("accountId", accountId);
		query.executeUpdate();
	}
	
	@Transactional
	public void createPayBusiness(PayBusinessDTO dto){
		if(dto == null || dto.getAccountId() == null || StringUtils.isBlank(dto.getBusinessNumber()) 
				|| StringUtils.isBlank(dto.getCallbackUrl()) || StringUtils.isBlank(dto.getSignKey())){
			return ;
		}
		
		TdPayBusiness po = new TdPayBusiness();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setBusinessId(po.getBusinessId());
	}
	
	@Transactional(readOnly = true)
	public PayBusinessDTO getPayBusinessById(Long businessId){
		PayBusinessDTO dto = null;
		if(businessId == null){
			return dto;
		}
		TdPayBusiness po = this.dao.get(TdPayBusiness.class, businessId);
		if(po != null){
			dto = po.po2dto();
		}
		return dto;
	}
	
	/**
	 * 根据业务编号查询业务信息。
	 * @param businessNumber
	 * @return
	 */
	@Transactional(readOnly = true)
	public PayBusinessDTO getPayBusinessByNumber(String businessNumber){
		PayBusinessDTO dto = null;
		if(StringUtils.isBlank(businessNumber)){
			return dto;
		}
		StringBuilder hql = new StringBuilder("from TdPayBusiness t where t.businessNumber= :businessNumber ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessNumber", businessNumber);

		List<TdPayBusiness> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayBusiness po = list.get(0);
			dto = po.po2dto();
		}
		
		return dto;
	}
	
	@Transactional
	public void createPayChannel(PayChannelDTO dto){
		if(dto == null || StringUtils.isBlank(dto.getCallbackUrl()) 
				|| StringUtils.isBlank(dto.getPayUrl()) || StringUtils.isBlank(dto.getStatus())){
			return ;
		}
		
		TdPayChannel po = new TdPayChannel();
		po.dto2po(dto);
		this.dao.save(po);
		
		dto.setChannelId(po.getChannelId());
	}
	
	@Transactional(readOnly = true)
	public PayChannelDTO getPayChannelById(String channelId){
		PayChannelDTO dto = null;
		if(StringUtils.isBlank(channelId)){
			return dto;
		}
		TdPayChannel po = this.dao.get(TdPayChannel.class, channelId);
		
		if(po != null){
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional
	public void createPayOrder(PayOrderDTO dto){
		if(dto == null){
			return ;
		}
		TdPayOrder po = new TdPayOrder();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setOrderId(po.getOrderId());
	}
	
	@Transactional(readOnly = true)
	public PayOrderDTO getPayOrderById(Long orderId){
		PayOrderDTO dto = null;
		if(orderId == null){
			return dto;
		}
		TdPayOrder po = this.dao.get(TdPayOrder.class, orderId);
		if(po != null){
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional(readOnly = true)
	public PayOrderDTO getPayOrderByOrderNumber(String supplierId,String orderNumber){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(orderNumber)){
			return dto;
		}
		
		StringBuilder hql = new StringBuilder("from TdPayOrder t where t.supplierId= :supplierId and t.orderNumber= :orderNumber ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierId", supplierId);
		params.put("orderNumber", orderNumber);

		List<TdPayOrder> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayOrder po = list.get(0);
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional(readOnly = true)
	public PayOrderDTO getPayOrderByBusiAndOrderNumber(String supplierId,String businessNumber,String orderNumber){
		PayOrderDTO dto = null;
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || StringUtils.isBlank(orderNumber)){
			return dto;
		}
		
		StringBuilder hql = new StringBuilder("from TdPayOrder t where t.supplierId= :supplierId " +
				"and t.businessNumber= :businessNumber and t.orderNumber= :orderNumber ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierId", supplierId);
		params.put("businessNumber", businessNumber);
		params.put("orderNumber", orderNumber);

		List<TdPayOrder> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayOrder po = list.get(0);
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional
	public Integer updatePayOrderStatus(PayOrderDTO dto){
		if(dto == null || dto.getBusinessId() == null || dto.getOrderId() == null || StringUtils.isBlank(dto.getStatus())){
			return 0;
		}
		
		StringBuilder hql = new StringBuilder("update TdPayOrder t set t.status = :status,t.updateDate = sysdate  ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("status", dto.getStatus());
		if(StringUtils.isNotBlank(dto.getOperator())){
			hql.append(", t.operator = :operator ");
			params.put("operator", dto.getOperator());
		}
		if(StringUtils.isNotBlank(dto.getRemarks())){
			hql.append(", t.remarks = :remarks ");
			params.put("remarks", dto.getRemarks());
		}
		hql.append(" where t.orderId= :orderId  and t.businessId= :businessId ");
		params.put("orderId", dto.getOrderId());
		params.put("businessId", dto.getBusinessId());
		
		int count = this.dao.update(hql.toString(), params);
		
		return count;
	}
	@Transactional
	public void updatePayOrderStatusById(Long orderId,PayOrderDTO dto){
		if(orderId == null || dto == null || StringUtils.isBlank(dto.getStatus())){
			return ;
		}
		TdPayOrder po = this.dao.get(TdPayOrder.class, orderId);
		if(po != null){
			po.setStatus(dto.getStatus());
			po.setUpdateDate(new Date(System.currentTimeMillis()));
			if(StringUtils.isNotBlank(dto.getOperator())){
				po.setOperator(dto.getOperator());
			}
			if(StringUtils.isNotBlank(dto.getRemarks())){
				po.setRemarks(dto.getRemarks());
			}
			
			this.dao.update(po);
		}
	}
	
	@Transactional
	public void createPayFlow(PayFlowDTO dto){
		if(dto == null){
			return ;
		}
		TdPayFlow po = new TdPayFlow();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setPayFlowId(po.getPayFlowId());
	}

	@Transactional
	public Integer updatePayFlowStatusByVOid(String VOid,String VPstatus,String VPstring,String VIsvirement,Double VAmountActual,String VMoneytypeActual){
		if(StringUtils.isBlank(VOid)){
			return 0;
		}
		
		StringBuilder hql = new StringBuilder("update TdPayFlow t set t.updateDate = sysdate  " );
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" ,t.VPstatus = :VPstatus,t.VPstring = :VPstring , t.VIsvirement = :VIsvirement");
		hql.append(" ,t.VAmountActual = :VAmountActual , t.VMoneytypeActual = :VMoneytypeActual ");
		params.put("VPstatus", VPstatus);
		params.put("VPstring", VPstring);
		params.put("VAmountActual", VAmountActual);
		params.put("VMoneytypeActual", VMoneytypeActual);
		
		if(StringUtils.isNotBlank(VIsvirement)){
			hql.append(" , t.VIsvirement = :VIsvirement ");
			params.put("VIsvirement", VIsvirement);
		}
		
		hql.append(" where t.VOid= :VOid ");
		params.put("VOid", VOid);
		
		int count = this.dao.update(hql.toString(), params);
		
		return count;
	}
	@Transactional
	public Integer updatePayFlowStatus(PayFlowDTO updateDTO){
		if(updateDTO == null || StringUtils.isBlank(updateDTO.getVOid()) || StringUtils.isBlank(updateDTO.getVPstatus()) 
				|| updateDTO.getVAmountActual() == null || StringUtils.isBlank(updateDTO.getVMoneytypeActual())){
			return 0;
		}
		
		StringBuilder hql = new StringBuilder("update TdPayFlow t set t.updateDate = sysdate  " );
		Map<String, Object> params = new HashMap<String, Object>();
		hql.append(" ,t.VPstatus = :VPstatus,t.VAmountActual = :VAmountActual , t.VMoneytypeActual = :VMoneytypeActual ");
		params.put("VPstatus", updateDTO.getVPstatus());
		params.put("VAmountActual", updateDTO.getVAmountActual());
		params.put("VMoneytypeActual", updateDTO.getVMoneytypeActual());
		
		if(StringUtils.isNotBlank(updateDTO.getVPmodeDesc())){
			hql.append(" ,t.VPmodeDesc = :VPmodeDesc ");
			params.put("VPmodeDesc", updateDTO.getVPmodeDesc());
		}
		
		if(StringUtils.isNotBlank(updateDTO.getVPstring())){
			hql.append(" , t.VPstring = :VPstring ");
			params.put("VPstring", updateDTO.getVPstring());
		}
		
		if(StringUtils.isNotBlank(updateDTO.getVIsvirement())){
			hql.append(" , t.VIsvirement = :VIsvirement ");
			params.put("VIsvirement", updateDTO.getVIsvirement());
		}
		
		if(StringUtils.isNotBlank(updateDTO.getOperator())){
			hql.append(" , t.operator = :operator ");
			params.put("operator", updateDTO.getOperator());
		}
		
		if(StringUtils.isNotBlank(updateDTO.getRemarks())){
			hql.append(" , t.remarks = :remarks ");
			params.put("remarks", updateDTO.getRemarks());
		}
		
		hql.append(" where t.VOid= :VOid ");
		params.put("VOid", updateDTO.getVOid());
		
		if(StringUtils.isNotBlank(updateDTO.getPayFlowId())){
			hql.append(" and t.payFlowId= :payFlowId ");
			params.put("payFlowId", updateDTO.getPayFlowId());
		}
		
		int count = this.dao.update(hql.toString(), params);
		
		return count;
	}
	
	@Transactional(readOnly = true)
	public PayFlowDTO getPayFlowById(String payFlowId){
		PayFlowDTO dto = null;
		if(StringUtils.isBlank(payFlowId)){
			return dto;
		}
		TdPayFlow po = this.dao.get(TdPayFlow.class, payFlowId);
		if(po != null){
			dto = po.po2dto();
		}
		return dto;
	}
	@Transactional(readOnly = true)
	public PayFlowDTO getPayFlowByVOid(String VOid){
		PayFlowDTO dto = null;
		if(StringUtils.isBlank(VOid)){
			return dto;
		}
		StringBuilder hql = new StringBuilder("from TdPayFlow t where t.VOid= :VOid ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("VOid", VOid);

		List<TdPayFlow> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayFlow po = list.get(0);
			dto = po.po2dto();
		}
		return dto;
	}
	
	@Transactional(readOnly = true)
	public PayFlowDTO getPayFlowLastForOrder(Long businessId,Long orderId){
		PayFlowDTO dto = null;
		if(businessId == null || orderId == null){
			return dto;
		}
		
		StringBuilder hql = new StringBuilder("from TdPayFlow t where t.businessId= :businessId and t.orderId = :orderId");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", businessId);
		params.put("orderId", orderId);
		
		hql.append(" and NOT EXISTS (select 1 from TdPayFlow t1 where t1.businessId= t.businessId and t1.orderId = t.orderId  and t1.createDate > t.createDate ) ");

		List<TdPayFlow> list = dao.query(hql.toString(), params);	
		if(list != null && list.size() > 0){
			TdPayFlow po = list.get(0);
			dto = po.po2dto();
		}
		
		return dto;
	}
	@Transactional
	public void createPayReconciliation(PayReconciliationDTO dto){
		if(dto == null){
			return ;
		}
		TdPayReconciliation po = new TdPayReconciliation();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setReconciliationId(po.getReconciliationId());
	}
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、更新支付订单流水信息；2、更新支付订单信息。
	 * @param updateDTO
	 */
	@Transactional
	public void updatePayStatusForPayEaseCallback(PayFlowDTO updateDTO){
		if(updateDTO == null || StringUtils.isBlank(updateDTO.getVOid()) || StringUtils.isBlank(updateDTO.getVPstatus()) 
				|| updateDTO.getVAmountActual() == null || StringUtils.isBlank(updateDTO.getVMoneytypeActual())){
			return ;
		}
		PayFlowDTO dto = this.getPayFlowByVOid(updateDTO.getVOid());
		updateDTO.setPayFlowId(dto.getPayFlowId());
		
		/**1、更新支付订单流水信息。*/
		Integer updateCount = this.updatePayFlowStatus(updateDTO);
		if(updateCount <= 0){
			throw new RuntimeException("updatePayStatusForPayEaseCallback:更新订单流水状态信息失败 !{void:"+updateDTO.getVOid()+",vpstatus:"+updateDTO.getVPstatus()+"}");
		}
		
		/**2、更新支付订单信息。*/
		PayOrderDTO currentPayOrderDTO = this.getPayOrderById(dto.getOrderId());
		PayOrderDTO payOrderDTO = new PayOrderDTO();
		payOrderDTO.setBusinessId(dto.getBusinessId());
		payOrderDTO.setOrderId(dto.getOrderId());
		
		if(currentPayOrderDTO != null && StringUtils.isNotBlank(currentPayOrderDTO.getStatus())){
			
			if(SellerPayConstant.Status_Success.equals(currentPayOrderDTO.getStatus())){
				//当现有订单状态已经为“支付成功”，则以第一次的状态变更为准，本次只更新订单流水状态。
				return ;
			}
		}
		
		if(SellerPayConstant.Status_Commit_PayEase.equals(updateDTO.getVPstatus().trim())){
			payOrderDTO.setStatus(SellerPayConstant.Status_Commit);
		}else{
			payOrderDTO.setStatus(updateDTO.getVPstatus());
		}
		payOrderDTO.setRemarks(updateDTO.getVPstring());
		
		Integer updatePayOrderCount = this.updatePayOrderStatus(payOrderDTO);
		if(updatePayOrderCount <= 0){
			throw new RuntimeException("updatePayStatusForPayEaseCallback:更新订单状态信息失败!{orderId:"+dto.getOrderId()+",void:"+updateDTO.getVOid()+",vpstatus:"+updateDTO.getVPstatus()+"}");
		}
		
		
	}
	/**
	 * 获取下一个支付流水序号。
	 * @return
	 */
	@Transactional(readOnly = true)
	public String getPayFlowVOidSeq(){
		String voidSeq = null;
		StringBuilder sql = new StringBuilder("select SEQ_TD_PAY_Flow_VOID.Nextval from dual");
		Query query =dao.createSQLQuery(sql.toString(),null);
		List list = query.list();
		if(list!=null && list.size()>=1){
			voidSeq = list.get(0).toString();
		}
		return voidSeq;
	}
	/**
	 * 保存订单支付流水信息，并且更新订单状态为：已提交。
	 * @param supplierid
	 * @param dto
	 */
	@Transactional
	public void savePayFlow(String supplierid,PayFlowDTO dto){
		if(dto == null || dto.getOrderId() == null){
			return ;
		}
		/**1、更新订单状态为已提交。*/
		PayOrderDTO payOrderDTO = new PayOrderDTO();
		payOrderDTO.setStatus(SellerPayConstant.Status_Commit);
		payOrderDTO.setOperator(supplierid);
		this.updatePayOrderStatusById(dto.getOrderId(), payOrderDTO);
		
		/**2、保存订单支付流水信息。*/
		TdPayFlow po = new TdPayFlow();
		po.dto2po(dto);
		
		this.dao.save(po);
		
		dto.setPayFlowId(po.getPayFlowId());
	}
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、保存对账信息；2、更新支付订单流水信息；3、更新支付订单信息。
	 * 
	 * @param updateDTO
	 */
	@Transactional
	public void updatePayStatusForReconciliation(PayReconciliationDTO updateDTO){
		if(updateDTO == null || StringUtils.isBlank(updateDTO.getVOid()) || StringUtils.isBlank(updateDTO.getVPstatus()) 
				|| updateDTO.getVAmountActual() == null || StringUtils.isBlank(updateDTO.getVMoneytypeActual())){
			return ;
		}
		
		/**1、保存对账信息。*/
		this.createPayReconciliation(updateDTO);
		
		/**2、更新支付订单流水信息。*/
		PayFlowDTO payFlowDTO = this.getPayFlowByVOid(updateDTO.getVOid());
		if(payFlowDTO == null || payFlowDTO.getOrderId() == null || payFlowDTO.getBusinessId() == null){
			//未知支付流水信息，直接返回。
			log.error("Unkown pay flow info !{VOid:"+updateDTO.getVOid()+",OrderId:"+payFlowDTO.getOrderId()+",BusinessId:"+payFlowDTO.getBusinessId()+"}");
			return ;
		}
		/**
		 * 2.1当支付流水已经为“支付成功”，但支付订单状态不为“支付成功”状态，则更新订单状态为“支付成功”，然后返回。
		 */
		if(SellerPayConstant.Status_Success.equals(payFlowDTO.getVPstatus())){
			PayOrderDTO currentPayOrderDTO = this.getPayOrderById(payFlowDTO.getOrderId());
			if(currentPayOrderDTO != null && StringUtils.isNotBlank(currentPayOrderDTO.getStatus())){
				if(!SellerPayConstant.Status_Success.equals(currentPayOrderDTO.getStatus())){
					
					currentPayOrderDTO.setStatus(SellerPayConstant.Status_Success);
					currentPayOrderDTO.setRemarks(payFlowDTO.getVPstring());
					currentPayOrderDTO.setOperator(payFlowDTO.getOperator());
					this.updatePayOrderStatus(currentPayOrderDTO);
					
					return ;
				}
			}
		}
		
		/**
		 * 2.2 对账操作，只是处理状态为：支付完成（1）、支付被拒绝（3）、持卡人拒付（7）的对账状态。
		 * 其中：
		 * 		1)、支付完成（1）为支付成功；
		 * 		2)、支付被拒绝（3）和持卡人拒付（7）为支付失败；
		 */
		if(SellerPayConstant.Status_Reconciliation_Success.equals(updateDTO.getVPstatus()) 
				|| SellerPayConstant.Status_Reconciliation_Failed.equals(updateDTO.getVPstatus()) 
				|| SellerPayConstant.Status_Reconciliation_Refuse.equals(updateDTO.getVPstatus())){
			
			if(SellerPayConstant.Status_Reconciliation_Success.equals(updateDTO.getVPstatus())){
				payFlowDTO.setVPstatus(SellerPayConstant.Status_Success);
			}else{
				payFlowDTO.setVPstatus(SellerPayConstant.Status_Failed);
			}
			payFlowDTO.setVAmountActual(updateDTO.getVAmountActual());
			payFlowDTO.setVMoneytypeActual(updateDTO.getVMoneytypeActual());
			payFlowDTO.setVPmodeDesc(updateDTO.getVPmodeDesc());
			payFlowDTO.setVPstring(updateDTO.getVPstring());
			payFlowDTO.setVIsvirement(updateDTO.getVIsvirement());
			payFlowDTO.setOperator(updateDTO.getOperator());
			payFlowDTO.setRemarks(updateDTO.getRemarks());
			
			this.updatePayFlowStatus(payFlowDTO);
			
			/**3、更新支付订单信息。*/
			PayOrderDTO currentPayOrderDTO = this.getPayOrderById(payFlowDTO.getOrderId());
			PayOrderDTO payOrderDTO = new PayOrderDTO();
			payOrderDTO.setBusinessId(payFlowDTO.getBusinessId());
			payOrderDTO.setOrderId(payFlowDTO.getOrderId());
			
			if(currentPayOrderDTO != null && StringUtils.isNotBlank(currentPayOrderDTO.getStatus())){
				if(SellerPayConstant.Status_Success.equals(currentPayOrderDTO.getStatus())){
					//当现有订单状态已经为“支付成功”，则以第一次的状态变更为准，本次只更新订单流水状态。
					return ;
				}
			}
			
			if(SellerPayConstant.Status_Commit_PayEase.equals(payFlowDTO.getVPstatus().trim())){
				payOrderDTO.setStatus(SellerPayConstant.Status_Commit);
			}else{
				payOrderDTO.setStatus(payFlowDTO.getVPstatus());
			}
			payOrderDTO.setRemarks(updateDTO.getVPstring());
			payOrderDTO.setOperator(payFlowDTO.getOperator());
			
			this.updatePayOrderStatus(payOrderDTO);
		}
	}
	/**
	 * 根据订单ID数组，查询订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumberArray
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PayOrderDTO> getPayOrderList(String supplierId,String businessNumber,String[] orderNumberArray){
		List<PayOrderDTO> result = new ArrayList<PayOrderDTO>();
		if(StringUtils.isBlank(supplierId) || StringUtils.isBlank(businessNumber) || orderNumberArray == null || orderNumberArray.length <= 0){
			return result;
		}
		StringBuilder hql = new StringBuilder("from TdPayOrder t where t.supplierId= :supplierId and t.businessNumber= :businessNumber and t.orderNumber in (:orderNumbers) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("supplierId", supplierId);
		params.put("businessNumber", businessNumber);
		params.put("orderNumbers", orderNumberArray);
		
		hql.append(" order by t.orderId asc ");

		List<TdPayOrder> list = dao.query(hql.toString(), params);
		if(list != null && list.size() > 0){
			for(TdPayOrder po : list){
				if(po != null){
					PayOrderDTO dto = po.po2dto();
					
					result.add(dto);
				}
			}
		}
		return result;
	}
	/**
	 * 根据订单ID数组，查询订单流水信息。
	 * @param orderIdArray
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PayFlowDTO> getPayFlowList(Long businessId,Long[] orderIdArray){
		List<PayFlowDTO> result = new ArrayList<PayFlowDTO>();
		if(businessId == null || orderIdArray == null || orderIdArray.length <= 0){
			return result;
		}
		StringBuilder hql = new StringBuilder("from TdPayFlow t where t.businessId= :businessId and t.orderId in (:orderIds) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("businessId", businessId);
		params.put("orderIds", orderIdArray);
		
		hql.append(" order by t.orderId asc ");

		List<TdPayFlow> list = dao.query(hql.toString(), params);
		if(list != null && list.size() > 0){
			for(TdPayFlow po : list){
				if(po != null){
					PayFlowDTO dto = po.po2dto();
					
					result.add(dto);
				}
			}
		}
		return result;
	}
	/**
	 * 根据订单ID数组，查询订单流水信息。
	 * @param orderIdArray
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<PayAccountDTO> getPayAccountListByNumbers(String[] accountNumberArray){
		List<PayAccountDTO> result = new ArrayList<PayAccountDTO>();
		if(accountNumberArray == null || accountNumberArray.length <= 0){
			return result;
		}
		StringBuilder hql = new StringBuilder("from TdPayAccount t where t.accountNumber in (:accountNumbers) ");
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("accountNumbers", accountNumberArray);
		
		hql.append(" order by t.accountId asc ");

		List<TdPayAccount> list = dao.query(hql.toString(), params);
		if(list != null && list.size() > 0){
			for(TdPayAccount po : list){
				if(po != null){
					PayAccountDTO dto = po.po2dto();
					
					result.add(dto);
				}
			}
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
	@Transactional(readOnly = true)
	public Page findPayFlowPage(PayFlowQueryDTO queryDTO,int pageNo, int pageSize) {
		if(queryDTO == null || queryDTO.getPayFlowDTO() == null){
			return null;
		}
		PayFlowDTO dto = queryDTO.getPayFlowDTO();
		HashMap<String, Object> params = new HashMap<String, Object>();

		StringBuffer hql = new StringBuffer("from TdPayFlow t where 1=1 ");
		
		if(queryDTO.getCreateDateStart() != null){
			hql.append(" and  t.createDate>= :createDateStart ");
			params.put("createDateStart", queryDTO.getCreateDateStart());
		}
		if(queryDTO.getCreateDateEnd() != null){
			hql.append(" and  t.createDate<= :createDateEnd ");
			params.put("createDateEnd", queryDTO.getCreateDateEnd());
		}
		if(StringUtils.isNotBlank(dto.getVPstatus())){
			hql.append(" and  t.VPstatus= :VPstatus ");
			params.put("VPstatus", dto.getVPstatus());
		}
		if (queryDTO.getVPstatusArray() != null && queryDTO.getVPstatusArray().length > 0) {
			hql.append(" and t.VPstatus in (:VPstatusArray) ");
			params.put("VPstatusArray", queryDTO.getVPstatusArray());
		}
		
		if(queryDTO.getOrderByColumn() != null && queryPayFlowOrderbySet.contains(queryDTO.getOrderByColumn())){
			if("payFlowId".equals(queryDTO.getOrderByColumn())){
				if("1".equals(queryDTO.getOrderByColumnAsc())){
					hql.append(" and t.payFlowId > :orderPayFlowId ");
					params.put("orderPayFlowId", queryDTO.getOrderPayFlowId());
				}else{
					hql.append(" and t.payFlowId < :orderPayFlowId ");
					params.put("orderPayFlowId", queryDTO.getOrderPayFlowId());
				}
			}
		}
		
		if(queryDTO.getOrderByColumn() != null && queryPayFlowOrderbySet.contains(queryDTO.getOrderByColumn())){
			if("VOid".equals(queryDTO.getOrderByColumn())){
				if("1".equals(queryDTO.getOrderByColumnAsc())){
					hql.append(" and t.VOid > :orderVOid ");
					params.put("orderVOid", queryDTO.getOrderVOid());
				}else{
					hql.append(" and t.VOid < :orderVOid ");
					params.put("orderVOid", queryDTO.getOrderVOid());
				}
			}
		}
		
		if(queryDTO.getOrderByColumn() != null && queryPayFlowOrderbySet.contains(queryDTO.getOrderByColumn())){
			hql.append(" order by  " + queryDTO.getOrderByColumn());
			if("1".equals(queryDTO.getOrderByColumnAsc())){
				hql.append(" asc  ");
			}else{
				hql.append(" desc ");
			}
		}else{
			hql.append(" order by t.updateDate desc  ");
		}
		
		Page result = dao.getPage(hql.toString(), pageNo, pageSize, params);

		return result;
	}
	
	/**
	 * 查询给定的支付流水的最大值。
	 * @param payFlowIdArray
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public String getMaxPayFlowIdForJob(String[] payFlowIdArray) {
		String maxPayFlowId = null;
		if(payFlowIdArray == null || payFlowIdArray.length <= 0){
			return maxPayFlowId;
		}
		StringBuffer hql = new StringBuffer("select greatest(  ");
		int i = 0;
		for(String payFlowId : payFlowIdArray){
			if(StringUtils.isNotBlank(payFlowId)){
				if(i > 0){
					hql.append(" , ");
				}
				hql.append("'");hql.append(payFlowId);hql.append("'");
				i++;
			}
		}
		hql.append(" )  from dual ");
		Query query = ((org.hibernate.SQLQuery)dao.createSQLQuery(hql.toString(), null));
		List<String> list = query.list();
		if(list != null && list.size() > 0){
			maxPayFlowId = list.get(0);
		}
		return maxPayFlowId;
	}
}
