package com.example.digitalsample.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Component
public class JwtTokenProvider {

    private String secretKey = "digital";

    private long tokenValidTime = 30 * 60 * 1000L;     // 토큰 유효시간 30분

    private final UserDetailsService userDetailsService;

    /**
     * @PostConstruct : 객체를 초기화한다
     *
     * 객체롤 초기화 한 이후에 secretKey를 Base64로 인코딩한다
     */
    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encodeToString(secretKey.getBytes());
    }

    /**
     * @param userPk : 사용자테이블의 PrimaryKey
     * @param roles  : 사용자에게 부여할 권한 목록
     * @return token을 발행해서 리턴한다
     * 토큰을 생성해주는 메소드이다
     */
    public String createToken(String userPk, List<String> roles) {
        Claims claims = Jwts.claims().setSubject(userPk); // JWT payload 에 저장되는 정보단위
        claims.put("roles", roles); // 정보는 key / value 쌍으로 저장
        Date now = new Date();
        return Jwts.builder()
                .setClaims(claims) // 정보 저장
                .setIssuedAt(now) // 토큰 발행 시간 정보
                .setExpiration(new Date(now.getTime() + tokenValidTime)) // 토큰 유효시각 설정
                .signWith(SignatureAlgorithm.HS256, secretKey)  // 암호화 알고리즘과, secret 값
                .compact();
    }

    /**
     * @param token : 토큰값
     * @return 인증 정보
     * 토큰에서 인증 정보를 조회하기 위한 메소드
     * */
    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(this.getUserPk(token)); //사용자의 이름으로 유저를 찾는다

        //만약 찾는데 성공할 시 토큰을 만들어서 넘긴다
        return new UsernamePasswordAuthenticationToken(
                userDetails,
                "",
                userDetails.getAuthorities()
        );
    }

    /**
     * @param token : 토큰값
     * @return 회원 정보
     * 토큰에서 회원 정보를 추출하기 위한 메소드이다
     * */
    public String getUserPk(String token) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    /**
     * @param token : 토큰값
     * @return 올바른 토큰인지 아닌지 리턴해준다
     * 토큰의 유효성과 만료일자를 확인하는 메소드이다
     * */
    public boolean validateToken(String token) {
        try {
            Jws<Claims> claims =
                    Jwts.parser()
                    .setSigningKey(secretKey)   //시크릿 키를 설정한다
                    .parseClaimsJws(token);     //토큰의 유효성을 확인한다

            //토큰의 만료일자를 확인한다
            return !claims.getBody()
                    .getExpiration()
                    .before(new Date());

        } catch (Exception e) {
            //만약 위의 토큰 유효성을 검사하는 부분에서 예외가 발생(유효하지 않은 토큰임이 밝혀짐)하면 false를 리턴한다
            return false;
        }
    }


    /**
     * @param request : 요청 헤더에서 token 값을 가져오기 위한 매개변수
     * @return 헤더에서 가져온 token을 리턴해준다
     *
     * 헤더에서 토큰을 추출해서 리턴해주는 메소드이다
     * */
    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("X-AUTH-TOKEN");
    }
}