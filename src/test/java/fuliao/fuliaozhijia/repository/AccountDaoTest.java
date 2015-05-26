/*******************************************************************************
 * Copyright (c) 2005, 2014 springside.github.io
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 *******************************************************************************/
package fuliao.fuliaozhijia.repository;

import cn.gd.thinkjoy.modules.test.spring.SpringTransactionalTestCase;

import com.google.common.collect.Lists;

import fuliao.fuliaozhijia.core.entity.RoleEntity;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.service.CoreRoleService;
import fuliao.fuliaozhijia.core.service.CoreUserService;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;


@ContextConfiguration(locations = { "/applicationContext.xml" })
public class AccountDaoTest extends SpringTransactionalTestCase {

	@Autowired
	private CoreUserService userService;
    @Autowired
    private CoreRoleService roleService;
       
    
    @Test
    @Rollback(false)
    public void addRole() {
//    	PermissionEntity perUser = new PermissionEntity();
//    	perUser.setAction("/core/user/list");
//    	perUser.setName("用户列表");
//    	perUser.setView("core/user/userList");
//    	perUser.setRemark("系统级权限");
//    	PermissionEntity perUserUpdate = new PermissionEntity();
//    	perUserUpdate.setAction("/core/user/update");
//    	perUserUpdate.setName("用户增改");
//    	perUserUpdate.setView("core/user/userForm");
//    	perUserUpdate.setRemark("系统级权限");
//    	PermissionEntity perRole = new PermissionEntity();
//    	perRole.setAction("/core/role/list");
//    	perRole.setName("角色列表");
//    	perRole.setView("core/role/roleList");
//    	perRole.setRemark("系统级权限");
//    	PermissionEntity perRoleUpdate = new PermissionEntity();
//    	perRoleUpdate.setAction("/core/role/update");
//    	perRoleUpdate.setName("角色增改");
//    	perRoleUpdate.setView("core/role/roleForm");
//    	perRoleUpdate.setRemark("系统级权限");
//        PermissionEntity perPermission = new PermissionEntity();
//        perPermission.setAction("/core/permission/list");
//        perPermission.setName("权限列表");
//        perPermission.setView("core/permission/permissionList");
//        perPermission.setRemark("系统级权限");
//    	PermissionEntity permissionUpdate = new PermissionEntity();
//    	permissionUpdate.setAction("/core/permission/update");
//    	permissionUpdate.setName("权限增改");
//    	permissionUpdate.setView("core/permission/permissionForm");
//    	permissionUpdate.setRemark("系统级权限");
//    	
//    	List<PermissionEntity> pers = Lists.newArrayList();
//    	pers.add(perUser);
//    	pers.add(perUserUpdate);
//    	pers.add(perRole);
//    	pers.add(perRoleUpdate);
//    	pers.add(perPermission);
//    	pers.add(permissionUpdate);
//    	perService.save(pers);
//    	
//        RoleEntity role = new RoleEntity();
//        role.setRoleName("管理员");
//        
//        List<RolePermission> rps = Lists.newArrayList();
//        RolePermission rp ;
//        for(PermissionEntity pe : pers){
//        	rp = new RolePermission();
//        	rp.setPermission(pe);
//        	rp.setRole(role);
//        	rps.add(rp);
//        }
//        role.setPermissions(rps);
//        roleService.save(role);
        
        
        UserEntity user = new UserEntity();
        user.setLoginName("user");
        user.setName("user");
        user.setTempPassword("123");
//        List<RoleEntity> roles = Lists.newArrayList();
//        roles.add(role);
//        user.setRoles(roles);
        userService.save(user);
        System.out.println("finish");
    }

}
