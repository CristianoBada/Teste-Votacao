package com.elecao.teste.java.teste.java.Controles;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Candidato;
import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Entidades.Eleitor;
import com.elecao.teste.java.teste.java.Repository.CandidatoRepository;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.Convercoes;

@Controller
public class EleitorControle {

	private int pagina;

	private Long id_eleicao;
	private int quantidade_cargos;

	private List<Cargo> cargos;

	@Autowired
	private EleicaoRepository eleicaoDAO;
	@Autowired
	private CargoRepository cargoDAO;
	@Autowired
	private CandidatoRepository candidatoDAO;

	@RequestMapping("/areaDoEleitor")
	public String areaEleitor(Model model) {
		model.addAttribute("eleitor", new Eleitor());

		// Lista com as elieções em andamento
		List<Eleicao> list = eleicaoDAO.findByFimGreaterThanEqualAndInicioLessThanEqual(Convercoes.dataAtualUS(),
				Convercoes.dataAtualUS());
		model.addAttribute("listaEleicao", list);

		pagina = 0;

		return "eleitor/AreaEleitor";
	}

	@PostMapping(value = "/areaDoEleitor/proximo")
	public String entraVoto(@Valid Eleitor eleitor, BindingResult result, RedirectAttributes attributes, Model model) {

		id_eleicao = eleitor.getEleicao().getId();
		cargos = cargoDAO.findByEleicao_id(id_eleicao);

		quantidade_cargos = cargos.size();

		return "redirect:/areaDoEleitorProximo";
	}

	@RequestMapping("/areaDoEleitorProximo")
	public String areaEleitorProximo(Model model) {

		model.addAttribute("pagina", pagina);

		List<Candidato> candidatos = candidatoDAO.findByCargo_id(cargos.get(pagina).getId());

		model.addAttribute("listaCandidato", candidatos);

		pagina++;

		return "eleitor/AreaEleitorProximo";
	}

	@PostMapping(value = "/areaDoEleitorProximo/proximo")
	public String proximoVoto(@Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {

		if (pagina < quantidade_cargos) {
			return "redirect:/areaDoEleitorProximo";
		} else {
			attributes.addFlashAttribute("mensagem", "Votação concluida!");
			return "redirect:/areaDoEleitor";
		}
	}
}
