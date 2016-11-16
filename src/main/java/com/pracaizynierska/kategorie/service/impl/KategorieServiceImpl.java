package com.pracaizynierska.kategorie.service.impl;

import com.pracaizynierska.kategorie.dto.KategorieDTO;
import com.pracaizynierska.kategorie.ob.KategorieOB;
import com.pracaizynierska.kategorie.repository.IKategorieRepository;
import com.pracaizynierska.kategorie.service.IKategorieService;
import com.pracaizynierska.tryb.dto.TrybDTO;
import com.pracaizynierska.utils.MyServerException;
import com.pracaizynierska.utils.converters.KategorieConventer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Bidzis on 11/12/2016.
 */
@Service
@Transactional
public class KategorieServiceImpl implements IKategorieService{
    @Autowired
    IKategorieRepository iKategorieRepository;

    @Override
    public KategorieDTO znajdzKategoriePoNazwie(String aNazwaKategorii) throws  MyServerException {
        KategorieOB pKategorieOB = iKategorieRepository.znajdzPoNazwieKategorii(aNazwaKategorii);
        if(pKategorieOB == null) throw new MyServerException("Nie ma takiej kategorii", HttpStatus.NOT_FOUND,new HttpHeaders());
        return KategorieConventer.kategorieOBdokategorieDTO(pKategorieOB);
    }
    @Override
    public List<KategorieDTO> znajdzWszystkieKategorie() {
        List<KategorieDTO> listaWynikowaKategorieDTO = new ArrayList<>();
        List<KategorieOB> listaKategorieOB = iKategorieRepository.findAll();
        for(KategorieOB kategorieOB : listaKategorieOB) listaWynikowaKategorieDTO.add(KategorieConventer.kategorieOBdokategorieDTO(kategorieOB));

        return listaWynikowaKategorieDTO;
    }
    @Override
    public KategorieDTO znajdzKategoriePoId(Long aId) throws MyServerException {
        KategorieOB pKategorieOB = iKategorieRepository.findOne(aId);
        if(pKategorieOB == null) throw new MyServerException("Nie ma takiej kategorii", HttpStatus.NOT_FOUND,new HttpHeaders());
        return KategorieConventer.kategorieOBdokategorieDTO(pKategorieOB);
    }
    @Override
    public KategorieDTO zapiszKategorie(KategorieDTO aKategorieDTO) throws MyServerException {
        if(aKategorieDTO == null){
            throw new MyServerException("Brak pola kategorie",HttpStatus.NOT_FOUND,new HttpHeaders());
        }
        KategorieOB pTrybOB = aKategorieDTO.getId() == null ? null : iKategorieRepository.findOne(aKategorieDTO.getId());
        if(pTrybOB == null){//gdy nie ma takiego to zapisz
            KategorieOB pKategorieOBNameVeryfication = aKategorieDTO.getNazwa() == null ? null : iKategorieRepository.znajdzPoNazwieKategorii(aKategorieDTO.getNazwa());
            if(pKategorieOBNameVeryfication != null) throw new MyServerException("Juz jest taki nazwa trybu",HttpStatus.METHOD_NOT_ALLOWED,new HttpHeaders()); //nie można stworzyć ponieważ już jest taki eamil;
            aKategorieDTO = KategorieConventer.kategorieOBdokategorieDTO(iKategorieRepository.save(KategorieConventer.kategorieDTOdoKategorieOB(aKategorieDTO)));//zapisuje
            pTrybOB = KategorieConventer.kategorieDTOdoKategorieOB(aKategorieDTO);//stwórz instancje do przypisania do dziennika

            return aKategorieDTO;
        }
        //edytuj istniejącego
        pTrybOB.setNazwa(aKategorieDTO.getNazwa());

        return KategorieConventer.kategorieOBdokategorieDTO(iKategorieRepository.save(pTrybOB));
    }
}
