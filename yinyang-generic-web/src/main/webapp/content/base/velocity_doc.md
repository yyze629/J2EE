## 在pom中增加依赖：
``` xml
	<dependency>
			<groupId>org.apache.velocity</groupId>
			<artifactId>velocity</artifactId>
			<version>1.7</version>
		</dependency>

		<dependency>
			<groupId>org.apache.velocity</groupId>
			<artifactId>velocity-tools</artifactId>
			<version>2.0</version>
			<exclusions>
				<exclusion>
					<groupId>org.apache.velocity</groupId>
					<artifactId>velocity</artifactId>
				</exclusion>
				<exclusion>
					<groupId>org.apache.struts</groupId>
					<artifactId>struts-taglib</artifactId>
				</exclusion>
				<exclusion>
					<groupId>commons-logging</groupId>
					<artifactId>commons-logging</artifactId>
				</exclusion>
			</exclusions>
		</dependency>

```

### 增加配置文件,文件示例见本工程：
`velocity.properties`

### 在struts.xml 中增加配置：
``` xml
	<constant name="struts.velocity.toolboxlocation" value="/WEB-INF/tools.xml" />
```


### 在web.xml中增加配置


``` xml
	<servlet>
        <servlet-name>velocity</servlet-name>
        <servlet-class>com.opensymphony.module.sitemesh.velocity.VelocityDecoratorServlet</servlet-class>     
        <!-- <servlet-class>org.apache.struts2.sitemesh.VelocityDecoratorServlet</servlet-class> -->
    </servlet>
    <servlet-mapping>
        <servlet-name>velocity</servlet-name>
        <url-pattern>*.vm</url-pattern>
    </servlet-mapping>
```
