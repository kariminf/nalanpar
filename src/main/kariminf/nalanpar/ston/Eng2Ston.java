package kariminf.nalanpar.ston;

import kariminf.nalanpar.Lang2Univ;
import kariminf.sentrep.univ.types.Relation;

public class Eng2Ston implements Lang2Univ {

	public Eng2Ston() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Relation getRelation(String prep, String word) {
		// TODO Complete the relartions
		return Relation.P_AT;
	}

	
	

}
