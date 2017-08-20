package com.common.model;

import java.io.Serializable;


public class TokenModel implements Serializable {

	private static final long serialVersionUID = -2263623295431202123L;
    private String token;

    public TokenModel() {

    }

    public TokenModel(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
    
    public String toString(){
    	return "token "+this.getToken();
    }
}
