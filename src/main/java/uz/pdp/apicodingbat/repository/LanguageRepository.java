package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Language;

public interface LanguageRepository extends JpaRepository<Language,Integer> {
    boolean existsByName(String name);
}

