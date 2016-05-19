package com.yinyang.sellerpay.web.velocity;

public abstract interface Enumeration<E>
{
  public abstract boolean hasMoreElements();

  public abstract E nextElement();
}