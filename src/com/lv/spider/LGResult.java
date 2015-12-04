package com.lv.spider;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import com.lv.log.LogStart;

public class LGResult {
	private static String[] Fileds = {
		"createTime",
		"positionName",
		"positionType",
		"workYear",
		"education",
		"companyName",
		"industryField",
		"financeStage",
		"salary"
		};
	
	public LGResult(){
		
	}
	
	public static void result(Map<String,Object> map) throws NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException{
		@SuppressWarnings("unchecked")
		Class<LGResult> model = (Class<LGResult>) Class.forName("com.lv.spider.LGResult");
		LGResult bean = model.newInstance();
		for(String key : Fileds){
			String str = map.get(key).toString();
			key = key.substring(0, 1).toUpperCase() + key.substring(1); // 将属性的首字符大写，方便构造get，set
			Method method = model.getMethod("set"+key,String.class);
			if(null != str){  
                method.invoke(bean, str);
            }
		}
		LogStart.WLog(bean.toString());
	}
	
	public LGResult(Map<String,Object> map) throws ClassNotFoundException, NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		LGResult bean=new LGResult();
		for(String key : Fileds){
			String str = map.get(key).toString();
			
			Class<?> cls = bean.getClass();
			Method methods[] = cls.getDeclaredMethods();  
//	        Field fields[] = cls.getDeclaredFields(); 
			
			String setMethod = pareSetName(key);  
            if(!checkMethod(methods, setMethod)){  
                continue;  
            }  
            Method method = cls.getMethod(setMethod, String.class);  
            if(null != str){  
//            	method.setAccessible(true);//如果方法是private可以通过设置这个参数来调用
                method.invoke(bean, str);
            }
			
		}
		LogStart.WLog(bean.toString());
	}

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

	/**  
     * 拼接某属性get 方法  
     * @param fldname  
     * @return  
     */  
    public static String pareGetName(String fldname){  
        if(null == fldname || "".equals(fldname)){  
            return null;  
        }  
        String pro = "get"+fldname.substring(0,1).toUpperCase()+fldname.substring(1);  
        return pro;  
    }  
    /**  
     * 拼接某属性set 方法  
     * @param fldname  
     * @return  
     */  
    public static String pareSetName(String fldname){  
        if(null == fldname || "".equals(fldname)){  
            return null;  
        }  
        String pro = "set"+fldname.substring(0,1).toUpperCase()+fldname.substring(1);  
        return pro;  
    }  
    /**  
     * 判断该方法是否存在  
     * @param methods  
     * @param met  
     * @return  
     */  
    public static boolean checkMethod(Method methods[],String met){  
        if(null != methods ){  
            for(Method method:methods){  
                if(met.equals(method.getName())){  
                    return true;  
                }  
            }  
        }          
        return false;  
    }  
	
	
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
