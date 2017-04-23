package org.example;

import org.example.bfs.Node;

import java.util.List;
import java.util.Map;

public interface ChainService {

    void checkValid(String filePath, String firstWord, String secondWord);
    List<Node> solve(String filePath, String wordOne, String wordTwo);
    Map<Integer, List<String>> readFile(String filePath);
}
