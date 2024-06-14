package org.example.utils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Pair<F, S> {
    public final F first;
    public final S second;

    public Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
}