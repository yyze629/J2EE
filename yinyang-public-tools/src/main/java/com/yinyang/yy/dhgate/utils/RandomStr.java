package com.yinyang.yy.dhgate.utils;

import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.StringTokenizer;

public class RandomStr
{
  private Integer length = new Integer(8);
  private String randomstr;
  private boolean allchars = false;
  private HashMap hmap;
  private ArrayList lower = null;
  private ArrayList upper = null;
  private char[] single = null;
  private int singlecount = 0;
  private boolean singles = false;
  private String algorithm = null;
  private String provider = null;
  private boolean secure = false;
  private Random random = null;
  private SecureRandom secrandom = null;

  public void setLength(int count)
  {
    this.length = new Integer(count);
  }

  private final float getFloat()
  {
    if (this.random == null)
      return this.secrandom.nextFloat();

    return this.random.nextFloat();
  }

  public final void generateRandomObject()
    throws Exception
  {
    if (this.secure)
      try
      {
        if (this.provider != null)
        {
          this.random = SecureRandom.getInstance(this.algorithm, this.provider); return;
        }
        this.random = SecureRandom.getInstance(this.algorithm);
      } catch (NoSuchAlgorithmException ne) {
        throw new Exception(ne.getMessage());
      } catch (NoSuchProviderException pe) {
        throw new Exception(pe.getMessage());
      }

    this.random = new Random();
  }

  private final void generaterandom()
  {
    int i;
    if (this.allchars)
      for (i = 0; i < this.length.intValue(); ++i) {
        RandomStr tmp13_12 = this; tmp13_12.randomstr = tmp13_12.randomstr + 
          new Character(
          (char)(34 + (int)(getFloat() * 93.0F))).toString
          (); }
    else if (this.singles)
    {
      if (this.upper.size() == 3)
      {
        for (i = 0; i < this.length.intValue(); ++i)
        {
          if ((int)(getFloat() * 100.0F) % 2 == 0)
          {
            if ((int)(getFloat() * 100.0F) % 2 == 0)
            {
              this.randomstr += randomSingle().toString();
            }
            else {
              RandomStr tmp161_160 = this; tmp161_160.randomstr = tmp161_160.randomstr + 
                randomChar((Character)this.lower.get(2), 
                (Character)this.upper.get(2)).toString
                ();
            }

          }
          else if ((int)(getFloat() * 100.0F) % 2 == 0)
          {
            RandomStr tmp231_230 = this; tmp231_230.randomstr = tmp231_230.randomstr + 
              randomChar((Character)this.lower.get(1), 
              (Character)this.upper.get(1)).toString
              ();
          }
          else {
            RandomStr tmp288_287 = this; tmp288_287.randomstr = tmp288_287.randomstr + 
              randomChar((Character)this.lower.get(0), 
              (Character)this.upper.get(0)).toString
              (); }
        }
      }
      else if (this.upper.size() == 2)
      {
        for (i = 0; i < this.length.intValue(); ++i)
        {
          if ((int)(getFloat() * 100.0F) % 2 == 0)
          {
            this.randomstr += randomSingle().toString();
          } else if ((int)(getFloat() * 100.0F) % 2 == 0)
          {
            RandomStr tmp436_435 = this; tmp436_435.randomstr = tmp436_435.randomstr + 
              randomChar((Character)this.lower.get(1), 
              (Character)this.upper.get(1)).toString
              ();
          }
          else
          {
            RandomStr tmp493_492 = this; tmp493_492.randomstr = tmp493_492.randomstr + 
              randomChar((Character)this.lower.get(0), 
              (Character)this.upper.get(0)).toString
              (); }
        }
      }
      else if (this.upper.size() == 1)
      {
        for (i = 0; i < this.length.intValue(); ++i)
          if ((int)getFloat() * 100 % 2 == 0)
          {
            this.randomstr += randomSingle().toString();
          }
          else {
            RandomStr tmp628_627 = this; tmp628_627.randomstr = tmp628_627.randomstr + 
              randomChar((Character)this.lower.get(0), 
              (Character)this.upper.get(0)).toString
              ();
          }
      }
      else {
        for (i = 0; i < this.length.intValue(); ++i)
          this.randomstr += randomSingle().toString();

      }

    }
    else if (this.upper.size() == 3)
    {
      for (i = 0; i < this.length.intValue(); ++i)
      {
        if ((int)(getFloat() * 100.0F) % 2 == 0)
        {
          RandomStr tmp782_781 = this; tmp782_781.randomstr = tmp782_781.randomstr + 
            randomChar((Character)this.lower.get(2), 
            (Character)this.upper.get(2)).toString
            ();
        } else if ((int)(getFloat() * 100.0F) % 2 == 0)
        {
          RandomStr tmp852_851 = this; tmp852_851.randomstr = tmp852_851.randomstr + 
            randomChar((Character)this.lower.get(1), 
            (Character)this.upper.get(1)).toString
            ();
        }
        else
        {
          RandomStr tmp909_908 = this; tmp909_908.randomstr = tmp909_908.randomstr + 
            randomChar((Character)this.lower.get(0), 
            (Character)this.upper.get(0)).toString
            (); }
      }
    }
    else if (this.upper.size() == 2)
    {
      for (i = 0; i < this.length.intValue(); ++i)
        if ((int)(getFloat() * 100.0F) % 2 == 0)
        {
          RandomStr tmp1009_1008 = this; tmp1009_1008.randomstr = tmp1009_1008.randomstr + 
            randomChar((Character)this.lower.get(1), 
            (Character)this.upper.get(1)).toString
            ();
        }
        else {
          RandomStr tmp1066_1065 = this; tmp1066_1065.randomstr = tmp1066_1065.randomstr + 
            randomChar((Character)this.lower.get(0), 
            (Character)this.upper.get(0)).toString
            ();
        }

    }
    else
      for (i = 0; i < this.length.intValue(); ++i)
      {
        RandomStr tmp1142_1141 = this; tmp1142_1141.randomstr = tmp1142_1141.randomstr + 
          randomChar((Character)this.lower.get(0), 
          (Character)this.upper.get(0)).toString
          ();
      }
  }

