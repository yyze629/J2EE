package com.yinyang.yy.gooagoo.utils;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

/**
 * cache 工具类
 * @author jmb
 *
 */
public class EHCacheUtil
{

    private static CacheManager cacheManager = CacheManager.getInstance();

    /**
     * 获取cache
     * @param cacheName cache库名
     * @return cacheName库名正确,cache;cacheName库名不正确, null
     */
    public static Cache createCache(String cacheName)
    {
        if (cacheName == null || cacheName.trim().length() == 0)
        {
            return null;
        }
        Cache cache = cacheManager.getCache(cacheName);
        if (cache == null)
        {
            cacheManager.addCache(cacheName);
            return cacheManager.getCache(cacheName);
        }
        return cache;
    }

    /**
     * 删除缓存数据
     * @param cacheName cache库名
     * @param key 数据key,不能为空
     */
    public static void remove(Cache cache, String key)
    {
        if (cache == null)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        cache.remove(key);
    }

    /**
     * 存入缓存数据
     * @param cacheName cache库名
     * @param key 数据key,不能为空
     * @param value 数据value
     */
    public static void put(String cacheName, String key, Object value)
    {
        if (cacheName == null || cacheName.trim().length() == 0)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        Element element = new Element(key, value);
        createCache(cacheName).put(element);
    }

    /**
     * 存入缓存数据
     * @param cache cache库
     * @param key 数据key,不能为空
     * @param value 数据value
     */
    public static void put(Cache cache, String key, Object value)
    {
        if (cache == null)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        Element element = new Element(key, value);
        cache.put(element);
    }

    /**
     * 获取数据
     * @param cacheName cache库名
     * @param key 数据key,不能为空
     * @return 如没有数据，则为null;否则则为value
     */
    public static Object get(String cacheName, String key)
    {
        if (cacheName == null || cacheName.trim().length() == 0)
        {
            return null;
        }
        if (key == null || key.trim().length() == 0)
        {
            return null;
        }
        Element element = createCache(cacheName).get(key);
        if (element == null)
        {
            return null;
        }
        return element.getObjectValue();
    }

    /**
     * 获取数据
     * @param cache cache库
     * @param key 数据key,不能为空
     * @return 如没有数据，则为null;否则则为value
     */
    public static Object get(Cache cache, String key)
    {
        if (cache == null)
        {
            return null;
        }
        if (key == null || key.trim().length() == 0)
        {
            return null;
        }
        Element element = cache.get(key);
        if (element == null)
        {
            return null;
        }
        return element.getObjectValue();
    }

    /**
     * 设置缓存数据过期
     * @param cacheName cache库名
     * @param key 数据key,不能为空
     * @param expireTime 过期时间，单位秒，不能<0,如为0，则为最大时间
     */
    public static void expire(String cacheName, String key, int expireTime)
    {
        if (cacheName == null || cacheName.trim().length() == 0)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        if (expireTime < 0)
        {
            return;
        }
        Element element = createCache(cacheName).get(key);
        if (element == null)
        {
            return;
        }
        element.setTimeToLive(expireTime);
    }

    /**
     * 设置缓存数据过期
     * @param cache cache库
     * @param key 数据key,不能为空
     * @param expireTime 过期时间，单位秒，不能<0,如为0，则为最大时间
     */
    public static void expire(Cache cache, String key, int expireTime)
    {
        if (cache == null)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        if (expireTime < 0)
        {
            return;
        }
        Element element = cache.get(key);
        if (element == null)
        {
            return;
        }
        element.setTimeToLive(expireTime);
    }

    /**
     * 设置缓存数据永不过期
     * @param cache cache库
     * @param key 数据key,不能为空
     * @param flage ture=永不失效，false=使用默认值 
     */
    public static void eternal(Cache cache, String key, boolean flage)
    {
        if (cache == null)
        {
            return;
        }
        if (key == null || key.trim().length() == 0)
        {
            return;
        }
        Element element = cache.get(key);
        if (element == null)
        {
            return;
        }
        element.setEternal(flage);
    }

    /**
     * EHCache释放资源
     */
    public static void dispose(String cacheName)
    {
        cacheManager.removeCache(cacheName);
        if (cacheManager.getCacheNames().length == 0)
        {
            cacheManager.shutdown();
        }
    }

}
