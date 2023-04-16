package data.structure.hability.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import data.structure.hability.Productif;

public class CollectableList implements ActionnableList<Productif>,Serializable {
    private ArrayList<Productif> productifs = new ArrayList<>();

    public CollectableList(){}

    public CollectableList(Productif productif) {
        productifs.add(productif);
    }

    @Override
    public void add(Productif elementToAdd) {
        productifs.add(elementToAdd);
    }

    @Override
    public Productif get(int index) {
        return productifs.get(index);
    }

    @Override
    public int size(){
        return productifs.size();
    }

    @Override
    public Iterator<Productif> iterator() {
        return productifs.iterator();
    }   
    
}
