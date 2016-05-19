package com.yinyang.yy.utils;

/**
 * 数组排序工具类
 * 
 * 测试结果
 *------------------------------------------
 *测试数据10000
 *冒泡排序：120ms
 *选择排序：32ms
 *插入排序：20ms
 *快速排序：7ms
 *------------------------------------------
 *测试数据100000
 *冒泡排序：13098ms
 *选择排序：2334ms
 *插入排序：1264ms
 *快速排序：23ms
 *引用于-http://blog.csdn.net/zhouj634620500/article/details/8056875
 * @author YY
 * 
 * @Title: ArraySortUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午1:18:55
 * @version V1.0
 */
public class ArraySortUtil
{
    /**
     * 互换
     * @param source
     * @param from
     * @param to
     */
    public static void swap(int[] source, int from, int to)
    {
        int temp = source[from];
        source[from] = source[to];
        source[to] = temp;
    }

    /**            
     * 冒泡排序法--升序
     * 时间复杂度为O（n^2），空间复杂度为O(1)，稳定
     * @param int[] source
     * @param int from
     * @param int len
     * @return int[] source对象
     */
    public static int[] bubbleSort_Up(int[] source, int from, int len)
    {
        for (int i = from + len - 1; i >= from; i--)
        {
            for (int j = from; j < i; j++)
            {
                if (source[j] > source[j + 1])
                {
                    swap(source, j, j + 1);
                }
            }
        }
        return source;
    }

    /**
     * 冒泡排序法--降序
     * 时间复杂度为O（n^2），空间复杂度为O(1)，稳定
     * @param int[] source
     * @param int from
     * @param int len
     * @return int[] source对象
     */
    public static int[] bubbleSort_Down(int[] source, int from, int len)
    {
        for (int i = from; i < from + len; i++)
        {
            for (int j = from + len - 1; j > i; j--)
            {
                if (source[j] < source[j - 1])
                {
                    swap(source, j - 1, j);
                }
            }
        }
        return source;
    }

    /**
     * 选择排序法
     * 时间复杂度为O(n^2) 空间复杂度为O(1)，不稳定
     * @param int[] source
     * @param int from
     * @param int len
     * @return int[] source对象
     */
    public static int[] selectionSort(int[] source, int from, int len)
    {
        for (int i = 0; i < len; i++)
        {
            int smallest = i;
            for (int j = from + i; j < from + len; j++)
            {
                if (source[j] < source[smallest])
                {
                    smallest = j;
                }
            }
            swap(source, i, smallest);
        }
        return source;
    }

    /**
     * 插入排序法  
     * 时间复杂度为O(n^2) 空间复杂度为O(1)，稳定
     * @param int[] source
     * @param int from
     * @param int len
     * @return int[] source对象
     */
    public static int[] insertionSort(int[] source, int from, int len)
    {
        int temp;
        for (int i = from + 1; i < from + len; i++)
        {
            temp = source[i];
            int j = i;
            for (; j > from; j--)
            {
                if (temp < source[j - 1])
                {
                    source[j] = source[j - 1];
                }
                else
                {
                    //如当前数不小前面数，那说明不小于前面所有数，因前面已经是排好序的，所以直接出当前一轮的比较
                    break;
                }
            }
            source[j] = temp;
        }
        return source;
    }

    /**
     * 快速排序法
     * 时间复杂度为O（nlogn，底数为2） 空间复杂度为O（logn，底数为2），不稳定
     * @param int[] source
     * @param int from
     * @param int to
     * @return int[] source对象
     */
    public static int[] quickSort(int[] source, int from, int to)
    {
        if (from < to - 1)
        {
            int pivot = partion(source, from, to);
            quickSort(source, from, pivot - 1);
            quickSort(source, pivot + 1, to);
        }
        return source;
    }

    private static int partion(int[] source, int from, int to)
    {//----------还有点问题
        int pivot = source[from];
        while (from < to)
        {
            while (from < to && source[to] >= pivot)
            {
                to--;
            }
            source[from] = source[to];

            while (from < to && source[from] <= pivot)
            {
                from++;
            }
            source[to] = source[from];
        }
        source[from] = pivot;
        return from;
    }

    /*private static int partion(int[] source, int from, int to)
    {
        int temp;
        int pivot = source[from];
        while (from < to)
        {
            while (from < to && source[to] > pivot)
            {
                to--;
            }
            temp = source[to];
            source[to] = source[from];
            source[from] = temp;
            
            while (from < to && source[from] < pivot)
            {
                from++;
            }
            temp = source[to];
            source[to] = source[from];
            source[from] = temp;
        }
        source[from] = pivot;
        return from;
    }*/

}
