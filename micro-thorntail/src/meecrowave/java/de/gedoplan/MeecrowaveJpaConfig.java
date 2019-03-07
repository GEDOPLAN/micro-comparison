package de.gedoplan;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.apache.meecrowave.jpa.api.PersistenceUnitInfoBuilder;
import org.h2.jdbcx.JdbcDataSource;

@ApplicationScoped
public class MeecrowaveJpaConfig {
  @Produces
  public PersistenceUnitInfoBuilder unit(DataSource ds) {
    return new PersistenceUnitInfoBuilder()
        .setUnitName("showcase")
        .setDataSource(ds)
        .setExcludeUnlistedClasses(false)
        .addProperty("openjpa.RuntimeUnenhancedClasses", "supported")
        .addProperty("openjpa.jdbc.SynchronizeMappings", "buildSchema");
  }

  @Produces
  @ApplicationScoped
  public DataSource dataSource() {
    JdbcDataSource ds = new JdbcDataSource();
    ds.setURL("jdbc:h2:mem:showcase");
    ds.setUser("sa");
    ds.setPassword("sa");
    return ds;
  }

}
