package JavaClasswork.Lesson1.Lesson5;

import java.io.*;
import java.util.*;
import java.util.Map;

public class Database {

    private TreeMap<Integer,ArrayList<Film>> data=new TreeMap<>();
    private FileOutputStream fos=null;
    private ObjectOutputStream out=null;
    private FileInputStream fis;
    private ObjectInputStream in;

    public Database() throws IOException {
        fos=new FileOutputStream("a.txt",false) ;
        out=new ObjectOutputStream(fos);
        fis=new FileInputStream("a.txt");
        in=new ObjectInputStream(fis);
    }
    public void add(Film val) throws IOException {
        ArrayList<Film> temp=new ArrayList<>();
        if(!data.containsKey(val.getYearCreation())){
            temp.add(val);
            data.put(val.getYearCreation(),temp);
        }
        else{
            data.get(val.getYearCreation()).add(val);
        }

        updateFile();
    }

    private void updateFile() throws IOException {
        updateOut();
        for(Map.Entry<Integer, ArrayList<Film>> item:data.entrySet()) {
            for (int i = 0; i <item.getValue().size() ; i++) {
                out.writeObject(item.getValue().get(i));
            }
        }
    }
    public List<Film> getDatabase() throws IOException, ClassNotFoundException {
        updateIN();
        ArrayList<Film> temp=new ArrayList<>();
        for(Map.Entry<Integer, ArrayList<Film>> item:data.entrySet()){
            for (int i = 0; i < item.getValue().size(); i++) {
                temp.add((Film) in.readObject());
            }
        }
        return temp;
    }
    private void  updateOut() throws IOException {
        out.close();
        fos=new FileOutputStream("a.txt",false) ;
        out=new ObjectOutputStream(fos);
    }
    private void updateIN() throws IOException {
        in.close();
        fis=new FileInputStream("a.txt");
        in=new ObjectInputStream(fis);
    }

    public boolean removeObject(Film val) throws IOException {
        for(Map.Entry<Integer, ArrayList<Film>> item:data.entrySet()) {
            for (int j = 0; j <item.getValue().size() ; j++) {
                if (item.getValue().get(j).equals(val)) {
                    if(item.getValue().size()==1){
                        data.remove(item.getKey());
                    }
                    else {
                        data.get(item.getKey()).remove(j);
                    }
                    updateFile();
                    return true;
                }
            }
        }
        updateFile();
        return false;
    }

    public boolean removeKey(int key) throws IOException {
        if(data.containsKey(key)) {
            data.remove(key );
            updateFile();
            return true;
        }
        return false;
    }

    public boolean containsObject(Film val) throws IOException, ClassNotFoundException {
        ArrayList<Film> temp=new ArrayList<>(getDatabase());
        for (int i = 0; i <temp.size() ; i++) {
            if(temp.get(i).equals(val))
                return true;
        }
        return false;
    }

    public boolean containsKey(int key){
        return data.containsKey(key);
    }

    public void printDatabase() throws IOException, ClassNotFoundException {
        ArrayList<Film> temp=new ArrayList<>();
        temp= (ArrayList<Film>) getDatabase();
        System.out.println();
        for (int i = 0; i <temp.size() ; i++) {
            System.out.println(temp.get(i).getYear()+" "+temp.get(i).getTitle()+" "+temp.get(i).getGenre());
        }
    }
}
