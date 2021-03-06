package com.pracaizynierska.odpowiedzi.repository;

import com.pracaizynierska.odpowiedzi.EPoprawna;
import com.pracaizynierska.odpowiedzi.ob.OdpowiedziOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
@Repository
public interface IOdpowiedziRepository extends JpaRepository<OdpowiedziOB,Long> {
    @Query("SELECT u FROM OdpowiedziOB u WHERE u.pytania.id=?1")
    List<OdpowiedziOB> znajdzPunktyPoPytaniu(Long aIdPytania);
    @Query("SELECT u FROM OdpowiedziOB u WHERE u.poprawna=?1")
    List<OdpowiedziOB> znajdzPoAktywnosci(EPoprawna aPoprawna);
}
