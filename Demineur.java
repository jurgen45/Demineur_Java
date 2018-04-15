import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.MouseListener;


public class Demineur extends JFrame implements ActionListener, MouseListener,WindowListener {
    private int ligne;
    private int colonne;
    private int mine1;
    private int marqueurs;
    private JLabel nbmarques = null;
    private Case[][] tabCase = null;
    private JFrame fenetre = new JFrame();
    private ImageIcon img = null;
    JButton save = new JButton("sauvegarder");
    JButton quitter = new JButton("quitter");
    Algo alg=null;
    public Demineur(int ligne, int colonne, int mine, boolean fichier) {
        super();
        this.ligne = ligne;
        this.colonne = colonne;
        this.setSize(600, 600);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        GridLayout grid = new GridLayout(colonne, ligne);
        this.setLayout(grid);
        tabCase = new Case[ligne][colonne];
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                tabCase[f][i] = new Case(false);
                tabCase[f][i].addActionListener(this);
                tabCase[f][i].addMouseListener(this);
            }
        }
        if (fichier == true) {
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
                        nbm = flux1.readInt();
                        if (nbm == 1) {
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
                        if (nbcompteur != 0) {
                            tabCase[f][i].setNbtoFile(nbcompteur);
                            tabCase[f][i].setText(tabCase[f][i].getNbStr());
                            tabCase[f][i].setBackground(Color.WHITE);
                        } else if (nbcompteur == 0 && valid == 1) {
                            tabCase[f][i].setBackground(Color.WHITE);
                        }

                    }
                }
                for (int i = 0; i < colonne; i++) {
                    for (int f = 0; f < ligne; f++) {
                        etat = flux1.readInt();
                        tabCase[f][i].setEtattoFile(etat);
                        if (tabCase[f][i].getEtat() == 1) {
                            img = new ImageIcon(new ImageIcon("flag1.png").getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));
                            tabCase[f][i].setIcon(img);
                            marqueurs++;
                        } else if (tabCase[f][i].getEtat() == 2) {
                            img = new ImageIcon(new ImageIcon("intero.png").getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));
                            tabCase[f][i].setIcon(img);
                            marqueurs--;
                        }
                    }
                }
                etat = flux1.readInt();
                marqueurs = etat;

            } catch (FileNotFoundException ex) {
                System.err.println("fichier non trouvé: lecture");
            } catch (IOException ex) {
                System.out.println("il y'a une erreur: lecture");
            }
        } else {

            double aleadouble = Math.random() * 100;
            int alea = (int) aleadouble;
            int compteurAleaMine = 0;
            while (compteurAleaMine < mine) {
                for (int i = 0; i < colonne; i++) {
                    for (int f = 0; f < ligne; f++) {
                        aleadouble = Math.random() * 100;
                        alea = (int) aleadouble;
                        if (alea == 1 && compteurAleaMine < mine + 1) {
                            tabCase[f][i] = new Case(true);
                            tabCase[f][i].addActionListener(this);
                            tabCase[f][i].addMouseListener(this);
                            compteurAleaMine++;
                        }
                    }
                }
            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                this.add(tabCase[f][i]);
                if (tabCase[f][i].getValide() == false || tabCase[f][i].etatMine() == true) {
                    tabCase[f][i].setBackground(Color.GRAY);
                }

            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (tabCase[f][i].etatMine() == true) {
                    tabCase[f][i].setValide();
                    tabCase[f][i].setText("");
                }
            }
        }

        mine1 = mine;
        JLabel nbmines = new JLabel();
        nbmarques = new JLabel();
        GridLayout grid1 = new GridLayout(4, 2);
        fenetre.setLayout(grid1);
        nbmarques.setIcon(new ImageIcon("flag1.png"));
        nbmarques.setText("" + marqueurs);
        nbmines.setIcon(new ImageIcon("mine.png"));
        nbmines.setText("" + mine);
        fenetre.setSize(150, 600);
        fenetre.setLocation(800, 100);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quitter.addActionListener(this);
        save.addActionListener(this);
        fenetre.add(nbmarques);
        fenetre.add(nbmines);
        fenetre.add(save);
        fenetre.add(quitter);
        fenetre.setVisible(true);
        this.addWindowListener(this);
        alg=new Algo(tabCase,ligne,colonne);
    }

    public void actionPerformed(ActionEvent e) {
        
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].etatMine() == false
                        && tabCase[f][i].getValide() == false && tabCase[f][i].getEtat() == 0) {
                    alg.recherche(tabCase,f, i,ligne, colonne);
                }
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].etatMine() == true
                        && tabCase[f][i].getEtat() == 0) {
                            for (int j = 0; j < colonne; j++) {
                                 for (int m = 0; m < ligne; m++) {
                                     if (tabCase[m][j].etatMine()==true) {
                                         tabCase[m][j].setBackground(Color.RED);
                                     }
                                     
                                 }
                                }
                            /*
                    this.dispose();
                    fenetre.dispose();
                    */
                }
            }
        }
        int compteurFinal = 0;
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (tabCase[f][i].getNb() == 0 && tabCase[f][i].getValide() == true
                        && tabCase[f][i].etatMine() == false) {
                    //tabCase[f][i].setBackground(Color.WHITE);
                }
                if (tabCase[f][i].getValide() == true) {
                    compteurFinal++;
                }
                if (compteurFinal == ligne * colonne) {
                    try {
                        FileOutputStream fichier = new FileOutputStream("save.txt");
                        DataOutputStream flux = new DataOutputStream(fichier);
                        flux.writeInt(-1);
                        flux.close();
                    } catch (FileNotFoundException ex) {
                        System.err.println("fichier non trouvé: ecriture");
                    } catch (IOException ex) {
                        System.out.println("il y'a une erreur: ecriture");
                    }
                    this.dispose();
                    fenetre.dispose();
                }

            }
        }

        if (e.getActionCommand() == "quitter") {
            this.dispose();
            fenetre.dispose();
        }
        if (e.getActionCommand() == "sauvegarder") {
            tabCase[0][0].sauvegarde(tabCase, ligne, colonne, mine1, marqueurs);
         

        }

    }
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){}

    public void windowClosing(WindowEvent e) {
        int confirm = JOptionPane.showOptionDialog(null, "Are You Sure to Close Application?", "Exit Confirmation",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == 0) {
            tabCase[0][0].sauvegarde(tabCase,ligne,colonne, mine1,marqueurs);
            System.exit(0);
        }
    }
    public void mousePressed(MouseEvent me) {
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
    }

    public void mouseExited(MouseEvent me) {
    }

    public void mouseClicked(MouseEvent e) {
        if (e.getModifiers() == MouseEvent.BUTTON3_MASK) {
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].getValide() == false
                        && e.getModifiers() == MouseEvent.BUTTON3_MASK
                        || tabCase[f][i].etatMine() == true && e.getSource() == tabCase[f][i]
                                && e.getModifiers() == MouseEvent.BUTTON3_MASK) {

                    tabCase[f][i].setEtat();

                }

            }
        }
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].getEtat() == 1
                        && e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img = new ImageIcon(new ImageIcon("flag1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                    tabCase[f][i].setIcon(img);
                    marqueurs++;
                } else if (e.getSource() == tabCase[f][i] && tabCase[f][i].getEtat() == 2
                        && e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img = new ImageIcon(new ImageIcon("intero.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
                    tabCase[f][i].setIcon(img);
                    marqueurs--;
                } else if (e.getSource() == tabCase[f][i] && tabCase[f][i].getEtat() == 0
                        && tabCase[f][i].getValide() == false && e.getModifiers() == MouseEvent.BUTTON3_MASK
                        || tabCase[f][i].etatMine() == true && e.getSource() == tabCase[f][i]
                                && e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img = null;
                    tabCase[f][i].setIcon(img);

                }

            }
        }
        nbmarques.setText("" + marqueurs);
    }

   
}
