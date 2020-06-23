package utils;

import java.io.FileOutputStream;

import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GeneratePdf {
	
	public static void main(String[] args) {
		try {
			
			String file_name = "C:\\Users\\Chaimaa\\git\\Mediateck\\MediaTeck\\WebContent/facture.pdf";
			Document document = new Document();
			
			PdfWriter.getInstance(document, new FileOutputStream(file_name));
			document.open();
			Paragraph para = new Paragraph("Helo world");
			document.add(para);
			
			document.close();
			System.out.println("finished");
			
		} catch (Exception e) {
			System.err.println(e);
		}
	}

}
