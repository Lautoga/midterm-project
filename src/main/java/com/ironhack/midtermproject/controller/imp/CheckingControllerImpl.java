package com.ironhack.midtermproject.controller.imp;

import com.ironhack.midtermproject.Service.Interfaces.CheckingService;
import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import com.ironhack.midtermproject.controller.interfaces.CheckingController;
import com.ironhack.midtermproject.model.Account;
import com.ironhack.midtermproject.model.Checking;
import com.ironhack.midtermproject.repository.CheckingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class CheckingControllerImpl implements CheckingController {

    @Autowired
   private CheckingRepository checkingRepository;

    @Autowired
    private CheckingService checkingService;

    @GetMapping("/checking")
    @ResponseStatus(HttpStatus.OK)
    public List<Checking> findAll() {
        return checkingRepository.findAll();
    }

    @GetMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Checking findById(@PathVariable (name = "id") Long id) {
        Optional<Checking> optionalChecking = checkingRepository.findById(id);
        return optionalChecking.get();
    }

    @PostMapping("/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Account store(@RequestBody @Valid Checking checking) {
        return checkingService.save(checking);
    }


    @PutMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@PathVariable Long id,@RequestBody @Valid Checking checking) {
    checkingService.update(id,checking);
    }

    @PatchMapping("/checking/{id}/balance")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance(@PathVariable Long id,@RequestBody @Valid BalanceDTO checkingBalanceDTO) {
    checkingService.updateBalance(id, checkingBalanceDTO.getBalance());
    }

    @DeleteMapping("/checking/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        checkingService.delete(id);
    }
}
