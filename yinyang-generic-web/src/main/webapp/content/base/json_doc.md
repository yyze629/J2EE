## JSON 格式数据请求说明

###JSON 格式数据请求使用方式

* 在`structs.xml`中增加配置：

	``` xml
		<package name="default" namespace="/" extends="json-default">
		... ...
		</package>
	```
		
* 在调用方法上声明返回类型为json
	
	``` java
		@Action(value = "showProdList", results = { @Result(name = "success", type = "json",params = {"root", "productList"}) })
	```
