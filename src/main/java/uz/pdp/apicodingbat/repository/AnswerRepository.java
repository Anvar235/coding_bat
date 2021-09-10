package uz.pdp.apicodingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.apicodingbat.entity.Answer;
import uz.pdp.apicodingbat.entity.Example;

public interface AnswerRepository extends JpaRepository<Answer,Integer> {

}

