package com.cuijing.sundial_dream.common;


import com.cuijing.sundial_dream.common.error.Errors;
import io.jsonwebtoken.Jws;
import org.springframework.security.core.Authentication;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface AuthenticationResolver {
    @Nullable
    Authentication authenticate(AuthorizationHeader info, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse);

    @Nullable
    default Authentication authenticate(Claims claims) {
        return null;
    }

    default Jws<Claims> parse(String claimsJws) {
        throw Errors.badRequest().asException();
    }
}

