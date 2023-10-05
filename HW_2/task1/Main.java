package HW_2.task1;

import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    float a = inputFloatFromUser();
    System.out.println("Результат ввода " + a);

  }

  public static float inputFloatFromUser() {
    System.out.println("Введите число с плавоющей точкой ");
    String str = "";
    float temp = -1;
    try (Scanner sc = new Scanner(System.in);) {
      str = sc.nextLine();
      str = str.replace(",", ".");
      if (isNumber2(str)) {
        temp = Float.parseFloat(str);
      } else {
        System.out.println("Введены не корректные данные, повторите ввод ");
        temp = inputFloatFromUser();
      }
    } catch (Exception e) {
      System.out.println(e.getMessage());
      temp = inputFloatFromUser();
    }
    return temp;
  }

  public static boolean isNumber2(String str) {
    String string = "0123456789.,";
    for (char elem : str.toCharArray()) {
      if (!string.contains(String.valueOf(elem))) {
        return false;
      }
    }
    return true;

  }
}