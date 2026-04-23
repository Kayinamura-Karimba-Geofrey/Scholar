package com.example.Scholar.ServiceImpl;

import com.example.Scholar.DTO.AuthResponseDTO;
import com.example.Scholar.DTO.LoginDTO;
import com.example.Scholar.DTO.RegisterDTO;
import com.example.Scholar.Model.RefreshToken;
import com.example.Scholar.Model.Role;
import com.example.Scholar.Model.User;
import com.example.Scholar.Repository.RefreshTokenRepository;
import com.example.Scholar.Repository.UserRepository;
import com.example.Scholar.Service.AuthService;
import com.example.Scholar.Service.RefreshTokenService;
import com.example.Scholar.security.JwtService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
     private final UserRepository repo;
     private final BCryptPasswordEncoder encoder;
     private final JwtService jwtService;
     private final RefreshTokenService refreshTokenService;
     private final RefreshTokenRepository refreshTokenRepo;

     public AuthServiceImpl(UserRepository repo, 
                            BCryptPasswordEncoder encoder, 
                            JwtService jwtService,
                            RefreshTokenService refreshTokenService,
                            RefreshTokenRepository refreshTokenRepo){
         this.encoder = encoder;
         this.repo = repo;
         this.jwtService = jwtService;
         this.refreshTokenService = refreshTokenService;
         this.refreshTokenRepo = refreshTokenRepo;
     }
     @Override
    public String register(RegisterDTO dto){
         User user = new User();
         user.setUsername(dto.username());
         user.setPassword(encoder.encode(dto.password()));
         user.setRole(Role.USER);
         repo.save(user);

         return "user registered successfully";
     }
    @Override
    public AuthResponseDTO login(LoginDTO dto) {

        User user = repo.findByUsername(dto.username())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!encoder.matches(dto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }

        String accessToken = jwtService.generateToken(user.getUsername());
        String refreshToken = refreshTokenService.createRefreshToken(user.getId());

        return new AuthResponseDTO(accessToken, refreshToken);
    }
    @Override
    public void logout(String refreshToken) {
        RefreshToken token = refreshTokenRepo.findByToken(refreshToken)
                .orElseThrow(() -> new RuntimeException("Refresh token not found"));

        refreshTokenRepo.delete(token);
    }


}
