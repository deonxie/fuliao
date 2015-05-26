package fuliao.fuliaozhijia.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


@Entity(name="coreRole")
@Table(name="core_Role")
public class RoleEntity extends AbstractStringId {
	private String roleName;
	private String roleLabel;
	private String permissions;
	public static final String split = ",";
	
	public RoleEntity() {
	}
	
	public RoleEntity(String id){
		this.id = id;
	}
	
	/**
	 * @return the roleName
	 */
	@Column(unique=true,updatable=false)
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	/**
	 * @return the {@link #roleLabel}
	 */
	public String getRoleLabel() {
		return roleLabel;
	}
	/**
	 * @param roleLabel the {@link #roleLabel} to set
	 */
	public void setRoleLabel(String roleLabel) {
		this.roleLabel = roleLabel;
	}
	/**
	 * @return the {@link #permissions}
	 */
	@Column(columnDefinition="text")
	public String getPermissions() {
		return permissions;
	}
	/**
	 * @param permissions the {@link #permissions} to set
	 */
	public void setPermissions(String permissions) {
		this.permissions = permissions;
	}
	
	@Transient
    public List<String> getPermissionList() {
        if (StringUtils.isNotBlank(permissions)) {
        	return ImmutableList.copyOf(StringUtils.split(permissions, split));
        } else {
            return Lists.newArrayList();
        }
    }
}
