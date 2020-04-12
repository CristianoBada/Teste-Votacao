package com.elecao.teste.java.teste.java.Controles;

import java.io.IOException;
import java.util.List;

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
import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.CandidatoRepository;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.ConvercoesDeDatas;

@Controller
public class CandidatoControle {

	@Autowired
	private CargoRepository cargoDao;

	@Autowired
	private CandidatoRepository candidatoDAO;

	@Autowired
	private EleicaoRepository eleicaoDAO;

	private Eleicao eleicao;

	private List<Cargo> cargos;

	@RequestMapping("/cadastroCandidato")
	public String CadastrarCandidatoProximo(Model model) {
		model.addAttribute("listaEleicao", eleicaoDAO.findByInicioAfter(ConvercoesDeDatas.dataAtualUS()));
		return "candidato/CadastroCandidatoEleicao";
	}

	@RequestMapping("/cadastroCandidatoProximo")
	public String CadastrarCandidato(Model model) {
		model.addAttribute("nomeEleicao", eleicao.getNome());
		model.addAttribute("candidato", new Candidato());
		model.addAttribute("listaCargo", cargos);
		return "candidato/CadastroCandidato";
	}

	@PostMapping(value = "/cadastroCandidatoEleicao/proximo")
	public String EleicaoProxima(@Valid Eleicao eleicao, BindingResult result, RedirectAttributes attributes) {
		cargos = cargoDao.findByEleicao_id(eleicao.getId());
		if (cargos.size() > 0) {
			this.eleicao = eleicao;
			return "redirect:/cadastroCandidatoProximo";
		} else {
			attributes.addFlashAttribute("mensagemAviso", "Não existe cargos ne eleição: " + eleicao.getNome());
			 return "redirect:/cadastroCandidato";
		}
	}

	@PostMapping(value = "/cadastroCandidato/novo")
	public String novaEleicao(@Valid Candidato candidato, @RequestParam("imageFile") MultipartFile imageFile,
			BindingResult result, RedirectAttributes attributes) {
		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagemAviso", "Verifique os campos!");
		} else {
			candidato.setVotos(0);
			try {
				if (imageFile.getBytes().length > 0) {
					candidato.setImagem(imageFile.getBytes());
				} else {
					attributes.addFlashAttribute("mensagemAviso", "Selecione uma imagem!");
					return "redirect:/cadastroCandidato";
				}
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
