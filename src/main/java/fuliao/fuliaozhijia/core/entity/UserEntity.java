package fuliao.fuliaozhijia.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.google.common.collect.Lists;



@Entity(name="coreUser")
@Table(name="core_user")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class UserEntity extends AbstractStringId{
	private String loginName;
	private String name;
	private String salt;
	private String password;
	private String tempPassword;
	private String telphone;
	private String email;
	private String weixinAccount;
	private String icon;
	private String address;
	private int status;
	private List<RoleEntity> roles = Lists.newArrayList();
	private DepartmentEntity department;
	
	public UserEntity() {
	}
	public UserEntity(String id) {
		this.id = id;
	}
	
	/**
	 * @return the longName
	 */
	@Column(unique=true)
	public String getLoginName() {
		return loginName;
	}
	/**
	 * @param longName the longName to set
	 */
	public void setLoginName(String loginName) {
		this.loginName = loginName;
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
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}
	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * @return the tempPassword
	 */
	@Transient
	public String getTempPassword() {
		return tempPassword;
	}
	/**
	 * @param tempPassword the tempPassword to set
	 */
	public void setTempPassword(String tempPassword) {
		this.tempPassword = tempPassword;
	}
	/**
	 * @return the telphone
	 */
	public String getTelphone() {
		return telphone;
	}
	/**
	 * @param telphone the telphone to set
	 */
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the roles
	 */
	@ManyToMany
	@JoinTable(name = "core_user_role", joinColumns = {@JoinColumn(name = "USER_ID")}, inverseJoinColumns = {@JoinColumn(name = "ROLE_ID")})
	public List<RoleEntity> getRoles() {
		return roles;
	}
	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<RoleEntity> roles) {
		this.roles = roles;
	}
	/**
	 * @return the departments
	 */
	@ManyToOne
	public DepartmentEntity getDepartment() {
		return department;
	}
	/**
	 * @param departments the departments to set
	 */
	public void setDepartment(DepartmentEntity department) {
		this.department = department;
	}
	/**
	 * @return the weixinAccount
	 */
//	@Column(unique=true)
	public String getWeixinAccount() {
		return weixinAccount;
	}
	/**
	 * @param weixinAccount the weixinAccount to set
	 */
	public void setWeixinAccount(String weixinAccount) {
		this.weixinAccount = weixinAccount;
	}
	/**
	 * @return the icon
	 */
	public String getIcon() {
		return icon;
	}
	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
	}
	
	/**
	 * @return the {@link #address}
	 */
	@Column(columnDefinition="text")
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the {@link #address} to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	@Transient
	public String getRoleIds(){
		StringBuffer sb = new StringBuffer();
		if(null != this.roles){
			for(int i=0; i< roles.size();i++){
				if(i == roles.size()-1)
					sb.append(roles.get(i).getId());
				else
					sb.append(roles.get(i).getId()).append(RoleEntity.split);
			}
		}
		return sb.toString();
	}
}
