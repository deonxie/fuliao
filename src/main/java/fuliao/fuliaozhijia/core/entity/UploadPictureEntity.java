package fuliao.fuliaozhijia.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity(name="coreUploadPicture")
@Table(name="core_UploadPicture")
public class UploadPictureEntity extends AbstractStringId {
	private String dataByte;
	private String relativePath;
	private String largerPicture;
	private String samllPicture;
	private UserEntity uploadUser;
	private int isCoverImg;
	
	public final static int IS_COVER_IMG = 1;
	public final static int ONT_COVER_IMG = 0;
	public final static String split = ",";
	/**
	 * @return the dataByte
	 */
	@Column(columnDefinition="text")
	public String getDataByte() {
		return dataByte;
	}
	/**
	 * @param dataByte the dataByte to set
	 */
	public void setDataByte(String dataByte) {
		this.dataByte = dataByte;
	}
	/**
	 * @return the relativePath
	 */
	public String getRelativePath() {
		return relativePath;
	}
	/**
	 * @param relativePath the relativePath to set
	 */
	public void setRelativePath(String relativePath) {
		this.relativePath = relativePath;
	}
	/**
	 * @return the largerPicture
	 */
	public String getLargerPicture() {
		return largerPicture;
	}
	/**
	 * @param largerPicture the largerPicture to set
	 */
	public void setLargerPicture(String largerPicture) {
		this.largerPicture = largerPicture;
	}
	/**
	 * @return the samllPicture
	 */
	public String getSamllPicture() {
		return samllPicture;
	}
	/**
	 * @param samllPicture the samllPicture to set
	 */
	public void setSamllPicture(String samllPicture) {
		this.samllPicture = samllPicture;
	}
	/**
	 * @return the uploadUser
	 */
	@ManyToOne
	public UserEntity getUploadUser() {
		return uploadUser;
	}
	/**
	 * @param uploadUser the uploadUser to set
	 */
	public void setUploadUser(UserEntity uploadUser) {
		this.uploadUser = uploadUser;
	}
	/**
	 * @return the {@link #isCoverImg}
	 */
	@Column(columnDefinition="integer default 0")
	public int getIsCoverImg() {
		return isCoverImg;
	}
	/**
	 * @param isCoverImg the {@link #isCoverImg} to set
	 */
	public void setIsCoverImg(int isCoverImg) {
		this.isCoverImg = isCoverImg;
	}
	
}
