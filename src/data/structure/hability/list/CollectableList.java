package data.structure.hability.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import data.structure.hability.ProductifPlace;

public class CollectableList implements ActionnableList<ProductifPlace>,Serializable {
    private ArrayList<ProductifPlace> productifs = new ArrayList<>();

    public CollectableList(){}

    public CollectableList(ProductifPlace productif) {
        productifs.add(productif);
    }

    @Override
    public void add(ProductifPlace elementToAdd) {
        productifs.add(elementToAdd);
    }

    @Override
    public ProductifPlace get(int index) {
        return productifs.get(index);
    }

    @Override
    public int size(){
        return productifs.size();
    }

    @Override
    public Iterator<ProductifPlace> iterator() {
        return productifs.iterator();
    }   
    
}
