package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Language;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    boolean existsByName(String name);
}

