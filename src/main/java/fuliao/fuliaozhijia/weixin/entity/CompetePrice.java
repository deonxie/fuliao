package fuliao.fuliaozhijia.weixin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;

@Entity(name="weixinCompetePrice")
@Table(name="weixin_CompetePrice")
public class CompetePrice extends AbstractStringId {
	private long price;
	private String remark;
	private Date createTime;
	private RequestProduct reqProduct;
	private Shops shop;
	/** 货源类型 1现货 ，0样板 */
	private int source;
	/**竞价成功 1，竞价中 0*/
	private int status;
	/**有现货 1*/
	public static final int has_source = 1;
	/**样板 0*/
	public static final int has_model = 0;
	/**竞价成功 1*/
	public static final int success = 1;
	/**竞价竞价中 0*/
	public static final int doing = 0;
	
	/**
	 * @return the price
	 */
	public long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(long price) {
		this.price = price;
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
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * @return the reqProduct
	 */
	@ManyToOne
	public RequestProduct getReqProduct() {
		return reqProduct;
	}
	/**
	 * @param reqProduct the reqProduct to set
	 */
	public void setReqProduct(RequestProduct reqProduct) {
		this.reqProduct = reqProduct;
	}
	/**
	 * @return the {@link #shop}
	 */
	@ManyToOne
	public Shops getShop() {
		return shop;
	}
	/**
	 * @param shop the {@link #shop} to set
	 */
	public void setShop(Shops shop) {
		this.shop = shop;
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
	 * @return the {@link #source}
	 */
	@Column(columnDefinition="integer default 0")
	public int getSource() {
		return source;
	}
	/**
	 * @param source the {@link #source} to set
	 */
	public void setSource(int source) {
		this.source = source;
	}
	
}
