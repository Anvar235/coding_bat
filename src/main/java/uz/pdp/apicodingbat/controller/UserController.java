package uz.pdp.apicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.User;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.service.CategoryService;
import uz.pdp.apicodingbat.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;


    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody User user) {
        ApiResponse apiResponse = userService.add(user);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<List<User>> get() {
        List<User> userList = userService.get();
        return ResponseEntity.ok(userList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        User user = userService.getByID(id);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse response = userService.delete(id);
        return ResponseEntity.status(response.isSuccess()?204:409).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> edit( @PathVariable Integer id,@RequestBody User user){
        ApiResponse apiResponse = userService.edit(id, user);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    
}
