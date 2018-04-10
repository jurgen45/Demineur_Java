import java.awt.Color;
import java.io.*;

public class Algo2 {
    int[][] tabAlgo = null;
 int compteur=0;
    public Algo2(Case[][] tabCase, int ligne, int colonne) {
        tabAlgo = new int[ligne + 1][colonne + 1];
        System.out.println("Algo");
        try {
            FileOutputStream fichier = new FileOutputStream("Algo.dat");
            DataOutputStream flux = new DataOutputStream(fichier);

            for (int i = 0; i < colonne; i++) {
                for (int f = 0; f < ligne; f++) {
                    int v = 0;
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
                    tabAlgo[f][i] = v;
                }
            }
            flux.close();
        } catch (FileNotFoundException ex) {
            System.err.println("fichier non trouvé: ecriture");
        } catch (IOException ex) {
            System.out.println("il y'a une erreur: ecriture");
        }

    }

    public void LectureAlgo(Case[][] tabCase, int ligne, int colonne, int a, int b) {

        if (a < colonne - 1 && tabAlgo[b][a + 1] == 0 && tabCase[b][a].getmouvementB() == false) {
            tabCase[b][a].setmouvementB();
            System.out.println("bas");

            System.out.println(a + "/" + b);
            tabCase[b][a].setBackground(Color.YELLOW);
            // tabCase[b][a].setText(compteur + "");
            this.LectureAlgo(tabCase, ligne, colonne, a + 1, b);
            tabCase[b][a].desetmouvementB();
        } else if (b + 1 < ligne && tabAlgo[b + 1][a] == 0 && tabCase[b][a].getmouvementD() == false) {
            System.out.println("droite");
            tabCase[b][a].setmouvementD();
            System.out.println(a + "/" + b);
            tabCase[b][a].setBackground(Color.YELLOW);
            //tabCase[b][a].setText(compteur + "");
            this.LectureAlgo(tabCase, ligne, colonne, a, b + 1);
            tabCase[b][a].desetmouvementD();
        } else if (a > 0 && tabAlgo[b][a - 1] == 0 && tabCase[b][a].getmouvementH() == false) {
            System.out.println("haut");
            tabCase[b][a].setmouvementH();
            System.out.println(a + "/" + b);
            tabCase[b][a].setBackground(Color.YELLOW);
            //tabCase[b][a].setText(compteur + "");
            this.LectureAlgo(tabCase, ligne, colonne, a - 1, b);
            tabCase[b][a].desetmouvementH();

        } else if (b > 0 && tabAlgo[b - 1][a] == 0 && tabCase[b][a].getmouvementG() == false) {
            System.out.println("gauche");
            tabCase[b][a].setmouvementG();
            System.out.println(a + "/" + b);
            tabCase[b][a].setBackground(Color.YELLOW);
            // tabCase[b][a].setText(compteur+"");
            this.LectureAlgo(tabCase, ligne, colonne, a, b - 1);
            tabCase[b][a].desetmouvementG();

        } else {
            tabCase[b][a].setText(compteur + "");
            tabCase[b][a].desetmouvementB();
            tabCase[b][a].desetmouvementD();
            tabCase[b][a].desetmouvementH();
            tabCase[b][a].desetmouvementG();
            System.out.println("Non");
            System.out.println(a + "/" + b);
            tabCase[b][a].setBackground(Color.BLACK);
            System.out.println();
            System.out.println();
            System.out.println("----return----");
            System.out.println();
            System.out.println();
            return;
        }

    }
}