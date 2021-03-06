package main;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.PdfReader;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class AddControlData {
    public static void main(String args[]) throws Exception {
        /*/ Example paths copy to use them
            src/test/original.pdf
            src/test/properties.json
            src/test/sample.pdf
        /*/

        // Set system input
        Scanner path = new Scanner(System.in);

        // Get original file path
        System.out.println("Original document path:");
        String source = path.next();
        path.nextLine();

        // Open JSON file
        JSONParser jsonParser = new JSONParser();
        System.out.println("JSON path:");
        String json = path.next();
        path.nextLine();
        JSONObject data = (JSONObject) jsonParser.parse(new FileReader(json));

        // Get destination file path
        System.out.println("Controlled document path:");
        String destination = path.next();
        path.nextLine();

        // Creating a PdfDocument object from source
        PdfDocument pdf = new PdfDocument(new PdfReader(source), new PdfWriter(destination));

        // Creating a Document object
        Document doc = new Document(pdf);

        // Creating a table
        float [] columnsWith = {104F, 104F, 104F, 104F, 104F};
        Table table = new Table(columnsWith);

        // Adding first row titles
        table.addCell(new Cell().add("Código").setBold());
        table.addCell(new Cell().add("Nombre").setBold());
        table.addCell(new Cell().add("Fecha creación").setBold());
        table.addCell(new Cell().add("Revisión").setBold());
        table.addCell(new Cell().add("Fecha rev.").setBold());

        // Adding first row values
        table.addCell(new Cell().add((String) data.get("code")));
        table.addCell(new Cell().add((String) data.get("name")));
        table.addCell(new Cell().add((String) data.get("cdate")));

        // Calculate revision
        table.addCell(new Cell().add(String.valueOf((Long) data.get("rev") + 1)));

        // Set revision date as today
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate today = LocalDate.now();
        table.addCell(new Cell().add(dtf.format(today)));

        // Adding second row titles
        table.addCell(new Cell().add("Creó").setBold());
        table.addCell(new Cell().add("Revisó").setBold());
        table.addCell(new Cell().add("Aprovó").setBold());
        table.addCell(new Cell().add("Tipo").setBold());
        table.addCell(new Cell().add("Siguiente rev.").setBold());

        // Adding first row values
        table.addCell(new Cell().add((String) data.get("creator")));
        table.addCell(new Cell().add((String) data.get("reviwer")));
        table.addCell(new Cell().add((String) data.get("approval")));
        table.addCell(new Cell().add((String) data.get("type")));

        // Calculate next revision date
        Long revPeriodicity = (Long) data.get("revp");
        LocalDate nextRev = LocalDate.now().plusYears(revPeriodicity);
        table.addCell(new Cell().add(dtf.format(nextRev)));

        // Adding Table to document
        doc.add(table);

        // Closing the document
        doc.close();
        System.out.println("New controlled document created...");
    }
}
