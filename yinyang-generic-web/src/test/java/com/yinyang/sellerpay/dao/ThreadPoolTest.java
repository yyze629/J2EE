package com.yinyang.sellerpay.dao;

public class ThreadPoolTest implements Runnable{
	@Override
	public void run() {
		// TODO Auto-generated method stub
		synchronized(this){
			try{
				System.out.println("线程名称，name:"+Thread.currentThread().getName());
				Thread.sleep(3000);
			}catch(Exception e){
				System.out.println(e);
			}
		}
	}//main
}
