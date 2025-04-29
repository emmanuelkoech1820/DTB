
package com.dtb.services.abstracts;

import com.dtb.entities.Profile;
import com.dtb.dto.LoginRequest;
import com.dtb.dto.RegisterRequest;
import com.dtb.dto.UpdateProfileRequest;
import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

public interface ProfileService {
    Profile register(RegisterRequest request);
    String login(LoginRequest request);
    Profile getProfile(Long profileId);
    String authenticate(LoginRequest request);
}






