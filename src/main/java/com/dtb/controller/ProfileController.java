package com.dtb.controller;

import com.dtb.dto.LoginRequest;
import com.dtb.dto.RegisterRequest;
import com.dtb.dto.UpdateProfileRequest;
import com.dtb.entities.Profile;
import com.dtb.implementation.ProfileServiceImpl;
import com.dtb.services.abstracts.ProfileService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }


   @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    //@PostMapping(value = "/register")
    public ResponseEntity<Profile> register(@RequestBody RegisterRequest request) {
        System.out.println(request.getEmail());
        Profile profile = profileService.register(request);
        return ResponseEntity.ok(profile);
    }
    @PostMapping(
            path = "/register2",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Profile> registerUser(@Valid @RequestBody RegisterRequest request) {
        Profile response = profileService.register(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest request) {
        String token = profileService.login(request);
        return ResponseEntity.ok(token);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Profile> getProfile(@PathVariable Long id) {
        Profile profile = profileService.getProfile(id);
        return ResponseEntity.ok(profile);
    }


}

