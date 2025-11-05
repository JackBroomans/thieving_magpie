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
        WHEN    The selectDefault() method is called
        AND     no enumerator class but 'null' is passed as the required parameter,
        THEN    the value 'null' is returned.
         */
        assertNull(EnumUtilities.selectDefault(null));

        /*
        WHEN    The selectDefault() method in the 'EnumUtilities' class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        AND     there's exact one constant assigned as default constant value 'null' is returned.
        THEN    there's exact one constant assigned as default constant value 'null' is returned.
         */
        assertNull(EnumUtilities.selectDefault(TestEnumNoDefault.class));

         /*
        WHEN    The SelectDefault() method in the 'EnumUtilities' enumerator class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        THEN    the first (ordinal) constant which is marked as default is returned.
         */
        assertEquals(TestEnumNoDefault.TestEnumTwoDefaults.PET,
                EnumUtilities.selectDefault(TestEnumNoDefault.TestEnumTwoDefaults.class));
        assertTrue(TestEnumNoDefault.TestEnumTwoDefaults.JIP.isDefault);

        /*
        WHEN    The SelectDefault() method in the 'EnumUtilities' enumerator class is called
        AND     an enumerator class, which has implemented the 'SelectableCode' interface, is passed as parameter
        AND     there's exact one constant assigned as default constant value
        THEN    the constant which is marked as default is returned.
         */
        Gender gender = EnumUtilities.selectDefault(Gender.class);
        assertEquals(Gender.NOT_SPECIFIED, gender);
        assertTrue(Gender.NOT_SPECIFIED.isDefault());
    }

    /**
     * <strong>TestEnumNoDefault</strong> - enumerator<br><br>
     * Enumerator to test an enumerator class without any of its constants set as default.
     */
    @Getter
    protected enum TestEnumNoDefault implements SelectableCode {
        DIKKERTJE ("DIK", "Dikkertje Dap", true,  false),
        PET ("PET", "Pet van de Petteflet" , true,  false),
        POMPELOENTJE("POM", "Beertje Pompeloentje", true,  false),
        JIP("JIP", "Jip van Janneke",  true,  false);

        final String code;
        final String display;
        final boolean isActive;
        final boolean isDefault;

        TestEnumNoDefault(String code, String display, boolean isActive, boolean isDefault) {
            this.code = code;
            this.display = display;
            this.isActive = isActive;
            this.isDefault = isDefault;
        }

        public TestEnumNoDefault selectDefault() {
            return EnumUtilities.selectDefault(TestEnumNoDefault.class);
        }

        /**
         * <strong>TestEnumNoDefault</strong> - enumerator<br><br>
         * Enumerator to test an enumerator class with two (or more) of its constants set as default.
         */
        @Getter
        protected enum TestEnumTwoDefaults implements SelectableCode {
            DIKKERTJE("DIK", "Dikkertje Dap", true, false),
            PET("PET", "Pet van de Petteflet", true, true),
            POMPELOENTJE("POM", "Beertje Pompeloentje", true, false),
            JIP("JIP", "Jip van Janneke", true, true);

            final String code;
            final String display;
            final boolean isActive;
            final boolean isDefault;

            TestEnumTwoDefaults(String code, String display, boolean isActive, boolean isDefault) {
                this.code = code;
                this.display = display;
                this.isActive = isActive;
                this.isDefault = isDefault;
            }

            public EnumUtilitiesTests.TestEnumNoDefault selectDefault() {
                return EnumUtilities.selectDefault(EnumUtilitiesTests.TestEnumNoDefault.class);
            }
        }

    }
}
