package data.myExceptions;

import data.acteur.Personne;

public class IsOverWorkedException extends Exception {
    public IsOverWorkedException(Personne person){
        super(person.getName() + "is overworked");
    }
//TODO a supprimer : on dois passer une personne au construteur
    public IsOverWorkedException(){
        super("Current person is overworked");
    }
}
