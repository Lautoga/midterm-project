package com.ironhack.midtermproject.controller.interfaces;

import com.ironhack.midtermproject.controller.DTO.BalanceDTO;
import org.springframework.security.core.Authentication;

public interface AccountController {
    void updateBalance(Long id, BalanceDTO balanceDTO);

    void transferBalance(Long id1,Long id2, int quantity);

    void ThirdPartyTransfer (int hashedkey, Long id1,Long id2, int quantity);
}
