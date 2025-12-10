package org.cross.medicore.security.internals.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.RequiredArgsConstructor;
import org.cross.medicore.exception.UserAlreadyExistException;
import org.cross.medicore.exception.UserDoesNotExistException;
import org.cross.medicore.security.api.dto.DeletedUserInfo;
import org.cross.medicore.security.api.dto.UserDetailsDto;
import org.cross.medicore.security.internals.constants.RoleName;
import org.cross.medicore.security.internals.entities.Role;
import org.cross.medicore.security.internals.entities.User;
import org.cross.medicore.security.internals.mapper.UserMapper;
import org.cross.medicore.security.internals.persistence.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final RoleService roleService;

    private User createUser(String username, String rawPassword, RoleName roleName){
        ensureUserDoesNotExist(username);

        String hashedPassword = BCrypt.withDefaults().hashToString(12, rawPassword.toCharArray());
        Role role = roleService.getRole(roleName);

        User user = new User(username, hashedPassword, role);

        return userRepository.save(user);
    }

    @Override
    public UserDetailsDto createPatientUser(String username, String rawPassword) {
       User createdUser = createUser(username, rawPassword, RoleName.ROLE_PATIENT);

       return UserMapper.toUserDetailsDto(createdUser);
    }

    @Override
    public UserDetailsDto createProviderUser(String username, String rawPassword) {
        User createdUser = createUser(username, rawPassword, RoleName.ROLE_PROVIDER);

        return UserMapper.toUserDetailsDto(createdUser);
    }

    @Override
    public DeletedUserInfo deleteUser(long userId) {
        return userRepository.deleteAndReturnUserInfo(userId);
    }

    @Override
    public UserDetailsDto getUserByUserId(long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserDoesNotExistException(userId));

        return UserMapper.toUserDetailsDto(user);
    }

    @Override
    public UserDetailsDto getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserDoesNotExistException(username));

        return UserMapper.toUserDetailsDto(user);
    }

    @Override
    public boolean validateUser(long userId, String rawPassword) {
        String storedHash = userRepository.findById(userId)
                .map(User::getHashedPassword)
                .orElseThrow(() -> new UserDoesNotExistException(userId));

        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), storedHash);

        return result.verified;
    }

    @Override
    public boolean validateUser(String username, String rawPassword) {
        String storedHash = userRepository.findByUsername(username)
                .map(User::getHashedPassword)
                .orElseThrow(() -> new UserDoesNotExistException(username));

        BCrypt.Result result = BCrypt.verifyer().verify(rawPassword.toCharArray(), storedHash);

        return result.verified;
    }

    private void ensureUserDoesNotExist(String username){
        if(userRepository.existsByUsername(username))
            throw new UserAlreadyExistException(username);
    }
}
