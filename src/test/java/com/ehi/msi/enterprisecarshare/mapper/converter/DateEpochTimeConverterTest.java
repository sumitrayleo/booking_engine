package com.ehi.msi.enterprisecarshare.mapper.converter;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.ehi.msi.test.AbstractSpringTest;

/**
 * Test class for DateEpochTimeConverter
 * 
 */
public class DateEpochTimeConverterTest extends AbstractSpringTest {
    private DateEpochTimeConverter dateEpochTimeConverter;

    @Before
    public void createDateEpochTimeConverter() {
        dateEpochTimeConverter = new DateEpochTimeConverter();
    }

    /**
     * Test Long to Date conversion
     */
    @Test
    public void testLongToCalendar() {
        Assert.assertNotNull(dateEpochTimeConverter.convertTo(new Long(1432729959), null));
    }

    /**
     * Test Date to Long conversion
     */
    @Test
    public void testCalendarToLong() {
        Assert.assertNotNull(dateEpochTimeConverter.convertFrom(Calendar.getInstance(), null));
    }
}
