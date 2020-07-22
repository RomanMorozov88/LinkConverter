package ru.morozov.services.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import ru.morozov.models.SiteObj;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static org.springframework.util.StringUtils.hasText;

/**
 * Создание\извлечение JWT.
 */
public class JWTUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JWTUtil.class);
    private static final String AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";

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

    public String getTokenFromRequest(HttpServletRequest request) {
        String result = null;
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith(TOKEN_PREFIX)) {
            result = bearer.substring(TOKEN_PREFIX.length());
        }
        return result;
    }

}