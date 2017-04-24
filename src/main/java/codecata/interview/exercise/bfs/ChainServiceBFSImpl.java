package codecata.interview.exercise.bfs;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ChainServiceBFSImpl implements ChainService {

    public List<Node> solve(String filePath, String wordOne, String wordTwo) {
        Logger.log("Solve");
        List<Node> nodeList = prepareNodeList(filePath, wordOne);

        Node nodeOne = getNodeIfExists(nodeList, new Node(wordOne));
        Node nodeTwo = getNodeIfExists(nodeList, new Node(wordTwo));

        NeighboursService.fillNeighbourList(nodeList);

        return getPathBFS(nodeOne, nodeTwo);
    }

    public Map<Integer, List<String>> readFile(String filePath) {
        Logger.log("Reading file");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            return bufferedReader.lines()
                    .collect(Collectors.groupingBy(String::length));//faster than List<String>
        } catch (IOException e) {
            Logger.log("Failed to read file");
            return null;
        }
    }

    private List<Node> prepareNodeList(String filePath, String wordOne) {
        Map<Integer, List<String>> entriesMap = readFile(filePath);

        List<String> entriesList = entriesMap.get(wordOne.length())
                .stream()
                .distinct()
                .collect(Collectors.toList());

        return fillNodeList(entriesList);
    }

    private Node getNodeIfExists(List<Node> nodeList, Node node) {
        if (nodeList.contains(node)) {
            Node finalNode = node;
            node = nodeList.stream()
                    .filter(p -> p.equals(finalNode))
                    .findFirst()
                    .get();
        } else {
            throw new RuntimeException("No path between words.");
        }
        return node;
    }

    List<Node> getPathBFS(Node start, Node finish) {
        Logger.log("BFS");
        Set<Node> vis = new HashSet<>();
        Map<Node, Node> prev = new HashMap<>();

        LinkedList<Node> path = new LinkedList();
        Queue<Node> queue = new LinkedList();
        Node current = start;
        queue.add(current);
        vis.add(current);
        while (!queue.isEmpty()) {
            current = queue.remove();
            if (current.equals(finish)) {
                break;
            } else {
                for (Node node : current.getNeighbourhood()) {
                    if (!vis.contains(node)) {
                        queue.add(node);
                        vis.add(node);
                        prev.put(node, current);
                    }
                }
            }
        }
        if (!current.equals(finish)) {
            throw new RuntimeException("No path between words.");
        }
        for (Node node = finish; node != null; node = prev.get(node)) {
            path.add(node);
        }
        Collections.reverse(path);
        Logger.log(path.toString());
        return path;
    }

    private List<Node> fillNodeList(List<String> list) {
        List<Node> result = new ArrayList<>(list.size());
        list.forEach(str -> result.add(new Node(str.toLowerCase())));
        return result;
    }
}

