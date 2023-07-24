package sheriff.customer.invoice.management.repository.implementation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import sheriff.customer.invoice.management.domain.Role;
import sheriff.customer.invoice.management.domain.User;
import sheriff.customer.invoice.management.exception.ApiException;
import sheriff.customer.invoice.management.repository.RoleRepository;
import sheriff.customer.invoice.management.repository.UserRepository;
import sheriff.customer.invoice.management.resource.UserResource;

import java.util.Collection;
import java.util.UUID;

import static java.util.Map.of;
import static java.util.Objects.requireNonNull;
import static sheriff.customer.invoice.management.enumeration.RoleType.ROLE_USER;
import static sheriff.customer.invoice.management.enumeration.VerificationType.ACCOUNT;
import static sheriff.customer.invoice.management.query.UserQuery.*;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImplementation implements UserRepository<User> {
    private final NamedParameterJdbcTemplate jdbc;
    private final RoleRepository<Role> roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public User create(User user) {
        // check if email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use a different email and try again.");
        }
        // save new user
        try{
            KeyHolder holder = new GeneratedKeyHolder();
            SqlParameterSource parameters = getSqlParameterSource(user);
            jdbc.update(INSERT_USER_QUERY, parameters, holder);
            user.setId(requireNonNull(holder.getKey()).longValue());
            UserResource userResource = null;
            userResource.saveUser(user);
            // add role to the user
            roleRepository.addRoleToUser(user.getId(), ROLE_USER.name());

            // send verification URL
            String verificationUrl = getVerificationUrl(UUID.randomUUID().toString(), ACCOUNT.getType());
            System.out.println(verificationUrl);
            // save URL in verification table
            jdbc.update(INSERT_VERIFICATION_QUERY, of("userId", user.getId(), "url", verificationUrl));

            // send email to user with verification URL
            //emailSerivice.sendVerificationUrl(user.getFirstName(), user.getEmail(), verificationUrl, ACCOUNT);
            user.setEnabled(false);
            user.setNotLocked(true);

            // return the newly created user
            System.out.println(user);
            return user;
            // if any errors, throw exception with proper message
        }catch (Exception exception){
            throw new ApiException("An error occurred creating user. Please try again.");
        }

    }

    @Override
    public Collection<User> list(int age, int pageSize) {
        return null;
    }

    @Override
    public User get(Long id) {
        return null;
    }

    @Override
    public User update(User data) {
        return null;
    }

    @Override
    public Boolean delete(Long id) {
        return null;
    }

    private Integer getEmailCount(String email){
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, of("email", email), Integer.class);
    }

    public SqlParameterSource getSqlParameterSource(User user) {
        return new MapSqlParameterSource()
                .addValue("firstName", user.getFirstName())
                .addValue("lastName", user.getLastName())
                .addValue("email", user.getEmail())
                .addValue("password", encoder.encode(user.getPassword()));
    }

    private String getVerificationUrl(String key, String type){
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/verify/" + type + "/" + key).toUriString();
    }
}
