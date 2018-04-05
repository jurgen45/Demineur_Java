import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Demineur extends JFrame implements ActionListener {
    private int ligne;
    private int colonne;
    private Case[][] tabCase=null;
   public Demineur(int ligne,int colonne,int mine)
   {
        super();
        this.ligne=ligne;
        this.colonne=colonne;
        this.setSize(600, 600);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        GridLayout grid=new GridLayout(colonne,ligne);
        this.setLayout(grid);
        tabCase=new Case[ligne][colonne];
            System.out.println(ligne+"/"+colonne + "/"+mine);
            for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                tabCase[f][i]=new Case(false);
                tabCase[f][i].addActionListener(this);
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
                        tabCase[f][i] = new Case(true);
                        tabCase[f][i].addActionListener(this);
                        System.out.println("mine :"+f + "/" + i);
                        compteurAleaMine++;
                    }
                }
            }
            }
            for (int i = 0; i < colonne; i++) 
            {
                    for (int f = 0; f < ligne; f++) 
                    {
                    this.add(tabCase[f][i]);
                    tabCase[f][i].setBackground(Color.GRAY);
                    }
            }
            for (int i = 0; i < colonne; i++) 
            {
                     for (int f = 0; f < ligne; f++) 
                    {
                           if (tabCase[f][i].etatMine()==true) 
                           {
                           System.out.println("mine black");
                           tabCase[f][i].setBackground(Color.BLACK);
                          }
                       }
              }
        }

    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==false) {
                    if (f==0) {
                        System.out.println("Ligne de gauche");
                        for (int k = i; k < 3; k++) {
                            for (int g = f ; g < 3; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setBackground(Color.YELLOW);
                                }
                            }
                        }
                    }else{
                        for (int k = i - 1; k < 4; k++) {
                            for (int g = f - 1; g < 4; g++) {
                                System.out.println("position: "+g+"/"+k);
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setBackground(Color.YELLOW);
                                    this.add(tabCase[f][i]);
                                }
                            }
                        }
                    }
                    








                        /*
                    tabCase[f][i].setBackground(Color.WHITE);
                    tabCase[f][i].setValide();
                    */
                }
                if(e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==true)
                    {
                        this.dispose();
                    }
            }
        }

       
    }
}
