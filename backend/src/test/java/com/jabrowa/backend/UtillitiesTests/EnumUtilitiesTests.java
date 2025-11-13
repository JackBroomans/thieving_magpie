package com.jabrowa.backend.UtillitiesTests;

import com.jabrowa.backend.model.enums.Gender;
import com.jabrowa.backend.model.interfaces.SelectableCode;
import com.jabrowa.backend.utilities.EnumUtilities;
import lombok.Getter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnumUtilitiesTests {

    @Test
    public void EnumSelectDefaultTests() {
        /*
        WHEN    the 'selectDefault()' method from the 'EnumUtilities' class is called
        AND     that class does not implement the 'SelectableCode' interface,
        AND     the 'selectDefault()' method from the 'EnumUtilities' class is called,
        THEN    a 'RuntimeError' is thrown.
         */
        assertThrows(RuntimeException.class,
                () -> {
                    EnumUtilities.selectDefault(TestEnumNoImplementation.class);
                }
        );

        /*
        WHEN    the 'selectDefault()' method from the 'EnumUtilities' class is called
        AND     no enumerator class but 'null' is passed as the required parameter,
        THEN    the value 'null' is returned.
         */
        assertNull(EnumUtilities.selectDefault(null));

        /*
        WHEN    the 'selectDefault(') method from the 'EnumUtilities' class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        AND     there's exact one constant assigned as default constant value 'null' is returned.
        THEN    there's exact one constant assigned as default constant value 'null' is returned.
         */
        assertNull(EnumUtilities.selectDefault(TestEnumNoDefault.class));

         /*
        WHEN    the 'SelectDefault()' method from the 'EnumUtilities' enumerator class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        THEN    the first (ordinal) constant which is marked as default is returned.
         */
        assertEquals(TestEnumTwoDefaults.PET,
                EnumUtilities.selectDefault(TestEnumTwoDefaults.class));
        assertTrue(TestEnumTwoDefaults.JIP.isDefault);

        /*
        WHEN    the 'SelectDefault()' method from the 'EnumUtilities' enumerator class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        AND     there's exact one constant assigned as default constant value
        THEN    the constant which is marked as default is returned.
         */
        Gender gender = EnumUtilities.selectDefault(Gender.class);
        assertEquals(Gender.NOT_SPECIFIED, gender);
        assertTrue(Gender.NOT_SPECIFIED.isDefault());
    }

    @Test
    public void EnumSelectByKeyValueTests() {
       /*
        WHEN    the 'selectByKey()' method from the 'EnumUtilities' class is called
        AND     one or both the parameters equals null,
        THEN    an empty Optional<> is returned
         */
        assertTrue(EnumUtilities.getByKeyValue(null, (short) 0).isEmpty());
        assertTrue(EnumUtilities.getByKeyValue(null, (short) 6).isEmpty());
        assertTrue(EnumUtilities.getByKeyValue(Gender.class, (short) 0).isEmpty());

        /*
        WHEN    the 'selectByKey()' method from the 'EnumUtilities' class is called
        AND     the both enumerator-class and key value are specified
        AND     the key value doesn't exist within the given enumerator-class,
        THEN    an empty Optional<> is returned
         */
        assertTrue(EnumUtilities.getByKeyValue(Gender.class, (short) 401).isEmpty());


        /*
        WHEN    the 'selectByKey()' method from the 'EnumUtilities' class is called
        AND     both enumerator-class and key value are specified
        AND     the key value is associated with a constant of the given enumerator-class,
        THEN    that constant is fetched, including its attributes values.
         */
        assertTrue(EnumUtilities.getByKeyValue(Gender.class, (short) 4).isPresent());
        assertEquals("I", EnumUtilities.getByKeyValue(Gender.class, (short) 4).get().getCode());

    }

    /**
     * <strong>TestEnumNoDefault</strong> - enumerator<br><br>
     * Enumerator to test an enumerator class without any of its constants set as default.
     */
    @Getter
    protected enum TestEnumNoDefault implements SelectableCode<Short> {
        DIKKERTJE((short) 1, "DIK", "Dikkertje Dap", true, false),
        PET((short) 2, "PET", "Pet van de Petteflet", true, false),
        POMPELOENTJE((short) 3, "POM", "Beertje Pompeloentje", true, false),
        JIP((short) 4, "JIP", "Jip van Janneke", true, false);

        final short number;
        final String code;
        final String display;
        final boolean isActive;
        final boolean isDefault;

        TestEnumNoDefault(short number, String code, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.code = code;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public TestEnumNoDefault selectDefault() {
            return EnumUtilities.selectDefault(TestEnumNoDefault.class);
        }
    }

    /**
     * <strong>TestEnumNoDefault</strong> - enumerator<br><br>
     * Enumerator to test an enumerator class with two (or more) of its constants set as default.
     */
    @Getter
    protected enum TestEnumTwoDefaults implements SelectableCode<Short> {
        DIKKERTJE((short) 1, "DIK", "Dikkertje Dap", true, false),
        PET((short) 2, "PET", "Pet van de Petteflet", true, true),
        POMPELOENTJE((short) 3, "POM", "Beertje Pompeloentje", true, false),
        JIP((short) 4, "JIP", "Jip van Janneke", true, true);

        final short number;
        final String code;
        final String display;
        final boolean isActive;
        final boolean isDefault;

        TestEnumTwoDefaults(short number, String code, String display, boolean isActive, boolean isDefault) {
            this.number = number;
            this.code = code;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public TestEnumTwoDefaults selectDefault() {
            return EnumUtilities.selectDefault(TestEnumTwoDefaults.class);
        }
    }

    /**
     * <strong>TestEnumNoImplementation</strong> - enumerator<br><br>
     * Enumerator to test an enumerator class with two (or more) of its constants set as default.
     */
    @Getter
    protected enum TestEnumNoImplementation {
        DIKKERTJE,
        PET,
        POMPELOENTJE,
        JIP;

        TestEnumNoImplementation() {
        }

        public TestEnumNoImplementation selectDefault() {
            return EnumUtilities.selectDefault(TestEnumNoImplementation.class);
        }

    }
}
