
# x86 Processor Manual Index File Generator

<img align="left" width="400" height="600" src="https://github.com/callbrok/.idxGenerator/blob/dc353b82151124a5b12490ff8c9b8df92f93e8e9/screenshot.png">

Since index files of manuals in [Ghidra](https://github.com/NationalSecurityAgency/ghidra) are not up-to-date .idxGenerator allows you to easily create an index file ".idx" of the latest version of any processor manual.

## .idx File Overview
The processor manual index file (*.idx) contains list of instructions mnemonic and page 
numbers into an identified PDF manual.  These files along with the corresponding PDF files
are generally located within the data/manuals directory of a Ghidra module
(e.g., Ghidra/Processors/x86/data/manuals/x86.idx)

The first line of the .idx file must be a PDF manual selector preceeded by the @ character. The PDF manual selector is immediately followed by lines containing instruction mnemonic, page number pairs which are contained within the specified PDF manual. Additional PDF manuals may be referenced by repeating this pattern starting with the @ character on a new line.

Example:

```
@Intel64_IA32_vol2a.pdf [Intel 64 and IA-32 Vol 2A: Instruction Set Reference, A-M, Nov 2008 (253666-029US)]
AAA, 74
AAD, 76

@Intel64_IA32_vol2b.pdf [Intel 64 and IA-32 Vol 2B: Instruction Set Reference, N-Z, Nov 2008 (253667-029US)]
NEG, 4
NOP, 7
NOT, 9
```

## Pre-made .idx
If you don't want to generate them, you can download some .idx files that I generated previously via .idxGenerator

| **Processor**                                                                                                               | **Version**    | **.pdf**     | **.idx**     |
|-----------------------------------------------------------------------------------------------------------------------------|----------------|--------------|--------------|
| Intel® 64 and IA-32 Architectures Software Developer’s Manual. _Volume 2 (2A, 2B, 2C, & 2D): Instruction Set Reference, A-Z_ | September 2023 | [Download](https://cdrdv2.intel.com/v1/dl/getContent/671110)     | [Download](https://github.com/callbrok/.idxGenerator/blob/6fc4b5f430adb5a08c7790c892afcf1d46103fd3/x86.idx)     |

## How to Use
Edit the configuration file _configuration.properties_, and at the `pdf_path` property indicate the absolute path to the manual in pdf format from which to generate the processor manual index file (*.idx).

```
# PDF PATH
pdf_path = C:\\Users\\Marco\\Desktop\\manual.pdf

# PROCESSOR ARCHITECTURE
processor_arc = "x86Intel"
```

Once you run the processor manual index file (*.idx) will be generated, to use it within Ghira, you need to copy the .idx file and the pdf manual to `..\ghidra_folder\Ghidra\Processors\x86\data\manuals`.
