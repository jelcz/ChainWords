package codecata.interview.exercise.bfs;

public class Main {

    private static String FILE_PATH = "src/main/resources/wordlist.txt";
    private static String WORD_ONE = "lead";
    private static String WORD_TWO = "gold";

    public static void main(String[] args) {
        ChainServiceBFS chainServiceBFS = new ChainServiceBFS();
        long sum = 0;
        int counter = 1;
        for (int i = 0; i < counter; i++) {

            long start = System.currentTimeMillis();

            try {
                ChainServiceInputValidator.checkValid(FILE_PATH, WORD_ONE, WORD_TWO);
                chainServiceBFS.solve(FILE_PATH, WORD_ONE, WORD_TWO);
            } catch (RuntimeException e) {
                e.printStackTrace();
            }

            long finish = System.currentTimeMillis();
            Logger.log(("Time single: " + (finish - start)));
            sum += (finish - start);
        }
        Logger.log("Time average: " + (sum / counter));
    }
}
