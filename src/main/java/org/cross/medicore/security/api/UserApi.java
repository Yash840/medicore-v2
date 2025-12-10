package org.cross.medicore.security.api;

import org.cross.medicore.security.api.dto.DeletedUserInfo;
import org.cross.medicore.security.api.dto.UserDetailsDto;

/**
 * API for creating and validating application users.
 *
 * <p>This interface provides methods to create user accounts for patients and providers
 * and to validate credentials either by username or by user id.</p>
 */
public interface UserApi {

    /**
     * Create a new application user with patient role.
     *
     * @param username the desired username for the patient account; must be unique within the
     *                 system and not null or empty
     * @param rawPassword the user's plaintext password; it will be hashed/stored by the
     *                    implementation (the caller is responsible for supplying a non-null value)
     * @return a {@link UserDetailsDto} containing identifying information about the newly created
     *         patient user (for example id, username and roles)
     */
    UserDetailsDto createPatientUser(String username, String rawPassword);

    /**
     * Create a new application user with provider role.
     *
     * @param username the desired username for the provider account; must be unique within the
     *                 system and not null or empty
     * @param rawPassword the provider's plaintext password; it will be hashed/stored by the
     *                    implementation (the caller is responsible for supplying a non-null value)
     * @return a {@link UserDetailsDto} containing identifying information about the newly created
     *         provider user (for example id, username and roles)
     */
    UserDetailsDto createProviderUser(String username, String rawPassword);

    UserDetailsDto getUserByUsername(String username);

    UserDetailsDto getUserByUserId(long userId);

    /**
     * Validate a user's credentials by username.
     *
     * @param username the username to validate; must not be null
     * @param rawPassword the plaintext password to check against the stored credential
     * @return true if the supplied credentials are valid for the given username, false otherwise
     */
    boolean validateUser(String username, String rawPassword);

    /**
     * Validate a user's credentials by user id.
     *
     * @param userId the internal id of the user to validate
     * @param rawPassword the plaintext password to check against the stored credential
     * @return true if the supplied password is valid for the user with the given id, false otherwise
     */
    boolean validateUser(long userId, String rawPassword);

    DeletedUserInfo deleteUser(long userId);
}
