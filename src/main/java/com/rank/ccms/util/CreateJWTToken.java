package com.rank.ccms.util;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateJWTToken {

    public static String createTokenWithPolicy(String user_id) {
        String token = "";
        try {
            String privateKeyContent = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCxp8NdW8WtOX1nRFmvxEnGmB2Eb8agaMAXrIssw/qXWkCecAOIt8nhAuJpyp7DUNWgxDcVp8Fm1x73behp4tg3nEUZTzIqNY347wYA6xUe7bNVOTU81T5cLnamDYbFHQ4gn8Rxamqsmwrux0TD2PZaUQrcVeg7kmhYop6wyRIu96o187cBnJ+0Aqxn8qnJuv3o8JxPOodd1g0wiWb6I6bo773CSKaN7BUXDXrXd/HD4F3xg7HUGmVx/fjaq3dDs1Mu1X9AH/8V2WWv5vGoNLsaHBna8i7BdqbCFr/IpW17zFwu45Fl8pJTV162OsDJEhpNBx+Je7aYbrgjjeFUgCZrAgMBAAECggEAZ/q+9+d83ZRmd4ijzaJfY+Moc5lQchadR73vf3X9N5TVeWYqS1z2xcIAbtbu8XZgcsYlT4FuSxjhwQQNIdxpnLLUJqoZvGa3/9k2dFcjd4S8j8b6B+7vnth7DVmhZcFJwYKBYbhdU0Uzsunir5ZPKjGA39FQKX3ZoPcdVm95Ow8z7ho6gFBQq/6K7IBlDfP7mbSNZhSLc1nh6tfNIyEePLKmT5HcXCAcOv4QGTNrN1di/6sSZW6+e6VQDynFO52g4tYSFwHm365yqGdevv5V7TWo9uOlubYLKa/KqwUovWIT3OoaCk2KL/jRVDUE0KTawckrWIhuKw3AZb0a9ofY8QKBgQDiwjJBLqaeb+bQFLd4N+LWTk8/0r9t954RcZMIg9Gct5Iy7RbWDop5XdZSOA2KuUALdwp71jZhldvRhkKXANzH4xprEI8mvyl4kTc0DNFZ/9j2hOdQ10zlSmFJnKq3gBltQC3jWk80RuMiW40dNxY49eiyAI41YUmXlRzzXoEoRwKBgQDIkI86Wvv6bgo4zlSVmMdCWvkXAYv5YNgLF1qxMybnTXT2EZDuqfwYStpRCCWQfr/OxG8UUy0mIoFMts/QCLqQQhZRswDBQ0iqFLO5WLiuB54yS6PsajKxaW77bqBwvm0um9v7aEn5FpCofgjdgKWrgPp82NEOPWEJTsZaA85GvQKBgCJJBHowiIbKGpb5muf1YznN61OWFNWWq/KtPnL6I1ZTtyo2CM7isfM5ye7yhLGl4tYY8J/auqw0kWDzsNvOCJO8dcEcZtiP7MBLDJK1IW4/LdnQLH1foEPG5EDSDEnkd3O6UXXfsLwRv8PxHbv+GUERhVZsmRPfGtZteEwfzN1/AoGAH94AM+hy8vZ6/02hWOeeeWeU/lfLksMVcKW0TkVvX/8Jn3NpENLHcGCsUYW22j+ZYFQLxjH1EYMYAkVlvYs1gWhOvYpwi0SSjhud9swnA9fIBfvIvu6cwV2cOaz5JxDKrkYrbMXEQySXtX3ZEMC5Egf7RMz6YoXUHjN7BqBmLQ0CgYB8xRkopl/0faKQboM9vbk1jegPKzG4xVZCyZ7pXo7/ZltchCpsPPGPIXPgWsFc+nzmu9hZsCSB/fWjH8l95VATNywWiXohhggxTvVcC4iL5dHsjZvToxc9JnbDiPVFzrckst8mRAS7N2AQEulkFnnSNj2McKzY/TDGyiEInJSoiQ==";

            KeyFactory kf = KeyFactory.getInstance("RSA");
            String jsonStr = "{ \"devices\":{\"user_id\": \"" + user_id + "\"}}";
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(jsonStr);
            PKCS8EncodedKeySpec keySpecPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKeyContent));
            PrivateKey privateKey = kf.generatePrivate(keySpecPKCS8);
            token = Jwts.builder().setIssuedAt(new java.util.Date())
                    .setSubject("apurba.maity@gmail.com")
                    .setAudience("https://cobrowse.io")
                    .setExpiration(new java.util.Date(2021, 1, 1))
                    .setIssuer("Cm__Mzd-HqBdhg")
                    .claim("displayName", "Apurba Maity")
                    .claim("policy", jsonNode)
                    // RS256 with privateKey
                    .signWith(SignatureAlgorithm.RS256, privateKey).compact();
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | IOException ex) {
            Logger.getLogger(CreateJWTToken.class.getName()).log(Level.SEVERE, null, ex);
        }
        return token;
    }

}
