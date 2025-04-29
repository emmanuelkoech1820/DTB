//package com.dtb.tests;
//
//import com.dtb.dto.LoginRequest;
//import com.dtb.dto.RegisterRequest;
//import com.dtb.entities.Profile;
//import com.dtb.repository.ProfileRepository;
//import com.dtb.implementation.ProfileServiceImpl;
//import com.dtb.services.abstracts.ProfileService;
//import com.dtb.util.JwtUtil;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//
//import java.util.Optional;
//
//import static jdk.jfr.internal.jfc.model.Constraint.any;
//import static org.junit.Assert.*;
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//
//class ProfileServiceImplTest {
//
//    private ProfileRepository profileRepository;
//    private JwtUtil jwtUtil;
//    private ProfileService profileService;
//
//    @BeforeEach
//    void setUp() {
//        profileRepository = mock(ProfileRepository.class);
//        jwtUtil = mock(JwtUtil.class);
//        profileService = new ProfileServiceImpl(profileRepository, jwtUtil);
//    }
//
//    @Test
//    void register_ShouldRegisterProfile_WhenNewUser() {
//        RegisterRequest request = new RegisterRequest();
//        request.setEmail("test@example.com");
//        request.setPassword("password");
//
//        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.empty());
//        when(profileRepository.save(any(Profile.class))).thenAnswer(i -> i.getArguments()[0]);
//
//        Profile result = profileService.register(request);
//
//        assertNotNull(result);
//        assertEquals("testuser", result.getUsername());
//    }
//
//    @Test
//    void register_ShouldThrowException_WhenEmailAlreadyExists() {
//        RegisterRequest request = new RegisterRequest();
//        request.setEmail("test@example.com");
//
//        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(new Profile()));
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            profileService.register(request);
//        });
//
//        assertEquals("Profile already exists", exception.getMessage());
//    }
//
//    @Test
//    void login_ShouldReturnToken_WhenCredentialsAreCorrect() {
//        LoginRequest request = new LoginRequest();
//        request.setEmail("test@example.com");
//        request.setPassword("password");
//
//        Profile profile = new Profile();
//        profile.setId(1L);
//        profile.setEmail("test@example.com");
//        profile.setPassword("password"); // Assume plaintext for now
//
//        when(profileRepository.findByEmail(request.getEmail())).thenReturn(Optional.of(profile));
//        when(jwtUtil.generateToken(profile.getId())).thenReturn("mocked-jwt-token");
//
//        String token = profileService.login(request);
//
//        assertNotNull(token);
//        assertEquals("mocked-jwt-token", token);
//    }
//}
//
