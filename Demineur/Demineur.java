import java.awt.event.ActionEvent;
import java.awt.event.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.awt.event.MouseListener;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Classe principale du jeu qui permet d'interagir avec toutes les autres
 * classes  
 * @version 1
 * @author Nathan Bertholier
 * @author Jürgen Hablainville
 */
public class Demineur extends JFrame implements ActionListener, MouseListener,WindowListener {
    private int ligne;
    private int colonne;
    private int mine1;
    private int marqueurs;
    private int nbCoup=0;
    private int sec=0, min=0;  
    private JLabel nbmarques = null;
    private JLabel nbmines = null;
    private JLabel time = null;
    private Case[][] tabCase = null;
    private JFrame fenetre = new JFrame();
    private ImageIcon img = null;
    private int victoire =0;
    JButton save = new JButton("sauvegarder & quitter");
    JButton quitter = new JButton("quitter");
    Timer temps = new Timer();
    Algo alg=null;
    
    /**
     * Initialise la partie
     * 
     * @param ligne   le nombre de ligne de la partie
     * @param colonne le nombe de colonne de la partie
     * @param mine  nombre de mine dans la partie
     * @param fichier true si fichier de sauvegarde present sinon false
     */
    public Demineur(int ligne, int colonne, int mine, boolean fichier) {
        super();
        marqueurs=mine;
        this.ligne = ligne;
        this.colonne = colonne;
        this.setSize(600, 600);
        this.setLocation(200, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        GridLayout grid = new GridLayout(colonne, ligne);
        this.setLayout(grid);
        /**
             * On créer un tableau bidimensionel de Case(Heritage de JButton)
             */
        tabCase = new Case[ligne][colonne];
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                 /**
             * l'attribu de validation de case est initialiser a false pour chaque case 
             * et chaque case est ajoutée au listener 
             */
                tabCase[f][i] = new Case(false);
                tabCase[f][i].addActionListener(this);
                tabCase[f][i].addMouseListener(this);
            }
        }
         /**
             * Si un fichier de sauvgarde est detecter on definit les attributs grace au données du fichier de save
             */
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
                            marqueurs--;
                        } else if (tabCase[f][i].getEtat() == 2) {
                            img = new ImageIcon(new ImageIcon("intero.png").getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));
                            tabCase[f][i].setIcon(img);
                            marqueurs++;
                        }
                    }
                }
                etat = flux1.readInt();
                marqueurs = etat;
                sec = flux1.readInt();
                min = flux1.readInt();

            } catch (FileNotFoundException ex) {
                System.err.println("fichier non trouvé: lecture");
            } catch (IOException ex) {
                System.out.println("il y'a une erreur: lecture");
            }
            /**
             * sinon on instancie de maniére aleatoire les mines dans le tableau
             */
        } else {
        int[] aleamine=new int[mine];
        double aleadouble;
            int alea;
        for(int i=0;i<mine;i++)
        {
            aleadouble = Math.random() * (ligne*colonne);
            alea = (int) aleadouble;
            for(int v=0;v<i;v++)
            {
                if(alea==aleamine[v]){
                    i--;
                    break;
                }else{
                    aleamine[i]=alea;
                }
            }
        }
        int compteur=0;
        boolean valide=false;
    for(int compteurtab=0;compteurtab<aleamine.length;compteurtab++){
    
        for(int i=0;i<colonne&&valide==false;i++)
        {
            for(int f=0;f<ligne&&valide==false;f++)
            {
                if(compteur==aleamine[compteurtab])
                {
                    tabCase[f][i] = new Case(true);
                    tabCase[f][i].addActionListener(this);
                    tabCase[f][i].addMouseListener(this);
                    compteur=0;
                    valide=true;
                }
                compteur++;
            }
        }
        compteur=0;
        valide=false;
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
                    /**
                     * on definit les mines comme validée pour faciliter le calcule final
                     */
                    tabCase[f][i].setValide();
                    //tabCase[f][i].setText("*");
                }
            }
        }
        /**
         * On initialise le menu des informations 
         */
        mine1 = mine;
        nbmines = new JLabel();
        nbmarques = new JLabel();
        time = new JLabel();
        img = new ImageIcon(new ImageIcon("Chronometre.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        time.setIcon(img);
        GridLayout grid1 = new GridLayout(5, 2);
        fenetre.setLayout(grid1);
        nbmarques.setIcon(new ImageIcon("flag1.png"));
        nbmarques.setText("" + marqueurs);
        nbmines.setIcon(new ImageIcon("mine.png"));
        nbmines.setText("" + mine);

        TimerTask action = new TimerTask() {
            public void run() {
                sec++;
                time.setText(""+min+":"+sec);
                if (sec==60) {
                    sec=0;
                    min++;
                }
            }
        };
        temps.schedule(action, 0, 1000);
        time.setText(""+min+":"+sec);
        fenetre.setSize(175, 600);
        fenetre.setLocation(800, 100);
        fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        quitter.addActionListener(this);
        save.addActionListener(this);
        fenetre.add(time);
        fenetre.add(nbmarques);
        fenetre.add(nbmines);
        fenetre.add(save);
        fenetre.setVisible(true);
        this.addWindowListener(this);
        fenetre.addWindowListener(this);

        alg=new Algo(tabCase,ligne,colonne);
    }

    public void actionPerformed(ActionEvent e) {
        if (victoire==0) {
            
        
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                /**
                 * Si un JButton est activer,on recupere son id est et on utilise l'algorithme de recherche
                 * pour trouver les mines dans les cases autour
                 */
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].etatMine() == false
                        && tabCase[f][i].getValide() == false && tabCase[f][i].getEtat() == 0) {
                    alg.recherche(tabCase,f, i,ligne, colonne);
                    nbCoup++;
                }
                /**
                 * si le JButton contient une mine alors on arrete le jeu et on lance l'ecran de fin 
                 */
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].etatMine() == true
                        && tabCase[f][i].getEtat() == 0) {
                         nbCoup++;  
                        fin(false, tabCase, fenetre, ligne, colonne, quitter, sec, min,  nbCoup, f, i);
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
                /**
                 * si tout les JButton ont ete activer alors on arrete le jeu et on lance l'ecran de fin 
                 */
                tabCase[0][0].ecritureTabScore(sec,min,ligne,colonne,mine1);
                    fin(true,tabCase,fenetre,ligne,colonne,quitter,sec,min,nbCoup,f,i);
                   
                }

            }
        }
        }
        if (e.getActionCommand() == "quitter") {
            /**
             * Si le JButton Quitter est activer alors on ferme le programme sans sauvegarder 
             * et on relance le menu principale
             */
            fenetre.dispose();
            this.dispose();
            int indice=0;

        try{
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                indice = flux1.readInt();
                
                flux1.close();
            }
            catch(FileNotFoundException ex){
                System.err.println("fichier non trouvé: lecture");
            }
            catch(IOException ex){
                System.out.println("il y'a une erreur: lecture");
            }
            /**
             * si le contenue du fichier de sauvgarde est negatif alors on lance le menu sans possibilité 
             * de charger une sauvgarde 
             */
            if (indice>0) {
             
                Menu m=new Menu();
                m.affiche();
                m.setLocation(200, 100);
                System.out.println("1");
            }
            /**
             * Sinon on lance le menu standart avec la possibilité de reprendre une sauvgarde
             */
            else{
                Menu2 m2 = new Menu2();
                m2.affiche();
                m2.setLocation(200, 100);
                
            }
            
           
        }
        /**
         * Si le JButton est activer alors on sauvgarde dans un fichier 
         */
        if (e.getActionCommand() == "sauvegarder & quitter") {
            tabCase[0][0].sauvegarde(tabCase, ligne, colonne, mine1, marqueurs, sec, min);
            fenetre.dispose();
            this.dispose();
            int indice=0;

        try{
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                indice = flux1.readInt();
                
                flux1.close();
            }
            catch(FileNotFoundException ex){
                System.err.println("fichier non trouvé: lecture");
            }
            catch(IOException ex){
                System.out.println("il y'a une erreur: lecture");
            }
                Menu m=new Menu();
                m.affiche();
                m.setLocation(200, 100);
                System.out.println("1");       
        }

    }
    public void windowActivated(WindowEvent e){}
    public void windowClosed(WindowEvent e){}
    public void windowDeactivated(WindowEvent e){}
    public void windowDeiconified(WindowEvent e){}
    public void windowIconified(WindowEvent e){}
    public void windowOpened(WindowEvent e){}

    public void windowClosing(WindowEvent e) {
        /**
         * Si on ferme la fenetre alors on sauvgarde
         */
        
            tabCase[0][0].sauvegarde(tabCase, ligne, colonne, mine1, marqueurs, sec, min);
            System.exit(0);
        
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
        /**
         * Si on clique droit sur une case alors on pose un drapeau puis un "?" puis une case vide
         */
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
        /**
         * On affiche l'image correspondante sur le JButton
         */
        for (int i = 0; i < colonne; i++) {
            for (int f = 0; f < ligne; f++) {
                if (e.getSource() == tabCase[f][i] && tabCase[f][i].getEtat() == 1
                        && e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img = new ImageIcon(new ImageIcon("flag1.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                    tabCase[f][i].setIcon(img);
                    marqueurs--;
                } else if (e.getSource() == tabCase[f][i] && tabCase[f][i].getEtat() == 2
                        && e.getModifiers() == MouseEvent.BUTTON3_MASK) {
                    img = new ImageIcon(new ImageIcon("intero.png").getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT));
                    tabCase[f][i].setIcon(img);
                    marqueurs++;
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

    /**
     * Definit l'ecran de fin
     * 
     * @param victoire true si victoire sinon false
     * @param tabCase  tableau de cases de la partie
     * @param fenetre  fenetre secondaire(menu)
     * @param ligne    le nombre de ligne de la partie
     * @param colonne  le nombe de colonne de la partie
     * @param quitter  JButton permettant de quitter
     * @param sec      nombre de seconde du timer
     * @param min      nombre de minute du timer
     * @param nbCoup   nombre de coups executer dans la partie 
     * @param i  position y de tabCase a l'appui du bouton
     * @param f  position x de tabCase a l'appui du bouton
     */
    public void fin(boolean victoire,Case tabCase[][],JFrame fenetre,int ligne,int colonne,JButton quitter,int sec, int min ,int nbCoup,int f,int i){
        /**
         * Si on gagne alors victoire==true ,On affiche le temps et le nombre de coups 
         */
        if (victoire==true) {
            JLabel etat = new JLabel();
            JLabel nb_cout = new JLabel();
            JLabel temps = new JLabel();
            fenetre.getContentPane().removeAll();
            this.victoire=1;
            for (int j = 0; j < colonne; j++) {
                for (int m = 0; m < ligne; m++) {
                    if (tabCase[m][j].etatMine() == true) {
                        tabCase[m][j].setBackground(Color.RED);
                       ImageIcon img = new ImageIcon(
                                new ImageIcon("mine.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                        tabCase[m][j].setIcon(img);
                    }

                }
            }
            fenetre.add(etat);
            fenetre.add(temps);
            fenetre.add(nb_cout);
            fenetre.add(quitter);
            ImageIcon img = new ImageIcon(
                    new ImageIcon("Chronometre.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            temps.setIcon(img);
            temps.setText("" + sec);
            nb_cout.setText("nombre de coups= " + nbCoup);
            etat.setText("Victoire");
            tabCase[0][0].lectureTabScore(sec,min,ligne,colonne,mine1);
            
        }else{
            /**
             * Si on perd alors l'ecran de fin est le même mais toutes les mines sont afficher en rouge et la mine qui a sauté
             */
            JOptionPane gameOver = new JOptionPane();
            JLabel etat = new JLabel();
            JLabel nb_cout = new JLabel();
            JLabel temps = new JLabel();
            fenetre.getContentPane().removeAll();
            this.victoire = 2;
            for (int j = 0; j < colonne; j++) {
                for (int m = 0; m < ligne; m++) {
                    if (tabCase[m][j].etatMine() == true) {
                        tabCase[m][j].setBackground(Color.RED);
                        ImageIcon  img = new ImageIcon(
                                new ImageIcon("mine.png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
                        tabCase[m][j].setIcon(img);
                        tabCase[f][i].setBackground(Color.ORANGE);
                    }

                }
            }
            fenetre.add(etat);
            fenetre.add(temps);
            fenetre.add(nb_cout);
            fenetre.add(quitter);
            ImageIcon img = new ImageIcon(
                    new ImageIcon("Chronometre.png").getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
            temps.setIcon(img);
            temps.setText("" + sec);
            nb_cout.setText("nombre de coups= " + nbCoup);
            etat.setText("Defaite");
            int yes=gameOver.showOptionDialog(null, "vous avez perdu\n"+"temps: "+min+":"+sec+"\nnombre de coups: "+nbCoup+"\nrecommencer?", "defaite",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
            if (yes == 0) {             
                Demineur jeu=new Demineur(ligne,colonne,mine1,false);
                this.dispose();
                fenetre.dispose();
            }
            else{
            	//vide le fichier de sauvegarde
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
        	System.exit(0);
            }
            
        }
        /**
         * On vide le fichier de sauvgarde
         */
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

    }

   
}
