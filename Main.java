import java.io.*;
import java.awt.*;
import javax.swing.*;

/**
 * Lance le programme en prenant en compte la sauvegarde pour les menus
 *  @version 1
 * @author Nathan Bertholier
 * @author Jürgen Hablainville
 */
public class Main {
    public static void main(String[] args) { 
        int indice=0;
        /**
     * System de lecture de fichier qui permet de détecter qu'ne partie est en sauvegarde si "indice" different de -1 
     * (on écrit -1 dans le fichier quand la partie est fini) 
     */
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
              /**
               * Menu lance le menu avec Nouvelle partie,reprendre la partie et quitter
               */
               Menu m=new Menu();
              m.affiche();
              m.setLocation(200, 100);
            }
            else{
             /**
               * Menu2 lance le menu avec Nouvelle partie et quitter
               */
                 Menu2 m2 = new Menu2();
                m2.affiche();
                m2.setLocation(200, 100);
            }
       
    }

}
