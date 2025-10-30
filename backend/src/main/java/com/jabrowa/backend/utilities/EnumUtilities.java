package com.jabrowa.backend.utilities;

import com.jabrowa.backend.model.interfaces.Selectable;

import java.util.Arrays;
import java.util.Optional;

import static com.jabrowa.backend.utilities.StringUtilities.normalizeString;

/**
 * <strong>EnumUtilities</strong> - Utility class<br><br>
 * Utility class containing all generic (shared) functionality for enumerators;
 * <ol>
 *     <li><strong>selectDefault()</strong><br>Method which returns the default marked constant.</li>
 *     <li><strong>selectDisplayValue()</strong><br>Method to select the display value argument of a constant.</li>
 * </ol>
 */
public class EnumUtilities {

    private EnumUtilities() {
        // Prevent instantiation
    }

    /**
     * <strong>ladisCodeToPrettyString(<i>Class, String</i>)</strong><br><br>
     * Returns a nicely formatted string representation of a certain Ladis code enum constant.  
     * @param enumClass The enum class type from which the Ladis code constant must be presented in a nicely format.
     * @param ladisCodeName The name of the Ladis code enum to be represented as header.
     * @return A nicely formatted string representation of a certain Ladis code enum constant. When an error occurs, an
     * IllegalArgumentException is thrown.
     */
    public static String ladisCodeToPrettyString(Class<?> enumClass, String ladisCodeName) {
        StringBuffer buffer = new StringBuffer();
        
       if(enumClass == null) {
            return buffer.toString();
        }
        ladisCodeName = (ladisCodeName == null || ladisCodeName.isEmpty() ? "" : ladisCodeName);

            try {
                buffer.append("Code: ").append(ladisCodeName).append("\n");
                buffer.append("\tLadis code:      ").append(enumClass.getDeclaredMethod("getNumber()")).append("\n");
                buffer.append("\tOmschrijving:    ").append(enumClass.getDeclaredMethod("getDisplay()")).append("\n");
                buffer.append("\tActief:          ").append(enumClass.getDeclaredMethod("isActive()")).append("\n");
                buffer.append("\tStandaard keuze: ").append(enumClass.getDeclaredMethod("isDefault")).append("\n");

            } catch (Exception ex) {
                throw new IllegalArgumentException(String.format("Enum %s doen't have all required methods!\n", 
                ladisCodeName) + ex.getMessage());
            } 
        return buffer.toString();
           
        }


    /**
     * <strong>selectDefault(<i>Class</i>)</strong><br><br>
     * Returns the enum constant marked as default (isDefaultValue = true). This isDefaultValue() is part of the
     * Selectable interface.
     * @param enumClass The enum class type from which the default marked constant must be retrieved.
     * @param <T> The enum class type from which the default marked constant must be retrieved.
     * @return The default marked enum constant, or null if none marked as such. If more than one constant is marked
     * as default, the first of the ordinal order is returned. If the Selectable interface isn't implemented
     * (NoSuchMethodException), a RuntimeException is thrown.
     */
    public static <T extends Enum<T>> T selectDefault(Class<T> enumClass) {
        if(enumClass == null) {
            return null;
        }
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> {
                    try {
                        return (boolean) enumClass
                                .getDeclaredMethod("isDefaultValue")
                                .invoke(e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Enum type must have isDefaultValue() method", ex);
                    }
                })
                .findFirst()
                .orElse(null);
    }

    public static <T extends Enum<T> & Selectable> T getFromDisplay(Class<T> enumClass, String display) {

        return fromDisplaySafe(enumClass, display)
                .orElseThrow(() -> new IllegalArgumentException("No enum constant with display value: " + display));
    }

    /**
     * <strong>fromDisplaySafe(<i>Class<E>, (String)</i></strong><br><br>
     * Searches for a certain display-(description) in a given enumerator.
     * Since the enumerator(s) inherits the EnumDisplay interface, the existence of the display field is certain.<br>
     * The search is case-insensitive, and all diacritics are replaced by there corresponding characters.
     * @param enumClass Class<E> The class of the enumerator from which the display definition (method)
     * @param display The value of the 'display field' in the enumerator to search for.
     * @return The enumerator item which contains the display-field with the given value.
     */

    private static <E extends Enum<E> & Selectable>

    Optional<E> fromDisplaySafe(Class<E> enumClass, String display) {

        String normalizedInput = normalizeString(display);
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> normalizeString(e.getDisplay()).equalsIgnoreCase(normalizedInput))
                .findFirst();

    }

}
