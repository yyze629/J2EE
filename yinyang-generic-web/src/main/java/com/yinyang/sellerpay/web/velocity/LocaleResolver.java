package com.yinyang.sellerpay.web.velocity;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract interface LocaleResolver
{
  public abstract void setLocale(HttpServletRequest paramHttpServletRequest, HttpServletResponse paramHttpServletResponse, Locale paramLocale, String paramString);

  public abstract void setCookieLocale(HttpServletRequest paramHttpServletRequest, String paramString);
}