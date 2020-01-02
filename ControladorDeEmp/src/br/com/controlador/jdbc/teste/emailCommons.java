package br.com.controlador.jdbc.teste;

import java.io.File;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

public class emailCommons {

	public static void main(String[] args) {

		long tempoInicio = System.currentTimeMillis();
		
		HtmlEmail  email = new HtmlEmail ();

		File f = new File("C:/Users/Mauricio/Desktop/800501.pdf"); 

		EmailAttachment attachment = new EmailAttachment();
		attachment.setPath(f.getPath()); // Obtem o caminho do arquivo
		attachment.setDisposition(EmailAttachment.ATTACHMENT);
		attachment.setDescription("File");
		attachment.setName(f.getName()); // Obtem o nome do arquivo

		try{
			email.setDebug(true);
			email.setHostName("smtp.gmail.com"); // o servidor SMTP para envio do e-mail
			email.setSmtpPort(465);
			email.setSSL(true);
			email.setAuthentication("licio.machado.mm@gmail.com", "19121998");
			email.addTo("licio.machado.mm@gmail.com", "John Doe"); //destinatário
			email.setFrom("licio.machado.mm@gmail.com", "Me"); // remetente
			email.setSubject("Mensagem de Teste"); // assunto do e-mail
			//MENSAGEM DO EMAIL
			StringBuffer texto = new StringBuffer(); 
		    texto.append("<h2 align='center'>Pedido : 2019NE80143 </h2>");
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
		    
			email.setMsg(texto.toString()); //conteudo do e-mail
			email.attach(attachment);
			email.send(); //envia o e-mail
			

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Tempo Total: "+((System.currentTimeMillis()-tempoInicio)/1000)+"segundos....");

	}

}
