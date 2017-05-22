package com.cadenzauk.siesta.oracle;

import com.cadenzauk.core.sql.PooledDataSource;
import com.cadenzauk.siesta.Dialect;
import com.cadenzauk.siesta.TableIntegrationTest;
import com.cadenzauk.siesta.dialect.OracleDialect;
import oracle.jdbc.pool.OracleConnectionPoolDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;
import java.sql.SQLException;

@ContextConfiguration
public class TableIntegrationTestOracle extends TableIntegrationTest {
    @Configuration
    public static class Config {
        @Bean
        public DataSource dataSource() throws SQLException {
            OracleConnectionPoolDataSource pool = new OracleConnectionPoolDataSource();
            pool.setUser("siesta");
            pool.setPassword("siesta");
            pool.setURL("jdbc:oracle:thin:@127.0.0.1:1521:xe");
            return new PooledDataSource(pool);
        }

        @Bean
        public Dialect dialect() {
            return new OracleDialect();
        }
    }
}
