package com.ironhack.midtermproject.Service.Interfaces;

import com.ironhack.midtermproject.Utils.Money;

public interface AccountService {
    void updateBalance(Long id, Money balance);

    void transferBalance(Long id1,Long id2, int quantity);

    void ThirdPartyTransfer (int hashedkey, Long id1,Long id2, int quantity);
}
