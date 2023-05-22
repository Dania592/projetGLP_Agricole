package data.structure;

import data.structure.hability.Actionnable;
import data.structure.hability.ProductifPlace;
import data.structure.hability.SpecialActionPerformer;


/**
 * Une structures d'action est {@link Actionnable}
 */
public  abstract class StructureAction extends Structure implements ProductifPlace, SpecialActionPerformer{
	public StructureAction( String reference ) {
		super(reference);
	}



}
