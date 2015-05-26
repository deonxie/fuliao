package fuliao.fuliaozhijia.core.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.repository.UploadPictureDao;

@Service("coreUploadPictureService")
@Transactional(readOnly=true)
public class UploadPictureService extends GenericeService<UploadPictureEntity, String>{
	private static Logger logger = LoggerFactory.getLogger(UploadPictureService.class);
	public static enum SavePath{
		TMP("/tmp/"),REQPRODUCT_IMG("/upload/reqProductImg/"),PRODUCT_IMG("/upload/productImg/"),
		SHOP_IMG("/upload/head/shops/"),SHOP_GROUP_IMG("/upload/head/shops/group/");
		public String path;
		SavePath(String path){
			this.path = path;
		}
	}
	
	@Autowired
	private UploadPictureDao dao;
	
	@Override
	public IDao<UploadPictureEntity, String> getDao() {
		return dao;
	}

	public List<UploadPictureEntity> findByIds(Collection<String> ids) {
		if(null == ids || ids.size()<1)
			return null;
		return dao.findForIds(new ArrayList<String>(ids));
	}
	
	@Transactional(readOnly=false)
	public String saveOneFile(String path,HttpServletRequest request,SavePath place) {
		UploadPictureEntity entity = saveFile(path, request, place);
		return null == entity ?null:entity.getId();
	}
	@Transactional(readOnly=false)
	public UploadPictureEntity saveOneFileGetEntity(String path,HttpServletRequest request,SavePath place) {
		return saveFile(path, request, place);
	}

	private UploadPictureEntity saveFile(String path,
			HttpServletRequest request, SavePath place) {
		UploadPictureEntity entity = null;
		if(!StringUtils.isBlank(path)){
			String root = getRootPath(request);
			if(moveFile(root+SavePath.TMP.path+path,root+place.path+path)){
				entity = new UploadPictureEntity();
				entity.setRelativePath(place.path+path);
				super.save(entity);
			}
		}
		return entity;
	}
	
	@Transactional(readOnly=false)
	public String saveFiles(String[] imgs,HttpServletRequest request,SavePath place) {
		List<UploadPictureEntity> list = saveManyFile(imgs, request, place);
		if(null != list && list.size()>0){
			StringBuffer sb = new StringBuffer();
			for(UploadPictureEntity upe : list)
				sb.append(upe.getId()).append(UploadPictureEntity.split);
			return sb.substring(0, sb.length()-1);
		}
		return null;
	}
	
	@Transactional(readOnly=false)
	public List<UploadPictureEntity> saveFilesGetEntity(String[] imgs,HttpServletRequest request,SavePath place) {
		return saveManyFile(imgs, request, place);
	}
	
	private List<UploadPictureEntity> saveManyFile(String[] imgs,HttpServletRequest request,SavePath place){
		List<UploadPictureEntity> list = null;
		if(null != imgs){
			String root = getRootPath(request);
			list = Lists.newArrayList();
			UploadPictureEntity entity;
			for(String path : imgs){
				if(!StringUtils.isBlank(path)){
					if(moveFile(root+SavePath.TMP.path+path,root+place.path+path)){
						entity = new UploadPictureEntity();
						entity.setRelativePath(place.path+path);
						list.add(entity);
					}
				}
			}
			if(list.size()>0)
				super.save(list);
		}
		return list;
	}
	private String getRootPath(HttpServletRequest request){
		return request.getServletContext().getRealPath("");
	}
	
	public boolean moveFile(String src ,String dest){
		try {
			FileUtils.moveFile(new File(src), new File(dest));
			return true;
		} catch (IOException e) {
			logger.error("移动文件失败。"+src, e);
		}
		return false;
	}
}
