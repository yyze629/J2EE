package com.yinyang.sellerpay.web.velocity;

import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.HttpServletRequest;

public class InternationalizationProperties
{
  public static ConcurrentHashMap<String, Properties> cachproperties = new ConcurrentHashMap();
  private static String bundles;
  public static final String LOCALE_REQUEST_ATTRIBUTE_NAME = CookieLocaleResolver.class.getName() + ".LOCALE";

  public static void setProp(Locale locale, String bundles)
  {
    if (bundles == null)
      bundles = bundles;

    Properties prop = new Properties();
    String propertiesFile = propertiesFile(locale);
    InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFile);
    if (in == null) {
      String propertiesFileDefault = propertiesFile();
      InputStream inDefault = Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileDefault);
      try {
        prop.load(inDefault);
        if (cachproperties.containsKey(propertiesFileDefault)) return;
        cachproperties.put(propertiesFileDefault, prop);
      }
      catch (IOException edefault)
      {
        edefault.printStackTrace();
      }
    }
    else {
      try {
        prop.load(in);
        if (cachproperties.containsKey(propertiesFile)) return;
        cachproperties.put(propertiesFile, prop);
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }

  private static String propertiesFile(Locale locale)
  {
    String language = locale.getLanguage();
    String country = locale.getCountry();
    StringBuffer buffer = new StringBuffer(bundles).append("_").append(language).append("_").append(country).append(".properties");
    return buffer.toString(); }

  private static String propertiesFile() {
    StringBuffer buffer = new StringBuffer(bundles).append("_").append("zh").append("_").append("CN").append(".properties");
    return buffer.toString();
  }

  private static Properties getProp(Locale locale) {
    String propertiesFile = propertiesFile(locale);
    if (cachproperties.containsKey(propertiesFile))
      return ((Properties)cachproperties.get(propertiesFile));

    return ((Properties)cachproperties.get(propertiesFile()));
  }

  private static Properties getProperties()
  {
    HttpServletRequest request = (HttpServletRequest)InternationalizationFilter.actionContext.get();
    Locale locale = (Locale)request.getAttribute(LOCALE_REQUEST_ATTRIBUTE_NAME);
    return getProp(locale);
  }

  public static String getProperty(String key) {
    String str = getProperties().getProperty(key);
    if (str == null)
      return key;

    return str;
  }
}