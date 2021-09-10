package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.entity.Task;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.TaskDto;
import uz.pdp.apicodingbat.repository.CategoryRepository;
import uz.pdp.apicodingbat.repository.LanguageRepository;
import uz.pdp.apicodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    LanguageRepository languageRepository;
    @Autowired
    CategoryRepository  categoryRepository;


    public ApiResponse add(TaskDto taskDto) {
        boolean existsByName = taskRepository.existsByName(taskDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday vazifa bazada mavjud ",false);
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageID());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til topilmadi ",false);
        Language language = optionalLanguage.get();
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryID());
        if(!optionalCategory.isPresent())
            return new ApiResponse("Bunday kategoriya topilmadi ",false);
        Category category = optionalCategory.get();
        Task task=new Task(null,taskDto.getName(),taskDto.getCondition(),taskDto.getSolution(),taskDto.getHint(),taskDto.getMethod(),taskDto.isHasStar(),language,category);
        taskRepository.save(task);
        return new ApiResponse("Yangi vazifa saqlandi", true);
    }


    public List<Task> get() {
        List<Task> taskList = taskRepository.findAll();
        return taskList;
    }


    public Task getByID(Integer id) {
        Optional<Task> optionalTask = taskRepository.findById(id);
        return optionalTask.orElse(null);
    }


    public ApiResponse delete(Integer id) {
        try {
            taskRepository.deleteById(id);
            return new ApiResponse("Vazifa o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Vazifa  topilmadi", false);
        }
    }


    public ApiResponse edit(Integer id, TaskDto taskDto) {
        boolean existsByName = taskRepository.existsByName(taskDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday vazifa bazada mavjud ",false);
        Optional<Task> optionalTask = taskRepository.findById(id);
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi ",false);
        Task editingTask = optionalTask.get();
        Optional<Language> optionalLanguage = languageRepository.findById(taskDto.getLanguageID());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til topilmadi ",false);
        Optional<Category> optionalCategory = categoryRepository.findById(taskDto.getCategoryID());
        if(!optionalCategory.isPresent())
            return new ApiResponse("Bunday kategoriya topilmadi ",false);
        Category category = optionalCategory.get();

        Language language = optionalLanguage.get();
        editingTask.setCondition(taskDto.getCondition());
        editingTask.setHint(taskDto.getHint());
        editingTask.setName(taskDto.getName());
        editingTask.setHasStar(taskDto.isHasStar());
        editingTask.setLanguage(language);
        editingTask.setSolution(taskDto.getSolution());
        editingTask.setMethod(taskDto.getMethod());
        editingTask.setCategory(category);
        taskRepository.save(editingTask);
        return new ApiResponse("Tanlangan til turi tahrirlandi",true);
    }
}
