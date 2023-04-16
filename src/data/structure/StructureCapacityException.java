package data.structure;

import data.structure.hability.Actionnable;

public class StructureCapacityException extends Exception {
    public StructureCapacityException(){
        super("Error structure capacity");
    }


    public StructureCapacityException(Actionnable actionnable){
        super(actionnable +" capcity is overload!");
    }
}
