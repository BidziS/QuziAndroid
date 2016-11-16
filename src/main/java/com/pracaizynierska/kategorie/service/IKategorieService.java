package com.pracaizynierska.kategorie.service;

import com.pracaizynierska.kategorie.dto.KategorieDTO;
import com.pracaizynierska.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public interface IKategorieService {
    KategorieDTO znajdzKategoriePoId(Long aId) throws MyServerException;
    List<KategorieDTO> znajdzWszystkieKategorie();
    KategorieDTO znajdzKategoriePoNazwie(String aNazwaKategorii) throws  MyServerException;
    KategorieDTO zapiszKategorie(KategorieDTO aKategorieDTO) throws MyServerException ;
}
