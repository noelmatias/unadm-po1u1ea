package main;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfReader;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

public class AddControlData {
    public static void main(String args[]) throws Exception {
        // Set original and destination file paths
        String source = "D:/OneDrive/Academy/UnADM/IDS/2101-B2/PO1/U1/EA/original.pdf";
        String destination = "D:/OneDrive/Academy/UnADM/IDS/2101-B2/PO1/U1/EA/sample.pdf";

        // Creating a PdfDocument object from source
        PdfDocument pdf = new PdfDocument(new PdfReader(source), new PdfWriter(destination));

        // Creating a Document object
        Document doc = new Document(pdf);

        // Creating a table
        float [] pointColumnWidths = {104F, 104F, 104F, 104F, 104F};
        Table table = new Table(pointColumnWidths);

        // Adding first row titles
        table.addCell(new Cell().add("Código").setBold());
        table.addCell(new Cell().add("Nombre").setBold());
        table.addCell(new Cell().add("Fecha creación").setBold());
        table.addCell(new Cell().add("Revisión").setBold());
        table.addCell(new Cell().add("Fecha rev.").setBold());

        // Adding first row values
        table.addCell(new Cell().add("CODE"));
        table.addCell(new Cell().add("Nombre"));
        table.addCell(new Cell().add("dd/mm/aaaa"));
        table.addCell(new Cell().add("#"));
        table.addCell(new Cell().add("dd/mm/aaaa"));

        // Adding second row titles
        table.addCell(new Cell().add("Creó").setBold());
        table.addCell(new Cell().add("Revisó").setBold());
        table.addCell(new Cell().add("Aprovó").setBold());
        table.addCell(new Cell().add("Siguiente rev.").setBold());
        table.addCell(new Cell().add("Tipo").setBold());

        // Adding first row values
        table.addCell(new Cell().add("Nombre Apellido"));
        table.addCell(new Cell().add("Nombre Revisó"));
        table.addCell(new Cell().add("Nombre Aprovó"));
        table.addCell(new Cell().add("dd/mm/aaaa"));
        table.addCell(new Cell().add("Interno"));

        // Adding Table to document
        doc.add(table);

        // Closing the document
        doc.close();
        System.out.println("New controlled document created..");
    }
}
