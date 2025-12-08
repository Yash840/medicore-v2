package org.cross.medicore.exception;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String username) {
        super("user with username: " + username + " does not exist");
    }
    public UserDoesNotExistException(long userId) {
        super("user with id: " + userId + " does not exist");
    }
}
