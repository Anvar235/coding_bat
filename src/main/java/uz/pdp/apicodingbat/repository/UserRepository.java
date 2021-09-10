package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}

