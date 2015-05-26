package fuliao.fuliaozhijia.weixin.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;
import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.entity.UserEntity;

/**
 * 商家信息
 * @author Deon
 *
 */
@Entity(name="weixinShops")
@Table(name="weixin_Shops")
public class Shops extends AbstractStringId {
	/**店铺名称*/
	private String name;
	/**主营业务*/
	private String mainBusines;
	/**店铺简介*/
	private String descript;
	/**联系电话*/
	private String telNum;
	/**地址*/
	private String address;
	/**状态 0未认证，1认证通过，2认证失败*/
	private int status;
	/**认证人**/
	private String authcUserName;
	/**认证失败原因*/
	private String failReason;
	/**创建时间*/
	private Date createTime;
	/**被收藏次数*/
	private long sotreNum;
	/**店铺登录用户*/
	private UserEntity user;
	/**店铺logo*/
	private UploadPictureEntity logo;
	/**店铺商品*/
	private List<Product> products;
	/**验证图片1*/
	private UploadPictureEntity verify1;
	/**验证图片2*/
	private UploadPictureEntity verify2;
	/**验证图片3*/
	private UploadPictureEntity verify3;
	/**状态 0未认证*/
	public static final int STATUS_NO_VERIFY = 0;
	/**状态 1认证通过*/
	public static final int STATUS_VERIFY_PASS = 1;
	/**状态 2认证失败*/
	public static final int STATUS_VERIFY_FAIL = 2;
	/**
	 * @return the {@link #name}
	 */
	@Column(nullable=false)
	public String getName() {
		return name;
	}
	/**
	 * @param name the {@link #name} to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the {@link #mainBusines}
	 */
	@Column(nullable=false)
	public String getMainBusines() {
		return mainBusines;
	}
	/**
	 * @param mainBusines the {@link #mainBusines} to set
	 */
	public void setMainBusines(String mainBusines) {
		this.mainBusines = mainBusines;
	}
	/**
	 * @return the {@link #descript}
	 */
	@Column(columnDefinition="text")
	public String getDescript() {
		return descript;
	}
	/**
	 * @param descript the {@link #descript} to set
	 */
	public void setDescript(String descript) {
		this.descript = descript;
	}
	/**
	 * @return the {@link #address}
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the {@link #address} to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the {@link #status}
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the {@link #status} to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the {@link #user}
	 */
	@OneToOne
	@JoinColumn(name="coreuser")
	public UserEntity getUser() {
		return user;
	}
	/**
	 * @param user the {@link #user} to set
	 */
	public void setUser(UserEntity user) {
		this.user = user;
	}
	/**
	 * @return the {@link #logo}
	 */
	@OneToOne
	public UploadPictureEntity getLogo() {
		return logo;
	}
	/**
	 * @param logo the {@link #logo} to set
	 */
	public void setLogo(UploadPictureEntity logo) {
		this.logo = logo;
	}
	/**
	 * @return the {@link #products}
	 */
	@OneToMany(mappedBy="shops")
	public List<Product> getProducts() {
		return products;
	}
	/**
	 * @param products the {@link #products} to set
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	/**
	 * @return the {@link #createTime}
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the {@link #createTime} to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the {@link #sotreNum}
	 */
	public long getSotreNum() {
		return sotreNum;
	}
	/**
	 * @param sotreNum the {@link #sotreNum} to set
	 */
	public void setSotreNum(long sotreNum) {
		this.sotreNum = sotreNum;
	}
	/**
	 * @return the {@link #verify1}
	 */
	@OneToOne
	public UploadPictureEntity getVerify1() {
		return verify1;
	}
	/**
	 * @param verify1 the {@link #verify1} to set
	 */
	public void setVerify1(UploadPictureEntity verify1) {
		this.verify1 = verify1;
	}
	/**
	 * @return the {@link #verify2}
	 */
	@OneToOne
	public UploadPictureEntity getVerify2() {
		return verify2;
	}
	/**
	 * @param verify2 the {@link #verify2} to set
	 */
	public void setVerify2(UploadPictureEntity verify2) {
		this.verify2 = verify2;
	}
	/**
	 * @return the {@link #verify3}
	 */
	@OneToOne
	public UploadPictureEntity getVerify3() {
		return verify3;
	}
	/**
	 * @param verify3 the {@link #verify3} to set
	 */
	public void setVerify3(UploadPictureEntity verify3) {
		this.verify3 = verify3;
	}
	/**
	 * @return the {@link #telNum}
	 */
	public String getTelNum() {
		return telNum;
	}
	/**
	 * @param telNum the {@link #telNum} to set
	 */
	public void setTelNum(String telNum) {
		this.telNum = telNum;
	}
	/**
	 * @return the {@link #authcUserName}
	 */
	public String getAuthcUserName() {
		return authcUserName;
	}
	/**
	 * @param authcUserName the {@link #authcUserName} to set
	 */
	public void setAuthcUserName(String authcUserName) {
		this.authcUserName = authcUserName;
	}
	/**
	 * @return the {@link #failReason}
	 */
	@Column(columnDefinition="text")
	public String getFailReason() {
		return failReason;
	}
	/**
	 * @param failReason the {@link #failReason} to set
	 */
	public void setFailReason(String failReason) {
		this.failReason = failReason;
	}
	
}
