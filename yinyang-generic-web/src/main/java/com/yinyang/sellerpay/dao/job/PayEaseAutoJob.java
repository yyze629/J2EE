package com.yinyang.sellerpay.dao.job;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dhgate.logging.Log;
import com.dhgate.logging.LogFactory;
import com.yinyang.sellerpay.dao.impl.bean.SellerPayServiceBean;
import com.yinyang.sellerpay.dao.util.DHDiamondManager;
import com.yinyang.sellerpay.dao.util.SellerPayConstant;
import com.yinyang.sellerpay.dao.util.SellerPayUtil;

@Service("payEaseAutoJob")
public class PayEaseAutoJob {
	
	private static final Log log = LogFactory.getLogger(PayEaseAutoJob.class);
	
	private static int CORE_POOL_SIZE = 3; //线程容器核心线程

	private static int MAX_POOL_SIZE = 8; //线程容器最大线程数

	private static long KEEP_ALIVE_TIME = 0;
	
	private static final int WORK_QUEUE_SIZE = 200; //任务队列大小
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
			new ArrayBlockingQueue(WORK_QUEUE_SIZE), new ThreadPoolExecutor.AbortPolicy());
	
	private int consumerThreadSize;
	private String autoJobIP;
	private int queueMaxSize;
	
	@Autowired
	SellerPayServiceBean sellerPayServiceBean;
	
	public void execute() {
		boolean loadDiamond = this.loadDiamondConfig();
		if(loadDiamond){
			String localIp = SellerPayUtil.getLocalIP();
			log.info("jobIp:" + this.autoJobIP + "--localIp:" + localIp+"{当前线程数量："+threadPool.getActiveCount()+",已完成线程数："+threadPool.getCompletedTaskCount()+",最大线程数："+threadPool.getLargestPoolSize()+"}");
			if (StringUtils.isBlank(this.autoJobIP) || StringUtils.isBlank(localIp)) {
				return;
			}
			if (!localIp.equals(this.autoJobIP)) {
				return;
			}
			
			/*log.info("ThreadId{"+Thread.currentThread().getId()+"}开始创建生产者线程 .....");
			PayEaseTaskQueue payEaseTaskQueue = new PayEaseTaskQueue(this.queueMaxSize);
			PayEaseProducerTask payEaseProducerTask = new PayEaseProducerTask(sellerPayServiceBean,payEaseTaskQueue,this.consumerThreadSize);
			payEaseProducerTask.setJobThreadId(Thread.currentThread().getId());
			threadPool.execute(payEaseProducerTask);
			log.info("ThreadId{"+Thread.currentThread().getId()+"}生产者线程创建 完成  !");
			log.info("ThreadId{"+Thread.currentThread().getId()+"}开始创建消费者线程 .....");
			for(int i=0;i<this.consumerThreadSize;i++){
				log.info("ThreadId{"+Thread.currentThread().getId()+"}开始创建第-"+(i+1)+" 个消费者线程 .....");
				PayEaseConsumerTask payEaseConsumerTask = new PayEaseConsumerTask(sellerPayServiceBean,payEaseTaskQueue);
				payEaseConsumerTask.setJobThreadId(Thread.currentThread().getId());
				threadPool.execute(payEaseConsumerTask);
				log.info("ThreadId{"+Thread.currentThread().getId()+"}创建第-"+(i+1)+" 个消费者线程完成  !");
			}*/
			log.info("ThreadId{"+Thread.currentThread().getId()+"}消费者线程创建 完成  !");
		}else{
			log.error("ThreadId{"+Thread.currentThread().getId()+"}首信易对账任务读取戴梦得配置失败，结束线程 !");
		}
	}
	
	public boolean loadDiamondConfig(){
		boolean result = false;
		try {
			String diamondGroup = SellerPayConstant.SellerPayDiamond_Group;
			String diamondDataId = SellerPayConstant.SellerPayDiamond_DataId;
			String consumerThreadSizeKey = SellerPayConstant.PayEaseJobQueue_ConsumerThreadSize_Max_Key;
			String autoJobIPKey = SellerPayConstant.PayEaseAutoJobIP_key;
			String queueMaxSizeKey = SellerPayConstant.PayEaseJobQueue_MaxSize_Key;
			
			String consumerThreadSizeValue = DHDiamondManager.getDiamondPropertiesValue(diamondGroup, diamondDataId, consumerThreadSizeKey);
			this.consumerThreadSize = Integer.parseInt(consumerThreadSizeValue);
			
			String autoJobIPValue = DHDiamondManager.getDiamondPropertiesValue(diamondGroup, diamondDataId, autoJobIPKey);
			this.autoJobIP = autoJobIPValue;
			
			String queueMaxSizeValue = DHDiamondManager.getDiamondPropertiesValue(diamondGroup, diamondDataId, queueMaxSizeKey);
			this.queueMaxSize = Integer.parseInt(queueMaxSizeValue);
			
			result = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error(e);
			log.info("首信易对账任务读取戴梦得配置失败！");
		}
		
		return result;
	}

}
