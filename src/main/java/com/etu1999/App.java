package com.etu1999;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.apache.commons.io.FileUtils;

import com.etu1999.huf.Huffman;
import com.etu1999.huf.HuffmanCompressor;
import com.etu1999.node.Node;
import com.etu1999.utils.Misc;


/**
 * Main app
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException
    {
        
        // HuffmanCompressor compressor = new HuffmanCompressor("/Users/rakharrs/Developer/codage_TSINJO/TextCompressor/file/file.txt");
        // compressor.compress();
        File file = new File("/Users/rakharrs/Developer/codage_TSINJO/TextCompressor/file/file.txt.cmp");
        HuffmanCompressor.decompress(file,"/Users/rakharrs/Developer/codage_TSINJO/TextCompressor/file/unzipped.txt");
        

    }  
}
