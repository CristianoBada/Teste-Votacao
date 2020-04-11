package com.elecao.teste.java.teste.java.Util;

import java.security.SecureRandom;
import java.util.stream.Collectors;

public class Protocolo {
	public static String gerecaoDeCodigoAlfaNumerico() {
		return new SecureRandom().ints(0,36)
	            .mapToObj(i -> Integer.toString(i, 36))
	            .map(String::toUpperCase).distinct().limit(16).collect(Collectors.joining())
	            .replaceAll("([A-Z0-9]{4})", "$1-").substring(0,19);
	}
}
