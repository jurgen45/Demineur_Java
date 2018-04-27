import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class test {
    public static void main(String[] args) {
        try {
            FileInputStream file = new FileInputStream("Score.dat");
            DataInputStream flux1 = new DataInputStream(file);
            while (flux1.available()>4) {
                int ligne = flux1.readInt();
                int colonne = flux1.readInt();
                int nbmine = flux1.readInt();
                int temps_m = flux1.readInt();
                int temps_s = flux1.readInt();
                System.out.println("ligne:"+ligne+" colonne:"+colonne+" nbmine:"+nbmine+"\ntemps:"+temps_s+":"+temps_m);
            }
        } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }
    }
}