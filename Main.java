import java.io.*;
import java.awt.*;
import javax.swing.*;
public class Main {
    public static void main(String[] args) { 
        int indice=0;
        
        try{
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                indice = flux1.readInt();
                
                flux1.close();
            }
            catch(FileNotFoundException ex){
              System.err.println("fichier non trouvé: lecture");
            }
            catch(IOException ex){
              System.out.println("il y'a une erreur: lecture");
            }
            if (indice>0) {
               Menu m=new Menu();
              m.affiche();
              m.setLocation(200, 100);
            }
            else{
             
                 Menu2 m2 = new Menu2();
                m2.affiche();
                m2.setLocation(200, 100);
            }
       
    }

}
