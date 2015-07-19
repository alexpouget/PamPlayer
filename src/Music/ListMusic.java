package music;

            import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import graphique.MyWindow;

import java.io.*;
import java.util.ArrayList;

/**
 * Created by alex on 11/06/2015.
 *
 * classe ListMusic crée pour implementer les methodes de gestion de sauvegarde des objets music
 */

    public class ListMusic implements Serializable{

    ArrayList<Music> list;

    /*constructeur de listmusic qui charge le fichier par default listJson et rempli la liste qui sera par la suite
    *afficher dans la bibliotheque
    */
    public ListMusic() {
        //objectmapper est un object de l'API Jackson il permet  de transformer l'objet en json et inversement
        ObjectMapper mapper = new ObjectMapper();
        list = new ArrayList<Music>();
        try {
            //on ouvre le fichier
            StringBuffer fileData = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader("Ressource/listJson.txt"));
            char[] buf = new char[1024];
            int numRead = 0;
            //on lis le fichier et le transmet dans la variable filedata
            while ((numRead = reader.read(buf)) != -1) {
                String readData = String.valueOf(buf, 0, numRead);
                fileData.append(readData);
            }
            reader.close();
            /*on utilise le mapper pour remplir la list en donnant le string du json ainsi que le
            *type de classe dans lequel on le cast !! important il faut obligatoirement definir un
            *constructeur vide pour cette classe
            */
            list = mapper.readValue(fileData.toString(), new TypeReference<ArrayList<Music>>() {});
        }catch(JsonProcessingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // constructeur pour playlist avec possibilité du choix du fichier d'enregistrement
    public ListMusic(String chemin) {
        list = new ArrayList<Music>();
        File filePlaylist= new File(chemin);
     
    }
    
    // constructeur pour charger une playlist dans la jtable
    public ListMusic(String chemin, boolean isExisting) {
        ObjectMapper mapper = new ObjectMapper();
        list = new ArrayList<Music>();
        try {
            StringBuffer fileData = new StringBuffer();
            BufferedReader reader = new BufferedReader(new FileReader(chemin));
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
            list = mapper.readValue(fileData.toString(), new TypeReference<ArrayList<Music>>() {});
            for (Music m : list){
                MyWindow.persoTablePlaylist.addMusic(m);
            }
        }catch(JsonProcessingException e){
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Music> getList() {
        return list;
    }

    //method d'ajout de musique dans la list
    public void addMusic(Music music){
        list.add(music);
        /*apres avoir ajouter la musique a la list on utilise la method
        * update pour reecrire le fichier de sauvegarde
        */
        this.update();
    }

    //method de suppresion de musique dans la list
    public void removeMusic(Music music){
        for(int i = 0;list.size()>i;i++){
            if(((Music)list.get(i)).getTitle().compareToIgnoreCase(music.getTitle())==0){
                list.remove(i);
                this.update();
            }
        }
    }

    //methode d'enregistrement de la listMusic dans le fichier listJson
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
            //on transforme l'objet ArrayList en string json grace a mapper
            s = mapper.writeValueAsString(list);
            //on ecrit le string json obtenue dans le fichier
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

    // méthode pour update le fichier json de la playlist
    public void update(String chemin){

        ObjectMapper mapper = new ObjectMapper();
        FileWriter f = null;
        try {
            f = new FileWriter(chemin);
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
    
    // méthode pour ajouter des musiques a la liste musique pour playlist
    public void addMusic(Music music, String fichier){
        list.add(music);
        this.update(fichier);


    }

    // method pour verifier que la musique n'est pas deja presente dans la liste avant de l'ajouter
    //comparaison seulement sur le titre
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
