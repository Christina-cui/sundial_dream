package com.cuijing.sundial_dream.web;

import com.cuijing.sundial_dream.common.error.Errors;
import com.cuijing.sundial_dream.utils.Spring;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.MutableClassToInstanceMap;
import org.springframework.web.context.request.RequestAttributes;

import javax.annotation.Nonnull;
import java.util.Optional;

public interface Currents {

    @SuppressWarnings("unchecked")
    @Nonnull
    static <T> T require(Class<T> type) {
        T t = (T) Holder.getCurrentMap().get(type);
        if (t == null) {
            throw Errors.unauthorized().asException();
        }
        return t;
    }

    @SuppressWarnings("unchecked")
    static <T> Optional<T> resolve(Class<T> type) {
        return Optional.ofNullable((T) Holder.getCurrentMap().get(type));
    }

    @SuppressWarnings("unchecked")
    static void put(Object o) {
        put((Class<? super Object>) Spring.getClass(o), o);
    }

    static <T> void put(Class<? super T> type, T o) {
        Holder.getCurrentMap().put(type, o);
    }

    final class Holder {

        private static final String NAME = "@Current";

        @SuppressWarnings("unchecked")
        private static ClassToInstanceMap<Object> getCurrentMap() {
            RequestAttributes attributes = SpringWeb.currentRequestAttributes();
            ClassToInstanceMap<Object> map = (MutableClassToInstanceMap<Object>) attributes.getAttribute(NAME, RequestAttributes.SCOPE_REQUEST);
            if (map == null) {
                map = MutableClassToInstanceMap.create();
                attributes.setAttribute(NAME, map, RequestAttributes.SCOPE_REQUEST);
                //} else {
                //UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) map.get(Authentication.class);
                //if (Objects.nonNull(token) && Objects.isNull(token.getCredentials())) {
                //  throw Errors.unauthorized().asException();
                //}
            }
            return map;
        }
    }
}
