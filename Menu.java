import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.*;

public class Menu extends JFrame implements ActionListener {
    public JPanel fond = null;
    public JButton b1 = null;
    public JButton b2 = null;
    public JButton b3 = null;
    public ImageIcon play;
    public Menu() {
        super();

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.menuDemarrage();
    }

    public void menuDemarrage() {
        JPanel paneltitre = new JPanel();
        fond = new JPanel();
        fond.setBackground(new Color(136, 225, 213));
        GridLayout grid = new GridLayout(3, 1);
        b1 = new JButton("1");
        b1.setIcon(new ImageIcon("play3.jpg"));
        b2 = new JButton("reprendre la partie précédente");
        b3 = new JButton("2");
        b3.setIcon(new ImageIcon("exit.png"));
        fond.setLayout(grid);
        fond.add(b1);
        fond.add(b2);
        fond.add(b3);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        JLabel titre = new JLabel("Demineur");
        paneltitre.add(titre);
        this.add(paneltitre, BorderLayout.NORTH);
        this.add(fond, BorderLayout.CENTER);

    }

 

    public void actionPerformed(ActionEvent e){
        System.out.println(e.getActionCommand());
        if (e.getActionCommand() == "1") {
            this.dispose();
            Reglage visu = new Reglage();          
            visu.affiche();
        } else if (e.getActionCommand() == "reprendre la partie précédente") {
            try{
                FileInputStream file = new FileInputStream("save.txt");
                DataInputStream flux1 = new DataInputStream(file);
                int nbl = flux1.readInt();
                int nbc = flux1.readInt();
                int nbm = flux1.readInt();
                this.dispose();
                System.out.println("run");
                Demineur jeu = new Demineur(nbl,nbc,nbm);
            }
            catch(FileNotFoundException ex){
              System.err.println("fichier non trouvé: lecture");
            }
            catch(IOException ex){
              System.out.println("il y'a une erreur: lecture");
            }
        } else if (e.getActionCommand() == "2"){
            this.dispose();
        }
    }

}
