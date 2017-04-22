package org.example;

import org.example.bfs.Node;

import java.util.List;

public interface ChainService {

    void checkValidSolve(String filePath, String firstWord, String secondWord);
    List<Node> solve(List<String> entriesList, String wordOne, String wordTwo);
    List<String> readFile(String filePath, Integer length);
}
