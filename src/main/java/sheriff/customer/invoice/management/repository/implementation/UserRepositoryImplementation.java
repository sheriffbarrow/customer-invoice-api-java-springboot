package sheriff.customer.invoice.management.repository.implementation;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import sheriff.customer.invoice.management.Exception.ApiException;
import sheriff.customer.invoice.management.domain.User;
import sheriff.customer.invoice.management.repository.UserRepository;

import java.util.Collection;
import java.util.Map;

@Repository
@RequiredArgsConstructor
@Slf4j
public class UserRepositoryImplementation implements UserRepository<User> {
    private static final  String COUNT_USER_EMAIL_QUERY = "";
    private NamedParameterJdbcTemplate jdbc;
    @Override
    public User create(User user) {
        // check if email is unique
        if(getEmailCount(user.getEmail().trim().toLowerCase()) > 0) {
            throw new ApiException("Email already in use. Please use a different email and try again.");
        }
        // save new user
        // add role to the user
        // send verification URL
        // save URL in verification table
        // send email to user with verification URL
        // return the newly created user
        // if any errors, throw exception with proper message
        return null;
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
        return jdbc.queryForObject(COUNT_USER_EMAIL_QUERY, Map.of("email", email), Integer.class);
    }
}
