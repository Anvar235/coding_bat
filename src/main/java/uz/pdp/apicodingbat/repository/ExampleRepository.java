package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Example;
import uz.pdp.apicodingbat.entity.Task;

public interface ExampleRepository extends JpaRepository<Example,Integer> {
    boolean existsByText(String text);
}

