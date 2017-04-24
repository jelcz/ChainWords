package codecata.interview.exercise.bfs;

import java.util.HashSet;
import java.util.Set;

public class Node {

    private String word;
    private Set<Node> neighbourhood;

    public Node(String word) {
        this.word = word;
        this.neighbourhood = new HashSet<>();
    }

    public void addNeighbour(Node node) {
        if (!this.neighbourhood.contains(node)) {
            this.neighbourhood.add(node);
            node.addNeighbourBackwards(this);
        }
    }

    private void addNeighbourBackwards(Node node) {
        this.neighbourhood.add(node);
    }

    public Set<Node> getNeighbourhood() {
        return neighbourhood;
    }

    public String getWord() {
        return word;
    }

    public String toString() {
        return word;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Node node = (Node) o;

        return word != null ? word.equals(node.word) : node.word == null;
    }

    @Override
    public int hashCode() {
        return word.hashCode();
    }
}
