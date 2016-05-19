package com.yinyang.yy.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.Priority;

/**
 * Log工具类【直接使用,无需通过配置文件初始化】
 * <pre>Logger的由低到高级别如下： ALL< DEBUG < INFO < WARN < ERROR < FATAL < OFF </pre>
 * 
 * @author 作者：yinyang E-mail:
 * @date 创建时间：2015年6月26日 下午2:29:05
 * @version 1.0
 */
public class LoggerUtil {
	
	/**
	 * 创建Logger实例
	 * 
	 * @param clazz
	 *            事件日志发生类
	 * @param ifConsole
	 *            是否输出到控制台
	 * @param ifFile
	 *            是否输出到文件
	 * @param logFilePath
	 *            日志文件地址（路径分割使用“/”）
	 * @param logFileName
	 *            日志文件名称（名称后面会自动增加日期:test 2015-06-26.log ）注意无需'.log'后缀名
	 * @param ifLocate
	 *            是否定位事件日志发生位置（类.方法 line）
	 * @param logLevel
	 *            日志级别（ALL0 < DEBUG1 < INFO2 < WARN3 < ERROR4 < FATAL5 < OFF6）控制台默认输出为DEBUG，文件默认输出INFO
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, boolean ifConsole, boolean ifFile, String logFilePath, String logFileName, boolean ifLocate, int logLevel) {

		if (ifConsole == false && ifFile == false)
			return null;

		//注意代码顺序
		String logFile = logFilePath + logFileName;
		if (ifFile == true && logFile.trim().length() < 1){
			return null;
		}else{
			String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
			logFile = logFilePath + logFileName + " " + date + ".log";
		}
		
		Priority LOG_LEVEL = null;
		if(logLevel==0){
			LOG_LEVEL = Level.ALL;
		}else if (logLevel==1){
			LOG_LEVEL = Level.DEBUG;
		}else if (logLevel==2){
			LOG_LEVEL = Level.INFO;
		}else if (logLevel==3){
			LOG_LEVEL = Level.WARN;
		}else if (logLevel==4){
			LOG_LEVEL = Level.ERROR;
		}else if (logLevel==5){
			LOG_LEVEL = Level.FATAL;
		}else if (logLevel==6){
			LOG_LEVEL = Level.OFF;
		}
		
		String conversionPattern;
		if (ifLocate == true) {
			// 日志内容形式如：[2014-03-11 01:34:45.572] [DEBUG]
			// com.yin.utils.LoggerUtilsTest.main(Line:15) | 测试DEBUG日志
			//[2015-06-26 14:49:52.056] [WARN ] [log4j.TestUtils.main(Line:18)] | 我是测试  LoggerUtil  的warn
			conversionPattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] [%c.%M(Line:%L)] | %m %n";
		} else {
			// 日志内容形式如：2014-03-11 01:34:45.572 [DEBUG] | 测试DEBUG日志
			conversionPattern = "[%d{yyyy-MM-dd HH:mm:ss.SSS}] [%-5p] | %m %n";
		}
		PatternLayout layout = new PatternLayout();
		layout.setConversionPattern(conversionPattern);

		Logger logger = Logger.getLogger(clazz);
		logger.removeAllAppenders();
		logger.setLevel(Level.DEBUG);
		logger.setAdditivity(false); // Logger不会在父Logger的appender里输出，默认为true

		if (ifConsole == true) { // 日志输出到控制台
			ConsoleAppender consoleAppender = new ConsoleAppender();
			consoleAppender.setLayout(layout);
			if(LOG_LEVEL==null){
				consoleAppender.setThreshold(Level.DEBUG); // ConsoleAppender日志级别为DEBUG
			}
			else{
				consoleAppender.setThreshold(LOG_LEVEL);
			}
			consoleAppender.activateOptions();
			logger.addAppender(consoleAppender);
		}

		if (ifFile == true) { // 日志输出到文件
			FileAppender fileAppender = new FileAppender();
			fileAppender.setLayout(layout);
			fileAppender.setFile(logFile);
			fileAppender.setEncoding("UTF-8");
			fileAppender.setAppend(true);
			if(LOG_LEVEL==null){
				fileAppender.setThreshold(Level.INFO); // FileAppender日志级别为INFO
			}
			else{
				fileAppender.setThreshold(LOG_LEVEL);
			}
			fileAppender.activateOptions();
			logger.addAppender(fileAppender);
		}

		return logger;
	}
	
	/**
	 * 创建Logger实例（仅输出到控制台）
	 * 
	 * @param clazz
	 *            事件日志发生类
	 * @param ifLocate
	 *            是否定位事件日志发生位置（类.方法 line）
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, boolean ifLocate) {
		return getLogger(clazz, true, false, null, null, ifLocate, 8);
	}
	
	/**
	 * 创建Logger实例（仅输出到文件）
	 * 
	 * @param clazz
	 *            事件日志发生类
	 * @param logFilePath
	 *            日志文件地址（路径分割使用“/”）
	 * @param logFileName
	 *            日志文件名称（名称后面会自动增加日期:test 2015-06-26.log ）注意无需'.log'后缀名
	 * @param ifLocate
	 *            是否定位事件日志发生位置（类.方法 line）
	 * @return Logger
	 */
	public static Logger getLogger(Class<?> clazz, String logFilePath, String logFileName,boolean ifLocate) {
		return getLogger(clazz, false, true, logFilePath, logFileName, ifLocate, 8);
	}
	
	/**
	 * 测试
	 * @param args
	 */
	public static void main(String[] args) {
		//Logger log = LoggerUtil.getLogger(TestUtils.class, true);
		
		//Logger log = LoggerUtil.getLogger(TestUtils.class, "D:/logs/LoggerUtil/","test", true);
		
		Logger log = LoggerUtil.getLogger(LoggerUtil.class, true, true, "D:/logs/LoggerUtil/", "test", true, 0);
		
		/**  ALL<DEBUG<INFO<WARN<ERROR<FATAL<OFF  */
		
		log.debug("我是测试  LoggerUtil  的debug");
		log.info("我是测试  LoggerUtil  的info");
		log.warn("我是测试  LoggerUtil  的warn");
		log.error("我是测试  LoggerUtil  的error");
		log.fatal("我是测试  LoggerUtil  的fatal");
		
		log.error("出错了", new Exception());
	}
}

