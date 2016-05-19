## memcached锁功能使用说明  

### 调用方法：
* 声明锁
`Locker lock = new IncreaseCacheLocker(cache);`
* 锁住对象
`lock.lock("key");`
* 检查点
`lock.checkPoint("key");`
* 释放对象
`lock.realse("key");`


### 注意事项：
* **现在必须手动处理异常，保证锁可以释放**

### 即将到来：
* memcached锁aop版本，更加透明，更加易用，敬请期待！

