package br.com.controlador.servlet;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.NotaFiscal;


@WebServlet("/protocolaItens")
public class ProtocolaItensServlet extends HttpServlet {

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response)
					throws IOException, ServletException {

		List<Integer> lista = new ArrayList<Integer>();
		//-----------------PEGANDO DATA DE AGORA
		Date d = new Date();
		System.out.println(d);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		try {
			d = df.parse(d.toString());
		} catch (ParseException e1) {
			System.out.println("Erro na conversao de data");
		}
		Calendar data = Calendar.getInstance();
		data.setTime(d); 
		
		for(String name: request.getParameterMap().keySet()) {
			for(String value: request.getParameterValues(name)) {
				//System.out.println(name+ ": "+ value);
				//String[] checked = request.getParameterValues("opcoes[]");
				lista.add(Integer.parseInt(value));
			}
		}
		for (int valores : lista) {
			EmpenhoDao dao = new EmpenhoDao();
			NotaFiscalDao NfDao = new NotaFiscalDao();
			dao.alteraStatus(valores);
			NfDao.adicionaDataProtocolado(valores, data);
			System.out.println(valores);
		}

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		Document document = new Document();
		try {
			PdfWriter.getInstance(document, stream);
		} catch (DocumentException e2) {
			e2.printStackTrace();
		}

		try {
			//open
			document.open();

			List<NotaFiscal> nfs = new ArrayList<NotaFiscal>();
			for (int valores : lista) {
				NotaFiscal nf = new NotaFiscal();
				NotaFiscalDao dao = new NotaFiscalDao();
				nf = dao.getListaProtocolo(valores);
				nfs.add(nf);
			}

			Paragraph p = new Paragraph();
			p.add("Protocolo do dia:");
			p.setAlignment(Element.ALIGN_CENTER);
			document.add(p);

			for (NotaFiscal nf : nfs) {
				document.add(new Paragraph("                                                                "));
				document.add(new Paragraph("Empenho: "+nf.getEmpenho().getNumeroEmpenho()));
				document.add(new Paragraph("Empresa: "+nf.getEmpresa().getNome()));
				document.add(new Paragraph("Numero da NF: "+nf.getNumNota()));
				document.add(new Paragraph("Destino: "+nf.getEmpenho().getDestino()));
				document.add(new Paragraph("________________________________________________________________RECEBIDO"));
			}
			document.close();
			
			byte[] DocumentEmArray = stream.toByteArray();
			
			System.out.println(DocumentEmArray);

			response.setContentType("application/pdf"); // tipo do conteúdo na resposta
			response.setContentLength(DocumentEmArray.length); // opcional. ajuda na barra de progresso
			response.setHeader("Content-Disposition", "attachment; filename="+"protocolo "+data.getTimeInMillis() +".pdf");
			ServletOutputStream outputStream = response.getOutputStream();
			outputStream.write(DocumentEmArray, 0, DocumentEmArray.length);
			outputStream.flush();
			outputStream.close();

			System.out.println("Concluido");

		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		stream.flush();
		stream.close();
		response.sendRedirect("pages/index.jsp");
	}
}