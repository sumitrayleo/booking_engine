package com.ehi.msi.enterprisecarshare.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;

/**
 * The StringUtil is intended to provide some specific use-case driven utility
 * methods. These may leverage other existing libraries such as Apache Commons,
 * however the intent is that these can be used instead of or in addition to
 * those for more fine-tuned uses.
 */
public final class StringUtil {
	public static final String EMPTY_STRING = "";
	public static final String ERROR_NULL_LIST = "UnenrichedError null list";
	public static final String NULL_STRING = "null";
	public static final String STRING_COMMA = ",";
	public static final String STRING_ONE_SPACE = " ";
	public static final String STRING_DOUBLE_SPACE = STRING_ONE_SPACE
			+ STRING_ONE_SPACE;
	public static final String STRING_HYPHEN = "-";
	public static final Pattern PATTERN_REG_EXP_PERCENT_OR_UNDERSCORE = Pattern
			.compile("[\\%\\_]");
	public static final Pattern PATTERN_REG_EXP_DOUBLE_SPACES = Pattern
			.compile(STRING_DOUBLE_SPACE);
	public static final Pattern PATTERN_REG_WHITE_SPACE = Pattern
			.compile(STRING_ONE_SPACE);
	public static final Pattern PATTERN_REG_EXP_NON_ALPHA_NUMERIC = Pattern
			.compile("[^a-z^A-Z^0-9]");
	public static final Pattern PATTERN_REG_EXP_NON_NUMERIC = Pattern
			.compile("[^0-9]");

	private StringUtil() {
	}

	/**
	 * This method returns a concatenation of input strings using StringBuilder.
	 * 
	 * If the list of input String contains a null value an
	 * IllegalArgumentException will be thrown.
	 * 
	 * @param inputStrings
	 *            - String[] of values to be concatenated
	 * @return String of concatenated values
	 */
	public static final String concat(final String... inputStrings) {
		return StringUtils.join(inputStrings);
	}

	/**
	 * Replace every non-alpha, non-numeric character with the specified
	 * replacement string
	 * 
	 * @param origString
	 * @param replacementString
	 * @return String with replacements
	 */
	public static String filterNonAlphaNumeric(final String origString,
			final String replacementString) {
		return filterRegex(origString, replacementString,
				PATTERN_REG_EXP_NON_ALPHA_NUMERIC);
	}

	/**
	 * Replace all pattern matches within the original string with the
	 * replacement string
	 * 
	 * @param origString
	 * @param replacementString
	 * @return String with replacements
	 */
	static String filterRegex(final String origString,
			final String replacementString, final Pattern pattern) {
		final Matcher theMatcher = pattern.matcher(origString);
		return theMatcher.replaceAll(replacementString);
	}

	/**
	 * Replace any occurances of either a percentile (%) or underscore (_) with
	 * the replacement string
	 * 
	 * @param origString
	 * @param replacementString
	 * @return String with replacements
	 */
	public static String filterPercentUnderscore(final String origString,
			final String replacementString) {
		return filterRegex(origString, replacementString,
				PATTERN_REG_EXP_PERCENT_OR_UNDERSCORE);
	}

	/**
	 * Replace each occurrence of a double-space (' ') with the specified
	 * replacement string. If the specified replacement string results in a
	 * double-space, it will also be filtered.
	 * 
	 * @param origString
	 * @param replacementString
	 * @return String with replacements
	 */
	public static String filterDoubleSpaces(final String origString,
			final String replacementString) {
		final Matcher theMatcher = PATTERN_REG_EXP_DOUBLE_SPACES
				.matcher(origString);
		String newString = origString;
		newString = theMatcher.replaceAll(replacementString);
		// If the string was indeed filtered, then try another filtering
		// (recursive). Otherwise just return.
		if (StringUtils.isNotBlank(newString)
				&& newString.contains(STRING_DOUBLE_SPACE)) {
			newString = filterDoubleSpaces(newString, replacementString);
		}
		return newString;
	}

	/**
	 * Remove percentiles (%), underscores (_), and double-spaces from the
	 * provided string.
	 * 
	 * @param stringToFilter
	 * @return String with characters removed
	 */
	public static String filterString(final String stringToFilter) {
		String newString = stringToFilter;
		if (StringUtils.isNotBlank(stringToFilter)) {
			newString = filterPercentUnderscore(newString, EMPTY_STRING);
			newString = filterDoubleSpaces(newString, STRING_ONE_SPACE);
		}
		return newString;
	}

	/**
	 * Remove any non-alpha, non-numeric characters from the provided string.
	 * 
	 * @param stringToFilter
	 * @return String with characters removed
	 */
	public static String removeNonAlphaNumeric(final String stringToFilter) {
		String newString = stringToFilter;
		if (StringUtils.isNotBlank(stringToFilter)) {
			newString = filterNonAlphaNumeric(newString, EMPTY_STRING);
		}
		return newString;
	}

