package org.example;


import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.swing.JTable;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PdfReportGenerator {

    public static void generate(JTable table) throws Exception {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("raport_magazynowy.pdf"));
        document.open();

        String date = new SimpleDateFormat("dd.MM.yyyy HH:mm").format(new Date());

        Font headerFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph header = new Paragraph("Raport Magazynowy\nData: " + date, headerFont);
        header.setAlignment(Element.ALIGN_CENTER);
        header.setSpacingAfter(20);
        document.add(header);

        PdfPTable pdfTable = new PdfPTable(table.getColumnCount());
        pdfTable.setWidthPercentage(100);

        Font cellFont = new Font(Font.FontFamily.HELVETICA, 10);

        for (int i = 0; i < table.getColumnCount(); i++) {
            PdfPCell cell = new PdfPCell(new Phrase(table.getColumnName(i), cellFont));
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell.setBackgroundColor(BaseColor.DARK_GRAY);
            pdfTable.addCell(cell);
        }

        for (int row = 0; row < table.getRowCount(); row++) {
            for (int col = 0; col < table.getColumnCount(); col++) {
                PdfPCell cell = new PdfPCell(
                        new Phrase(table.getValueAt(row, col).toString(), cellFont)
                );
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfTable.addCell(cell);
            }
        }

        document.add(pdfTable);
        document.close();
    }
}
