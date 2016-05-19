## 国际化功能说明  

* 在`pom.xml`中增加依赖：
``` xml
	 <dependency>
			<groupId>com.dhgate</groupId>
			<artifactId>dhgate-apsaras-internation</artifactId>
			<version>1.0-SNAPSHOT</version>
		</dependency>
```

* 增加配置文件：
`globalMessages_en_US.properties` `globalMessages_zh_CN.properties` 

* 在struts.xml 中增加配置:
``` xml
	<constant name="struts.custom.i18n.resources" value="globalMessages" />
```

* 在vm页面中用如下方式调用:
``` 
	\#sdh("product.input.category")
```
