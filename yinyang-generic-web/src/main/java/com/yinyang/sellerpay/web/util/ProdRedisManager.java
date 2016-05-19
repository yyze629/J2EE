package com.yinyang.sellerpay.web.util;

import java.io.IOException;

import com.dhgate.redis.DiamondRedisFactory;
import com.dhgate.redis.IRedis;

public class ProdRedisManager{
	public static final String REDIS_PRODUCT_DIAMOND_PROPERTIES="product_redis_config.properties";
	public static IRedis sr = null;
	static{
		sr = DiamondRedisFactory.getDiamonShardedRedis(REDIS_PRODUCT_DIAMOND_PROPERTIES);
	}

	public static String setString(String key, String str, int liveSeconds) {
		return sr.setString(key,str,liveSeconds,0,null);
	}
	public static String setString(String key, String str, int liveSeconds,int index) {
		return sr.setString(key,str,liveSeconds,index,null);
	}
	public static String setString(String key, String str, int liveSeconds,String poolKey) {
		return sr.setString(key,str,liveSeconds,0,poolKey);
	}
	/**
	 * 缓存string对象
	 * @param key key
	 * @param str  value
	 * @param liveSeconds  缓存时间，单位为秒
	 * @return redis返回的状态码
	 * @throws IOException 
	 */
	public static String setString(String key, String str, int liveSeconds,int index,String poolKey) {
		return sr.setString(key, str, liveSeconds,index,poolKey);
	}
	
	/**
	 * 从redis里取得string
	 * @param key key
	 * @return value 缓存内容
	 */
	public static String getString(String key) {
		return sr.getString(key,0,null);
	}
	public static String getString(String key,int index) {
		return sr.getString(key,index,null);
	}
	public static String getString(String key,String poolKey) {
		return sr.getString(key,0,poolKey);
	}
	public static String getString(String key,int index,String poolKey) {
		return sr.getString(key,index,poolKey);
	}
	
	public static long delString(String key) {
		return sr.delString(key,0,null);
	}
	public static long delString(String key,int index) {
		return sr.delString(key,index,null);
	}
	public static long delString(String key,String poolKey) {
		return sr.delString(key,0,poolKey);
	}
	
	public static boolean isExist(String key) {
		return sr.isExist(key,0,null);
	}
	public static boolean isExist(String key,int index) {
		return sr.isExist(key,index,null);
	}
	public static boolean isExist(String key,int index,String poolKey) {
		return sr.isExist(key,0,poolKey);
	}
	/**
	 * 删除string缓存对象（不支持key自动编码utf8）
	 * @param key key
	 * @return 1 if success , 0 fail或者对象不存在
	 */
	public static long delString(String key,int index,String poolKey) {
		return sr.delString(key,index,poolKey);
	}
	
	
	public static long delStringNew(String key) {
		return sr.delStringNew(key,0,null);
	}
	/**
	 * @Description: 简单对象删除方法（支持key自动编码utf8）
	 * @param key
	 * @return long 1 if success , 0 fail或者对象不存在
	 * @create time 2013-4-21 下午5:41:19
	 */
	public static long delStringNew(String key,String poolKey) {
		return sr.delStringNew(key,0,poolKey);
	}
	public static long delStringNew(String key,int index,String poolKey) {
		return sr.delStringNew(key,index,poolKey);
	}
	
	public static long delObject(String key) {
		return sr.delObject(key,0,null);
	}
	public static long delObject(String key,int index) {
		return sr.delObject(key,index,null);
	}
	/**
	 * 删除缓存object
	 * @param key key
	 * @return 1 if success , 0 fail或者对象不存在
	 */
	public static long delObject(String key,String poolKey) {
		return sr.delObject(key,0,poolKey);
	}
	public static long delObject(String key,int index,String poolKey) {
		return sr.delObject(key,index,poolKey);
	}
	
	public static Object getObject(String key) {
		return sr.getObject(key,0,null);
	}
	public static Object getObject(String key,int index) {
		return sr.getObject(key,0,null);
	}
	public static Object getObject(String key,String poolKey) {
		return sr.getObject(key,0,poolKey);
	}
	/**
	 * 从redis里取得对象
	 * @param key key
	 * @return value 缓存的对象
	 */
	public static Object getObject(String key,int index,String poolKey) {
		return sr.getObject(key,index,poolKey);
	}
	
	public static String setObject(String key, int seconds, Object obj) {
		return sr.setObject(key,seconds,obj,0,null);
	}
	public static String setObject(String key, int seconds, Object obj,int index) {
		return sr.setObject(key,seconds,obj,index,null);
	}
	public static String setObject(String key, int seconds, Object obj,String poolKey) {
		return sr.setObject(key, seconds, obj,0,poolKey);
	}
	public static String setObject(String key, int seconds, Object obj,int index,String poolKey) {
		return sr.setObject(key, seconds, obj,index,poolKey);
	}

	public static boolean lock(String key, int seconds) {
		return sr.lock(key,seconds,0,null);
	}
	public static boolean lock(String key, int seconds,int index) {
		return sr.lock(key,seconds,index,null);
	}
	/***
	 * 锁定某个key在指定的时间内，比较依赖于系统时间
	 * @author yangqin@dhgate.com
	 * @param key
	 * @param seconds
	 * @return true 表示锁定成功  false 表示锁定失败
	 */
	public static boolean lock(String key, int seconds,String poolKey) {
		return sr.lock(key, seconds,0,poolKey);
	}
	public static boolean lock(String key, int seconds,int index,String poolKey) {
		return sr.lock(key, seconds,index,poolKey);
	}
	public static long incr(String key){
		return sr.incr(key,0,null);
	}
	
	/**
	 * jedis incr方法 （String values internally as integers）
	 * @param String key
	 * @return long
	 */
	public static long incr(String key,String poolKey){
		return sr.incr(key,0,poolKey);
	}
	public static long incr(String key,int index){
		return sr.incr(key,index,null);
	}
	public static long incr(String key,int index,String poolKey){
		return sr.incrByStr(key,index,poolKey);
	}
	
	/**
	 * 指定某个时间点过期
	 * @param key key 
	 * @param unixTime 自1970年以来的毫秒数
	 * @return long 1成功    0 key不存在或者操作失败
	 */
	public static long expireAt(String key, long unixTime) {
		return sr.expireAt(key,unixTime,null);
	}
	
	/**
	 * 指定某个时间点过期
	 * @param key key 
	 * @param unixTime 自1970年以来的毫秒数
	 * @param poolKey poolKey pool名字，对应配置文件里配置多个redis池的情况
	 * @return long 1成功    0 key不存在或者操作失败
	 */
	public static long expireAt(String key, long unixTime, String poolKey) {
		return sr.expireAt(key,unixTime,poolKey); 
	}
	
	
	/**
	 * 标记某个key新的过期时间
	 * @param key key
	 * @param seconds  秒
	 * @param poolKey  pool名字，对应配置文件里配置多个redis池的情况
	 * @return  1: 成功 the timeout was set. 0: 如果key不存在 
	 */
	public static long expire(String key, int seconds, String poolKey) {
		return sr.expire(key,seconds,poolKey);
	}
	
	
	/**
	 * 标记某key新的过期时间
	 * @param key key
	 * @param seconds  秒
	 * @return  1: 成功 the timeout was set. 0: 如果key不存在 
	 */
	public static long expire(String key, int seconds) {
		return sr.expire(key,seconds);
	}
}
