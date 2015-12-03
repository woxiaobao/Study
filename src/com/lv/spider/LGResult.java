package com.lv.spider;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map;

public class LGResult {
	private static String[] Fileds = {"createTime","positionName"};
	
//	public LGResult(Map<String,Object> map) throws ClassNotFoundException{
//		for(String key : Fileds){
//			String str = map.get(key).toString();
//			Class<LGResult> name = (Class<LGResult>) Class.forName("com.lv.spider.LGResult");
//			Field[] rule = name.getDeclaredFields();
//			for(int i=0;i<rule.length;i++){
//				System.out.println(rule[i].getName());
//				String names=rule[i].getName();
//				if(str.equals(names)){
//					rule[i].set
//				}
//			}
//			
//		}
//	}

	// 发布时间
	private String createTime;

	// 部门
	private String positionName;

	// 职位
	private String positionType;

	// 工作时长
	private String workYear;

	// 学历
	private String education;

	// 招聘公司
	private String companyName;

	// 公司行业
	private String industryField;

	// 公司类型
	private String financeStage;

	// 薪资
	private String salary;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String str = companyName + " " + industryField + " " + financeStage
				+ " " + positionName + " " + positionType + " " + workYear
				+ " " + education + " " + salary + " " + createTime;
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
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class<Rule> name = (Class<Rule>) Class.forName("com.lv.spider.Rule");
		Field[] rule = name.getDeclaredFields();
		System.out.println(rule.length);
		for(int i=0;i<rule.length;i++){
			System.out.println(rule[i].getName());
		}
		
	}

}
