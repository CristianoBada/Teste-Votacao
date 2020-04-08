package com.elecao.teste.java.teste.java.Controles;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.Convercoes;

@Controller
public class CargoControle {

	@Autowired
	private EleicaoRepository eleicaoDAO;
	
	@Autowired
	private CargoRepository cargoDao;
	
	@RequestMapping("/cadastroCargo")
	public String CadastrarCargo(Model model) {
		Cargo cargo = new Cargo();
		model.addAttribute("cargo", cargo);
		List<Eleicao> eleicoes = eleicaoDAO.findAll();
		model.addAttribute("listaEleicao", eleicoes);
		return "cargo/CadastroCargo";
	}
	
	@PostMapping(value = "/cadastroCargo/novo")
	public String novaEleicao(@Valid Cargo cargo, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos!");
		} else {
			cargo.setId(null);
			cargoDao.save(cargo);
			attributes.addFlashAttribute("mensagem", "Cargo salvo com sucesso!");
		}
		
		return "redirect:/cadastroCargo";
	}
	
}
