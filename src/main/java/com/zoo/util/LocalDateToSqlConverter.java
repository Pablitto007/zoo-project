package com.zoo.util;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
/**
 * LocalDate need to be converted to SqlDate because LocalDate 
 * is not supported  in JPA yet (2.1 at the moment))
 * 
 * @author Paweł (Pablitto007)
 */
@Converter(autoApply = true)
public class LocalDateToSqlConverter implements AttributeConverter<LocalDate, Date> {
	
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
    	return (locDate == null ? null : Date.valueOf(locDate));
    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
    	return (sqlDate == null ? null : sqlDate.toLocalDate());
    }
}
