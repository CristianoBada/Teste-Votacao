package com.elecao.teste.java.teste.java.Controles;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PaginaInicialController {
	
	@RequestMapping("/")
	public String PaginaInicial() {
		return "PaginaInicial";
	}
}
