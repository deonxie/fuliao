package fuliao.fuliaozhijia.weixin.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;
import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.entity.UserEntity;


@Entity(name="weixinRequestProduct")
@Table(name="weixin_RequestProduct")
public class RequestProduct extends AbstractStringId {
	/** 求购产品名称 */
	private String name;
	/** 求购产品材质 */
	private String texture;
	/** 需求描述 */
	private String descript;
	/** 单位 */
	private String units;
	/** 需求数量 */
	private long requestNum;
	/** 产品货源类型 0现货，1样板 */
	private int model;
	/** 单价 */
	private double price;
	/** 创建时间 */
	private Date createTime;
	/** 解决时间 */
	private Date solveTime;
	/** 产品状态 0正常，1取消 2完成*/
	private int status;
	/** 产品类型 */
	private ProductType type;
	/** 所属用户 **/
	private UserEntity user;
	/**产品图集 */
	private UploadPictureEntity image1;
	/**产品图集 */
	private UploadPictureEntity image2;
	/**产品图集 */
	private UploadPictureEntity image3;
	/**竞价列表*/
	private List<CompetePrice> outPrices;
	/**正在求购 0*/
	public final static int status_doing = 0;
	/**取消求购1*/
	public final static int status_cancel= 1;
	/**完成求购2*/
	public final static int status_finish =2;
	
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
	 * @return the {@link #texture}
	 */
	public String getTexture() {
		return texture;
	}
	/**
	 * @param texture the {@link #texture} to set
	 */
	public void setTexture(String texture) {
		this.texture = texture;
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
	 * @return the {@link #units}
	 */
	public String getUnits() {
		return units;
	}
	/**
	 * @param units the {@link #units} to set
	 */
	public void setUnits(String units) {
		this.units = units;
	}
	/**
	 * @return the {@link #requestNum}
	 */
	public long getRequestNum() {
		return requestNum;
	}
	/**
	 * @param requestNum the {@link #requestNum} to set
	 */
	public void setRequestNum(long requestNum) {
		this.requestNum = requestNum;
	}
	/**
	 * @return the {@link #model}
	 */
	public int getModel() {
		return model;
	}
	/**
	 * @param model the {@link #model} to set
	 */
	public void setModel(int model) {
		this.model = model;
	}
	/**
	 * @return the {@link #price}
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the {@link #price} to set
	 */
	public void setPrice(double price) {
		this.price = price;
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
	 * @return the {@link #solveTime}
	 */
	public Date getSolveTime() {
		return solveTime;
	}
	/**
	 * @param solveTime the {@link #solveTime} to set
	 */
	public void setSolveTime(Date solveTime) {
		this.solveTime = solveTime;
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
	 * @return the {@link #type}
	 */
	@ManyToOne
	public ProductType getType() {
		return type;
	}
	/**
	 * @param type the {@link #type} to set
	 */
	public void setType(ProductType type) {
		this.type = type;
	}
	/**
	 * @return the {@link #user}
	 */
	@ManyToOne
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
	 * @return the {@link #image1}
	 */
	@OneToOne
	public UploadPictureEntity getImage1() {
		return image1;
	}
	/**
	 * @param image1 the {@link #image1} to set
	 */
	public void setImage1(UploadPictureEntity image1) {
		this.image1 = image1;
	}
	/**
	 * @return the {@link #image2}
	 */
	@OneToOne
	public UploadPictureEntity getImage2() {
		return image2;
	}
	/**
	 * @param image2 the {@link #image2} to set
	 */
	public void setImage2(UploadPictureEntity image2) {
		this.image2 = image2;
	}
	/**
	 * @return the {@link #image3}
	 */
	@OneToOne
	public UploadPictureEntity getImage3() {
		return image3;
	}
	/**
	 * @param image3 the {@link #image3} to set
	 */
	public void setImage3(UploadPictureEntity image3) {
		this.image3 = image3;
	}
	
	/**
	 * @return the {@link #outPrices}
	 */
	@OneToMany(mappedBy="reqProduct")
	public List<CompetePrice> getOutPrices() {
		return outPrices;
	}
	/**
	 * @param outPrices the {@link #outPrices} to set
	 */
	public void setOutPrices(List<CompetePrice> outPrices) {
		this.outPrices = outPrices;
	}
	@Transient
	public UploadPictureEntity getCoverImg(){
		if(null != image2 && image2.getIsCoverImg() == UploadPictureEntity.IS_COVER_IMG)
			return image2;
		if(null != image3 && image3.getIsCoverImg() == UploadPictureEntity.IS_COVER_IMG)
			return image3;
		if(null != image1 && image1.getIsCoverImg() == UploadPictureEntity.IS_COVER_IMG)
			return image1;
		else{
			if(null != image1)
				return image1;
			if(null != image2)
				return image2;
			if(null != image3)
				return image3;
		}
		return null;
	}
}
