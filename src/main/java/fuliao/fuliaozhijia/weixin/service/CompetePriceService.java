package fuliao.fuliaozhijia.weixin.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.CompetePrice;
import fuliao.fuliaozhijia.weixin.repository.CompetePriceDao;

@Service("weixinCompetePriceService")
@Transactional(readOnly=true)
public class CompetePriceService extends GenericeService<CompetePrice, String> {

	@Autowired
	private CompetePriceDao dao;
	
	@Override
	public IDao<CompetePrice, String> getDao() {
		return dao;
	}

	public List<CompetePrice> findByReqProId(String reqProId) {
		return dao.findByReqProId(reqProId);
		
	}

	public String validate(CompetePrice compete) {
		StringBuffer sb = new StringBuffer();
		if(compete.getPrice()<0)
			sb.append("请填入有效报价<br>");
		if(compete.getReqProduct() == null || StringUtils.isBlank(compete.getReqProduct().getId()))
			sb.append("没有要报价的产品！<br>");
		if(compete.getShop() == null || StringUtils.isBlank(compete.getShop().getId()))
			sb.append("没有要报价的商家！<br>");
		return sb.toString();
	}

	public int findShopHasOutPrice(String shopid, String reqprodid) {
		return dao.findShopHasOutPrice(shopid,reqprodid);
	}

}
