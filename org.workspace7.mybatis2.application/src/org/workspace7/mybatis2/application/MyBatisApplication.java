package org.workspace7.mybatis2.application;

import javax.sql.DataSource;

import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import osgi.enroute.configurer.api.RequireConfigurerExtender;
import osgi.enroute.google.angular.capabilities.RequireAngularWebResource;
import osgi.enroute.rest.api.REST;
import osgi.enroute.twitter.bootstrap.capabilities.RequireBootstrapWebResource;
import osgi.enroute.webserver.capabilities.RequireWebServerExtender;

@RequireAngularWebResource(resource = { "angular.js", "angular-resource.js", "angular-route.js" }, priority = 1000)
@RequireBootstrapWebResource(resource = "css/bootstrap.css")
@RequireWebServerExtender
@RequireConfigurerExtender
@Component(name = "org.workspace7.mybatis")
public class MyBatisApplication implements REST {

	private static final Logger LOGGER = LoggerFactory.getLogger(MyBatisApplication.class);

	DataSource dataSource;

	// FIXME this core piece need to be moved to OSGi way
	Environment environment;
	Configuration configuration;
	SqlSessionFactory sqlSessionFactory;

	public String getUpper(String strId) {
		LOGGER.info("Getting Persons ..");
		Person person = new Person();
		SqlSession sqlSession = null;
		try {
			long personId = Long.parseLong(strId);
			sqlSession = sqlSessionFactory.openSession();
			person = sqlSession.selectOne(
					PersonMapper.class.getName()+".selectPerson", personId);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		} finally {
			sqlSession.close();
		}
		return person.toString();
	}

	@Reference
	public void setDataSource(DataSource dataSource) {
		LOGGER.debug("Setting DataSource");
		this.dataSource = dataSource;
		// Dirty and Boilerplate code
		environment = new Environment("osgi", new JdbcTransactionFactory(), dataSource);
		configuration = new Configuration(environment);
		configuration.addMapper(PersonMapper.class);
		sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
	}

}
