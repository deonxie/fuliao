package fuliao.fuliaozhijia.weixin.controller.rest;



import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.util.SystemProperties;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveEventMsg;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMediaMsg;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMsg;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveEventMsgService;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveMediaMsgService;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveMsgService;
import fuliao.fuliaozhijia.weixin.util.SHA1;
import fuliao.fuliaozhijia.weixin.util.WeixinMsgType;


@RestController
@RequestMapping("/weixin/msg/")
public class WeixinMsgController{
	private static Logger logger = LoggerFactory.getLogger(WeixinMsgController.class);
	public static boolean check = false;
	@Autowired
	@Qualifier("weixinReceiveMsgService")
	private WeixinReceiveMsgService textSer;
	@Autowired
	@Qualifier("weixinReceiveMediaMsgService")
	private WeixinReceiveMediaMsgService mediaSer;
	@Autowired
	@Qualifier("weixinReceiveEventMsgService")
	private WeixinReceiveEventMsgService eventSer;
	
	@RequestMapping(value="",produces={MediaType.APPLICATION_XML_VALUE})
	public String checkToken(HttpServletRequest request){
		String echostr = request.getParameter("echostr");
		if(!check){
			String signature = request.getParameter("signature");
			String nonce = request.getParameter("nonce");
			String timestamp =	request.getParameter("timestamp");
			String token = SystemProperties.getInstance().getValue("WeixinMsgController.checkToken.token");
			String digest = SHA1.getSHA1(token, timestamp, nonce);
			logger.info("signature="+signature+" echostr="+echostr+" nonce="+nonce
					+" timestamp="+timestamp+" digest="+digest);
			if (digest.equalsIgnoreCase(signature)){
				check =true;
				return echostr;
			}
			logger.error("weixin check fail");
		}
		return receiveMsg(request, echostr);
//		logger.info("receive msg over");
	}
	

	/**
	 * 解析微信平台转发过来的消息
	 * @param request
	 */
	private final String receiveMsg(HttpServletRequest request,String echostr){
	    InputStream inputStream = null;
		try {
			inputStream = request.getInputStream();
			if(inputStream != null){
				
				StringBuffer xml = new StringBuffer();
				byte[] data = new byte[10240];
				int read = 0;
				while((read=inputStream.read(data))>-1)
					xml.append(new String(data,0,read,"UTF-8"));
				if(xml.length()>0){
					logger.info("msg:"+xml.toString());
					Map<String, String> mesage = Maps.newHashMap();
					Document doc = DocumentHelper.parseText(xml.toString()); // 将字符串转为XML
					Element root = doc.getRootElement(); // 获取根节点
					@SuppressWarnings("unchecked")
					List<Element> elementList = root.elements(); 
					for (Element e : elementList){
						mesage.put(e.getName(), e.getText());
					}
					delailMsg(mesage);
					if(!WeixinMsgType.EVENT.type.equals(mesage.get("MsgType"))){
						xml = new StringBuffer("<xml><ToUserName><![CDATA[");
						xml.append(mesage.get("FromUserName")).append("]]></ToUserName><FromUserName><![CDATA[");
						xml.append(mesage.get("ToUserName")).append("]]></FromUserName><CreateTime>");
						xml.append(mesage.get("CreateTime")).
						append("</CreateTime><MsgType><![CDATA[transfer_customer_service]]></MsgType></xml>");
						return xml.toString();
					}
				}
			}
		} catch (IOException e) {
			logger.error("系统读取微信推送信息异常", e);
		} catch (DocumentException e) {
			logger.error("xml 解析异常", e);
		}  
		if(inputStream != null){
		    try {
				inputStream.close();
			} catch (IOException e) {
				logger.error("系统读取微信推送信息,关闭输入流异常", e);
			}  
		}
		return echostr;
	}
	
	private void delailMsg(final Map<String, String> mesage){
		new Thread(){
			@Override
			public void run() {
				if(null != mesage){
					if(WeixinMsgType.TEXT.type.equals(mesage.get("MsgType"))
						|| WeixinMsgType.LOCATION.type.equals(mesage.get("MsgType"))
						|| WeixinMsgType.LINK.type.equals(mesage.get("MsgType"))){
						textSer.save(new WeixinReceiveMsg(mesage));
					}else if(WeixinMsgType.IMAGE.type.equals(mesage.get("MsgType"))
						|| WeixinMsgType.VOICE.type.equals(mesage.get("MsgType"))
						|| WeixinMsgType.VIDEO.type.equals(mesage.get("MsgType"))
						|| WeixinMsgType.SHORTVIDEO.type.equals(mesage.get("MsgType"))){
						mediaSer.save(new WeixinReceiveMediaMsg(mesage));
					}else if(WeixinMsgType.EVENT.type.equals(mesage.get("MsgType"))){
						eventSer.save(new WeixinReceiveEventMsg(mesage));
					}
				}
			}
		}.start();
	}
}
