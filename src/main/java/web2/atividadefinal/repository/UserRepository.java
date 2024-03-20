package web2.atividadefinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.atividadefinal.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
