package com.pracaizynierska.uzytkownik.service;

import com.pracaizynierska.utils.MyServerException;
import com.pracaizynierska.uzytkownik.dto.UzytkownikDTO;

import java.util.List;

/**
 * Created by Bidzis on 11/3/2016.
 */
public interface IUzytkownikService {
    UzytkownikDTO znajdzUzytkownikaPoId(Long aId) throws MyServerException;
    UzytkownikDTO znajdzUzytkownikaPoNicku(String aNick) throws  MyServerException;
    List<UzytkownikDTO> znajdzWszystkichUzytkownikow();
    UzytkownikDTO zapiszUzytkownika(UzytkownikDTO aUzytkownikDTO) throws MyServerException ;
}
