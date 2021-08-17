package com.example.mongopersonlist.converter.todto;

import com.example.mongopersonlist.dto.middle.PersonPage;
import com.example.mongopersonlist.dto.response.PersonPageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

/**
 * Converter for converting PersonPage to PersonPageResponse
 */
@Component
@RequiredArgsConstructor
public class PersonPageToPersonPageResponse implements Converter<PersonPage, PersonPageResponse> {

    private final PersoneToPersoneResponseConverter personeConverter;

    @Override
    public PersonPageResponse convert(PersonPage personPage) {
        return PersonPageResponse.builder()
                .persons(personPage.getPersons().stream()
                        .map(personeConverter::convert)
                        .collect(Collectors.toList()))
                .number(personPage.getNumber())
                .size(personPage.getSize())
                .totalElements(personPage.getTotalElements())
                .totalPages(personPage.getTotalPages())
                .build();
    }
}
