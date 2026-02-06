package bo;

public class ResponceBO {
	private String environmentName =""; 
	private String moduleName ="";
	private String devResponceCode="";
	private String apiCallCode ="";
	private String Reason ="";
	private String exceptionMessage ="";
	private String apiPath ="";
	private String description="";
	private String requestType ="";
	
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	public String getDevResponceCode() {
		return devResponceCode;
	}
	public void setDevResponceCode(String devResponceCode) {
		this.devResponceCode = devResponceCode;
	}
	public String getApiCallCode() {
		return apiCallCode;
	}
	public void setApiCallCode(String apiCallCode) {
		this.apiCallCode = apiCallCode;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public String getExceptionMessage() {
		return exceptionMessage;
	}
	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}
	public String getEnvironmentName() {
		return environmentName;
	}
	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
	}
	public String getApiPath() {
		return apiPath;
	}
	public void setApiPath(String apiPath) {
		this.apiPath = apiPath;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getRequestType() {
		return requestType;
	}
	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}
	
	
	
	
	
}
