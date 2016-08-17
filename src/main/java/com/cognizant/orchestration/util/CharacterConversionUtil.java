package com.cognizant.orchestration.util;

import org.apache.commons.lang.StringUtils;

/**
 * Conversion utility class to modify and convert characters of a String
 */
public final class CharacterConversionUtil {

    private CharacterConversionUtil() {
    }

    /**
     * 
     * Replaces the characters internally of a string literal wherein from and to characters are provided
     * 
     * @param baseLiteral
     * @param fromCharacter
     * @param toCharacter
     * @return changed String literal
     */
    public static String replaceFirstTokenOccurrence(final String baseLiteral, final Character fromCharacter,
        final Character toCharacter) {
        if (StringUtils.isBlank(baseLiteral) || fromCharacter == null || toCharacter == null) {
            throw new IllegalArgumentException("Neither the string nor the characters for conversion can be empty.");
        }
        return StringUtils.replaceOnce(baseLiteral, fromCharacter.toString(), toCharacter.toString());
    }
}
