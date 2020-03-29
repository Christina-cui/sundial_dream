package com.cuijing.sundial_dream.config;


import com.cuijing.sundial_dream.common.AuthenticationResolver;
import com.cuijing.sundial_dream.common.AuthorizationHeader;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Component
@RequiredArgsConstructor
public class ApiAuthenticationResolver implements AuthenticationResolver {


    @Nullable
    @Override
    public Authentication authenticate(AuthorizationHeader info, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }
}
