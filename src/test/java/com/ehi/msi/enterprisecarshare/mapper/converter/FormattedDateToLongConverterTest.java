package com.ehi.msi.enterprisecarshare.mapper.converter;

import org.junit.Before;
import org.junit.Test;

import com.ehi.msi.test.AbstractSpringTest;

/**
 * Test class to check custom dozer converter between Long and formatted Date String in {@code yyyyMMdd} format
 */
public class FormattedDateToLongConverterTest extends AbstractSpringTest {
    private FormattedDateToLongConverter dateToLongConverter;
    private final static String FORMATTED_DATE = "20151231";

    @Before
    public void createStringToLongConverter() {
        dateToLongConverter = new FormattedDateToLongConverter();
    }

    /**
     * This test method will check both the converter method of {@link FormattedDateToLongConverter}
     */
    @Test
    public void testConversionsBothWays() {
        final Long dateAsLong = dateToLongConverter.convertFrom(FORMATTED_DATE);
        final String actualDate = dateToLongConverter.convertTo(dateAsLong);
        assertEquals(FORMATTED_DATE, actualDate);
    }

}
