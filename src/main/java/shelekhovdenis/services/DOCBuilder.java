package shelekhovdenis.services;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import shelekhovdenis.models.album.AlbumObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DOCBuilder {

    public void writeToFile(AlbumObject album) throws IOException {
        File file = new File("file.docx");
        XWPFDocument document = new XWPFDocument(new FileInputStream(file));
        List<XWPFParagraph> pastParagraph = document.getParagraphs();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();

        run.setFontSize(14);
        run.setFontFamily("Times New Roman");

        run.setText("Album: " + album.getName() + ";");
        run.addBreak();
        run.setText("Artist: " + album.getArtist() + ";");
        run.addBreak();

        run.setText("Genres: " + album.getTags().toString() + ";");
        run.addBreak();

        run.setText("Images:");
        run.addBreak();
        String[] images = album.imageToString().split(";");
        for(String image : images) {
            run.addTab();
            run.setText(image + ";");
            run.addBreak();
        }

        run.setText("Tracks:");
        run.addBreak();
        ArrayList<String> tracks = album.getTracks().getTracksToTile();
        for(String track : tracks){
            run.addTab();
            run.setText(track);
            run.addBreak();
        }

        document.write(new FileOutputStream(file));
        document.close();
    }

    public void ClearFile(){
        File file = new File("file.docx");
        XWPFDocument document = new XWPFDocument();
        XWPFParagraph paragraph = document.createParagraph();
        XWPFRun run = paragraph.createRun();
        try {
            document.write(new FileOutputStream(file));
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
