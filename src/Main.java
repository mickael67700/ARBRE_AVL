import java.util.Scanner;

public class Main {
    public static void main(String[]args) {
        int nbrcps[] = new int[1];
        CArbreAVL ArbreAVL = new CArbreAVL();
        System.out.printf("Entrez une suite de valeurs entières \n -1 pour fin\n");
        // Contruction de l'arbre de recherche
        Scanner Entree = new Scanner(System.in);
        int nb = 0;
        while (nb != -1) {
            nb = Entree.nextInt();
            if (nb != -1) { //Insertion dans l'arbre
                ArbreAVL.change_rotation(false);
                ArbreAVL.insertion_noeud(ArbreAVL.racine(), nb, null);
                // Affichage de l'état de l'arbre
                ArbreAVL.ParcoursParNiveau(ArbreAVL.racine());
                System.out.printf("\n");
            }
        }
        System.out.printf("\nEntrez le nombre entier à rechercher : ");
        nb = Entree.nextInt();
        Entree.close();
        // Recherche dans l'arbre
        CNoeudAVL noeud = ArbreAVL.recherche_arbre(nb, nbrcps);
        if (noeud == null)
            System.out.printf(" Valeur non trouvée \n");
        else
            System.out.printf("%d trouvée en %d itérations \n", nb, nbrcps[0]);
    }
}
