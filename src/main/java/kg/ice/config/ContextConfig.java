package kg.ice.config;

import org.dbunit.DataSourceDatabaseTester;
import org.dbunit.util.fileloader.XlsDataFileLoader;
import org.springframework.context.annotation.*;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

@Configuration
@ImportResource("classpath:datasource-tx-jpa.xml")
@ComponentScan(basePackages = "kg.ice")
@Profile("test")
public class ContextConfig {
    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("script/schema.sql").build();
    }

    @Bean(name = "databaseTester")
    public DataSourceDatabaseTester dataSourceDatabaseTester() {
        return new DataSourceDatabaseTester(dataSource());
    }

    @Bean(name = "xlsDataFileLoader")
    public XlsDataFileLoader xlsDataFileLoader() {
        return new XlsDataFileLoader();
    }

}
