package com.crm.utils;

import org.springframework.stereotype.Component;

import com.common.model.TokenModel;
import com.common.redis.CommonRedis;

//import com.crm.common.model.TokenModel;





/**
* Created by Leon. 2016/12/26 17:09
*/
@Component("extendRedis")
public class ExtendRedis extends CommonRedis {

    /**
     * @param token
     * @param uid
     * @param appId
     * @return
     */
    public TokenModel checkToken(String token) {
        TokenModel tokenModel = get("token"+token, TokenModel.class);
        //TokenModel tokenModel = get("token" + token, TokenModel.class);
        return tokenModel;
    }

}
