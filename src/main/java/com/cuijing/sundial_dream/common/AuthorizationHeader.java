package com.cuijing.sundial_dream.common;



public class AuthorizationHeader {
    private AuthorizationType type;
    private String token;

    public boolean isUserToken() {
        return this.type == AuthorizationType.TOKEN;
    }

    public AuthorizationHeader() {
    }

    public AuthorizationType getType() {
        return this.type;
    }

    public String getToken() {
        return this.token;
    }

    public AuthorizationHeader setType(final AuthorizationType type) {
        this.type = type;
        return this;
    }

    public AuthorizationHeader setToken(final String token) {
        this.token = token;
        return this;
    }

    public boolean equals(final Object o) {
        if (o == this) {
            return true;
        } else if (!(o instanceof AuthorizationHeader)) {
            return false;
        } else {
            AuthorizationHeader other = (AuthorizationHeader) o;
            if (!other.canEqual(this)) {
                return false;
            } else {
                Object this$type = this.getType();
                Object other$type = other.getType();
                if (this$type == null) {
                    if (other$type != null) {
                        return false;
                    }
                } else if (!this$type.equals(other$type)) {
                    return false;
                }

                Object this$token = this.getToken();
                Object other$token = other.getToken();
                if (this$token == null) {
                    if (other$token != null) {
                        return false;
                    }
                } else if (!this$token.equals(other$token)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(final Object other) {
        return other instanceof AuthorizationHeader;
    }

    public int hashCode() {
        boolean PRIME = true;
        int result = 1;
        Object $type = this.getType();
        result = result * 59 + ($type == null ? 43 : $type.hashCode());
        Object $token = this.getToken();
        result = result * 59 + ($token == null ? 43 : $token.hashCode());
        return result;
    }

    public String toString() {
        return "AuthorizationHeader(type=" + this.getType() + ", token=" + this.getToken() + ")";
    }
}
