package cn.yzy.testServer.entity;

/**
 * 
 * Created by yuzaiang on 2017/10/21
 * 
 * */

//注册信息领域对象
public class insertMSG {

	String phone,name,eid,pwd;
	int id;
	int cf;//数据重复的条数
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEid() {
		return eid;
	}
	public void setEid(String eid) {
		this.eid = eid;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getCf() {
		return cf;
	}
	public void setCf(int cf) {
		this.cf = cf;
	}
}