  private final Character randomSingle()
  {
    return new Character(this.single[(int)(getFloat() * this.singlecount - 1F)]);
  }

  private final Character randomChar(Character lower, Character upper)
  {
    char low = lower.charValue();
    char up = upper.charValue();

    int tempval = (int)(low + getFloat() * (up - low));

    return new Character((char)tempval);
  }

  public final String getRandom()
  {
    this.randomstr = new String();

    generaterandom();

    if (this.hmap != null)
    {
      while (this.hmap.containsKey(this.randomstr))
      {
        generaterandom();
      }

      this.hmap.put(this.randomstr, null);
    }

    return this.randomstr;
  }

  public final void setRanges(ArrayList low, ArrayList up)
  {
    this.lower = low;
    this.upper = up;
  }

  public final void setHmap(HashMap map)
  {
    this.hmap = map;
  }

  public final void setLength(String value)
  {
    this.length = new Integer(value);
  }

  public final void setAlgorithm(String value)
  {
    this.algorithm = value;
    this.secure = true;
  }

  public final void setProvider(String value)
  {
    this.provider = value;
  }

  public final void setAllchars(boolean value)
  {
    this.allchars = value;
  }

  public final void setSingle(char[] chars, int value)
  {
    this.single = chars;
    this.singlecount = value;
    this.singles = true;
  }

  public final void setCharset(String value)
  {
    boolean more = true;

    this.lower = new ArrayList(3);
    this.upper = new ArrayList(3);

    if (value.compareTo("all") == 0) {
      this.allchars = true;

      more = false;
    } else if ((value.charAt(1) == '-') && (value.charAt(0) != '\\'))
    {
      while ((more) && (value.charAt(1) == '-'))
      {
        if (value.charAt(0) == '\\') {
          break;
        }

        this.lower.add(new Character(value.charAt(0)));
        this.upper.add(new Character(value.charAt(2)));

        if (value.length() <= 3) {
          more = false;
        }
        else
        {
          value = value.substring(3);
        }
      }
    }

    if (more)
    {
      this.single = new char[30];

      StringTokenizer tokens = new StringTokenizer(value);

      while (tokens.hasMoreTokens())
      {
        String token = tokens.nextToken();

        if (token.length() > 1)
        {
          this.single[(this.singlecount++)] = '-';
        }

        this.single[(this.singlecount++)] = token.charAt(0);
      }
    }
    if ((this.lower == null) && (this.single == null))
      setCharset("a-zA-Z0-9");
  }
}