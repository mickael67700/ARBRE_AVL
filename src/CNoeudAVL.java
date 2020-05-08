/*
En informatique théorique, les arbres AVL ont été historiquement les premiers arbres binaires de recherche automatiquement équilibrés.
Dans un arbre AVL, les hauteurs des deux sous-arbres d'un même nœud diffèrent au plus de un. La recherche, l'insertion et la suppression sont toutes
en O(log 2(n)) dans le pire des cas.
L'insertion et la suppression nécessitent d'effectuer des rotations.
Les opérations de base d'un arbre AVL mettent en œuvre généralement les mêmes algorithmes que pour un arbre binaire de recherche,
à ceci près qu'il faut ajouter des rotations de rééquilibrage nommées « rotations AVL ».
 */
public class CNoeudAVL {
    private CNoeudAVL pt_gauche, pt_droit;
    private  int val_donnee, val_facteur_eq;
    //Constructeur

    public CNoeudAVL(int nb) {
        val_donnee = nb;
        val_facteur_eq = 0;
        pt_gauche = null;
        pt_droit = null;
    }
    public void change_gauche(CNoeudAVL pt){
        this.pt_gauche = pt;
    }
    public void change_droit(CNoeudAVL pt){
        this.pt_droit = pt;
    }
    public void change_valeur(int _valeur){
        this.val_donnee = _valeur;
    }
    public void change_facteur_equilibre(int _facteur_equilibre){
        this.val_facteur_eq = _facteur_equilibre;
    }
    public CNoeudAVL gauche(){
        return this.pt_gauche;
    }
    public CNoeudAVL droit(){
        return this.pt_droit;
    }
    public int valeur(){
        return this.val_donnee;
    }
    public int facteur_equilibre(){
        return this.val_facteur_eq;
    }
}
