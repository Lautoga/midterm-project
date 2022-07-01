package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.model.Admin;

import java.util.List;

public interface AdminController {
    List<Admin> findAll();
    Admin findById(Long id);
}
