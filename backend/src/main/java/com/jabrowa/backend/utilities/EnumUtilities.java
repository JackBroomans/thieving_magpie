package com.jabrowa.backend.utilities;

import com.jabrowa.backend.model.interfaces.SelectableCode;

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
                                .getDeclaredMethod("isDefault")
                                .invoke(e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Enumerator class does not have an isDefault() method.");
                    }
                })
                .findFirst()
                .orElse(null);
    }

    public static <T extends Enum<T> & SelectableCode> T getFromDisplay(Class<T> enumClass, String display) {

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

    private static <E extends Enum<E> & SelectableCode>
        Optional<E> fromDisplaySafe(Class<E> enumClass, String display) {

        String normalizedInput = normalizeString(display);
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> normalizeString(e.getDisplay()).equalsIgnoreCase(normalizedInput))
                .findFirst();

    }


    /**
     * <strong>getByKeyValue(<i>Enum<E>, int</E></i>)</strong><br><br>
     * Searches and selects a constant with the provided key value (attribute) in a given enumerator-class.
     * @param enumClass The enumerator-class from which the constant with the provided key value must be selected.
     * @param keyValue The key value of the constant of the given enumerator class to search and select on.
     * @return An Optional of the type of enumerator wherein the search was performed, When not found the default set
     * constant of the enumerator-class is returned as optional, and when that isn't found empty optional is returned.
     * is returned.
     */
    public static <E extends Enum<E> & SelectableCode>
    Optional<E> getByKeyValue(Class<E> enumClass, int keyValue) {
        if (enumClass == null || keyValue <= 0) {
            throw new IllegalArgumentException("No enum constant and/or key value passed.");
        }

       return Optional.ofNullable(Arrays.stream(enumClass.getEnumConstants())
               .filter(e -> e.getKeyValue() == keyValue)
               .findFirst()
               .orElse(selectDefault(enumClass)));
    }
}
