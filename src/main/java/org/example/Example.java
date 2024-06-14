package org.example;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Example {
    public int dev(int a, int b, int c){
        if(c == 123 || b == 13 || a == 12){
            throw new IllegalStateException();
        }
        return (a/b)+(b/c);
    }
}
