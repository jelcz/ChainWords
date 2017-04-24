package codecata.interview.exercise.bfs;

import org.junit.Test;

public class ChainServiceInputValidatorImplTest {

    private String FILE_PATH = "src/main/resources/wordlist.txt";
    private String INVALID_FILE_PATH = "src/main/resources/wordlisst.txt";
    private ChainServiceInputValidator chainServiceInputValidator = new ChainServiceInputValidatorImpl();

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionNotFound() {
        String wordOne = "lead";
        String wordTwo = "gold";
        chainServiceInputValidator.checkValid(INVALID_FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionDifferentLength() {
        String wordOne = "leads";
        String wordTwo = "gold";
        chainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionNullWord() {
        String wordOne = null;
        String wordTwo = null;
        chainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }

    @Test(expected=RuntimeException.class)
    public void shouldThrowExceptionWrongWord() {
        String wordOne = "";
        String wordTwo = "";
        chainServiceInputValidator.checkValid(FILE_PATH, wordOne, wordTwo);
    }
}
