package fuliao.fuliaozhijia.core.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name="core_sysconfig")
public class SysConfig extends AbstractLongId{
	private String key;
	private String value;
	private int status;//可用0;禁用1;
	public static final int ENABLE = 0;
	public static final int DISABLE = 1;
	
	/**
	 * @return the {@link #key}
	 */
	public String getKey() {
		return key;
	}
	/**
	 * @param key the {@link #key} to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	/**
	 * @return the {@link #value}
	 */
	public String getValue() {
		return value;
	}
	/**
	 * @param value the {@link #value} to set
	 */
	public void setValue(String value) {
		this.value = value;
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
	
}
