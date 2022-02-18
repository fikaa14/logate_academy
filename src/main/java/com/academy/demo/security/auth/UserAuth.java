package com.academy.demo.security.auth;

import com.academy.demo.entity.User;
import com.academy.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserAuth {

    private final UserRepository userRepository;

    //Ako je uz username proslijedjena i dobra sifra onda
    //funkcija isAuthorized vraca true
    public boolean isAuthorized(String providedKey)
    {
        Base64 base64 = new Base64();
        String key = new String(base64.decode(providedKey.getBytes()));
        String[] keys = key.split(":");
        String username = keys[0];
        String password = keys[1];

        User user = userRepository.findByUsename(username);

        if(user!=null && user.getPassword() == password)
        {
            return true;
        }

        return false;
    }
}
