package br.com.controlador.logica;

import java.awt.FileDialog;
import java.awt.Frame;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import br.com.controlador.jdbc.dao.EmpenhoDao;
import br.com.controlador.jdbc.dao.NotaFiscalDao;
import br.com.controlador.jdbc.modelo.Empenho;
import br.com.controlador.jdbc.modelo.NotaFiscal;


public class GeraPDF {

	public static void main(String[] args) {

		List<Integer> lista = new ArrayList<Integer>();
		lista.add(31);
		lista.add(33);
		lista.add(35);
		lista.add(40);

		writeUsingIText(lista);
	}

	public static void writeUsingIText(List<Integer> lista) {

		Document document = new Document();
		
		/*
		JFileChooser chooser = null;
		File pdf = null;
		try {
			pdf = File.createTempFile("protocolo", ".pdf");
		} catch (IOException e1) {
			e1.printStackTrace();
		}            
		chooser = new JFileChooser();
		chooser.setCurrentDirectory(pdf);
		chooser.setSelectedFile(pdf);
		chooser.setAcceptAllFileFilterUsed(false);
		chooser.setMultiSelectionEnabled(false);
		String caminho = "";
		

		int retorno = chooser.showSaveDialog(null);
		if (retorno==JFileChooser.APPROVE_OPTION){
			caminho = chooser.getSelectedFile().getAbsolutePath();
		}*/
		
		FileDialog fc; 
        fc = new FileDialog(new Frame(),"Escolha onde salvar", FileDialog.SAVE);
        fc.setDirectory("*.pdf");
        fc.setVisible(true);
        String path=fc.getDirectory (  )  + fc.getFile (  ) ; 
        System.out.println(path);

        
		if(path != null) {
			try {

				PdfWriter.getInstance(document, new FileOutputStream(path));

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

				//close
				document.close();

				System.out.println("Concluido");

			} catch (FileNotFoundException | DocumentException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
