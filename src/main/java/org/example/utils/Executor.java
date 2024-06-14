package org.example.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class Executor {

    private final Tuzzer tuzzer;
    private final Result result;
    private final Method method;

    public Executor(Tuzzer tuzzer) {
        this.tuzzer = tuzzer;
        result = new Result();
        result.setTargetClass(tuzzer.getTarget().getClass());
        result.setMethodName(tuzzer.getMethod());
        method = MethodExtractor.get(tuzzer);
    }

    public void execute() {

        switch (tuzzer.getLimiter().type()) {
            case DURATION -> executeWithCountLimiter();
            case COUNT -> executeWithDurationLimiter();
            default -> throw new IllegalStateException("Unexpected value: " + tuzzer.getLimiter().type());
        }

        tuzzer.getReporters().forEach(t -> t.report(result));

        if (!result.isPassed()) {
            throw new TuzzerFailureException();
        }
    }


    private void executeWithDurationLimiter() {
        int countOfTrying = 0;
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() < start + tuzzer.getLimiter().getDuration().toMillis()) {
            countOfTrying++;
            invoke();
        }
        result.setCountOfTrying(countOfTrying);
    }


    private void executeWithCountLimiter() {
        for (int i = 0; i <= tuzzer.getLimiter().getCount(); i++) {
            invoke();
        }
        result.setCountOfTrying(tuzzer.getLimiter().getCount());
    }

    private void invoke() {
        Object[] args = MapperArgs.map(generateArg());
        try {
            method.invoke(tuzzer.getTarget(), args);
        } catch (Exception e) {
            handleException(e, args);
        }
    }

    private void handleException(Throwable e, Object[] args) {
        if (tuzzer.getExpectedExceptions().contains(e.getClass()))
            return;

        result.getExceptions().add(new Pair<>(e, Arrays.stream(args).toList()));
    }

    List<Pair<Class<?>, Object>> generateArg() {
        return tuzzer.getGenerators().stream()
                        .map(t->new Pair<Class<?>, Object>(t.first, t.second.generate()))
                        .toList();

    }
}
