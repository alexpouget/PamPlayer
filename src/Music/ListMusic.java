package music;

            import com.fasterxml.jackson.core.JsonProcessingException;
            import com.fasterxml.jackson.core.type.TypeReference;
            import com.fasterxml.jackson.databind.ObjectMapper;
            import com.fasterxml.jackson.databind.JsonMappingException;
            import com.fasterxml.jackson.databind.ObjectMapper;
            import java.io.*;
            import java.util.ArrayList;

/**
 * Created by alex on 11/06/2015.
 */

    public class ListMusic implements Serializable{

    ArrayList<Music> list;


    public ListMusic() {
        ObjectMapper mapper = new ObjectMapper();
        list = new ArrayList<Music>();
        try {
            StringBuffer fileData = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader("Ressource/listJson.txt"));
            char[] buf = new char[1024];
            int numRead = 0;
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            String[] v = fileData.toString().split(":");
            for(int i = 0;i<v.length;i++){
                System.out.println(v[i]);
            }
            //ListMusic p = mapper.readValue(fileData.toString(), ListMusic.class);new TypeReference<ArrayList<T>>() {}
            //ArrayList<Music> p = mapper.readValue(fileData.toString(), ArrayList.class);
            list = mapper.readValue(fileData.toString(), new TypeReference<ArrayList<Music>>() {});
            for (Music m : list){
                System.out.println(m.getTitle());
            }
            //System.out.println(p.toString());
            //list.addAll(p);

            /*FileInputStream fis = new FileInputStream("Ressource/list.txt");
            ObjectInputStream objectInputStream = new ObjectInputStream(fis);
            int i = 0;
            list = new ArrayList<Music>();
            while (fis.available() > i) {
                Music objet = (Music) objectInputStream.readObject();
                System.out.println(objet);
                System.out.println(objet.getPath());
                list.add(objet);
            }
        objectInputStream.close();*/
        }catch(JsonProcessingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



    public ListMusic(String fichier) {
        list = new ArrayList<Music>();
        File filePlaylist= new File("Ressource/"+fichier);
     
    }

    public ArrayList<Music> getList() {
        return list;
    }
    public void addMusic(Music music){
        list.add(music);
        this.update();
    }

    public void removeMusic(Music music){
        for(int i = 0;list.size()>i;i++){
            if(((Music)list.get(i)).getTitle().compareToIgnoreCase(music.getTitle())==0){
                list.remove(i);
                this.update();

            }
        }
    }

    public void update(){

        ObjectMapper mapper = new ObjectMapper();
        FileWriter f = null;
        try {
            f = new FileWriter("Ressource/listJson.txt");
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String s ="";
        try {

            s = mapper.writeValueAsString(list);

            f.write(s);
            f.flush();
            f.close();
        } catch (JsonProcessingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void addMusic(Music music, String chemin){
        list.add(music);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(chemin);
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
