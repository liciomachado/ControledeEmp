package br.com.controlador.jdbc.teste;

import java.io.IOException;
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

public class SendEmail {

	public static void main(String[] args) {
		//authentication info
		final String username = "licio.machado.mm@gmail.com";
		final String password = "19121998";
		String fromEmail = "licio.machado.mm@gmail.com";
		String toEmail = "licio.machado.mm@gmail.com";
		
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
		
		StringBuffer texto = new StringBuffer(); 
	    texto.append("<h2 align='center'>Pedido : "+2019+" - "+ 2019+"</h2>");
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
	    
	    texto.append("Mauricio");
	    
	    
		try {
			msg.setFrom(new InternetAddress(fromEmail));
			msg.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
			msg.setSubject("2019NE....");
			
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

	}

}