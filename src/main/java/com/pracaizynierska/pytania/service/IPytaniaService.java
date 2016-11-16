package com.pracaizynierska.pytania.service;

import com.pracaizynierska.punkty.dto.PunktyDTO;
import com.pracaizynierska.pytania.dto.PytaniaDTO;
import com.pracaizynierska.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
public interface IPytaniaService {
    List<PytaniaDTO> znajdzPytaniaPoKategorii(Long aIdTryb);
    List<PytaniaDTO> znajdzPytaniaPoNazwieKategorii(String nazwaKategorii);
    PytaniaDTO zapiszPytania(PytaniaDTO aPytaniaDTO) throws MyServerException;
    void usunPytania(Long aId);
}
