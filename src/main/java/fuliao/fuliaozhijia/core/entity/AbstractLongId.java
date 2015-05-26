package fuliao.fuliaozhijia.core.entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * long类型id
 * @author DEON
 */
@MappedSuperclass
public abstract class AbstractLongId implements IEntity{
	protected Long id;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
}
