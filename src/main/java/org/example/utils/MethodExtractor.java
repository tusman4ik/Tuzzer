package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;

@Slf4j
public class MethodExtractor {
    public static Method get(Tuzzer tuzzer) {
        Class<?> clazz = tuzzer.getTarget().getClass();
        Class<?>[] args = tuzzer.getGenerators().stream()
                .map(t->t.first)
                .toArray(Class<?>[]::new);
        try {
            return clazz.getMethod(tuzzer.getMethod(), args);
        } catch (NoSuchMethodException e) {
            log.warn("Class = {}, args = {}",clazz, Arrays.toString(args));
            Arrays.stream(clazz.getMethods()).forEach(t->
                    log.warn("->name = {}, args = {}",t.getName(), Arrays.toString(t.getParameterTypes())));
            throw new RuntimeException(e);
        }
    }
}
