package fuliao.fuliaozhijia.weixin.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.entity.UserEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.core.service.UploadPictureService;
import fuliao.fuliaozhijia.core.service.UploadPictureService.SavePath;
import fuliao.fuliaozhijia.core.util.ImageUtil;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.repository.ShopsDao;

@Service("weixinShopsService")
@Transactional(readOnly=true)
public class ShopsService extends GenericeService<Shops, String> {
	@Autowired
	ShopsDao dao;
	@Autowired
	@Qualifier("coreUploadPictureService")
	private UploadPictureService fileSer;
	
	@Override
	public IDao<Shops, String> getDao() {
		return dao;
	}
	public Shops findByUserAndVerifyPass(String userId){
		if(StringUtils.isBlank(userId))
			return null;
		return dao.findByUserIdAndPass(userId,Shops.STATUS_VERIFY_PASS);
	}
	
	public Shops findByUser(UserEntity user){
		if(null == user || StringUtils.isBlank(user.getId()))
			return null;
		return dao.findByUserId(user.getId());
	}
	
	public String validation(Shops entity){
		StringBuffer error = new StringBuffer();
		if(StringUtils.isBlank(entity.getName()))
			error.append("请输入店铺名称!<br>");
		else if(StringUtils.length(entity.getName())>120)
			error.append("店铺名称长度不能超过120 ！<br>");
		if(StringUtils.isBlank(entity.getMainBusines()))
			error.append("请输入店铺主营业务!<br>");
		else if(StringUtils.length(entity.getMainBusines())>200)
			error.append("店铺主营业务不能超过250字！<br>");
		if(StringUtils.isBlank(entity.getDescript()))
			error.append("请输入产品描述!<br>");
		if(StringUtils.length(entity.getTelNum())>20)
			error.append("店铺联系电话不能超过20字！<br>");
		return error.toString();
	}
	
	@Transactional(readOnly=false)
	public void modfiy(Shops shop) {
		if(StringUtils.isBlank(shop.getId()))
			shop.setCreateTime(new Date());
		if(shop.getStatus()==Shops.STATUS_VERIFY_FAIL)
			shop.setStatus(Shops.STATUS_NO_VERIFY);
		
		super.save(shop);
	}
	public List<Shops> findShopByMainBusines(String type) {
		if(null ==type || StringUtils.isBlank(type))
			return null;
		return dao.findShopByMainBusines("%"+StringUtils.trim(type)+"%",Shops.STATUS_VERIFY_PASS);
	}
	/**
	 * 审核商家
	 * @param id
	 * @param b
	 * @param reason
	 */
	@Transactional(readOnly=false)
	public void verifyShop(String id, boolean pass, String reason) {
		if(pass)
			dao.verifyShop(id,getUser().name,Shops.STATUS_VERIFY_PASS,"");
		else
			dao.verifyShop(id,getUser().name,Shops.STATUS_VERIFY_FAIL,reason);
	}
	public long findNoVerifyCount() {
		return dao.findNotVerifyShop(Shops.STATUS_NO_VERIFY);
	}
	
	@Transactional(readOnly=false)
	public UploadPictureEntity saveProductPicture(String picture,String rootPath,boolean coverimg){
		UploadPictureEntity entity = null;
		if(!StringUtils.isBlank(picture)){
			if(fileSer.moveFile(rootPath+SavePath.TMP.path+picture,rootPath+SavePath.SHOP_IMG.path+picture)){
				entity = new UploadPictureEntity();
				String path = ImageUtil.cutImage(rootPath+SavePath.SHOP_IMG.path+picture, 1000, 1000, 'L', "fuliao",false);
				entity.setLargerPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.SHOP_IMG.path+picture, 100, 100, 'S', "fuliao",false);
				entity.setSamllPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.SHOP_IMG.path+picture, 400, 400, 'M', "fuliao",true);
				entity.setRelativePath(path.substring(rootPath.length()));
				if(coverimg)
					entity.setIsCoverImg(UploadPictureEntity.IS_COVER_IMG);
				fileSer.save(entity);
			}
		}
		return entity;
	}
}
