import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

public class WriteReadFromFile {

    String pathToFile, stringData;
    public static void main(String[] args) {

      // writeToFile("C:\\test\\notes3.txt", "Мама мыла раму, раму мыла мама");
       // readFromFile1("C:\\test\\notes3.txt");

    }
    WriteReadFromFile(String pT){
        pathToFile=pT;
    }
    WriteReadFromFile(String pT, String sD){
      pathToFile=pT;
      stringData=sD;
    }

    public ArrayList<String> readFromFile() {
        ArrayList<String> elementsOfFile = new ArrayList<String>();
        try {// читаем построчно
            Files.lines(Paths.get(pathToFile), StandardCharsets.UTF_8).forEach(value -> elementsOfFile.add(value));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return elementsOfFile;
        //  elementsOfFile.forEach(value -> System.out.println(value));
         // System.out.println(elementsOfFile.get(1));
    }

  /*  public static void readFromFile1(String pathToFile) {
        ArrayList<String> elementsOfFile = new ArrayList<String>();
        try {// читаем построчно
            Files.lines(Paths.get(pathToFile), StandardCharsets.UTF_8).forEach(value -> elementsOfFile.add(value));
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        //  elementsOfFile.forEach(value -> System.out.println(value));
        System.out.println(elementsOfFile.get(1));
    }*/
    public void writeToFile() {
       try(FileWriter writer = new FileWriter(pathToFile, false))
        {
            // запись всей строки
            writer.write(stringData);
            writer.append('\n');
            writer.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
       /* try {
            Files.write(Paths.get(pathToFile), stringData.getBytes(), StandardOpenOption.APPEND);
        }
        catch (IOException e) {
            System.out.println(e);
        }*/
    }
}
