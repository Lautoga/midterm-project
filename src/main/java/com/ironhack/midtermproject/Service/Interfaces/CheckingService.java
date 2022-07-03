package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.StudentChecking;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

public interface CheckingService {
    Checking findById(Long id);
    Checking save (Checking checking,StudentChecking studentChecking);

    StudentChecking saveStudent(StudentChecking studentChecking);

    void delete(Long id);


}
