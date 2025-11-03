package com.jabrowa.backend.LadisTests;

import com.jabrowa.backend.ladis.codes.AddictionDuration;
import com.jabrowa.backend.ladis.codes.LivingSituation;
import com.jabrowa.backend.ladis.entities.LadisCode;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@Testable
public class LadisCodeTests {
    private final static Logger logger = Logger.getLogger(LadisCodeTests.class.getName());

    @Test
    public void ladisCodeInstantiationTests() {
        /*
        WHEN    A Ladis code record is instantiated
        AND     not all (mandatory) parameters are provided,
        THEN    an IllegalArgumentException is thrown.
         */
        assertThrows(IllegalArgumentException.class, () -> {
                    LadisCode ladisCode = new LadisCode(null, 0, null, false, false);
        });

        /*
        WHEN    A Ladis-code-record is instantiated
        AND     one of the parameters contains the value null
        OR      one of the parameters is empty
        OR      one of the parameters contains only blank-spaces,
        THEN    an IllegalArgumentException is thrown.
         */
        assertThrows(IllegalArgumentException.class, () -> {
            LadisCode ladisCode = new LadisCode(
                    LivingSituation.KIND_MEEROUDER.name(),
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    null,
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());
            });

        assertThrows(IllegalArgumentException.class, () -> {
            LadisCode ladisCode = new LadisCode(
                    LivingSituation.KIND_MEEROUDER.name(),
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    "",
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LadisCode ladisCode = new LadisCode(
                    "   ",
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    LivingSituation.KIND_MEEROUDER.getDisplay(),
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());
            });

        /*
        WHEN    A Ladis-code-record is instantiated
        AND     all the (mandatory) parameters are correctly specified,
        THEN    a valid Ladis-code-record is created. (Happy flow)
         */
            LadisCode ladisCode = new LadisCode(
                    LivingSituation.KIND_MEEROUDER.name(),
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    LivingSituation.KIND_MEEROUDER.getDisplay(),
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());

        assertEquals(LivingSituation.KIND_MEEROUDER.getNumber(), ladisCode.number());
        assertEquals(LivingSituation.KIND_MEEROUDER.getDisplay(), ladisCode.display());
        assertEquals(LivingSituation.KIND_MEEROUDER.isActive(), ladisCode.isActive());

        /*
        Check toNiceFormat by logging and log 'Test completed'
         */
        logger.info(ladisCode.toNiceString());
        logger.info("Completed: ladisCodeInstantiationTests()\n");
    }

    @Test
    public void ladisCreateCodeFromEnumTests() {
        /*
        WHEN    A ladis-code-record is instantiated and created from an enumerated code specification directly,
        THEN    All properties of the ladis=code-record meets those of the assigned enumerator.
         */
        AddictionDuration addictionDuration = AddictionDuration.M1TM3;
        LadisCode ladisCode = addictionDuration.createLadisCodeFromEnum();

        assertEquals(addictionDuration.getNumber(), ladisCode.number());
        assertEquals(addictionDuration.getDisplay(), ladisCode.display());
        assertEquals(addictionDuration.isActive(), ladisCode.isActive());
        assertEquals(addictionDuration.isDefault(), ladisCode.isDefault());

        /*
        Log 'Test completed'
         */
        logger.info("Completed: ladisCreateCodeFromEnumTests()\n");
    }
}
