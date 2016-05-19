package com.yinyang.sellerpay.web.velocity;

import java.io.IOException;
import java.util.Locale;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class InternationalizationFilter implements Filter
{
  public static ThreadLocal<HttpServletRequest> actionContext = new ThreadLocal();
  private static String bundles = "resourcesMessages";

  public void init(FilterConfig cong)
  {
    String bundles = cong.getInitParameter("bundles");
    if ((bundles != null) && (!("".equals(bundles))))
      bundles = bundles;
  }

  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
    throws IOException, ServletException
  {
    HttpServletRequest request = (HttpServletRequest)servletRequest;
    HttpServletResponse response = (HttpServletResponse)servletResponse;
    request.setCharacterEncoding("UTF-8");
    actionContext.set(request);
    /*String languageName = request.getParameter("dhLanguageName");
    String serverName = request.getServerName();
    LocaleResolver localeResolver = new CookieLocaleResolver(serverName);
    if ((languageName != null) && (!("".equals(languageName)))) {
      String[] languageArray = languageName.split("-");
      Locale locale = new Locale(languageArray[0], languageArray[1]);
      localeResolver.setLocale(request, response, locale, bundles);
    } else {
      localeResolver.setCookieLocale(request, bundles);
    }*/

    chain.doFilter(request, response);
    
  }

  public void destroy()
  {
  }
}