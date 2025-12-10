package org.cross.medicore.security.internals.service;

import lombok.RequiredArgsConstructor;
import org.cross.medicore.exception.UserAlreadyExistException;
import org.cross.medicore.exception.UserDoesNotExistException;
import org.cross.medicore.security.api.UserSecurityApi;
import org.cross.medicore.security.api.dto.DeletedUserInfo;
import org.cross.medicore.security.api.dto.UserDetailsDto;
import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Role;
import org.cross.medicore.security.internals.entities.User;
import org.cross.medicore.security.internals.mapper.UserMapper;
import org.cross.medicore.security.internals.persistence.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService, UserSecurityApi {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Transactional
    private User createUser(String username, String rawPassword, RoleName roleName){
        ensureUserDoesNotExist(username);

        String hashedPassword = passwordEncoder.encode(rawPassword);
        Role role = roleService.getRole(roleName);

        User user = new User(username, hashedPassword, role);

        return userRepository.save(user);
    }

    @Transactional
    public UserDetailsDto createAdminUser(){
        User user = createUser("admin001", "Admin@123", RoleName.ROLE_ADMIN);

        return UserMapper.toUserDetailsDto(user);
    }

    @Override
    @Transactional
    public UserDetailsDto createPatientUser(String username, String rawPassword) {
       User createdUser = createUser(username, rawPassword, RoleName.ROLE_PATIENT);

       return UserMapper.toUserDetailsDto(createdUser);
    }

    @Override
    @Transactional
    public UserDetailsDto createProviderUser(String username, String rawPassword) {
        User createdUser = createUser(username, rawPassword, RoleName.ROLE_PROVIDER);

        return UserMapper.toUserDetailsDto(createdUser);
    }

    @Override
    @Transactional
    public DeletedUserInfo deleteUser(long userId) {
        return userRepository.deleteAndReturnUserInfo(userId);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailsDto getUserByUserId(long userId) {
        User user = getUser(userId);

        return UserMapper.toUserDetailsDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetailsDto getUserByUsername(String username) {
        User user = getUser(username);

        return UserMapper.toUserDetailsDto(user);
    }

    private boolean isValidPassword(String hash, String raw){
        return passwordEncoder.matches(raw, hash);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateUser(long userId, String rawPassword) {
        String storedHash = userRepository.findById(userId)
                .map(User::getHashedPassword)
                .orElseThrow(() -> new UserDoesNotExistException(userId));

        return isValidPassword(storedHash, rawPassword);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean validateUser(String username, String rawPassword) {
        String storedHash = userRepository.findByUsername(username)
                .map(User::getHashedPassword)
                .orElseThrow(() -> new UserDoesNotExistException(username));

        return isValidPassword(storedHash, rawPassword);
    }

    private void ensureUserDoesNotExist(String username){
        if(userRepository.existsByUsername(username))
            throw new UserAlreadyExistException(username);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserDoesNotExistException(username));
    }

    @Override
    @Transactional(readOnly = true)
    public User getUser(long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException(userId));
    }
}
