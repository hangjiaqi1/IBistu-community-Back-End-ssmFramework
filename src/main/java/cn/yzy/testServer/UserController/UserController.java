package cn.yzy.testServer.UserController;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import cn.yzy.testServer.dao.UserMapper;
import cn.yzy.testServer.entity.DataResult;
import cn.yzy.testServer.entity.insertMSG;
import cn.yzy.testServer.service.IBistuService;
/**
 * 
 * Created by yuzaiang on 2017/10/21
 * 
 * */
//注册模块
@Controller
@RequestMapping({"/home"})
public class UserController {
    @Autowired
    UserMapper userMapper;
    @Autowired
    IBistuService service;
    //服务器测试
    @RequestMapping(value="/test")
    @ResponseBody
    public String testServer(){   	
    	return "success";
    }
    //注册,并校验是否已经有相同注册名
    @RequestMapping(value="/GetMSG")
    @ResponseBody
    public void GetMSG(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException{
        String eid= request.getParameter("eid");
        String pwd= request.getParameter("pwd");  
    	String phone=request.getParameter("phone");
    	String name= request.getParameter("name");  	
    	insertMSG checkmsg=new insertMSG();
    	checkmsg.setPhone(phone);
    	int cf=userMapper.checkMSG(checkmsg);
    	//System.out.println(cf);
    	//再插入数据
    	//先校验(count*获取重复用户名数目，若>1则说明用户名)
    	if(cf>=1){
    		System.out.println("用户名重复");
    		//若失败返回状态码
    		response.setContentType("text/html");
    		PrintWriter out = response.getWriter();
    		out.println("0000");
    		out.flush();
    		out.close();
    	}
    	else{
    	insertMSG insertmsg=new insertMSG();
    	insertmsg.setEid(eid);
    	insertmsg.setName(name);
    	insertmsg.setPhone(phone);
    	insertmsg.setPwd(pwd);   
        //数据库信息并返回插入后该条信息所对应id
    	userMapper.insertMSG(insertmsg);
    	//获取自增id
    	int id=insertmsg.getId();
    	response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println(id);
		out.flush();
		out.close();
    	}	
    }
    
    //登录模块
    //登陆并校验，若是密码不匹配则返回0001给给前端
    @RequestMapping(value="/Login")
    @ResponseBody
    public DataResult Login(@RequestBody JSONObject param){
    	return service.Login(param); 	
    }
    
    //商城下单模块
    //接收商品编号和购买数量，若库存数<购买数量。返回0002。否则购买成功返回0003
    @RequestMapping(value="/Market")
	@ResponseBody
	public DataResult Market(@RequestBody JSONObject param){
    	return service.Order(param);
	}
    
  //返回usermsg中所有注册用户的学生号
    @RequestMapping(value="/CheckAll")
	@ResponseBody
	public DataResult CheckAll() {	
    	return service.CheckAll();
    }
	
    

    
    
}