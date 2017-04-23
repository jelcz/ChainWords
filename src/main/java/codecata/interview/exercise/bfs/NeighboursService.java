package codecata.interview.exercise.bfs;

import java.util.List;

class NeighboursService {
    static void fillNeighbourList(List<Node> nodes) {
        int wordLength = nodes.get(0).getWord().length();
        nodes.forEach(node -> nodes.subList((nodes.indexOf(node) + 1), nodes.size())
            .forEach(parentNode -> {
                if (checkIfNeighbours(parentNode.getWord(), node.getWord(), wordLength))
                    parentNode.addNeighbour(node);
                }
            )
        );
    }

    private static boolean checkIfNeighbours(String left, String right, int wordLength) {
        if (left == right) { //faster than equals
            return false;
        }

        int amountDifferentLetters = 0;
        for (int i = 0; i < wordLength; i++) {
            if (left.charAt(i) != right.charAt(i))
                amountDifferentLetters++;
            if (amountDifferentLetters == 2)
                return false;
        }
        return true;
    }
}
