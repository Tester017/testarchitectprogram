package bo;

public class PeriodFilterBo {
	
	private String year ="";
	private String conds ="";
	private String period ="";
	private String startdate ="";
	private String enddate="";
	private String type ="";
	
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getConds() {
		return conds;
	}
	public void setConds(String conds) {
		this.conds = conds;
	}
	public String getPeriod() {
		return period;
	}
	public void setPeriod(String period) {
		this.period = period;
	}
	public String getStartdate() {
		return startdate;
	}
	public void setStartdate(String startdate) {
		this.startdate = startdate;
	}
	public String getEnddate() {
		return enddate;
	}
	public void setEnddate(String enddate) {
		this.enddate = enddate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "PeriodFilterBo [year=" + year + ", conds=" + conds + ", period=" + period + ", startdate=" + startdate
				+ ", enddate=" + enddate + ", type=" + type + "]";
	}
	
	
	 
	
	

}
