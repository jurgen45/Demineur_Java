import javax.swing.*;
import java.awt.*;
public class Case extends JButton{
    private boolean mine;
    private boolean valide;
public Case(boolean etatmine)
{
    super();
    this.mine=etatmine;

}
public boolean etatMine()
{
    return this.mine;
}

public void setValide()
{
    this.valide=true;
}

}