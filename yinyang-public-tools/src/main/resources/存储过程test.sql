BEGIN
	#Routine body goes here...

#END;
#BEGIN 
    /* 定义变量 */
    declare tmp0 VARCHAR(1000);
    declare tmp1 VARCHAR(1000);
    declare done int default -1;  -- 用于控制循环是否结束
      
    /* 声明游标 */  
    declare myCursor cursor for select cell_0,cell_1 from t_test;  
      
    /* 当游标到达尾部时，mysql自动设置done=1 */     
    declare continue handler for not found set done=1;  
      
    /* 打开游标 */  
    open myCursor;  
      
    /* 循环开始 */  
    myLoop: LOOP  
      
        /* 移动游标并赋值 */  
        fetch myCursor into tmp0,tmp1;  
        
				-- 游标到达尾部,退出循环
        if done = 1 then   
        leave myLoop;  
        end if;  
          
        /* do something */  
        -- 循环输出信息
				select tmp0,tmp1 ;

				-- 可以加入insert,update等语句
      
    /* 循环结束 */  
    end loop myLoop;  
      
    /* 关闭游标 */  
    close myCursor;  
END