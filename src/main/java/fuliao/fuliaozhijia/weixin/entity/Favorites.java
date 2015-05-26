package fuliao.fuliaozhijia.weixin.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fuliao.fuliaozhijia.core.entity.AbstractStringId;
import fuliao.fuliaozhijia.core.entity.UserEntity;
/**
 * 用户收藏产品、店铺
 * @author deon
 *
 */
@Entity(name="weixinFavorites")
@Table(name="weixin_Favorites")
public class Favorites extends AbstractStringId {
	private Product prodcut;
	private Shops shop;
	private UserEntity favUser;
	private Date createTime;
	
	/**
	 * @return the {@link #prodcut}
	 */
	@ManyToOne
	public Product getProdcut() {
		return prodcut;
	}
	/**
	 * @param prodcut the {@link #prodcut} to set
	 */
	public void setProdcut(Product prodcut) {
		this.prodcut = prodcut;
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
	 * @return the {@link #favUser}
	 */
	@ManyToOne
	public UserEntity getFavUser() {
		return favUser;
	}
	/**
	 * @param favUser the {@link #favUser} to set
	 */
	public void setFavUser(UserEntity favUser) {
		this.favUser = favUser;
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
}
