package com.village.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.village.entity.User;
import com.village.exception.TokenVerification;

import java.util.Calendar;
import java.util.Date;

public class TokenUtil {

    private static final int TIME_OUT = 3;

    private static final String KEY = "@!2381";


    public static String createToken(User user) {

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.DATE,TIME_OUT);
        Date expiresDate = nowTime.getTime();

        return JWT.create().withAudience(user.getId())   //签发对象
                .withIssuedAt(new Date())    //发行时间
                .withExpiresAt(expiresDate)  //有效时间
                .withClaim("userId", user.getId())
                .withClaim("username", user.getUsername())//载荷
                .withClaim("nick", user.getNick())
                .sign(Algorithm.HMAC256(KEY));   //加密
    }


    public static void verifyToken(String token) throws TokenVerification {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(KEY)).build();
            verifier.verify(token);
        } catch (Exception e) {
            //效验失败
            throw new TokenVerification();
        }
    }

    /**
     * 获取签发对象
     */
    public static String getAudience(String token) throws TokenVerification {
        String audience = null;
        try {
            audience = JWT.decode(token).getAudience().get(0);
        } catch (JWTDecodeException j) {
            throw new TokenVerification();
        }
        return audience;
    }


    /**
     * 通过载荷名字获取载荷的值
     */
    public static Claim getClaimByName(String token, String name){
        return JWT.decode(token).getClaim(name);
    }
}
