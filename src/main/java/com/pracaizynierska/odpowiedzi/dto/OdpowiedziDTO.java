package com.pracaizynierska.odpowiedzi.dto;

import com.pracaizynierska.base.DTO.BaseDTO;
import com.pracaizynierska.odpowiedzi.EPoprawna;
import com.pracaizynierska.pytania.dto.PytaniaDTO;

import java.util.Date;

/**
 * Created by Bidzis on 11/12/2016.
 */
public class OdpowiedziDTO extends BaseDTO{
    private String odpowiedz;
    private EPoprawna poprawna;
    private PytaniaDTO pytania;

    public OdpowiedziDTO(){

    }
    public OdpowiedziDTO(Long id, Date techDate, String odpowiedz, EPoprawna poprawna, PytaniaDTO pytania){
        super(id, techDate);
        this.odpowiedz = odpowiedz;
        this.poprawna = poprawna;
        this.pytania = pytania;
    }

    public String getOdpowiedz() {
        return odpowiedz;
    }

    public void setOdpowiedz(String odpowiedz) {
        this.odpowiedz = odpowiedz;
    }

    public EPoprawna getPoprawna() {
        return poprawna;
    }

    public void setPoprawna(EPoprawna poprawna) {
        this.poprawna = poprawna;
    }

    public PytaniaDTO getPytania() {
        return pytania;
    }

    public void setPytania(PytaniaDTO pytania) {
        this.pytania = pytania;
    }
}
