package fuliao.fuliaozhijia.core.repository;

import java.util.Date;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.entity.SMSLogEntity;

public interface SMSLogDao extends IStringIdDao<SMSLogEntity> {

	@Query("select count(phoneNum) from coreSMSLog where phoneNum=?1 and sendDate=?2")
	int checkOneDaySendCount(String telnum, String sendDate);
	@Query("select max(sendTime) from coreSMSLog where sendDate=?1 and phoneNum=?2 and content=?3 and businessType=?4")
	Date checkVerifyCode(String sendDate,String telnum, String code, String typeCode);

}
