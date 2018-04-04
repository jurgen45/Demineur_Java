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
            for(int i=0;i<colonne;i++)
            {
                for(int f=0;f<ligne;f++)
                    {
                        tabJButton[f][i] = new JButton();
                        this.add(tabJButton[f][i]);
                    }
            }
            double aleadouble=Math.random() * 10;
            int alea=(int)aleadouble;
            int compteurAleaMine=0;
            
                
            while (compteurAleaMine<mine) {
                
            
            for (int i = 0; i < colonne; i++) {
                for (int f = 0; f < ligne; f++) {
                    aleadouble = Math.random() * 10;
                    alea = (int) aleadouble;
                    if (alea==1&&compteurAleaMine<mine) {
                        tabJButton[f][i] = new JButton("*");
                        tabJButton[f][i].setBackground(Color.BLACK);
                        compteurAleaMine++;
                        System.out.println("mine");
                       System.out.println("f:"+f+" i:"+i);
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