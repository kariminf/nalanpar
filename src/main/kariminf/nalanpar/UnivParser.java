package kariminf.nalanpar;

import kariminf.nalanpar.Types.Featured;
import kariminf.nalanpar.Types.Phrasal;
import kariminf.nalanpar.Types.PhrasalFeature;
import kariminf.nalanpar.Types.Posable;

public abstract class UnivParser {
	
	protected ParseHandler handler;
	protected POSTransformer posTrans;
	
	public UnivParser(ParseHandler handler, POSTransformer posTrans){
		this.handler = handler;
		this.posTrans = posTrans;
	}
	
	/**
	 * 
	 * @param text
	 */
	public void parse(String text){
		if (! prepare(text))
			return;
		while (next()){
			Element e  = getElement();
			elementProcess(e);
		}
	}
	
	private void elementProcess(Element e){
		if (e == null)
			return;
		
		Posable p = e.getPos();
		if (p == null)
			return;
		
		if (p instanceof Phrasal){
			Featured f = e.getFeature();
			
			if (f == null || ! (f instanceof PhrasalFeature))
				return;
			
			Phrasal phrasal = (Phrasal) p;
			
			switch(phrasal){
			case NP:
				if (((PhrasalFeature)f).getbegin())
					handler.beginNP();
				else
					handler.endNP();
				return;
			case VP:
				if (((PhrasalFeature)f).getbegin())
					handler.beginVP();
				else
					handler.endVP();
				return;
				
			case ADJP:
				if (((PhrasalFeature)f).getbegin())
					handler.beginAdjP();
				else
					handler.endAdjP();
				return;
				
			case ADVP:
				if (((PhrasalFeature)f).getbegin())
					handler.beginAdvP();
				else
					handler.endAdvP();
				return;
				
			default:
				return;
			}
				
			
			
		}
		
		
	}
	
	protected abstract boolean prepare(String text);
	protected abstract boolean next();
	protected abstract Element getElement();
	
	public static class Element {
		private String val = "";
		private Posable pos;
		private Featured feat;
		
		public Element (String val, Posable pos, Featured feat){
			this.val = val;
			this.pos = pos;
			this.feat = feat;
		}
		
		public String getVal(){
			return val;
		}
		
		public Posable getPos(){
			return pos;
		}
		
		public Featured getFeature(){
			return feat;
		}
	}

}
