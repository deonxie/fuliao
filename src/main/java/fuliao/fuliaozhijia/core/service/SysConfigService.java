package fuliao.fuliaozhijia.core.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;

import fuliao.fuliaozhijia.core.entity.SysConfig;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.repository.SysConfigDao;
@Service("core_sysconfig")
public class SysConfigService extends GenericeService<SysConfig, Long> {

	private static final Map<String, String> config = Maps.newHashMap();
	@Autowired
	SysConfigDao dao;
	
	@Override
	public IDao<SysConfig, Long> getDao() {
		return dao;
	}
	
	public void init(){
		List<SysConfig> list = dao.findAllCanUse(SysConfig.ENABLE);
		for(SysConfig conf : list){
			config.put(StringUtils.defaultString(conf.getKey()), 
					StringUtils.defaultString(conf.getValue()));
		}
	}
}
