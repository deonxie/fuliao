package fuliao.fuliaozhijia.weixin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMediaMsg;
import fuliao.fuliaozhijia.weixin.repository.WeixinReceiveMediaMsgDao;

@Service("weixinReceiveMediaMsgService")
@Transactional(readOnly=true)
public class WeixinReceiveMediaMsgService extends GenericeService<WeixinReceiveMediaMsg, String> {

	@Autowired
	private WeixinReceiveMediaMsgDao dao;
	
	@Override
	public IDao<WeixinReceiveMediaMsg, String> getDao() {
		return dao;
	}

}
