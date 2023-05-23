package com.vn.cines_start.utils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.vn.cines_start.Model.CustomUserDetail;

import java.util.ArrayList;
import java.util.List;

public class SecurityUtils {

    public static CustomUserDetail getPrincipal() {
        return (CustomUserDetail) (SecurityContextHolder.getContext()).getAuthentication().getPrincipal();
    }
}