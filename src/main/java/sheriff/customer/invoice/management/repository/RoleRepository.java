package sheriff.customer.invoice.management.repository;

import sheriff.customer.invoice.management.domain.Role;
import java.util.Collection;

public interface RoleRepository<T extends Role> {
    /* basic crud application */
    T create(T data);
    Collection<T> list(int age, int pageSize);
    T get(Long id);
    T update (T data);
    Boolean delete(Long id);

    void addRoleToUser(Long userId, String roleName);
}
