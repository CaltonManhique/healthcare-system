package com.exercise.healthcaresystem.controller;

import com.exercise.healthcaresystem.model.AdminModel;
import com.exercise.healthcaresystem.model.Doctor;
import com.exercise.healthcaresystem.model.Role;
import com.exercise.healthcaresystem.model.User;
import com.exercise.healthcaresystem.repo.DoctorRepo;
import com.exercise.healthcaresystem.repo.RoleRepo;
import com.exercise.healthcaresystem.repo.UserRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private DoctorRepo doctorRepo;
    private UserRepo userRepo;
    private RoleRepo roleRepo;

    @Autowired
    public AdminController(DoctorRepo doctorRepo, UserRepo userRepo, RoleRepo roleRepo) {
        this.doctorRepo = doctorRepo;
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;

    }

    @InitBinder
    public void initBinder(WebDataBinder binder){
        StringTrimmerEditor trimmer = new StringTrimmerEditor(true);
        binder.registerCustomEditor(String.class, trimmer);
    }

    @GetMapping("/admin")
    public String management(Model model) {
        model.addAttribute("adminModel", new AdminModel());
        return "staff-management";
    }

    @PostMapping("/createDoctor")
    public String createDoc(@Valid @ModelAttribute("adminModel") AdminModel adminModel, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "staff-management";
        } else {
            User user = new User();
            user.setEmail(adminModel.getUser().getEmail());

            //Password encryption
            PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
            user.setPassword(passwordEncoder.encode(adminModel.getUser().getPassword()));

            user.setEnabled(1);

            userRepo.save(user);

            Role role = new Role();
            role.setEmail(adminModel.getUser().getEmail());
            role.setAuthority(adminModel.getRole().getAuthority());

            roleRepo.save(role);

            Doctor doctor = new Doctor();
            doctor.setEmail(adminModel.getUser().getEmail());
            doctor.setName(adminModel.getDoctor().getName());
            doctor.setSpeciality(adminModel.getDoctor().getSpeciality());

            doctorRepo.save(doctor);

            return "creation-conf";
        }
    }

}
