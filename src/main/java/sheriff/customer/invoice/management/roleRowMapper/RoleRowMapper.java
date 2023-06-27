package sheriff.customer.invoice.management.roleRowMapper;

import org.springframework.jdbc.core.RowMapper;
import sheriff.customer.invoice.management.domain.Role;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleRowMapper implements RowMapper<Role> {
    @Override
    public Role mapRow(ResultSet resultSet, int rowNum) throws SQLException {
        return Role.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("id"))
                .permission(resultSet.getString("permission"))
                .build();















    }
}
