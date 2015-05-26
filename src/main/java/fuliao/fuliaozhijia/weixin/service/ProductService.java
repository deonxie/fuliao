package fuliao.fuliaozhijia.weixin.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.core.service.UploadPictureService;
import fuliao.fuliaozhijia.core.service.UploadPictureService.SavePath;
import fuliao.fuliaozhijia.core.util.ImageUtil;
import fuliao.fuliaozhijia.weixin.entity.Product;
import fuliao.fuliaozhijia.weixin.repository.ProductDao;

@Service("weixinProductService")
@Transactional(readOnly=true)
public class ProductService extends GenericeService<Product, String> {
	@Autowired
	ProductDao dao;
	@Autowired
	@Qualifier("coreUploadPictureService")
	private UploadPictureService fileSer;
	
	@Override
	public IDao<Product, String> getDao() {
		return dao;
	}

	public String validation(Product entity){
		StringBuffer error = new StringBuffer();
		if(StringUtils.isBlank(entity.getName()))
			error.append("请输入产品名称!<br>");
		else if(StringUtils.length(entity.getName())>120)
			error.append("产品名称长度不能超过120 ！<br>");
		if(StringUtils.length(entity.getTexture())>200)
			error.append("产品材质不能超过200字！<br>");
		if(StringUtils.isBlank(entity.getDescript()))
			error.append("请输入产品描述!<br>");
		
		return error.toString();
	}
	
	@Transactional(readOnly=false)
	public void modfiy(Product prod) {
		if(StringUtils.isBlank(prod.getId())){
			prod.setCreateTime(new Date());
			super.save(prod);
			return;
		}
		dao.save(prod);
	}
	
	@Transactional(readOnly=false)
	public void updateProduct(String id, int status) {
		dao.updateProductStatus(id,status);
	}
	
	@Transactional(readOnly=false)
	public UploadPictureEntity saveProductPicture(String picture,String rootPath,boolean coverimg){
		UploadPictureEntity entity = null;
		if(!StringUtils.isBlank(picture)){
			if(fileSer.moveFile(rootPath+SavePath.TMP.path+picture,rootPath+SavePath.PRODUCT_IMG.path+picture)){
				entity = new UploadPictureEntity();
				String path = ImageUtil.cutImage(rootPath+SavePath.PRODUCT_IMG.path+picture, 640, 320, 'L', "fuliao",false);
				entity.setLargerPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.PRODUCT_IMG.path+picture, 160, 80, 'S', "fuliao",false);
				entity.setSamllPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.PRODUCT_IMG.path+picture, 480, 240, 'M', "fuliao",true);
				entity.setRelativePath(path.substring(rootPath.length()));
				if(coverimg)
					entity.setIsCoverImg(UploadPictureEntity.IS_COVER_IMG);
				fileSer.save(entity);
			}
		}
		return entity;
	}
}
