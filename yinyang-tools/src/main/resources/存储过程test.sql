BEGIN
	#Routine body goes here...

#END;
#BEGIN 
    /* ������� */
    declare tmp0 VARCHAR(1000);
    declare tmp1 VARCHAR(1000);
    declare done int default -1;  -- ���ڿ���ѭ���Ƿ����
      
    /* �����α� */  
    declare myCursor cursor for select cell_0,cell_1 from t_test;  
      
    /* ���α굽��β��ʱ��mysql�Զ�����done=1 */     
    declare continue handler for not found set done=1;  
      
    /* ���α� */  
    open myCursor;  
      
    /* ѭ����ʼ */  
    myLoop: LOOP  
      
        /* �ƶ��α겢��ֵ */  
        fetch myCursor into tmp0,tmp1;  
        
				-- �α굽��β��,�˳�ѭ��
        if done = 1 then   
        leave myLoop;  
        end if;  
          
        /* do something */  
        -- ѭ�������Ϣ
				select tmp0,tmp1 ;

				-- ���Լ���insert,update�����
      
    /* ѭ������ */  
    end loop myLoop;  
      
    /* �ر��α� */  
    close myCursor;  
END