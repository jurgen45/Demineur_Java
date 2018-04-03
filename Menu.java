import java.awt.event.ActionEvent;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class Menu extends JFrame implements ActionListener {
    public JPanel fond = null;
    public JButton b1 = null;
    public JButton b2 = null;
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
        fond=new JPanel();
        fond.setBackground(new Color(136,225,213));
        GridLayout grid=new GridLayout(1,2);
       b1= new JButton("Nouvelle partie");
       b2= new JButton("reprendre la partie précédente");
        fond.setLayout(grid);
        fond.add(b1);
        fond.add(b2);
        b1.addActionListener(this);
        b2.addActionListener(this);
        JLabel titre=new JLabel("Demineur");
        paneltitre.add(titre);
        fond.setMaximumSize(new Dimension(600,400));
        this.add(paneltitre, BorderLayout.NORTH);
        this.add(fond);
        
    }

    public void actionPerformed(ActionEvent e) {
       System.out.println(e.getActionCommand());
       if (e.getActionCommand()=="Nouvelle partie") {
            b1.setBackground(Color.BLUE);
       } else if (e.getActionCommand() == "reprendre la partie précédente") {
            b2.setBackground(Color.CYAN);
        }
    }
    
}