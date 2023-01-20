package es.jose.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"es.jose.model.db1.dao"},
  entityManagerFactoryRef = "db1EntityManager",
  transactionManagerRef = "db1TransactionManager"
)
@PropertySource("classpath:application.properties")
public class DatasourceBd1Config {
	
	@Bean
    @ConfigurationProperties(prefix = "bbdd1.datasource")
    public JndiPropertyHolder jndiDatabase1() {
        return new JndiPropertyHolder();
    }
	
	@Bean("db1DataSource")
	@Primary
    public DataSource db1DataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(jndiDatabase1().getJndiName());
    }
	
	@Bean("db1EntityManager")
	@Primary
	public LocalContainerEntityManagerFactoryBean db1EntityManager(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db1DataSource") DataSource serversDataSource) throws IllegalArgumentException, NamingException {

		return builder
				.dataSource(serversDataSource)
				.packages("es.jose.model.db1.vo")
				.build();

	}

	@Bean("db1TransactionManager")
	@Primary
	public JpaTransactionManager db1TransactionManager(
			@Qualifier("db1EntityManager") EntityManagerFactory serversEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(serversEntityManager);

		return transactionManager;
	}

}