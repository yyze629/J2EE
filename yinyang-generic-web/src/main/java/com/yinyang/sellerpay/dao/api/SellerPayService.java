package com.yinyang.sellerpay.dao.api;

import java.util.List;

import com.dhgate.common.metadata.ResultDto;
import com.dhgate.common.util.Page;
import com.yinyang.sellerpay.dao.api.dto.PayAccountDTO;
import com.yinyang.sellerpay.dao.api.dto.PayBusinessDTO;
import com.yinyang.sellerpay.dao.api.dto.PayChannelDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowDTO;
import com.yinyang.sellerpay.dao.api.dto.PayFlowQueryDTO;
import com.yinyang.sellerpay.dao.api.dto.PayOrderDTO;
import com.yinyang.sellerpay.dao.api.dto.PayReconciliationDTO;

/**
 * 卖家支付服务接口
 * 
 * @Title: SellerPayService.java
 * @Package com.dhgate.sellerpay.api
 * @author yinyang@dhgate.com
 * @date 2015年12月21日 下午3:55:41
 * @version V1.0
 */
public interface SellerPayService {
	
	/**
	 * 增加支付账户。
	 * @param dto
	 * @return
	 */
	public ResultDto<Long> createPayAccount(PayAccountDTO dto);
	/**
	 * 增加支付订单信息。
	 * @param dto
	 */
	public ResultDto<Long> createPayOrder(PayOrderDTO dto);
	/**
	 * 增加支付订单流水信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<String> createPayFlow(PayFlowDTO dto);
	/**
	 * 增加支付订单流水对账信息。
	 * @param dto
	 */
	public ResultDto<String> createPayReconciliation(PayReconciliationDTO dto);
	/**
	 * 根据账户ID，取得支付账户信息。
	 * @param accountId
	 * @return
	 */
	public PayAccountDTO getPayAccountById(Long accountId);
	/**
	 * 根据业务ID，查询业务信息。
	 * @param businessId
	 * @return
	 */
	public PayBusinessDTO getPayBusinessById(Long businessId);
	/**
	 * 根据订单ID查询订单信息。
	 * @param orderId
	 * @return
	 */
	public PayOrderDTO getPayOrderById(Long orderId);
	/**
	 * 根据业务编号查询业务信息。
	 * @param businessNumber
	 * @return
	 */
	public PayBusinessDTO getPayBusinessByNumber(String businessNumber);
	/**
	 * 根据渠道ID，取得渠道信息。
	 * @param channelId
	 * @return
	 */
	public PayChannelDTO getPayChannelById(String channelId);
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderByBusiAndOrderNumber(String supplierId,String businessNumber,String orderNumber);
	/**
	 * 根据卖家ID，业务编号，订单ID获取订单信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumber
	 * @return
	 */
	public PayOrderDTO getPayOrderInfo(String supplierId,String businessNumber,String orderNumber);
	/**
	 * 根据订单ID，更新订单状态信息。
	 * @param orderId
	 * @param dto
	 */
	public ResultDto<Integer> updatePayOrderStatusById(Long orderId,PayOrderDTO dto);
	/**
	 * 根据支付流水ID，获取支付流水信息。
	 * @param VOid
	 * @return
	 */
	public PayFlowDTO getPayFlowByVOid(String VOid);
	/**
	 * 根据首信易回调信息，更新支付状态信息。
	 * 1、更新支付订单流水信息；2、更新支付订单信息。
	 * @param dto
	 * @return
	 */
	public ResultDto<Integer> updatePayStatusForPayEaseCallback(PayFlowDTO dto);
	/**
	 * 验证支付订单信息的数字签名是否正确。
	 * 用于防止篡改。
	 * @param payBusinessDTO
	 * @param dto
	 * @return
	 */
	public Boolean validatePayOrderSign(PayBusinessDTO payBusinessDTO,PayOrderDTO payOrderDTO);
	/**
	 * 取得最近一条订单支付流水信息。
	 * @param businessId
	 * @param orderId
	 * @return
	 */
	public PayFlowDTO getPayFlowLastForOrder(Long businessId,Long orderId);
	/**
	 * 生成首信易支付流水订单号。
	 * @param businessId
	 * @return
	 */
	public String generatePayEaseVOid(Long businessId);
	/**
	 * 生成首信易支付流水数据校验信息。
	 * @param supplierId
	 * @param dto
	 * @return
	 */
	public String generatePayFlowMd5info(String supplierId,PayFlowDTO dto);
	/**
	 * 保存订单支付流水信息，并且更新订单状态为：已提交。
	 * @param supplierid
	 * @param dto
	 */
	public ResultDto<String> savePayFlow(String supplierid,PayFlowDTO dto);
	/**
	 * 校验首信易返回信息是否正确。
	 * @param dto
	 * @return
	 */
	public boolean checkPayEaseReturnSign(PayFlowDTO dto);
	/**
	 * 根据订单ID数组，查询订单信息。
	 * 同时包含支付流水信息。
	 * @param supplierId
	 * @param businessNumber
	 * @param orderNumberArray
	 * @return
	 */
	public List<PayOrderDTO> getPayOrderList(String supplierId,String businessNumber,String[] orderNumberArray);
	/**
	 * 分页查询订单流水信息。
	 * @param queryDTO
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Page findPayFlowPage(PayFlowQueryDTO queryDTO,int pageNo, int pageSize);

	
}
