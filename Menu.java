import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
/**
* On initialise le menu avec Jouer, charger une partie et quitter
*/
public class Menu extends JFrame implements MouseListener{
    public Dessin des = new Dessin();
    public void affiche(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(des);
        this.setSize(510,180);
        this.setVisible(true);
        des.paintComponent(this.getGraphics());
        this.addMouseListener(this);
    }
    
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseClicked (MouseEvent e){
        if (e.getX()>10 && e.getX()<150 && e.getButton() == MouseEvent.BUTTON1) {
            /**
             * Si nouvelle partie est cliquée alors on lance le panneau de reglage
             */
            this.dispose();
            Reglage visu = new Reglage();          
            
        }
        if (e.getX()>200 && e.getX()<340 && e.getButton() == MouseEvent.BUTTON1) {
            /**
             * Si on charge une partie, on lit les donnée sauvegardé
             */
            try{
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                int nbl = flux1.readInt();
                int nbc = flux1.readInt();
                int nbm = flux1.readInt();
                this.dispose();
                System.out.println("run");
                Demineur jeu = new Demineur(nbl,nbc,nbm,true);
            }
            catch(FileNotFoundException ex){
              System.err.println("fichier non trouvé: lecture");
            }
            catch(IOException ex){
              System.out.println("il y'a une erreur: lecture");
            }
        }
        if (e.getX()>390 && e.getButton() == MouseEvent.BUTTON1) {
            this.dispose();
            System.exit(0);
        }

    }
}