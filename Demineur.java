import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.MouseListener;
import java.io.*;

public class Demineur extends JFrame implements ActionListener,MouseListener {
    private int ligne;
    private int colonne;
    private int mine1;
    private Case[][] tabCase=null;
    private JFrame fenetre = new JFrame();
    JButton save = new JButton("sauvegarder");
    JButton quitter = new JButton("quitter");
   public Demineur(int ligne,int colonne,int mine,boolean fichier)
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
                tabCase[f][i].addMouseListener(this);
            }
        }
        if (fichier==true){
            int nbm;
            try {
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                nbm = flux1.readInt();
                nbm = flux1.readInt();
                nbm = flux1.readInt();
                for (int i = 0; i < colonne; i++) {
                    for (int f = 0; f < ligne; f++) {
                       nbm  = flux1.readInt();
                        System.out.println(nbm);
                       if (nbm==1) {
                           System.out.println("lecture 1");
                            tabCase[f][i] = new Case(true);
                            tabCase[f][i].addActionListener(this);
                            tabCase[f][i].addMouseListener(this);
                       }
                    }
                }
                
               
            } catch (FileNotFoundException ex) {
                System.err.println("fichier non trouvé: lecture");
            } catch (IOException ex) {
                System.out.println("il y'a une erreur: lecture");
            }
        }else{

        
            double aleadouble=Math.random() * 10;
            int alea=(int)aleadouble;
            int compteurAleaMine=0;
            while (compteurAleaMine<mine) {
            for (int i = 0; i < colonne; i++) {
                for (int f = 0; f < ligne; f++) {
                    aleadouble = Math.random() * 100;
                    alea = (int) aleadouble;
                    if (alea==1&&compteurAleaMine<mine+1) {
                        tabCase[f][i] = new Case(true);
                        tabCase[f][i].addActionListener(this);
                        tabCase[f][i].addMouseListener(this);
                        compteurAleaMine++;
                    }
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
                            tabCase[f][i].setValide();
                           tabCase[f][i].setBackground(Color.BLACK);
                          }
                       }
              }
              
              mine1=mine;
              JLabel nbmines = new JLabel();
              JLabel nbmarques = new JLabel();
              GridLayout grid1 = new GridLayout(4,2);
              fenetre.setLayout(grid1);
              nbmarques.setText("nombres de marqueurs: ");
              nbmines.setText("nombres de mines: "+mine);
              fenetre.setSize(150,600);
              fenetre.setLocation(800,100);
              fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
              quitter.addActionListener(this);
              save.addActionListener(this);
              fenetre.add(nbmarques);
              fenetre.add(nbmines);
              fenetre.add(save);
              fenetre.add(quitter);
              fenetre.setVisible(true);
        }

    public void actionPerformed(ActionEvent e){
        System.out.println("event");
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==false&&tabCase[f][i].getValide()==false&&tabCase[f][i].getEtat()==0){
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
                if(e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==true&&tabCase[f][i].getEtat()==0)
                    {
                        this.dispose();
                        fenetre.dispose();
                    }
            }
        }
        int compteurFinal=0;
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (tabCase[f][i].getNb() == 0 && tabCase[f][i].getValide() == true&& tabCase[f][i].etatMine()==false) {
                    tabCase[f][i].setBackground(Color.WHITE);
                }
                if (tabCase[f][i].getValide()==true) {
                    compteurFinal++;
                }
                if (compteurFinal==ligne*colonne) {
                    this.dispose();
                    fenetre.dispose();
                }
                
            }
        }

        if (e.getActionCommand()=="quitter") {
            this.dispose();
            fenetre.dispose();
        }
        if (e.getActionCommand()=="sauvegarder") {
            try{
                
                FileOutputStream fichier = new FileOutputStream("save.txt");
                DataOutputStream flux = new DataOutputStream(fichier);
                flux.writeInt(ligne);
                flux.writeInt(colonne);
                flux.writeInt(mine1);

                for (int i=0;i<ligne ;i++ ) {
                    for (int f=0;f<colonne ;f++ ) {
                        if (tabCase[f][i].etatMine()==true){
                            flux.writeInt(1);
                            System.out.println("ecrit 1");
                        }
                        else if (tabCase[f][i].etatMine()==false) {
                            flux.writeInt(0);
                            System.out.println("ecrit 0");
                        }
                    }
                }
                /////////////////////////////////////////////////////
                for (int i = 0; i < ligne; i++) {
                    for (int f = 0; f < colonne; f++) {
                        if (tabCase[f][i].etatMine() == true) {
                            flux.writeInt(1);
                            System.out.println("ecrit 1");
                        } else if (tabCase[f][i].etatMine() == false) {
                            flux.writeInt(0);
                            System.out.println("ecrit 0");
                        }
                    }
                }

                
                flux.close();
            }
            catch(FileNotFoundException ex){
                System.err.println("fichier non trouvé: ecriture");
            }
            catch(IOException ex){
                System.out.println("il y'a une erreur: ecriture");
            }
              
        }
       
    }
     public void mousePressed(MouseEvent me) { }
        public void mouseReleased(MouseEvent me) { }
        public void mouseEntered(MouseEvent me) { }
        public void mouseExited(MouseEvent me) { }
          public void mouseClicked (MouseEvent e) 
            {       			
    if (e.getModifiers() == MouseEvent.BUTTON3_MASK)
    {
      System.out.println("You right clicked on the button");
    }
    for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getValide()==false && e.getModifiers() == MouseEvent.BUTTON3_MASK||tabCase[f][i].etatMine()==true) {
                                    
                                tabCase[f][i].setEtat();
                    
                }
               
            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat()==1) {
                    tabCase[f][i].setIcon(new ImageIcon("flag1.png"));
                }
                else if (e.getSource() == tabCase[f][i] &&tabCase[f][i].getEtat() == 2) {
                    tabCase[f][i].setIcon(new ImageIcon("intero.png"));
                } else if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat() == 0&&tabCase[f][i].getValide()==false||tabCase[f][i].etatMine()==true&& e.getSource() == tabCase[f][i]) {
                    tabCase[f][i].setIcon(new ImageIcon("vide.png"));
                }
                
            }
        }
        }
}
