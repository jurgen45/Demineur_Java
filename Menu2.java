import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Menu2 extends JFrame implements MouseListener{
    public Dessin2 des2 = new Dessin2();
    public int indice;
    public void affiche(){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(des2);
        this.setSize(510, 180);
        this.setVisible(true);
        des2.paintComponent(this.getGraphics());
        this.addMouseListener(this);
    }
    
    public void mousePressed(MouseEvent me){}
    public void mouseReleased(MouseEvent me){}
    public void mouseEntered(MouseEvent me){}
    public void mouseExited(MouseEvent me){}
    public void mouseClicked (MouseEvent e){
       
            if (e.getX()>100 && e.getButton() == MouseEvent.BUTTON1) {
                this.dispose();  
            Reglage visu = new Reglage();          
            visu.affiche();
            }
            if (e.getX()>300 && e.getButton() == MouseEvent.BUTTON1) {
            this.dispose();
            }
        
       
        }
        

    
}