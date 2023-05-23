package com.vn.cines_start.Controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.vn.cines_start.Model.Role;
import com.vn.cines_start.Model.User;
import com.vn.cines_start.Repository.RoleRepository;
import com.vn.cines_start.Repository.UserRepository;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    private PasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping("/login")
    public String login(){
		/* GlobalData.cart.clear(); */
        return "login";
    }//page login

    @GetMapping("/forgotpassword")
    public String forgotPass(Model model){
        model.addAttribute("userDTO", new User());
        return "forgotpassword";
    }

    @GetMapping("/register")
    public String registerGet(Model model){
        return "register";
    } //page register

    @PostMapping("/register")
    public String registerPost(@ModelAttribute User userModel, HttpServletRequest request) throws ServletException{
        //chuyen password tu form dki thanh dang ma hoa
        String password = userModel.getPassword();
        userModel.setPassword(bCryptPasswordEncoder.encode(password));
        //set mac dinh role user,admin
        List<Role> roles = new ArrayList<>();
        roles.add(roleRepository.findById(2L).get());
        userModel.setRoles(roles);
        userRepository.save(userModel);
        //login & chuyen den page home
        request.login(userModel.getEmail(), password);
        return "redirect:/";
    }//after register success
    
    
}
