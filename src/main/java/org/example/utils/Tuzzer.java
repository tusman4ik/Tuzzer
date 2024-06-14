package org.example.utils;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.example.generators.Generator;
import org.example.limiters.Limiter;
import org.example.reporters.Reporter;

import java.util.*;

@Slf4j
@Getter(value = AccessLevel.PACKAGE)
public final class Tuzzer {

    private final List<Reporter> reporters = new ArrayList<>();
    private final Object target;
    private final String method;
    private final List<Pair<Class<?>, Generator<?>>> generators = new ArrayList<>();
    private final List<Class<? extends Throwable>> expectedExceptions = new ArrayList<>();

    {
        limiter = Limiter.of(100);
    }

    private Limiter limiter;

    private Tuzzer(String method, Object target) {
        this.method = method;
        this.target = target;
    }

    public static Tuzzer of(Object target, String method){
        return new Tuzzer(method, target);
    }
    public Tuzzer withLimit(Limiter limiter) {
        this.limiter = limiter;
        return this;
    }

    public Tuzzer withArg(Class<?> cast, Generator<?> generator) {
        generators.add(new Pair<>(cast, generator));
        return this;
    }
    public Tuzzer addReporter(Reporter... reporter) {
        reporters.addAll(Arrays.stream(reporter).toList());
        return this;
    }

    public Tuzzer expect(Class<? extends Throwable>... expected){
        this.expectedExceptions.addAll(Arrays.stream(expected).toList());
        return this;
    }

    public void execute(){
        new Executor(this).execute();
    }

}
