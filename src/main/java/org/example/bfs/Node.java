package org.example.bfs;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private String word;
    private Set<Node> outNodes;

    public String toString() {
        return word;
    }

    public Node(String word) {
        this.word = word;
        this.outNodes = new HashSet<>();
    }

    public void addOutNode(Node node) {
        if (!this.outNodes.contains(node)) {
            this.outNodes.add(node);
            node.addOutNodeBackwards(this);
        }
    }

    public void addOutNodeBackwards(Node node) {
        this.outNodes.add(node);
    }

    public Set<Node> getOutNodes() {
        return outNodes;
    }

    public String getWord() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return word != null ? word.equals(node.word) : node.word == null;
    }
}
