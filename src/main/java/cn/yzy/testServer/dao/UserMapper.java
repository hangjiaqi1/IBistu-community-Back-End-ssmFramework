package cn.yzy.testServer.dao;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

import cn.yzy.testServer.entity.Checkall;
import cn.yzy.testServer.entity.Update;
import cn.yzy.testServer.entity.User;
import cn.yzy.testServer.entity.insertMSG;
/**
 * 
 * Created by yuzaiang on 2017/10/21
 * 
 * */
@Mapper
public interface UserMapper {
	
	//验证是否有相同账号
	public int checkMSG(insertMSG checkmsg);
	
	//插入注册信息
	public int insertMSG(insertMSG insertmsg);
	
	//查找用户名下所对应的密码
	public String chekPWD(String name);
	
	//商城下单
	public int Order(String goodsID);
	
	//更新商城库存
	public void updateStock(Update update);
	//查询表库所有信息
	public User checkUser();
	
	//学生辞典，查询所有用户学号信息
		public List<Checkall> CheckAll();
}
