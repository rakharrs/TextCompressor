package com.etu1999.process;

import com.etu1999.node.Node;

public class NodeComparator implements java.util.Comparator<Node> {

    @Override
    public int compare(Node node1, Node node2) {
        return Double.compare(node1.getValue(), node2.getValue());
    }
    
}
