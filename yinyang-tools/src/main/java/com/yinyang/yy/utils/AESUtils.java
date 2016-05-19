package com.yinyang.yy.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES加解密工具类
 * @author 作者:尹洋 E-mail:yinyang@shujutang.com
 * @date 创建时间：2015年8月25日 上午10:26:00
 * @version 1.0
 */
@SuppressWarnings("restriction")
public class AESUtils {
	
	private static final String CODE = "UTF-8";
	private static Logger logger = Logger.getLogger(AssertUtils.class);
	
	/**
	 * 通过秘钥文件对字符串进行AES加密
	 * <pre>秘钥文件已从配置文件的路径读取成为字节码</pre>
	 * @param content
	 * @param path
	 * @return 加密后的base 64 code[3n/4base64编码是取3位字节，编码后转为4为。]
	 */
	public static String encodeAesByKeyFile(String content,String path){
		try {
			byte[] readKeyFileBytes = FileUtils.readFile(path);
			logger.info("AES加解密工具类-通过秘钥文件对字符串进行AES加密-秘钥文件路径 ["+path+"]");
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			random.setSeed(readKeyFileBytes);
			kgen.init(128, random);

			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			
			byte[] resultBytes = cipher.doFinal(content.getBytes(CODE));
			if(resultBytes != null){
				logger.info("加密之后转base64码之前的原始字节码长度["+resultBytes.length+"],返回加密内容为:["+base64Encode(resultBytes)+"]");
				return base64Encode(resultBytes);
			}
		} catch (Exception e) {
			logger.error("AES加解密工具类[通过秘钥文件对字符串进行AES加密]异常,加密原始内容["+content+"]!", e.getCause());
		} 
		return null;
	}
	
	/**
	 * 通过秘钥文件对字符串进行AES解密
	 * <pre>秘钥文件已从配置文件的路径读取成为字节码</pre>
	 * @param content
	 * @param path
	 * @return 解密后的base 64 code[3n/4base64编码是取3位字节，编码后转为4为。]
	 */
	public static String decodeAesByKeyFile(String content,String path){
		try {
			byte[] readKeyFileBytes = FileUtils.readFile(path);
			logger.info("AES加解密工具类-通过秘钥文件对字符串进行AES解密-秘钥文件路径 ["+path+"]");
			
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
			random.setSeed(readKeyFileBytes);
			kgen.init(128, random);
			
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
			byte[] decryptBytes = cipher.doFinal(base64Decode(content));
			
			String result = StringUtils.isEmpty(content) ? null : new String(decryptBytes,CODE);
			logger.info("解密之后转base64码之前的原始字节码长度["+decryptBytes.length+"],返回解密内容为:["+result+"]");
			
			return result;
		} catch (Exception e) {
			logger.error("AES加解密工具类[通过秘钥文件对字符串进行AES解密]异常,解密原始内容["+content+"]!", e.getCause());
		} 
		return null;
	}
	
