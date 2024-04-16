package com.etu1999.node;

public class Node {
    String id = null;
    double value;

    String code;

    Node left;
    Node right;


    public Node(){}
    public Node(String id, double value){
        setId(id);
        setValue(value);
    }

    public Node(char id, double value){
        setId(String.valueOf(id));
        setValue(value);
    }



    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }



    public Node getLeft() {
        return left;
    }

    public Node getRight() {
        return right;
    }

    public String getId() {
        return id;
    }

    public double getValue() {
        return value;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
}
