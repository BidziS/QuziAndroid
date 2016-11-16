package com.pracaizynierska.utils.converters;

import com.pracaizynierska.punkty.dto.PunktyDTO;
import com.pracaizynierska.punkty.ob.PunktyOB;
import com.pracaizynierska.tryb.ob.TrybOB;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/11/2016.
 */
public class PunktyConventer {
    public static PunktyDTO punktyOBdoPunktyDTO(PunktyOB aPunktyOB){
        if(aPunktyOB == null) return null;
        return new PunktyDTO(aPunktyOB.getId(),aPunktyOB.getTechDate(),aPunktyOB.getPunkty(),TrybConventer.trybOBdotrybDTO(aPunktyOB.getTryb()),UzytkownikConverter.uzytOBdoUzytkDTO(aPunktyOB.getUzytkownicy()));
    }

    public static PunktyOB punktyDTOdoPunktyOB(PunktyDTO aPunktyDTO){
        if(aPunktyDTO == null) return null;
        PunktyOB pPunktyOB = new PunktyOB(TrybConventer.trybDTOdoTrybOB(aPunktyDTO.getTryb()));
        pPunktyOB.setId(aPunktyDTO.getId());
        pPunktyOB.setTechDate(aPunktyDTO.getTechDate());
        pPunktyOB.setPunkty(aPunktyDTO.getPunkty());
        pPunktyOB.setUzytkownicy(UzytkownikConverter.uzytDTOdoUzytkOB(aPunktyDTO.getUzytkownicy()));
        return pPunktyOB;
    }

    public static List<PunktyDTO> listPunktyOBdoPunktyDTO(List<PunktyOB> aListaTrybowOB)
    {
        List<PunktyDTO> pListaPunktyDTO = new ArrayList<>();
        for(PunktyOB tryb : aListaTrybowOB)
        {
            pListaPunktyDTO.add(punktyOBdoPunktyDTO(tryb));
        }
        return pListaPunktyDTO;
    }

    public static List<PunktyOB> listPunktyDTOdoPunktyOB(List<PunktyDTO> aListaPunktyDTO){
        List<PunktyOB> pListaPunktyOB = new ArrayList<>();
        for(PunktyDTO punktyDTO : aListaPunktyDTO){
            pListaPunktyOB.add(punktyDTOdoPunktyOB(punktyDTO));
        }
        return pListaPunktyOB;
    }
}
