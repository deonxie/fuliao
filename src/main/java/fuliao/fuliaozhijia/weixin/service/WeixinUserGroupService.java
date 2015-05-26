package fuliao.fuliaozhijia.weixin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.WeixinUserGroup;
import fuliao.fuliaozhijia.weixin.repository.WeixinUserGroupDao;

@Service("weixinUserGroupService")
@Transactional(readOnly=true)
public class WeixinUserGroupService extends GenericeService<WeixinUserGroup, Long> {

	@Autowired
	private WeixinUserGroupDao dao;
	
	@Override
	public IDao<WeixinUserGroup, Long> getDao() {
		return dao;
	}

	@Transactional(readOnly=false)
	public String saveName(String name) {
		if(StringUtils.isNotBlank(name)){
			int count = dao.findNameCount(name);
			if(count>0)
				return "该分组已存在";
			WeixinUserGroup group = new WeixinUserGroup();
			group.setName(name);
			dao.save(group);
			return "";
		}
		return "名字为空";
	}

}
