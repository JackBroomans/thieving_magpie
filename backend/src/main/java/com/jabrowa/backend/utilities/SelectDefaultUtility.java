package com.jabrowa.backend.utilities;

import com.jabrowa.backend.model.interfaces.Selectable;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Optional;

/**
 * <strong>SelectDefaultUtility</strong> - Utility class<br><br>
 * Generic utility class which contains the method that selects the default set element from an enumerator.
 */
public class SelectDefaultUtility<T extends Enum<T>> {
    private final static String NO_ELEMENTS_PASSED =
            "No (enumerated) elements passed to search for the default set elements!";

    private final Class<T> enumType;

    public SelectDefaultUtility(Class<T> enumType) {
        this.enumType = enumType;
    }

    public Optional<T> getDefaultSetting() {
        try {
            for (T constant : enumType.getEnumConstants()) {
                for (Field field : enumType.getDeclaredFields()) {
                    if (!field.isEnumConstant() && !Modifier.isStatic(field.getModifiers())) {
                        field.setAccessible(true);
                        Object value = field.get(constant);
                        return Optional.of(T.));
                    }
                }
            }

            return Optional.of(Enum.valueOf(enumType, name);
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public T fromString(String name) {
        return Enum.valueOf(enumType, name);
    }
}
