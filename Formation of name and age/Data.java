package JavaClasswork.Lesson1.Lesson4;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;

public class Data {

    private String name="";
    private String surname="";
    private String patronymic="";
    private String dateOfBirth="";
    private String fullName="";
    private String sex="";
    private int age=0;

    public Data(){
    }

    public Data(String str) throws IncorrectDataException{
       try{
           String data[]=str.split(" ");
            this.name=data[1];
            this.surname=data[0];
            this.patronymic=data[2];
            this.dateOfBirth=data[3];}
        catch(Exception e){
           throw new IncorrectDataException("No correct data of person");
        }
        if(!checkСorrectString(name)||!checkСorrectString(surname)|| !checkСorrectString(patronymic)||!checkCorrectStringDate(dateOfBirth)) {
            throw new IncorrectDataException("No correct data of person");
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFullName() {
        return fullName;
    }
    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    private void definefullName() {
        String tmpName=name.toUpperCase();
        String tmpPatronymic=patronymic.toUpperCase();
        fullName+=(surname+" "+tmpName.charAt(0)+"."+tmpPatronymic.charAt(0)+".");
    }

    private void defineSex() throws IncorrectDataException {
        if (patronymic.endsWith("ич")) {
            sex = "муж.";
        } else {
            if (patronymic.endsWith("на")) {
                sex = "жен.";
            } else {
                throw new  IncorrectDataException("No correct patronymic of person");
            }
        }
    }

    private void defineAge() throws IncorrectDataException {
        String dateMas[]=dateOfBirth.split("\\.");
        int day=Integer.parseInt(dateMas[0]);
        int month=Integer.parseInt(dateMas[1]);
        int year=Integer.parseInt(dateMas[2]);
        try{LocalDate now=LocalDate.now();
        LocalDate born=LocalDate.of(year,month,day);
        age = now.getYear() - born.getYear();
        if (now.getDayOfYear() < born.getDayOfYear()) {
            age -= 1;
        }
        }catch (Exception e){
            throw new IncorrectDataException("Incorrect date of birth");
        }
        if (age<0){
            throw new IncorrectDataException("Incorrect date of birth");
        }
    }

    public void transformation() throws IncorrectDataException {
       definefullName();
       defineSex();
       defineAge();
    }

    private boolean checkСorrectString(String name) {
        return (name.matches("[а-яА-Я]+") || name.matches("[a-zA-ЯZ]+"));
    }

    private boolean checkCorrectStringDate(String date){

        return(date.matches("[0-9][0-9][.][0-9][0-9][.][0-9][0-9][0-9][0-9]"));
    }

    @Override
    public String toString() {
        return  fullName + " пол " + sex + " " + age +" лет";
    }
}
