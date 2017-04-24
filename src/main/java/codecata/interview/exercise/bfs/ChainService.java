package codecata.interview.exercise.bfs;

import java.util.List;
import java.util.Map;

public interface ChainService {

    List<Node> solve(String filePath, String wordOne, String wordTwo);
    Map<Integer, List<String>> readFile(String filePath);
}
