package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.controller.interfaces.StudentCheckingController;
import com.ironhack.midtermproject.model.AccountHolder;
import com.ironhack.midtermproject.model.StudentChecking;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
@RestController
public class StudentCheckingControllerImpl implements StudentCheckingController {

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    @GetMapping("/student-checking")
    @ResponseStatus(HttpStatus.OK)
    public List<StudentChecking> findAll() {
        return studentCheckingRepository.findAll();
    }


    @GetMapping("/student-checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public StudentChecking findById(@PathVariable(name = "id") Long id) {
        Optional<StudentChecking> optionalStudentChecking = studentCheckingRepository.findById(id);
        return optionalStudentChecking.get();
    }
}
