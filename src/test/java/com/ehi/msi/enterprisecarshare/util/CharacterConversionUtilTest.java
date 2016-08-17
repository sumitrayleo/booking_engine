package com.ehi.msi.enterprisecarshare.util;

import org.junit.Test;

import com.cognizant.orchestration.util.CharacterConversionUtil;
import com.ehi.msi.test.AbstractSpringTest;

public class CharacterConversionUtilTest extends AbstractSpringTest {

    private final static String STRING_WITH_COLON = "US:enterprisecarshare";
    private final static String STRING_WITH_UNDERSCORE = "US_enterprisecarshare";
    private final static Character COLON = ':';
    private final static Character UNDERSCORE = '_';

    /**
     * Happy Scenario -To convert STRING_WITH_COLON to STRING_WITH_UNDERSCORE
     */
    @Test
    public void testToConvertFromColonToUnderscore() {
        assertEquals(STRING_WITH_UNDERSCORE,
            CharacterConversionUtil.replaceFirstTokenOccurrence(STRING_WITH_COLON, COLON, UNDERSCORE));
    }

    /**
     * Happy Scenario -To convert STRING_WITH_UNDERSCORE to STRING_WITH_COLON
     */
    @Test
    public void testToConvertFromUnderscoreToColon() {
        assertEquals(STRING_WITH_COLON,
            CharacterConversionUtil.replaceFirstTokenOccurrence(STRING_WITH_UNDERSCORE, UNDERSCORE, COLON));
    }

    /**
     * Negative Scenario- For Empty String
     */
    @Test(expected = IllegalArgumentException.class)
    public void testForEmptyString() {
        CharacterConversionUtil.replaceFirstTokenOccurrence(null, UNDERSCORE, COLON);
    }

    /**
     * Negative Scenario- Without Underscore
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithoutUnderscore() {
        CharacterConversionUtil.replaceFirstTokenOccurrence(STRING_WITH_COLON, null, COLON);
    }

    /**
     * Negative Scenario- Without Colon
     */
    @Test(expected = IllegalArgumentException.class)
    public void testWithoutColon() {
        CharacterConversionUtil.replaceFirstTokenOccurrence(STRING_WITH_UNDERSCORE, UNDERSCORE, null);
    }

}
