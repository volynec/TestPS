import java.util.*;

/**
 * Created by volynec on 26.04.2018.
 * первая задача, надо составить (динамически) массив из 200 слов(рандомных)
 * затем пройтись по массиву и составить по нему хэш таблицу со словами которые содержат более 2 символов "а"
 * на консоли мог ввести любую букву и она уже будет искаться в твоих словах,
 * на экран надо вывести все слова в которых эта буква встречается чаще чем 2 раза
 *
 *
 */
public class Main {
    public static void main(String[] args) {
        String[] randomWords = generateRandomWords(200);
        List repeatingLetterWords = findRepeatingLetter(randomWords);
        System.out.println(repeatingLetterWords);

    }

    public static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(10) + 1];
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) (  random.nextInt(26)+97);
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    public static List findRepeatingLetter(String[] words) {
        List list = new ArrayList();
        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i]=='a'&& arr[i] == arr[i + 1]) {
                    list.add(word);
                }
            }
        }
        return list;
    }
}
