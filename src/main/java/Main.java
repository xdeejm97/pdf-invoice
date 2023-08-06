import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        final PDFont helvetica = PDType1Font.HELVETICA;
        Calendar calendar = Calendar.getInstance();
        String fileName = "files/invoice_" + calendar.get(Calendar.DAY_OF_MONTH) +
                "_" + calendar.get(Calendar.MONTH) + "_" + calendar.get(Calendar.YEAR) + "_" +
                calendar.get(Calendar.HOUR_OF_DAY) + "_" + calendar.get(Calendar.MINUTE) + ".pdf";

        /**
         *Creating PDF document
         */
        PDDocument document = new PDDocument();

        /**
         * Adding page
         */
        PDPage page = new PDPage();
        document.addPage(page);

        /**
         * Writing, drawing
         */
        PDPageContentStream contentStream = new PDPageContentStream(document,
                page);
        // date of creation invoice
        plainText(contentStream, helvetica, 40, 40, 720, "INVOICE");
        plainText(contentStream, helvetica, 14, 400, 720, "Date: ");
        System.out.println("Write date of creation document in format: yyyy-MM-dd");
        String date = scanner.nextLine();
        plainText(contentStream, helvetica, 14, 440, 720, date);

        plainText(contentStream, helvetica, 14, 40, 650, "Company details: ");
        plainText(contentStream, helvetica, 14, 400, 650, "Billed to: ");
        System.out.println("Write name of company who you issue an invoice: ");
        String company = scanner.nextLine();
        System.out.println("Write adress company of who you issue an invoice: ");
        String adress = scanner.nextLine();
        System.out.println("Write NIP of who you issue an invoice: ");
        String nip = scanner.nextLine();
        plainText(contentStream, helvetica, 11, 400, 630, company);
        plainText(contentStream, helvetica, 11, 400, 610, adress);
        plainText(contentStream, helvetica, 11, 400, 590, nip);

        System.out.println("Write your name of company: ");
        String myCompany = scanner.nextLine();
        System.out.println("Write your adress company: ");
        String myAdress = scanner.nextLine();
        System.out.println("Write your NIP: ");
        String myNip = scanner.nextLine();
        plainText(contentStream, helvetica, 11, 40, 630, myCompany);
        plainText(contentStream, helvetica, 11, 40, 610, myAdress);
        plainText(contentStream, helvetica, 11, 40, 590, myNip);

        plainText(contentStream, helvetica, 14, 60, 530, "Description");
        plainText(contentStream, helvetica, 14, 460, 530, "Price");

        // description
        plainText(contentStream, helvetica, 12, 60, 500, "-----------------------------------------------------------------------------------------------------------------");
        System.out.println("Write the description: ");
        String description = scanner.nextLine();
        plainText(contentStream, helvetica, 10, 60, 480, description);
        plainText(contentStream, helvetica, 12, 60, 440, "-----------------------------------------------------------------------------------------------------------------");

        // set the price
        System.out.println("What is the price: ");
        String price = scanner.nextLine();
        plainText(contentStream, helvetica, 10, 420, 480, price + ",00 PLN ");



        plainText(contentStream, helvetica, 12, 370, 420, "Total:");
        plainText(contentStream, helvetica, 10, 420, 420, price + ",00 PLN");



        contentStream.close();
        document.save(fileName);
        document.close();
    }

    /**
     * @param - any text that we put in document it starts in left down corner regarding to tx and ty
     *          PDPageContentStream enables to put text/img to our PDF
     */
    static void plainText(PDPageContentStream contentStream, PDFont font,
                          int sizeFont, int tx, int ty,
                          String text) {
        try {
            contentStream.beginText();
            contentStream.setFont(font, sizeFont);
            contentStream.newLineAtOffset(tx, ty); // 595x842 - points
            contentStream.showText(text); // check what it means
            contentStream.endText();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
