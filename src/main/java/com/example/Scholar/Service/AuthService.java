package com.example.Scholar.Service;

import com.example.Scholar.DTO.LoginDTO;
import com.example.Scholar.DTO.RegisterDTO;
import com.example.Scholar.DTO.AuthResponseDTO;

public interface AuthService {
    String  register(RegisterDTO dto);
    AuthResponseDTO login(LoginDTO dto);
    void logout(String refreshToken);

}
