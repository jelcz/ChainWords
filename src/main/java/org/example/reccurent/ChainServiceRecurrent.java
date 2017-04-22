package org.example.reccurent;

import org.example.ChainService;
import org.example.bfs.Logger;
import org.example.bfs.Node;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class ChainServiceRecurrent implements ChainService {

    Logger logger = new Logger();
    int counter = 0;

    public void checkValidSolve(String filePath, String firstWord, String secondWord) {
        logger.log("Start");
        if (firstWord.length() != secondWord.length()) {
            throw new RuntimeException("Words differ in length");
        }

        List<String> entriesList = readFile(filePath, firstWord.length());
        solve(entriesList.stream().distinct().collect(Collectors.toList()),firstWord, secondWord);
        logger.log(solve(entriesList.stream().distinct().collect(Collectors.toList()),firstWord, secondWord).toString());
    }

    public List<Node> solve(List<String> entriesList, String wordOne, String wordTwo) {

        logger.log("Prepare data");
        logger.log("wordOne: " + wordOne + " wordTwo: " + wordTwo);

        List<String> listFiltered = new LinkedList();
        listFiltered.add(wordOne);

        findRecurrency(listFiltered, entriesList, wordTwo);
        System.out.println(counter);
        return new LinkedList<Node>();
    }

    public int findRecurrency(List<String> wordsList, List<String> listAll, String wordTwo) {
        counter++;
        listAll.removeAll(wordsList);
        List<String> result = new LinkedList<>();

        wordsList.forEach(str -> {
            String regexp = prepareRegexp(str);
            result.addAll(listAll.stream().filter(s ->
                    s.matches(regexp)).collect(Collectors.toList()));
        });

        if (result.contains(wordTwo) || listAll.size() == 0) {
            logger.log("znalazlem!");
            return 1;
        } else {
            return findRecurrency(result, listAll, wordTwo);
        }
    }

    public String prepareRegexp(String str) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(str);
            stringBuilder.replace(i, i + 1, "[a-z]");
            stringBuilder.toString();
            result += stringBuilder.toString() + "|";
        }
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
}

