package com.ehi.msi.enterprisecarshare.mapper.converter;

import java.util.Calendar;
import java.util.TimeZone;

import org.dozer.DozerConverter;

/**
 * Custom Converter class to convert a date passed as Long to java.util.Calendar
 * object and vice versa. The advantage of creating sub class from
 * DozerConverter is to leave up to the Dozer to take care of the type casting
 * and utilize the MappingException in case the types at runtime does not match.
 * 
 */
public class DateEpochTimeConverter extends DozerConverter<Long, Calendar> {
	public DateEpochTimeConverter() {
		super(Long.class, Calendar.class);
	}

	/*
	 * @see org.dozer.DozerConverter#convertFrom(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public Long convertFrom(Calendar source, Long destination) {
		Long date = null;
		if (source != null) {
			final TimeZone tz = source.getTimeZone();
			final int offsetFromUTC = tz.getOffset(source.getTimeInMillis());
			source.add(Calendar.MILLISECOND, offsetFromUTC);
			date = source.getTimeInMillis() / 1000;
		}
		return date;
	}

	/*
	 * @see org.dozer.DozerConverter#convertTo(java.lang.Object,
	 * java.lang.Object)
	 */
	@Override
	public Calendar convertTo(Long source, Calendar destination) {
		Calendar cal = null;
		if (source != null) {
			cal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
			cal.setTimeInMillis(source * 1000);
		}
		return cal;
	}

}
