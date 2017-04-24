package codecata.interview.exercise.bfs;

import org.junit.Test;

public class ChainServiceInputValidatorTest {

    private String FILE_PATH = "src/main/resources/wordlist.txt";
    private String INVALID_FILE_PATH = "src/main/resources/wordlisst.txt";

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionNotFound() {
        String wordOne = "lead";
        String wordTwo = "gold";
        ChainServiceInputValidator.checkValid(INVALID_FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionDifferentLength() {
        String wordOne = "leads";
        String wordTwo = "gold";
        ChainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionNullWord() {
        String wordOne = null;
        String wordTwo = null;
        ChainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionWrongWord() {
        String wordOne = "";
        String wordTwo = "";
        ChainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }
}
