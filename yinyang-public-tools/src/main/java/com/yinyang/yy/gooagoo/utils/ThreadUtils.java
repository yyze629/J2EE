package com.yinyang.yy.gooagoo.utils;

import java.util.List;
import java.util.Vector;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程 工具类
 * 
 * @author Administrator
 *
 */
public class ThreadUtils
{
    public static ThreadUtils instance = new ThreadUtils();
    public static Vector<Future<List<?>>> vector = new Vector<Future<List<?>>>();
    public static ConcurrentMap<String, BlockingQueue<byte[]>> asynData = new ConcurrentHashMap<String, BlockingQueue<byte[]>>();
    public ExecutorService executor;

    public static ThreadUtils getInstance()
    {
        if (instance == null)
        {
            instance = new ThreadUtils();
        }
        return instance;
    }

    private ThreadUtils()
    {
        this.executor = Executors.newCachedThreadPool();
        Thread t = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    try
                    {
                        Thread.sleep(10000);
                        for (int i = 0; i < vector.size(); i++)
                        {
                            Future<List<?>> f = vector.get(i);
                            if (f.isDone())
                            {
                                List<?> list = f.get();
                                if (list != null && list.size() == 2)
                                {
                                    AssertUtils.isInstanceOf(String.class, list.get(0), "mac地址为String");
                                    AssertUtils.isInstanceOf(byte[].class, list.get(1), "数据为byte[]");
                                    getBlockingQueue((String) list.get(0)).add((byte[]) list.get(1));
                                    vector.remove(i);
                                }
                            }
                        }
                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
    }

    public void submit(Callable<List<?>> task)
    {
        vector.add(this.executor.submit(task));
    }

    public static BlockingQueue<byte[]> getBlockingQueue(String macAddr)
    {
        return asynData.get(macAddr);
    }
}
