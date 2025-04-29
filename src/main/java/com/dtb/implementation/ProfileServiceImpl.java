package com.dtb.implementation;

import com.dtb.constants.Role;
import com.dtb.dto.LoginRequest;
import com.dtb.dto.RegisterRequest;
import com.dtb.entities.Profile;
import com.dtb.repository.ProfileRepository;
import com.dtb.services.abstracts.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.dtb.util.JwtUtil;

import java.util.Optional;

@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepository profileRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public ProfileServiceImpl(ProfileRepository profileRepository,
                              PasswordEncoder passwordEncoder,
                              JwtUtil jwtUtil) {
        this.profileRepository = profileRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Profile register(RegisterRequest request) {

        if (profileRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already in use.");

        }

        // Check if phone number already exists
        if (profileRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already in use.");
        }

        // Proceed to register
        Profile profile = new Profile();
        profile.setFirstName(request.getFirstName());
        profile.setLastName(request.getLastName());
        profile.setEmail(request.getEmail());
        profile.setPhoneNumber(request.getPhoneNumber());
        profile.setPassword(passwordEncoder.encode(request.getPassword()));
        profile.setRole(Role.USER); // default role

        return profileRepository.save(profile);
    }

    @Override
    public String login(LoginRequest request) {
        Optional<Profile> profileOpt = profileRepository.findByEmail(request.getEmail());
        if (profileOpt.isEmpty()) {
            throw new RuntimeException("Invalid credentials");
        }
        Profile profile = profileOpt.get();

        if (!passwordEncoder.matches(request.getPassword(), profile.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        return jwtUtil.generateToken(profile.getEmail());
    }

    @Override
    public String authenticate(LoginRequest request) {
        // Same logic as login() for now — can be improved later to refresh token
        return login(request);
    }

    @Override
    public Profile getProfile(Long profileId) {
        return profileRepository.findById(profileId)
                .orElseThrow(() -> new RuntimeException("Profile not found"));
    }
}
