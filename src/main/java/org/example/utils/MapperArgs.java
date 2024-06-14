package org.example.utils;


import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MapperArgs {
    public static Object[] map(List<Pair<Class<?>, Object>> args) {
        return args.stream()
                .map(s -> switch (s.second) {
                    case Integer i -> i.intValue();
                    case Long i -> i.longValue();
                    case Float i -> i.floatValue();
                    case Double i -> i.doubleValue();
                    case Boolean i -> i.booleanValue();
                    default -> s.first.cast(s.second);
                })
                .toArray();
    }
}
