package org.example;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDDocumentOutline;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineItem;
import org.apache.pdfbox.pdmodel.interactive.documentnavigation.outline.PDOutlineNode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Intelx86Processors {
    List<IndexLine> idxList = new ArrayList<>();

    public List<IndexLine> x86(String pdfPath) throws IOException
    {
        PDDocument document = PDDocument.load(new File(pdfPath));
        PDDocumentOutline outline =  document.getDocumentCatalog().getDocumentOutline();

        printBookmark(outline, document);

        document.close();
        return idxList;
    }

    private void printBookmark(PDOutlineNode bookmark, PDDocument document) throws IOException
    {
        PDOutlineItem current = bookmark.getFirstChild();

        String regex = ".*\u2014.*"; // Pattern for em dash (U+2014)
        Pattern pattern = Pattern.compile(regex);

        while (current != null)
        {
            String line = current.getTitle();
            Matcher matcher = pattern.matcher(line);

            PDPage currentPage = current.findDestinationPage(document);
            String currentPageIndex = Integer.toString(document.getPages().indexOf(currentPage)+1);

            if(matcher.matches() && !line.contains("[")) {
                int indiceTrattino = line.indexOf('\u2014');
                String newLine = line.substring(0, indiceTrattino);

                if(newLine.contains("/")){
                    List<String> subInstruction = Arrays.asList(newLine.split("/"));

                    for(String subIns : subInstruction){
                        idxList.add(new IndexLine(subIns, currentPageIndex));
                    }
                }else{
                    idxList.add(new IndexLine(newLine, currentPageIndex));
                }
            }

            printBookmark(current, document);
            current = current.getNextSibling();
        }
    }
}
