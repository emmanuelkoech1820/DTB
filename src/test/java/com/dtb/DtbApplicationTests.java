package com.dtb;


import com.dtb.dto.LoginRequest;
import com.dtb.dto.RegisterRequest;
import com.dtb.entities.Profile;
import com.dtb.implementation.ProfileServiceImpl;
import com.dtb.repository.ProfileRepository;
import com.dtb.services.abstracts.ProfileService;
import com.dtb.util.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.hamcrest.Matchers.any;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


class ProfileServiceImplTest {

    private ProfileRepository profileRepository;
    private JwtUtil jwtUtil;
    private ProfileService profileService;
	private PasswordEncoder passwwordEncoder;

    @BeforeEach
    void setUp() {
        profileRepository = mock(ProfileRepository.class);
        jwtUtil = mock(JwtUtil.class);
		passwwordEncoder = mock(PasswordEncoder.class);
        profileService = new ProfileServiceImpl(profileRepository, passwwordEncoder, jwtUtil );
    }

    @Test
    void register_ShouldRegisterProfile_WhenNewUser() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());


        Profile result = profileService.register(request);

        assertNotNull(result);
        assertEquals("testuser", result.getFirstName());
    }

    @Test
    void register_ShouldThrowException_WhenEmailAlreadyExists() {
        RegisterRequest request = new RegisterRequest();
        request.setEmail("test@example.com");

        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Profile()));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            profileService.register(request);
        });

        assertEquals("Profile already exists", exception.getMessage());
    }

    @Test
    void login_ShouldReturnToken_WhenCredentialsAreCorrect() {
        LoginRequest request = new LoginRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");

        Profile profile = new Profile();
        profile.setId(1L);
        profile.setEmail("test@example.com");
        profile.setPassword("password"); // Assume plaintext for now

        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(profile));
        when(jwtUtil.generateToken(profile.getEmail()));

        String token = profileService.login(request);

        assertNotNull(token);
        assertEquals("mocked-jwt-token", token);
    }
}

