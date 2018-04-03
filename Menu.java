import javax.swing.*;
import java.awt.*;
public class Menu extends JFrame {
    public Menu() {
    super();
        this.setSize(600, 600);
        this.setLocation(2000, 100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.menuDemarrage();
    }

    public void menuDemarrage()
    {
        JPanel paneltitre = new JPanel();
        JPanel fond=new JPanel();
        fond.setBackground(new Color(136,225,213));
        GridLayout grid=new GridLayout(1,2);
        JButton b1=new JButton("Nouvelle partie");
        JButton b2 = new JButton("reprendre la partie précédente");
        fond.setLayout(grid);
        fond.add(b1);
        fond.add(b2);
        JLabel titre=new JLabel("Demineur");
        paneltitre.add(titre);
        fond.setMaximumSize(new Dimension(600,400));
        this.add(paneltitre, BorderLayout.NORTH);
        this.add(fond);
        
    }

    
}