package cn.tjhyyt.user.common.utli;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * JWT token 工具类,提供JWT生成,校验,工作
 *
 * @Date 2019-05-25
 * @Description: TODO
 */
@Data
@Slf4j
public class JwtUtil {
    private static String secret = "12345";

    /**
     *
     * 生成JWT token
     * @param userId
     * @return
     */
    public static String generateToken(String userId) {
        Date nowDate = new Date();
        Date expireDate = new Date(nowDate.getTime() + 60*60*1000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId)
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact();
 
    }
 
    /**
     *
     * 解析JWT token
     * @param token
     * @return
     */
    public static Claims parseToken(String token) {
        try {
            return Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            log.info("解析token出错");
            return null;
        }
    }
 
    /**
     *
     * 校验token是否过期
     * @param expiprationTime
     * @return
     */
    public static boolean isTokenExpired(Date expiprationTime){
        return expiprationTime.before(new Date());
    }
}
