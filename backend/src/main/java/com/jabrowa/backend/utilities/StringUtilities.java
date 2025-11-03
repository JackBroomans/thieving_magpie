package com.jabrowa.backend.utilities;

import java.text.Normalizer;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

// todo: Plan the (temporary completion of the StringUtilities() utilty class
public class StringUtilities {

    /**
     * <strong>normalizeString(<i>String</i>)</strong><br><br>
     * Normalizes a given string by replacing all diacritics with their common characters.
     * @param toNormalize The string to be normalized.
     * @return The given string where diacritics are replaced by their common characters.
     * @throws IllegalArgumentException - When the given string is <i>null</i><br>
     * <i>The Regex pattern is a constant value, so a PatternSyntax is not expected</i>
     */
    public static String normalizeString(String toNormalize) {
        if (toNormalize == null) {
            throw new IllegalArgumentException();
        }
        else if(toNormalize.isBlank()) {
            return toNormalize.trim();
        }
        else {
            String normalized = Normalizer.normalize(toNormalize, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            String result = pattern.matcher(normalized).replaceAll("");

            for (Map.Entry<String, String> entry : LETTER_CHAR_REPLACEMENTS.entrySet()) {
                result = result.replace(entry.getKey(), entry.getValue());
            }
            return result;
        }
    }


    /**
     * <strong>EXTRA_REPLACEMENTS</strong> - constant <br><br>
     * A constant map which contains the diacritic characters and their associated normalized characters.
     * These diacritics are characters themselves and not characters with accents, so they won't be transformed by the
     * regular expression in the method 'normalizeString()'.
     */
    private static final Map<String, String> LETTER_CHAR_REPLACEMENTS = new LinkedHashMap<>();

    static {
        /* A */
        LETTER_CHAR_REPLACEMENTS.put("Å", "A");
        LETTER_CHAR_REPLACEMENTS.put("Â", "A");
        LETTER_CHAR_REPLACEMENTS.put("Ă", "A");
        LETTER_CHAR_REPLACEMENTS.put("Æ", "AE");
        LETTER_CHAR_REPLACEMENTS.put("å", "a");
        LETTER_CHAR_REPLACEMENTS.put("ă", "a");
        LETTER_CHAR_REPLACEMENTS.put("â", "a");
        LETTER_CHAR_REPLACEMENTS.put("æ", "ae");
        /* C */
        LETTER_CHAR_REPLACEMENTS.put("Ć", "C");
        LETTER_CHAR_REPLACEMENTS.put("Č", "C");
        LETTER_CHAR_REPLACEMENTS.put("ć", "c");
        LETTER_CHAR_REPLACEMENTS.put("č", "c");
        /* D */
        LETTER_CHAR_REPLACEMENTS.put("Đ", "D");
        LETTER_CHAR_REPLACEMENTS.put("Ď", "D");
        LETTER_CHAR_REPLACEMENTS.put("đ", "d");
        LETTER_CHAR_REPLACEMENTS.put("ď", "d");
        /* H */
        LETTER_CHAR_REPLACEMENTS.put("Ě", "E");
        LETTER_CHAR_REPLACEMENTS.put("ě", "e");
        /* G */
        LETTER_CHAR_REPLACEMENTS.put("Ģ", "G");
        LETTER_CHAR_REPLACEMENTS.put("ģ", "g");
        /* K */
        LETTER_CHAR_REPLACEMENTS.put("Ķ", "K");
        LETTER_CHAR_REPLACEMENTS.put("ķ", "k");
        /* L*/
        LETTER_CHAR_REPLACEMENTS.put("Ł", "L");
        LETTER_CHAR_REPLACEMENTS.put("Ļ", "L");
        LETTER_CHAR_REPLACEMENTS.put("ł", "l");
        LETTER_CHAR_REPLACEMENTS.put("ļ", "l");
        /* N */
        LETTER_CHAR_REPLACEMENTS.put("Ń", "N");
        LETTER_CHAR_REPLACEMENTS.put("Ň", "N");
        LETTER_CHAR_REPLACEMENTS.put("Ņ", "N");
        LETTER_CHAR_REPLACEMENTS.put("ń", "n");
        LETTER_CHAR_REPLACEMENTS.put("ň", "n");
        LETTER_CHAR_REPLACEMENTS.put("ņ", "n");
        /* O */
        LETTER_CHAR_REPLACEMENTS.put("Ø", "O");
        LETTER_CHAR_REPLACEMENTS.put("Ő", "O");
        LETTER_CHAR_REPLACEMENTS.put("Œ", "OE");
        LETTER_CHAR_REPLACEMENTS.put("ø", "o");
        LETTER_CHAR_REPLACEMENTS.put("ő", "o");
        LETTER_CHAR_REPLACEMENTS.put("œ", "oe");
        /* R */
        LETTER_CHAR_REPLACEMENTS.put("Ř", "R");
        LETTER_CHAR_REPLACEMENTS.put("ř", "r");
        /* S */
        LETTER_CHAR_REPLACEMENTS.put("Ś", "S");
        LETTER_CHAR_REPLACEMENTS.put("Š", "S");
        LETTER_CHAR_REPLACEMENTS.put("Ș", "S");
        LETTER_CHAR_REPLACEMENTS.put("ś", "s");
        LETTER_CHAR_REPLACEMENTS.put("š", "s");
        LETTER_CHAR_REPLACEMENTS.put("ș", "s");
        /* T */
        LETTER_CHAR_REPLACEMENTS.put("Ť", "T");
        LETTER_CHAR_REPLACEMENTS.put("Ţ", "T");
        LETTER_CHAR_REPLACEMENTS.put("Ț", "T");
        LETTER_CHAR_REPLACEMENTS.put("ť", "t");
        LETTER_CHAR_REPLACEMENTS.put("ţ", "t");
        LETTER_CHAR_REPLACEMENTS.put("ț", "t");
        /* S */
        LETTER_CHAR_REPLACEMENTS.put("ß", "ss");
        /* U */
        LETTER_CHAR_REPLACEMENTS.put("Ű", "U");
        LETTER_CHAR_REPLACEMENTS.put("ű", "u");
        /* Z */
        LETTER_CHAR_REPLACEMENTS.put("Ź", "Z");
        LETTER_CHAR_REPLACEMENTS.put("ź", "z");
        LETTER_CHAR_REPLACEMENTS.put("Ż", "Z");
        LETTER_CHAR_REPLACEMENTS.put("Ž", "Z");
        LETTER_CHAR_REPLACEMENTS.put("ż", "z");
        LETTER_CHAR_REPLACEMENTS.put("ž", "z");
    }
}
