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
import br.com.controlador.jdbc.dao.EmpresaDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.Observacoes;
import br.com.controlador.jdbc.modelo.Usuario;
import br.com.controlador.logica.EnviarEmail;

@WebServlet("/enviaEmail")
@MultipartConfig(maxFileSize = 16177215)
public class enviaEmpenhoPorEmailESalvaServlet extends HttpServlet {
	
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
		try {date = new SimpleDateFormat("dd/MM/yyyy").parse(dStr);} catch (ParseException e) {e.printStackTrace();}
        data = Calendar.getInstance();
		data.setTime(date);
		empenho.setDataEmpenho(data);
		empenho.setValorTotal(valorTotal);
		EmpenhoDao dao = new EmpenhoDao();
		//dao.adiciona(empenho);
		int idResultado = dao.empenhoComFile2(file, empenho);
		HttpSession sessao = request.getSession(true);
		sessao.setAttribute("LastResult", idResultado);

		//request.getRequestDispatcher("pages/adcSucessoEmpenho.jsp").forward(request,response);
		
		Empresa empresa2 = new Empresa();
		EmpresaDao daoEmpresa =  new EmpresaDao();
		empresa2 = daoEmpresa.buscaPorId(idEmpresa);
		
		
		EnviarEmail enviar = new EnviarEmail();
	    enviar.setEmailDestinatario(empresa2.getEmail());
	    enviar.setAssunto("Empenho : "+numEmpenho+" - "+ empresa2.getNome());
	    //uso StringBuffer para otimizar a concatenação de string
	    StringBuffer texto = new StringBuffer(); 
	    texto.append("<h2 align='center'>Pedido : "+numEmpenho+" - "+ empresa2.getNome()+"</h2>");
	    texto.append("Solicito-vos fornecer o(s) item(s) do empenho em anexo:<br/><br/><br/>");
	    texto.append("LOCAL DE ENTREGA: <br/>");
	    texto.append("1º B Fv <br/>");
	    texto.append("Rua: 2º Batalhão Rodoviário s/n  <br/>");
	    texto.append("Bairro: Conta Dinheiro <br/>");
	    texto.append("CEP: 88.520-900 - Lages/SC <br/><br/>");
	    
	    
	    texto.append("Nota Fiscal: <br/>");
	    texto.append("Dados adicionais(Informações complementares) <br/>");
	    texto.append("Informar se é optante do simples nacional: <br/>");
	    texto.append("se não for, informar alíquota e datalhando(cofins, CSLL/IR e PIS/PASEP) <br/><br/>");
	    
	    texto.append("Prazo para entrega: conforme edital. Solicito ainda informar a possível data de entrega. <br/>");
	    
	    texto.append("<h2 align='center' text-color='red' >Solicito-vos acusar o recebimento </h2><br/>");
	    texto.append("Atenciosamente, <br/><br/>");
	    
	    texto.append(s.getAttribute("usuario"));
	    
	    enviar.setMsg(texto.toString());
	    
	    enviar.enviarGmail();
	 
		response.sendRedirect("pages/adcSucessoEmpenho.jsp");
    }
}
