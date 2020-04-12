package com.elecao.teste.java.teste.java.Controles;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Candidato;
import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Entidades.Eleitor;
import com.elecao.teste.java.teste.java.Repository.CandidatoRepository;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Repository.EleitorRepository;
import com.elecao.teste.java.teste.java.Util.ConvercoesDeDatas;
import com.elecao.teste.java.teste.java.Util.Protocolo;

@Controller
public class EleitorControle {

	private int pagina;

	private Long id_eleicao;
	private int quantidade_cargos;

	private List<Cargo> cargos;

	private String nomeCargo;

	List<Candidato> candidatos;
	List<Candidato> candidatosParaSalavar;
	private boolean existe_candidatos;

	private Eleitor eleitor;

	@Autowired
	private EleicaoRepository eleicaoDAO;
	@Autowired
	private CargoRepository cargoDAO;
	@Autowired
	private CandidatoRepository candidatoDAO;
	@Autowired
	private EleitorRepository eleitorDAO;

	@RequestMapping("/areaDoEleitor")
	public String areaEleitor(Model model) {
		eleitor = new Eleitor();
		model.addAttribute("eleitor", eleitor);

		// Lista com as elieções em andamento
		List<Eleicao> list = eleicaoDAO.findByFimGreaterThanEqualAndInicioLessThanEqual(ConvercoesDeDatas.dataAtualUS(),
				ConvercoesDeDatas.dataAtualUS());
		model.addAttribute("listaEleicao", list);

		pagina = 0;
		existe_candidatos = false;
		candidatosParaSalavar = new ArrayList<>();

		return "eleitor/AreaEleitor";
	}

	@PostMapping(value = "/areaDoEleitor/proximo")
	public String entraVoto(@Valid Eleitor eleitor, BindingResult result, RedirectAttributes attributes, Model model) {

		Eleitor eleitorCpf = eleitorDAO.findByCpfAndEleicao_id(eleitor.getCpf(), eleitor.getEleicao().getId());
		if (eleitorCpf == null) {
			id_eleicao = eleitor.getEleicao().getId();
			cargos = cargoDAO.findByEleicao_id(id_eleicao);
			this.eleitor = eleitor;

			if (cargos.size() > 0) {

				quantidade_cargos = cargos.size();
				validaSeExisteCandidato();

				if (existe_candidatos) {
					return "redirect:/areaDoEleitorProximo";
				} else {
					attributes.addFlashAttribute("mensagemAviso", "Não existe candidatos para essa eleição!");
					return "redirect:/areaDoEleitor";
				}
			} else {
				attributes.addFlashAttribute("mensagemAviso", "Não existe cargos para essa eleição!");
				return "redirect:/areaDoEleitor";
			}

		} else {
			attributes.addFlashAttribute("mensagemError", "O eleitor com CPF " + eleitorCpf.getCpf()
					+ " não pode votar na eleição " + eleitorCpf.getEleicao().getNome());
			return "redirect:/areaDoEleitor";
		}
	}

	private void validaSeExisteCandidato() {
		int quantidade_candidatos = 0;
		while (!existe_candidatos && pagina < quantidade_cargos) {
			candidatos = candidatoDAO.findByCargo_id(cargos.get(pagina).getId());
			quantidade_candidatos = candidatos.size();
			if (quantidade_candidatos > 0) {
				existe_candidatos = true;
				nomeCargo = cargos.get(pagina).getNome();
			}
			pagina++;
		}
	}

	@RequestMapping("/areaDoEleitorProximo")
	public String areaEleitorProximo(Model model) {

		model.addAttribute("nomeCargo", nomeCargo);
		model.addAttribute("listaCandidato", candidatos);

		return "eleitor/AreaEleitorProximo";
	}

	@PostMapping(value = "/areaDoEleitorProximo/proximo")
	public String proximoVoto(@Valid Candidato candidato, BindingResult result, RedirectAttributes attributes) {
		existe_candidatos = false;

		candidatosParaSalavar.add(candidato);
		validaSeExisteCandidato();
		if (existe_candidatos) {
			return "redirect:/areaDoEleitorProximo";
		} else {
			salvaCandidatos();
			salvarEleitor();
			attributes.addFlashAttribute("mensagem", "Votação concluida! Protocolo: " + eleitor.getProtocolo());
			return "redirect:/areaDoEleitor";
		}
	}
	
	private void salvarEleitor( ) {
		eleitor.setProtocolo(Protocolo.gerecaoDeCodigoAlfaNumerico());
		Eleicao eleicao = eleicaoDAO.findById(id_eleicao).get();
		eleitor.setEleicao(eleicao);
		eleitorDAO.save(eleitor);
	}

	private void salvaCandidatos() {
		for (Candidato c : candidatosParaSalavar) {
			c.setVotos(c.getVotos() + 1);
			candidatoDAO.save(c);
		}
	}
}
