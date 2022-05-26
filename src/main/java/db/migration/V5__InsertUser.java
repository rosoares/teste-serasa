package db.migration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class V5__InsertUser extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        String password = new BCryptPasswordEncoder().encode("strongPassword");

        new JdbcTemplate(new SingleConnectionDataSource(context.getConnection(), true))
                .execute("INSERT INTO usuario (id, email, pass) VALUES (1, 'admin@gmail.com', '" + password + "')");
    }
}
