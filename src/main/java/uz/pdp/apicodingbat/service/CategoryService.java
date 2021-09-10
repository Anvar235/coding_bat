package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.payload.CategoryDto;
import uz.pdp.apicodingbat.repository.CategoryRepository;
import uz.pdp.apicodingbat.repository.LanguageRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    LanguageRepository languageRepository;

    /**
     * Kategoriya qo'shadigan metod
     * @return ApiResponse
     */
    public ApiResponse add(CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday kategoriya bazada mavjud. Boshqa kategoriya kiriting ",false);
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til bazadan topilmadi. ",false);
        Language language = optionalLanguage.get();
        Category category=new Category(null,categoryDto.getName(),categoryDto.getDescription(),language);
        categoryRepository.save(category);
        return new ApiResponse("Yangi kategoriya saqlandi", true);
    }

    /**
     * Kategoriya listini qaytaradigan metod
     * @return List<Language>
     */
    public List<Category> get() {
        List<Category> categoryList = categoryRepository.findAll();
        return categoryList;
    }

    /**
     * Kategoriya  ID si bo'yicha qaytaradigan metod
     * @param id Integer
     * @return Kategoriya
     * Kategoriya bo'lmasa null qaytadi
     */
    public Category getByID(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        return optionalCategory.orElse(null);
    }

    /**
     * Kategoriya ID si bo'yicha udalit qiladigan metod
     * @param id Integer
     * @return ApiResponse
     */
    public ApiResponse delete(Integer id) {
        try {
            categoryRepository.deleteById(id);
            return new ApiResponse("Kategoriya o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Kategoriya  topilmadi", false);
        }
    }

    /**
     * Kategoriya ID si bo'yicha tahrirlaydigan metod
     * @param id      Integer
     * @param categoryDto CategoryDto
     * @return ApiResponse
     */
    public ApiResponse edit(Integer id, CategoryDto categoryDto) {
        boolean existsByName = categoryRepository.existsByName(categoryDto.getName());
        if (existsByName)
            return new ApiResponse("Bunday kategoriya bazada mavjud. Boshqa kategoriya kiriting ",false);
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent())
            return new ApiResponse("Bunday kategoriya turi topilmadi ",false);
        Category editingCategory = optionalCategory.get();
        Optional<Language> optionalLanguage = languageRepository.findById(categoryDto.getLanguageId());
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til bazadan topilmadi. ",false);
        Language language = optionalLanguage.get();
        editingCategory.setName(categoryDto.getName());
        editingCategory.setDescription(categoryDto.getDescription());
        editingCategory.setLanguage(language);
        categoryRepository.save(editingCategory);
        return new ApiResponse("Tanlangan til turi tahrirlandi",true);
    }
}
