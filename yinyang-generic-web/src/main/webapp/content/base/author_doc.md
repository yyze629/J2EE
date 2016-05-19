## 验证登陆功能使用说明  

### 在`structs.xml`中增加如下配置：
``` xml
	 <package name="default" namespace="/" extends="json-default">
        <interceptors>
			<interceptor name="authority" class="com.dhgate.skeleton.web.interceptor.SecurityInterceptor" />
				<interceptor-stack name="loginStack">
					<interceptor-ref name="authority" >
						<param name="includeMethods">showpassport</param> 
					</interceptor-ref>
					<interceptor-ref name="defaultStack" />
				</interceptor-stack>
			</interceptors>
			
			<default-interceptor-ref name="loginStack" />
		
        <global-results>
            <result name="login" type="redirect">
            	<!-- 这里配置登陆的url 可能为第三方的 -->
            	<param name="location">/sso/login.do</param>
            	<!-- 以下的参数都是登录功能使用的 -->
            	<param name="successedurl">/dhgate-dmrs-web/usr/return.do</param>
            	<param name="failedurl">/dhgate-dmrs-web/usr/return.do</param>
            </result>
        </global-results>
    </package>
```
