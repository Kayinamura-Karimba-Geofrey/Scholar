package com.example.Scholar.Controller;

import com.example.Scholar.DTO.AuthResponseDTO;
import com.example.Scholar.DTO.LoginDTO;
import com.example.Scholar.DTO.RegisterDTO;
import com.example.Scholar.Service.AuthService;
import com.example.Scholar.Service.RefreshTokenService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService service;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService service, RefreshTokenService refreshTokenService){
        this.service = service;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterDTO dto){
        return service.register(dto);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@Valid @RequestBody LoginDTO dto){
        return service.login(dto);
    }

    @PostMapping("/refresh")
    public String refresh(@RequestParam String refreshToken) {
        return refreshTokenService.verifyAndGenerateAccessToken(refreshToken);
    }

    @PostMapping("/logout")
    public String logout(@RequestParam String refreshToken) {
        service.logout(refreshToken);
        return "Logged out successfully";
    }
}
