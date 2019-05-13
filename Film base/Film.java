package JavaClasswork.Lesson1.Lesson5;

import java.io.Serializable;

public class Film implements Serializable {
    private String yearCreation;
    private String title;
    private String genre;

    Film(){
        this.yearCreation="";
        this.title="";
        this.genre="";
    }

    Film(String str) throws IncorrectDataException {
        try{
            String data[]=str.split(" ");
            if(data.length<3)
                throw new IncorrectDataException("No correct data of film");
            this.yearCreation=data[0];
            this.title="";
            for (int i = 1; i <data.length-2 ; i++) {
                this.title+=data[i]+" ";
            }
            this.title+=data[data.length-2];
            this.genre=data[data.length-1];}
        catch(Exception e){
            throw new IncorrectDataException("No correct data of film");
        }
        if( !checkСorrectString(genre) || !checkCorrectStringInt(yearCreation))
            throw  new IncorrectDataException("No correct data of film");
    }

    public int getYearCreation(){
        return Integer.parseInt(yearCreation);
    }

    private boolean checkСorrectString(String str) {
        return (str.matches("[а-яА-Я]+") || str.matches("[a-zA-ЯZ]+"));
    }

    private boolean checkCorrectStringInt(String str){
        return(str.matches("[0-9][0-9][0-9][0-9]"));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;

        if(this == obj) return true;

        if (this.getClass() != obj.getClass()) return false;

        if(super.equals(obj)) return true;

        Film other = (Film) obj;
        return  (this.genre.equals(other.genre)) && (this.title.equals(other.title)) && (this.yearCreation.equals(other.yearCreation));
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public String getYear(){
        return yearCreation;
    }

}
