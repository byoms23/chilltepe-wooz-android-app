package me.wooz.mobile.android.utils;

import org.greenrobot.greendao.converter.PropertyConverter;
import org.joda.time.DateTime;


public class JodaDateTimeConverter implements PropertyConverter<DateTime, Long> {

	@Override
	public DateTime convertToEntityProperty(Long databaseValue) {
		return new DateTime(databaseValue);
	}

	@Override
	public Long convertToDatabaseValue(DateTime entityProperty) {
		return entityProperty.getMillis();
	}
}

