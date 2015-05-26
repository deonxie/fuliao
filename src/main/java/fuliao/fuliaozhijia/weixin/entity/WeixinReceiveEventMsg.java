package fuliao.fuliaozhijia.weixin.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;

/**
 * 微信推送 事件类型 实体
 * @author DEON
 *  <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 *  <FromUserName><![CDATA[fromUser]]></FromUserName> 
 *  <CreateTime>1348831860</CreateTime>
 *  <MsgType><![CDATA[text]]></MsgType>
 *  <Event><![CDATA[subscribe]]></Event></xml>
 */
@Entity(name="WeixinReceiveEventMsg")
@Table(name="Weixin_ReceiveEventMsg")
public class WeixinReceiveEventMsg extends AbstractStringId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**开发者微信号*/
	private String toUserName;
	/**发送方帐号（一个OpenID）*/
	private String fromUserName;
	/**消息创建时间 整型*/
	private long createTime;
	/**消息类型 event（事件） **/
	private String msgType;
	/**微信推送的事件类型，
	 * subscribe(订阅)、unsubscribe(取消订阅)
	 * VIEW（点击菜单跳转链接）、CLICK（点击菜单拉取消息时的事件推送 ）
	 **/
	private String event ;
	/**事件KEY值*/
	private String eventKey;
	
	public WeixinReceiveEventMsg() {
	}
	
	/**
	 * 按参数初始化实体,
	 * @param map
	 */
	public WeixinReceiveEventMsg(Map<String,String> map){
		toUserName = StringUtils.trimToNull(map.get("ToUserName"));
		fromUserName = StringUtils.trimToNull(map.get("FromUserName"));
		createTime = Long.parseLong(StringUtils.trim(map.get("CreateTime")));
		msgType = StringUtils.trimToNull(map.get("MsgType"));
		if("event".equals(msgType)){
			event = StringUtils.trimToNull(map.get("Event"));
			eventKey = StringUtils.trimToNull(map.get("EventKey"));
		}
	}
	
	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}
	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}
	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}
	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return msgType;
	}
	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the event
	 */
	public String getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(String event) {
		this.event = event;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
	
	
}
