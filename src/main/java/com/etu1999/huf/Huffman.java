package com.etu1999.huf;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import org.apache.commons.io.FileUtils;

import com.etu1999.node.Node;
import com.etu1999.process.NodeComparator;
import com.etu1999.utils.Misc;

public class Huffman{

    private Node root;
    HashMap<String, String> map; // Map du code et du caractere
    private Node[] nodes;           // Node non triés
    private String text;

    public Node[] getNodes() {
        return nodes;
    }

    public void setNodes(Node[] nodes) {
        this.nodes = nodes;
    }

    public String getText(){
        return this.text;
    }

    public Node getRoot() {
        return root;
    }

    public void setText(String text){
        Node[] source = Misc.fromTextToNodes(text, text.length());
        this.root = findRoot(source);
        this.text = text;
        this.nodes = source;
        this.map = Misc.mapSource(source);
    }

    public void print_source(){
        for (Node node : this.nodes)
            System.out.println(node.getId() + " : " + node.getCode() );
    }

    public String encode(){
        return encode(getText(), this.map);
    }
    /** 
     * get the root of the sources
     * and init every code of each characters
     */
    public static Node findRoot(Node[] sources){
        Node root = retrieveRoot(sources);
        initCode(root);
        return root;
    }

    public static String encode(String text, HashMap<String, String> map){
        return Misc.convert(text, map);
    }

    
 

    public static List<Node> initCode(Node root){
        List<Node> val = new ArrayList<>();
        initCode(root, "", val);
        return val;
    }


    public static Node retrieveRoot(Node[] sources){
        PriorityQueue<Node> queue = new PriorityQueue<Node>(new NodeComparator());

        for (Node node : sources) {
            queue.add(node);
        }

        Node root = new Node();

        // Miboucle anle queue hatramn'ny hoe ray sisa no ao
        while(queue.size() > 1 ){
            Node first = queue.peek();
            queue.poll();
            Node second = queue.peek();
            queue.poll();
            Node newNode = new Node();
            newNode.setId(null);
            newNode.setValue(first.getValue() + second.getValue());

            newNode.setLeft(first);
            newNode.setRight(second);
            
            queue.add(newNode);

            root = newNode;
        }

        return root;
    }

    public static void initCode(Node root, String value, List<Node> nodes){
        
        if(root.getLeft() == null && root.getRight() == null){
            root.setCode(value);
            nodes.add(root);
            return;
        }

        initCode(root.getLeft(), value + '0', nodes);
        initCode(root.getRight(), value + '1', nodes);
    }

    public static String decode(Node root, String code){
        String val = "";
        Node node = root;
        for (int i = 0; i < code.length(); i++) {
            if(code.charAt(i) == '0')
                node = node.getLeft();
            else if(code.charAt(i) == '1')
                node = node.getRight();
            if(node.getLeft() == null && node.getRight() == null){
                val += node.getId();
                node = root;
            }
        } 
        return val;
    }

    public String decode(String code, HashMap<String, String> map){
        String val = "";
        String decode = "";
        Set<String> keys = map.keySet();
        for (int i = 0; i < code.length(); i++) {
            decode += code.charAt(i);
            for (String key : keys) {
                if(map.get(key).equals(decode)){
                    val += key;
                    decode = "";
                }
            }
        }
        return val;
    }

    public String decode(String code){
        return decode(code, this.map);
    }

    public File compress(File file) throws IOException{
        File compressed = new File(file.getParent()+File.separator+file.getName()+".cmp");
        String code = encode();
        byte[] bytes = Misc.toBytes(code);
        String codage = getNodeAsString();
        byte[] codageBytes = codage.getBytes();
        FileUtils.writeByteArrayToFile(compressed, (String.valueOf(bytes.length)+"\n").getBytes(),false);
        FileUtils.writeByteArrayToFile(compressed, codageBytes, true);
        FileUtils.writeByteArrayToFile(compressed, bytes, true);
        return compressed;
    }

    public static String test(File file) throws IOException{
        BufferedReader br = new BufferedReader(new FileReader(file));
        StringBuilder builder = new StringBuilder();
        String st;
        int textlength = Integer.parseInt(br.readLine());
        int count = Integer.parseInt(br.readLine());
        System.out.println(count);
        int i = 0;
        HashMap<String, String> code = new HashMap<>();
        String temp_st = "";
        int s = 0;
        String temp2 = "";
        while (i  < count && (st = br.readLine()) != null){
            temp2 += st;
            if(st.isEmpty()){
                code.put(temp_st, "\n");
                continue;
            }
            temp_st = br.readLine();
            code.put(temp_st, st);
            i++;
        }
        byte[] all = FileUtils.readFileToByteArray(file);
        all = Arrays.copyOfRange(all, all.length - textlength, all.length);
        
        
        Huffman ne = new Huffman();
        //return "";
        return ne.decode(Misc.fromBytetoString(all), code);
        
    }

    public String getNodeAsString(){
        StringBuilder builder = new StringBuilder();
        builder.append(nodes.length);
        builder.append("\n");
        for (int i = 0; i < nodes.length; i++) {
            builder.append(nodes[i].getCode());
            builder.append("\n");
            builder.append(nodes[i].getId());
            builder.append("\n");
        }
        return builder.toString();
    }
}