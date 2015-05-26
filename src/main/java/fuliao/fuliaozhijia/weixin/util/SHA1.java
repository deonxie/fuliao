/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 * 
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 */

// ------------------------------------------------------------------------

package fuliao.fuliaozhijia.weixin.util;

import java.security.MessageDigest;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * SHA1 class
 *
 * 计算公众平台的消息签名接口.
 */
public class SHA1 {
	private static Logger logger = LoggerFactory.getLogger(SHA1.class);
    protected final static char[] digit = {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};
    
    /**
     * 用SHA1算法生成安全签名
     * @param token 票据
     * @param timestamp 时间戳
     * @param nonce 随机字符串
     * @param encrypt 随机字符串
     * @return 安全签名
     */
    public static String getSHA1(String token, String timestamp, String nonce){
    	String[] array = new String[] { token, timestamp, nonce };
    	Arrays.sort(array);
    	
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < array.length; i++) {
    		sb.append(array[i]);
    	}
    	try {// SHA1签名生成
    		MessageDigest md = MessageDigest.getInstance("SHA-1");
    		byte[] digest = md.digest(sb.toString().getBytes());
    		return byteToStr(digest);
    	} catch (java.security.NoSuchAlgorithmException e) {
    		logger.error("SHA-1  error", e);
    	}
    	return "";
    }
    
    public static String sha1(String str){
    	try {// SHA1签名生成
    		MessageDigest md = MessageDigest.getInstance("SHA-1");
    		byte[] digest = md.digest(str.getBytes());
    		return byteToStr(digest);
    	} catch (java.security.NoSuchAlgorithmException e) {
    		logger.error("SHA-1  error", e);
    	}
    	return "";
    }
    
    /**
     * 将字节数组转换为十六进制字符串,
     * 将字节转换为十六进制字符串
     * @param digest
     * @return
     */
    private static String byteToStr(byte[] digest) {
    	StringBuffer strDigest = new StringBuffer();
    	for(int i = 0; i < digest.length; i++){
    		strDigest.append(digit[(digest[i] >>> 4) & 0X0F]).append(digit[digest[i] & 0X0F]);
    	}
    	return strDigest.toString();
    }
    
}
