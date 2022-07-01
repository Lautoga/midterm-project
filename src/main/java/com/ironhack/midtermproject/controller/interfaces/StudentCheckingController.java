package com.ironhack.midtermproject.controller.interfaces;


import com.ironhack.midtermproject.model.StudentChecking;

import java.util.List;

public interface StudentCheckingController {
    List<StudentChecking> findAll();

    StudentChecking findById(Long id);
}
