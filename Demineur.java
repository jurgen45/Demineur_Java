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
    private int marqueurs;
    private JLabel nbmarques=null;
    public Case[][] tabCase=null;
    private JFrame fenetre = new JFrame();
    private ImageIcon img=null;
    JButton save = new JButton("sauvegarder");
    JButton quitter = new JButton("quitter");
   public Demineur(int ligne,int colonne,int mine,boolean fichier)
   {
        super();
        marqueurs = mine;
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
            int nbcompteur;
            int valid;
            int etat;
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
                for (int i = 0; i < colonne; i++) {
                    for (int f = 0; f < ligne; f++) {
                        valid = flux1.readInt();
                        nbcompteur = flux1.readInt();
                        
                        if (valid == 1) {
                            tabCase[f][i].setValide();
                            
                        }
                        if (nbcompteur!=0) {
                            tabCase[f][i].setNbtoFile(nbcompteur);
                            tabCase[f][i].setText(tabCase[f][i].getNbStr());
                            tabCase[f][i].setBackground(Color.WHITE);
                        }else if(nbcompteur == 0&& valid == 1)
                        {
                            tabCase[f][i].setBackground(Color.WHITE);
                        }

                        
                    }
                }
                for (int i = 0; i < colonne; i++) {
                    for (int f = 0; f < ligne; f++) {
                        etat = flux1.readInt();
                       tabCase[f][i].setEtattoFile(etat);
                       if (tabCase[f][i].getEtat()==1) {
                            img = new ImageIcon("flag1.png");
                            tabCase[f][i].setIcon(img);
                            marqueurs++;
                       }else if (tabCase[f][i].getEtat() == 2) {
                            img = new ImageIcon("intero.png");
                            tabCase[f][i].setIcon(img);
                            marqueurs--;
                       }
                    }
                }
                etat = flux1.readInt();
                marqueurs=etat;
                
               
            } catch (FileNotFoundException ex) {
                System.err.println("fichier non trouvé: lecture");
            } catch (IOException ex) {
                System.out.println("il y'a une erreur: lecture");
            }
        }else{

        
            double aleadouble=Math.random() * 100;
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
                    if (tabCase[f][i].getValide()==false||tabCase[f][i].etatMine()==true) {
                        tabCase[f][i].setBackground(Color.GRAY);
                    }
                    
                    }
            }
            for (int i = 0; i < colonne; i++) 
            {
                     for (int f = 0; f < ligne; f++) 
                    {
                           if (tabCase[f][i].etatMine()==true) 
                           {
                            tabCase[f][i].setValide();
                            tabCase[f][i].setText("*");
                          }
                       }
              }
              
              mine1=mine;
              JLabel nbmines = new JLabel();
              nbmarques = new JLabel();
              GridLayout grid1 = new GridLayout(4,2);
              fenetre.setLayout(grid1);
              nbmarques.setIcon(new ImageIcon("flag1.png"));
              nbmarques.setText(""+marqueurs);
              nbmines.setIcon(new ImageIcon("mine.png"));
              nbmines.setText(""+mine);
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
        int g=0,k=0;
        System.out.println("event");
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&& tabCase[f][i].etatMine()==false&&tabCase[f][i].getValide()==false&&tabCase[f][i].getEtat()==0){
                    
                    tabCase[f][i].setBackground(Color.WHITE);
                        if (f == ligne - 1 && i == colonne - 1) {
                        System.out.println("angle bas droite");
                        for ( k = i - 1; k < i + 1; k++) {
                            for ( g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    }else if (f == 0 && i == 0) {
                        System.out.println("angle haut gauche");
                        for ( k = i; k < i + 2; k++) {
                            for ( g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    }
                    else if (i == 0 && f == ligne - 1) {
                        System.out.println("angle haut droite");
                        for ( k = i ; k < i + 2; k++) {
                            for ( g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    } else if (f == 0 && i == colonne - 1) {
                        System.out.println("angle bas gauche");
                        for ( k = i-1; k < i + 1; k++) {
                            for ( g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    }else if (f==0) {
                        System.out.println("Ligne de gauche");
                        for ( k = i-1; k < i+2; k++) {
                            for ( g = f ; g < f+2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                                
                            }
                        }
                    }else if (i==0) {
                        System.out.println("Ligne de haut");
                        for ( k = i; k < i+2; k++) {
                            for ( g = f-1 ; g < f+2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                                
                            }
                        }
                    } else if (i == colonne-1) {
                        System.out.println("Ligne du bas");
                        for ( k = i-1; k < i + 1; k++) {
                            for ( g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    } else if (f == ligne - 1) {
                        System.out.println("Ligne du bas");
                        for ( k = i - 1; k < i + 2; k++) {
                            for ( g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                } 
                            }
                        }
                    }  
                    else{
                        
                        for ( k = i - 1; k < i+2; k++) {
                            for ( g = f - 1; g < f+2; g++) {
                                System.out.println("position: "+g+"/"+k);
                                if (tabCase[g][k].etatMine() == true) {
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                                

                                
                                
                            }
                        }
                    }
                    if (tabCase[f][i].getNb()==0) {
                        tabCase[f][i].setValide();
                        //tabCase[f][i].detectionRecursive(tabCase, g, k, ligne, colonne);
                    }
                    
                    /*
                    if (tabCase[f][i].getNb()==0) {
                        tabCase[f][i].detectionRecursive(tabCase, k, g, ligne, colonne);
                        tabCase[f][i].setValide();
                    }
                    */
                    
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
                for (int i = 0; i < ligne; i++) {
                    for (int f = 0; f < colonne; f++) {
                        if (tabCase[f][i].getValide() == true) {
                            flux.writeInt(1);
                            System.out.println("ecrit 1");
                        } else if (tabCase[f][i].getValide() == false) {
                            flux.writeInt(0);
                            System.out.println("ecrit 0");
                        }
                        if (tabCase[f][i].getNb()!=0) {
                            flux.writeInt(tabCase[f][i].getNb());
                            System.out.println("ecrit nb");
                        } else {
                            flux.writeInt(0);
                            System.out.println("ecrit nb:0");
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
                if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getValide()==false && e.getModifiers() == MouseEvent.BUTTON3_MASK||tabCase[f][i].etatMine()==true&& e.getSource() == tabCase[f][i]&& e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                                    
                                tabCase[f][i].setEtat();
                        tabCase[f][i].setEtat();
                    
                }
               
            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat()==1&& e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    marqueurs--;
                    img=new ImageIcon("flag1.png");
                    tabCase[f][i].setIcon(img);
                    
                }
                else if (e.getSource() == tabCase[f][i] &&tabCase[f][i].getEtat() == 2&& e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img=new ImageIcon("intero.png");
                    tabCase[f][i].setIcon(img);

                    marqueurs++;
                } else if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat() == 0&&tabCase[f][i].getValide()==false||tabCase[f][i].etatMine()==true&& e.getSource() == tabCase[f][i]) {

                    marqueurs--;
                } else if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat() == 0&&tabCase[f][i].getValide()==false&&e.getModifiers() == MouseEvent.BUTTON3_MASK||tabCase[f][i].etatMine()==true&& e.getSource() == tabCase[f][i]&&e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img=null;
                    tabCase[f][i].setIcon(img);
                    
                }
                
            }
        }
        nbmarques.setText(""+marqueurs);
        }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat()==1&& e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    marqueurs--;
                    img=new ImageIcon("flag1.png");
                    tabCase[f][i].setIcon(img);
                    
                }
                else if (e.getSource() == tabCase[f][i] &&tabCase[f][i].getEtat() == 2&& e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img=new ImageIcon("intero.png");
                    tabCase[f][i].setIcon(img);

                    marqueurs++;
                }
                else if (e.getSource()==tabCase[f][i]&&tabCase[f][i].getEtat() == 0&&tabCase[f][i].getValide()==false&&e.getModifiers() == MouseEvent.BUTTON3_MASK||tabCase[f][i].etatMine()==true&& e.getSource() == tabCase[f][i]&&e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img=null;
                    tabCase[f][i].setIcon(img);
                    System.out.println("case vide");
                    
                }
                
            }
        }
        nbmarques.setText(""+marqueurs);
        }

////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////

////////////////////////////////////////////////////////////////////////

        

    

}
