package com.ehi.msi.enterprisecarshare.mapper.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import org.apache.commons.lang.StringUtils;
import org.dozer.DozerConverter;
import org.dozer.converters.ConversionException;

/**
 * This custom dozer converter is used to map String Formatted date to Long Date
 * and eliminate the millisecond part in it
 * 
 */
public class FormattedDateToLongConverter extends DozerConverter<Long, String> {

	private DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");

	public FormattedDateToLongConverter() {
		super(Long.class, String.class);
	}

	@Override
	public Long convertFrom(String source, Long destination) {
		try {
			if (StringUtils.isNotEmpty(source)) {
				final Date currentTimeZoneDate = dateFormat.parse(source);
				final Calendar gmtCalendar = Calendar.getInstance(TimeZone
						.getTimeZone("GMT"));
				gmtCalendar.setTime(currentTimeZoneDate);
				return gmtCalendar.getTimeInMillis() / 1000;
			}
			return destination;
		} catch (ParseException e) {
			throw new ConversionException(
					"Unable to parse source object using specified date format",
					e);
		}
	}

	@Override
	public String convertTo(Long source, String destination) {
		if (source != null) {
			final Calendar gmtCalendar = Calendar.getInstance(TimeZone
					.getTimeZone("GMT"));
			gmtCalendar.setTimeInMillis(source * 1000);
			return dateFormat.format(gmtCalendar.getTimeInMillis());
		}
		return destination;
	}
}
