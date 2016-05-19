package com.yinyang.sellerpay.web.velocity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class WebUtils
{
  public static Cookie getCookie(HttpServletRequest request, String name)
  {
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
      Cookie[] arrayOfCookie1;
      int j = (arrayOfCookie1 = cookies).length; for (int i = 0; i < j; ++i) { Cookie cookie = arrayOfCookie1[i];
        if (name.equals(cookie.getName()))
          return cookie;
      }
    }

    return null;
  }

  public static boolean hasLength(CharSequence str)
  {
    return ((str != null) && (str.length() > 0));
  }

  public static boolean hasLength(String str)
  {
    return hasLength(str);
  }

  public static String trimLeadingWhitespace(String str)
  {
    if (!(hasLength(str)))
      return str;

    StringBuilder sb = new StringBuilder(str);
    while ((sb.length() > 0) && (Character.isWhitespace(sb.charAt(0))))
      sb.deleteCharAt(0);

    return sb.toString();
  }

  public static String trimLeadingCharacter(String str, char leadingCharacter)
  {
    if (!(hasLength(str)))
      return str;

    StringBuilder sb = new StringBuilder(str);
    while ((sb.length() > 0) && (sb.charAt(0) == leadingCharacter))
      sb.deleteCharAt(0);

    return sb.toString();
  }

  public static Locale parseLocaleString(String localeString) {
    String[] parts = tokenizeToStringArray(localeString, "_ ", false, false);
    String language = (parts.length > 0) ? parts[0] : "";
    String country = (parts.length > 1) ? parts[1] : "";
    String variant = "";
    if (parts.length >= 2)
    {
      int endIndexOfCountryCode = localeString.indexOf(country) + country.length();

      variant = trimLeadingWhitespace(localeString.substring(endIndexOfCountryCode));
      if (variant.startsWith("_"))
        variant = trimLeadingCharacter(variant, '_');
    }

    return ((language.length() > 0) ? new Locale(language, country, variant) : null);
  }

  public static String[] tokenizeToStringArray(String str, String delimiters) {
    return tokenizeToStringArray(str, delimiters, true, true);
  }

  public static String[] toStringArray(Collection<String> collection)
  {
    if (collection == null)
      return null;

    return ((String[])collection.toArray(new String[collection.size()]));
  }

  public static String[] tokenizeToStringArray(String str, String delimiters, boolean trimTokens, boolean ignoreEmptyTokens)
  {
    if (str == null)
      return null;

    StringTokenizer st = new StringTokenizer(str, delimiters);
    List tokens = new ArrayList();
    while (st.hasMoreTokens()) {
      String token = st.nextToken();
      if (trimTokens)
        token = token.trim();

      if ((!(ignoreEmptyTokens)) || (token.length() > 0))
        tokens.add(token);
    }

    return toStringArray(tokens);
  }
}