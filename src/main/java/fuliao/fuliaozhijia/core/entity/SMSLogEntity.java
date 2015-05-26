package fuliao.fuliaozhijia.core.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;


/**
 * 短信发送记录表
 * @author DEON
 *
 */
@Entity(name="coreSMSLog")
@Table(name="core_smslog")
public class SMSLogEntity extends AbstractStringId {
	
	private String phoneNum;
	private String sendUserName;
	private String businessType;
	private Date sendTime;
	/**发送日期 yyyy-MM-dd格式*/
	private String sendDate;
	private String content;
	/**默认0 发送成功 1发送失败*/
	private int status;
	
	
	/**
	 * @return the {@link #phoneNum}
	 */
	public String getPhoneNum() {
		return phoneNum;
	}
	/**
	 * @param phoneNum the {@link #phoneNum} to set
	 */
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	/**
	 * @return the {@link #sendUserName}
	 */
	public String getSendUserName() {
		return sendUserName;
	}
	/**
	 * @param sendUserName the {@link #sendUserName} to set
	 */
	public void setSendUserName(String sendUserName) {
		this.sendUserName = sendUserName;
	}
	/**
	 * @return the {@link #businessType}
	 */
	public String getBusinessType() {
		return businessType;
	}
	/**
	 * @param businessType the {@link #businessType} to set
	 */
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	/**
	 * @return the {@link #sendTime}
	 */
	public Date getSendTime() {
		return sendTime;
	}
	/**
	 * @param sendTime the {@link #sendTime} to set
	 */
	public void setSendTime(Date sendTime) {
		this.sendTime = sendTime;
	}
	/**
	 * @return the {@link #content}
	 */
	public String getContent() {
		return content;
	}
	/**
	 * @param content the {@link #content} to set
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * @return the {@link #status}
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status the {@link #status} to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}
	/**
	 * @return the {@link #sendDate}
	 */
	public String getSendDate() {
		return sendDate;
	}
	/**
	 * @param sendDate the {@link #sendDate} to set
	 */
	public void setSendDate(String sendDate) {
		this.sendDate = sendDate;
	}
	
}
