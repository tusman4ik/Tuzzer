package org.example.generators;

@SuppressWarnings("unused")
public interface Generator<T> {

    Class<T> getTargetClass();

    T generate();
}
