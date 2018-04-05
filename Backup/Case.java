import javax.swing.*;
import java.awt.*;
public class Case extends JButton{
    private boolean mine;
    private boolean valide;
    private int nb;
public Case(boolean etatmine)
{
    super();
    this.mine=etatmine;
    this.valide=false;
    this.nb = 0;

}
public boolean etatMine()
{
    return this.mine;
}

public void setValide()
{
    this.valide=true;
}

public boolean getValide() {
    return this.valide;
}
public void setNb()
{
    this.nb++;
}

public int getNb() {
    return this.nb;
}

    public String getNbStr() {
        int k= this.nb+1;
        return ""+k;
    }

}