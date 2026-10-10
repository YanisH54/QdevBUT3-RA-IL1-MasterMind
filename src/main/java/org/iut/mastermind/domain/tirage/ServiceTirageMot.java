package org.iut.mastermind.domain.tirage;

public class ServiceTirageMot {
    private final MotsRepository repository;
    private final ServiceNombreAleatoire nbAleatoire;

    public ServiceTirageMot(MotsRepository repository, ServiceNombreAleatoire nbAleatoire) {
        this.repository = repository;
        this.nbAleatoire = nbAleatoire;
    }

    // retourne le mot reçu du service nombre aléatoire
    public String tirageMotAleatoire() {
        int max = repository.nbMaxMots();
		int index = nbAleatoire.next(max);
        return repository.getMotByIndex(index);
    }
}
