## dhgate session 功能使用说明  

### dhgate session 主要动能：
* 借助cache 实现setAttribute和getAttribute
* 分离cache相关操作。session中不再保存非回话生命周期的缓存数据
* 抽离鉴权的对象：DHGatePassport. 细节的User Info信息视为session内缓存数据。

### dhgate session 主要优点：
* 为数据生命周期划分做基础
    * request
    * session
    * application
    * cache
* 分离cache方式：memcached or redis
* 权限相关的操作使用DHGatePassport。dhgatepassport以字符串形式缓存，并支持按版本号向前兼容
* DHGatePassport可比较容易的拓展为支持不同系统间鉴权。

### dhgate session 使用方式：
* 在action中使用：
    继承BaseAction

```java
package com.dhgate.skeleton.web.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.validation.SkipValidation;

@ParentPackage("default")
@Namespace("/needlogin")
public class needloginAction extends BaseAction{
    
         @Action(value = "showpassport", results = { @Result(name = "success", type = "velocity", location = "showpassport.vm") })
    public String showpassport() {
        //String data = (String)session.getAttribute("dataKey");
                   request.setAttribute("passport", this.getDHgatePassport());
        return "success";
    }
}   
```

* 在非action中使用

```java
package com.dhgate.skeleton.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.dhgate.skeleton.web.context.CacheDHgateSessionFactory;
import com.dhgate.skeleton.web.context.ContextConstants;
import com.dhgate.skeleton.web.context.DHgatePassport;
import com.dhgate.skeleton.web.util.CookieConstants;
import com.dhgate.skeleton.web.util.CookieUtil;
import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

/**
* 安全检查拦截器。实现身份认证和授权检查两个功能。
* 扩展MethodFilterInterceptor类，是为了提供excludeMethods功能，即对指定的方法名不进行拦截。
* 
 * @author shengyangchun@dhgate.com
* 
 */
public class SecurityInterceptor extends MethodFilterInterceptor {

    private static final long serialVersionUID = -8852694586628070811L;
         private static final Logger logger = Logger
                            .getLogger(SecurityInterceptor.class);
         private static final String BUYERDOMAIN = "www.dhgate.com";
         private static final String SELLERDOMAIN = "seller.dhgate.com";

         @Override
         public String doIntercept(ActionInvocation invocation) throws Exception {
                   // 取得请求相关的ActionContext实例
                   ActionContext ctx = invocation.getInvocationContext();
                   String sessionID; // seller系统种的cookie中存在的sessionid

                   HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
                   HttpServletResponse response = (HttpServletResponse) ctx.get(ServletActionContext.HTTP_RESPONSE);

                   logger.info(request.getRequestURL().toString());

                   /* 首先检查是否有cookie */

                   sessionID = CookieUtil.getCookie(request, CookieConstants.DHC_S);
                   if (StringUtils.isBlank(sessionID)) {
                            logger.warn("Cookie doesn't exist,redirect to login page");
                            return Action.LOGIN;
                   }

                   DHgateSession session = (DHgateSession) CacheDHgateSessionFactory.getDHgateSession(sessionID);
                   if(null == session){
                            return Action.LOGIN;
                   }
                   
                   DHgatePassport passport = (DHgatePassport)session.getAttribute(ContextConstants.PASSPORT);
                   if (null == passport || !ContextConstants.PASSPORT_ROLE_VISITOR.equals(passport.getRole())) {
                            logger.warn(" error ------------ user is null ---------------");
                            return Action.LOGIN;
                   }
                   
                   return invocation.invoke();
         }

}

```
* 如何实现dhpassport的向前兼容:

```java
  package com.dhgate.skeleton.web.context;

import java.io.Serializable;

public class DHgatePassport implements Serializable{

         private static final long serialVersionUID = 8878285178274158703L;
         
         private static String VERSION = "2013-01-05 10:53:22";


         public static DHgatePassport fromJson(String json, String version) {
                   if (VERSION.equals(version)) {
                            return fromJson(json);
                   }

                   throw new RuntimeException("Version : " + version + 
                                     " not been supported any more! " +
                                     "Curr version is : " + VERSION);
         }

}

```
