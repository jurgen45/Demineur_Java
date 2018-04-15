import java.io.*;
public class RappelJava
{
    public void lireOctet(){
        try {
              DataInputStream flux=new DataInputStream(new FileInputStream(new File("save.txt")));//ouvre le fichier
              int r=0;
              while (flux.available()>0) {//boucle tant que le nombre de byte du fichier sont + grand que 0
                  r=flux.readInt();//Stocke la valeur du fichier dans r
                  System.out.println(r);//affiche r
              }
              flux.close();//ferme le fichier
            }
            catch (FileNotFoundException exception) //donne l'exception si aucun fichier n'est trouvé
            {
            System.out.println ("Le fichier n'a pas été trouvé");
             
            }catch (IOException exception) { //donne l'exception d'un probleme de lecture
                System.out.println("Erreur lors de la lecture : " + exception.getMessage());
            }
    }

    public void ecrireOctet(){
        try {
            DataOutputStream flux=new DataOutputStream(new BufferedOutputStream( new FileOutputStream(new File("foo.bin"))));
        for(int i=0;i<50;i++)
            {
        double alea=Math.random() * ( 100 - 0 );
        int aleafinal=(int) alea;//genere 50 Int aleatoire
        flux.writeInt(aleafinal);//ecrit chque Int
        }
        flux.close();//ferme le fichier
        }
            catch (FileNotFoundException exception) //donne l'exception si aucun fichier n'est trouvé
            {
            System.out.println ("Le fichier n'a pas été trouvé");
             
            }catch (IOException exception) { //donne l'exception d'un probleme de lecture
                System.out.println("Erreur lors de la lecture : " + exception.getMessage());
            }
        
    }

    public int lireClavier(){
        int k=0;
        try {
            InputStreamReader entree = new InputStreamReader(System.in);
            BufferedReader clavier = new BufferedReader(entree);
            String f=clavier.readLine();
            k=Integer.parseInt(f);
            System.out.println(k);
            
        } catch (IOException exception) {
             System.out.println("Erreur lors de la lecture : " + exception.getMessage());
        }
        return k;
    }

    public void lireCarac(){
        String s="a";
        try {
                InputStream ips = new FileInputStream("save.txt");
                InputStreamReader entree = new InputStreamReader(ips);
                BufferedReader buf = new BufferedReader(entree);
                while (s!=null) {
                    s=buf.readLine();
                    System.out.println(s);
                }
                entree.close();
        } catch (FileNotFoundException exception) //donne l'exception si aucun fichier n'est trouvé
            {
            System.out.println ("Le fichier n'a pas été trouvé");
             
            }catch (IOException exception) { //donne l'exception d'un probleme de lecture
                System.out.println("Erreur lors de la lecture : " + exception.getMessage());
            }
       
    }

    public void ecrireCarac()
    {
        try {
            FileWriter output = new FileWriter("foo.txt");
            output.write("Hello lorem oe ls cd");
            output.close();
        } catch (FileNotFoundException exception) //donne l'exception si aucun fichier n'est trouvé
            {
            System.out.println ("Le fichier n'a pas été trouvé");
             
            }catch (IOException exception) { //donne l'exception d'un probleme de lecture
                System.out.println("Erreur lors de la lecture : " + exception.getMessage());
            }
    }

    public static void main(String[] args) {
        RappelJava t=new RappelJava();
        
        t.lireOctet();
        }
        
}