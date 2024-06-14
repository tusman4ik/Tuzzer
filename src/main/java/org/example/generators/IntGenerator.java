package org.example.generators;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
public class IntGenerator implements Generator<Integer> {
    private int min = Integer.MIN_VALUE;
    private int max = Integer.MAX_VALUE;
    private boolean generateBorderLineValue = false;
    private final Random rand = new Random();

    private final List<Integer> borderlineValue = new ArrayList<>(List.of(
            Integer.MIN_VALUE,
            Integer.MIN_VALUE,
            0
    ));

    @Override
    public Class<Integer> getTargetClass() {
        return Integer.class;
    }

    @Override
    public Integer generate() {
        if(generateBorderLineValue && !borderlineValue.isEmpty()){
            int index = rand.nextInt(borderlineValue.size());
            int result =  borderlineValue.get(index);
            borderlineValue.remove(index);
            return result;
        }
        if(min > max){
            throw new IllegalArgumentException("min must be less than max");
        }
        long bound = Math.max(max*1L - min*1L, 1);
        long l = rand.nextLong(bound) + min;
        return ((int) l % Integer.MAX_VALUE);
    }

    public IntGenerator min(int min) {
        this.min = min;
        return this;
    }

    public IntGenerator max(int max) {
        this.max = max;
        return this;
    }

    public IntGenerator generateCustomBorderLineValue(Integer... borderlineValue) {
        this.borderlineValue.addAll(List.of(borderlineValue));
        this.generateBorderLineValue = true;
        return this;
    }

    public IntGenerator enableMandatoryGenerationBorderLineValue(){
        this.generateBorderLineValue = true;
        return this;
    }
}
