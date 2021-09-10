package uz.pdp.apicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Task;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.TaskDto;
import uz.pdp.apicodingbat.service.CategoryService;
import uz.pdp.apicodingbat.service.TaskService;

import java.util.List;

@RestController
@RequestMapping("/api/task")
public class TaskController {
    @Autowired
    TaskService taskService;


    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody TaskDto taskDto) {
        ApiResponse apiResponse = taskService.add(taskDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<List<Task>> get() {
        List<Task> taskList = taskService.get();
        return ResponseEntity.ok(taskList);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Task task = taskService.getByID(id);
        return ResponseEntity.ok(task);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse response = taskService.delete(id);
        return ResponseEntity.status(response.isSuccess()?204:409).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> edit( @PathVariable Integer id,@RequestBody TaskDto taskDto){
        ApiResponse apiResponse = taskService.edit(id, taskDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    
}
