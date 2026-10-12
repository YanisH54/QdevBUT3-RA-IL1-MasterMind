package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    public Lettre lettre(int position) {
        return this.resultat.get(position);
    }

    public void compare(String essai) {
        this.resultat.clear();

        boolean[] lettresUtilisees = new boolean[this.motSecret.length()];

        comparerLettresPlacees(essai, lettresUtilisees);
        comparerLettresRestantes(essai, lettresUtilisees);
    }

    private void comparerLettresPlacees(String essai, boolean[] lettresUtilisees) {
        for (int i = 0; i < this.motSecret.length(); i++) {
            if (this.motSecret.charAt(i) == essai.charAt(i)) {
                this.resultat.add(Lettre.PLACEE);
                lettresUtilisees[i] = true;
            } else {
                this.resultat.add(null);
            }
        }
    }

    private void comparerLettresRestantes(String essai, boolean[] lettresUtilisees) {
        for (int i = 0; i < this.motSecret.length(); i++) {
            if (this.resultat.get(i) == null) {
                int position = trouverLettre(essai.charAt(i), lettresUtilisees);

                if (position != -1) {
                    this.resultat.set(i, Lettre.NON_PLACEE);
                    lettresUtilisees[position] = true;
                } else {
                    this.resultat.set(i, Lettre.INCORRECTE);
                }
            }
        }
    }

    private int trouverLettre(char lettre, boolean[] lettresUtilisees) {
        for (int i = 0; i < this.motSecret.length(); i++) {
            if (!lettresUtilisees[i] && this.motSecret.charAt(i) == lettre) {
                return i;
            }
        }

        return -1;
    }

    public boolean lettresToutesPlacees() {
        boolean placee = true;
        int i = 0;

        while (i < this.resultat.size() && placee) {
            if (this.resultat.get(i) != Lettre.PLACEE) {
                placee = false;
            }
            i++;
        }

        return placee;
    }

    public List<Lettre> lettresResultat() {
        return Collections.unmodifiableList(resultat);
    }
}