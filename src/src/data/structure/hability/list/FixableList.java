package data.structure.hability.list;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import data.structure.hability.Fixable;
public class FixableList implements ActionnableList<Fixable> , Serializable{
    private ArrayList<Fixable> fixables = new ArrayList<>();

    @Override
    public void add(Fixable elementToAdd) {
        fixables.add(elementToAdd);
    }

    @Override
    public Fixable get(int index) {
        return fixables.get(index);
    }

    public FixableList(Fixable fixable) {
        fixables.add(fixable);
    }

    public FixableList() {
    }

    @Override
    public int size() { 
        return fixables.size();
    }

    @Override
    public Iterator<Fixable> iterator() {
        return fixables.iterator();
    }
    
}
