package org.example.utils;


import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Setter
public class Result {
    private Class<?> targetClass;
    private String methodName;
    private boolean isPassed;
    private long countOfTrying;
    private List<Pair<Throwable, List<Object>>> exceptions = new ArrayList<>();


}
