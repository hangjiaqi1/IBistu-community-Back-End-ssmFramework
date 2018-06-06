package cn.yzy.testServer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import cn.yzy.testServer.dao.UserMapper;
import cn.yzy.testServer.entity.Checkall;
import cn.yzy.testServer.entity.DataResult;
import cn.yzy.testServer.entity.Update;
@Service
public class IBistuService {
	@Autowired
	UserMapper UserMapper;
	//验证密码匹配性
	public DataResult Login(JSONObject param){
		String  name=param.getString("name");
		String  pwd=param.getString("pwd");
		String pwdcheck=UserMapper.chekPWD(name);
		if(pwdcheck.equalsIgnoreCase(pwd)){
			//如果密码匹配一致则返回为空
			System.out.println("密码匹配");
			return new DataResult();			
		}
		else{
			//如果密码匹配不一致，则返回String值0001
			System.out.println("密码不匹配");
			String a="0001";
			return new DataResult(a);
		}	
	}

	
	//商城下单模块
	public DataResult Order(JSONObject param){
		String goodsID=param.getString("goodsID");
		String number=param.getString("number");
		int numbers=Integer.parseInt(number);
		
		//查询库存数
		int Stock=UserMapper.Order(goodsID);
		int num=Stock-numbers;
		
		//库存数小于购买数返回0002
		if(Stock<numbers){	
			System.out.println("库存不足");
			return new DataResult("0002");
		}
		
		//库存数够返回0003
		//更新库存数信息
		else{
			System.out.println("库存足");
			Update update=new Update();
			update.setGooddsID(goodsID);
			update.setStock(num);
			//更新库存数
			UserMapper.updateStock(update);			
			return new DataResult("0003");			
		}		
	}
	
	//学生字典模块
		public DataResult CheckAll() {
			List<Checkall> StudentMsg=UserMapper.CheckAll();
			//list转换成json格式
			String json = JSON.toJSONString(StudentMsg);
			System.out.println(json);
			return new DataResult(json);	
		}
	
	
}
