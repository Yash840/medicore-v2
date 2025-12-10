package org.cross.medicore.security.internals.persistence;

import org.cross.medicore.security.api.dto.DeletedUserInfo;
import org.cross.medicore.security.internals.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);

    @Modifying
    @Query(value = "DELETE FROM users WHERE id = ?1 RETURNING id, username;", nativeQuery = true)
    DeletedUserInfo deleteAndReturnUserInfo(long userId);

}
