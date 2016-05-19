## 在pom中增加依赖：
``` xml
		<dependency>
			<groupId>org.apache.struts</groupId>
			<artifactId>struts2-sitemesh-plugin</artifactId>
			<version>${struts2.version}</version>
		</dependency>
```

### 增加配置文件,文件示例见本工程：
`sitemesh.xml`


### 在web.xml中增加配置


``` xml
	<filter-mapping>
        <filter-name>sitemesh</filter-name>
        <url-pattern>/*</url-pattern>
        <dispatcher>REQUEST</dispatcher>
        <dispatcher>FORWARD</dispatcher>
        <dispatcher>INCLUDE</dispatcher>
    </filter-mapping>
  
    <servlet>
        <servlet-name>velocity</servlet-name>
        <servlet-class>com.opensymphony.module.sitemesh.velocity.VelocityDecoratorServlet</servlet-class>     
        <!-- <servlet-class>org.apache.struts2.sitemesh.VelocityDecoratorServlet</servlet-class> -->
    </servlet>
```
