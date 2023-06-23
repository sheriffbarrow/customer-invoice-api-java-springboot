package sheriff.customer.invoice.management.repository;
import java.util.Collection;
import sheriff.customer.invoice.management.domain.User;

public interface UserRepository<T extends User> {
    /* basic crud application */
    T create(T data);
    Collection<T> list(int age, int pageSize);
    T get(Long id);
    T update (T data);
    Boolean delete(Long id);
}
