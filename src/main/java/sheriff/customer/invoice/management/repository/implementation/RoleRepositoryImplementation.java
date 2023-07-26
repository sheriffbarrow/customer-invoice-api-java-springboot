package sheriff.customer.invoice.management.repository.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sheriff.customer.invoice.management.exception.ApiException;
import sheriff.customer.invoice.management.domain.Role;
import sheriff.customer.invoice.management.repository.RoleRepository;
import sheriff.customer.invoice.management.roleRowMapper.RoleRowMapper;

import java.util.Collection;
import java.util.Map;
import java.util.Objects;

import static java.util.Map.*;
import static sheriff.customer.invoice.management.enumeration.RoleType.ROLE_USER;
import static sheriff.customer.invoice.management.query.RoleQuery.*;


@Repository
@RequiredArgsConstructor
@Slf4j
public class RoleRepositoryImplementation implements RoleRepository<Role> {

    private final NamedParameterJdbcTemplate jdbc;

    @Override
    public Role create(Role data) {
        return null;
    }

    @Override
    public Collection<Role> list(int age, int pageSize) {
        return null;
    }

    @Override
    public Role get(Long id) {
        return null;
    }

    @Override
    public Role update(Role data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    @Override
    public void addRoleToUser(Long userId, String roleName) {
        log.info("Adding role: {} to user id: {}", roleName, userId);
        try{
             Role role = jdbc.queryForObject(SELECT_ROLE_BY_NAME_QUERY, of("name", roleName), new RoleRowMapper());
             jdbc.update(INSERT_ROLE_TO_USER_QUERY, of("userId", userId, "roleId", Objects.requireNonNull(role).getId()));
        }catch (EmptyResultDataAccessException exception) {
            throw new ApiException("No role found by name: " + ROLE_USER.name());
        }catch (Exception exception){
            log.error(exception.getMessage());
            throw new ApiException("An error occurred adding role. Please try again.");
        }
    }

    @Override
    public Role getRoleByUserId(Long userId) {
        return null;
    }

    @Override
    public Role getRoleByEmail(String email) {
        return null;
    }

    @Override
    public void updateUserRole(Long userId, String roleName) {

    }
}
