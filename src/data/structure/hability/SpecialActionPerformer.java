package data.structure.hability;

import data.myExceptions.UnknownActivityException;
import data.planning.Activity;

public interface SpecialActionPerformer extends Actionnable{
    boolean canPerformSpecialAction(Activity activity) throws UnknownActivityException;
}
