import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * On initialise le menu de reglage 
 * @version 1
 * @author Nathan Bertholier
 * @author Jürgen Hablainville
 */
public class Reglage extends JFrame implements ActionListener{

	private int nbc=4;
	private int nbl=4;
	private int nbm=nbc*nbl/4;
	private JLabel col = new JLabel();
	private JLabel lig = new JLabel();
	private JLabel mines = new JLabel();
	
	/**
	 * On créer la fenetre des réglage
	 */
	public Reglage(){
		
		GridLayout gestionnaire = new GridLayout(8, 2);
		this.setLayout(gestionnaire);
		JButton c1 = new JButton("colonne +");
		JButton c2 = new JButton("colonne -");
		JButton l1 = new JButton("ligne +");
		JButton l2 = new JButton("ligne -");
		JButton m1 = new JButton("mines +");
		JButton m2 = new JButton("mines -");
		JButton ci5 = new JButton("colonne +5");
		JButton cd5 = new JButton("colonne -5");
		JButton li5 = new JButton("ligne +5");
		JButton ld5 = new JButton("ligne -5");
		JButton md5 = new JButton("mines -5");
		JButton mi5 = new JButton("mines +5");	
		JButton run = new JButton("lancer la partie !");
		c1.addActionListener(this);
		c2.addActionListener(this);
		l1.addActionListener(this);
		l2.addActionListener(this);
		m1.addActionListener(this);
		m2.addActionListener(this);
		ci5.addActionListener(this);
		cd5.addActionListener(this);
		li5.addActionListener(this);
		ld5.addActionListener(this);
		mi5.addActionListener(this);
		md5.addActionListener(this);
		mi5.addActionListener(this);
		run.addActionListener(this);
		this.setSize(400,400);
		this.setLocation(200, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.add(c1);	
		this.add(l1);
		this.add(c2);
		this.add(l2);
		this.add(ci5);
		this.add(li5);
		this.add(cd5);
		this.add(ld5);
		this.add(col);
		this.add(lig);
		col.setText("colonne: " + nbc);
		lig.setText("ligne: " + nbl);
		this.add(m2);
		this.add(m1);
		this.add(md5);
		this.add(mi5);
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
	      	 	nbc=30;
	      	 }	
		  col.setText("colonne: " + nbc); 
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if(e.getActionCommand()=="colonne -")
	    {
	      nbc--;	      
	      if (nbc<=4) {
	      	 	nbc=4;
	      	 }
		  col.setText("colonne: " + nbc);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if(e.getActionCommand()=="ligne +")
	    {
	      nbl++;	      
	      if (nbl>=30) {
	      	 	nbl=30;
	      	 }
		  lig.setText("ligne: " + nbl);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if (e.getActionCommand()=="ligne -")
	    {
	      nbl--;		      
	      if (nbl<=4) {
	      	 	nbl=4;
	      	 }
		  lig.setText("ligne: " + nbl);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
		}
		else if (e.getActionCommand()=="mines +") {
			nbm++;
			if(nbm>(nbl*nbc/2))
			{
				nbm=(nbl*nbc/2);
			}
	    	mines.setText("mines: " + nbm);
	    }
	    else if (e.getActionCommand()=="mines -") {
	    	nbm--;
	    	if (nbm<=4) {
	    		nbm=4;
			}
			if(nbm<(nbl*nbc/4))
			{
				nbm=nbl*nbc/4;
			}
	    	mines.setText("mines: " + nbm);
		}
		else if (e.getActionCommand()=="lancer la partie !") {
			this.dispose();
			Demineur jeu=new Demineur(nbl,nbc,nbm,false);
	    }
	    else if (e.getActionCommand()=="colonne +5") {
	    	nbc=nbc+5;
	    	if (nbc>=30) {
	      	 	nbc=30;
	      	 }
		  col.setText("colonne: " + nbc);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if (e.getActionCommand()=="colonne -5") {
	    	nbc=nbc-5;
	    	if (nbc<=4) {
	      	 	nbc=4;
	      	 }
		  col.setText("colonne: " + nbc);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if(e.getActionCommand()=="ligne +5")
	    {
	      nbl=nbl+5;	      
	      if (nbl>=30) {
	      	 	nbl=30;
	      	 }
		  lig.setText("ligne: " + nbl);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
	    }
	    else if (e.getActionCommand()=="ligne -5")
	    {
	      nbl=nbl-5;		      
	      if (nbl<=4) {
	      	 	nbl=4;
	      	 }
		  lig.setText("ligne: " + nbl);
		  nbm=nbl*nbc/4;
	    	mines.setText("mines: " + nbm);
		}
		else if (e.getActionCommand()=="mines +5")
	    {
		  nbm=nbm+5;
		  if(nbm>(nbl*nbc/2))
			{
				nbm=(nbl*nbc/2);
			}
	    	mines.setText("mines: " + nbm);
		}
		else if (e.getActionCommand()=="mines -5") {
	    	nbm=nbm-5;
	    	if (nbm<=4) {
	    		nbm=4;
			}
			if(nbm<(nbl*nbc/4))
			{
				nbm=nbl*nbc/4;
			}
	    	mines.setText("mines: " + nbm);
		}
	}
}