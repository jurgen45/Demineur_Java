import javax.swing.*;
import java.awt.*;
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
    int k= this.nb+1;
      return ""+k;
}
public void detectionRecursive(Case [][] tabCase,int x,int y,int ligne,int colonne)
        {
            
            

        if (y == ligne - 1 && x == colonne - 1) {
            System.out.println("angle bas droite");
            for (int k = x - 1; k < x + 1; k++) {
                for (int g = y - 1; g < y + 1; g++) {
                    if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                        System.out.println("mine");
                        tabCase[y][x].setText(tabCase[y][x].getNbStr());
                        tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                        tabCase[y][x].setBackground(Color.WHITE);
                        return;
                    }else{
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                        tabCase[y][x].detectionRecursive(tabCase,g,k,ligne,colonne);

                    }
                }
            }
        } else if (y == 0 && x == 0) {
            System.out.println("angle haut gauche");
            for (int k = x; k < x + 2; k++) {
                for (int g = y; g < y + 2; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }
                }
            }
        } else if (x == 0 && y == ligne - 1) {
            System.out.println("angle haut droite");
            for (int k = x; k < x + 2; k++) {
                for (int g = y - 1; g < y + 1; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }
                }
            }
        } else if (y == 0 && x == colonne - 1) {
            System.out.println("angle bas gauche");
            for (int k = x - 1; k < x + 1; k++) {
                for (int g = y; g < y + 2; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }
                }
            }
        } else if (y == 0) {
            System.out.println("Ligne de gauche");
            for (int k = x - 1; k < x + 2; k++) {
                for (int g = y; g < y + 2; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }

                }
            }
        } else if (x == 0) {
            System.out.println("Ligne de haut");
            for (int k = x; k < x + 2; k++) {
                for (int g = y - 1; g < y + 2; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }

                }
            }
        } else if (x == colonne - 1) {
            System.out.println("Ligne du bas");
            for (int k = x - 1; k < x + 1; k++) {
                for (int g = y - 1; g < y + 2; g++) {
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }
                }
            }
        } else if (y == ligne - 1) {
            System.out.println("Ligne du bas");
            for (int k = x - 1; k < x + 2; k++) {
                for (int g = y - 1; g < y + 1; g++) {
                            if (tabCase[g][k].etatMine() == true && tabCase[g][k].getValide() == false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }
                }
            }
        } else {

            for (int k = x - 1; k < x + 2; k++) {
                for (int g = y - 1; g < y + 2; g++) {
                    System.out.println("position: " + g + "/" + k);
                            if (tabCase[g][k].etatMine() == true&&tabCase[g][k].getValide()==false) {
                                System.out.println("mine");
                                tabCase[y][x].setText(tabCase[y][x].getNbStr());
                                tabCase[y][x].setNb();
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                return;
                            } else {
                                tabCase[y][x].setValide();
                                tabCase[y][x].setBackground(Color.WHITE);
                                tabCase[y][x].detectionRecursive(tabCase, g, k, ligne, colonne);

                            }

                }
            }
        }

        
    
}
}