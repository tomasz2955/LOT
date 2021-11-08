package com.example.LOT;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@Service // to jest controller czy @Service? Aplikacja nie startuje
public class UserService {
    //przydalby sie endpoint do logowania ktory moglby przy poprawnym logowaniu zwracac date jak dlugo logowanie powinno byc wazne

    private UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping
    List<User> getUserList() {
        return userRepository.findAll();
    }

    @PostMapping("/create/{user}") // musimy pogadać o koncepcji DTO https://www.baeldung.com/java-dto-pattern
    User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @PostMapping("/login/{user}") // czy sprawdzales czy te endpointy działają przez postmana? Musimy pogadać o tym jak działa dodawanie parametrów do sciezki
    void loginUser(@RequestBody User email, @PathVariable User password) { //typy email i password są niepoprawne

    }

    @DeleteMapping("/delete/{user}")  // powinienes odbierac samo id a nie calego Usera
    public List<User> removeUser(@PathVariable User user) {  // musimy obgadac roznice pomiedzy @RequestBody vs @PathVariable
        // https://www.baeldung.com/spring-request-response-body  https://www.baeldung.com/spring-pathvariable
        userRepository.delete(user);
        return userRepository.findAll();  // po co zwracac wszystkich uzytkownikow podczas update jednego?
    }

    @PutMapping("/update/{user}")
    public List<User> updateUser(@RequestBody User user, @PathVariable Long id) {
        User currentuser = userRepository.findById(id).get(); //w momencie braku odnalezionego id, trzeba rzucic exception
        currentuser.setName(user.getName());
        currentuser.setLastName(user.getLastName());
        currentuser.setEmail(user.getEmail()); // update bardzo naiwny, ktos moze podac tutaj niepoprawny email, email ktory juz istnieje itp. to trzeba jakos validowac
        currentuser.setPhoneNumber(user.getPhoneNumber());
        currentuser.setPassword(user.getPassword());
        return userRepository.findAll(); // po co zwracac wszystkich uzytkownikow podczas update jednego?
    }


}
