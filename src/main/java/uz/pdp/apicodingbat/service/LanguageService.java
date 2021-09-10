package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Language;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.repository.LanguageRepository;
import java.util.List;
import java.util.Optional;

@Service
public class LanguageService {
    @Autowired
    LanguageRepository languageRepository;


    public ApiResponse add(Language language) {
        boolean existsByName = languageRepository.existsByName(language.getName());
        if (existsByName)
            return new ApiResponse("Bunday til bazada mavjud. Boshqa tilni kiriting ",false);
        languageRepository.save(language);
        return new ApiResponse("Yangi til saqlandi", true);
    }


    public List<Language> get() {
        List<Language> languageList = languageRepository.findAll();
        return languageList;
    }


    public Language getByID(Integer id) {
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        return optionalLanguage.orElse(null);
    }


    public ApiResponse delete(Integer id) {
        try {
            languageRepository.deleteById(id);
            return new ApiResponse("Til o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Til  topilmadi", false);
        }
    }


    public ApiResponse edit(Integer id, Language language) {
        boolean existsByName = languageRepository.existsByName(language.getName());
        if (existsByName)
            return new ApiResponse("Bunday til bazada mavjud. Boshqa tilni kiriting ",false);
        Optional<Language> optionalLanguage = languageRepository.findById(id);
        if (!optionalLanguage.isPresent())
            return new ApiResponse("Bunday til turi topilmadi ",false);
        Language language1 = optionalLanguage.get();
        language1.setName(language.getName());
        languageRepository.save(language1);
        return new ApiResponse("Tanlangan til turi tahrirlandi",true);
    }
}
