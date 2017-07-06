package com.qf.springboot.config;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @ClassName: SendaryDataSourceConfig
 * @Description:
 * @author haichangzhang
 * @date 2017年6月8日 下午5:40:09
 * 
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactorySecondary", transactionManagerRef = "transactionManagerSecondary", basePackages = "com.qf.springboot.secondary.repository")
public class SecondaryDataSourceConfig {

	@Autowired
	@Qualifier("secondaryDataSource")
	private DataSource dataSource;

	@Autowired
	private JpaProperties jpaProperties;

	@Bean(name = "entityMangerSecondary")
	public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
		return entityManagerFactoryPrimary(builder).getObject().createEntityManager();
	}

	/**
	 * @Title: entityManagerFactoryPrimary @Description:
	 *         TODO(这里用一句话描述这个方法的作用) @param @param builder @param @return
	 *         设定文件 @return LocalContainerEntityManagerFactoryBean 返回类型 @throws
	 */
	@Bean(name = "entityManagerFactorySecondary")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
		return builder.dataSource(dataSource).properties(jpaProperties.getHibernateProperties(dataSource))
				.persistenceUnit("secondaryPersistenceUnit").packages("com.qf.springboot.model").build();
	}

	@Bean(name = "transactionManagerSecondary")
	public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
		return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
	}

}
