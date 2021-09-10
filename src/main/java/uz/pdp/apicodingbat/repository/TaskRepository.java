package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Task;

public interface TaskRepository extends JpaRepository<Task,Integer> {
   boolean existsByName(String name);
}

