package cn.yzy.testServer.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.StringUtils;
import com.alibaba.druid.pool.DruidDataSource;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
@MapperScan(
        basePackages = "cn.yzy.testServer.dao",
        sqlSessionFactoryRef = "sqlSessionFactory")
@EnableTransactionManagement
public class DataSourceConfig {
	@Autowired
	private Environment env;
	@Bean
	public DataSource dataSource(){
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setDriverClassName(env.getProperty("mysql.driverClassName"));
        dataSource.setUrl(env.getProperty("mysql.businessurl"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        dataSource.setMinIdle(Integer.parseInt(env.getProperty("mysql.minIdle")));
        dataSource.setMaxActive(Integer.parseInt(env.getProperty("mysql.maxActive")));
        return dataSource;
	}
	@Bean("sqlSessionFactory")//这里要确定路径
	public SqlSessionFactory SqlSessionFactory(DataSource datasource) throws Exception{
		SqlSessionFactoryBean SessionFactoryBean=new SqlSessionFactoryBean();
		String mapperpackage=env.getProperty("spring.mybatis.mapperPackage");
		SessionFactoryBean.setDataSource(datasource);
		if (!StringUtils.isEmpty(mapperpackage)) {//如果不是空则设置路径
			ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
			SessionFactoryBean.setMapperLocations(resourcePatternResolver.getResources(mapperpackage));
		}
		return SessionFactoryBean.getObject();		
	}
	
}
