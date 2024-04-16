package com.etu1999.huf;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;

import com.etu1999.utils.Misc;

public class HuffmanCompressor {
    Huffman huffman = new Huffman();
    File file;

    public HuffmanCompressor(String path) throws IOException{
        this.file = new File(path);
        huffman.setText(Misc.readFile(file));
    }

    public void compress() throws IOException{
        huffman.compress(file);
    }

    public static void decompress(File file, String path) throws IOException{
        File unziped = new File(path);
        file.createNewFile();
        FileUtils.write(unziped, Huffman.test(file), StandardCharsets.UTF_8, false);
    }
    
}
