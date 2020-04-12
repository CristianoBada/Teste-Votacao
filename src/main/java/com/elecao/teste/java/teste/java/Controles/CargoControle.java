package com.elecao.teste.java.teste.java.Controles;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;

@Controller
public class CargoControle {

	@Autowired
	private EleicaoRepository eleicaoDAO;
	
	@Autowired
	private CargoRepository cargoDao;
	
	@RequestMapping("/cadastroCargo")
	public String CadastrarCargo(Model model) {
		model.addAttribute("cargo",  new Cargo());
		model.addAttribute("listaEleicao", eleicaoDAO.findAll());
		return "cargo/CadastroCargo";
	}
	
	@PostMapping(value = "/cadastroCargo/novo")
	public String novaEleicao(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagemAviso", "Verifique os campos!");
		} else {
			cargoDao.save(cargo);
			attributes.addFlashAttribute("mensagem", "Cargo salvo com sucesso!");
		}
		
		return "redirect:/cadastroCargo";
	}
	
}
