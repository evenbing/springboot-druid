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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.xa.DruidXADataSource;
import com.atomikos.jdbc.AtomikosDataSourceBean;

import javax.sql.DataSource;

/**
 * Created with IDEA
 * User: vector
 * Data: 2017/8/22
 * Time: 18:05
 * Description:
 */
@Configuration
@MapperScan(basePackages = "com.easybcp.biz.dao", sqlSessionTemplateRef  = "secondarySqlSessionTemplate")
public class DataSourceSecondaryConfig {

	@Bean(name = "secondaryDataSource1",initMethod="init",destroyMethod="close")
    @ConfigurationProperties(prefix = "spring.datasource.primary") 
    public DataSource secondaryDataSource1() 
    {
    	DruidXADataSource druidDataSource = new DruidXADataSource();  
    	
        return druidDataSource;  
    }
	
    @Bean(name = "secondaryDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.secondary")
    public DataSource testDataSource2() {
    	DruidXADataSource druidDataSource = (DruidXADataSource) secondaryDataSource1();  
    	AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
    	xaDataSource.setXaDataSource(druidDataSource);
		xaDataSource.setUniqueResourceName("secondaryDataSource");

        return xaDataSource;  
    }

    @Bean(name = "secondarySqlSessionFactory")
    public SqlSessionFactory testSqlSessionFactory(@Qualifier("secondaryDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        System.out.println(dataSource.getClass().getName()+"------------------------------");
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/com/easybcp/biz/**/*.xml"));
        return bean.getObject();
    }

  /*  @Bean(name = "secondaryTransactionManager")
    public DataSourceTransactionManager testTransactionManager(@Qualifier("secondaryDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }*/

    @Bean(name = "secondarySqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(@Qualifier("secondarySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
