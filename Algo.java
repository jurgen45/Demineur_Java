import java.awt.Color;
import java.io.*;
public class Algo{
int[][] tabAlgo=null;
/**
 * @param tabCase est le tableau de cases de la partie
 * @param ligne le nombre de ligne de la partie
 * @param colonne le nombe de colonne de la partie
 */
    public Algo(Case[][] tabCase, int ligne, int colonne){
        /**
         * On stocke dans le tableau tabAlgo le nombre de mines detecter autour de chque case
         */
        tabAlgo=new int[ligne][colonne];
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
                        
                        tabAlgo[f][i]=v;
                }
            }
           
             
           
    
           
    
    }

    /**
     * @param tabCase est le tableau de cases de la partie
     * @param ligne   le nombre de ligne de la partie
     * @param colonne le nombe de colonne de la partie
     * @param a la position y de tabCase a l'appel recursif
     * @param b la position x de tabCase a l'appel recursif
     */
public void LectureAlgo(Case[][] tabCase, int ligne, int colonne,int a,int b)
{
    /**
     * On detecte de maniere recursive les case adjacente,si aucune mines n'est presente on passe a la case en bas puis a droite 
     * puis en haut puis a gauche
     * Si une case a deja ete visiter alors on la bloque avec Valide
     */
        
            if (tabCase[b][a].getValide()==true) {
                return;
            }

           
            tabCase[b][a].setValide();
                
                if (a<colonne-1&&tabAlgo[b][a+1]==0) {
                    
                        //System.out.println("bas");

                    
                    tabCase[b][a].setBackground(Color.WHITE);
                    this.LectureAlgo(tabCase, ligne, colonne, a + 1, b);
            
                }else if(a<colonne-1){
                    tabCase[b][a+1].setBackground(Color.WHITE);
                    tabCase[b][a+1].setValide();
                    tabCase[b][a+1].setNbtoFile(tabAlgo[b][a+1]);
                    tabCase[b][a+1].setText(tabCase[b][a+1].getNbStr());
                }
                 if(b+1<ligne&&tabAlgo[b+1][a]==0 ){
                    //System.out.println("droite");

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a, b+1);
            
                } else if (b + 1 < ligne) {
            tabCase[b+1][a].setBackground(Color.WHITE);
            tabCase[b+1][a].setValide();
            tabCase[b+1][a].setNbtoFile(tabAlgo[b+1][a]);
            tabCase[b+1][a].setText(tabCase[b+1][a].getNbStr());
        }

                 if (a>0 && tabAlgo[b][a-1] == 0 ) {
            //System.out.println("haut");

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a-1, b);
            

        } else if(a>0) {
            tabCase[b][a - 1].setBackground(Color.WHITE);
            tabCase[b][a - 1].setValide();
            tabCase[b][a - 1].setNbtoFile(tabAlgo[b][a - 1]);
            tabCase[b][a - 1].setText(tabCase[b][a - 1].getNbStr());
        } 
        
        if (b >0 && tabAlgo[b -1][a] == 0 ) {
            //System.out.println("gauche");

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a, b - 1);
            
            
        } else if (b > 0) {
            tabCase[b - 1][a].setBackground(Color.WHITE);
            tabCase[b - 1][a].setValide();
            tabCase[b - 1][a].setNbtoFile(tabAlgo[b - 1][a]);
            tabCase[b - 1][a].setText(tabCase[b - 1][a].getNbStr());
        }
        
        
        if(b-1>0&&a-1>0&&tabAlgo[b -1][a-1] == 0)
        {
            

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a - 1, b - 1);
        }else if (b-1 > 0 && a-1 > 0) {
            tabCase[b - 1][a-1].setBackground(Color.WHITE);
            tabCase[b - 1][a-1].setValide();
            tabCase[b - 1][a-1].setNbtoFile(tabAlgo[b - 1][a-1]);
            tabCase[b - 1][a-1].setText(tabCase[b - 1][a-1].getNbStr());
        }
             
        if (b + 1 < ligne && a-1 > 0 && tabAlgo[b + 1][a - 1] == 0) {
            

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a - 1, b + 1);
        } else if (b + 1 < ligne && a-1 > 0) {
            tabCase[b + 1][a - 1].setBackground(Color.WHITE);
            tabCase[b + 1][a - 1].setValide();
            tabCase[b + 1][a - 1].setNbtoFile(tabAlgo[b + 1][a - 1]);
            tabCase[b + 1][a - 1].setText(tabCase[b + 1][a - 1].getNbStr());
        }

        if (b-1>0 && a+1 < colonne  && tabAlgo[b - 1][a + 1] == 0) {
            

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a + 1, b-1);
        } else if (b-1 > 0 && a+1 < colonne ) {
            tabCase[b-1][a + 1].setBackground(Color.WHITE);
            tabCase[b-1][a + 1].setValide();
            tabCase[b-1][a + 1].setNbtoFile(tabAlgo[b-1][a + 1]);
            tabCase[b-1][a + 1].setText(tabCase[b-1][a + 1].getNbStr());
        }

        if (b+1 < ligne && a+1 < colonne && tabAlgo[b + 1][a + 1] == 0) {
            

            
            tabCase[b][a].setBackground(Color.WHITE);
            this.LectureAlgo(tabCase, ligne, colonne, a + 1, b + 1);
        } else if (b+1< ligne && a+1 < colonne) {
            tabCase[b + 1][a + 1].setBackground(Color.WHITE);
            tabCase[b + 1][a + 1].setValide();
            tabCase[b + 1][a + 1].setNbtoFile(tabAlgo[b + 1][a + 1]);
            tabCase[b + 1][a + 1].setText(tabCase[b + 1][a + 1].getNbStr());
        }


               
            
           
       
}

    /**
     * @param tabCase est le tableau de cases de la partie
     * @param ligne   le nombre de ligne de la partie
     * @param colonne le nombe de colonne de la partie
     * @param i       la position y de tabCase a l'appui du bouton
     * @param f       la position x de tabCase a l'appui du bouton
     */
