package HW_3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) {
      String arg = inputStringFromUser();
      ArrayList<String> people = new ArrayList<String>();
      try {
        people = turnStringToArrayWithCheckData(arg);
        String path = "HW_3\\" + people.get(0) + ".txt";
        writeInFile(people, path);
      } catch (RuntimeException e) {
        System.out.println(e.getMessage());
      }
      System.out.println(people);
    }
  
    public static ArrayList<String> turnStringToArrayWithCheckData(String str) throws RuntimeException {
      ArrayList<String> temp = new ArrayList<String>();
      String phoneString = "";
      String birthDate = "";
      String gender = "";
      for (String string : str.split(" ")) {
        if (isNumber(string)) {
          phoneString = string;
          continue;
        }
        if (string.equals("f") || string.equals("m")) {
          gender = string;
          continue;
        }
        if (isDate(string)) {
          birthDate = string;
          continue;
        }
        temp.add(string);
      }
      if (temp.get(0).isEmpty() || temp.get(1).isEmpty() || temp.get(2).isEmpty()) {
        throw new RuntimeException("Ф.И.О введены не полностью ");
      }
      if (!phoneString.isEmpty()) {
        temp.add(phoneString);
      } else {
        throw new RuntimeException("Не указан номер телефона ");
      }
      if (!birthDate.isEmpty() & checkDate(birthDate)) {
        temp.add(birthDate);
      } else {
        throw new RuntimeException("Не указана дата рождения ");
      }
      if (!gender.isEmpty()) {
        temp.add(gender);
      } else {
        throw new RuntimeException("Не указан пол ");
      }
      return temp;
    }
  
    public static String inputStringFromUser() {
      String temp = "";
      try (Scanner cs = new Scanner(System.in)) {
        temp = cs.nextLine();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
      return temp;
    }
  
    public static void writeInFile(ArrayList<String> array, String path) {
      String info = "";
      File file = new File(path);
      try (BufferedWriter out = new BufferedWriter(new FileWriter(path, file.exists()))) {
        for (String strings : array) {
          info += "<" + strings + ">";
        }
        out.write(info + "\n");
        out.close();
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
    }
  
    public static boolean isNumber(String str) {
      String string = "0123456789";
      for (char elem : str.toCharArray()) {
        if (!string.contains(String.valueOf(elem))) {
          return false;
        }
      }
      return true;
    }
  
    public static boolean isDate(String str) {
      String string = "0123456789,./";
      for (char elem : str.toCharArray()) {
        if (!string.contains(String.valueOf(elem))) {
          return false;
        }
      }
      return true;
    }
  
    public static boolean checkDate(String str) {
      Date currentDate = new Date();
      try {
        Date D2 = new SimpleDateFormat("dd.MM.yyyy").parse(str);
        long duration = currentDate.getTime() - D2.getTime();
        long diffInYears = TimeUnit.MILLISECONDS.toDays(duration);
        if (diffInYears < 0) {
          System.out.println("Дата еще не наступила ");
          return false;
        }
        if (diffInYears / 365 > 100) {
          System.out.println("Приветствуем Вас ");
        }
      } catch (Exception e) {
        System.out.println("Некорректный формат даты ");
      }
      return true;
    }
    
}
