package fuliao.fuliaozhijia.weixin.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import fuliao.fuliaozhijia.core.entity.AbstractLongId;

/**
 * 微信用户分组
 * @author DEON
 *{"id": 2,"name": "星标组", "count": 0 }
 */
@Entity(name="WeixinUserGroup")
@Table(name="Weixin_UserGroup")
public class WeixinUserGroup extends AbstractLongId{
	private long weixinGroupId;
	private String name;
	private List<WeixinUser> users;
	/**
	 * @return the weixinGroupId
	 */
	public long getWeixinGroupId() {
		return weixinGroupId;
	}
	/**
	 * @param weixinGroupId the weixinGroupId to set
	 */
	public void setWeixinGroupId(long weixinGroupId) {
		this.weixinGroupId = weixinGroupId;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the count
	 */
	@Transient
	public long getCount() {
		if(users==null)
			return 0;
		return users.size();
	}
	/**
	 * @return the users
	 */
	@JsonIgnore
	@OneToMany(mappedBy="userGroup")
	public List<WeixinUser> getUsers() {
		return users;
	}
	/**
	 * @param users the users to set
	 */
	public void setUsers(List<WeixinUser> users) {
		this.users = users;
	}
	
	
}
