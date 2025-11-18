package com.jabrowa.backend.utilities;

import com.jabrowa.backend.model.interfaces.SelectableCode;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Optional;
import java.util.logging.Logger;

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
    private final static Logger LOGGER = Logger.getLogger(EnumUtilities.class.getName());

    /**
     * <strong>getByKeyValue(<i>Enum<E>, int</E></i>)</strong><br><br>
     * Searches and selects a constant based on the provided key value (attribute) in a given enumeration.
     * @param enumClass The enumerator-class from which the constant with the provided key value must be selected.
     * @param keyValue The key value of the constant in the enumeration to search for and select on.
     * @return An Optional of the enumeration constant which is identified by the given key value.
     * If no such constant, with the given key value is found, an empty Optional is returned.
     */
    public static <T extends Enum<T>> Optional<T> getByKeyValue(Class<T> enumClass, int keyValue) {
        if (enumClass == null) {
            return Optional.empty();
        }
        try {
            Method getNumberMethod = enumClass.getDeclaredMethod("getNumber");
            getNumberMethod.setAccessible(true);
            return Arrays.stream(enumClass.getEnumConstants())
                    .filter(e -> {
                        try {
                            Object value = getNumberMethod.invoke(e);
                            if (value instanceof Number) {
                                return ((Number) value).intValue() == keyValue;
                            }
                            return false;
                        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
                            LOGGER.warning(String.format
                                    ("Key value %d not found in %s", keyValue, enumClass.getSimpleName()));
                            return false;
                        }
                    })
                    .findFirst();
        } catch (NoSuchMethodException ex) {
            LOGGER.severe(String.format
                    ("Error on fetching %s with key value %d", enumClass.getSimpleName(), keyValue));
            return Optional.empty();
        }
    }

    /**
     * <strong>getByInterCode(<i>Enum<E>, String</E></i>)</strong><br><br>
     * Searches and selects a constant based on the provided key value (attribute) in a given enumeration.
     * @param enumClass The enumerator-class from which the constant with the provided key value must be selected.
     * @param interCode The code of the constant in the enumeration to search for and select on.
     * @return An Optional of the enumeration constant which is identified by the given code.
     * If no such constant, with the given code is found, an empty Optional is returned.
     */
    public static <T extends Enum<T>> Optional<T> getByInterCode(Class<T> enumClass, String interCode) {
        if (enumClass == null) {
            return Optional.empty();
        }
        try {
            Method getCodeMethod = enumClass.getDeclaredMethod("getCode");
            getCodeMethod.setAccessible(true);
            return Arrays.stream(enumClass.getEnumConstants())
                    .filter(e -> {
                        try {
                            Object value = getCodeMethod.invoke(e);
                            if (value instanceof String) {
                                return value == interCode;
                            }
                            return false;
                        } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ex) {
                            LOGGER.warning(String.format
                                    ("Key value %s not found in %s", interCode, enumClass.getSimpleName()));
                            return false;
                        }
                    })
                    .findFirst();
        } catch (NoSuchMethodException ex) {
            LOGGER.severe(String.format
                    ("Error on fetching %s with key value %s", enumClass.getSimpleName(), interCode));
            return Optional.empty();
        }
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
                                .getDeclaredMethod("isDefault")
                                .invoke(e);
                    } catch (Exception ex) {
                        throw new RuntimeException("Enumerator class does not have an isDefault() method.");
                    }
                })
                .findFirst()
                .orElse(null);
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
    private static <E extends Enum<E> & SelectableCode<?>>
        Optional<E> fromDisplaySafe(Class<E> enumClass, String display) {

        String normalizedInput = normalizeString(display);
        return Arrays.stream(enumClass.getEnumConstants())
                .filter(e -> normalizeString(e.getDisplay()).equalsIgnoreCase(normalizedInput))
                .findFirst();
    }
}
