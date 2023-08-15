package com.example.be_dolan_final.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

@Component
public class XmlMapperUtils {
    private final static ObjectMapper objectMapper = XmlMapper.builder().build();

    public static ObjectMapper getObjectMapper() {
        return objectMapper;
    };
}
