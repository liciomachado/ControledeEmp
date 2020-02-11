package br.com.controlador.logica;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;

@WebServlet("/filtroPendentes")
public class filtroEmpenhosPendentes extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String acao = req.getParameter("filtro");
		List<Empenho> empenhos = null;

		switch (acao) {
		case "filtroEmpresa":
			String filtro = req.getParameter("empresa");
			empenhos = new EmpenhoDao().getListaEmpenhosPendentesFiltroEmpresa(filtro);
			break;

		case "filtroValor":
			String filtroValor = req.getParameter("valor");
			empenhos = new EmpenhoDao().getListaEmpenhosPendentesFiltroValor(filtroValor);
			break;

		case "filtroDestino":
			String filtroDestino = req.getParameter("destino");
			empenhos = new EmpenhoDao().getListaEmpenhosPendentesFiltroDestino(filtroDestino);
			break;
		case "filtroPrazo":
			empenhos = new EmpenhoDao().getListaEmpenhosPendentesFiltroPrazoVencido();
			break;
		}
		req.setAttribute("empenhos", empenhos);
		req.getRequestDispatcher("/pages/empenhosPendentesFiltro.jsp").forward(req,res);
	}
}
