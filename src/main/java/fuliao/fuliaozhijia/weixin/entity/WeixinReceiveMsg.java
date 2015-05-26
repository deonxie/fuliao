package fuliao.fuliaozhijia.weixin.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;

/**
 * 微信推送 文本类型 消息实体
 * @author DEON
 *  <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 *  <FromUserName><![CDATA[fromUser]]></FromUserName> 
 *  <CreateTime>1348831860</CreateTime>
 *  <MsgType><![CDATA[text]]></MsgType>
 *  <Content><![CDATA[this is a test]]></Content>
 *  <MsgId>1234567890123456</MsgId></xml>
 */
@Entity(name="WeixinReceiveMsg")
@Table(name="Weixin_ReceiveMsg")
public class WeixinReceiveMsg extends AbstractStringId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**开发者微信号*/
	private String toUserName;
	/**发送方帐号（一个OpenID）*/
	private String fromUserName;
	/**消息创建时间 整型*/
	private long createTime;
	/**消息类型 text 文本消息、location 位置、link 公众平台官网链接**/
	private String msgType;
	/**微信推送的消息id**/
	private String msgId;
	
	/**文本 消息内容*/
	private String content;

	/**位置消息 地理位置维度 */
	private double location_X;
	/**位置消息 地理位置经度 */
	private double location_Y;
	/**地图缩放大小 */
	private int scale;
	/**地理位置信息 */
	private String label;//位置信息
	 
	/**链接消息 消息标题 */
	private String title;
	/**链接消息 消息描述  */ 
	private String description;
	/**链接消息 消息链接 */
	private String url;
	
	public WeixinReceiveMsg() {
	}
	
	/**
	 * 按参数初始化实体
	 * @param map
	 */
	public WeixinReceiveMsg(Map<String,String> map){
		toUserName = StringUtils.trimToNull(map.get("ToUserName"));
		fromUserName = StringUtils.trimToNull(map.get("FromUserName"));
		createTime = Long.parseLong(StringUtils.trim(map.get("CreateTime")));
		msgType = StringUtils.trimToNull(map.get("MsgType"));
		msgId = StringUtils.trimToNull(map.get("MsgId"));
		if("text".equals(msgType)){
			content = StringUtils.trimToNull(map.get("Content"));
		}else if("location".equals(msgType)){
			location_X = Double.parseDouble(StringUtils.trimToNull(map.get("Location_X")));
			location_Y = Double.parseDouble(StringUtils.trimToNull(map.get("Location_Y")));
			scale = Integer.parseInt(StringUtils.trimToNull(map.get("Scale")));
			label = StringUtils.trimToNull(map.get("Label"));
		}else if("link".equals(msgType)){
			title = StringUtils.trimToNull(map.get("Title"));
			description = StringUtils.trimToNull(map.get("Description"));
			url = StringUtils.trimToNull(map.get("Url"));
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
	 * @return the msgId
	 */
	public String getMsgId() {
		return msgId;
	}
	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	/**
	 * @return the content
	 */
	@Column(columnDefinition="text")
	public String getContent() {
		return content;
	}
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the location_X
	 */
	public double getLocation_X() {
		return location_X;
	}
	/**
	 * @param location_X the location_X to set
	 */
	public void setLocation_X(double location_X) {
		this.location_X = location_X;
	}
	/**
	 * @return the location_Y
	 */
	public double getLocation_Y() {
		return location_Y;
	}
	/**
	 * @param location_Y the location_Y to set
	 */
	public void setLocation_Y(double location_Y) {
		this.location_Y = location_Y;
	}
	/**
	 * @return the scale
	 */
	public int getScale() {
		return scale;
	}
	/**
	 * @param scale the scale to set
	 */
	public void setScale(int scale) {
		this.scale = scale;
	}
	/**
	 * @return the label
	 */
	@Column(columnDefinition="text")
	public String getLabel() {
		return label;
	}
	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the description
	 */
	@Column(columnDefinition="text")
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
}
