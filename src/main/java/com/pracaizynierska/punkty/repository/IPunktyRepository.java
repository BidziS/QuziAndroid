package com.pracaizynierska.punkty.repository;

import com.pracaizynierska.punkty.ob.PunktyOB;
import com.pracaizynierska.uzytkownik.ob.UzytkownikOB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Bidzis on 11/11/2016.
 */
@Repository
public interface IPunktyRepository extends JpaRepository<PunktyOB,Long> {
    @Query("SELECT u FROM PunktyOB u WHERE u.uzytkownicy.id=?1")
    List<PunktyOB> znajdzPunktyPoUzytkowniku(Long aIdUzytkownik);

    @Query("SELECT u FROM PunktyOB u WHERE u.tryb.id=?1")
    List<PunktyOB> znajdzPunktyPoTrybie(Long aIdTryb);
}
