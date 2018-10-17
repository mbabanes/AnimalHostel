package animalHostel.gui.reports;

import animalHostel.gui.modelsFx.AnimalFx;
import animalHostel.gui.modelsFx.AnimalToHealFx;
import animalHostel.gui.utils.FXMLUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AnimalToHealReport
{
    private static final String ARIAL_FONT = "/fonts/arial/arial.ttf";
    private static final String TIMES_FONT = "/fonts/timesNewRoman/times.ttf";
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern(FXMLUtils.getResourceBundle().getString("datePattern"));

    private AnimalToHealFx animalToHealFx;

    private Font arialNormal;
    private Font arialBold;
    private Font timesMin;

    private Document document;
    private PdfWriter writer;

    public AnimalToHealReport(AnimalToHealFx animalToHealFx)
    {
        this();
        this.animalToHealFx = animalToHealFx;
    }

    private AnimalToHealReport()
    {
        document = new Document();
        prepareFonts();
    }


    public void createPdf(String dest) throws IOException, DocumentException
    {
        createFileAndSetNumberingPageAdding(dest);

        document.open();

        putHostelTitle();
        putAddress();
        putTitleOfReport();
        putNumberOfRegistration();
        putAnimalInformation();
        putDateOfRegistration();
        putSymptoms();

        document.close();
    }

    private void prepareFonts()
    {
        prepareArialNormalAndBoldFonts();
        prepareTimesNewRomanFont();
    }

    private void prepareArialNormalAndBoldFonts()
    {
        String fontPath = this.getClass().getResource(ARIAL_FONT).getFile();
        arialNormal = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 12);
        arialBold = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.EMBEDDED, 18, Font.BOLD);
    }

    private void prepareTimesNewRomanFont()
    {
        String fontPath = this.getClass().getResource(TIMES_FONT).getFile();
        timesMin = FontFactory.getFont(fontPath, BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED, Font.NORMAL);
        timesMin.setSize(10);
    }


    private void createFileAndSetNumberingPageAdding(String dest) throws DocumentException, FileNotFoundException
    {
        writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        writer.setPageEvent(new NumberingPageAdding());
    }

    private void putHostelTitle() throws DocumentException
    {
        Paragraph hostelTitleParagraph = createHostelTitleParagraph();
        Paragraph dateParagraph = createDateOfNowParagraph();

        Chunk glue = new Chunk(new VerticalPositionMark());

        hostelTitleParagraph.add(glue);
        hostelTitleParagraph.add(dateParagraph);

        document.add(hostelTitleParagraph);
    }

    private void putAddress() throws DocumentException
    {
        Paragraph addressParagraph = createAddressParagraph();
        Paragraph postParagraph = createPostParagraph();

        document.add(addressParagraph);
        document.add(postParagraph);
    }

    private void putTitleOfReport() throws DocumentException
    {
        Paragraph reportTitleParagraph = createTitleOfReportParagraph();
        document.add(reportTitleParagraph);
    }

    private void putNumberOfRegistration() throws DocumentException
    {
        Paragraph numberOfRegistrationParagraph = createNumberOfRegistrationParagraph();
        document.add(numberOfRegistrationParagraph);
    }

    private void putAnimalInformation() throws DocumentException
    {
        Paragraph animalInfoParagraph = createAnimalInfoParagraph();
        document.add(animalInfoParagraph);
    }

    private void putDateOfRegistration() throws DocumentException
    {
        Paragraph dateOfRegisterParag = createDateOfRegisterParagraph();
        document.add(dateOfRegisterParag);
    }

    private void putSymptoms() throws DocumentException
    {
        Paragraph symptomsParagraph = createSymptomsParagraph();

        document.add(symptomsParagraph);
    }

    private Paragraph createDateOfNowParagraph()
    {
        LocalDate localDate = LocalDate.now();

        Paragraph dateParagraph = createParagraph(localDate.format(DATE_FORMAT) + "r.", timesMin);
        dateParagraph.setAlignment(Element.ALIGN_RIGHT);
        return dateParagraph;
    }


    private Paragraph createHostelTitleParagraph()
    {
        String hostelTitle = FXMLUtils.getResourceBundle().getString("report.hostelName");
        return createParagraph(hostelTitle, arialBold);
    }


    private Paragraph createSymptomsParagraph()
    {
        String symptoms = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.report.symptomsLabel") + animalToHealFx.getSymptoms();
        return createParagraph(symptoms, arialNormal);
    }


    private Paragraph createDateOfRegisterParagraph()
    {
        LocalDate dateOfRegister = animalToHealFx.getDateOfRegister();
        String dateOfRegisterString = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.report.dateOfRegistrationLabel") + dateOfRegister.format(DATE_FORMAT);
        return createParagraph(dateOfRegisterString, arialNormal);
    }

    private Paragraph createAnimalInfoParagraph()
    {
        AnimalFx animal = animalToHealFx.getAnimal();
        String animalString = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.animal") + ": " + animal.getId() + " (" + animal.getAnimalTypeFx().getType() + ", " + animal.getAnimalTypeFx().getRace() + ")";
        Paragraph animalParagraph = createParagraph(animalString, arialNormal);
        animalParagraph.setSpacingBefore(50f);

        return animalParagraph;
    }


    private Paragraph createNumberOfRegistrationParagraph()
    {
        String numberOfRegistration = "Nr: " + animalToHealFx.getIdAnimalToHeal();
        Paragraph numberOfRegistrationParagraph = createParagraph(numberOfRegistration, arialNormal);
        numberOfRegistrationParagraph.setAlignment(Element.ALIGN_CENTER);
        numberOfRegistrationParagraph.setSpacingBefore(10f);

        return numberOfRegistrationParagraph;
    }


    private Paragraph createTitleOfReportParagraph()
    {
        String mainTitle = FXMLUtils.getResourceBundle().getString("addAnimalToHeal.report.title");
        Paragraph mainTitleParagraph = createParagraph(mainTitle, arialBold);
        mainTitleParagraph.setSpacingBefore(30f);
        mainTitleParagraph.setAlignment(Element.ALIGN_CENTER);
        return mainTitleParagraph;
    }


    private Paragraph createPostParagraph()
    {
        return createParagraph("24-211 Miasto", timesMin);
    }

    private Paragraph createAddressParagraph()
    {
        Paragraph addressParagraph = createParagraph("ul. Iksińska 2", timesMin);
        addressParagraph.setSpacingBefore(10f);
        return addressParagraph;
    }


    private Paragraph createParagraph(String content, Font font)
    {
        Chunk chunk = new Chunk(content, font);
        return new Paragraph(chunk);
    }

    private class NumberingPageAdding extends PdfPageEventHelper
    {
        @Override
        public void onEndPage(PdfWriter writer, Document document)
        {
            super.onEndPage(writer, document);
            PdfContentByte cb = writer.getDirectContent();
            Phrase footer = new Phrase(String.valueOf(writer.getPageNumber()), arialNormal);
            float x = (document.right() - document.left()) / 2 + document.leftMargin();
            float y = document.bottom() - 10;
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, footer, x, y, 0);
        }
    }
}
