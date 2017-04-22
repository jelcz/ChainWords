package org.example.bfs;

import org.example.ChainService;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ChainServiceBFS implements ChainService {

    static Logger logger = new Logger();
    static NeighboursService neighboursService = new NeighboursService();

   /* public static void checkValidSolve(String filePath, String firstWord, String secondWord) {
        logger.log("Start");
        if (firstWord.length() != secondWord.length()) {
            throw new RuntimeException("Words differ in length");
        }

        List<String> entriesList = readFile(filePath, firstWord.length());
        solve(entriesList.stream().distinct().collect(Collectors.toList()), firstWord, secondWord);
        logger.log("Finish");
    }*/

    public void checkValidSolve(String filePath, String firstWord, String secondWord) {
        logger.log("Start");
        if (firstWord.length() != secondWord.length()) {
            throw new RuntimeException("Words differ in length");
        }
        Map<Integer, List<String>> entriesMap = readFileMap(filePath);
        logger.log(solve(entriesMap.get(firstWord.length()).stream().distinct().collect(Collectors.toList()), firstWord, secondWord).toString());
        logger.log("Finish");
    }

    public List<Node> solve(List<String> entriesList, String wordOne, String wordTwo) {

        logger.log("Prepare data");
        List<Node> nodeList = makeNodeList(entriesList);

        Node nodeOne = getNodeIfExists(nodeList, new Node(wordOne));
        Node nodeTwo = getNodeIfExists(nodeList, new Node(wordTwo));

        neighboursService.fillNeighbourList(nodeList);

        return getPathBFS(nodeOne, nodeTwo);
    }

    private Node getNodeIfExists(List<Node> nodeList, Node node) {
        if (nodeList.contains(node)) {
            Node finalNode = node;
            node = nodeList.stream().filter(p -> p.equals(finalNode)).findFirst().get();
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
        return path;
    }

    public List<Node> makeNodeList(List<String> list) {
        List<Node> result = new LinkedList<>();
        list.forEach(str -> result.add(new Node(str.toLowerCase())));
        return result;
    }

    public List<String> readFile(String filePath, Integer length) {
        logger.log("Reading file");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader.lines().collect(Collectors.toList()).stream().filter(m -> (m.length() == length)).collect(Collectors.toList());
    }

    public Map<Integer, List<String>> readFileMap(String filePath) {
        logger.log("Reading file");
        BufferedReader bufferedReader = null;
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(filePath), "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bufferedReader.lines().collect(Collectors.groupingBy(String::length));//faster than List<String>
    }
}

