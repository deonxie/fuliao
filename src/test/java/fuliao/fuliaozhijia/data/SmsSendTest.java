package fuliao.fuliaozhijia.data;

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
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import cn.gd.thinkjoy.modules.utils.Encodes;

import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.core.util.SendSMSUtil;

public class SmsSendTest {

	public static void main(String[] args) {
//		SendSMS.sms("新的订单已处理，请登录平台处理", "15015906765");
//		System.out.println(createSig());
//		System.out.println(auth());
		String js = "{\"resp\":{\"respCode\":\"000000\",\"failure\":1,\"templateSMS\":{\"createDate\":20140623185016,"
				+ "\"smsId\":\"f96f79240e372587e9284cd580d8f953\"}}}";
		try {
		JSONObject json = new JSONObject(js);
			System.out.println(json.getJSONObject("resp").get("respCode"));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Test
	public void sendSms(){
		String url = "https://api.ucpaas.com/2014-06-30/Accounts/9ac67866d5e8e2de0f753081eaca70ee/Messages/templateSMS?sig=1DF47BE4AF84CB5F75AAFACAF0EB84ED";
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost(url);
			 Map<String, Object> param = Maps.newHashMap();
				   
			 param.put("appId", "13f42800e7d247b4bc4f9d4be408e3c7");
			 param.put("templateId", "5186");
			 param.put("to", "15015906765");
			 param.put("param", "4543");
			 Map<String, Object> params = Maps.newHashMap();
			 params.put("templateSMS", param);
			 String sms = new JSONObject(params).toString();
			 postMethod.addHeader("Content-Type", "application/json;charset=utf-8");
			 postMethod.addHeader("Accept", "application/json");
			postMethod.addHeader("Authorization", "OWFjNjc4NjZkNWU4ZTJkZTBmNzUzMDgxZWFjYTcwZWU6MjAxNTA0MTIxMzIyNTQ=");
//			b94b684b901fcb02d24e55ae33924919:=
			postMethod.setEntity(new StringEntity(sms,"UTF-8")); // 将参
			HttpResponse response = httpClient.execute(postMethod); // 执行POST方法
			if(response.getStatusLine().getStatusCode()==HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+""; // 获取响应码
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	public static String createSig(){
		
		String password ="9ac67866d5e8e2de0f753081eaca70ee"+"b94b684b901fcb02d24e55ae33924919"
				+"20150412140000";//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try {
			return new EncryptUtil().md5Digest(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	public static String auth(){
		String auth ="9ac67866d5e8e2de0f753081eaca70ee:"+"20150412140000";//new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		try {
			System.out.println(Encodes.encodeBase64(auth.getBytes()));
			return new EncryptUtil().base64Encoder(auth);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}
//	bbf2ab1a3a
//	27a127213a
//	7b51d137fb
//	0c
}
