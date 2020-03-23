package com.cuijing.sundial_dream.config;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.yikaiye.common.error.CommonErrors;
import com.yikaiye.common.security.auth.Authorities;
import com.yikaiye.common.security.auth.AuthorizationHeader;
import com.yikaiye.common.security.permission.Subjects;
import com.yikaiye.common.security.token.AuthenticationResolver;
import com.yikaiye.common.server.web.Currents;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ApiAuthenticationResolver implements AuthenticationResolver {


    @Nullable
    @Override
    public Authentication authenticate(AuthorizationHeader info, HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse) {
        return null;
    }
}
