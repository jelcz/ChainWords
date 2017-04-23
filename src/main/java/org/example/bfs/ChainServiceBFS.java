package org.example.bfs;

import org.example.ChainService;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class ChainServiceBFS implements ChainService {

    static Logger logger = new Logger();
    static NeighboursService neighboursService = new NeighboursService();

    public void checkValid(String filePath, String firstWord, String secondWord) {
        logger.log("Check valid");

        if (firstWord == null || secondWord == null || firstWord.length() == 0) {
            throw new RuntimeException("Words are empty");
        }
        if (firstWord.length() != secondWord.length()) {
            throw new RuntimeException("Words differ in length");
        }
        File f = new File(filePath);
        if (!f.exists() || f.isDirectory()) {
            throw new RuntimeException("Wrong file path");
        }
    }

    public List<Node> solve(String filePath, String wordOne, String wordTwo) {
        logger.log("Solve");
        Map<Integer, List<String>> entriesMap = readFile(filePath);

        List<String> entriesList = entriesMap.get(wordOne.length())
                .stream()
                .distinct()
                .collect(Collectors.toList());

        List<Node> nodeList = makeNodeList(entriesList);

        Node nodeOne = getNodeIfExists(nodeList, new Node(wordOne));
        Node nodeTwo = getNodeIfExists(nodeList, new Node(wordTwo));

        neighboursService.fillNeighbourList(nodeList);

        return getPathBFS(nodeOne, nodeTwo);
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

    public List<Node> getPathBFS(Node start, Node finish) {
        logger.log("BFS");
        Set<Node> vis = new HashSet<Node>();
        Map<Node, Node> prev = new HashMap<Node, Node>();

        List path = new LinkedList();
        Queue q = new LinkedList();
        Node current = start;
        q.add(current);
        vis.add(current);
        while (!q.isEmpty()) {
            current = (Node) q.remove();
            if (current.equals(finish)) {
                break;
            } else {
                for (Node node : current.getOutNodes()) {
                    if (!vis.contains(node)) {
                        q.add(node);
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
        logger.log(path.toString());
        return path;
    }

    public List<Node> makeNodeList(List<String> list) {
        List<Node> result = new ArrayList<>();
        list.forEach(str -> result.add(new Node(str.toLowerCase())));
        return result;
    }

    public Map<Integer, List<String>> readFile(String filePath) {
        logger.log("Reading file");
        BufferedReader bufferedReader;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
            return bufferedReader.lines()
                    .collect(Collectors.groupingBy(String::length));//faster than List<String>
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}

