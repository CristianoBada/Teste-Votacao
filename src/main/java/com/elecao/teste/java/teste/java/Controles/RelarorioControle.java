package com.elecao.teste.java.teste.java.Controles;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.EleicaoRepository;
import com.elecao.teste.java.teste.java.Util.Convercoes;
import com.elecao.teste.java.teste.java.report.GeracaoDeRelatorios;

@Controller
public class RelarorioControle {
	@Autowired
	private EleicaoRepository eleicaoDAO;

	@RequestMapping("/relatorios")
	public String cadastrarEleicao() {
		return "relatorio/Relatorios";
	}

	@GetMapping(value = "/relatorioFinal")
	public ModelAndView relatorioElecoesFinalizadas() {
		String dataS = new Convercoes().dataAtualUS();
		System.out.println(dataS);
		return new ModelAndView(new GeracaoDeRelatorios(), "listaEleicao",
				eleicaoDAO.findByFimLessThanEqual(dataS));
	}

	@GetMapping(value = "/relatorioParcial")
	public ModelAndView relatorioElecoesEmAndamento() {
		String dataS = new Convercoes().dataAtualUS();
		System.out.println(dataS);
		List<Eleicao> eleicoes = eleicaoDAO.findByFimGreaterThanEqualAndInicioLessThanEqual(dataS, dataS);
		return new ModelAndView(new GeracaoDeRelatorios(), "listaEleicao", eleicoes);
	}
}
