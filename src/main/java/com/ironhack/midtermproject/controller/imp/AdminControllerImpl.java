package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.controller.interfaces.AccountHolderController;
import com.ironhack.midtermproject.controller.interfaces.AdminController;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.Admin;
import com.ironhack.midtermproject.repository.AccountHolderRepository;
import com.ironhack.midtermproject.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class AdminControllerImpl implements AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/admin")
    @ResponseStatus(HttpStatus.OK)
    public List<Admin> findAll() {
        return adminRepository.findAll();
    }

    @GetMapping("/admin/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Admin findById(@PathVariable(name="id") Long id) {
        Optional<Admin> optionalAdmin = adminRepository.findById(id);
        return optionalAdmin.get();
    }
}


