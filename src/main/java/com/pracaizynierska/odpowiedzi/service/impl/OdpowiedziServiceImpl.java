package com.pracaizynierska.odpowiedzi.service.impl;

import com.pracaizynierska.kategorie.dto.KategorieDTO;
import com.pracaizynierska.kategorie.ob.KategorieOB;
import com.pracaizynierska.kategorie.repository.IKategorieRepository;
import com.pracaizynierska.odpowiedzi.EPoprawna;
import com.pracaizynierska.odpowiedzi.dto.OdpowiedziDTO;
import com.pracaizynierska.odpowiedzi.ob.OdpowiedziOB;
import com.pracaizynierska.odpowiedzi.repository.IOdpowiedziRepository;
import com.pracaizynierska.odpowiedzi.service.IOdpowiedziService;
import com.pracaizynierska.pytania.dto.PytaniaDTO;
import com.pracaizynierska.pytania.ob.PytaniaOB;
import com.pracaizynierska.pytania.repository.IPytaniaRepository;
import com.pracaizynierska.utils.MyServerException;
import com.pracaizynierska.utils.converters.KategorieConventer;
import com.pracaizynierska.utils.converters.OdpowiedziConventer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/13/2016.
 */
@Service
@Transactional
public class OdpowiedziServiceImpl implements IOdpowiedziService{

    @Autowired
    IOdpowiedziRepository iOdpowiedziRepository;

    @Autowired
    IPytaniaRepository iPytaniaRepository;

    @Autowired
    IKategorieRepository iKategorieRepository;


    @Override
    public List<OdpowiedziDTO> znajdzOdpowiedziPoPytaniu(Long aIdPytania){
        List<OdpowiedziDTO> listaWynikowaOdpowiedziDTO = new ArrayList<>();
        List<OdpowiedziOB> listaOdpowiedziOB = iOdpowiedziRepository.znajdzPunktyPoPytaniu(aIdPytania);
        for(OdpowiedziOB odpowiedziOB : listaOdpowiedziOB)
            listaWynikowaOdpowiedziDTO.add(OdpowiedziConventer.odpowiedziOBdoOdpowiedziDTO(odpowiedziOB));
        //Collections.sort(listaWynikowaPunktyDTO, (PunktyDTO a, PunktyDTO b) -> b.getPunkty().compareTo(a.getPunkty()));
        return listaWynikowaOdpowiedziDTO;
    }
    @Override
    public List<OdpowiedziDTO> znajdzOdpowiedziPoPoprawnosci(EPoprawna poprawna){
        List<OdpowiedziDTO> listaWynikowaOdpowiedziDTO = new ArrayList<>();//utworzenie pojemnika
        List<OdpowiedziOB> listaOdpowiedziOB = iOdpowiedziRepository.znajdzPoAktywnosci(poprawna);

        for(OdpowiedziOB odpowiedzi : listaOdpowiedziOB) listaWynikowaOdpowiedziDTO.add(OdpowiedziConventer.odpowiedziOBdoOdpowiedziDTO(odpowiedzi));

        return listaWynikowaOdpowiedziDTO;
    }
    @Override
    public OdpowiedziDTO zapiszOdpowiedz(OdpowiedziDTO aOdpowiedziDTO) throws MyServerException{
        PytaniaDTO pPytaniaDTO = aOdpowiedziDTO.getPytania();
        if (pPytaniaDTO  == null)  throw new MyServerException("Nie znaleziono pola trybu", HttpStatus.NOT_FOUND,new HttpHeaders());
        PytaniaOB pPytaniaOB = pPytaniaDTO.getId() == null ? null :
                iPytaniaRepository.findOne(pPytaniaDTO.getId());
        if(pPytaniaOB == null)  throw new MyServerException("Nie znaleziono pytania",HttpStatus.NOT_FOUND,new HttpHeaders());


//        KategorieDTO pKategorieDTO = pPytaniaDTO.getKategorieDTO();
//        if (pKategorieDTO  == null)  throw new MyServerException("Nie znaleziono pola trybu", HttpStatus.NOT_FOUND,new HttpHeaders());
        KategorieOB pKategorieOB = pPytaniaOB.getKategorie();
        if(pKategorieOB == null)  throw new MyServerException("Nie znaleziono kategorii",HttpStatus.NOT_FOUND,new HttpHeaders());

        pPytaniaDTO.setKategorieDTO(KategorieConventer.kategorieOBdokategorieDTO(pKategorieOB));

        OdpowiedziOB pOdpowiedziOB = aOdpowiedziDTO.getId() == null ? null :
                iOdpowiedziRepository.findOne(aOdpowiedziDTO.getId());
        if(pOdpowiedziOB == null) {//gdy nie ma takiego dziennika planów
            pOdpowiedziOB = new OdpowiedziOB(aOdpowiedziDTO.getOdpowiedz(),aOdpowiedziDTO.getPoprawna(),pPytaniaOB);
           // aOdpowiedziDTO.setTechDate(aOdpowiedziDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
           // aOdpowiedziDTO.setOdpowiedz(aOdpowiedziDTO.getOdpowiedz()); //zmieniam dane!
           // aOdpowiedziDTO.setPoprawna(aOdpowiedziDTO.getPoprawna());
           // aOdpowiedziDTO.setPytania(pPytaniaDTO);
           // pOdpowiedziOB = OdpowiedziConventer.odpowiedziDTOdoOdpowiedziOB(aOdpowiedziDTO);
            return OdpowiedziConventer.odpowiedziOBdoOdpowiedziDTO(iOdpowiedziRepository.save(pOdpowiedziOB));
        }
        pOdpowiedziOB.setTechDate(aOdpowiedziDTO.getTechDate()); //to akurat wiadomo, że muszę zapisać kiedy to się stało
        aOdpowiedziDTO.setOdpowiedz(aOdpowiedziDTO.getOdpowiedz()); //zmieniam dane!
        aOdpowiedziDTO.setPoprawna(aOdpowiedziDTO.getPoprawna());
        aOdpowiedziDTO.setPytania(pPytaniaDTO);
        return OdpowiedziConventer.odpowiedziOBdoOdpowiedziDTO(iOdpowiedziRepository.save(pOdpowiedziOB));
    }
    @Override
    public void usunOdpowiedzi(Long aId){
        iOdpowiedziRepository.delete(aId);
    }
}
