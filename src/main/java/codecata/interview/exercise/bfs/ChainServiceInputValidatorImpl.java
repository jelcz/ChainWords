package codecata.interview.exercise.bfs;

import java.io.File;

class ChainServiceInputValidatorImpl implements ChainServiceInputValidator {

    public void checkValid(String filePath, String firstWord, String secondWord) {
        Logger.log("Check valid");

        areWordsEmpty(firstWord, secondWord);
        areWordsEqualSize(firstWord, secondWord);
        dictionaryFileExists(filePath);
    }

    private void areWordsEmpty(String firstWord, String secondWord) {
        if (firstWord == null || secondWord == null || firstWord.length() == 0) {
            throw new RuntimeException("Words are empty");
        }
    }

    private void areWordsEqualSize(String firstWord, String secondWord) {
        if (firstWord.length() != secondWord.length()) {
            throw new RuntimeException("Words differ in length");
        }
    }

    private void dictionaryFileExists(String filePath) {
        File f = new File(filePath);
        if (!f.exists() || f.isDirectory()) {
            throw new RuntimeException("Wrong file path");
        }
    }
}
