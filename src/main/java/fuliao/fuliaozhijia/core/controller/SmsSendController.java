package fuliao.fuliaozhijia.core.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.regex.Pattern;

import javax.servlet.ServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fuliao.fuliaozhijia.core.entity.SMSLogEntity;
import fuliao.fuliaozhijia.core.service.SmsLogService;
import fuliao.fuliaozhijia.core.util.SendSMSUtil;
import fuliao.fuliaozhijia.core.util.SystemProperties;

@RestController
@RequestMapping(value="/core/sms/",method = RequestMethod.POST)
public class SmsSendController {
	private static final Logger logger = LoggerFactory.getLogger(SmsSendController.class);
	Pattern pattern = Pattern.compile(SystemProperties.getInstance().getValue("system.verify.mobile.phone.formart"));
	Random rand = new Random();
	SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	@Autowired
	@Qualifier("coreSmsLogService")
	private SmsLogService smsSer;
	
	@RequestMapping(value="sendCode")
	public String login(@RequestParam("telnum")String telnum,ServletRequest request) {
		if(StringUtils.length(telnum)==11){
			telnum = StringUtils.trim(telnum);
			if(pattern.matcher(telnum).find()){
				//一天之内同一个号码最多发送3次验证码
				String date = format.format(new Date());
				int times = smsSer.checkOneDaySendCount(telnum,date);
				if(times>3)
					return "已超过每天发送验证码的次数";
				String code = "";
				for(int i=0;i<4; i++)
					code +=rand.nextInt(10);
				
				String json = SendSMSUtil.sendCode(code, SmsLogService.LIVE_SECOND/60, telnum);
				if(checkResult(json)){
					SMSLogEntity sms = new SMSLogEntity();
					sms.setContent(code);
					sms.setPhoneNum(telnum);
					sms.setSendTime(new Date());
					sms.setSendDate(date);
					sms.setBusinessType(SmsLogService.TYPE_CODE);
					smsSer.save(sms);
					return "true";
				}
				return "短信发送失败";
			}
		}
		return "请检查手机号码是否正确";
	}
	
	private boolean checkResult(String json){
		try {
			String status = new JSONObject(json).getJSONObject("resp").getString("respCode");
			if("000000".equals(status))
				return true;
		} catch (Exception e) {
			logger.error("短信发送结果："+json);
		}
		return false;
	}
}
