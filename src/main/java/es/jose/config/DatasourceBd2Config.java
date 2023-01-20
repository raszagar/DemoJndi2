package es.jose.config;

import javax.naming.NamingException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
  basePackages = {"es.jose.model.db2.dao"},
  entityManagerFactoryRef = "db2EntityManager",
  transactionManagerRef = "db2TransactionManager"
)
public class DatasourceBd2Config {

	@Bean
    @ConfigurationProperties(prefix = "bbdd2.datasource")
    public JndiPropertyHolder jndiDatabase2() {
        return new JndiPropertyHolder();
    }
	
	@Bean("db2DataSource")
    public DataSource db2DataSource() {
        JndiDataSourceLookup dataSourceLookup = new JndiDataSourceLookup();
        return dataSourceLookup.getDataSource(jndiDatabase2().getJndiName());
    }
	
	@Bean
	public LocalContainerEntityManagerFactoryBean db2EntityManager(
			EntityManagerFactoryBuilder builder,
			@Qualifier("db2DataSource") DataSource serversDataSource) throws IllegalArgumentException, NamingException {

		return builder
				.dataSource(serversDataSource)
				.packages("es.jose.model.db2.vo")
				.build();

	}

	@Bean
	public JpaTransactionManager db2TransactionManager(
			@Qualifier("db2EntityManager") EntityManagerFactory serversEntityManager) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(serversEntityManager);

		return transactionManager;
	}

}