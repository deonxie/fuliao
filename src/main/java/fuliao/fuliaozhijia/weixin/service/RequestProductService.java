package fuliao.fuliaozhijia.weixin.service;


import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import fuliao.fuliaozhijia.core.entity.UploadPictureEntity;
import fuliao.fuliaozhijia.core.repository.IDao;
import fuliao.fuliaozhijia.core.service.GenericeService;
import fuliao.fuliaozhijia.core.service.UploadPictureService;
import fuliao.fuliaozhijia.core.service.UploadPictureService.SavePath;
import fuliao.fuliaozhijia.core.util.ImageUtil;
import fuliao.fuliaozhijia.weixin.entity.ProductType;
import fuliao.fuliaozhijia.weixin.entity.RequestProduct;
import fuliao.fuliaozhijia.weixin.entity.Shops;
import fuliao.fuliaozhijia.weixin.repository.RequestProductDao;
import fuliao.fuliaozhijia.weixin.util.WeixinSendMsgUtil;

@Service("weixinRequestProductService")
@Transactional(readOnly=true)
public class RequestProductService extends GenericeService<RequestProduct, String>{
	private static Logger logger = LoggerFactory.getLogger(RequestProductService.class);
	
	@Autowired
	private RequestProductDao dao;
	@Autowired
	@Qualifier("weixinShopsService")
	private ShopsService shopSer;
	@Autowired
	@Qualifier("weixinUserService")
	private WeixinUserService wUserSer;
	@Autowired
	@Qualifier("weixinProductTypeService")
	private ProductTypeService typeSer;
	@Autowired
	@Qualifier("coreUploadPictureService")
	private UploadPictureService fileSer;
	
	@Override
	public IDao<RequestProduct, String> getDao() {
		return dao;
	}

	public String validation(RequestProduct prod) {
		StringBuffer sb = new StringBuffer();
		if(StringUtils.isBlank(prod.getName()))
			sb.append("请输入求购产品的名称！<br>");
		else if(StringUtils.length(prod.getName())>120)
			sb.append("求购产品名称不能超过120字！<br>");
		if(StringUtils.length(prod.getTexture())>200)
			sb.append("求购产品的材质不能超过200字 <br>");
		if(StringUtils.isBlank(prod.getDescript()))
			sb.append("求购描述没填写！<br>");
		if(prod.getRequestNum()<0)
			sb.append("请输入求购数量! <br>");
		return null;
	}
	
	@Transactional(readOnly=false)
	public void modfiy(RequestProduct prod) {
		if(StringUtils.isBlank(prod.getId()))
			prod.setCreateTime(new Date());
		
		super.save(prod);
		logger.info("求购已发布："+prod.getName());
		sendMsgToShops(prod);
		logger.info("求购已推送到商家："+prod.getName());
	}
	
	private void sendMsgToShops(RequestProduct prod){
		ProductType ptype =  typeSer.findOne(prod.getType().getId());
		if(null == ptype)
			return;
		String type = ptype.getTypeName();
		List<Shops> list = shopSer.findShopByMainBusines(type);
		if(list != null){
			List<String> loginNames = Lists.newArrayList();
			for(Shops sh : list){
				loginNames.add(sh.getUser().getLoginName());
			}
			logger.info("求购已推送商家数量："+list.size()+"  商家用户个数："+loginNames.size());
			WeixinSendMsgUtil.sendReqProdcut2Shop(wUserSer.findByUserLoginNames(loginNames),prod);
		}
	}

	@Transactional(readOnly=false)
	public void modfiyStatus(String id, int status) {
		if(!StringUtils.isBlank(id)){
			dao.modfiyStatus(id,status,new Date());
		}
	}
	
	@Transactional(readOnly=false)
	public UploadPictureEntity saveProductPicture(String picture,String rootPath,boolean coverimg){
		UploadPictureEntity entity = null;
		if(!StringUtils.isBlank(picture)){
			if(fileSer.moveFile(rootPath+SavePath.TMP.path+picture,rootPath+SavePath.REQPRODUCT_IMG.path+picture)){
				entity = new UploadPictureEntity();
				String path = ImageUtil.cutImage(rootPath+SavePath.REQPRODUCT_IMG.path+picture, 640, 320, 'L', "fuliao",false);
				entity.setLargerPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.REQPRODUCT_IMG.path+picture, 160, 80, 'S', "fuliao",false);
				entity.setSamllPicture(path.substring(rootPath.length()));
				path = ImageUtil.cutImage(rootPath+SavePath.REQPRODUCT_IMG.path+picture, 480, 240, 'M', "fuliao",true);
				entity.setRelativePath(path.substring(rootPath.length()));
				if(coverimg)
					entity.setIsCoverImg(UploadPictureEntity.IS_COVER_IMG);
				fileSer.save(entity);
			}
		}
		return entity;
	}
}
