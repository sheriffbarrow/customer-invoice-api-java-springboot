package sheriff.customer.invoice.management.service;

import sheriff.customer.invoice.management.domain.User;
import sheriff.customer.invoice.management.dto.UserDTO;

public interface UserService {
    UserDTO createUser(User user);
}
