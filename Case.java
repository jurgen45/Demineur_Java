import javax.swing.*;
import java.awt.*;
public class Case extends JButton{
    private boolean mine;
public Case(boolean etatmine)
{
    super();
    this.mine=etatmine;

}
public boolean etatMine()
{
    return this.mine;
}

}