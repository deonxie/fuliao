package fuliao.fuliaozhijia.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.ProductType;
import fuliao.fuliaozhijia.weixin.repository.ProductTypeDao;

@Service("weixinProductTypeService")
@Transactional(readOnly=true)
public class ProductTypeService extends GenericeService<ProductType, Long> {

	@Autowired
	private ProductTypeDao dao;
	
	@Override
	public IDao<ProductType, Long> getDao() {
		return dao;
	}
	
}
