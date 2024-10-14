package com.smartcom.copyexcelfile.h2;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Copyright (c) 2024, Iforce5, All Right Reserved.
 * https://iforce5.com
 * <p>
 * When: @created 08/October/2024 -- 1:52 PM
 *
 * @author name :  Serges KAMGA on 10/8/2024
 * @author email : kamgakamga93@gmail.com /  serge.kamga@iforce5.com
 * Project : @project copy-excel-file
 * Package : @package com.smartcom.copyexcelfile
 **/
@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "h2_f_ecriturec")
public class H2FEcritureC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reference;
    private String numPiece;
    private String numCompteG;
    private String numCompteT;
    private String sens;
    private double montant;
    private String journal;
    private LocalDate dateSaisie;
    private String libelle;

    // Getters and setters
}
