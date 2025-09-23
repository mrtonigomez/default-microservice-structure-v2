package org.oril.client;

import org.oril.entities.UserVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service", url = "localhost:9002/users")
public interface UserClient {

    @PostMapping
    ResponseEntity<UserVO> save(@RequestBody UserVO userVO);

    @GetMapping("/secured")
    ResponseEntity<String> securedEndpoint();
}
