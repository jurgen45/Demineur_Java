import java.awt.Color;
import java.io.*;

public class Algo{
    int[][] tabAlgo=null;
public Algo(Case[][] tabCase, int ligne, int colonne){
    tabAlgo=new int[ligne+1][colonne+1];
    System.out.println("Algo");
    try {
           FileOutputStream fichier = new FileOutputStream("Algo.dat");
                DataOutputStream flux = new DataOutputStream(fichier);
    
    

for(int i=0;i<colonne;i++){
        for(int f=0;f<ligne;f++){
            int v=0;
                if (f == ligne - 1 && i == colonne - 1) {
                       
                        for (int k = i - 1; k < i + 1; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }
                            }
                        }
                    } else if (f == 0 && i == 0) {
                       
                        for (int k = i; k < i + 2; k++) {
                            for (int g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }
                            }
                        }
                    } else if (i == 0 && f == ligne - 1) {
                     
                        for (int k = i; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }
                            }
                        }
                    } else if (f == 0 && i == colonne - 1) {
                       
                        for (int k = i - 1; k < i + 1; k++) {
                            for (int g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }
                            }
                        }
                    } else if (f == 0) {
                        
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }

                            }
                        }
                    } else if (i == 0) {
                        
                        for (int k = i; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }

                            }
                        }
                    } else if (i == colonne - 1) {
                       
                        for (int k = i - 1; k < i + 1; k++) {
                            for (int g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                   v++;
                                }
                            }
                        }
                    } else if (f == ligne - 1) {
                        
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }
                            }
                        }
                    } else {

                        for (int k = i - 1; k < i + 2; k++) {
                            for (int g = f - 1; g < f + 2; g++) {
                                
                                if (tabCase[g][k].etatMine() == true) {
                                    v++;
                                }

                            }
                        }
                    }
                    flux.writeInt(v);
                    tabAlgo[f][i]=v;
            }
        }
        flux.close();
         } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }
         
       

       

}
public void LectureAlgo(Case[][] tabCase, int ligne, int colonne,int a,int b)
{
    
        
          
            
            
                
                if (a<colonne&&tabAlgo[b][a]==0) {
                    
                        System.out.println("Oui");

                    System.out.println(a + "/" + b);
                    tabCase[b][a].setBackground(Color.YELLOW);
                    this.LectureAlgo(tabCase, ligne, colonne, a + 1, b);
                    
                     
                }
                else{
                System.out.println("Non");
                System.out.println(a + "/" + b);
                //tabCase[b][a].setBackground(Color.YELLOW);
                return;
                }
               
            
           
       
}
}