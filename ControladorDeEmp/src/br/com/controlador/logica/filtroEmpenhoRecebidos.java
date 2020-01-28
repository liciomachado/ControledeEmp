package br.com.controlador.logica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.NotaFiscal;

@WebServlet("/filtroRecebidos")
public class filtroEmpenhoRecebidos extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String acao = req.getParameter("filtro");
		List<NotaFiscal> notas = null;

		switch (acao) {
		case "filtroEmpresa":
			String filtro = req.getParameter("empresa");
			notas = new NotaFiscalDao().getNotaRecebidosFiltroEmpresa(filtro);
			break;

		case "filtroDestino":
			String filtroDestino = req.getParameter("destino");
			notas = new NotaFiscalDao().getNotaRecebidosFiltroDestino(filtroDestino);
			break;
		}
		req.setAttribute("notas", notas);
		req.getRequestDispatcher("/pages/empenhosRecebidosFiltro.jsp").forward(req,res);
	}
}
