package fuliao.fuliaozhijia.weixin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveEventMsg;
import fuliao.fuliaozhijia.weixin.repository.WeixinReceiveEventMsgDao;
import fuliao.fuliaozhijia.weixin.util.WeixinMsgType;
import fuliao.fuliaozhijia.weixin.util.WeixinSendMsgUtil;

@Service("weixinReceiveEventMsgService")
@Transactional(readOnly=true)
public class WeixinReceiveEventMsgService extends GenericeService<WeixinReceiveEventMsg, String> {

	@Autowired
	private WeixinReceiveEventMsgDao dao;
	@Autowired
	@Qualifier("weixinUserService")
	private WeixinUserService weixinUserSer;
	
	@Override
	public IDao<WeixinReceiveEventMsg, String> getDao() {
		return dao;
	}

	@Override
	@Transactional(readOnly=false)
	public void save(WeixinReceiveEventMsg entity) {
		if(checkEventNeedReply(entity))
			super.save(entity);
	}

	private boolean checkEventNeedReply(WeixinReceiveEventMsg entity) {
		if(WeixinMsgType.EVENT.type.equals(entity.getMsgType())){
			boolean reply = StringUtils.equals("subscribe", entity.getEvent());
			if(reply){
				weixinUserSer.saveByOpenId(entity.getFromUserName());
				WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.welcomMsg(entity.getFromUserName()), null);
			}
			if(StringUtils.equals("unsubscribe", entity.getEvent())){
				weixinUserSer.updateUnsubscribe(entity.getFromUserName());
				return false;
			}
		}
		return false;
	}
}
