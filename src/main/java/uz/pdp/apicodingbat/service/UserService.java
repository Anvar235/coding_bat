package uz.pdp.apicodingbat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.apicodingbat.entity.Category;
import uz.pdp.apicodingbat.entity.User;
import uz.pdp.apicodingbat.payload.ApiResponse;
import uz.pdp.apicodingbat.repository.CategoryRepository;
import uz.pdp.apicodingbat.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;


    public ApiResponse add(User user) {
        boolean existsByName = userRepository.existsByEmail(user.getEmail());
        if (existsByName)
            return new ApiResponse("Bunday foydalanuvchi bazada mavjud. Boshqa foydalanuvchi kiriting ",false);
        userRepository.save(user);
        return new ApiResponse("Yangi foydalanuvchi saqlandi", true);
    }

    public List<User> get() {
        List<User> userList = userRepository.findAll();
        return userList;
    }


    public User getByID(Integer id) {
        Optional<User> optionalUser = userRepository.findById(id);
        return optionalUser.orElse(null);
    }


    public ApiResponse delete(Integer id) {
        try {
            userRepository.deleteById(id);
            return new ApiResponse("Foydalanuvchi o'chirildi", true);
        } catch (Exception e) {
            return new ApiResponse("Foydalanuvchi  topilmadi", false);
        }
    }

   
    public ApiResponse edit(Integer id, User user) {
        boolean existsByName = userRepository.existsByEmail(user.getEmail());
        if (existsByName)
            return new ApiResponse("Bunday foydalanuvchi bazada mavjud. Boshqa foydalanuvchi kiriting ",false);
        Optional<User> optionalUser = userRepository.findById(id);
        if (!optionalUser.isPresent())
            return  new ApiResponse("Bunday foydalanuvchi topilmadi. ",false);
        User editingUser = optionalUser.get();
        editingUser.setEmail(user.getEmail());
        editingUser.setPassword(user.getPassword());
        userRepository.save(editingUser);
        return new ApiResponse("Tanlangan foydalanuvchi tahrirlandi",true);
    }
}
