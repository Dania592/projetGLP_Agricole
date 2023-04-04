package data.structure.hability.list;

import java.util.ArrayList;

import data.espece.faune.Mouton;
import data.espece.faune.Poule;
import data.espece.faune.Vache;

public class EnclosStorageStructure{
    private ArrayList<Vache> vaches = new ArrayList<>(); 
    private ArrayList<Poule> poules = new ArrayList<>(); 
    private ArrayList<Mouton> moutons = new ArrayList<>(); 

    public EnclosStorageStructure(){

    }

    public void put(Vache vache){
        vaches.add(vache);
    }

    public void put(Poule poule){
        poules.add(poule);
    }

    public void put(Mouton mouton){
        moutons.add(mouton);
    }

    public ArrayList<Vache> getVaches() {
        return vaches;
    }

    public ArrayList<Poule> getPoules() {
        return poules;
    }

    public ArrayList<Mouton> getMoutons() {
        return moutons;
    }

    public void addToVaches(Vache vache){
        vaches.add(vache);
    }

    public void addToPoules(Poule poule){
        poules.add(poule);
    }

    public void addToMoutons(Mouton mouton){
        moutons.add(mouton);
    }


    




}
