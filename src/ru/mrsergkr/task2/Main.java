package ru.mrsergkr.task2;

import java.io.*;

/**
 *
 * @author MrSergKr
 */
public class Main
{
    /**
     * @param args the command line arguments
     * args[0] - filepath
     */
    public static void main(String[] args) {
        String filepath = "strings.txt";
        /*if (args.length != 0) {
            filepath = args[0];
        }*/
        File file = new File(filepath);
        if (file.exists() && file.isFile()) {
            System.out.println(countWordsNumberInFirstLineOfFile(file));
        } else {
            System.out.println("File \"" + (new File("").getAbsolutePath()).replace('\\', '/') + "/" + filepath + "\" don't exist!");
        }
    }

    // Подсчитывает кол-во слов в первой строке файла.
    private static int countWordsNumberInFirstLineOfFile(File file) {
        int wordsCount = 0;
        try(FileReader reader = new FileReader(file)) {
            boolean isPrevCharWhitespace = true; // Предыдущий символ - пробельный?
            char currentChar;

            // Пока есть непрочитанные символы:
            while(reader.ready()) {
                currentChar = (char) reader.read();

                if (currentChar == '\n') {
                    break;
                }

                // Если текущий символ - пробельный: устанавливаем флаг.
                if (isWhitespaceCharacter(currentChar)) {
                    isPrevCharWhitespace = true;
                } else {
                    // Если текущий символ - непробельный, а предыдущий - пробельный, то начинается новое слово.
                    if (isPrevCharWhitespace) {
                        wordsCount++;
                        isPrevCharWhitespace = false;
                    }
                }

            }
            reader.close();
        }
        catch(IOException ex)
        {
            System.out.println(ex.getMessage());
        }

        return wordsCount;
    }

    // Проверяет, является ли символ пробельным.
    private static boolean isWhitespaceCharacter(char character) {
        char[] whitespaceCharacters = {' ', '\t', '\r', '\f'};

        for (char ch: whitespaceCharacters) {
            if (ch == character) {
                return true;
            }
        }
        return false;
    }
}
