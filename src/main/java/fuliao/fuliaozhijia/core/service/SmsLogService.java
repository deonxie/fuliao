package fuliao.fuliaozhijia.core.service;


import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.entity.SMSLogEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.repository.SMSLogDao;

@Service("coreSmsLogService")
@Transactional(readOnly=true)
public class SmsLogService extends GenericeService<SMSLogEntity, String>{
	private static Logger logger = LoggerFactory.getLogger(SmsLogService.class);
	
	/**验证码类型短信*/
	public final static String TYPE_CODE = "短信验证码";
	/**有效秒数*/
	public final static int LIVE_SECOND = 600;
	@Autowired
	private SMSLogDao dao;

	@Override
	public IDao<SMSLogEntity, String> getDao() {
		return dao;
	}

	public int checkOneDaySendCount(String telnum,String date) {
		return dao.checkOneDaySendCount(telnum,date);
	}

	public int checkCode(String sendDate,String telnum, String code){
		Date sendTime = dao.checkVerifyCode(sendDate,telnum,code,TYPE_CODE);
		if(null != sendTime){
			long send = new Date().getTime() - sendTime.getTime();
			logger.info("send-date:"+sendDate+" tel-num:"+ telnum+" code:"+code+" ms:"+send);
			return (int)send/1000;
		}
		return 0;
	}
	
}
