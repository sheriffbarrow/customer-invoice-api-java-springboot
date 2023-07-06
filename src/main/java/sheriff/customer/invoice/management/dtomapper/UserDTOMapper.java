package sheriff.customer.invoice.management.dtomapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import sheriff.customer.invoice.management.domain.User;
import sheriff.customer.invoice.management.dto.UserDTO;

@Component
public class UserDTOMapper {
    public static UserDTO fromUser(User user){
        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(user, userDTO);
        return userDTO;
    }

    public static User toUser(UserDTO userDTO){
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }
}