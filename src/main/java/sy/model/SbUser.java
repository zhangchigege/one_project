package sy.model;/**
 * @program: zxshow
 * @description: 社保查询用户信息实体
 * @author: ZhangChi
 * @create: 2019-03-11 12:30
 **/

public class SbUser {
	String uid;//表id
	String comPany_Name; //单位名称
    String unified_Social_Credit_Code;//统一社会信用代码
    String social_Security_Registration_Number;//社会保险登记号
    String county;//所属区县
    String userName;//用户名称
    String id_Number;//身份证号
    String date;//出生日期
    String sex;//性别
    String nation;//民族
    String state;//国家
    String pin;//个人身份
    String date_Of_Employment;//参加工作日期
    String account_Properties; //户口性质
    String registered_Permanent_Residence;//户口所在地
    String place_Of_Abode;//居住地
    String phone;//参保人电话
    String salary;//参保人工资
    String bank_Account;//银行账号
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getComPany_Name() {
		return comPany_Name;
	}
	public void setComPany_Name(String comPany_Name) {
		this.comPany_Name = comPany_Name;
	}
	public String getUnified_Social_Credit_Code() {
		return unified_Social_Credit_Code;
	}
	public void setUnified_Social_Credit_Code(String unified_Social_Credit_Code) {
		this.unified_Social_Credit_Code = unified_Social_Credit_Code;
	}
	public String getSocial_Security_Registration_Number() {
		return social_Security_Registration_Number;
	}
	public void setSocial_Security_Registration_Number(String social_Security_Registration_Number) {
		this.social_Security_Registration_Number = social_Security_Registration_Number;
	}
	public String getCounty() {
		return county;
	}
	public void setCounty(String county) {
		this.county = county;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getId_Number() {
		return id_Number;
	}
	public void setId_Number(String id_Number) {
		this.id_Number = id_Number;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getNation() {
		return nation;
	}
	public void setNation(String nation) {
		this.nation = nation;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public String getDate_Of_Employment() {
		return date_Of_Employment;
	}
	public void setDate_Of_Employment(String date_Of_Employment) {
		this.date_Of_Employment = date_Of_Employment;
	}
	public String getAccount_Properties() {
		return account_Properties;
	}
	public void setAccount_Properties(String account_Properties) {
		this.account_Properties = account_Properties;
	}
	public String getRegistered_Permanent_Residence() {
		return registered_Permanent_Residence;
	}
	public void setRegistered_Permanent_Residence(String registered_Permanent_Residence) {
		this.registered_Permanent_Residence = registered_Permanent_Residence;
	}
	public String getPlace_Of_Abode() {
		return place_Of_Abode;
	}
	public void setPlace_Of_Abode(String place_Of_Abode) {
		this.place_Of_Abode = place_Of_Abode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public String getBank_Account() {
		return bank_Account;
	}
	public void setBank_Account(String bank_Account) {
		this.bank_Account = bank_Account;
	}
	@Override
	public String toString() {
		return "SbUser [uid=" + uid + ", comPany_Name=" + comPany_Name + ", unified_Social_Credit_Code="
				+ unified_Social_Credit_Code + ", social_Security_Registration_Number="
				+ social_Security_Registration_Number + ", county=" + county + ", userName=" + userName + ", id_Number="
				+ id_Number + ", date=" + date + ", sex=" + sex + ", nation=" + nation + ", state=" + state + ", pin="
				+ pin + ", date_Of_Employment=" + date_Of_Employment + ", account_Properties=" + account_Properties
				+ ", registered_Permanent_Residence=" + registered_Permanent_Residence + ", place_Of_Abode="
				+ place_Of_Abode + ", phone=" + phone + ", salary=" + salary + ", bank_Account=" + bank_Account + "]";
	}
    




}
