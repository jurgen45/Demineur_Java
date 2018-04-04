import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Jeu extends JFrame implements ActionListener{
	public int nbc=4;
	public int nbl=4;
	public JLabel col = new JLabel();
	public JLabel lig = new JLabel();
	public void affiche(){
		
		GridLayout gestionnaire = new GridLayout(4, 2);
		this.setLayout(gestionnaire);
		JButton c1 = new JButton("colonne +");
		JButton c2 = new JButton("colonne -");
		JButton l1 = new JButton("ligne +");
		JButton l2 = new JButton("ligne -");
		c1.addActionListener(this);
		c2.addActionListener(this);
		l1.addActionListener(this);
		l2.addActionListener(this);
		this.setSize(400,400);
		this.setLocation(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(c1);	
		this.add(l1);
		this.add(c2);
		this.add(l2);
		this.add(col);
		this.add(lig);
		col.setText("colonne: " + nbc);
		lig.setText("ligne: " + nbl);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getActionCommand()=="colonne +")
	    {
	      nbc++;     
	      if (nbc>=40) {
	      	 	nbc=40;
	      	 }	
		  col.setText("colonne: " + nbc); 
	    }
	    else if(e.getActionCommand()=="colonne -")
	    {
	      nbc--;	      
	      if (nbc<=4) {
	      	 	nbc=4;
	      	 }
	      col.setText("colonne: " + nbc);
	    }
	    else if(e.getActionCommand()=="ligne +")
	    {
	      nbl++;	      
	      if (nbl>=40) {
	      	 	nbl=40;
	      	 }
	      lig.setText("ligne: " + nbl);
	    }
	    else if (e.getActionCommand()=="ligne -")
	    {
	      nbl--;		      
	      if (nbl<=4) {
	      	 	nbl=4;
	      	 }
	      lig.setText("ligne: " + nbl);
	    }	
	}
}