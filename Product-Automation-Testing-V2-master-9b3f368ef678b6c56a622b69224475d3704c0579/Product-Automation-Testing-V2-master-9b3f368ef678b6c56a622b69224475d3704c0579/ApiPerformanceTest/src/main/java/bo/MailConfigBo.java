package bo; 

public class MailConfigBo {

	private String subject;
	private String bodyContent;
	
	private String fromIpAddress;
	private String fromAddress;
	private String toAddress;
	private String bccAddress;
	private String ccAddress;
	private int successStatus;
	
	private byte[] attachement;
	
	private String message;

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBodyContent() {
		return bodyContent;
	}

	public void setBodyContent(String bodyContent) {
		this.bodyContent = bodyContent;
	}

	public String getFromIpAddress() {
		return fromIpAddress;
	}

	public void setFromIpAddress(String fromIpAddress) {
		this.fromIpAddress = fromIpAddress;
	}

	public String getFromAddress() {
		return fromAddress;
	}

	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}

	public String getToAddress() {
		return toAddress;
	}

	public void setToAddress(String toAddress) {
		this.toAddress = toAddress;
	}

	public String getBccAddress() {
		return bccAddress;
	}

	public void setBccAddress(String bccAddress) {
		this.bccAddress = bccAddress;
	}

	public String getCcAddress() {
		return ccAddress;
	}

	public void setCcAddress(String ccAddress) {
		this.ccAddress = ccAddress;
	}

	public byte[] getAttachement() {
		return attachement;
	}

	public void setAttachement(byte[] attachement) {
		this.attachement = attachement;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getSuccessStatus() {
		return successStatus;
	}

	public void setSuccessStatus(int successStatus) {
		this.successStatus = successStatus;
	}
	
	
}
