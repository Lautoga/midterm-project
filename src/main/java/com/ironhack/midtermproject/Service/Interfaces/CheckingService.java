package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.StudentChecking;

public interface CheckingService {
    Checking findById(Long id);
    Checking save (Checking checking,StudentChecking studentChecking);

    StudentChecking saveStudent(StudentChecking studentChecking);

    void delete(Long id);


}
