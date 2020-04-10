package com.elecao.teste.java.teste.java.report;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.elecao.teste.java.teste.java.Entidades.Candidato;
import com.elecao.teste.java.teste.java.Entidades.Cargo;
import com.elecao.teste.java.teste.java.Entidades.Eleicao;
import com.elecao.teste.java.teste.java.Util.Convercoes;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

public class GeracaoDeRelatorios extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {

		@SuppressWarnings("unchecked")
		String arquivo = (String) model.get("nomeArquivo");
		response.setHeader("Content-Disposition", "attachment; filename=\"" + arquivo + ".pdf\"");

		try {
			@SuppressWarnings("unchecked")
			List<Eleicao> eleicoes = (List<Eleicao>) model.get("listaEleicao");

			@SuppressWarnings("unchecked")
			String titulo = (String) model.get("titulo");
			document.add(new Paragraph(titulo));
			document.add(new Paragraph(""));

			@SuppressWarnings("unchecked")
			List<Cargo> cargos = (List<Cargo>) model.get("listaCargo");

			@SuppressWarnings("unchecked")
			List<Candidato> candidatos = (List<Candidato>) model.get("listaCandidato");

			Convercoes convercoes = new Convercoes();

			for (Eleicao e : eleicoes) {
				document.add(new Paragraph(
						e.getNome() + "   Data de incio: " + convercoes.convertDateUStoDataBR(e.getInicio())
								+ "    Data de termino:" + convercoes.convertDateUStoDataBR(e.getFim())));

				for (Cargo c : cargos) {
					if (c.getEleicao().getId() == e.getId()) {
						document.add(new Paragraph(c.getNome()));
						document.add(new Paragraph("Candidatos:"));
						for (Candidato ca : candidatos) {
							if (ca.getCargo().getId() == c.getId()) {
								document.add(new Paragraph(ca.getNome()));
							}
						}
					}

				}

				document.add(
						new Paragraph("-----------------------------------------------------------------------------"));
			}
		} catch (Exception e) {
		}

	}
}
