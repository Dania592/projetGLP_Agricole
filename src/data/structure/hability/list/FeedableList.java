package data.structure.hability.list;

import java.util.ArrayList;
import java.util.Iterator;

import data.structure.hability.Feedable;

public class FeedableList implements ActionnableList<Feedable> {
    private ArrayList<Feedable> feedables = new  ArrayList<>();


    public void add(Feedable elementToAdd) {
        feedables.add(elementToAdd);
    }

    @Override
    public Feedable get(int index) {
        return feedables.get(index);
    }

    public FeedableList(Feedable feedable) {
        feedables.add(feedable);
    }

    public FeedableList() {
    }

    @Override
    public int size() {
        return feedables.size();
    }

    @Override
    public Iterator<Feedable> iterator() {
        return feedables.iterator();
    }
    
}
