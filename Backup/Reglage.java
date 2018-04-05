import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Reglage extends JFrame implements ActionListener{

	public int nbc=4;
	public int nbl=4;
	public int nbm=2;
	public JLabel col = new JLabel();
	public JLabel lig = new JLabel();
	public JLabel mines = new JLabel();
	public void affiche(){
		
		GridLayout gestionnaire = new GridLayout(5, 2);
		this.setLayout(gestionnaire);
		JButton c1 = new JButton("colonne +");
		JButton c2 = new JButton("colonne -");
		JButton l1 = new JButton("ligne +");
		JButton l2 = new JButton("ligne -");
		JButton m1 = new JButton("mines +");
		JButton m2 = new JButton("mines -");
		JButton run = new JButton("lancer la partie !");
		c1.addActionListener(this);
		c2.addActionListener(this);
		l1.addActionListener(this);
		l2.addActionListener(this);
		m1.addActionListener(this);
		m2.addActionListener(this);
		run.addActionListener(this);
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
		this.add(m2);
		this.add(m1);
		this.add(mines);
		mines.setText("mines: "+nbm);
		this.add(run);
		this.setVisible(true);
	}
	public void actionPerformed(ActionEvent e)
	{
	    if(e.getActionCommand()=="colonne +")
	    {
	      nbc++;     
	      if (nbc>=30) {
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
	      if (nbl>=30) {
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
		else if (e.getActionCommand()=="mines +") {
	    	nbm++;
	    	if (nbm>=nbl*nbc) {
	    		nbm=nbm/4;
	    	}
	    	mines.setText("mines: " + nbm);
	    }
	    else if (e.getActionCommand()=="mines -") {
	    	nbm--;
	    	if (nbm<=nbl*nbc/4) {
	    		nbm=nbl*nbc/4;
	    	}
	    	mines.setText("mines: " + nbm);
		}
		else if (e.getActionCommand()=="lancer la partie !") {
			this.dispose();
			System.out.println("run");
			Demineur jeu=new Demineur(nbl,nbc,nbm,false);
	    }	
	}
}