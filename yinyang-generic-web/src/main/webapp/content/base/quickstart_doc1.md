## 构建一个拥有全功能的web项目  

### 操作步骤:
* 从 http://192.168.1.234/svn/trunk/productline/dhgate-dmrs-web/trunk 导出一份demo工程
* 将工程名修改为项目名字
* 修改pom文件中 artifactId 为工程名。（用 <!-- 请修改为工程名字  --> 标出）
* 修改 applicationContext.xml中的datasource地址。
* 导入eclipse并启动工程。可以看到工程的帮助说明。
* 开始开发时。可删除 webapp和src/main/java目录下的示例代码。进行业务的开发。
