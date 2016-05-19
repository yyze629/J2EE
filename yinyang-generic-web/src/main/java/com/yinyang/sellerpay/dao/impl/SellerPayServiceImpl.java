package com.yinyang.sellerpay.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhgate.apsaras.access.spring.annotation.ApsarasService;
import com.dhgate.apsaras.metadata.StackEnum;
import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Page;
import com.dhgate.framework.service.dao.GenericDao;
import com.dhgate.framework.service.service.impl.AbstractGenericServiceImpl;
import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.api.SellerPayService;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowQueryDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;
import com.yinyang.sellerpay.dao.impl.bean.SellerPayServiceBean;


/**
 * 卖家支付系统
 	<br>
	SellerPayServiceImpl---调用--->SellerPayServiceBean
    --->SellerPayDaoImpl           	   @Resource(name = "dhSellerPayDao")   public GenericDao dao;
	--->SellerPayDaoHibernateImpl      @Resource(name = "defaultSF")   private SessionFactory sf;
	</br>
 * @Title: SellerPayServiceImpl.java
 * @Package com.yinyang.sellerpay.impl
 * @author yinyang@dhgate.com
 * @date 2015年12月21日 下午5:50:31
 * @version V1.0
 */
//@ApsarasService(version = "1.0.0", stackEnum = StackEnum.NETTY)
@Service("SellerPayService")
public class SellerPayServiceImpl extends AbstractGenericServiceImpl implements SellerPayService {
	
	private static final Log log = LogFactory.getLogger(SellerPayServiceImpl.class);
	
	@Autowired
	SellerPayServiceBean sellerPayServiceBean;
	
	/**
	 * 增加支付账户。
	 * @param dto
	 * @return
	 */
	public ResultDto<Long> createPayAccount(PayAccountDTO dto){
		return this.sellerPayServiceBean.createPayAccount(dto);
	}
	/**
	 * 增加支付订单信息。
	 * @param dto
	 */
	public ResultDto<Long> createPayOrder(PayOrderDTO dto){
		return this.sellerPayServiceBean.createPayOrder(dto);
	}
	/**
	 * 增加支付订单流水信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<String> createPayFlow(PayFlowDTO dto){
		return this.sellerPayServiceBean.createPayFlow(dto);
	}
	/**
	 * 增加支付订单流水对账信息。
	 * @param dto
	 */
	public ResultDto<String> createPayReconciliation(PayReconciliationDTO dto){
		return this.sellerPayServiceBean.createPayReconciliation(dto);
	}
	/**
	 * 根据账户ID，取得支付账户信息。
	 * @param accountId
	 * @return
	 */
	public PayAccountDTO getPayAccountById(Long accountId){
		return this.sellerPayServiceBean.getPayAccountById(accountId);
	}
	/**
	 * 根据业务ID，查询业务信息。
	 * @param businessId
	 * @return
	 */
	public PayBusinessDTO getPayBusinessById(Long businessId){
		return this.sellerPayServiceBean.getPayBusinessById(businessId);
	}
	/**
	 * 根据订单ID查询订单信息。
	 * @param orderId
	 * @return
	 */
	public PayOrderDTO getPayOrderById(Long orderId){
		return this.sellerPayServiceBean.getPayOrderById(orderId);
	}
	/**
	 * 根据业务编号查询业务信息。
	 * @param businessNumber
	 * @return
	 */
	public PayBusinessDTO getPayBusinessByNumber(String businessNumber){
		return this.sellerPayServiceBean.getPayBusinessByNumber(businessNumber);
	}
	/**
	 * 根据渠道ID，取得渠道信息。
	 * @param channelId
	 * @return
	 */
	public PayChannelDTO getPayChannelById(String channelId){
		return this.sellerPayServiceBean.getPayChannelById(channelId);
	}
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderByBusiAndOrderNumber(String supplierId,String businessNumber,String orderNumber){
		return this.sellerPayServiceBean.getPayOrderByBusiAndOrderNumber(supplierId, businessNumber, orderNumber);
	}
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderInfo(String supplierId,String businessNumber,String orderNumber){
		return this.sellerPayServiceBean.getPayOrderInfo(supplierId, businessNumber, orderNumber);
	}
	/**
	 * 根据订单ID，更新订单状态信息。
	 * @param orderId
	 * @param dto
	 */
	public ResultDto<Integer> updatePayOrderStatusById(Long orderId,PayOrderDTO dto){
		return this.sellerPayServiceBean.updatePayOrderStatusById(orderId, dto);
	}
	/**
	 * 根据支付流水ID，获取支付流水信息。
	 * @param VOid
	 * @return
	 */
	public PayFlowDTO getPayFlowByVOid(String VOid){
		return this.sellerPayServiceBean.getPayFlowByVOid(VOid);
	}
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、更新支付订单流水信息；2、更新支付订单信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<Integer> updatePayStatusForPayEaseCallback(PayFlowDTO dto){
		return this.sellerPayServiceBean.updatePayStatusForPayEaseCallback(dto);
	}
	/**
	 * 验证支付订单信息的数字签名是否正确。
	 * 用于防止篡改。
	 * @param payBusinessDTO
	 * @param dto
	 * @return
	 */
	public Boolean validatePayOrderSign(PayBusinessDTO payBusinessDTO,PayOrderDTO payOrderDTO){
		return this.sellerPayServiceBean.validatePayOrderSign(payBusinessDTO, payOrderDTO);
	}
	
	/**
	 * 取得最近一条订单支付流水信息。
	 * @param businessId
	 * @param orderId
	 * @return
	 */
	public PayFlowDTO getPayFlowLastForOrder(Long businessId,Long orderId){
		return this.sellerPayServiceBean.getPayFlowLastForOrder(businessId, orderId);
	}
	/**
	 * 生成首信易支付流水订单号。
	 * @param businessId
	 * @return
	 */
	public String generatePayEaseVOid(Long businessId){
		return this.sellerPayServiceBean.generatePayEaseVOid(businessId);
	}
	/**
	 * 生成首信易支付流水数据校验信息。
	 * @param supplierId
	 * @param dto
	 * @return
	 */
	public String generatePayFlowMd5info(String supplierId,PayFlowDTO dto){
		return this.sellerPayServiceBean.generatePayFlowMd5info(supplierId, dto);
	}
	/**
	 * 保存订单支付流水信息，并且更新订单状态为：已提交。
	 * @param supplierid
	 * @param dto
	 */
	public ResultDto<String> savePayFlow(String supplierid,PayFlowDTO dto){
		return this.sellerPayServiceBean.savePayFlow(supplierid, dto);
	}
	/**
	 * 校验首信易返回信息是否正确。
	 * @param dto
	 * @return
	 */
	public boolean checkPayEaseReturnSign(PayFlowDTO dto){
		return this.sellerPayServiceBean.checkPayEaseReturnSign(dto);
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
		return this.sellerPayServiceBean.getPayOrderList(supplierId, businessNumber, orderNumberArray);
	}
	/**
	 * 分页查询订单流水信息。
	 * @param queryDTO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPayFlowPage(PayFlowQueryDTO queryDTO,int pageNo, int pageSize) {
		return this.sellerPayServiceBean.findPayFlowPage(queryDTO, pageNo, pageSize);
	}
	
}
