package fuliao.fuliaozhijia.weixin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;

/**
 * 微信用户信息实体
 * @author DEON
 * {"subscribe": 1,"openid": "o1c8wt40T2yDoCqxCkBJqg8G0yW4", "nickname": "deon","sex": 1,"language": "zh_CN", "city": "珠海", 
 *  "province": "广东","country": "中国","headimgurl": "http://wx.qlogo.cn/mmopen//0", "subscribe_time": 1426392468,"remark": ""}
 */
@Entity(name="WeixinUser")
@Table(name="Weixin_User")
public class WeixinUser extends AbstractStringId implements Serializable{
	private static final long serialVersionUID = 1L;
	public static final int SUBSCRIBE = 1;
	public static final int UNSUBSCRIBE = 0;
	/**类型 1订阅*/
	private int subscribe;
	/**用户订阅后微信的标示符*/
	private String openid;
	/**微信平台用户昵称*/
	private String nickName;
	/**用户性别 1男*/
	private int sex;
	/**用户语言*/
	private String language;
	/**用户所在城市*/
	private String city;
	/**用户所在省*/
	private String province;
	/**用户所在国家*/
	private String country;
	/**用户头像图片url*/
	private String headimgurl;
	/**用户关注时间*/
	private long subscribe_time;
	/**用户备注名称*/
	private String remark;
	
	/**以上信息由微信提供， 微信绑定的 平台用户登录手机号，*/
	private String loginName;
	/**用户分组*/
	private WeixinUserGroup userGroup;
	
	/**
	 * @return the subscribe
	 */
	public int getSubscribe() {
		return subscribe;
	}
	/**
	 * @param subscribe the subscribe to set
	 */
	public void setSubscribe(int subscribe) {
		this.subscribe = subscribe;
	}
	/**
	 * @return the openid
	 */
	@Column(unique=true)
	public String getOpenid() {
		return openid;
	}
	/**
	 * @param openid the openid to set
	 */
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	/**
	 * @return the nickName
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * @param nickName the nickName to set
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}
	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}
	/**
	 * @return the language
	 */
	public String getLanguage() {
		return language;
	}
	/**
	 * @param language the language to set
	 */
	public void setLanguage(String language) {
		this.language = language;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the province
	 */
	public String getProvince() {
		return province;
	}
	/**
	 * @param province the province to set
	 */
	public void setProvince(String province) {
		this.province = province;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the headimgurl
	 */
	public String getHeadimgurl() {
		return headimgurl;
	}
	/**
	 * @param headimgurl the headimgurl to set
	 */
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	/**
	 * @return the subscribe_time
	 */
	public long getSubscribe_time() {
		return subscribe_time;
	}
	/**
	 * @param subscribe_time the subscribe_time to set
	 */
	public void setSubscribe_time(long subscribe_time) {
		this.subscribe_time = subscribe_time;
	}
	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return the userGroup
	 */
	@ManyToOne
	public WeixinUserGroup getUserGroup() {
		return userGroup;
	}
	/**
	 * @param userGroup the userGroup to set
	 */
	public void setUserGroup(WeixinUserGroup userGroup) {
		this.userGroup = userGroup;
	}
	/**
	 * @return the loginName
	 */
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param loginName the loginName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
}
