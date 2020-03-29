package com.cuijing.sundial_dream.utils;

import com.google.common.base.Throwables;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public interface Lambdas {
    static <T> Optional<T> first(Iterable<T> iterable) {
        return (Optional<T>) Optional.ofNullable(Iterables.getFirst(iterable, (Object)null));
    }

    static <T> Consumer<T> emptyConsumer() {
        return Lambdas.Holder.EMPTY_CONSUMER;
    }

    static <T, U> BiConsumer<T, U> emptyBiConsumer() {
        return Lambdas.Holder.EMPTY_BI_CONSUMER;
    }

    static <T> List<T> emptyList(List<T> list) {
        return list == null ? Collections.emptyList() : list;
    }

    static <K, V> Map<K, V> emptyMap(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : map;
    }

    static <T> Stream<T> stream(Iterable<T> iterable) {
        return iterable == null ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterable.iterator(), 16), false);
    }

    static <T> Stream<T> stream(Iterator<T> iterator) {
        return iterator == null ? Stream.empty() : StreamSupport.stream(Spliterators.spliteratorUnknownSize(iterator, 16), false);
    }

    static <T> Stream<T> stream(Collection<T> collection) {
        return collection != null && !collection.isEmpty() ? collection.stream() : Stream.empty();
    }

    static <T, C extends Iterable<T>> C each(C t, Consumer<? super T> action) {
        if (t != null) {
            t.forEach(action);
        }

        return t;
    }

    static <C> C[] each(C[] t, Consumer<? super C> action) {
        if (t != null) {
            Object[] var2 = t;
            int var3 = t.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                C c = (C) var2[var4];
                action.accept(c);
            }
        }

        return t;
    }

    static <T, C extends Iterable<T>> Optional<Integer> firstIndex(C t, Predicate<? super T> action) {
        if (t != null) {
            int i = 0;

            for(Iterator var3 = t.iterator(); var3.hasNext(); ++i) {
                T v = (T) var3.next();
                if (action.test(v)) {
                    return Optional.of(i);
                }
            }
        }

        return Optional.empty();
    }

    static <T, C extends Collection<T>> Optional<T> removeFirst(C t, Predicate<? super T> action) {
        if (t != null) {
            Iterator iterator = t.iterator();

            while(iterator.hasNext()) {
                T v = (T) iterator.next();
                if (action.test(v)) {
                    iterator.remove();
                    return Optional.of(v);
                }
            }
        }

        return Optional.empty();
    }

    static <V> V accept(V v, Consumer<? super V> action) {
        if (v != null) {
            action.accept(v);
        }

        return v;
    }

    static <V, R> R apply(V v, Function<? super V, R> action) {
        return v == null ? null : action.apply(v);
    }

    static <V, R> R apply(V v, R def, Function<? super V, R> action) {
        return v == null ? def : action.apply(v);
    }

    static <T> Function<T, T> identity(Consumer<? super T> action) {
        return (v) -> {
            action.accept(v);
            return v;
        };
    }

    static <T> T swallow(Callable<T> call) {
        try {
            return call.call();
        } catch (Exception var2) {
            throw Throwables.propagate(var2);
        }
    }

    static <T, C extends Collection<T>> C remove(C t, Predicate<? super T> action) {
        if (t != null) {
            t.removeIf(action::test);
        }

        return t;
    }

    static <T> Optional<T> instanceOf(Object o, Class<T> type) {
        return o != null && type.isAssignableFrom(o.getClass()) ? Optional.of(type.cast(o)) : Optional.empty();
    }

    static <T> Optional<Class<T>> isAssignableFrom(Class o, Class<T> type) {
        return o != null && type.isAssignableFrom(o) ? Optional.of(o) : Optional.empty();
    }

    @SafeVarargs
    static <T> Optional<T> first(Supplier<Optional<T>>... suppliers) {
        Supplier[] var1 = suppliers;
        int var2 = suppliers.length;

        for(int var3 = 0; var3 < var2; ++var3) {
            Supplier<Optional<T>> supplier = var1[var3];
            Optional<T> opt = (Optional)supplier.get();
            if (opt.isPresent()) {
                return opt;
            }
        }

        return Optional.empty();
    }

    @SafeVarargs
    static <V, T> Optional<T> first(V v, Function<V, Optional<T>>... funs) {
        if (v == null) {
            return Optional.empty();
        } else {
            Function[] var2 = funs;
            int var3 = funs.length;

            for(int var4 = 0; var4 < var3; ++var4) {
                Function<V, Optional<T>> fun = var2[var4];
                Optional<T> opt = (Optional)fun.apply(v);
                if (opt.isPresent()) {
                    return opt;
                }
            }

            return Optional.empty();
        }
    }

    static <T> List<T> move(List<T> s, Predicate<T> test) {
        List<T> d = Lists.newArrayList();

        for(int i = 0; i < s.size(); ++i) {
            T v = s.get(i);
            if (test.test(v)) {
                d.add(v);
                s.remove(v);
                --i;
            }
        }

        return d;
    }

    static <T> void onlyMove(List<T> s, Predicate<T> test) {
        for(int i = 0; i < s.size(); ++i) {
            T v = s.get(i);
            if (test.test(v)) {
                s.remove(v);
                --i;
            }
        }

    }

    public static class Holder {
        private static final Consumer EMPTY_CONSUMER = (v) -> {
        };
        private static final BiConsumer EMPTY_BI_CONSUMER = (a, b) -> {
        };

        public Holder() {
        }
    }
}
