package com.smartcom.copyexcelfile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/ecritures")
public class EcritureController {

    @Autowired
    private EcritureService ecritureService;

    @GetMapping("/transfer")
    public ResponseEntity<String> transferData(@RequestParam String numCompte, @RequestParam String journal,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                               @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        ecritureService.transferFilteredDataToH2(numCompte, journal, startDate, endDate);
        return ResponseEntity.ok("Data transferred successfully.");
    }

    @GetMapping("/bilan-actifs")
    public ResponseEntity<Double> calculerBilanActifs() {
        double bilan = ecritureService.calculerBilanActifs();
        return ResponseEntity.ok(bilan);
    }
}
