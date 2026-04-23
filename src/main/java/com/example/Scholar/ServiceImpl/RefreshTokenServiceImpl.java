package com.example.Scholar.ServiceImpl;

import com.example.Scholar.Model.RefreshToken;
import com.example.Scholar.Model.User;
import com.example.Scholar.Repository.RefreshTokenRepository;
import com.example.Scholar.Repository.UserRepository;
import com.example.Scholar.Service.RefreshTokenService;
import com.example.Scholar.security.JwtService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final RefreshTokenRepository repo;
    private final UserRepository userRepo;
    private JwtService jwtService;

    public  RefreshTokenServiceImpl(RefreshTokenRepository repo, UserRepository userRepo, JwtService jwtService){
        this.repo= repo;
        this.userRepo= userRepo;
        this.jwtService= jwtService;
    }
    @Override
    public  String createRefreshToken(Long userId) {
        User user = userRepo.findById(userId)
                .orElseThrow(()-> new RuntimeException("user not found "));

        RefreshToken token = new RefreshToken();
        token.setUser(user);
        token.setToken(UUID.randomUUID().toString());
        token.setExpiryDate(Instant.now().plusSeconds(7*24*60*60));

        repo.save(token);
        return  token.getToken();

        }
        @Override
    public String verifyAndGenerateAccessToken(String refreshToken){
        RefreshToken token = repo.findByToken(refreshToken)
                .orElseThrow(()->new RuntimeException("invalid refresh token"));

        if (token.getExpiryDate().isBefore(Instant.now())){
            repo.delete(token);
            throw new RuntimeException("Refresh token expired");

        }
        return jwtService.generateToken(token.getUser().getUsername());


    }

}
