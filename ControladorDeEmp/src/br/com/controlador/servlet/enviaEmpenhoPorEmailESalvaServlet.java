package br.com.controlador.servlet;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.EmpresaDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.Empresa;
import br.com.controlador.jdbc.modelo.Usuario;

@WebServlet("/enviaEmail")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2,   // 2MB
				maxFileSize = 1024 * 1024 * 10,         // 10MB
				maxRequestSize = 1024 * 1024 * 50)		// 50MB
public class enviaEmpenhoPorEmailESalvaServlet extends HttpServlet {
	
    protected void doPost(HttpServletRequest request,
                        HttpServletResponse response)
                        throws IOException, ServletException {
    	
    	String numEmpenho = request.getParameter("numEmpenho");
    	String destino = request.getParameter("destinoEmpenho");
    	double valorTotal = Double.parseDouble(request.getParameter("valor"));
    	int idEmpresa = Integer.parseInt(request.getParameter("inputEmpenho"));
    	Part filePart = request.getPart("imagem");
    	InputStream file = null;
    	if(filePart != null) {        	file = filePart.getInputStream();    	}
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

		Empresa empresa2 = new Empresa();
		EmpresaDao daoEmpresa =  new EmpresaDao();
		empresa2 = daoEmpresa.buscaPorId(idEmpresa);
		
		//COMEÇANDO TRABALHAR O EMAIL
		final String username = "licio.machado.mm@gmail.com";
		final String password = "19121998";
		String fromEmail = "licio.machado.mm@gmail.com";
		String toEmail = empresa2.getEmail();
		
		Properties properties = new Properties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.socketFactory.port", "465");
        properties.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.port", "465");
		
		Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username,password);
			}
		});
		//Start our mail message
		MimeMessage msg = new MimeMessage(session);
		
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
	    
	    
	    try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("Empenho: "+numEmpenho + " - "+ empresa2.getNome());
			
			Multipart emailContent = new MimeMultipart();
			
			//Text body part
			MimeBodyPart textBodyPart = new MimeBodyPart();
			textBodyPart.setText(texto.toString(), "UTF-8", "html");
			
			//Attachment body part.
			MimeBodyPart pdfAttachment = new MimeBodyPart();
			pdfAttachment.attachFile("C:/Users/Mauricio/Desktop/800501.pdf");
			
			//Attach body parts
			emailContent.addBodyPart(textBodyPart);
			emailContent.addBodyPart(pdfAttachment);
			
			//Attach multipart to message
			//msg.setContent(texto.toString(),"text/html; charset=utf-8");
			msg.setContent(emailContent);
			
			Transport.send(msg);
			System.out.println("Sent message");
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	    response.sendRedirect("pages/adcSucessoEmpenho.jsp");
    }
}
