## 配置中心功能使用说明  
###配置中心主要功能
配置中心主要功能是将业务相关的配置集中处理。

###配置中心有哪些功能主要优点
* 集中处理分散的配置文件。
* 集中不同模式的配置文件例如 xml ,properties ,diamond ,system参数。
* 可实现定制化的reload。
* 便于监控参数。

###配置中心使用方式

* 不使用集中管理，只使用加载的工具类。
	java 代码示例如下：
	
		Configuration config = LoaderFactory.load("classpath:proptest.properties");
		config.getString("somekey");
		
* 使用集中式的管理
	增加配置中心的配置文件，格式如下：
	 
    	dhconfig=classpath:dhconfig.properties
        payment=classpath:payment.properties
        errorCode=classpath:errorCode.properties
	
	`configCenter.properties`
	
* 示例代码见  `com.dhgate.skeleton.web.action.BaseAction#config`

	* 使用spring 声明bean
	
    	``` xml
    	<bean id="configCenter" class="com.dhgate.config.impl.DefaultConfigCenter">
        	<constructor-arg>
        		<value>classpath:configCenter.properties</value>
        	</constructor-arg>
        </bean>
		```
		
	* 在使用的bean中注入, 并调用。
	
    	``` java
    		@Autowired
			private ConfigCenter configCenter;
			... ...
			configCenter.getString("errorCode.001001")
    	```
	