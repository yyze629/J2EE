package com.yinyang.sellerpay.web.velocity;

import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieLocaleResolver extends CookieGenerator
  implements LocaleResolver
{
  public static final String LOCALE_REQUEST_ATTRIBUTE_NAME = CookieLocaleResolver.class.getName() + ".LOCALE";
  public static final String DEFAULT_COOKIE_NAME = CookieLocaleResolver.class.getName() + ".LOCALE";
  private Locale defaultLocale;

  public CookieLocaleResolver(String url)
  {
    setCookieName(DEFAULT_COOKIE_NAME);
    setCookieDomain(url);
  }

  public void setDefaultLocale(Locale defaultLocale)
  {
    this.defaultLocale = defaultLocale;
  }

  protected Locale getDefaultLocale()
  {
    return this.defaultLocale;
  }

  public void setCookieLocale(HttpServletRequest request, String bundles)
  {
    Locale locale = null;

    Cookie cookie = WebUtils.getCookie(request, getCookieName());
    if (cookie != null) {
      locale = WebUtils.parseLocaleString(cookie.getValue());

      if (locale != null) {
        request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
        InternationalizationProperties.setProp(locale, bundles);
        return;
      }

    }

    determineDefaultLocale(request, bundles);
  }

  public void setLocale(HttpServletRequest request, HttpServletResponse response, Locale locale, String bundles) {
    if (locale != null)
    {
      request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, locale);
      InternationalizationProperties.setProp(locale, bundles);
      addCookie(response, locale.toString());
    }
  }

  protected Locale determineDefaultLocale(HttpServletRequest request, String bundles)
  {
    Locale defaultLocale = getDefaultLocale();
    if (defaultLocale == null)
      defaultLocale = request.getLocale();

    request.setAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME, defaultLocale);
    InternationalizationProperties.setProp(defaultLocale, bundles);
    return defaultLocale;
  }
}