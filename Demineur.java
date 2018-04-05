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
                           
                           tabCase[f][i].setBackground(Color.BLACK);
                          }
                       }
              }
        }

    public void actionPerformed(ActionEvent e){
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==false&&tabCase[f][i].getValide()==false) {
                    tabCase[f][i].setValide();
                    if (f == ligne - 1 && i == colonne - 1) {
                        System.out.println("angle bas droite");
                        for (int k = i - 1; k < i + 1; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    }else if (f == 0 && i == 0) {
                        System.out.println("angle haut gauche");
                        for (int k = i; k < i + 2; k++) {
                            for (int g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    }
                    else if (i == 0 && f == ligne - 1) {
                        System.out.println("angle haut droite");
                        for (int k = i ; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    } else if (f == 0 && i == colonne - 1) {
                        System.out.println("angle bas gauche");
                        for (int k = i-1; k < i + 1; k++) {
                            for (int g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    }else if (f==0) {
                        System.out.println("Ligne de gauche");
                        for (int k = i-1; k < i+2; k++) {
                            for (int g = f ; g < f+2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                }
                                
                            }
                        }
                    }else if (i==0) {
                        System.out.println("Ligne de haut");
                        for (int k = i; k < i+2; k++) {
                            for (int g = f-1 ; g < f+2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                }
                                
                            }
                        }
                    } else if (i == colonne-1) {
                        System.out.println("Ligne du bas");
                        for (int k = i-1; k < i + 1; k++) {
                            for (int g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    } else if (f == ligne - 1) {
                        System.out.println("Ligne du bas");
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                } 
                            }
                        }
                    }  
                    else{
                        
                        for (int k = i - 1; k < i+2; k++) {
                            for (int g = f - 1; g < f+2; g++) {
                                System.out.println("position: "+g+"/"+k);
                                if (tabCase[g][k].etatMine() == true) {
                                    System.out.println("mine");
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setNb();
                                }
                                
                            }
                        }
                    }
                    

                   
                  





                      
                }
                if(e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==true)
                    {
                        this.dispose();
                    }
            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (tabCase[f][i].getNb() == 0 && tabCase[f][i].getValide() == true) {
                    tabCase[f][i].setBackground(Color.WHITE);
                }
                
            }
        }

       
    }
}
