package com.yinyang.sellerpay.web.velocity;

public class StringTokenizer
  implements Enumeration<Object>
{
  private int currentPosition;
  private int newPosition;
  private int maxPosition;
  private String str;
  private String delimiters;
  private boolean retDelims;
  private boolean delimsChanged;
  private int maxDelimCodePoint;
  private boolean hasSurrogates;
  private int[] delimiterCodePoints;

  private void setMaxDelimCodePoint()
  {
    int c;
    if (this.delimiters == null) {
      this.maxDelimCodePoint = 0;
      return;
    }

    int m = 0;

    int count = 0;
    for (int i = 0; i < this.delimiters.length(); i += Character.charCount(c)) {
      c = this.delimiters.charAt(i);
      if ((c >= 55296) && (c <= 57343)) {
        c = this.delimiters.codePointAt(i);
        this.hasSurrogates = true;
      }
      if (m < c)
        m = c;
      ++count;
    }
    this.maxDelimCodePoint = m;

    if (this.hasSurrogates) {
      this.delimiterCodePoints = new int[count];
      int i = 0; for (int j = 0; i < count; ) {
        c = this.delimiters.codePointAt(j);
        this.delimiterCodePoints[i] = c;

        ++i; j += Character.charCount(c);
      }
    }
  }

  public StringTokenizer(String str, String delim, boolean returnDelims)
  {
    this.hasSurrogates = false;

    this.currentPosition = 0;
    this.newPosition = -1;
    this.delimsChanged = false;
    this.str = str;
    this.maxPosition = str.length();
    this.delimiters = delim;
    this.retDelims = returnDelims;
    setMaxDelimCodePoint();
  }

  public StringTokenizer(String str, String delim)
  {
    this(str, delim, false);
  }

  public StringTokenizer(String str)
  {
    this(str, " \t\n\r\f", false);
  }

  private int skipDelimiters(int startPos)
  {
    if (this.delimiters == null)
      throw new NullPointerException();

    int position = startPos;
    while ((!(this.retDelims)) && (position < this.maxPosition))
      if (!(this.hasSurrogates)) {
        char c = this.str.charAt(position);
        if (c > this.maxDelimCodePoint) break; if (this.delimiters.indexOf(c) < 0)
          break;
        ++position;
      } else {
        int c = this.str.codePointAt(position);
        if (c > this.maxDelimCodePoint) break; if (!(isDelimiter(c)))
          break;

        position += Character.charCount(c);
      }

    return position;
  }

  private int scanToken(int startPos)
  {
    char c;
    int cInt;//TODO 已经改动了
    int position = startPos;
    while (position < this.maxPosition)
      if (!(this.hasSurrogates)) {
        c = this.str.charAt(position);
        if ((c <= this.maxDelimCodePoint) && (this.delimiters.indexOf(c) >= 0))
          break;
        ++position;
      } else {
    	  cInt = this.str.codePointAt(position);
        if ((cInt <= this.maxDelimCodePoint) && (isDelimiter(cInt)))
          break;
        position += Character.charCount(cInt);
      }

    if ((this.retDelims) && (startPos == position))
      if (!(this.hasSurrogates)) {
        c = this.str.charAt(position);
        if ((c <= this.maxDelimCodePoint) && (this.delimiters.indexOf(c) >= 0))
          ++position;
      } else {
    	  cInt = this.str.codePointAt(position);
        if ((cInt <= this.maxDelimCodePoint) && (isDelimiter(cInt)))
          position += Character.charCount(cInt);
      }

    return position;
  }

  private boolean isDelimiter(int codePoint) {
    for (int i = 0; i < this.delimiterCodePoints.length; ++i)
      if (this.delimiterCodePoints[i] == codePoint)
        return true;


    return false;
  }

  public boolean hasMoreTokens()
  {
    this.newPosition = skipDelimiters(this.currentPosition);
    return (this.newPosition < this.maxPosition);
  }

  public String nextToken()
  {
    this.currentPosition = (((this.newPosition >= 0) && (!(this.delimsChanged))) ? 
      this.newPosition : skipDelimiters(this.currentPosition));

    this.delimsChanged = false;
    this.newPosition = -1;

    if (this.currentPosition >= this.maxPosition)
      return "";
    int start = this.currentPosition;
    this.currentPosition = scanToken(this.currentPosition);
    return this.str.substring(start, this.currentPosition);
  }

  public String nextToken(String delim)
  {
    this.delimiters = delim;

    this.delimsChanged = true;

    setMaxDelimCodePoint();
    return nextToken();
  }

  public boolean hasMoreElements()
  {
    return hasMoreTokens();
  }

  public Object nextElement()
  {
    return nextToken();
  }

  public int countTokens()
  {
    int count = 0;
    int currpos = this.currentPosition;
    while (currpos < this.maxPosition) {
      currpos = skipDelimiters(currpos);
      if (currpos >= this.maxPosition)
        break;
      currpos = scanToken(currpos);
      ++count;
    }
    return count;
  }
}