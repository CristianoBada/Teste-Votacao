package com.elecao.teste.java.teste.java.Controles;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elecao.teste.java.teste.java.Entidades.Eleitor;

@Controller
public class EleitorControle {
	@RequestMapping("/areaDoEleitor")
	public String cadastrarEleicao(Model model) {
		model.addAttribute("eleitor", new Eleitor());
		return "eleitor/AreaEleitor";
	}
}
