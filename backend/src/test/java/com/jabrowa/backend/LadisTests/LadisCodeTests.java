package com.jabrowa.backend.LadisTests;

import com.jabrowa.backend.ladis.codes.*;
import com.jabrowa.backend.ladis.entities.LadisCode;
import com.jabrowa.backend.utilities.EnumUtilities;
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
            LadisCode ladisCode = new LadisCode(null, (short) 0, null, null,
                    false, false);
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
                    LivingSituation.KIND_MEEROUDER.getDisplay(),
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());
            });

        assertThrows(IllegalArgumentException.class, () -> {
            LadisCode ladisCode = new LadisCode(
                    LivingSituation.KIND_MEEROUDER.name(),
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    LivingSituation.KIND_MEEROUDER.getCode(),
                    "",
                    LivingSituation.KIND_MEEROUDER.isActive(),
                    LivingSituation.KIND_MEEROUDER.isDefault());
        });

        assertThrows(IllegalArgumentException.class, () -> {
            LadisCode ladisCode = new LadisCode(
                    "   ",
                    LivingSituation.KIND_MEEROUDER.getNumber(),
                    LivingSituation.KIND_MEEROUDER.getDisplay(),
                    LivingSituation.KIND_MEEROUDER.getCode(),
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
                    LivingSituation.KIND_MEEROUDER.getCode(),
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
        assertEquals(addictionDuration.getCode(), ladisCode.code());
        assertEquals(addictionDuration.getDisplay(), ladisCode.display());
        assertEquals(addictionDuration.isActive(), ladisCode.isActive());
        assertEquals(addictionDuration.isDefault(), ladisCode.isDefault());

        /*
        Log 'Test completed'
         */
        logger.info("Completed: ladisCreateCodeFromEnumTests()\n");
    }

    /*
    WHEN    a particular Ladis code enumerator isn't defined
    AND     the default assigned enumeration constant is requested by 'selectDefault()',
    OR      an enumeration constant is requested based on its key value,
    OR      an enumeration constant is requested, based on the code,
    THEN    the default enumeration constant is selected.
         */
    @Test
    public void ladisSelectEnumConstantTests() {

        /* AddictionDuration */
        AddictionDuration addictionDuration = null;
        assertNull(addictionDuration);
        assertEquals((short) 9, EnumUtilities.selectDefault(AddictionDuration.class).getNumber());

        addictionDuration = AddictionDuration.J5TM10;
        assertNotNull(addictionDuration);
        assertTrue(EnumUtilities.getByKeyValue(AddictionDuration.class, (short) 5).isPresent());
        assertNotEquals(addictionDuration.getNumber(),
                EnumUtilities.getByKeyValue(AddictionDuration.class, (short) 5).get().getNumber());

        assertTrue(EnumUtilities.getByInterCode(AddictionDuration.class, "AD-0005").isPresent());
        assertEquals((short) 5,
                EnumUtilities.getByInterCode(AddictionDuration.class, "AD-0005").get().getNumber());


        /* GamblingLocation */
        GamblingLocation gamblingLocation = null;
        assertNull(gamblingLocation);
        assertEquals((short) 9, EnumUtilities.selectDefault(GamblingLocation.class).getNumber());

        gamblingLocation = GamblingLocation.AMUSEMENTSHAL;
        assertNotNull(gamblingLocation);
        assertNotEquals(gamblingLocation.getNumber(),
                EnumUtilities.selectDefault(GamblingLocation.class).getNumber());

        assertTrue(EnumUtilities.getByInterCode(AddictionDuration.class, "GL-0011").isPresent());
        assertEquals((short) 5,
                EnumUtilities.getByInterCode(AddictionDuration.class, "GL-0011").get().getNumber());


        /* Educational Level */
        Education education = null;
        assertNull(education);
        assertEquals((short) 99, EnumUtilities.selectDefault(Education.class).getNumber());

        education = Education.MBO34_HAVO_VWO;
        assertNotNull(education);
        assertNotEquals(addictionDuration.getNumber(),
                EnumUtilities.selectDefault(Education.class).getNumber());

        assertTrue(EnumUtilities.getByInterCode(AddictionDuration.class, "ED-0043").isPresent());
        assertEquals((short) 5,
                EnumUtilities.getByInterCode(AddictionDuration.class, "ED-0043").get().getNumber());

    }
}
