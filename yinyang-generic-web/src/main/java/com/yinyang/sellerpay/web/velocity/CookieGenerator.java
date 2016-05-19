package com.yinyang.sellerpay.web.velocity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

public class CookieGenerator
{
  public static final String DEFAULT_COOKIE_PATH = "/";

  @Deprecated
  public static final int DEFAULT_COOKIE_MAX_AGE = 2147483647;
  private String cookieName;
  private String cookieDomain;
  private String cookiePath = "/";
  private Integer cookieMaxAge = Integer.valueOf(2147483647);
  private boolean cookieSecure = false;

  public void setCookieName(String cookieName)
  {
    this.cookieName = cookieName;
  }

  public String getCookieName()
  {
    return this.cookieName;
  }

  public void setCookieDomain(String cookieDomain)
  {
    this.cookieDomain = cookieDomain;
  }

  public String getCookieDomain()
  {
    return this.cookieDomain;
  }

  public void setCookiePath(String cookiePath)
  {
    this.cookiePath = cookiePath;
  }

  public String getCookiePath()
  {
    return this.cookiePath;
  }

  public void setCookieMaxAge(Integer cookieMaxAge)
  {
    this.cookieMaxAge = cookieMaxAge;
  }

  public Integer getCookieMaxAge()
  {
    return this.cookieMaxAge;
  }

  public void setCookieSecure(boolean cookieSecure)
  {
    this.cookieSecure = cookieSecure;
  }

  public boolean isCookieSecure()
  {
    return this.cookieSecure;
  }

  public void addCookie(HttpServletResponse response, String cookieValue)
  {
    Cookie cookie = createCookie(cookieValue);
    Integer maxAge = getCookieMaxAge();
    if (maxAge != null)
      cookie.setMaxAge(maxAge.intValue());

    if (isCookieSecure())
      cookie.setSecure(true);

    response.addCookie(cookie);
  }

  public void removeCookie(HttpServletResponse response)
  {
    Cookie cookie = createCookie("");
    cookie.setMaxAge(0);
    response.addCookie(cookie);
  }

  protected Cookie createCookie(String cookieValue)
  {
    Cookie cookie = new Cookie(getCookieName(), cookieValue);
    if (getCookieDomain() != null)
      cookie.setDomain(getCookieDomain());

    cookie.setPath(getCookiePath());
    return cookie;
  }
}