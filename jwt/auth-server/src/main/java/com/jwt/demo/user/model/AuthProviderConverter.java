package com.jwt.demo.user.model;

import com.jwt.demo.common.converter.AbstractEnumConverter;

public class AuthProviderConverter extends AbstractEnumConverter<AuthProvider> {

    public AuthProviderConverter() {
        this.enumCodeType = AuthProvider.class;
    }
}
