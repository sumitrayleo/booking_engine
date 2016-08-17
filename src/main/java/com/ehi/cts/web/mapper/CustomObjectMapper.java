package com.ehi.cts.web.mapper;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;

/**
 * Custom Object Mapper class used for JSON serialization/De-Serialization. This
 * source code was previously a part of cts-common and later was incorporated in
 * carshare-msi.
 * 
 */
@Component
@SuppressWarnings("serial")
public class CustomObjectMapper extends ObjectMapper {

	private static final String CALENDAR_PARSING_FORMAT_SERIALIZE = "yyyy-MM-dd";

	public CustomObjectMapper() {

		// format the serialization
		setSerializationInclusion(JsonInclude.Include.NON_NULL);
		setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		configure(SerializationFeature.INDENT_OUTPUT, false);

		// return timezone with responses from associated calendar
		final Version version = new Version(1, 0, 0, "", null, null);
		SimpleModule module = new SimpleModule("CalendarModule", version);
		module = module.addSerializer(new CustomJsonCalendarSerializer());
		module = module.addDeserializer(Calendar.class,
				new CustomJsonCalendarDeserializer());
		registerModule(module);
	}

	public class CustomJsonCalendarSerializer extends JsonSerializer<Calendar> {

		@Override
		public Class<Calendar> handledType() {
			return Calendar.class;
		}

		@Override
		public void serialize(Calendar value, JsonGenerator jgen,
				SerializerProvider provider) throws IOException,
				JsonProcessingException {

			try {
				final SimpleDateFormat formatter = new SimpleDateFormat(
						CALENDAR_PARSING_FORMAT_SERIALIZE);
				formatter.setTimeZone(value.getTimeZone());
				jgen.writeString(formatter.format(value.getTime()));
			} catch (final Exception e) {
				e.printStackTrace();
			}
		}
	}

	public class CustomJsonCalendarDeserializer extends
			JsonDeserializer<Calendar> {

		@Override
		public Class<Calendar> handledType() {
			return Calendar.class;
		}

		// Note: this function DOES NOT preserve time Zone information
		@Override
		public Calendar deserialize(JsonParser arg0, DeserializationContext arg1)
				throws IOException, JsonProcessingException {
			final SimpleDateFormat parser = new SimpleDateFormat(
					CALENDAR_PARSING_FORMAT_SERIALIZE);
			final Calendar cal = Calendar.getInstance();
			try {
				cal.setTime(parser.parse(arg0.getText()));
			} catch (final ParseException e) {
				e.printStackTrace();
			}
			return cal;
		}
	}
}
