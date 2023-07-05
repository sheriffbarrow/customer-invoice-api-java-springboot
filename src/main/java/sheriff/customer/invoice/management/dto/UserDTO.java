package sheriff.customer.invoice.management.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String title;
    private String address;
    private String bio;
    private boolean enabled;
    private boolean isNotLocked;
    private String imageUrl;
    private LocalDateTime createdAt;
}
