package com.infosys.irs.utility;

import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.AbstractView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.util.Map;

public abstract class AbstractIInfyGoPdfView extends AbstractView {

    public AbstractIInfyGoPdfView() {
        setContentType("application/pdf");
    }

    protected void createPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {}

    protected abstract void createPdfDoc(Map<String, Object> model,
                                             Document document,
                                             PdfWriter pdfwriter,
                                             HttpServletRequest request,
                                             HttpServletResponse response) throws Exception;

    @Override
    protected void renderMergedOutputModel(Map<String, Object> model,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws Exception {

        ByteArrayOutputStream out = createTemporaryOutputStream();
        Document document = new Document(PageSize.A4);
        PdfWriter pdfwriter = PdfWriter.getInstance(document, out);
        pdfwriter.setViewerPreferences(PdfWriter.ALLOW_PRINTING | PdfWriter.PageLayoutSinglePage);
        createPdfMetadata(model, document, request);

        document.open();
        createPdfDoc(model, document, pdfwriter, request, response);
        document.close();
        writeToResponse(response, out);
    }

/*    @Override
    protected boolean generatesDownloadContent() {
        return true;
    }*/
}
