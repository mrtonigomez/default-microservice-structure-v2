package org.oril.services;

import org.oril.entities.AuthRequest;
import org.oril.entities.AuthResponse;

public interface AuthService {

    AuthResponse register(AuthRequest request);

}
