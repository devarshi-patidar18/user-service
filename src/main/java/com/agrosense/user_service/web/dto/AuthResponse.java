package com.agrosense.user_service.web.dto;

public record AuthResponse(String token, Object refreshToken) {}