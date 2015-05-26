package fuliao.fuliaozhijia.weixin.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class WeixinDownload {
	static Logger logger = LoggerFactory.getLogger(WeixinDownload.class);
	
	public static String download(String mediaId,String dir){
//		http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID
		URL source;
		try {
			source = new URL("http://file.api.weixin.qq.com/cgi-bin/media/get?access_token="
					+ WeixinAccessUtil.getAccessToken()+"&media_id="+mediaId);
			URLConnection connection = source.openConnection();
			connection.setConnectTimeout(60*1000);
			connection.setReadTimeout(300*1000);
			InputStream input = connection.getInputStream();
			String fileType = connection.getHeaderField("Content-disposition");
			System.out.println("Content-disposition:"+fileType);
			String fileName = UUID.randomUUID().toString()+fileType.substring(fileType.lastIndexOf("."),fileType.length()-1);
			File file = new File(dir+fileName);
			FileUtils.copyInputStreamToFile(input, file);
			return fileName;
		} catch (MalformedURLException e) {
			logger.error("下载文件异常", e);
		} catch (IOException e) {
			logger.error("下载文件异常", e);
		} catch (Exception e){
			logger.error("下载文件异常", e);
		}
		return null;
	}
	public static void main(String[] args) {
		System.out.println("df.ff".substring("df.ff".lastIndexOf("."),"df.ff".length()-1));
		String fil = download("WtaIPuXmatzTYvsNv3iagKvL3_hb_-0C5tJXd1SsMJty3xVM6ccTzrFbtyRSu3Gi","/Users/jlusoft/Desktop/");
		System.out.println(fil);
	}
}
