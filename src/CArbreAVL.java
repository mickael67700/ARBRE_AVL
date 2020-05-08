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
    public int insertion_noeud(CNoeudAVL s_arbre, int nombre, CNoeudAVL pere) {
        int retour = 0;
        if (s_arbre == null) //Racine de l'arbre
        { //Création d'un nouveau noeud
            pt_racine = new CNoeudAVL(nombre);
            System.out.printf("-%4d : racine -\n", pt_racine.valeur());
        } else { //Parcours du sous-arbre pour trouver la position d'insertion.
            if (nombre < s_arbre.valeur()) {
                //insertion à gauche
                if (s_arbre.gauche() == null) {
                    //Création d'un nouveau noeud
                    CNoeudAVL NouvNoeud = new CNoeudAVL(nombre);
                    System.out.printf("-%4d : fils gauche de ", NouvNoeud.valeur());
                    System.out.printf(" %4d -\n", s_arbre.valeur());
                    s_arbre.change_gauche(NouvNoeud);
                    retour = VERS_GAUCHE;
                } else retour = insertion_noeud(s_arbre.gauche(), nombre, s_arbre);
                if (!rotation) {
                    if (retour < 0)
                        retour = -retour;
                    s_arbre.change_facteur_equilibre(s_arbre.facteur_equilibre() + retour);
                }
                retour = s_arbre.facteur_equilibre();
            } else { //nombre >s_arbre.valeur()
                if (s_arbre.droit() == null) {
                    //création d'un nouveau noeud
                    CNoeudAVL NouvNoeud = new CNoeudAVL(nombre);
                    System.out.printf("-%4d : fils droid de ", NouvNoeud.valeur());
                    System.out.printf(" %4d -\n", s_arbre.valeur());
                    s_arbre.change_droit(NouvNoeud);
                    retour = VERS_DROITE;
                } else retour = insertion_noeud(s_arbre.droit(), nombre, s_arbre);
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

    // Rotation à gauche.
    public int rotation_gauche(CNoeudAVL s_arbre, CNoeudAVL pere) {
        int retour = 0;
        CNoeudAVL fils_gauche, petit_fils;
        rotation = true;
        System.out.printf("---Rotation à gauche de %d\n", s_arbre.valeur());
        fils_gauche = s_arbre.gauche();
        if (fils_gauche.facteur_equilibre() == VERS_GAUCHE) {
            petit_fils = fils_gauche.gauche();
            System.out.printf("--> G_GAUCHE fils(%d) ", fils_gauche.valeur());
            System.out.printf(" petit fils (%d)\n", petit_fils.valeur());
            // Affectaion au pointeur gauche de s_arbre le pointeur droit de fils gauche.
            s_arbre.change_gauche(fils_gauche.droit());
            // Affectaion s_arbre au pointeur droit de fils gauche.
            fils_gauche.change_droit(s_arbre);
            //On fait pointer le père de s_arbre vers fils_gauche.
            if (pere != null) {
                if (pere.gauche() == s_arbre)
                    pere.change_gauche(fils_gauche);
                else pere.change_droit(fils_gauche);
            } else //On a changé la racine
                pt_racine = fils_gauche;
            // Mise à jour facteur d'équilibre
            fils_gauche.change_facteur_equilibre(EQUILIBRE);
            s_arbre.change_facteur_equilibre(EQUILIBRE);
            retour = (fils_gauche.facteur_equilibre());
        } else if (fils_gauche.facteur_equilibre() == VERS_DROITE) {
            petit_fils = fils_gauche.droit();
            System.out.printf("--> G_DROITE fils (%d) ", fils_gauche.valeur());
            System.out.printf(" petit_fils (%d)\n", petit_fils.valeur());
            // Affectaion au pointeur droit de fils gauche le pointeur gauche de petit_fils
            fils_gauche.change_droit(petit_fils.gauche());
            // Affectaion au pointeur gauche de petit_fils , fils_gauche
            petit_fils.change_gauche(fils_gauche);
            // Affectation au pointeur gauche de s_arbre le pointeur droit de petit_fils
            s_arbre.change_gauche(petit_fils.droit());
            // Affectation au pointeur droit de petit_fils, s_arbre
            petit_fils.change_droit(s_arbre);
            // Pointage du père de s_arbre sur petit_fils
            if (pere != null) {
                if (pere.gauche() == s_arbre) pere.change_gauche(petit_fils);
                else pere.change_droit(petit_fils);
            } else // on a changé la racine
                pt_racine = petit_fils;
            // Mise à jour facteur d'équilibre
            if (petit_fils.facteur_equilibre() == VERS_GAUCHE) {
                s_arbre.change_facteur_equilibre(VERS_DROITE);
                fils_gauche.change_facteur_equilibre(EQUILIBRE);
            } else if (petit_fils.facteur_equilibre() == EQUILIBRE) {
                s_arbre.change_facteur_equilibre(EQUILIBRE);
                fils_gauche.change_facteur_equilibre(EQUILIBRE);
            } else if (petit_fils.facteur_equilibre() == VERS_DROITE) {
                s_arbre.change_facteur_equilibre(EQUILIBRE);
                fils_gauche.change_facteur_equilibre(VERS_GAUCHE);
            }
            petit_fils.change_facteur_equilibre(EQUILIBRE);
            retour = petit_fils.facteur_equilibre();
        }
        return retour;
    }

    public int rotation_droite(CNoeudAVL s_arbre, CNoeudAVL pere) {
        int retour = 0;
        CNoeudAVL fils_droit, petit_fils;
        rotation = true;
        System.out.printf("--- Rotation à droite de %d\n", s_arbre.valeur());
        fils_droit = s_arbre.droit();
        if (fils_droit.facteur_equilibre() == VERS_DROITE) {
            petit_fils = fils_droit.droit();
            System.out.printf("---> D_DROITE fils (%d) ", fils_droit.valeur());
            System.out.printf(" petit_fils (%d) \n", petit_fils.valeur());
            //On affecte au pointeur droit de s_arbre, le pointeur gauche de fils_droit
            s_arbre.change_droit(fils_droit.gauche());
            // On affecte s_arbre au pointeur droit de fils_gauche
            fils_droit.change_gauche(s_arbre);
            // On fait pointer le père de s_arbre vers fils_droit
            if (pere != null) {
                if (pere.gauche() == s_arbre)
                    pere.change_gauche(fils_droit);
                else pere.change_droit(fils_droit);
            } else //on a changé la racine
                pt_racine = fils_droit;
            // On met à jour les facteurs d'équilibre
            fils_droit.change_facteur_equilibre(EQUILIBRE);
            s_arbre.change_facteur_equilibre(EQUILIBRE);
            retour = fils_droit.facteur_equilibre();
        } else if (fils_droit.facteur_equilibre() == VERS_GAUCHE) {
            petit_fils = fils_droit.gauche();
            System.out.printf("--> D_GAUCHE fils (%d)", fils_droit.valeur());
            System.out.printf(" petit_fils (%d) \n", petit_fils.valeur());
            //Affectation au pointeur gauche de fils_droit le pointeur droit de petit_fils
            fils_droit.change_gauche(petit_fils.droit());
            // Affection au pointeur droit de s_arbre le pointeur gauche de petit_fils
            s_arbre.change_droit(petit_fils.gauche());
            //On affecte au pointeur gauche de petit_fils, s_arbre
            petit_fils.change_gauche(s_arbre);
            //On fait pointer le père de s_arbre sur petit_fils
            if (pere != null) {
                if (pere.gauche() == s_arbre)
                    pere.change_gauche(petit_fils);
                else pere.change_droit(petit_fils);
            } else //On change de racine
                pt_racine = petit_fils;
            // Mise à jour des facteurs d'équilibre
            if (petit_fils.facteur_equilibre() == VERS_DROITE) {
                s_arbre.change_facteur_equilibre(VERS_GAUCHE);
                fils_droit.change_facteur_equilibre(EQUILIBRE);
            } else if (petit_fils.facteur_equilibre() == EQUILIBRE) {
                s_arbre.change_facteur_equilibre(EQUILIBRE);
                fils_droit.change_facteur_equilibre(EQUILIBRE);
            } else if (petit_fils.facteur_equilibre() == VERS_GAUCHE) {
                s_arbre.change_facteur_equilibre(EQUILIBRE);
                fils_droit.change_facteur_equilibre(VERS_DROITE);
            }
            petit_fils.change_facteur_equilibre(EQUILIBRE);
            retour = petit_fils.facteur_equilibre();
        }
        return retour;
    }

}


