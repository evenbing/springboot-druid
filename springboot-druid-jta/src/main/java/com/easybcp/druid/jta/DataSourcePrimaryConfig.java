package com.easybcp.druid.jta;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.sql.DataSource;
import javax.sql.XADataSource;


@Configuration
@MapperScan(basePackages = "com.easybcp.sys.dao", sqlSessionTemplateRef  = "primarySqlSessionTemplate")
public class DataSourcePrimaryConfig {

	@Bean(name = "primaryDataSource1",initMethod="init",destroyMethod="close")
    @ConfigurationProperties(prefix = "spring.datasource.primary")
    
    public DataSource primaryDataSource1() 
    {
    	DruidXADataSource druidDataSource = new DruidXADataSource();  
    	
        return druidDataSource;  
    }
    @Bean(name = "primaryDataSource")
    @Primary
    public DataSource primaryDataSource2() {
    	DataSource druidDataSource =primaryDataSource1();  
    	
    	AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    	xaDataSource.setXaDataSource((XADataSource) druidDataSource);
    	//xaDataSource.setXaDataSourceClassName("com.alibaba.druid.pool.xa.DruidXADataSource");
		xaDataSource.setUniqueResourceName("primaryDataSource");

        return xaDataSource;  
    }

    @Bean(name = "primarySqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("primaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/com/easybcp/sys/**/*.xml"));
        System.out.println(dataSource.getClass().getName()+"------------------------------");
        return bean.getObject();
    }

   /* @Bean(name = "primaryTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("primaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "primarySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("primarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
