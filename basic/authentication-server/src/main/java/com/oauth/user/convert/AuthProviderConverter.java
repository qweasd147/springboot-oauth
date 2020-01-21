package com.oauth.user.convert;

import com.oauth.user.model.AuthProvider;

public class AuthProviderConverter extends AbstractEnumConverter<AuthProvider> {

    public AuthProviderConverter() {
        this.enumCodeType = AuthProvider.class;
    }
}
