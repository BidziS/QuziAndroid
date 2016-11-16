package com.pracaizynierska.odpowiedzi.service;

import com.pracaizynierska.odpowiedzi.EPoprawna;
import com.pracaizynierska.odpowiedzi.dto.OdpowiedziDTO;
import com.pracaizynierska.utils.MyServerException;

import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
public interface IOdpowiedziService {
    List<OdpowiedziDTO> znajdzOdpowiedziPoPytaniu(Long aIdPytania);
    List<OdpowiedziDTO> znajdzOdpowiedziPoPoprawnosci(EPoprawna poprawna);
    OdpowiedziDTO zapiszOdpowiedz(OdpowiedziDTO aOdpowiedziDTO) throws MyServerException;
    void usunOdpowiedzi(Long aId);
}
