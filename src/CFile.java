//Classe pour la File
public class CFile {
    static final int TAILLE = 100;
    private CNoeudAVL[] file;
    private int debut_file, fin_file;

    public CFile() {
        debut_file = 0;
        fin_file = 0;
        file = new CNoeudAVL[TAILLE];
    }
    public void initialiser_file(){
        debut_file = 0;
        fin_file = 0;
    }
    public void enfiler(CNoeudAVL NdCourant){
        file[fin_file++] = NdCourant;
    }
    public CNoeudAVL defiler(){
        return file[debut_file++];
    }
    public boolean file_vide(){
        return debut_file == fin_file;
    }
}
