package music;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by alex on 11/06/2015.
 */
public class ListMusic {
    ArrayList<Music> list;

    public ListMusic() {
        list = new ArrayList<Music>();
        try {
            FileInputStream fis = new FileInputStream("Ressource/list.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            int i = 0;
            list = new ArrayList<Music>();
            while(fis.available()>i) {
                Music objet = (Music) objectInputStream.readObject();
                System.out.println(objet);
                System.out.println(objet.getPath());
                list.add(objet);
            }
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Music> getList() {
        return list;
    }
    public void addMusic(Music music){
        list.add(music);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream("Ressource/list.txt");
            ObjectOutputStream ois = new ObjectOutputStream(fileOutputStream);
            int i = 0;
            while(list.size()>i) {
                ois.writeObject(list.get(i));
                i++;
            }
            ois.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public boolean present(Music music){
        for(int i = 0;i<list.size();i++){
            if(list.get(i).equals(music)){
                return true;
            }
        }
        return false;
    }
    public void setList(ArrayList<Music> list) {
        this.list = list;
    }
}
