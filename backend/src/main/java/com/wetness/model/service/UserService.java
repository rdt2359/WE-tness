package com.wetness.model.service;

import com.wetness.db.entity.LoggedContinue;
import com.wetness.db.entity.User;
import com.wetness.model.dto.request.JoinUserDto;
import com.wetness.model.dto.request.PasswordDto;
import com.wetness.model.dto.request.UpdateUserDto;
import com.wetness.model.dto.response.LoginDto;
import org.springframework.security.core.Authentication;

import java.io.IOException;
import java.util.Map;

public interface UserService {

    boolean checkEmailDuplicate(String email);

    boolean checkNicknameDuplicate(String nickname);

    boolean registerUser(JoinUserDto user);

    boolean registerUserBySocial(User user);

    boolean updateUser(Long id, UpdateUserDto updateUserDto);

    void updateUser(Long id, User user);

    boolean updateUserPassword(long id, PasswordDto passwordDto);

    User findByNickname(String nickname);

    User findByEmail(String email);

    User findById(Long id);

    User loginUser(String nickname, String password);

    void saveRefreshToken(String nickname, String refreshToken);

    String getRefreshToken(String nickname);

    String getSocialToken(int social, String code) throws IOException;

    Map<String, Object> getUserInfo(String accessToken) throws IOException;

    void logoutUser(String nickname);

    void deleteUser(String nickname);

    void setLoginData(Long userId);

    LoggedContinue getLoginData(Long userId);

    public LoginDto loginUser(User user);

    public Authentication getAuthentication(User user);

    public LoginDto getCurrentUserLoginDto(String headerAuth, String nickname);
}