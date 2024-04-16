package com.etu1999.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.CRC32;

import com.etu1999.node.Node;

public class Misc {
    public static byte[] toBytes(String code){
        byte bytes[] = new byte[Math.round(code.length()/7)+1];
        int i=0;
        while (code.length() > 7) {
            bytes[i] = Byte.parseByte(code.substring(0, 7),2);
            code = code.substring(7);
            i++;
        }
        bytes[i] = Byte.parseByte(code,2);
        return bytes;
    }

    public static String fromBytetoString(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        int length = bytes.length;
        for (int i = 0; i < length - 1; i++)
            builder.append(appending_zero(Integer.toBinaryString(bytes[i])));
        builder.append(Integer.toBinaryString(bytes[length - 1]));
        return builder.toString();
    }

    public static String appending_zero(String binarie){
        while(binarie.length() < 7)
            binarie = '0' + binarie;
        return binarie;
    }

    public static String readFile(File file) throws IOException{
         BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String st;
        while ((st = br.readLine()) != null){
            builder.append(st);
            builder.append("\n");
        }
        return builder.toString();
    }

    public static double findFrequency(String text, int text_length, char s){
        double val = 0;
        for (int i = 0; i < text_length; i++)
            if(text.charAt(i) == s)
                val++;
        return val / text_length;
    }

    public static double findFrequency(String text, char s){
        int text_length = text.length(); 
        return findFrequency(text, text_length, s);
    }

    public static Node[] fromTextToNodes(String text, int text_length){
        HashMap<Character, Node> char_value = new HashMap<>();
        for (int i = 0; i < text_length; i++) {
            char c = text.charAt(i);
            if(char_value.get(c) == null)
                char_value.put(c, new Node(c, findFrequency(text, text_length, c)));
        }
        return mapToNodeList(char_value);
    }

    public static Node[] mapToNodeList(HashMap<?,Node> map){
        ArrayList<Node> nodes = new ArrayList<>();
        for(Object key : map.keySet())
            nodes.add(map.get(key));
        return nodes.toArray(new Node[0]);
    }

    public static String findCode(Node[] source, char c){
        for (int i = 0; i < source.length; i++)
            if(source[i].getId().equals(String.valueOf(c)))
                return source[i].getCode();
        return "";
    }

    /**
     * Convert array of node 
     * @param sources
     * @return
     */
    public static HashMap<String, String> mapSource(Node[] sources){
        HashMap<String, String> val = new HashMap<>();
        for (Node node : sources) 
            val.put(node.getId(), node.getCode());
        return val;
    }

    public static String convert(String text, HashMap<String,String> map){
        String val = new String();
        val = text;
        for(String key : map.keySet())
            val = val.replace(key, map.get(key));
        return val;
    }





}
