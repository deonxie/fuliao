package fuliao.fuliaozhijia.core.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;

public interface UploadPictureDao extends IStringIdDao<UploadPictureEntity>{
	
	@Query("from coreUploadPicture where id in(?1)")
	public List<UploadPictureEntity> findForIds(List<String> ids);

}
