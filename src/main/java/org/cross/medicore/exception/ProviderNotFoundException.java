package org.cross.medicore.exception;

public class ProviderNotFoundException extends RuntimeException {
    public ProviderNotFoundException(long providerId) {
        super("Provider not found with ID: " + providerId);
    }
}
