package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();
    private int position;

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    // on récupère la lettre à la position spécifiée en paramètre dans le résultat
    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        for(int i = 0 ; i<this.motSecret.length(); i++){
            if (this.motSecret.charAt(i) == essai.charAt(i)){
                this.resultat.add(Lettre.PLACEE);
            } else if (this.motSecret.indexOf(essai.charAt(i)) != -1) {
                this.resultat.add(Lettre.NON_PLACEE);
            }
            else{
                this.resultat.add(Lettre.INCORRECTE);
            }
        }
    }

    // vrai si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        boolean placee = true;
        int i = 0;

        while(i < this.motSecret.length() && placee){
            if (this.resultat.get(i) != Lettre.PLACEE){
                placee = false;
            }
            i++;
        }
        return placee;
    }

	// méthode obligatoire - à ne pas supprimer
    public List<Lettre> lettresResultat() {
        return Collections.unmodifiableList(resultat);
    }

    // renvoie le statut du caractère (incorrect, mal placé, placé)
    private Lettre evaluationCaractere(char carCourant) {
        position = this.motSecret.indexOf(carCourant);
        if (position != -1){
            return this.resultat.get(carCourant);
        }
        return Lettre.INCORRECTE;
    }

    // vrai si le caractère est présent dans le mot secret
    private boolean estPresent(char carCourant) {
        int pos = this.motSecret.indexOf(carCourant);
        return pos != -1;
    }

    // vrai si le caractère est placé dans le mot secret
    private boolean estPlace(char carCourant) {
        boolean estPlacee = false;
        if(estPresent(carCourant)){
            int pos = this.motSecret.indexOf(carCourant);
            estPlacee =  this.resultat.get(pos) == Lettre.PLACEE;
        }

        return estPlacee;
    }

    public List<Lettre> getResultat() {
        return resultat;
    }
}
