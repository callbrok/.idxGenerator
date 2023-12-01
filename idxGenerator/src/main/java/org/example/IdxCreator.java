package org.example;

import java.io.*;
import java.util.List;
import java.util.Properties;

public class IdxCreator {

    public static void main(String[] args) throws Exception {

        Properties configurationProperties = new Properties();
        configurationProperties.load(new FileInputStream("configuration.properties"));

        String architecture;


        // Select which .idx file create
        List<IndexLine> idxList = switch (configurationProperties.getProperty("processor_arc")) {
            case "x86Intel" -> {
                Intelx86Processors x86Intel = new Intelx86Processors();
                architecture = "x86";
                yield x86Intel.x86(configurationProperties.getProperty("pdf_path"));
            }
            default -> {
                Intelx86Processors x86Intel = new Intelx86Processors();
                architecture = "x86";
                yield x86Intel.x86(configurationProperties.getProperty("pdf_path"));
            }
        };

        String pdfFilePath = configurationProperties.getProperty("pdf_path");
        String pdfFileName = pdfFilePath.substring(pdfFilePath.lastIndexOf("\\")+1);
        String idxFileName = architecture + ".idx";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(idxFileName))) {

            writer.write("@" + pdfFileName + " [" + architecture + " Mapped Manual by @callbrok]");
            writer.newLine(); // add new line

            for (IndexLine idx : idxList) {
                writer.write(idx.getInstructionName() + ", " + idx.getPageNumber());
                writer.newLine();
            }

            System.out.println(".idx file successfully created.");

        } catch (IOException e) {
            System.err.println("An error occurred while creating the file.");
            e.printStackTrace();
        }

    }

}
