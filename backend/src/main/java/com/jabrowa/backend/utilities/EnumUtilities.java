package com.jabrowa.backend.utilities;

import java.util.Arrays;

//

public class EnumUtilities {

    private EnumUtilities() {
        // Prevent instantiation
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
}

//public class EnumUtilities {
//    public static <E extends Enum<E> & Selectable> E fromDisplay(Class<E> enumClass, String display) {
//
//        return fromDisplaySafe(enumClass, display)
//                .orElseThrow(() -> new IllegalArgumentException("No enum constant with display value: " + display));
//
//    }
//
//
//    /**
//     * <strong>fromDisplaySafe(<i>Class<E>, (String)</i></strong><br><br>
//     * The method searches for a given display (description) in a given enumerator. Since the enumerator(s) inherits
//     * the EnumDisplay interface, the existence of the display field should be guaranteed.<br>
//     * The search is case-insensitive, and all diacritics are replaced by there common characters.
//     * @param enumClass The enumerator class which contains the display definition Getter().
//     * @param display The value of the 'display field' in the enumerator to search for.
//     * @return The enumerator item which contains the display-field with the given value.
//     * @throws NullPointerException When the display (description) isn't accepted by the normalize method.
//     * @throws PatternSyntaxException When regular expression of the normalizeString() method can't be applied.
//     */
//    static <E> Optional<E> fromDisplaySafe(Class<E> enumClass, String display)
//            throws NullPointerException, PatternSyntaxException {
//
//        return Arrays.stream(enumClass.getEnumConstants())
//                .filter(e -> normalizeString(e.getDisplay()).equalsIgnoreCase(normalizeString(display)))
//                .findFirst();
//
//    }
//}
