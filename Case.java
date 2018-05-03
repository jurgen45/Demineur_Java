import javax.swing.*;
import java.awt.*;
import java.io.*;
/**
 * Case herite de JButton et ajoute des arguments
 */
public class Case extends JButton{

    private boolean mine;
    private boolean valide;
    private int nb;
    private int etat;
/**
 * permet de definir la case avec etatmine==true si on veut que la case soit minée
 * @param etatmine definit l'argument mine en la valeur d'etatmine
 */
public Case(boolean etatmine)
{
    super();
    this.mine=etatmine;
    this.valide=false;
    this.nb = 0;
    this.etat=0;
    

}
/**
 * renvoi la valeur de mine (true si mine presente)
 * @return valeur boolean de mine
 */
public boolean etatMine()
{
    return this.mine;
}

    /**
     * Definit la valeur de valide sur true
     */
public void setValide()
{
    this.valide=true;
}

    /**
     * Definit la valeur de valide sur fals
     */
public void setDeValide() {
     this.valide = false;
}

    /**
     * renvoi la valeur de de valide (true si case validé)
     * @return valeur boolean de valide
     */
public boolean getValide() {
    return this.valide;
}

    /**
     * Incremente la valeur de nb(nombre de mines aux alentour)
     */
public void setNb()
{
    this.nb++;
}

    /**
     * Determine la valeur de nb(nombre de mines aux alentour) par les donnes
     * du fichier de sauvegarde
     * 
     * @param n valeur a assigné a nb
     */
public void setNbtoFile(int n) {
      this.nb=n;
}

    /**
     * Incremente la valeur de etat(logo drapeau,"?" ou vide)
     */
public void setEtat()
{
    this.etat++;
    if (this.etat>2) {
        this.etat=0;
    }
}

    /**
     * Determine la valeur de etat(logo drapeau,"?" ou vide) par les donnes du fichier
     * de sauvegarde
     * 
     * @param n valeur a assigné a etat
     */
public void setEtattoFile(int n) {
    this.etat=n;
    
 }
 
    /**
     * Donne la valeur de etat(logo drapeau,"? ou vide")
     * 
     * @return etat valeur de etat(logo drapeau,"?"" ou vide)
     */
public int getEtat()
{
    return this.etat;
}

    /**
     * Donne la valeur de nb(nombre de mines aux alentour)
     * 
     * @return nb valeur de nb(nombre de mines aux alentour)
     */
public int getNb() {
    return this.nb;
}
/**
 * Donne au format Sting la valeur de nb
 * @return k valeur de k en String
 */
public String getNbStr() {
    int k= this.nb;
      return ""+k;
}
        
    
    /**
     * Sauvgarde dans le fichier les cases minée,les drapeaux,les "?" et les cases
     * deja tester
     * 
     * @param tabCase  est le tableau de cases de la partie
     * @param ligne    le nombre de ligne de la partie
     * @param colonne  le nombe de colonne de la partie
     * @param nbmine    nombre de mine dans la partie
     * @param marqueur valeur de etat(logo drapeau,"? ou vide")
     * @param sec nombre de seconde du timer
     * @param min nombre de minute du timer 
     */
public void sauvegarde(Case[][] tabCase, int ligne, int colonne, int nbmine, int marqueurs, int sec, int min){

        try {

            FileOutputStream fichier = new FileOutputStream("save.txt");
            DataOutputStream flux = new DataOutputStream(fichier);
            flux.writeInt(ligne);
            flux.writeInt(colonne);
            flux.writeInt(nbmine);

            for (int i = 0; i < ligne; i++) {
                for (int f = 0; f < colonne; f++) {
                    if (tabCase[f][i].etatMine() == true) {
                        flux.writeInt(1);
                    } else if (tabCase[f][i].etatMine() == false) {
                        flux.writeInt(0);
                    }
                }
            }
            for (int i = 0; i < ligne; i++) {
                for (int f = 0; f < colonne; f++) {
                    if (tabCase[f][i].getValide() == true) {
                        flux.writeInt(1);
                    } else if (tabCase[f][i].getValide() == false) {
                        flux.writeInt(0);
                    }
                    if (tabCase[f][i].getNb() != 0) {
                        flux.writeInt(tabCase[f][i].getNb());
                    } else {
                        flux.writeInt(0);
                    }

                }
            }
            for (int i = 0; i < ligne; i++) {
                for (int f = 0; f < colonne; f++) {
                    flux.writeInt(tabCase[f][i].getEtat());
                }
            }

            flux.writeInt(marqueurs);
            flux.writeInt(sec);
            flux.writeInt(min);
            flux.close();
        } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }

}

    /**
     * Ecrit dans Score.dat le score de chaque partie
     * @param ligne    le nombre de ligne de la partie
     * @param colonne  le nombe de colonne de la partie
     * @param nbmine    nombre de mine dans la partie
     * @param sec      nombre de seconde du timer
     * @param min      nombre de minute du timer
     */
public void ecritureTabScore(int sec, int min,int ligne,int colonne,int nbmine){
    /**
     * 
     */
try {
    
            FileOutputStream fichier = new FileOutputStream("Score.dat",true);
            DataOutputStream flux = new DataOutputStream(fichier);
            flux.writeInt(ligne);
            flux.writeInt(colonne);
            flux.writeInt(nbmine);
            flux.writeInt(sec);
            flux.writeInt(min);
            flux.close();
}catch(FileNotFoundException ex)
    {
        System.err.println("fichier non trouvé: ecriture");
    }catch(
    IOException ex)
    {
        System.out.println("il y'a une erreur: ecriture");
    }
}

public void lectureTabScore(int sec, int min,int aligne,int acolonne,int anbmine){
    /**
     * Lis dans Score.dat les scores correspondant au parametre de la partie
     */
    JOptionPane tbscore = new JOptionPane();
    int temps_m=0;
    int temps_s=0;
    String text=null;
    String textfinal="";
    int p1m=min;
    int p1s=sec;
   
        try {
            FileInputStream file = new FileInputStream("Score.dat");
            DataInputStream flux1 = new DataInputStream(file);
         System.out.println("Grille: " + aligne + "*" + acolonne + "\n" + anbmine + " mines");
         System.out.println("Votre Temps: "+min+":"+sec);
                
            while (flux1.available()>8) {
                
                int ligne = flux1.readInt();
                int colonne = flux1.readInt();
                int nbmine = flux1.readInt();
                temps_s = flux1.readInt();
                temps_m = flux1.readInt();
                if (aligne==ligne&&acolonne==colonne&&anbmine==nbmine) {
                    
                
                if(temps_m<=p1m&&temps_s<p1s)
                {
                p1m=temps_m;
                p1s=temps_s;
                }
                
   
                
            }
        }
            
            
            System.out.println(); 
            
            textfinal="Votre Temps: "+min+":"+sec+"\n1er: "+p1m+":"+p1s;
            
            tbscore.showMessageDialog(null, "Grille: " + aligne + "*" + acolonne + "\n" + anbmine + " mines\n"+textfinal, "Score", JOptionPane.INFORMATION_MESSAGE);

            flux1.close();
        } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }
}

}
