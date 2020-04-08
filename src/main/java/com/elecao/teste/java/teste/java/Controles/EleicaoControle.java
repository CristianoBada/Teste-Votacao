package com.elecao.teste.java.teste.java.Controles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.Convercoes;

@Controller
public class EleicaoControle {
	@Autowired
	private EleicaoRepository eleicaoDAO;
	
	@RequestMapping("/cadastroEleicao")
	public String cadastrarEleicao(Model model) {
		Eleicao eleicao = new Eleicao();
		model.addAttribute("eleicao", eleicao);
		return "eleicao/CadastroEleicao";
	}
	
	@PostMapping(value = "/cadastroEleicao/novo")
	public String novaEleicao(@Valid Eleicao eleicao, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
		} else {
			eleicao.setFim(new Convercoes().convertDateBRtoDataUS(eleicao.getFim()));
			eleicao.setInicio(new Convercoes().convertDateBRtoDataUS(eleicao.getInicio()));
			eleicao.setId(null);
			eleicaoDAO.save(eleicao);
			attributes.addFlashAttribute("mensagem", "Eleicao salva com sucesso!");
		}
		
		return "redirect:/cadastroEleicao";
	}
	
}