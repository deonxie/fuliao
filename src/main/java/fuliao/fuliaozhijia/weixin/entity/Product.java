package fuliao.fuliaozhijia.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;
import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;

@Entity(name="weixinProduct")
@Table(name="weixin_Product")
public class Product extends AbstractStringId{
	/** 产品名称 */
	private String name;
	/** 产品材质 */
	private String texture;
	/** 产品描述 */
	private String descript;
	/** 单位 */
	private String units;
	/** 库存数量 */
	private long stockNum;
	/** 产品货源类型 0现货，1样板 */
	private int model;
	/** 单价 */
	private double price;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date updateTime;
	/** 产品状态 0正常，1下架 */
	private int status;
	/** 产品类型 */
	private ProductType type;
	/** 月销售量 */
	private long saleNum;
	/** 收藏人数 */
	private long storeNum;
	/** 用户评分 */
	private long gradeScore;
	/** 所属商铺 **/
	private Shops shops;
	/**产品图集 */
	private UploadPictureEntity image1;
	/**产品图集 */
	private UploadPictureEntity image2;
	/**产品图集 */
	private UploadPictureEntity image3;
	
	public static final int status_enable = 0;
	public static final int status_disable = 1;
	/**
	 * @return the {@link #name}
	 */
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
	 * @return the {@link #stockNum}
	 */
	public long getStockNum() {
		return stockNum;
	}
	/**
	 * @param stockNum the {@link #stockNum} to set
	 */
	public void setStockNum(long stockNum) {
		this.stockNum = stockNum;
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
	 * @return the {@link #updateTime}
	 */
	public Date getUpdateTime() {
		return updateTime;
	}
	/**
	 * @param updateTime the {@link #updateTime} to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
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
	 * @return the {@link #saleNum}
	 */
	public long getSaleNum() {
		return saleNum;
	}
	/**
	 * @param saleNum the {@link #saleNum} to set
	 */
	public void setSaleNum(long saleNum) {
		this.saleNum = saleNum;
	}
	/**
	 * @return the {@link #storeNum}
	 */
	public long getStoreNum() {
		return storeNum;
	}
	/**
	 * @param storeNum the {@link #storeNum} to set
	 */
	public void setStoreNum(long storeNum) {
		this.storeNum = storeNum;
	}
	/**
	 * @return the {@link #gradeScore}
	 */
	public long getGradeScore() {
		return gradeScore;
	}
	/**
	 * @param gradeScore the {@link #gradeScore} to set
	 */
	public void setGradeScore(long gradeScore) {
		this.gradeScore = gradeScore;
	}
	/**
	 * @return the {@link #shops}
	 */
	@ManyToOne
	public Shops getShops() {
		return shops;
	}
	/**
	 * @param shops the {@link #shops} to set
	 */
	public void setShops(Shops shops) {
		this.shops = shops;
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
