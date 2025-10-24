package com.jabrowa.backend.utilities;

import java.text.Normalizer;
import java.util.regex.PatternSyntaxException;

public class StringUtilities {

    /**
     * <strong>normalizeString(<i>String</i>)</strong><br><br>
     * Normalizes a given string by replacing all diacritics with their common characters.
     * @param toNormalize   The string to be normalized.
     * @return The given string where diacritics are replaced by their common characters.
     * @throws NullPointerException - When the given string is <i>null</i>
     * @throws PatternSyntaxException - When the regular expression is <i>null</i>
     */
    public static String normalizeString(String toNormalize) throws NullPointerException, PatternSyntaxException {
            return Normalizer.normalize(toNormalize, Normalizer.Form.NFC)
                    .replaceAll("\\p{M}}", "").trim();
    }
}
