package com.yinyang.yy.utils;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * DES加解密工具类
 * 
 * @Title: DESUtils.java
 * @Package com.yinyang.yy.utils
 * @author yinyang@dhgate.com
 * @date 2016年5月14日 下午3:32:32
 * @version V1.0
 */
public class DESUtils {
	/**
	 * dklink项目.net中的原始加密key,通过md5加密后取前8位用作DES加解密的密钥
	 */
	private static final String KEY = "MATICSOFT";
	/** 加密、解密key. */
	private static String PASSWORD_CRYPT_KEY = "";

	/** 加密算法 */
	private final static String ALGORITHM = "DES/CBC/PKCS5Padding";
	static {
		try {
			setPASSWORD_CRYPT_KEY(MD5Utils.toMD5(KEY).substring(0, 8));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 对数据进行DES加密.
	 * 
	 * @param data
	 *            待进行DES加密的数据
	 * @return 返回经过DES加密后的数据
	 * @throws Exception
	 */

	public final static String decrypt(String data) throws Exception {
		return new String(decrypt(hex2byte(data.getBytes()), PASSWORD_CRYPT_KEY.getBytes()));
	}

	/**
	 * 
	 * 对用DES加密过的数据进行解密.
	 * 
	 * @param data
	 *            DES加密数据
	 * @return 返回解密后的数据
	 * @throws Exception
	 */

	public final static String encrypt(String data) throws Exception {
		return byte2hex(encrypt(data.getBytes(), PASSWORD_CRYPT_KEY.getBytes()));
	}

	/**
	 * 
	 * 用指定的key对数据进行DES加密.
	 * 
	 * @param data
	 *            待加密的数据
	 * @param key
	 *            DES加密的key
	 * @return 返回DES加密后的数据
	 * @throws Exception
	 */

	private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
		// 从原始密匙数据创建DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec转换成 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		//IV向量
		IvParameterSpec iv = new IvParameterSpec(key);
		// Cipher对象实际完成加密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.ENCRYPT_MODE, securekey, iv);
		// 获取数据并加密， 正式执行加密操作
		return cipher.doFinal(data);
	}

	/**
	 * 用指定的key对数据进行DES解密.
	 * @param data
	 *            待解密的数据
	 * @param key
	 *            DES解密的key
	 * @return 返回DES解密后的数据
	 * @throws Exception
	 */

	private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
		// 从原始密匙数据创建一个DESKeySpec对象
		DESKeySpec dks = new DESKeySpec(key);
		// 创建一个密匙工厂，然后用它把DESKeySpec对象转换成 一个SecretKey对象
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey securekey = keyFactory.generateSecret(dks);
		// 向量
		IvParameterSpec iv = new IvParameterSpec(key);
		// Cipher对象实际完成解密操作
		Cipher cipher = Cipher.getInstance(ALGORITHM);
		// 用密匙初始化Cipher对象
		cipher.init(Cipher.DECRYPT_MODE, securekey, iv);
		// 现在，获取数据并解密
		// 正式执行解密操作
		return cipher.doFinal(data);

	}

	public static byte[] hex2byte(byte[] b) {
		if ((b.length % 2) != 0)
			throw new IllegalArgumentException("长度不是偶数");
		byte[] b2 = new byte[b.length / 2];
		for (int n = 0; n < b.length; n += 2) {
			String item = new String(b, n, 2);
			b2[n / 2] = (byte) Integer.parseInt(item, 16);
		}
		return b2;
	}

	/**
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
		}
		return hs.toUpperCase();
	}

	public static String getPASSWORD_CRYPT_KEY() {
		return PASSWORD_CRYPT_KEY;
	}

	public static void setPASSWORD_CRYPT_KEY(String pASSWORD_CRYPT_KEY) {
		PASSWORD_CRYPT_KEY = pASSWORD_CRYPT_KEY;
	}

	public static void main(String[] args) throws Exception {
		String value = "123321";
		String jiami = java.net.URLEncoder.encode(value, "utf-8").toLowerCase();

		System.out.println("加密数据:" + jiami);
		String a = encrypt("syyyy0006");

		System.out.println("加密后的数据为:" + a);
		String b = decrypt(a);
		System.out.println("解密后的数据:" + b);
	}

}
