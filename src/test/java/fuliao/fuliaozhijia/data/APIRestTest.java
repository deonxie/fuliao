package fuliao.fuliaozhijia.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.io.input.ReaderInputStream;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.jboss.netty.handler.codec.http.HttpResponseStatus;
import org.junit.Test;

import com.google.common.collect.Lists;

import fuliao.fuliaozhijia.weixin.util.WeixinSendMsgUtil;

public class APIRestTest {

	public static void main(String[] args) {
		
//		WeixinAccessUtil.getAccessToken();
//		String re = WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.sendTest(),null);
		String re = WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.welcomMsg("o1c8wt40T2yDoCqxCkBJqg8G0yW4"),null);
		System.out.println(re);
		System.out.println(WeixinSendMsgUtil.welcomMsg("o1c8wt40T2yDoCqxCkBJqg8G0yW4"));
		
	}
	@Test
	public void sendWeixinMsg(){
		String result = null;
		try {
			 HttpClient httpClient = HttpClients.createDefault();
			 HttpPost postMethod = new HttpPost("http://localhost:8081/fuliaozhijia/weixin/msg/?"
			 		+ "signature=291ee11adb835e762ea0ee2c6c300bd0e1f64064&echostr="
			 		+ "&nonce=942403834&timestamp=1428129514");
			 //event
			 String event = "<xml><ToUserName><![CDATA[gh_da521251eefb]]></ToUserName>"
			 		+ "<FromUserName><![CDATA[o1c8wt40T2yDoCqxCkBJqg8G0yW4]]></FromUserName>"
			 		+ "<CreateTime>1428133191</CreateTime><MsgType><![CDATA[event]]></MsgType>"
			 		+ "<Event><![CDATA[subscribe]]></Event><EventKey><![CDATA[]]></EventKey>"
			 		+ "<Encrypt><![CDATA[ALn/Li4lfALrfc5Z+6K1mNpAXfi1d3VRbzxiVF/jg2wPZgxofc5Kdtu/91XLkBJIQbNFmN+"
			 		+ "GMDC6Z0PlmLmrWHckc9KILUtp3zSFbfCNV6l5gNh3SiCeMQZPmAzmT1DXd3sxxIxlirSAT6Zb3us9zfWRTWfpxzZGT"
			 		+ "NAbw2/ZvdfvrTgFvbwmQPouTkUFtphy8NwEvCDdfkm+irADQhicsqKyFgPcfGuuVZf5QX/nPuOaIml5En6LLj/akF9"
			 		+ "o7H3XpxyTLBv+WVetk0Zd7YJgbRCfnbnTKdTwH7rHu/O7X5OMqCdGLzX2UX7UazKgoZ0zvw50bim4PTPtJt04WkfrrT"
			 		+ "uBWElkapAMN2yM3S5+cZETZzfAL6tTdYdLrM1W+zdwT+UjjyFJD9dkf6SBQg9Vmej5xuDOn7WmSIPedHhRtxI=]]></Encrypt></xml>";
			 //text msg
			String str = "<xml><ToUserName>gh_da521251eefb</ToUserName>"
			 		+ "<FromUserName>o1c8wt40T2yDoCqxCkBJqg8G0yW4</FromUserName>"
			 		+ "<CreateTime>1348831860</CreateTime><MsgType>text</MsgType>"
			 		+ "<Content>客服注册</Content><MsgId>1234567890123456</MsgId></xml>";
			
			 InputStream input =new ReaderInputStream(new  StringReader(str));
			postMethod.setEntity(new InputStreamEntity(input));
			 
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
	
}
