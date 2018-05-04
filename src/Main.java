import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.*;

/**
 * Created by volynec on 26.04.2018.
 * первая задача, надо составить (динамически) массив из 200 слов(рандомных)
 * затем пройтись по массиву и составить по нему хэш таблицу со словами которые содержат более 2 символов "а"
 * на консоли мог ввести любую букву и она уже будет искаться в твоих словах,
 * на экран надо вывести все слова в которых эта буква встречается чаще чем 2 раза
 * записать в файл
 */
public class Main {
    static String[] randomWords;
    static List repeatingLetterWordsInList;
    static int count = 1;

    public static void main(String[] args) {
        randomWords = generateRandomWords(200);
        char letter = scanLetter();
        repeatingLetterWordsInList = findRepeatingLetterList(randomWords, letter);
//        Map repeatingLetterWordsInMap = findRepeatingLetterMap(randomWords, letter);
        while (repeatingLetterWordsInList.isEmpty()) {
            randomWords = generateRandomWords(200);
            repeatingLetterWordsInList = findRepeatingLetterList(randomWords, letter);
            count++;
            System.out.println("Попытка: " + count);
        }
        System.out.println(repeatingLetterWordsInList);
        writeUsingFiles(repeatingLetterWordsInList);


    }

    private static void writeUsingFiles(List<String> repeatingLetterWordsInList) {
        try {
            for (String data : repeatingLetterWordsInList) {
                Files.write(Paths.get("C:/files.txt"), data.getBytes(), StandardOpenOption.APPEND);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(15) + 1];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) (random.nextInt(26) + 97);
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private static Map findRepeatingLetterMap(String[] words, char letter) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            char[] arr = word.toCharArray();
//            Arrays.sort(arr);
            int count = countLetter(arr, letter);
            if (count >= 2) {
                map.put(word, count);
            }
        }
        return map;
    }

    public static List<String> findRepeatingLetterList(String[] words, char letter) {
        List<String> list = new ArrayList();
        for (String word : words) {
            char[] arr = word.toCharArray();
//            Arrays.sort(arr);
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i] == letter && arr[i] == arr[i + 1]) {
                    list.add(word);
                }
            }
        }
        return list;
    }

    private static int countLetter(char[] arr, char letter) {
        int coutn = 0;
        for (char c : arr) {
            if (c == letter) {
                coutn++;
            }
        }
        return coutn;
    }

    public static char scanLetter() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter letter!");
        String s = sc.next().toLowerCase().trim();
        char letter = s.charAt(0);
        return letter;
    }
}
