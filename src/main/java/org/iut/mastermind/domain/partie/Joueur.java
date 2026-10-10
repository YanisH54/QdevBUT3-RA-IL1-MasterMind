package org.iut.mastermind.domain.partie;

public record Joueur(String nom) {

    public String getNom() {
        return this.nom;
    }

    @Override
    public boolean equals(Object o) {
        return o.equals(this.nom);
    }

}
