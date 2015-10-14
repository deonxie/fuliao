/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package fuliao.fuliaozhijia.data;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.common.collect.Maps;

import cn.gd.thinkjoy.modules.security.utils.Digests;
import cn.gd.thinkjoy.modules.utils.Encodes;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.util.CoreHttpClient;
import fuliao.fuliaozhijia.core.util.ImageUtil;
import fuliao.fuliaozhijia.core.util.SystemProperties;
import fuliao.fuliaozhijia.weixin.util.WeixinAccessUtil;
import fuliao.fuliaozhijia.weixin.util.WeixinMenuUtil;


public class UserData {
	static String baseUrl = "https://api.weixin.qq.com/";

	public static void main(String[] args) {
//		url();
		WeixinAccessUtil.getAccessToken();
	        byte[] salt = Digests.generateSalt(10);
	       System.out.println(Encodes.encodeHex(salt));

	        byte[] hashPassword = Digests.sha1("123".getBytes(), salt, 10);
	       System.out.println(Encodes.encodeHex(hashPassword));
//		String str="/Users/jlusoft/Desktop/testL.png";
////		ImageUtil.cutImage("/Users/jlusoft/Desktop/testL.png", 100, 100, 'L', "中大辅料",true);
////		System.out.println(str.substring(0,str.lastIndexOf(".")+1));
//		System.out.println(str.substring("/Users/jlusoft/Desktop".length()));
//		HttpClient httpClient = HttpClients.createDefault();
//		 try {
			 String url = "http://wx.qlogo.cn/mmopen/PiajxSqBRaEKPwAP4PRF495AE6aoP91nOzYjnOJofxewfMgZkQkW3xSU7DQSwwU2gdkP7Wn1mtVJjoSlIMFCr2w/0";
//			 HttpClient httpClient = HttpClients.createDefault();
//			 HttpGet get = new HttpGet(url);
//			HttpEntity en = httpClient.execute(get).getEntity();
//			System.out.println(en.getContentType().getName()+":"+en.getContentType().getValue());
//			 Content-Type:image/jpeg
//			System.out.println(UserData.class.getResource("/").getPath()); 
//			FileUtils.copyURLToFile(new URL(url), new File("/Users/jlusoft/Desktop/user.jpeg"));
//			en.getContent();
//		} catch (IOException e) {
//			
//			e.printStackTrace();
//		}
	}
	
	
	private static void cml(){
		String event = "<xml><ToUserName><![CDATA[gh_da521251eefb]]></ToUserName>"
				+ "<FromUserName><![CDATA[o1c8wt40T2yDoCqxCkBJqg8G0yW4]]></FromUserName>"
				+ "<CreateTime>1428133191</CreateTime><MsgType><![CDATA[event]]></MsgType>"
				+ "<Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey>"
				+ "<Encrypt><![CDATA[ALn/Li4lfALrfc5Z+6K1mNpAXfi1d3VRbzxiVF/jg2wPZgxofc5Kdtu/91XLkBJIQbNFmN+"
				+ "GMDC6Z0PlmLmrWHckc9KILUtp3zSFbfCNV6l5gNh3SiCeMQZPmAzmT1DXd3sxxIxlirSAT6Zb3us9zfWRTWfpxzZGT"
				+ "NAbw2/ZvdfvrTgFvbwmQPouTkUFtphy8NwEvCDdfkm+irADQhicsqKyFgPcfGuuVZf5QX/nPuOaIml5En6LLj/akF9"
				+ "o7H3XpxyTLBv+WVetk0Zd7YJgbRCfnbnTKdTwH7rHu/O7X5OMqCdGLzX2UX7UazKgoZ0zvw50bim4PTPtJt04WkfrrT"
				+ "uBWElkapAMN2yM3S5+cZETZzfAL6tTdYdLrM1W+zdwT+UjjyFJD9dkf6SBQg9Vmej5xuDOn7WmSIPedHhRtxI=]]></Encrypt></xml>";
		Map map;
		try {
			map = deailXml(event);
			System.out.println(map.size());
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private static Map<String, String> deailXml(String xml) throws DocumentException {
		Map<String, String> mesage = Maps.newHashMap();
		Document doc = DocumentHelper.parseText(xml); // 将字符串转为XML
		Element root = doc.getRootElement(); // 获取根节点
		@SuppressWarnings("unchecked")
		List<Element> elementList = root.elements(); 
		for (Element e : elementList){
			mesage.put(e.getName(), e.getText());
		}
		return mesage;
	}
	
	public static void url(){
//		String s = "/static/**";
//		s = s.replace("**", "[0-9a-zA-z/.-]{0,}").replace("*", "[0-9a-zA-z.-]+")+"$)|";
//		System.out.println(s);
//		Pattern p = Pattern.compile("^/[0-9a-zA-z/.-]{0,}/login$)|(^/static/[0-9a-zA-z/.-]{0,} $)");
//		System.out.println(p.matcher("/static/bootstrap/3.3.2/css/bootstrap.min.css").find());
		
		System.out.println("<![CDATA[]]".substring(9, "<![CDATA[]]".length()-2));
	}
	
	public static String[] create(){
		byte[] salt = Digests.generateSalt(10);
        String strSalt = Encodes.encodeHex(salt);

        byte[] hashPassword = Digests.sha1("12 3".getBytes(), salt, 10);
        String password = Encodes.encodeHex(hashPassword);
        return new String[]{strSalt,password};
	}
	
	public static void randomNewUser() {
		String[] sp = create();
		System.out.println(sp[0]);
		System.out.println(sp[1]);
		byte[] salt = Encodes.decodeHex(sp[0]);
		byte[] hashPassword = Digests.sha1("12 3".getBytes(), salt, 10);
		String pwd = Encodes.encodeHex(hashPassword);
		System.out.println(pwd.equals(sp[1]));
	}
	public static String download(String mediaId,String dir){
//		http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
		URL source;
		try {
			source = new URL("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
					+ "oPaVk_NjH_TCX1fQhIVz_8DoRqE85Vx2E_sawJWQXpen5Q6HykjRnqA--6yE-y2VaQTU1f3vY5K-udylcgm55igkwa--7kVQ-KyDndcylmE"
					//WeixinAccessUtil.getAccessToken()
					+"&media_id="+mediaId);
			URLConnection connection = source.openConnection();
			connection.setConnectTimeout(60*1000);
			connection.setReadTimeout(300*1000);
			InputStream input = connection.getInputStream();
			String fileType = connection.getHeaderField("Content-disposition");
			System.out.println("Content-disposition:"+fileType);
			String fileName = UUID.randomUUID().toString()+fileType.substring(fileType.lastIndexOf("."),fileType.length()-1);
			File file = new File(dir+fileName);
			FileUtils.copyInputStreamToFile(input, file);
			return fileName;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
/*

1、URL目录是基于HttpServletRequest.getContextPath()此目录设置 
2、URL可使用通配符，**代表任意子目录 
3、Shiro验证URL时，URL匹配成功便不再继续匹配查找。所以要注意配置文件中的URL顺序，尤其在使用通配符时。 

Shiro内置的FilterChain 
Filter Name	Class
anon	org.apache.shiro.web.filter.authc.AnonymousFilter
authc	org.apache.shiro.web.filter.authc.FormAuthenticationFilter
authcBasic	org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter
perms	org.apache.shiro.web.filter.authz.PermissionsAuthorizationFilter
port	org.apache.shiro.web.filter.authz.PortFilter
rest	org.apache.shiro.web.filter.authz.HttpMethodPermissionFilter
roles	org.apache.shiro.web.filter.authz.RolesAuthorizationFilter
ssl	org.apache.shiro.web.filter.authz.SslFilter
user	org.apache.shiro.web.filter.authc.UserFilter
*/