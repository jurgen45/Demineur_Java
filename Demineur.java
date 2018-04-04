import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Demineur extends JFrame implements ActionListener {
    private int nbligne;
    private int nbColonne;
    private int nbMine;
    private JButton[][] tabJButton=null;
   public Demineur(int ligne,int colonne,int mine)
   {
        super();
        this.setSize(600, 600);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        GridLayout grid=new GridLayout(colonne,ligne);
        this.setLayout(grid);
        tabJButton=new JButton[ligne][colonne];
            System.out.println(ligne+"/"+colonne + "/"+mine);
            for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                tabJButton[f][i]=new JButton();
            }
        }
            double aleadouble=Math.random() * 10;
            int alea=(int)aleadouble;
            int compteurAleaMine=0;
            while (compteurAleaMine<mine) {
            for (int i = 0; i < colonne; i++) {
                for (int f = 0; f < ligne; f++) {
                    aleadouble = Math.random() * 100;
                    alea = (int) aleadouble;
                    if (alea==1&&compteurAleaMine<mine) {
                        tabJButton[f][i] = new JButton("*");
                        tabJButton[f][i].setBackground(Color.BLACK);
                        System.out.println("mine :"+f + "/" + i);
                        compteurAleaMine++;
                    }
                    
                
                }
            }
            }
            for (int i = 0; i < colonne; i++) {
                for (int f = 0; f < ligne; f++) {
            this.add(tabJButton[f][i]);
                }
            }
            
            
        }
        
   







    public void actionPerformed(ActionEvent e){

    }
}
