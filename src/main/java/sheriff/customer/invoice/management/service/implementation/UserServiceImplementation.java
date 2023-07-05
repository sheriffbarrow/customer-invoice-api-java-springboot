package sheriff.customer.invoice.management.service.implementation;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sheriff.customer.invoice.management.domain.User;
import sheriff.customer.invoice.management.dto.UserDTO;
import sheriff.customer.invoice.management.dtomapper.UserDTOMapper;
import sheriff.customer.invoice.management.repository.UserRepository;
import sheriff.customer.invoice.management.service.UserService;

@Service
@RequiredArgsConstructor
public class UserServiceImplementation implements UserService {
    private final UserRepository<User> userUserRepository;
    @Override
    public UserDTO createUser(User user) {
        return UserDTOMapper.fromUser(userUserRepository.create(user));
    }
}














