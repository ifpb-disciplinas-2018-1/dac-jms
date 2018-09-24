/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.dac.cartaocredito;

import java.sql.Date;
import java.time.LocalDate;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 *
 * @author jose
 */
@Converter()
public class LocalDatePersistenceConverter implements AttributeConverter<LocalDate, Date>  {
    @Override
    public Date convertToDatabaseColumn(LocalDate locDate) {
        if (locDate != null) {
            return Date.valueOf(locDate);
        } else {
            return null;
        }

    }

    @Override
    public LocalDate convertToEntityAttribute(Date sqlDate) {
        if (sqlDate != null) {
            return sqlDate.toLocalDate();
        } else {
            return null;
        }
}
}
