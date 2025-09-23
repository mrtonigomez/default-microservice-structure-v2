package org.oril.services;

import lombok.AllArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.oril.client.UserClient;
import org.oril.entities.AuthRequest;
import org.oril.entities.AuthResponse;
import org.oril.entities.UserVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    private final JwtUtil jwtUtil;
    private final UserClient userClient;

    public AuthResponse register(AuthRequest request) {
        //do validation if user exists in DB
        request.setPassword(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()));
        UserVO user = new UserVO();
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        ResponseEntity<UserVO> registeredUser = userClient.save(user);

        String accessToken = jwtUtil.generate(registeredUser.getBody().getId(), registeredUser.getBody().getRole(), "ACCESS");
        String refreshToken = jwtUtil.generate(registeredUser.getBody().getId(), registeredUser.getBody().getRole(), "REFRESH");

        return new AuthResponse(accessToken, refreshToken);
    }

}
