package org.iut.mastermind.domain.proposition;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.iut.mastermind.domain.proposition.Lettre.*;

@DisplayName("Test si la proposition:")
public class TestProposition {

    @Test
    @DisplayName("contient une lettre incorrecte")
    public void casLettreIncorrecte() {
        var reponse = new Reponse("S");
        reponse.compare("Z");
        assertResultat(reponse, INCORRECTE);
    }

    @Test
    @DisplayName("contient une lettre placée")
    public void casLettrePlacee() {
        var reponse = new Reponse("S");
        reponse.compare("S");
        assertResultat(reponse, PLACEE);
    }

    @Test
    @DisplayName("contient une lettre incorrecte et une non placée")
    public void casDeuxiemeLettreMalPlacee() {
        var reponse = new Reponse("SO");
        reponse.compare("ZS");
        assertResultat(reponse, INCORRECTE, NON_PLACEE);
    }

    @Test
    @DisplayName("contient une lettre incorrecte, une non placée et une placée")
    public void casCombinaisons() {
        var reponse = new Reponse("SOL");
        reponse.compare("ZSL");
        assertResultat(reponse, INCORRECTE, NON_PLACEE, PLACEE);
    }

    @Test
    @DisplayName("contient toutes les lettres placées")
    void casToutesLettresPlacees() {
        var reponse = new Reponse("SOLID");
        reponse.compare("SOLID");
        assertThat(reponse.lettresToutesPlacees()).isTrue();
    }

    @Test
    @DisplayName("n'a pas toutes les lettres placées")
    void casLettresIncorrectes() {
        var reponse = new Reponse("SOLID");
        reponse.compare("SOL*D");
        assertThat(reponse.lettresToutesPlacees()).isFalse();
    }

    @Test
    @DisplayName("possède le bon nombre de caractères")
    void casAccesLettres() {
        var reponse = new Reponse("SOLID");
        reponse.compare("SOL*D");
        assertThat(reponse.lettresResultat()).hasSize(5);
    }

    private void assertResultat(Reponse reponse, Lettre... resultatAttendu) {
        for (int position = 0; position < resultatAttendu.length; position++) {
            Lettre attendue = resultatAttendu[position];
            assertThat(reponse.lettre(position)).isEqualTo(attendue);
        }
    }
}