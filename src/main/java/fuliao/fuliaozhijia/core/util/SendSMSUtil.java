package fuliao.fuliaozhijia.core.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

import cn.gd.thinkjoy.modules.utils.Encodes;

public class SendSMSUtil {
	private static final Logger logger = LoggerFactory.getLogger(SendSMSUtil.class);
	private static final String UTF8 = "utf-8";
	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	private static String baseUrl = SystemProperties.getInstance().getValue("SendSMS.base.url");
	private static String accountSid = SystemProperties.getInstance().getValue("SendSMS.base.accountSid");
	private static String authToken = SystemProperties.getInstance().getValue("SendSMS.base.Auth.Token");
	private static String appId = SystemProperties.getInstance().getValue("SendSMS.base.appId1");
	private static String templateId1 = SystemProperties.getInstance().getValue("SendSMS.base.templateId1");
	
	/**
	 * 重新加载短信配置
	 */
	public static void reloadConfig() {
		baseUrl = SystemProperties.getInstance().getValue("SendSMS.base.url");
		accountSid = SystemProperties.getInstance().getValue("SendSMS.base.accountSid");
		authToken = SystemProperties.getInstance().getValue("SendSMS.base.Auth.Token");
		appId = SystemProperties.getInstance().getValue("SendSMS.base.appId1");
		templateId1 = SystemProperties.getInstance().getValue("SendSMS.base.templateId1");
	}
	/**
	 * 发送短信验证码
	 * @param code
	 * @param tel
	 * @return
	 */
	public static String sendCode(String code,int minute,String tel){
		return sendSMS(code+","+minute,tel,templateId1);
	}
//"5241"
	private static String sendSMS(String content,String tel,String templeId) {
		Map<String, Object> param = Maps.newHashMap();
		param.put("appId", appId);
		param.put("templateId", templeId);
		param.put("to", tel);
		param.put("param", content);
		Map<String, Object> params = Maps.newHashMap();
		params.put("templateSMS", param);
		String sms = new JSONObject(params).toString();
		HttpClient httpClient = HttpClients.createDefault();
		Date date = new Date();
		HttpPost postMethod = new HttpPost(baseUrl+accountSid+"/Messages/templateSMS?sig="+createSig(date));
		postMethod.addHeader("Content-Type", "application/json;charset=utf-8");
		postMethod.addHeader("Accept", "application/json");
		postMethod.addHeader("Authorization", createAuth(date));
		try{
			postMethod.setEntity(new StringEntity(sms,"UTF-8"));
			HttpResponse response = httpClient.execute(postMethod);
			String result;
			if(response.getStatusLine().getStatusCode()==HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+"";
			return result;
		} catch (UnsupportedEncodingException e) {
			logger.error("短信转换为utf-8编码错误",e);
		} catch (ClientProtocolException e) {
			logger.error("短信服务端不支持https 协议",e);
		} catch (IOException e) {
			logger.error("读取响应信息异常",e);
		}
		return "";
	}

	/**
	 * REST API 验证参数
	 * 
	 * @return
	 */
	public static String createSig(Date date) {
		return md5Digest(accountSid + authToken + dateFormat.format(date));

	}

	/**
	 * 创建包头验证信息
	 * 
	 * @param date
	 * @return
	 */
	public static String createAuth(Date date) {
		return base64Encoder(accountSid + ":" + dateFormat.format(date));
	}

	/**
	 * MD5数字签名 定义数字签名方法可用：MD5, SHA-1
	 * 
	 * @param src
	 * @return
	 */
	public static String md5Digest(String src) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			return byte2HexStr(md.digest(src.getBytes(UTF8)));
		} catch (NoSuchAlgorithmException e) {
			logger.error("短信签名加密不支持MD5算法", e);
		} catch (UnsupportedEncodingException e) {
			logger.error("短信签名加密不支持utf-8编码", e);
		}
		return null;
	}

	/**
	 * BASE64编码
	 */
	public static String base64Encoder(String src) {
		try {
			return Encodes.encodeBase64(src.getBytes(UTF8));
		} catch (UnsupportedEncodingException e) {
			logger.error("短信64位验证信息不支持utf-8编码", e);
		}
		return null;
	}

	/**
	 * BASE64解码
	 */
	public static String base64Decoder(String dest) {
		try {
			return new String(Encodes.decodeBase64(dest), UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.error("短信64位验证信息不支持utf-8解码", e);
		}
		return null;
	}

	/**
	 * 字节数组转化为大写16进制字符串
	 */
	private static String byte2HexStr(byte[] b) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < b.length; i++) {
			String s = Integer.toHexString(b[i] & 0xFF);
			if (s.length() == 1) {
				sb.append("0");
			}
			sb.append(s.toUpperCase());
		}
		return sb.toString();
	}
	
}
