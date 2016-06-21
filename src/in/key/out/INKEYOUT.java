/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package in.key.out;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

/**
 *
 * @author ua053903 утилита поиска строк по ключам Версия 1.0 Время создания
 * 12:02 09.01.2015
 */
public class INKEYOUT {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

        String line;
        //BufferedReader br = null;
        ArrayList<String> IN, KEY;
        IN = new ArrayList();
        KEY = new ArrayList();
        //Имена файлов для обработки
        String FlIn = "";
        String FlOut = "";
        String FlKey = "";
        int Count = 0;

        //Опредиление структуры параметров командной строки
        switch (args.length) {
            case 2:
                FlIn = args[0];
                FlKey = args[1];
                FlOut = "OUT.txt";
                break;
            case 3:
                FlIn = args[0];
                FlKey = args[1];
                FlOut = args[2];
                break;
            default:
                System.out.println("Info: Need two or three arguments INPUT file, KEY file, OUTPUT file");
                System.out.println("Example: java.exe -jar INKEYOUT.jar IN.txt KEY.txt");
                System.out.println("Example: java.exe -jar INKEYOUT.jar IN.txt KEY.txt OUT.txt");
                System.exit(1);
        }

        //IN.txt
        //проверим существование исходного файла
        File f = new File(FlIn);
        if (!f.exists()) {
            System.out.println("ERROR, file - " + FlIn + " not found");
            System.exit(1);
        }

        //KEY.txt
        //проверим существование файла ключей поиска
        f = new File(FlKey);
        if (!f.exists()) {
            System.out.println("ERROR, file - " + FlKey + " not found");
            System.exit(1);
        }

        //OutFileName
        //проверим существование выходного файла, если уже существует, изменим имя
        f = new File(FlOut);
        //if (args.length == 1 && f.exists()) {
        while (f.exists()) {
            Calendar calendar = Calendar.getInstance();
            FlOut = String.format(FlIn.replaceFirst("[.][^.]+$", "") + "_%1$tY%1$tm%1$te-%1$tH%1$tM%1$tS.txt", calendar);
            f = new File(FlOut);
        }

        //---------------------------------------------------
        // Прочитаем все строки в ArrayList файла IN
        try (BufferedReader br = new BufferedReader(new FileReader(FlIn));) {
                      
            while ((line = br.readLine()) != null) {

                IN.add(line);
                //System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("FileNotFoundException - " + e);
            System.exit(1);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException - " + e);
        }

        //---------------------------------------------------
        // Прочитаем все строки в ArrayList файла KEY
        try (BufferedReader br = new BufferedReader(new FileReader(FlKey))) {
            //br = new BufferedReader(new FileReader(".\\KEY.txt"));
            
            while ((line = br.readLine()) != null) {

                KEY.add(line);
                //System.out.println(line);
            }
        } catch (FileNotFoundException e) {
            //e.printStackTrace();
            System.out.println("FileNotFoundException - " + e);
            System.exit(1);
        } catch (IOException e) {
            //e.printStackTrace();
            System.out.println("IOException - " + e);
        } 

        //---------------------------------------------------
        // Сравним все строки IN на ниличие строк KEY
        //try (PrintWriter pw = new PrintWriter(new File(".\\OUT.txt"), "Cp1251")) {
        try (PrintWriter pw = new PrintWriter(new File(FlOut))) {
            for (String INstr : IN) {

                for (String KEYstr : KEY) {

                    //System.out.println(KEYstr);
                    if (INstr.contains(KEYstr)) {
                        
                        //System.out.println(KEYstr);
                        //System.out.println(INstr);
                        pw.println(INstr);
                        Count++;
                    }
                }

            }
        } catch (Exception e) {
            System.out.println("Exception - " + e.getMessage());
            System.exit(1);
        }
        
        System.out.println("In file " + FlIn + " found " + Count + " matches, result to file " + FlOut);
        System.exit(0);
    }
}
