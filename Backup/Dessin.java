import javax.swing.*;
import java.awt.*;
 
public class Dessin extends JComponent {
  private Image play;
  private Image replay;
  private Image quitter;
  @Override
  public void paintComponent(Graphics pinceau) {
    Graphics pinceau1 = pinceau.create();
    play = Toolkit.getDefaultToolkit().getImage("play.png");
    replay = Toolkit.getDefaultToolkit().getImage("replay.png");
    quitter = Toolkit.getDefaultToolkit().getImage("quitter.png");
    if (this.isOpaque()) {
      pinceau1.setColor(this.getBackground());
      pinceau1.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
    Font f = new Font("Arial", Font.ROMAN_BASELINE  , 15);
    pinceau1.setFont(f);
    pinceau1.drawImage(play,10,10,this);
    pinceau1.drawString("nouvelle partie", 15, 130);
    pinceau1.drawImage(replay,200,10,this);
    pinceau1.drawString("reprendre la partie", 200, 130);
    pinceau1.drawImage(quitter,390,10,this);
    pinceau1.drawString("quitter", 410, 130);
  }
}