package com.yinyang.yy;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import net.sf.json.JSONArray;
import org.apache.commons.lang.StringUtils;
import com.yinyang.yy.utils.PropertiesUtils;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

/**
 * 
 * 
 * @Title: JedisManager.java
 * @Package com.yinyang.yy
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:53:40
 * @version V1.0
 */
@SuppressWarnings({"deprecation"})
public class JedisManager {
	private static final String defaultEncoding = "UTF-8";
	public static final String REDIS_POOL = "redis.pool";
	private static ShardedJedisPool pool;
	static {
		JedisPoolConfig config = new JedisPoolConfig();
		//config.setMaxActive(100);
		config.setMaxIdle(20);
		//config.setMaxWait(1000);
		config.setTestOnBorrow(true);
		/** redis.pool=192.168.1.172,192.168.1.173 */
		String[] strArray = PropertiesUtils.getStringArray(REDIS_POOL);
		List<JedisShardInfo> shardJedis = new ArrayList<JedisShardInfo>();
		for (int i = 0; i < strArray.length; i++) {
			shardJedis.add(new JedisShardInfo(strArray[i]));
		}
		pool = new ShardedJedisPool(config, shardJedis);
	}

	public static void removeKey(String key) {
		ShardedJedis jedis = pool.getResource();
		if (StringUtils.isNotEmpty(key) && jedis.exists(key)) {
			jedis.expire(key, 0);
		}
	}

	public static String getKey(String key, boolean isString) {
		ShardedJedis jedis = pool.getResource();
		if (StringUtils.isEmpty(key) || !jedis.exists(key)) {
			return null;
		} else {
			if (isString) {
				return getString(key);
			} else {
				return JSONArray.fromObject(getObject(key)).toString();
			}
		}
	}

	public static String setString(String key, String str, int liveSeconds) {
		if (key == null || str == null)
			return null;
		ShardedJedis jedis = pool.getResource();
		String ret = null;
		try {
			ret = jedis.setex(encode(key), liveSeconds, str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return ret;
	}

	public static String getString(String key) {
		ShardedJedis jedis = pool.getResource();
		String ret = null;
		try {
			ret = jedis.get(encode(key));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return ret;
	}

	private static String encode(String str) {
		String ret = null;
		if (StringUtils.isNotEmpty(str)) {
			try {
				ret = URLEncoder.encode(str, defaultEncoding);
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return ret;
	}

	private static byte[] getBytes(byte[] key) {
		if (key == null)
			return null;
		ShardedJedis jedis = pool.getResource();
		byte[] ret = null;
		try {
			ret = jedis.get(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return ret;
	}

	private static String setBytes(byte[] key, int seconds, byte[] bytes) {
		if (key == null || bytes == null)
			return null;
		ShardedJedis jedis = pool.getResource();
		String ret = null;
		try {
			if (seconds <= 0) {
				ret = jedis.set(key, bytes);
			} else {
				ret = jedis.setex(key, seconds, bytes);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.returnResource(jedis);
		}
		return ret;
	}

	public static Object getObject(String key) {
		if (key == null)
			return null;
		byte[] ret = getBytes(key.getBytes());
		if (ret == null)
			return null;
		return ObjectBytesExchange.toObject(ret);
	}

	public static String setObject(String key, int seconds, Object obj) {
		if (key == null || obj == null)
			return null;
		byte[] byteObj = ObjectBytesExchange.toByteArray(obj);
		if (null == byteObj)
			return null;
		return setBytes(key.getBytes(), seconds, byteObj);
	}

}
