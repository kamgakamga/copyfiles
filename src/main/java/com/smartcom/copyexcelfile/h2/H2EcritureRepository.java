package com.smartcom.copyexcelfile.h2;

import com.smartcom.copyexcelfile.h2.H2FEcritureC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright (c) 2024, Iforce5, All Right Reserved.
 * https://iforce5.com
 * <p>
 * When: @created 08/October/2024 -- 1:53 PM
 *
 * @author name :  Serges KAMGA on 10/8/2024
 * @author email : kamgakamga93@gmail.com /  serge.kamga@iforce5.com
 * Project : @project copy-excel-file
 * Package : @package com.smartcom.copyexcelfile
 **/

@Repository()
public interface H2EcritureRepository extends JpaRepository<H2FEcritureC, Long> {
}
