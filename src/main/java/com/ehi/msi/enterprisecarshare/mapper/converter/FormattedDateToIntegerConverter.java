package com.ehi.msi.enterprisecarshare.mapper.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.dozer.DozerConverter;
import org.dozer.converters.ConversionException;

/**
 * This custom dozer converter is used to map String Formatted date to Integer
 * Date and eliminate the millisecond part in it
 * 
 */
public class FormattedDateToIntegerConverter extends
		DozerConverter<Integer, String> {

	private DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	public FormattedDateToIntegerConverter() {
		super(Integer.class, String.class);
	}

	@Override
	public Integer convertFrom(String source, Integer destination) {
		try {
			return Integer.valueOf((Long.valueOf(dateFormat.parse(source)
					.getTime() / 1000)).intValue());
		} catch (ParseException e) {
			throw new ConversionException(
					"Unable to parse source object using specified date format",
					e);
		}
	}

	@Override
	public String convertTo(Integer source, String destination) {
		final Date newDate = new Date(source.longValue() * 1000);
		return dateFormat.format(newDate);
	}
}
