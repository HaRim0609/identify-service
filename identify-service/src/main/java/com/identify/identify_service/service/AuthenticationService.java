package com.identify.identify_service.service;

import java.text.ParseException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.StringJoiner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.identify.identify_service.dto.request.AuthenticationRequest;
import com.identify.identify_service.dto.request.IntrospecRequest;
import com.identify.identify_service.dto.response.AuthenticationResponse;
import com.identify.identify_service.dto.response.IntrospecResponse;
import com.identify.identify_service.entity.User;
import com.identify.identify_service.exception.AppException;
import com.identify.identify_service.exception.ErrorCode;
import com.identify.identify_service.repository.UserRepository;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.JWSObject;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationService {
    UserRepository userRepository;

    @NonFinal
    @Value("${spring.jwt.signerKey}")
    protected String SIGNER_KEY;

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        var user = userRepository.findByUsername(request.getUsername())
                    .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));
        boolean authenticated =  passwordEncoder.matches(request.getPassword(), user.getPassword());
        // System.out.println(request.getPassword());
        // System.out.println(user.getPassword());
        // System.out.println(passwordEncoder.matches(request.getPassword(), user.getPassword()));
        if (!authenticated) throw new AppException(ErrorCode.UNAUTHENTICATED);

        var token = generateToken(request.getUsername());
        return AuthenticationResponse.builder()
        .token(token)
        .build();

        
    }

    private String generateToken(User user){
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder().subject(user.getUsername()).issuer("identify-service").issueTime(new Date()).expirationTime(new Date(
            Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
        )).claim("scope", "Custom").build();

        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
        JWSObject jwsObject = new JWSObject(header, payload);

        // Ki token
        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            // TODO Auto-generated catch block
            log.error("Cannot create token",e);
            throw new RuntimeException(e);
        }
    }

    private String builderScope(User user){
        StringJoiner stringJoiner = new StringJoiner("");
            
    }

    public IntrospecResponse introspec(IntrospecRequest request) throws JOSEException, ParseException{
        var token = request.getToken();

        JWSVerifier verifier = new MACVerifier(SIGNER_KEY.getBytes());

        SignedJWT signedJWT = SignedJWT.parse(token);
        Date expityTime = signedJWT.getJWTClaimsSet().getExpirationTime();
        var verified = signedJWT.verify(verifier);

        return IntrospecResponse.builder().valid(verified && expityTime.after(new Date())).build();
    }
}
