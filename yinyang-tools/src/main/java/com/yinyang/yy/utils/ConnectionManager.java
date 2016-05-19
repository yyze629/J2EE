package com.yinyang.yy.utils;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.mysql.jdbc.Statement;

/** 
 * 数据库连接池工具类 读取-从库-192.168.140.29
 * @author  作者：yinyang E-mail: 
 * @date 创建时间：2015年4月15日 下午5:45:55 
 * @version 1.0  
 */
public final class ConnectionManager {
	
    private static ConnectionManager instance;
    private static ComboPooledDataSource dataSource;
    private final static ResourceBundle bundle = ResourceBundle.getBundle("jdbc");
    private final static String USERNAME = bundle.getString("jdbc.username");
    private final static String PASSWORD = bundle.getString("jdbc.password");
    private final static String URL = bundle.getString("jdbc.Url");
    private final static String DRIVER = bundle.getString("jdbc.Driver");
    
    /*private static Properties p = new Properties();  
    private final static String USERNAME = p.getProperty("jdbc.username2");
    private final static String PASSWORD = p.getProperty("jdbc.password2");
    private final static String URL = p.getProperty("jdbc.Url2");
    private final static String DRIVER = p.getProperty("jdbc.Driver2");
    static{  
        try {  
	           	String filePath = System.getProperty("user.dir") + "/jdbc.properties";    
	        	InputStream in = new BufferedInputStream(new FileInputStream(filePath));   	
	        	p.load(in);
	        } catch (IOException e) {  
	        	e.printStackTrace();  
	        }  
      } */
    /*private final static String USERNAME = "llb365";
    private final static String PASSWORD = "1qazxsw2#EDC";
    private final static String URL = "jdbc\\:mysql\\://192.168.140.32\\:3306/liuliangbao?characterEncoding\\=utf8&zeroDateTimeBehavior\\=convertToNull";
    private final static String DRIVER = "com.mysql.jdbc.Driver";*/
    
    private ConnectionManager() throws SQLException, PropertyVetoException {
        dataSource = new ComboPooledDataSource();
        dataSource.setUser(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setJdbcUrl(URL);
        dataSource.setDriverClass(DRIVER);
        dataSource.setInitialPoolSize(10);
        dataSource.setMinPoolSize(2);
        dataSource.setMaxPoolSize(20);
        dataSource.setMaxStatements(0);                   
        dataSource.setMaxIdleTime(60);
        dataSource.setCheckoutTimeout(10000);//
        dataSource.setAutoCommitOnClose(false);
        dataSource.setIdleConnectionTestPeriod(60);
        dataSource.setTestConnectionOnCheckin(false);
        dataSource.setTestConnectionOnCheckout(false);
        dataSource.setAcquireIncrement(5);//
        dataSource.setAcquireRetryAttempts(20);
        dataSource.setAcquireRetryDelay(1000);
    }
    
    public static final ConnectionManager getInstance() {
        if (instance == null) {
            try {
                instance = new ConnectionManager();
                
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return instance;
    }
    
    public synchronized final Connection getConnection() {
        Connection conn = null;
        try {
            conn = dataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    
    /**
     * 释放资源
     * @param o
     */
	public static void release(Object o) {
		if (o == null) {
			return;
		}
		if (o instanceof ResultSet) {
			try {
				((ResultSet) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Statement) {
			try {
				((Statement) o).close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (o instanceof Connection) {
			Connection c = (Connection) o;
			try {
				if (!c.isClosed()) {
					c.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

	}

	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		release(rs);
		release(stmt);
		release(conn);
	}
}