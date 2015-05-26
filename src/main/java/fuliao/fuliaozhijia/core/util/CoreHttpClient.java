package fuliao.fuliaozhijia.core.util;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.LinkedList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.entity.FileEntity;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class CoreHttpClient {
	private static Logger logger = LoggerFactory.getLogger(CoreHttpClient.class);
	/**基础URL*/
	private String baseUrl;
	/**保存cookie等request header的上下文*/
	protected HttpContext context;
	
	public CoreHttpClient(String baseUrl){
		this.baseUrl = baseUrl;
		createContext();
	}
	
	/**
	 * 创建保存cookie等request header的上下文，用于系统session验证
	 */
	private void createContext(){
		CookieStore cookieStore = new BasicCookieStore();
		context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);																// Entity中
	}
	
	/**
	 * post方式访问，无参数时params可传null
	 * @param url
	 * @param params
	 * @return
	 */
	public String  post(String url,LinkedList<BasicNameValuePair> params) {
		url  = baseUrl+url;
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost(url);
			 if(null != params)
				 postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8"));

			HttpResponse response = httpClient.execute(postMethod,context);
			if(response.getStatusLine().getStatusCode()==HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+""; // 获取响应码
		} catch (UnsupportedEncodingException e) {
			logger.error("post访问数据不支持utf-8编码",e);
		} catch (ClientProtocolException e) {
			logger.error("post访问不支持http协议",e);
		} catch (IOException e) {
			logger.error("post访问数据传输失败",e);
		}
		logger.info(url+"  ===  "+result);
		return result;
	}
	
	/**
	 * post方式访问，参数是json字符串，无参数时可以传人null
	 * @param url
	 * @param jsonParams
	 * @return
	 */
	public String  postJson(String url,String jsonParams) {
		url  = baseUrl+url;
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost(url);
			 if(null != jsonParams){
			     postMethod.addHeader("content-type", "application/json");  
				 postMethod.setEntity(new StringEntity(jsonParams,"UTF-8")); // 将参数填入POST
			 }
			HttpResponse response = httpClient.execute(postMethod,context); // 执行POST方法
			if(response.getStatusLine().getStatusCode()==HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+""; // 获取响应码
		} catch (UnsupportedEncodingException e) {
			logger.error("post访问传入参数json数据不支持utf-8编码",e);
		} catch (ClientProtocolException e) {
			logger.error("post访问json参数，不支持http协议",e);
		} catch (IOException e) {
			logger.error("post访问json数据传输失败",e);
		}
		logger.info(url+"  ===  "+result);
		return result;
	}
	
	/**
	 * get方式访问数据，无参数时可以传入null
	 * @param url
	 * @param params
	 * @return
	 */
	public String get(String url,LinkedList<BasicNameValuePair> params){
		String result = null;
		url = baseUrl +url;
		if(null != params)
			url = url + URLEncodedUtils.format(params, "UTF-8");
		try {
			HttpClient httpClient = HttpClients.createDefault();
			HttpGet getMethod = new HttpGet(url);
			HttpResponse response = httpClient.execute(getMethod, context);

			if(response.getStatusLine().getStatusCode() == HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+""; // 获取响应码

		} catch (UnsupportedEncodingException e) {
			logger.error("get访问参数不支持utf-8编码",e);
		} catch (ClientProtocolException e) {
			logger.error("get访问不支持http协议",e);
		} catch (IOException e) {
			logger.error("get访问数据传输失败",e);
		}
		logger.info(url+"  ===  "+result);
		return result;
	}
	
	/**
	 * 上传文件
	 * @param url
	 * @param filePath
	 * @return
	 */
	public String postFile(String url, String filePath) {
		url  = baseUrl+url;
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost(url);
			 postMethod.addHeader("content-type", "multipart/form-data"); 
			 postMethod.setEntity(new FileEntity(new File(filePath)));
			HttpResponse response = httpClient.execute(postMethod,context); // 执行POST方法
			if(response.getStatusLine().getStatusCode()==HttpResponseStatus.OK.getCode())
				result = EntityUtils.toString(response.getEntity(), "utf-8");
			else
				result = response.getStatusLine().getStatusCode()+""; // 获取响应码
		} catch (ClientProtocolException e) {
			logger.error("postFile，不支持http协议",e);
		} catch (IOException e) {
			logger.error("postFile数据传输失败",e);
		}
		logger.info(url+"  ===  "+result);
		return result;
	}
	
	/**
	 * 从微信平台下载文件
	 * @param string 文件保存路径
	 * @return
	 */
	public String getFile(String string) {
		return null;
	}
	
}
