## 表单功能使用说明  

### 在`structs.xml`中增加如下配置：
``` xml
	 <package name="default" namespace="/" extends="json-default">
        <interceptors>
			<interceptor name="token" class="com.dhgate.skeleton.web.interceptor.TokenInterceptor" />
				<interceptor-stack name="dhStack">
					<interceptor-ref name="token" >
					</interceptor-ref>
					<interceptor-ref name="defaultStack" />
				</interceptor-stack>
			</interceptors>
			
			<default-interceptor-ref name="dhStack" />
    </package>
```
### 在跳转到页面时，像session和form中设置token字段:
``` java
	String tokenValue = UUID.randomUUID().toString();
	this.getDHgateSession().set("product.token", tokenValue);
```

``` html
	<code>
		<input type="hidden" name="struts.token.name" value="product.token"/>
		<input type="hidden" name="struts.token" value="${tokenValue}"/>
	</code>
```
