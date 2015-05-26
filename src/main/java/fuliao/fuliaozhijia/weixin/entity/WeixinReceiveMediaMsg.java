package fuliao.fuliaozhijia.weixin.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;

/**
 * 微信推送 多媒体消息（图片，声音，视频）实体
 * @author DEON
 * <xml><ToUserName><![CDATA[toUser]]></ToUserName>
 * <FromUserName><![CDATA[fromUser]]></FromUserName>
 * <CreateTime>1357290913</CreateTime>
 * <MsgType><![CDATA[shortvideo]]></MsgType>
 * <MediaId><![CDATA[media_id]]></MediaId>
 * <ThumbMediaId><![CDATA[thumb_media_id]]></ThumbMediaId>
 * <MsgId>1234567890123456</MsgId></xml>
 */
@Entity(name="WeixinReceiveMediaMsg")
@Table(name="Weixin_ReceiveMediaMsg")
public class WeixinReceiveMediaMsg extends AbstractStringId implements Serializable{
	private static final long serialVersionUID = 1L;
	
	/**开发者微信号*/
	private String toUserName;
	/**发送方帐号（一个OpenID）*/
	private String fromUserName;
	/**消息创建时间 整型*/
	private long createTime;
	/**消息类型 image 图片、voice 声音、video视频、shortvideo小视频**/
	private String msgType;
	/**媒体id，可以调用多媒体文件下载接口拉取数据 大概80字符**/
	private String mediaId;
	/**微信推送的消息id**/
	private String msgId;
	
	/** 图片消息类型的url 大概120字符*/
	private String picUrl; 
	
	/** 声音消息类型的音质类型 */
	private String format;
	
	/** 视频消息缩略图的媒体id，可以调用多媒体文件下载接口拉取数据 */
	private String thumbMediaId;
	
	public WeixinReceiveMediaMsg() {
	}
	
	/**
	 * 按参数初始化实体
	 * @param map
	 */
	public WeixinReceiveMediaMsg(Map<String,String> map){
		toUserName = StringUtils.trimToNull(map.get("ToUserName"));
		fromUserName = StringUtils.trimToNull(map.get("FromUserName"));
		createTime = Long.parseLong(StringUtils.trim(map.get("CreateTime")));
		msgType = StringUtils.trimToNull(map.get("MsgType"));
		msgId = StringUtils.trimToNull(map.get("MsgId"));
		if("image".equals(msgType)){
			picUrl = StringUtils.trimToNull(map.get("PicUrl"));
		}else if("voice".equals(msgType)){
			format = StringUtils.trimToNull(map.get("Format"));
		}else if("video".equals(msgType) || "shortvideo".equals(msgType)){
			thumbMediaId = StringUtils.trimToNull(map.get("ThumbMediaId"));
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
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}
	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	/**
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}
	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}
	/**
	 * @param format the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}
	/**
	 * @return the thumbMediaId
	 */
	public String getThumbMediaId() {
		return thumbMediaId;
	}
	/**
	 * @param thumbMediaId the thumbMediaId to set
	 */
	public void setThumbMediaId(String thumbMediaId) {
		this.thumbMediaId = thumbMediaId;
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

}
