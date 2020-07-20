package ru.morozov.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ru.morozov.models.SiteObj;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expired}")
    private int expired;

    public JWTUtil() {
    }

    public String generateToken(SiteObj siteObj) {
        Date date = Date
                .from(
                        LocalDate.now()
                                .plusDays(expired)
                                .atStartOfDay(ZoneId.systemDefault())
                                .toInstant()
                );
        return Jwts.builder()
                .setSubject(siteObj.getLogin())
                .setExpiration(date)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            LOGGER.error("invalid token");
        }
        return false;
    }

    public String getLoginFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }

}