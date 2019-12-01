package br.com.controlador.servlet;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.Observacoes;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/adicionaEmpenho")
@MultipartConfig(maxFileSize = 16177215)
public class adicionaEmpenhoServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                        throws IOException, ServletException {
    	
    	String numEmpenho = request.getParameter("numEmpenho");
    	String destino = request.getParameter("destinoEmpenho");
    	double valorTotal = Double.parseDouble(request.getParameter("valor"));
    	int idEmpresa = Integer.parseInt(request.getParameter("inputEmp"));
    	
    	Part filePart = request.getPart("imagem");
    	
    	InputStream file = null;
    	
    	if(filePart != null) {
        	file = filePart.getInputStream();
    	}
    	
    	Empresa empresa = new Empresa();
    	empresa.setIdEmpresa(idEmpresa);
    	
    	Empenho empenho = new Empenho();
    	empenho.setNumeroEmpenho(numEmpenho);
    	empenho.setEmpresa(empresa);
    	empenho.setDestino(destino);
    	
    	Usuario usuario = new Usuario(); 
		HttpSession s = request.getSession();
		usuario.setIdUsuario(Integer.parseInt(s.getAttribute("userId").toString()));
		empenho.setUsuario(usuario);
		
    	Calendar data = null;
		Date d = new Date();
		String dStr = java.text.DateFormat.getDateInstance(DateFormat.MEDIUM).format(d);
		Date date = null;
		try {
			date = new SimpleDateFormat("dd/MM/yyyy").parse(dStr);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        data = Calendar.getInstance();
		data.setTime(date);
		empenho.setDataEmpenho(data);
		empenho.setValorTotal(valorTotal);
		
		EmpenhoDao dao = new EmpenhoDao();
		//dao.adiciona(empenho);
		int idResultado = dao.empenhoComFile2(file, empenho);
		HttpSession sessao = request.getSession(true);
		sessao.setAttribute("LastResult", idResultado);
		
		response.sendRedirect("pages/adcSucessoEmpenho.jsp");
		//request.getRequestDispatcher("pages/adcSucessoEmpenho.jsp").forward(request,response);
    }
}
