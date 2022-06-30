package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.CheckingService;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;


    public Checking save(Checking checking) {

        return checking;
    }

    public void renovate(Long id, Checking checking) {

    }


    public void delete(Long id) {

    }
}
