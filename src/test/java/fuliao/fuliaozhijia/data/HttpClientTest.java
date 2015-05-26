package fuliao.fuliaozhijia.data;

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
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;

public class HttpClientTest {
	private String baseUrl;
	HttpContext context;
	public HttpClientTest(String baseUrl){
		this.baseUrl = baseUrl;
		createContext();
	}
	private void createContext(){
		CookieStore cookieStore = new BasicCookieStore();
		context = new BasicHttpContext();
		context.setAttribute(HttpClientContext.COOKIE_STORE, cookieStore);																// Entity中
	}
	
	public String  post(String url,LinkedList<BasicNameValuePair> params) {
		url  = baseUrl+url;
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost(url);
			 if(null != params)
				 postMethod.setEntity(new UrlEncodedFormEntity(params, "utf-8")); // 将参数填入POST

			HttpResponse response = httpClient.execute(postMethod,context); // 执行POST方法
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
		return result;
	}

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
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
