package kariminf.nalanpar.lang;

import java.util.ArrayList;

import kariminf.sentrep.UnivMap;
import kariminf.sentrep.types.Comparison;
import kariminf.sentrep.types.Determiner;
import kariminf.sentrep.types.Modality;
import kariminf.sentrep.types.Pronoun;
import kariminf.sentrep.types.SentMood;
import kariminf.sentrep.types.VerbTense;
import kariminf.sentrep.types.Pronoun.Gender;
import kariminf.sentrep.types.Pronoun.Head;
import kariminf.sentrep.types.Pronoun.Person;
import kariminf.sentrep.types.Relation.Adpositional;
import kariminf.sentrep.types.Relation.Adverbial;
import kariminf.sentrep.types.Relation.Relative;

public class Eng2UnivMapper implements UnivMap {
	 

	private static enum SubjectPronoun {
		I (Person.FIRST, Pronoun.Number.SINGLE, Gender.NEUTRAL),
		YOU(Person.SECOND, Pronoun.Number.NONE, Gender.NEUTRAL),
		HE(Person.THIRD, Pronoun.Number.SINGLE, Gender.MALE),
		SHE(Person.THIRD, Pronoun.Number.SINGLE, Gender.FEMALE),
		IT(Person.THIRD, Pronoun.Number.SINGLE, Gender.NEUTRAL),
		WE(Person.SECOND, Pronoun.Number.PLURAL, Gender.NEUTRAL),
		THEY(Person.THIRD, Pronoun.Number.PLURAL, Gender.NEUTRAL);
		
		private static final ArrayList<String> list = new ArrayList<String>();
		private final Pronoun pronoun = new Pronoun();
		private SubjectPronoun(Person p, Pronoun.Number n, Gender g){
			pronoun.setProperty(Head.SUBJECTIVE);
			pronoun.setProperty(p);
			pronoun.setProperty(n);
			pronoun.setProperty(g);
		}
		
		static {
			for (SubjectPronoun sp: SubjectPronoun.values())
				list.add(sp.name());
		}
		
		public Pronoun getPronoun(){
			return pronoun;
		}
		
		public static boolean isSubjectivePronoun(String value){
			//value = value.toUpperCase();
			//You have to convert it outside
			return list.contains(value);
		}
	}
	
	@Override
	public VerbTense mapTense(String langTense) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Modality mapModal(String langModal) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adpositional mapAdposition(String langAdpos) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Relative mapRelative(String langRel) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Adverbial mapAdverbial(String langAdv) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Determiner mapDeterminer(String langDet) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Comparison mapComparison(String comp) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Pronoun mapPronoun(String pronoun) {
		pronoun = pronoun.toUpperCase();
		//Pronoun thePronoun = new Pronoun();
		if (SubjectPronoun.isSubjectivePronoun(pronoun)){
			return SubjectPronoun.valueOf(pronoun).getPronoun();
		}
		return null;
	}

	@Override
	public SentMood mapMood(String mood) {
		// TODO Auto-generated method stub
		return null;
	}

}
