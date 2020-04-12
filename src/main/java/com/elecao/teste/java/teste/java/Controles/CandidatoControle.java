package com.elecao.teste.java.teste.java.Controles;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Candidato;
import com.elecao.teste.java.teste.java.Repository.CandidatoRepository;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;


@Controller
public class CandidatoControle {

	@Autowired
	private CargoRepository cargoDao;

	@Autowired
	private CandidatoRepository candidatoDAO;

	@RequestMapping("/cadastroCandidato")
	public String CadastrarCandidato(Model model) {
		model.addAttribute("candidato", new Candidato());
		model.addAttribute("listaCargo", cargoDao.findAll());
		return "candidato/CadastroCandidato";
	}

	@PostMapping(value = "/cadastroCandidato/novo")
	public String novaEleicao(@Valid Candidato candidato, @RequestParam("imageFile") MultipartFile imageFile, BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagemAviso", "Verifique os campos!");
		} else {
			candidato.setId(null);
			candidato.setVotos(0);
			try {
				candidato.setImagem(imageFile.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			candidatoDAO.save(candidato);
			attributes.addFlashAttribute("mensagem", "Candidato salvo com sucesso!");
		}

		return "redirect:/cadastroCandidato";
	}
}
