package com.yinyang.yy.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * 
 * @Title: MessageUtil.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午4:40:51
 * @version V1.0
 */
public class MessageUtil {
	
    public static Pattern chineseP = Pattern.compile("([\u4E00-\u9FA5]|[\uFE30-\uFFA0])+?");//设成短路匹配
    public static final String CACHE_CATEGORY_KEY = "dhgatecommon";
    public static final String CACHE_OBJECTTYPE_KEY = "message";
    public static final String CACHE_PREFIX_NICKNAME = "nickORsysid_";
    public static final String CACHE_PREFIX_ORGID = "orgIdOf_";
    public static final String CACHE_PREFIX_MESSAGECOUNT = "countdto_";
    public static final String ACTION_STATE_SENDERSTAT = "senderstat";
    public static final String ACTION_STATE_RECIVESTAT = "recivestat";
    public static final String ACTION_STATE_SENDER_MARKED = "sender_marked";
    public static final String ACTION_STATE_RECIVE_MARKED = "recive_marked";
    public static final String ACTION_STATE_MC_LOCK = "mc_lock";
    public static final String ACTION_STATE_RECIVEREADED = "recivereaded";
    public static final String ACTION_STATE_SENDEREADED = "sendereaded";
    public static final String[] ACTION_STATE_LIST={};
    public static final String[] MSG_TYPE_LIST={};
    public static final String[] REPLY_TYPE_LIST={};
    public static final String[] INCREMENTAL_TYPE_LIST={};
    public static final String[] MUSTSYSTEM_TYPE_LIST={};
    public static final String[] BATCH_NOTICE_TYPE_LIST={};
    public static final int BATCH_SEND_MESSAGE_NUM = 20;
    public static final String OPERATE_ADD = "add";
	
	//类型是否存在
	public static boolean isMsgTypeValidate(String msgtype){
		return MessageUtil.isInArray(msgtype, MSG_TYPE_LIST);
	}
	//是否可以回复操作
	public static boolean isReply(String msgtype){
		return MessageUtil.isInArray(msgtype, REPLY_TYPE_LIST);
	}
	//是否增量操作 
	public static boolean isIncremental(String msgtype){
		return MessageUtil.isInArray(msgtype, INCREMENTAL_TYPE_LIST);
	}
	//状态操作是否存
	public static boolean isState(String state){
		return MessageUtil.isInArray(state, ACTION_STATE_LIST);
	}
	//是否 php平台群发站内信
	public static boolean isBatchNoticeType(String msgtype){
		return MessageUtil.isInArray(msgtype, BATCH_NOTICE_TYPE_LIST);
	}
	//是否需要强制将senderId转为SYSTEM
	public static boolean isSenderMustSystem(String msgtype){
		return MessageUtil.isInArray(msgtype, MUSTSYSTEM_TYPE_LIST);
	}
	//是否可以输入中文
//	public static boolean isChinese(String state){
//		return MessageUtil.isInArray(state, MessageSYS.ACTION_STATE_LIST);
//	}
	
	//是否匹配中文内容
	public static boolean isMatchChinese(String src) {   
	    Matcher m = chineseP.matcher(src);
	    return m.find(); 
	}  
	 /**
     * 判断是否在数组中
     * 1: 在
     * 0: 不在
     * @param object
     * @param objects
     * @return
     */
    public static boolean isInArray(Object object, Object [] objects){
    	for (int i = 0; i < objects.length; i++) {
			if (object.equals(objects[i])) {
				return true;
			}
		}
    	return false;
    }
    
//    public static void main(String[] args) {
////    	String src="中华人民共和国sdfadf";
////    	System.out.println(src.length());
//    	
//    	int Start=1;
//    	int end=1;
//    	int index =1;
//    	for(int i=1;i<5600;i+=2000){
//    		Start=index*i-1;	//开始字符位置
//    		end=Start+999;	//结束字符位置
//    		System.out.println(Start+"         "+end +"         "+index);
//    	}
//    }
}
