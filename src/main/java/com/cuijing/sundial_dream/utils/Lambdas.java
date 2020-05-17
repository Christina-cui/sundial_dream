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
	    return Optional.ofNullable(Iterables.getFirst(iterable, null));
	  }

	  @SuppressWarnings("unchecked")
	  static <T> Consumer<T> emptyConsumer() {
	    return Holder.EMPTY_CONSUMER;
	  }

	  @SuppressWarnings("unchecked")
	  static <T, U> BiConsumer<T, U> emptyBiConsumer() {
	    return Holder.EMPTY_BI_CONSUMER;
	  }

	  static <T> List<T> emptyList(List<T> list) {
	    return list == null ? Collections.emptyList() : list;
	  }

	  static <K, V> Map<K, V> emptyMap(Map<K, V> map) {
	    return map == null ? Collections.emptyMap() : map;
	  }

	  //    static boolean isEmpty(Consumer<?> v) {
	  //        return v == Holder.EMPTY_CONSUMER;
	  //    }

	  /** Create stream for iterable */
	  static <T> Stream<T> stream(Iterable<T> iterable) {
	    if (iterable == null) {
	      return Stream.empty();
	    }
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(iterable.iterator(), Spliterator.ORDERED), false);
	  }

	  static <T> Stream<T> stream(Iterator<T> iterator) {
	    if (iterator == null) {
	      return Stream.empty();
	    }
	    return StreamSupport.stream(
	        Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
	  }

	  static <T> Stream<T> stream(Collection<T> collection) {
	    if (collection == null || collection.isEmpty()) {
	      return Stream.empty();
	    }
	    return collection.stream();
	  }

	  /** Null-safe foreach */
	  static <T, C extends Iterable<T>> C each(C t, Consumer<? super T> action) {
	    if (t != null) {
	      t.forEach(action);
	    }
	    return t;
	  }

	  /** Null-safe foreach */
	  static <C> C[] each(C[] t, Consumer<? super C> action) {
	    if (t != null) {
	      for (C c : t) {
	        action.accept(c);
	      }
	    }
	    return t;
	  }

	  static <T, C extends Iterable<T>> Optional<Integer> firstIndex(C t, Predicate<? super T> action) {
	    if (t != null) {
	      int i = 0;
	      for (T v : t) {
	        if (action.test(v)) {
	          return Optional.of(i);
	        }
	        i++;
	      }
	    }
	    return Optional.empty();
	  }

	  static <T, C extends Collection<T>> Optional<T> removeFirst(C t, Predicate<? super T> action) {
	    if (t != null) {
	      for (Iterator<T> iterator = t.iterator(); iterator.hasNext(); ) {
	        T v = iterator.next();
	        if (action.test(v)) {
	          iterator.remove();
	          return Optional.of(v);
	        }
	      }
	    }
	    return Optional.empty();
	  }

	  /** Null-safe action */
	  static <V> V accept(V v, Consumer<? super V> action) {
	    if (v != null) {
	      action.accept(v);
	    }
	    return v;
	  }

	  /** Null-safe function */
	  static <V, R> R apply(V v, Function<? super V, R> action) {
	    if (v == null) {
	      return null;
	    }
	    return action.apply(v);
	  }

	  static <V, R> R apply(V v, R def, Function<? super V, R> action) {
	    if (v == null) {
	      return def;
	    }
	    return action.apply(v);
	  }

	  /** Same as {@link Function#identity()} */
	  static <T> Function<T, T> identity(Consumer<? super T> action) {
	    return v -> {
	      action.accept(v);
	      return v;
	    };
	  }

	  static <T> T swallow(Callable<T> call) {
	    try {
	      return call.call();
	    } catch (Exception e) {
	      throw Throwables.propagate(e);
	    }
	  }

	  static <T, C extends Collection<T>> C remove(C t, Predicate<? super T> action) {
	    if (t != null) {
	      t.removeIf(action::test);
	    }
	    return t;
	  }

	  static <T> Optional<T> instanceOf(Object o, Class<T> type) {
	    if (o != null && type.isAssignableFrom(o.getClass())) {
	      return Optional.of(type.cast(o));
	    }
	    return Optional.empty();
	  }

	  @SuppressWarnings("unchecked")
	  static <T> Optional<Class<T>> isAssignableFrom(Class o, Class<T> type) {
	    if (o != null && type.isAssignableFrom(o)) {
	      return Optional.of((Class<T>) o);
	    }
	    return Optional.empty();
	  }

	  /** 因为 Java 8 的 Optional 没有 orElse + Optional 的操作, 使得拼接多个 Optional 的时候很麻烦 */
	  @SafeVarargs
	  static <T> Optional<T> first(Supplier<Optional<T>>... suppliers) {
	    for (Supplier<Optional<T>> supplier : suppliers) {
	      Optional<T> opt = supplier.get();
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
	    }
	    for (Function<V, Optional<T>> fun : funs) {
	      Optional<T> opt = fun.apply(v);
	      if (opt.isPresent()) {
	        return opt;
	      }
	    }
	    return Optional.empty();
	  }

	  static <T> List<T> move(List<T> s, Predicate<T> test) {
	    List<T> d = Lists.newArrayList();
	    for (int i = 0; i < s.size(); i++) {
	      T v = s.get(i);
	      if (test.test(v)) {
	        d.add(v);
	        s.remove(v);
	        i--;
	      }
	    }
	    return d;
	  }

	  class Holder {

	    private static final Consumer EMPTY_CONSUMER = v -> {};
	    private static final BiConsumer EMPTY_BI_CONSUMER = (a, b) -> {};
	  }
}
