package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Answer;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.entity.Task;
import uz.pdp.apicodingbat.entity.User;
import uz.pdp.apicodingbat.payload.AnswerDto;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.TaskDto;
import uz.pdp.apicodingbat.repository.AnswerRepository;
import uz.pdp.apicodingbat.repository.LanguageRepository;
import uz.pdp.apicodingbat.repository.TaskRepository;
import uz.pdp.apicodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AnswerService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    AnswerRepository answerRepository;
    @Autowired
    UserRepository userRepository;


    public ApiResponse add(AnswerDto answerDto) {

        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi ",false);
        Task task = optionalTask.get();
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday foydalanuvchi topilmadi ",false);
        User user = optionalUser.get();
        Answer answer=new Answer(null,answerDto.getText(),answerDto.isCorrect(),task,user);
        answerRepository.save(answer);
        return new ApiResponse("Yangi javob saqlandi", true);
    }


    public List<Answer> get() {
        List<Answer> answerList = answerRepository.findAll();
        return answerList;
    }


    public Answer getByID(Integer id) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        return optionalAnswer.orElse(null);
    }


    public ApiResponse delete(Integer id) {
        try {
            answerRepository.deleteById(id);
            return new ApiResponse("Javob o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Javob  topilmadi", false);
        }
    }


    public ApiResponse edit(Integer id, AnswerDto answerDto) {
        Optional<Answer> optionalAnswer = answerRepository.findById(id);
        if (!optionalAnswer.isPresent())
            return new ApiResponse("Bunday javob topilmadi ",false);
        Answer editingAnswer = optionalAnswer.get();
        Optional<Task> optionalTask = taskRepository.findById(answerDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi ",false);
        Task task = optionalTask.get();
        Optional<User> optionalUser = userRepository.findById(answerDto.getUserId());
        if (!optionalUser.isPresent())
            return new ApiResponse("Bunday foydalanuvchi topilmadi ",false);
        User user = optionalUser.get();
        editingAnswer.setText(answerDto.getText());
        editingAnswer.setCorrect(answerDto.isCorrect());
        editingAnswer.setTask(task);
        editingAnswer.setUser(user);
        answerRepository.save(editingAnswer);
        return new ApiResponse("Tanlangan javob tahrirlandi",true);
    }
}
