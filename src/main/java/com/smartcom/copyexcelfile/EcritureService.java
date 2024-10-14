package com.smartcom.copyexcelfile;

import com.smartcom.copyexcelfile.h2.H2EcritureRepository;
import com.smartcom.copyexcelfile.mysql.FEcritureC;
import com.smartcom.copyexcelfile.h2.H2FEcritureC;
import com.smartcom.copyexcelfile.mysql.MySqlEcritureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class EcritureService {

    private final MySqlEcritureRepository mySqlEcritureRepository;

    private final H2EcritureRepository h2EcritureRepository;

    public EcritureService(MySqlEcritureRepository mySqlEcritureRepository,
                           H2EcritureRepository h2EcritureRepository) {
        this.mySqlEcritureRepository = mySqlEcritureRepository;
        this.h2EcritureRepository = h2EcritureRepository;
    }

    @Transactional
    public void transferFilteredDataToH2(String numCompte, String journal, LocalDate startDate, LocalDate endDate) {
        // Filtrer les données dans MySQL
        List<FEcritureC> filteredEcritures = mySqlEcritureRepository
                .findAllByNumCompteGAndJournalAndDateSaisieBetween(numCompte, journal, startDate, endDate);

        // Transférer les données filtrées dans H2
        for (FEcritureC ecriture : filteredEcritures) {
            H2FEcritureC h2Ecriture = new H2FEcritureC();
            h2Ecriture.setReference(ecriture.getReference());
            h2Ecriture.setNumPiece(ecriture.getNumPiece());
            h2Ecriture.setNumCompteG(ecriture.getNumCompteG());
            h2Ecriture.setNumCompteT(ecriture.getNumCompteT());
            h2Ecriture.setSens(ecriture.getSens());
            h2Ecriture.setMontant(ecriture.getMontant());
            h2Ecriture.setJournal(ecriture.getJournal());
            h2Ecriture.setDateSaisie(ecriture.getDateSaisie());
            h2Ecriture.setLibelle(ecriture.getLibelle());

            h2EcritureRepository.save(h2Ecriture);
        }
    }

    public double calculerBilanActifs() {
        List<H2FEcritureC> h2Ecritures = h2EcritureRepository.findAll();

        // Calculer le bilan des actifs
        return h2Ecritures.stream()
                .filter(e -> e.getSens().equals("Debit") && e.getNumCompteG().startsWith("2"))
                .mapToDouble(H2FEcritureC::getMontant)
                .sum();
    }
}
