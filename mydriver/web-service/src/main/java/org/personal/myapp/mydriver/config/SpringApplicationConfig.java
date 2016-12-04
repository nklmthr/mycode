package org.personal.myapp.mydriver.config;

import static springfox.documentation.builders.PathSelectors.regex;

import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.orm.jpa.JpaDialect;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableEncryptableProperties
@PropertySources({ @PropertySource("classpath:application-${spring.profiles.active:dev}.properties"),
		@PropertySource(value = "classpath:local.properties", ignoreResourceNotFound = true) })
public class SpringApplicationConfig {

	private static final Logger logger = LoggerFactory.getLogger(SpringApplicationConfig.class);

	@Value("${jdbc.username}")
	private String username;

	@Value("${jdbc.password}")
	private String password;

	@Value("${jdbc.driverClassName}")
	private String driverClassName;

	@Value("${jdbc.driverUrl}")
	private String driverUrl;

	@Value("${conn.pool.maxTotal}")
	private int maxActive;

	@Value("${conn.pool.maxIdle}")
	private int maxIdle;

	@Value("${conn.pool.minIdle}")
	private int minIdle;

	@Value("${conn.pool.maxWaitMillis}")
	private int maxWaitMillis;

	@Value("${conn.pool.validationQuery}")
	private String validationQuery;

	@Value("${conn.pool.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${conn.pool.validationInterval}")
	private long validationInterval;

	@Value("${conn.pool.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${conn.pool.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${hibernate.dialect}")
	private String hibernateDialect;

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource());
		em.setPackagesToScan(new String[] { "org.personal.myapp.mydriver.dto" });
		HibernateJpaVendorAdapter hVendorAdapter = new HibernateJpaVendorAdapter();
		hVendorAdapter.setDatabase(Database.ORACLE);
		JpaVendorAdapter vendorAdapter = hVendorAdapter;
		em.setJpaVendorAdapter(vendorAdapter);
		return em;
	}

	@Bean
	public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(emf);
		return txManager;
	}

	@Bean
	public DataSource dataSource() {

		BasicDataSource dataSource = new BasicDataSource();

		logger.info("**************** Starting DB Connections ****************");
		logger.debug(driverUrl + "|" + driverClassName + "|" + username + "|" + password);

		// PoolProperties p = new PoolProperties();
		dataSource.setUrl(driverUrl);
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setValidationQuery(validationQuery);
		dataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		dataSource.setTestWhileIdle(testWhileIdle);
		return dataSource;

	}

	@Bean(name = "org.dozer.Mapper")
	public DozerBeanMapper dozerBean() {
		List<String> mappingFiles = Arrays.asList("dozer-mapping.xml");
		DozerBeanMapper dozerBean = new DozerBeanMapper();
		dozerBean.setMappingFiles(mappingFiles);
		return dozerBean;
	}

	@Bean
	public Docket newsApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select().paths(regex("/mydriver/.*")).build();
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder().title("MyDriver Service").description("My Driver Service ").version("2.0").build();
	}

	@Bean
	public FilterRegistrationBean requesrResFilterRegistration() {

		FilterRegistrationBean registration = new FilterRegistrationBean();
		registration.setFilter(requestResponseFilter());
		registration.addUrlPatterns("/mydriver/*");
		registration.setName("requestResponseFilter");
		return registration;
	}

	@Bean(name = "requestResponseFilter")
	public RequestResponseFilter requestResponseFilter() {
		return new RequestResponseFilter();
	}
}
