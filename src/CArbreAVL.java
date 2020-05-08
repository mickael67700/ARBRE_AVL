public class CArbreAVL {
    static final int EQUILIBRE = 0;
    static final int VERS_GAUCHE = +1;
    static final int VERS_DROITE = -1;
    private CNoeudAVL pt_racine;
    boolean rotation;

    public CArbreAVL() {
        pt_racine = null;
        rotation = false;
    }

    public CNoeudAVL racine() {
        return pt_racine;
    }

    public void change_rotation(boolean _rotation) {
        this.rotation = _rotation;
    }

    //Méthode insertion noeud.
    public int insertion_noeud(CNoeudAVL s_arbre, int nombre, CNoeudAVL pere)
    { int retour = 0;
        if (s_arbre == null) //Racine de l'arbre
        { //Création d'un nouveau noeud
            pt_racine = new CNoeudAVL(nombre);
            System.out.printf("-%4d : racine -\n", pt_racine.valeur());
        } else
            { //Parcours du sous-arbre pour trouver la position d'insertion.
            if (nombre < s_arbre.valeur())
            {
                //insertion à gauche
                if (s_arbre.gauche() == null)
                {
                    //Création d'un nouveau noeud
                    CNoeudAVL NouvNoeud = new CNoeudAVL(nombre);
                    System.out.printf("-%4d : fils gauche de ", NouvNoeud.valeur());
                    System.out.printf(" %4d -\n", s_arbre.valeur());
                    s_arbre.change_gauche(NouvNoeud);
                    retour = VERS_GAUCHE;
                }
                else retour = insertion_noeud(s_arbre.gauche(), nombre, s_arbre);
                if (!rotation) {
                    if (retour < 0)
                        retour = -retour;
                    s_arbre.change_facteur_equilibre(s_arbre.facteur_equilibre() + retour);
                }
                    retour = s_arbre.facteur_equilibre();
                }
            else
                { //nombre >s_arbre.valeur()
                    if (s_arbre.droit() == null)
                    {
                        //création d'un nouveau noeud
                        CNoeudAVL NouvNoeud = new CNoeudAVL(nombre);
                        System.out.printf("-%4d : fils droid de ", NouvNoeud.valeur());
                        System.out.printf(" %4d -\n", s_arbre.valeur());
                        s_arbre.change_droit(NouvNoeud);
                        retour = VERS_DROITE;
                    }
                    else retour = insertion_noeud(s_arbre.droit(), nombre, s_arbre);
                    if (!rotation) {
                        if (retour < 0)
                            retour = -retour;
                        s_arbre.change_facteur_equilibre(s_arbre.facteur_equilibre() - (retour));
                    }
                        retour = s_arbre.facteur_equilibre();
                    }
                    if (retour > VERS_GAUCHE)
                        retour = rotation_gauche(s_arbre, pere);
                    else if (retour < VERS_DROITE)
                        retour = rotation_droite(s_arbre, pere);
                }
                return retour;
            }
        }


