package uz.pdp.apicodingbat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.service.LanguageService;
import java.util.List;

@RestController
@RequestMapping("/api/language")
public class LanguageController {
    @Autowired
    LanguageService languageService;


    @PostMapping
    public ResponseEntity<ApiResponse> add(@RequestBody Language language) {
        ApiResponse apiResponse = languageService.add(language);
        return ResponseEntity.status(apiResponse.isSuccess() ? HttpStatus.CREATED : HttpStatus.CONFLICT).body(apiResponse);
    }


    @GetMapping
    public ResponseEntity<List<Language>> get() {
        List<Language> languages = languageService.get();
        return ResponseEntity.ok(languages);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        Language language = languageService.getByID(id);
        return ResponseEntity.ok(language);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> delete(@PathVariable Integer id){
        ApiResponse response = languageService.delete(id);
        return ResponseEntity.status(response.isSuccess()?204:409).body(response);
    }

    @PutMapping("{id}")
    public ResponseEntity<ApiResponse> edit( @PathVariable Integer id,@RequestBody Language language){
        ApiResponse apiResponse = languageService.edit(id, language);
        return ResponseEntity.status(apiResponse.isSuccess()?201:409).body(apiResponse);
    }

//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public Map<String, String> handleValidationExceptions(
//            MethodArgumentNotValidException ex) {
//        Map<String, String> errors = new HashMap<>();
//        ex.getBindingResult().getAllErrors().forEach((error) -> {
//            String fieldName = ((FieldError) error).getField();
//            String errorMessage = error.getDefaultMessage();
//            errors.put(fieldName, errorMessage);
//        });
//        return errors;
//    }

    
}
