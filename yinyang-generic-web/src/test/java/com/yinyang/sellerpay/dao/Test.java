package com.yinyang.sellerpay.dao;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Test {
	
	private static BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);
	private static ThreadPoolExecutor executor = new ThreadPoolExecutor(2,6,0L,TimeUnit.DAYS,queue);
	
	public static void main(String[] args) throws InterruptedException {
		//BlockingQueue<Runnable> queue = new ArrayBlockingQueue<Runnable>(4);
		//ThreadPoolExecutor executor = new ThreadPoolExecutor(2,6,0L,TimeUnit.DAYS,queue);
		for(int i=0;i<10;i++){
			executor.execute(new Thread(new ThreadPoolTest(),"TestThread".concat("---"+i)));
			int threadSize = queue.size();
			System.out.println("线程队列大小："+threadSize);
			if(threadSize==4){
				queue.put(new Runnable() {
					@Override
					public void run() {
						System.out.println("我是新线程，看看能不能搭个车加进去！");
					}
				});
			}
		}
		executor.shutdown();
	}
}
