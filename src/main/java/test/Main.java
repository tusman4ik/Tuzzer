package test;

import lombok.extern.slf4j.Slf4j;
import org.example.Test;
import org.example.utils.Tuzzer;
import org.example.generators.IntGenerator;
import org.example.limiters.Limiter;
import org.example.reporters.LogReporter;

@Slf4j
public class Main {
    public static void main(String[] args) {
        Tuzzer.of(new Test(),"dev")
                .withArg(int.class, new IntGenerator().generateCustomBorderLineValue(123))
                .withArg(int.class, new IntGenerator().generateCustomBorderLineValue(123))
                .withArg(int.class, new IntGenerator().generateCustomBorderLineValue(123))
                .addReporter(new LogReporter())
                .withLimit(Limiter.of(10_000_000))
                .execute();
    }
}