public void recherche(Case[][] tabCase,int f,int i,int ligne,int colonne)
{
    /**
     * On cherche autour du JButton les cases minée en prenant en compte les angles 
     */
    int g,k;
        tabCase[f][i].setBackground(Color.WHITE);
        
     if (f == ligne - 1 && i == colonne - 1) {
                        //System.out.println("angle bas droite");
                        for (  k = i - 1; k < i + 1; k++) {
                            for (  g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else if (f == 0 && i == 0) {
                        //System.out.println("angle haut gauche");
                        for (  k = i; k < i + 2; k++) {
                            for (  g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                   // System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else if (i == 0 && f == ligne - 1) {
                        //System.out.println("angle haut droite");
                        for (  k = i; k < i + 2; k++) {
                            for (  g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else if (f == 0 && i == colonne - 1) {
                        //System.out.println("angle bas gauche");
                        for (  k = i - 1; k < i + 1; k++) {
                            for (  g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                   // System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else if (f == 0) {
                       // System.out.println("Ligne de gauche");
                        for (  k = i - 1; k < i + 2; k++) {
                            for (  g = f; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }

                            }
                        }
                    } else if (i == 0) {
                        //System.out.println("Ligne de haut");
                        for (  k = i; k < i + 2; k++) {
                            for (  g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }

                            }
                        }
                    } else if (i == colonne - 1) {
                        //System.out.println("Ligne du bas");
                        for (  k = i - 1; k < i + 1; k++) {
                            for (  g = f - 1; g < f + 2; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else if (f == ligne - 1) {
                       //System.out.println("Ligne du bas");
                        for (  k = i - 1; k < i + 2; k++) {
                            for (  g = f - 1; g < f + 1; g++) {
                                if (tabCase[g][k].etatMine() == true) {
                                    //System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }
                            }
                        }
                    } else {

                        for (  k = i - 1; k < i + 2; k++) {
                            for (  g = f - 1; g < f + 2; g++) {
                                //System.out.println("position: " + g + "/" + k);
                                if (tabCase[g][k].etatMine() == true) {
                                   // System.out.println("mine");
                                    tabCase[f][i].setNb();
                                    tabCase[f][i].setText(tabCase[f][i].getNbStr());
                                    tabCase[f][i].setValide();
                                }

                            }
                        }
                    }
                    if (tabCase[f][i].getNb()==0) {
                        tabCase[f][i].setBackground(Color.WHITE);
                        this.LectureAlgo(tabCase, ligne, colonne,i, f);
                    }
                
}

}