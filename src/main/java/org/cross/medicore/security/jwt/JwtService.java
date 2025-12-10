package org.cross.medicore.security.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    @Value("${app.auth.jwt.secret}")
    private String JWT_SECRET;

    @Value("${app.auth.jwt.expiration}")
    private long JWT_EXPIRATION_MILLIS;

    private SecretKey getSignInKey(){
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(JWT_SECRET));
    }

    public String generateToken(String username){
        return Jwts.builder()
                .header()
                .add("X-MEDICORE-AUTH", "v2.0.0")
                .and()
                .expiration(new Date(System.currentTimeMillis() + JWT_EXPIRATION_MILLIS))
                .issuedAt(new Date())
                .issuer("MEDICORE-AUTH")
                .subject(username)
                .signWith(getSignInKey())
                .compact();
    }

    public Claims extractAllClaims(String token){
        return Jwts.parser()
                .verifyWith(getSignInKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        Claims claims = extractAllClaims(token);

        return claimsResolver.apply(claims);
    }

    public String extractUsername(String token){
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token){
        Date tokenExpiryDate = extractExpiration(token);
        Date today = new Date();

        return tokenExpiryDate.before(today);
    }

    public boolean isValidToken(String token, UserDetails userDetails){
        String username = userDetails.getUsername();

        return
                extractUsername(token).equals(username) && !isTokenExpired(token);
    }
}
