package com.elecao.teste.java.teste.java.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Repository.CargoRepository;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;



public class GeracaoDeRelatorios extends AbstractPdfView{
	
	@Autowired
	private CargoRepository cargoDAO;
	
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"relatorioEleicoes.pdf\"");

		@SuppressWarnings("unchecked")
		List<Eleicao> eleicoes = (List<Eleicao>) model.get("listaEleicao");
		
		for (Eleicao e: eleicoes) {
			document.add(new Paragraph(e.getNome() + "   Data de incio: " + e.getInicio() + "    Data de termino:" + e.getFim()));
			
			
		}
		
	}
}
