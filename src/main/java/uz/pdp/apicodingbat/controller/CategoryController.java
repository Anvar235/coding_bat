package uz.pdp.apicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.CategoryDto;
import uz.pdp.apicodingbat.service.CategoryService;
import uz.pdp.apicodingbat.service.LanguageService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;


    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody CategoryDto categoryDto) {
        ApiResponse apiResponse = categoryService.add(categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<List<Category>> get() {
        List<Category> categoryList = categoryService.get();
        return ResponseEntity.ok(categoryList);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Category category = categoryService.getByID(id);
        return ResponseEntity.ok(category);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse response = categoryService.delete(id);
        return ResponseEntity.status(response.isSuccess()?204:409).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> edit( @PathVariable Integer id,@RequestBody CategoryDto categoryDto){
        ApiResponse apiResponse = categoryService.edit(id, categoryDto);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }
    
}
