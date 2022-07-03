package com.ironhack.midtermproject.Service.Impl;

import com.ironhack.midtermproject.Service.Interfaces.CheckingService;
import com.ironhack.midtermproject.classes.Money;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.model.StudentChecking;
import com.ironhack.midtermproject.repository.CheckingRepository;
import com.ironhack.midtermproject.repository.StudentCheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

@Service
public class CheckingServiceImpl implements CheckingService {

    @Autowired
    private CheckingRepository checkingRepository;

    @Autowired
    private StudentCheckingRepository studentCheckingRepository;

    private final Money penaltyFee = new Money(new BigDecimal(40));

    public Checking findById(Long id) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        if(checking.getBalance().getAmount().compareTo(checking.getMinimumBalance().getAmount())<0){
            checking.setBalance(new Money(checking.getBalance().decreaseAmount(penaltyFee)));
        }
        return checking;
    }

    public Checking save(Checking checking, StudentChecking studentChecking) {
        LocalDate localDate =LocalDate.now().minusYears(24);
        if (checking.getPrimaryOwner().getDateOfBirth().isAfter(localDate)){
            saveStudent(studentChecking);
        }
        checkingRepository.save(checking);
        return checking;
    }


    public StudentChecking saveStudent(StudentChecking checking){
    studentCheckingRepository.save(checking);
    return checking;
}



    public void delete(Long id) {
        Checking checking = checkingRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found"));
        checkingRepository.delete(checking);
    }


}
