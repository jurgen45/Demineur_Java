import javax.swing.*;
import java.awt.*;
 
public class Dessin2 extends JComponent {
   /**
   * On dessine le menu avec les images pour Jouer et Quitter
   */
  private Image play;
  private Image quitter;
  @Override
  public void paintComponent(Graphics pinceau) {
    Graphics pinceau1 = pinceau.create();
    play = Toolkit.getDefaultToolkit().getImage("play.png");
    quitter = Toolkit.getDefaultToolkit().getImage("quitter.png");
    if (this.isOpaque()) {
      pinceau1.setColor(this.getBackground());
      pinceau1.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    Font f = new Font("Arial", Font.ROMAN_BASELINE  , 15);
    pinceau1.setFont(f);
    pinceau1.drawImage(play,100,10,this);
    pinceau1.drawString("nouvelle partie", 105, 130);
    /**
    * On dessine l'image pour nouvelle partie 
    */
    pinceau1.drawImage(quitter,300,10,this);
    pinceau1.drawString("quitter", 330, 130);
    /**
    * On dessine l'image pour quitter le jeu 
    */
  }
}