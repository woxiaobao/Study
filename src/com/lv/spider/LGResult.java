package com.lv.spider;

public class LGResult {
	
	//发布时间
	private String createTime;
	
	//部门
	private String positionName;
	
	//职位
	private String positionType;
	
	//工作时长
	private String workYear;
	
	//学历
	private String education;
	
	//招聘公司
	private String companyName;
	
	//公司行业
	private String industryField;
	
	//公司类型
	private String financeStage;
	
	//薪资
	private String salary;
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str=companyName+" "+industryField+" "+financeStage+" "+positionName+" "+positionType+" "+workYear+" "+education+" "+salary+" "+createTime;
		return str;
	}
	
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getPositionName() {
		return positionName;
	}
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}
	public String getPositionType() {
		return positionType;
	}
	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getIndustryField() {
		return industryField;
	}
	public void setIndustryField(String industryField) {
		this.industryField = industryField;
	}
	public String getFinanceStage() {
		return financeStage;
	}
	public void setFinanceStage(String financeStage) {
		this.financeStage = financeStage;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	
}
