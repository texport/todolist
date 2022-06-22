import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main
{
    static ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args)
    {
        System.out.println("Вводите команды с правильным синтаксисом\nLIST - посмотреть список задач\nADD 'текст задачи' - добавить новую задачу" +
                "\nDELETE 'номер задачи' - удалить задачу\nADDNUM 'номер задачи' 'задача'");

        String[] text = getScanner();

        while (!text[0].equals("EXIT"))
        {
            if (text[0].equals("ADD") && text[1] != null && text[2] == null)
            {
                addTask(text[1]);
                System.out.println("Вы добавили новую задачу");
            }
            else if (text[0].equals("ADDNUM") && text[1] != null && text[2] != null)
            {
                addTaskNumber(Integer.parseInt(text[1]), text[2]);
                System.out.println("Вы добавили новую задачу по номеру");
            }
            else if (todoList.size() >= 1 && text[0].equals("LIST") && text[1] == null && text[2] == null)
            {
                getToDoList();
            }
            else if (todoList.size() >= 1 && text[0].equals("DELETE") && text[1] != null && text[2] == null)
            {
                System.out.println("Вы удалили задачу №: " + deleteTask(Integer.parseInt(text[1])));
            }
            else if (todoList.size() >= 1 && text[0].equals("EDIT") && text[1] != null && text[2] != null)
            {
                System.out.println("Вы изменили задачу №: " + editTaskNumber(Integer.parseInt(text[1]), text[2]));
            }
            else
            {
                System.out.println("Ваш синтаксис не верен, либо список задач пустой!!!");
            }

            text = getScanner();
        }
    }

    // Считываем то что написали и парсим
    public static String[] getScanner()
    {
        Scanner scanner = new Scanner(System.in);
        String textRead = scanner.nextLine();

        // ADD
        int aIndex = textRead.indexOf('A');                 // A
        int firstDIndex = textRead.indexOf('D');            // D
        int secondDIndex = firstDIndex + 1;                 // D
        int spaceADDIndex = textRead.indexOf(' ');          // SPACE

        // ADDNUM
        int firstAADDNUMIndex = textRead.indexOf('A');      // A
        int firstADDNUMDIndex = textRead.indexOf('D');      // D
        int secondDADDNUMIndex = firstDIndex + 1;           // D
        int firstNADDNUMIndex = textRead.indexOf('N');      // N
        int firstUADDNUMIndex = textRead.indexOf('U');      // U
        int firstMADDNUMIndex = textRead.indexOf('M');      // M
        int spaceADDNUMIndex = textRead.indexOf(' ');       // SPACE

        // LIST
        int firstLLISTIndex = textRead.indexOf('L');        // L
        int firstILISTIndex = textRead.indexOf('I');        // I
        int firstSLISTIndex = textRead.indexOf('S');        // S
        int firstTLISTIndex = textRead.indexOf('T');        // T

        // DELETE
        int firstDDELETEIndex = textRead.indexOf('D');      // D
        int firstEDELETEIndex = textRead.indexOf('E');      // E
        int firstLDELETEIndex = textRead.indexOf('L');      // L
        int secondEDELETEIndex = textRead.indexOf('E', firstEDELETEIndex + 1);     // E
        int firstTDELETEIndex = textRead.indexOf('T');      // T
        int trecondEDELETEIndex = textRead.indexOf('E', secondEDELETEIndex + 1);   // E
        int spaceDeleteIndex = textRead.indexOf(' ');       // SPACE

        // EDIT
        int fistEEDITIndex = textRead.indexOf('E');         // E
        int fistDEDITIndex = textRead.indexOf('D');         // D
        int fistIEDITIndex = textRead.indexOf('I');         // I
        int fistTEDITIndex = textRead.indexOf('T');         // T
        int spaceEDITIndex = textRead.indexOf(' ');         // SPACE

        // Пробелы
        int firstSpaceIndex = textRead.indexOf(' ');
        int secondSpaceIndex = textRead.indexOf(' ', firstSpaceIndex + 1);

        // Какие есть команды
        boolean isADD = false;
        boolean isADDNUM = false;
        boolean isDELETE = false;
        boolean isEDIT = false;

        // Создаем массив который возвращает метод getScanner
        String[] textComplete = new String[3];

        // Временная переменная
        textComplete[0] = " ";
        String temp = " ";

        // Сейчас будет код который будет проверять первое слово и записывать его в первый элемент массива
        // Ищем первое слово и проверяем что это точно команда ADD
        if (aIndex == 0 && firstDIndex == 1 && secondDIndex == 2 && spaceADDIndex == 3)
        {
            char[] add = {textRead.charAt(aIndex), textRead.charAt(firstDIndex), textRead.charAt(secondDIndex)};
            temp = String.valueOf(add);
            isADD = true;
            textComplete[0] = temp;
        }
        // ADDNUM
        else if (firstAADDNUMIndex == 0 && firstADDNUMDIndex == 1 && secondDADDNUMIndex == 2 &&
                firstNADDNUMIndex == 3 && firstUADDNUMIndex == 4 && firstMADDNUMIndex == 5 &&
                spaceADDNUMIndex == 6)
        {
            char[] addNum = {textRead.charAt(firstAADDNUMIndex), textRead.charAt(firstADDNUMDIndex), textRead.charAt(secondDADDNUMIndex),
                    textRead.charAt(firstNADDNUMIndex), textRead.charAt(firstUADDNUMIndex), textRead.charAt(firstMADDNUMIndex)
            };
            temp = String.valueOf(addNum);
            isADDNUM = true;
            textComplete[0] = temp;
        }
        // LIST
        else if (firstLLISTIndex == 0 && firstILISTIndex == 1 && firstSLISTIndex == 2 && firstTLISTIndex == 3)
        {
            char [] list = {textRead.charAt(firstLLISTIndex), textRead.charAt(firstILISTIndex),
                    textRead.charAt(firstSLISTIndex), textRead.charAt(firstTLISTIndex)
            };
            temp = String.valueOf(list);
            textComplete[0] = temp;
        }
        // DELETE
        else if (firstDDELETEIndex == 0 && firstEDELETEIndex == 1 && firstLDELETEIndex == 2 && secondEDELETEIndex == 3 &&
        firstTDELETEIndex == 4 && trecondEDELETEIndex == 5 && spaceDeleteIndex == 6)
        {
            char[] delete = {textRead.charAt(firstDDELETEIndex), textRead.charAt(firstEDELETEIndex),
                    textRead.charAt(firstLDELETEIndex), textRead.charAt(secondEDELETEIndex), textRead.charAt(firstTDELETEIndex),
                    textRead.charAt(trecondEDELETEIndex)
            };
            temp = String.valueOf(delete);
            isDELETE = true;
            textComplete[0] = temp;
        }
        // EDIT
        else if (fistEEDITIndex == 0 && fistDEDITIndex == 1 && fistIEDITIndex == 2 && fistTEDITIndex == 3 && spaceEDITIndex == 4)
        {
            char[] edit = {textRead.charAt(fistEEDITIndex), textRead.charAt(fistDEDITIndex), textRead.charAt(fistIEDITIndex), textRead.charAt(fistTEDITIndex)};
            temp = String.valueOf(edit);
            isEDIT = true;
            textComplete[0] = temp;
        }

        // После того когда мы проверили команду, разносим строчки по массиву
        if (isADD)
        {
            temp = textRead.substring(firstSpaceIndex).trim();
            textComplete[1] = temp;
        }
        else if (isADDNUM)
        {
            temp = textRead.substring(firstSpaceIndex, secondSpaceIndex).trim();

            if (temp.matches("[0-9]"))
            {
                textComplete[1] = temp;
                temp = textRead.substring(secondSpaceIndex).trim();
                textComplete[2] = temp;
            }
        }
        else if (isDELETE)
        {
            temp = textRead.substring(firstSpaceIndex).trim();

            if (temp.matches("[0-9]"))
            {
                textComplete[1] = temp;
            }
        }
        else if (isEDIT)
        {
            temp = textRead.substring(firstSpaceIndex, secondSpaceIndex).trim();

            if (temp.matches("[0-9]"))
            {
                textComplete[1] = temp;
                temp = textRead.substring(secondSpaceIndex).trim();
                textComplete[2] = temp;
            }
        }

        return textComplete;
    }

    // Считываем весь список задач
    public static void getToDoList()
    {
        for (int i = 0; i < todoList.size(); i++)
        {
            System.out.println(i + " - " + todoList.get(i));
        }
    }

    // Добавляем новую задачу в конце списка
    public static void addTask(String task)
    {
        todoList.add(task);
    }

    // Добавить новую задачу по номеру
    public static void addTaskNumber(int index, String task)
    {
        todoList.add(index, task);
    }

    public static int editTaskNumber(int index, String task)
    {
        deleteTask(index);
        addTaskNumber(index, task);
        return index;
    }

    public static int deleteTask(int index)
    {
        todoList.remove(index);
        return index;
    }
}