package JavaClasswork.Lesson1.Lesson5;

import javafx.util.Pair;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.io.*;
import java.util.*;

public class Map<Integer,Film> implements java.util.Map<Integer, Film> {

    ArrayList<Pair<Integer,ArrayList<Film>>> value=new ArrayList<>();
    FileOutputStream fos=null;
    ObjectOutputStream out=null;
    FileInputStream fis;
    ObjectInputStream in;

    Map(){
        try {
            fos=new FileOutputStream("a.txt",false) ;
            out=new ObjectOutputStream(fos);
            fis=new FileInputStream("a.txt");
            in=new ObjectInputStream(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return value.size();
    }

    @Override
    public boolean isEmpty() {
        return size()==0;
    }

    @Override
    public boolean containsKey(Object key) {
        for (int i = 0; i <value.size() ; i++) {
            if(value.get(i).getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object val) {
        for (int i = 0; i < value.size(); i++) {
            for (int j = 0; j <value.get(i).getValue().size() ; j++) {
                if(value.get(i).getValue().get(j).equals(val))
                    return true;
            }
        }
        return  false;
    }

    @Override
    public Film get(Object key) {
        throw new NotImplementedException();
    }

    @Override
    public Film put(Integer key, Film val) {
        ArrayList<Film> temp=new ArrayList<>();

        if(!containsKey(key)){
            temp.add(val);
            Pair<Integer,ArrayList<Film>> pair=new Pair<>(key,temp);
            value.add(size(),pair);
            value.sort( (s1, s2) ->{
                if (s1.getKey().hashCode()<s2.getKey().hashCode())
                    return -1;
                else
                    return 1;
            });
            try {
                updateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
        else {
            for (int i = 0; i <value.size() ; i++) {
                if(value.get(i).getKey().equals(key))
                    value.get(i).getValue().add(val);
            }
            value.sort( (s1, s2) ->{
                if (s1.getKey().hashCode()<s2.getKey().hashCode())
                    return -1;
                else
                    return 1;
            });
            try {
                updateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return val;
        }
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

    private void updateFile() throws IOException {
        updateOut();
        for (int i = 0; i <value.size() ; i++) {
            for (int j = 0; j <value.get(i).getValue().size() ; j++) {
                out.writeObject(value.get(i).getValue().get(j));
            }
        }
    }

    public List<Film> getDatabase() throws IOException, ClassNotFoundException {
        updateIN();
        ArrayList<Film> temp=new ArrayList<>();
        for (int i = 0; i <value.size() ; i++) {
            for (int j = 0; j <value.get(i).getValue().size() ; j++) {
                temp.add((Film) in.readObject());
            }
        }
        return temp;
    }
    @Override
    public Film remove(Object key) {
        Film ret = null;
        if(containsKey(key)) {
            for (int i = 0; i <value.size() ; i++) {
                if(value.get(i).getKey().equals(key))
                    ret= (Film) value.remove(i);
            }
            try {
                updateFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return  ret;
    }


    @Override
    public void putAll(java.util.Map<? extends Integer, ? extends Film> m) {
        throw new NotImplementedException();
    }

    @Override
    public void clear() {
        throw new NotImplementedException();
    }

    @Override
    public Set<Integer> keySet() {
        throw new NotImplementedException();
    }

    @Override
    public Collection<Film> values() {
        throw new NotImplementedException();
    }

    @Override
    public Set<Entry<Integer, Film>> entrySet() {
        throw new NotImplementedException();
    }

    @Override
    public boolean remove(Object key, Object val) {
        for (int i = 0; i <value.size() ; i++) {
            for (int j = 0; j <value.get(i).getValue().size() ; j++) {
                if(value.get(i).getValue().get(j).equals(val)){
                    if(value.get(i).getValue().size()==1){
                        value.remove(i);
                    }
                    else{
                        value.get(i).getValue().remove(j);
                    }
                    try {
                        updateFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return true;
                }
            }
        }
        try {
            updateFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
