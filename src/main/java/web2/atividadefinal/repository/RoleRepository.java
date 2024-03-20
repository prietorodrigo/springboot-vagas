package web2.atividadefinal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web2.atividadefinal.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
