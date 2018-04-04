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
   }







    public void actionPerformed(ActionEvent e){

    }
}
