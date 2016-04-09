package kariminf.nalanpar;

import kariminf.nalanpar.Types.Featured;
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
		
		
		
	}
	
	protected abstract boolean prepare(String text);
	protected abstract boolean next();
	protected abstract Element getElement();
	
	public class Element {
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
