package fuliao.fuliaozhijia.core.entity;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

public enum PermissionEnum{
	// 系统设置
	USER_VIEW("user:view", "查看用户", "系统管理"), 
	USER_EDIT("user:edit", "操作用户","系统管理"), 
	ROLE_VIEW("role:view", "查看角色", "系统管理"), 
	ROLE_EDIT("role:edit", "操作角色", "系统管理"), 
	DRUID_MONITOR("druid:monitor","数据库监控", "系统管理"),
	//微信模块权限
	WEIXIN_SYSTEM("weixinsystem:view", "微信系统设置", "微信权限"),
	REQ_PRD_VIEW("reqprod:view", "查看求购列表", "微信权限"), 
	REQ_PRD_EDIT("reqprod:edit", "发布求购","微信权限"), 
	PRD_VIEW("prod:view", "查看产品列表", "微信权限"), 
	PRD_EDIT("prod:edit", "发布产品", "微信权限"), 
	OUT_PRICE_VIEW("outprice:view", "查看求购竞价", "微信权限"), 
	OUT_PRICE_EDIT("outprice:edit", "竞价求购产品", "微信权限"),
	REQ_TEL_VIEW("reqtel:view", "查看求购电话", "微信权限"), 
	SHOP_TEL_VIEW("shoptel:view", "查看商铺电话", "微信权限"),
	
	IMPORT_USER_EDIT("importuser:edit", "导入微信用户", "微信权限"), 
	WEIXIN_USER_VIEW("weixinuser:view", "微信用户列表", "微信权限"),
	USER_GROUP_EDIT("wusergroup:edit", "添加微信用户分组", "微信权限"), 
	USER_GROUP_VIEW("wusergroup:view", "微信用户分组列表", "微信权限"),
	PROD_TYPE_VIEW("prodtype:view", "产品类型列表", "微信权限"), 
	PROD_TYPE_EDIT("prodtype:edit", "添加产品类型", "微信权限"),
	SHOP_AUTHC_VIEW("shopauthc:view", "店铺列表", "微信权限"),
	SHOP_AUTHC_EDIT("shopauthc:edit", "审核店铺", "微信权限"),
	CLOSE_PROD_EDIT("closeprod:edit", "后台删除产品", "微信权限"),
	CLOSE_REQPROD_EDIT("closereqprod:edit", "后台删除求购", "微信权限");
	
	

	public String value;
	public String displayName;
	public String type;
	private static Map<String, List<PermissionEnum>> typePerms = Maps.newHashMap();
	
	static {
		for (PermissionEnum permission : PermissionEnum.values()) {
			List<PermissionEnum> strPermissionList = typePerms.get(permission.type);
			if (strPermissionList == null) {
				strPermissionList = Lists.newArrayList();
				typePerms.put(permission.type, strPermissionList);
			}
			strPermissionList.add(permission);
		}
	}

	PermissionEnum(String value, String displayName, String type) {
		this.value = value;
		this.displayName = displayName;
		this.type = type;
	}
	
	public static Map<String, List<PermissionEnum>> allTypePerms(){
		return  ImmutableMap.copyOf(typePerms);
	}

	/**
	 * @return the {@link #value}
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @return the {@link #displayName}
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @return the {@link #type}
	 */
	public String getType() {
		return type;
	}
	
}
