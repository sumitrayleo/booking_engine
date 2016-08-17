package com.ehi.msi.enterprisecarshare.mapper.converter;

import org.junit.Before;
import org.junit.Test;

import com.ehi.msi.test.AbstractSpringTest;

/**
 * Test class to check custom dozer converter between Integer and formatted Date String in {@code yyyy-MM-dd} format
 */
public class FormattedDateToIntegerConverterTest extends AbstractSpringTest {
    private FormattedDateToIntegerConverter dateToIntegerConverter;
    private final static String FOMATTED_DATE = "2015-12-31";

    @Before
    public void createDateToIntegerConverter() {
        dateToIntegerConverter = new FormattedDateToIntegerConverter();
    }

    /**
     * This test method will check both the converter method of {@link FormattedDateToIntegerConverter}
     */
    @Test
    public void testConversionsBothWays() {
        final int dateAsInteger = dateToIntegerConverter.convertFrom(FOMATTED_DATE);
        final String actualDate = dateToIntegerConverter.convertTo(dateAsInteger);
        assertEquals(FOMATTED_DATE, actualDate);
    }

}
