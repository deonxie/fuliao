package fuliao.fuliaozhijia.weixin.util;

import java.util.List;
import java.util.Map;
import java.util.Vector;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveEventMsg;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMediaMsg;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMsg;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveEventMsgService;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveMediaMsgService;
import fuliao.fuliaozhijia.weixin.service.WeixinReceiveMsgService;

public class WeixinMsgUtilJob {
	private static Logger logger = LoggerFactory.getLogger(WeixinMsgUtilJob.class);
	
	public final static java.util.Vector<String> msgXmls = new Vector<String>(50);
	@Autowired
	@Qualifier("weixinReceiveMsgService")
	private WeixinReceiveMsgService textSer;
	@Autowired
	@Qualifier("weixinReceiveMediaMsgService")
	private WeixinReceiveMediaMsgService mediaSer;
	@Autowired
	@Qualifier("weixinReceiveEventMsgService")
	private WeixinReceiveEventMsgService eventSer;

	
	public void deailWeixinMsg(){
		Map<String, String> map = null;
		String xml = null;
		for(int i=0 ;i<50;i++){
			try{
				if(msgXmls.size()>0){
					xml = msgXmls.remove(0);
					if(null!= xml)
						map = deailXml(xml);
				}else{
					break;
				}
			}catch(Exception e){
				logger.error("消息document解析异常", e);
				continue;
			}
			if(null != map){
				if(WeixinMsgType.TEXT.type.equals(map.get("MsgType"))
					|| WeixinMsgType.LOCATION.type.equals(map.get("MsgType"))
					|| WeixinMsgType.LINK.type.equals(map.get("MsgType"))){
					textSer.save(new WeixinReceiveMsg(map));
				}else if(WeixinMsgType.IMAGE.type.equals(map.get("MsgType"))
					|| WeixinMsgType.VOICE.type.equals(map.get("MsgType"))
					|| WeixinMsgType.VIDEO.type.equals(map.get("MsgType"))
					|| WeixinMsgType.SHORTVIDEO.type.equals(map.get("MsgType"))){
					mediaSer.save(new WeixinReceiveMediaMsg(map));
				}else if(WeixinMsgType.EVENT.type.equals(map.get("MsgType"))){
					eventSer.save(new WeixinReceiveEventMsg(map));
				}
				logger.info("解析成功，"+xml);
			}
		}
	}
	
	private Map<String, String> deailXml(String xml) throws DocumentException {
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
}
