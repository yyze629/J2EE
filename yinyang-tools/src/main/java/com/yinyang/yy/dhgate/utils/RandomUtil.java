package com.yinyang.yy.dhgate.utils;

import java.io.PrintStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class RandomUtil
{
  public static final String USER_QUESTION_1 = "USER_QUESTION_1";
  public static final String USER_QUESTION_2 = "USER_QUESTION_2";
  public static final String OPTION_TYPE_1 = "1";
  public static final int OPTION_TYPE_1_COUNT = 19;
  public static final int OPTION_TYPE_1_SELECT = 12;
  public static final String OPTION_TYPE_2 = "2";
  public static final int OPTION_TYPE_2_COUNT = 21;
  public static final int OPTION_TYPE_2_SELECT = 13;
  public static final String SEQUENCETYPE_PRODUCTITEMCODE = "productitemcode";

  public static String[] RandomArr(int count, int select)
  {
    Map s = new HashMap();
    String[] temp = new String[select];
    int j = 0;
    while (s.size() != select)
    {
      Random r = new Random();
      int b = r.nextInt(count);

      String str = String.valueOf(b + 1);
      if (!(str.equals(s.get(str)))) {
        s.put(str, str);
        temp[j] = str;

        ++j;
      }
    }

    return temp;
  }

  public static String[] text1(int count, int select) {
    Map s = new HashMap();
    String[] temp = new String[select];
    int j = 0;
    while (s.size() != select)
    {
      Random r = new Random();
      int b = r.nextInt(count);

      String str = String.valueOf(b + 1);
      if (!(str.equals(s.get(str)))) {
        s.put(str, str);
        temp[j] = str;
        System.out.print("   " + s.get(str));
        ++j;
      }

    }

    return temp;
  }

  public static void text2()
  {
    Map s = new HashMap();
    for (int i = 0; i < 25; ++i) {
      Random r = new Random();
      int b = r.nextInt(40);
      s.put(b, b);
    }
    Set ss = s.keySet();
    Iterator it = ss.iterator();
    while (it.hasNext())
      System.out.println(it.next());
  }

  public static int isSelectResult(String[] formValueArr, String optionCode, long correctness)
  {
    for (int i = 0; i < formValueArr.length; ++i)
      if (formValueArr[i].equals(optionCode)) {
        if (correctness == 0L)
          return 0;

        if (correctness == 1L)
          return 1;

      }


    if (correctness == 0L)
      return 1;

    if (correctness == 1L)
      return 0;

    return 2;
  }

  public static void main(String[] args)
  {
    String[] tempArr = { "A", "B" };
    String tempStr = "C";
    long correctness = 0L;
    System.out.println(isSelectResult(tempArr, tempStr, correctness));
  }
}