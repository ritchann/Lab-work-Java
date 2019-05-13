package JavaClasswork.Lesson1.Lesson4;

import java.util.Scanner;

public class Test {
    public static void main(String[] args)  {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter the surname, name, patronymic, and date of birth(Surname Name Patronomic dd.mm.yyyy) : ");
        String str=in.nextLine();
        Data data;
        try{
            data=new Data(str);
            data.transformation();
            System.out.println(data);
        }catch (IncorrectDataException e) {
            System.out.println(e.getMessage());
        }
    }
}

