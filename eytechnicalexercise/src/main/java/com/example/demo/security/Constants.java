package com.example.demo.security;

public class Constants {

	// Spring Security

	public static final String LOGIN_URL = "/login";
	public static final String HEADER_AUTHORIZACION_KEY = "Authorization";
	public static final String TOKEN_BEARER_PREFIX = "Bearer ";
	
	// JWT

	public static final String ISSUER_INFO = "https://www.autentia.com/";
	public static final String SUPER_SECRET_KEY = "1234";
	public static final long TOKEN_EXPIRATION_TIME = 864_000_000; // 10 day

	// Controllers
	
	public static final String PATTERN_EMAIL = "^([_A-Za-z0-9-+]+\\.?[_A-Za-z0-9-+]+@(dominio.cl))$";
	public static final String PATTERN_PASSWORD = "^[A-Z][a-z]+[0-9][0-9]";
	public static final String VALIDATION_DOMAIN_EMAIL = "El dominio del correo no es correcto";
	public static final String VALIDATION_DUPLICATED_EMAIL = "El correo ya se encuentra registrado";
	public static final String VALIDATION_PASSWORD = "El password es invalido";
}
