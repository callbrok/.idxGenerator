package org.example;

public class IndexLine {

    private String instructionName;
    private String pageNumber;


    public IndexLine(String instructionName, String pageNumber) {
        this.instructionName = instructionName;
        this.pageNumber = pageNumber;
    }

    public String getInstructionName() {return instructionName;}
    public String getPageNumber() {return pageNumber;}


    public void setInstructionName(String instructionName) {this.instructionName = instructionName;}
    public void setPageNumber(String pageNumber) {this.pageNumber = pageNumber;}
}
