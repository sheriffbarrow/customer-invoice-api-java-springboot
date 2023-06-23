package sheriff.customer.invoice.management.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import java.time.LocalDateTime;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_DEFAULT;

@Data
@NoArgsConstructor
@SuperBuilder
@AllArgsConstructor
@JsonInclude(NON_DEFAULT)
public class User {
    private Long id;
    @NotEmpty(message = "First name can not be empty")
    private String firstName;
    @NotEmpty(message = "Last name can not be empty")
    private String lastName;
    @NotEmpty(message = "email name can not be empty")
    @NotEmpty(message = "Invalid email. Please enter a valid email address")
    private String email;
    @NotEmpty(message = "Password can not be empty")
    private String password;
    private String phone;
    private String title;
    private String address;
    private String bio;
    private boolean enabled;
    private boolean isNotLocked;
    private String imageUrl;
    private LocalDateTime createdAt;

}
