package JavaClasswork.Lesson1.Lesson5;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TestMap {
    public static void main(String[] args) throws IncorrectDataException, IOException, ClassNotFoundException {
        Scanner input=new Scanner(System.in);
        Map<Integer,Film> database=new Map<>();
        int choice;
        Film film;
        boolean flag=true;
        while (flag) {
            System.out.println();
            System.out.println("1 - добавить новый элемент");
            System.out.println("2 - удалить элемент");
            System.out.println("3 - существует ли данный фильм в бд?");
            System.out.println("4 - вывести текущую базу данных");
            System.out.println("0 - выйти из меню");
            choice = input.nextInt();
            System.out.println();
            switch (choice){
                case 1:
                    System.out.print("Введите новые данные (1994 Побег из Шоушенка драма):");
                    System.out.println();
                    String data=input.nextLine();
                    data=input.nextLine();
                    try {
                        film = new Film(data);
                        database.put(film.getYearCreation(), film);
                    }catch (IncorrectDataException e){
                        System.out.println("Неправильно введенные данные");
                    }
                    break;
                case 2:
                    System.out.println("1 - удалить фильм(ы) по году выпуска \n2 - удалить определнный элемент");
                    switch (input.nextInt()){
                        case 1:
                            System.out.print("Введите год : ");
                            try {
                                int year = input.nextInt();
                                database.remove(year);
                            }catch (InputMismatchException e){
                                System.out.println("Неправильно введенные данные");
                            }
                            break;
                        case 2:
                            System.out.print("Введите данные (1994 Побег из Шоушенка драма) : ");
                            data=input.nextLine();
                            data=input.nextLine();
                            film=new Film(data);
                            database.remove(film.getYearCreation(),film);
                    }
                    break;
                case 3:
                    System.out.println("1 - есть ли фильмы определенного года выпуска? \n2 - есть ли данный фильм?");
                    switch (input.nextInt()){
                        case 1:
                            System.out.print("Введите год : ");
                            int year=input.nextInt();
                            if(database.containsKey(year))
                                System.out.println("Да, есть");
                            else
                                System.out.println("Нет");
                            break;
                        case 2:
                            System.out.println("Введите данные (1994 Побег из Шоушенка драма) : ");
                            data=input.nextLine();
                            data=input.nextLine();
                            film=new Film(data);
                            if(database.containsValue(film))
                                System.out.println("Да, есть");
                            else
                                System.out.println("Нет");
                            break;
                    }
                    break;
                case 4:
                    ArrayList<Film> temp=new ArrayList<>();
                    temp= (ArrayList<Film>) database.getDatabase();
                    System.out.println();
                    for (int i = 0; i <temp.size() ; i++) {
                        System.out.println(temp.get(i).getYear()+" "+temp.get(i).getTitle()+" "+temp.get(i).getGenre());
                    }
                    break;
                case 0:
                    input.close();
                    flag=false;
                    break;
                 default:
                     System.out.println("Некорректные данные, введите еще раз");
                     break;
            }
        }
    }
}
