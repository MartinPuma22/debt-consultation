package com.demo.poc.commons.repository.csv;


import com.demo.poc.commons.repository.Entity;
import org.apache.commons.csv.CSVRecord;

import java.lang.reflect.Field;

public class CsvReflectionMapper {

    public static <T extends Entity> T assignFieldsAndGet(Class<T> entityType, CSVRecord record) {
        try {
            T entity = entityType.getDeclaredConstructor().newInstance();
            Class<?> currentClass = entityType;
            while (currentClass != null) {
                assignFields(currentClass.getDeclaredFields(), record, entity);
                currentClass = currentClass.getSuperclass();
            }
            return entity;
        } catch (Exception exception) {
            throw new RuntimeException("Unable to assign field: " + exception.getMessage());
        }
    }

    private static <T extends Entity> void assignFields(Field[] fields, CSVRecord record, T element) throws IllegalAccessException {
        for (Field field : fields) {
            field.setAccessible(true);
            String fieldNameInSnakeCase = camelToUpperSnake(field.getName());
            field.set(element, parseValue(field.getType(), record.get(fieldNameInSnakeCase)));
        }
    }

    private static String camelToUpperSnake(String camelCase) {
        if (camelCase == null || camelCase.isEmpty()) {
            throw new IllegalArgumentException("Input string cannot be null or empty");
        }

        return camelCase
            .replaceAll("([a-z])([A-Z])", "$1_$2")
            .toUpperCase();
    }

    private static Object parseValue(Class<?> type, String value) {
        if (type == Long.class || type == long.class)
            return Long.parseLong(value);

        if (type == Integer.class || type == int.class)
            return Integer.parseInt(value);

        if (type == Double.class || type == double.class)
            return Double.parseDouble(value);

        if (type == Float.class || type == float.class)
            return Float.parseFloat(value);

        if (type == Boolean.class || type == boolean.class)
            return Boolean.parseBoolean(value);

        return value;
    }
}