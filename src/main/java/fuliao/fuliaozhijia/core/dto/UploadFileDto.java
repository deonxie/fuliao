package fuliao.fuliaozhijia.core.dto;

public class UploadFileDto {
	private boolean isSuccess;
	private String result;
	
	public UploadFileDto(boolean isSuccess,String result){
		this.isSuccess = isSuccess;
		this.result = result;
	}
	/**
	 * @return the isSuccess
	 */
	public boolean isSuccess() {
		return isSuccess;
	}
	/**
	 * @param isSuccess the isSuccess to set
	 */
	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
}
