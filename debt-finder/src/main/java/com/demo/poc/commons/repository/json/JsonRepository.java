package com.demo.poc.commons.repository.json;


import com.demo.poc.commons.repository.CrudRepository;
import com.demo.poc.commons.repository.Entity;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@RequiredArgsConstructor
public class JsonRepository<T extends Entity> implements CrudRepository<T> {

  private final String filePath;
  private final TypeReference<List<T>> typeReference;

  @Override
  public List<T> findAll() {
    try {
      InputStream inputStream = JsonRepository.class.getClassLoader().getResourceAsStream(filePath);
      return new ObjectMapper().readValue(inputStream, typeReference);
    } catch (IOException ioException) {
      throw new RuntimeException("error reading json file: " + ioException.getMessage(), ioException);
    }
  }
}
