package com.jabrowa.backend.LadisTests;


import com.jabrowa.backend.ladis.codes.AddictionDuration;
import com.jabrowa.backend.ladis.entities.LadisCode;
import org.junit.jupiter.api.Test;

import java.util.logging.Logger;

public class LadisCodeTests {
    private final static Logger logger = Logger.getLogger(LadisCodeTests.class.getName());

    public void ladisCodeInstantiationTests() {
        /*
        WHEN    A Ladis code record is instantiated
        AND     not all (mandatory) parameters are provided,
        THEN    an IllegalArgumentException is thrown.
         */

        /*
        WHEN    A Ladis-code-record is instantiated
        AND     one of the parameters contains the value null
        OR      one of the parameters is empty
        OR      one of the parameters contains only blank-spaces,
        THEN    an IllegalArgumentException is thrown.
         */

        /*
        WHEN    A Ladis-code-record is instantiated
        AND     all the (mandatory) parameters are correctly specified,
        THEN    a valid Ladis-code-record is created.
         */
    }

    @Test
    public void ladisCreateCodeFromEnumTests() {
        /*
        WHEN
        AND
        THEN
         */
        AddictionDuration addictionDuration = AddictionDuration.M1TM3;
        LadisCode ladisCode = addictionDuration.tranformToLadisCode();

        /*
        Log in pretty readable format
         */
        logger.info(ladisCode.toNiceString());
    }
}
