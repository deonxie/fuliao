package fuliao.fuliaozhijia.weixin.util;

/**
 * 微信推送信息的类型
 * @author DEON
 *
 */
public enum WeixinMsgType {
	TEXT("text"),LOCATION("location"),LINK("link"),
	IMAGE("image"), VOICE("voice"), VIDEO("video"),SHORTVIDEO("shortvideo"),
	EVENT("event");
	public String type;
	WeixinMsgType(String type){
		this.type = type;
	}
}
