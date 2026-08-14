package com.leavehub.service;

import com.leavehub.model.LeaveRequest;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

@Service
public class PdfGeneratorService {

    public ByteArrayInputStream generateLeaveDocument(LeaveRequest request) {
        Document document = new Document(PageSize.A4);
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {
            PdfWriter.getInstance(document, out);
            document.open();

            Font titleFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 18);
            Paragraph title = new Paragraph("CERERE DE CONCEDIU", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);
            document.add(Chunk.NEWLINE);

            Font bodyFont = FontFactory.getFont(FontFactory.HELVETICA, 12);
            String text = "Subsemnatul(a) " + request.getEmployeeName() + ",\n" +
                    "Angajat(a) in cadrul departamentului " + request.getDepartment() + ",\n" +
                    "Va rog sa-mi aprobati cererea de concediu (" + request.getLeaveType() + ") " +
                    "Pentru perioada " + request.getStartDate() + " - " + request.getEndDate() + " " +
                    "(" + request.getRequestedDays() + " zile).\n\n" +
                    "Motiv: " + (request.getReason() != null ? request.getReason() : "Nespecificat") + "\n" +
                    "Status cerere: " + request.getStatus();

            Paragraph content = new Paragraph(text, bodyFont);
            document.add(content);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);

            Paragraph signature = new Paragraph("Data: " + (request.getCreatedAt() != null ? request.getCreatedAt().toLocalDate() : "N/A") + "\nSemnatura: ____________", bodyFont);
            signature.setAlignment(Element.ALIGN_RIGHT);
            document.add(signature);

            document.close();

        } catch (DocumentException e) {
            throw new RuntimeException("Eroare la generarea documentului PDF: " + e.getMessage(), e);
        }

        return new ByteArrayInputStream(out.toByteArray());
    }
}