	/**
	 * Mask the string outside of the provided position with the masking
	 * character. The length is kept at the same length as the original string.
	 * 
	 * @param origVal
	 *            - The string to be partially masked
	 * @param startPos
	 *            - The starting position of the {@code origVal} to be kept
	 *            unmasked. Zero (0) represents the first character.
	 * @param endPos
	 *            - The ending position, of the {@code origVal} to be kept
	 *            unmasked. One (1) represents the stopping position
	 *            <strong>after</strong> the first character.
	 * @param maskingChar
	 *            - The character used in the masked character positions.
	 * @return String - The process string after masking is applied.
	 */
	public static String partiallyMaskString(final String origVal,
			final int startPos, final int endPos, final char maskingChar) {
		final int safeLength = origVal != null ? origVal.length() : 0;
		return partiallyMaskStringExtendLeft(origVal, startPos, endPos,
				maskingChar, safeLength);
	}

	/**
	 * Mask the string outside of the provided position with the masking
	 * character. The length is based on {@code resultLength}, which will
	 * override the length of {@code origVal} in a both additive or subtractive
	 * manner, as necessary.
	 * 
	 * @param origVal
	 *            - The string to be partially masked
	 * @param startPos
	 *            - The starting position of the {@code origVal} to be kept
	 *            unmasked. Zero (0) represents the first character.
	 * @param endPos
	 *            - The ending position, of the {@code origVal} to be kept
	 *            unmasked. One (1) represents the stopping position
	 *            <strong>after</strong> the first character.
	 * @param maskingChar
	 *            - The character used in the masked character positions.
	 * @param resultLength
	 *            - The length of the final string. If longer than
	 *            {@code origVal}, additional masking characters will be added
	 *            to the right side. If shorter than {@code origVal}, characters
	 *            will be truncated from the right side.
	 * @return String - The process string after masking is applied.
	 */
	public static String partiallyMaskStringExtendRight(final String origVal,
			final int startPos, final int endPos, final char maskingChar,
			final int resultLength) {
		String result = null;
		if (origVal != null) {
			final int safeStartPos = NumberUtils.max(0, 0, startPos);
			final int safeEndPos = endPos > origVal.length() ? origVal.length()
					: endPos;

			// Get the substring to be wrapped in masking characters
			result = origVal.substring(safeStartPos, safeEndPos);

			// Truncate from the right to the resultLength in case it's longer
			result = StringUtils.left(result, resultLength);

			// Left-padding based on where the substring starts
			result = StringUtils.leftPad(result,
					safeStartPos + result.length(), maskingChar);

			// Remaining right padding, only if {@code result}'s length is less
			// than the intended length
			result = StringUtils.rightPad(result, resultLength, maskingChar);
		}
		return result;
	}

	/**
	 * Mask the string outside of the provided position with the masking
	 * character. The length is based on {@code resultLength}, which will
	 * override the length of {@code origVal} in a both additive or subtractive
	 * manner, as necessary.
	 * 
	 * @param origVal
	 *            - The string to be partially masked
	 * @param startPos
	 *            - The starting position of the {@code origVal} to be kept
	 *            unmasked. Zero (0) represents the first character.
	 * @param endPos
	 *            - The ending position, of the {@code origVal} to be kept
	 *            unmasked. One (1) represents the stopping position
	 *            <strong>after</strong> the first character.
	 * @param maskingChar
	 *            - The character used in the masked character positions.
	 * @param resultLength
	 *            - The length of the final string. If longer than
	 *            {@code origVal}, additional masking characters will be added
	 *            to the left side. If shorter than {@code origVal}, characters
	 *            will be truncated from the left side.
	 * @return String - The process string after masking is applied.
	 */
	public static String partiallyMaskStringExtendLeft(final String origVal,
			final int startPos, final int endPos, final char maskingChar,
			final int resultLength) {
		String result = null;
		if (origVal != null) {
			final int safeStartPos = NumberUtils.max(0, 0, startPos);
			final int safeEndPos = endPos > origVal.length() ? origVal.length()
					: endPos;

			// Get the substring to be wrapped in masking characters
			result = origVal.substring(safeStartPos, safeEndPos);

			// Truncate from the left to the resultLength in case it's longer
			result = StringUtils.right(result, resultLength);

			// Right-padding based on where the substring starts
			final int safeRightPadLength = origVal.length() - safeStartPos <= resultLength ? origVal
					.length() - safeStartPos
					: resultLength;
			result = StringUtils.rightPad(result, safeRightPadLength,
					maskingChar);

			// Remaining left padding, only if {@code result}'s length is less
			// than the intended length
			result = StringUtils.leftPad(result, resultLength, maskingChar);
		}

		return result;
	}

}
