package com.elecao.teste.java.teste.java.Controles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elecao.teste.java.teste.java.Entidades.Candidato;
import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.CandidatoRepository;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.ConvercoesDeDatas;
import com.elecao.teste.java.teste.java.report.GeracaoDeRelatorios;

@Controller
public class RelarorioControle {
	@Autowired
	private EleicaoRepository eleicaoDAO;

	@Autowired
	private CargoRepository cargoDAO;

	@Autowired
	private CandidatoRepository candidatoDAO;

	@RequestMapping("/relatorios")
	public String cadastrarEleicao() {
		return "relatorio/Relatorios";
	}

	@GetMapping(value = "/relatorioFinal")
	public ModelAndView relatorioElecoesFinalizadas(RedirectAttributes attributes) {
		List<Eleicao> eleicoes = eleicaoDAO.findByFimLessThanEqual(ConvercoesDeDatas.dataAtualUS());
		if (eleicoes != null) {

			List<Cargo> cargos = cargoDAO.findAll();
			List<Candidato> candidatos = (List<Candidato>) candidatoDAO.findAll();

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("listaEleicao", eleicoes);
			model.put("listaCargo", cargos);
			model.put("listaCandidato", candidatos);
			String titulo = "Eleições Finalizadas";
			model.put("titulo", titulo);
			String nomeArquivo = "relatorioFinal";
			model.put("nomeArquivo", nomeArquivo);

			return new ModelAndView(new GeracaoDeRelatorios(), model);
		} else {
			attributes.addFlashAttribute("mensagem", "Não existe dados de eleições!");
			return new ModelAndView();
		}

	}

	@GetMapping(value = "/relatorioParcial")
	public ModelAndView relatorioElecoesEmAndamento(RedirectAttributes attributes) {
		String dataS = ConvercoesDeDatas.dataAtualUS();

		List<Eleicao> eleicoes = eleicaoDAO.findByFimGreaterThanEqualAndInicioLessThanEqual(dataS, dataS);
		if (eleicoes != null) {
			List<Cargo> cargos = cargoDAO.findAll();

			List<Candidato> candidatos = (List<Candidato>) candidatoDAO.findAll();

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("listaEleicao", eleicoes);
			model.put("listaCargo", cargos);
			model.put("listaCandidato", candidatos);
			String titulo = "Eleições em andamento";
			model.put("titulo", titulo);
			String nomeArquivo = "relatorioParcial";
			model.put("nomeArquivo", nomeArquivo);

			return new ModelAndView(new GeracaoDeRelatorios(), model);
		} else {
			attributes.addFlashAttribute("mensagemError", "Não existe dados de eleições!");
			return new ModelAndView();
		}
	}
}
