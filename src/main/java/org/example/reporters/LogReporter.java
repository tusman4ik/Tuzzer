package org.example.reporters;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.Result;

import java.util.stream.Collectors;

@Slf4j
public class LogReporter implements Reporter{
    @Override
    public void report(Result result) {
        log.info("Class: {}, method: {}", result.getTargetClass(), result.getMethodName());
        log.info("Is passed: {}, count of trying: {}", result.isPassed(), result.getCountOfTrying());
        if(!result.getExceptions().isEmpty()){
            log.info("***EXCEPTIONS***\t(COUNT: {})", result.getExceptions().size());
            result.getExceptions().forEach(t-> {
                String exception = t.first.getCause().getClass().toString();
                log.info("\tException: {}, args: {}", exception, t.second.stream().map(Object::toString).collect(Collectors.joining(" ")));
            });
        }
    }
}
