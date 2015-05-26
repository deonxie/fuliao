package fuliao.fuliaozhijia.core.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageUtil {
	/**
	 * @param path 被裁剪图片的原始绝对路径
	 * @param w 裁剪后的宽度
	 * @param h 裁剪后的高度
	 * @param suffix 裁剪后的图片路径追加的后缀名，如‘L’表示裁剪的大图片，‘S’表示中图，‘M’表示小图
	 * @param waterText 裁剪后图片添加的水印文字
	 * @param drop 裁剪成功是否删除原图片，true删除，false不删除
	 * @return 返回裁剪后图片的绝对路径
	 */
	public static String cutImage(String path,int w,int h,char suffix,String waterText,boolean drop){
		try {
			File file = new File(path);
			BufferedImage image = ImageIO.read(file);
			int width = image.getWidth();
			int height = image.getHeight();
			float scaleW = width/(0.0f+w);
			float scaleH = height/(0.0f+h);
			float sclae = scaleW>=scaleH ?scaleH:scaleW;
			int newW = (int)(w*sclae);
			int newH = (int)(h*sclae);
			int x = (width-newW)/2;
			int y = (height-newH)/2;
			
			BufferedImage tmp = image.getSubimage(x, y, newW, newH);
			Image img = tmp.getScaledInstance(w, h, BufferedImage.SCALE_SMOOTH);
			BufferedImage target = new BufferedImage(w, h,image.getType());
			Graphics2D g = target.createGraphics();
			g.drawImage(img, 0, 0, null);
			if(null != waterText){
				g.setColor(Color.GRAY);
				g.setFont(new Font("宋体", Font.PLAIN, 4));
				g.drawString(waterText, 2, h-6);
				g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_ATOP, 0.4f));
			}
			g.dispose();
			String imageType = path.substring(path.lastIndexOf(".")+1);
			String newFile = path.substring(0,path.lastIndexOf("."))+suffix+"."+imageType;
			ImageIO.write(target, imageType, new File(newFile));
			if(drop)
				file.delete();
			return newFile;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "";
	}
	

}
