package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Example;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.entity.Task;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.ExampleDto;
import uz.pdp.apicodingbat.payload.TaskDto;
import uz.pdp.apicodingbat.repository.ExampleRepository;
import uz.pdp.apicodingbat.repository.LanguageRepository;
import uz.pdp.apicodingbat.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ExampleService {
    @Autowired
    TaskRepository taskRepository;
    @Autowired
    ExampleRepository exampleRepository;


    public ApiResponse add(ExampleDto exampleDto) {
        boolean existsByName = exampleRepository.existsByText(exampleDto.getText());
        if (existsByName)
            return new ApiResponse("Bunday namuna bazada mavjud ",false);
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi ",false);
        Task task1 = optionalTask.get();
        Example example=new Example(null,exampleDto.getText(),task1);
        exampleRepository.save(example);
        return new ApiResponse("Yangi namuna saqlandi", true);
    }


    public List<Example> get() {
        List<Example> exampleList = exampleRepository.findAll();
        return exampleList;
    }


    public Example getByID(Integer id) {
        Optional<Example> optionalExample = exampleRepository.findById(id);
        return optionalExample.orElse(null);
    }

    public ApiResponse delete(Integer id) {
        try {
            exampleRepository.deleteById(id);
            return new ApiResponse("Namuna o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Namuna  topilmadi", false);
        }
    }


    public ApiResponse edit(Integer id, ExampleDto exampleDto) {
        boolean existsByName = exampleRepository.existsByText(exampleDto.getText());
        if (existsByName)
            return new ApiResponse("Bunday namuna bazada mavjud ",false);
        Optional<Example> optionalExample = exampleRepository.findById(id);
        if (!optionalExample.isPresent())
            return  new ApiResponse("Bunday namuna bazadan topilmadi ",false);
        Example editingExample = optionalExample.get();
        Optional<Task> optionalTask = taskRepository.findById(exampleDto.getTaskId());
        if (!optionalTask.isPresent())
            return new ApiResponse("Bunday vazifa topilmadi ",false);
        Task task1 = optionalTask.get();
        editingExample.setText(exampleDto.getText());
        editingExample.setTask(task1);
        exampleRepository.save(editingExample);
        return new ApiResponse("Tanlangan namuna tahrirlandi",true);
    }
}
