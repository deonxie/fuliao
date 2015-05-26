package fuliao.fuliaozhijia.weixin.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.weixin.entity.WeixinReceiveMsg;
import fuliao.fuliaozhijia.weixin.repository.WeixinReceiveMsgDao;
import fuliao.fuliaozhijia.weixin.util.WeixinMsgType;
import fuliao.fuliaozhijia.weixin.util.WeixinSendMsgUtil;

@Service("weixinReceiveMsgService")
@Transactional(readOnly=true)
public class WeixinReceiveMsgService extends GenericeService<WeixinReceiveMsg, String> {

	@Autowired
	private WeixinReceiveMsgDao dao;
	
	@Override
	public IDao<WeixinReceiveMsg, String> getDao() {
		return dao;
	}

	@Override
	@Transactional(readOnly=false)
	public void save(WeixinReceiveMsg entity) {
		if(checkMsgNeedReply(entity))
			super.save(entity);
	}

	private boolean checkMsgNeedReply(WeixinReceiveMsg entity) {
		if(WeixinMsgType.TEXT.type.equals(entity.getMsgType())){
			boolean reply = StringUtils.trim(entity.getContent()).startsWith("注册");
			if(reply){
				WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.welcomMsg(entity.getFromUserName()), null);
				return reply;
			}
			reply = StringUtils.trim(entity.getContent()).startsWith("登录");
			if(reply){
				WeixinSendMsgUtil.sendMsgToUser(WeixinSendMsgUtil.sysLoginMsg(entity.getFromUserName()), null);
				return reply;
			}
		}
		return false;
	}
}