	/**
	 * AES加密为base 64 code
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的base 64 code[3n/4base64编码是取3位字节，编码后转为4为。]
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, String encryptKey) throws Exception {
		return base64Encode(aesEncryptToBytes(content, encryptKey));
	}
	
	/**
	 * AES加密为base 64 code
	 * @param content 待加密的内容
	 * @param encryptKeyBytes 加密密钥-字节数组(通过UTF-8方式获取)
	 * @return 加密后的base 64 code[3n/4base64编码是取3位字节，编码后转为4为。]
	 * @throws Exception
	 */
	public static String aesEncrypt(String content, byte[] encryptKeyBytes) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		random.setSeed(encryptKeyBytes);
		kgen.init(128, random);

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		
		return base64Encode(cipher.doFinal(content.getBytes(CODE)));
	}
	
	/**
	 * 将base 64 code AES解密
	 * @param encryptStr 待解密的base 64 code
	 * @param decryptKey 解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, String decryptKey) throws Exception {
		return StringUtils.isEmpty(encryptStr) ? null : aesDecryptByBytes(base64Decode(encryptStr), decryptKey);
	}
	
	/**
	 * 将base 64 code AES解密
	 * @param encryptStr 待解密的base 64 code
	 * @param decryptKeyBytes 解密密钥-字节数组(通过UTF-8方式获取)
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static String aesDecrypt(String encryptStr, byte[] decryptKeyBytes) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		random.setSeed(decryptKeyBytes);
		kgen.init(128, random);
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(base64Decode(encryptStr));
		
		return StringUtils.isEmpty(encryptStr) ? null : new String(decryptBytes);
	}
	
	/**
	 * AES加密
	 * @param content 待加密的内容
	 * @param encryptKey 加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	private static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		random.setSeed(encryptKey.getBytes(CODE));
		kgen.init(128, random);

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		
		return cipher.doFinal(content.getBytes(CODE));
	}
	
	/**
	 * AES解密
	 * @param encryptBytes 待解密的byte[]
	 * @param decryptKey 解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	private static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		SecureRandom random = SecureRandom.getInstance("SHA1PRNG", "SUN");
		random.setSeed(encryptBytes);
		kgen.init(128, random);
		
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);
		
		return new String(decryptBytes);
	}
	
	/**
	 * base 64 encode
	 * @param bytes 待编码的byte[]
	 * @return 编码后的base 64 code[3n/4base64编码是取3位字节，编码后转为4为。]
	 */
	private static String base64Encode(byte[] bytes){
		return new BASE64Encoder().encode(bytes);
	}
	
	/**
	 * base 64 decode
	 * @param base64Code 待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	private static byte[] base64Decode(String base64Code) throws Exception{
		return StringUtils.isEmpty(base64Code) ? null : new BASE64Decoder().decodeBuffer(base64Code);
	}
	
	/**
	 * 获取byte[]的md5值
	 * @param bytes byte[]
	 * @return md5
	 * @throws Exception
	 */
	private static byte[] md5(byte[] bytes) throws Exception {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes);
		
		return md.digest();
	}
	
	/**
	 * 获取字符串md5值
	 * @param msg 
	 * @return md5
	 * @throws Exception
	 */
	private static byte[] md5(String msg) throws Exception {
		return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes(CODE));
	}
	
	/** ----------------------------------   */
	
	/**
	 * 结合base64实现md5加密
	 * @param msg 待加密字符串
	 * @return 获取md5后转为base64
	 * @throws Exception
	 */
	public static String md5Encrypt(String msg) throws Exception{
		return StringUtils.isEmpty(msg) ? null : base64Encode(md5(msg));
	}
	
	/**
	 * 将byte[]转为各种进制的字符串
	 * @param bytes byte[]
	 * @param radix 可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix){
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}
	
	public static void test() throws Exception{
		String content = "420125198911023596";//"";//"我shujutang你";
		System.out.println("加密前：" + content);
		
		String key = "123456";
		System.out.println("加密密钥和解密密钥：" + key);
		
		String encrypt = aesEncrypt(content, key);
		System.out.println("加密后：" + encrypt +"  长度："+encrypt.length());
		byte[] enResultBytes = aesEncryptToBytes(content, key);
		System.out.println("加密后的字节码：" + enResultBytes.length);
		
		String decrypt = aesDecrypt(encrypt, key);
		System.out.println("解密后：" + decrypt +"  长度："+decrypt.length());
		
		
		System.out.println("-------------------");
		String decrypt2 = aesDecrypt("wU9BZKdo83swm2XfOrdkeuiz2lT4FsDIcdOnFR9B66c=", key);
		System.out.println("解密后2：" + decrypt2 +"  长度："+decrypt2.length());
	}
	
	public static void main(String[] args) throws Exception {
		
		System.out.println("---------------test------------------");
		test();
		System.out.println("---------------test------------------");
		String filePath= "";
		
		byte[] readFileBytes = FileUtils.readFile(filePath);
		System.out.println(readFileBytes.length);
		System.out.println("key转字符串:"+base64Encode(readFileBytes));
		
		String content = "420125198911023596";//"";//"我shujutang你";
		System.out.println("加密前：" + content);
		
		System.out.println("加密密钥和解密密钥：" + filePath);
		
		String encrypt = aesEncrypt(content, readFileBytes);
		System.out.println("加密后：" + encrypt +"  长度："+encrypt.length());
		
		String decrypt = aesDecrypt(encrypt, readFileBytes);
		System.out.println("解密后：" + decrypt +"  长度："+decrypt.length());
		
		
		System.out.println("-------------------");
		String decrypt2 = aesDecrypt("OKJr5gTpbGKdF+oBH0ljKIGb0XPwt/ECk0XOocH9LO0=", readFileBytes);
		System.out.println("解密后2：" + decrypt2 +"  长度："+decrypt2.length());
		
	}
	
}
