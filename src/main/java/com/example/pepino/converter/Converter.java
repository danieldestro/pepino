package com.example.pepino.converter;

import java.util.List;

public interface Converter<F, T> {

    T to(F obj);

    List<T> to(List<F> list);

    F from(T obj);

    List<F> from(List<T> list);
}
