import javax.swing.*;
import java.awt.*;
import java.io.*;
public class Case extends JButton{
    private boolean mine;
    private boolean valide;
    private int nb;
    private int etat;

public Case(boolean etatmine)
{
    super();
    this.mine=etatmine;
    this.valide=false;
    this.nb = 0;
    this.etat=0;
    

}
public boolean etatMine()
{
    return this.mine;
}


public void setValide()
{
    this.valide=true;
}

public void setDeValide() {
     this.valide = false;
}

public boolean getValide() {
    return this.valide;
}
public void setNb()
{
    this.nb++;
}

public void setNbtoFile(int n) {
      this.nb=n;
}

public void setEtat()
{
    this.etat++;
    if (this.etat>2) {
        this.etat=0;
    }
}

public void setEtattoFile(int n) {
    this.etat=n;
    
 }
public int getEtat()
{
    return this.etat;
}

public int getNb() {
    return this.nb;
}

public String getNbStr() {
    int k= this.nb;
      return ""+k;
}
        
    
   
public void sauvegarde(Case[][] tabCase,int ligne,int colonne,int mine1,int marqueurs){

        try {

            FileOutputStream fichier = new FileOutputStream("save.txt");
            DataOutputStream flux = new DataOutputStream(fichier);
            flux.writeInt(ligne);
            flux.writeInt(colonne);
            flux.writeInt(mine1);

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
            flux.close();
        } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }

}
}
