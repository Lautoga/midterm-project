package com.ironhack.midtermproject.repository;

import com.ironhack.midtermproject.model.ThirdParty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty,Long> {
}
