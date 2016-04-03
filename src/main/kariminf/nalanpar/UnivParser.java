package kariminf.nalanpar;

import kariminf.nalanpar.Types.POSType;
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
			elementProcess();
		}
	}
	
	private void elementProcess(){
		
		
		
	}
	
	protected abstract boolean prepare(String text);
	protected abstract boolean next();
	protected abstract Element getElement();
	
	public class Element {
		private String val = "";
		private Posable pos;
		
		public Element (String val, Posable pos){
			this.val = val;
			this.pos = pos;
		}
		
		public String getVal(){
			return val;
		}
		
		public Posable getPos(){
			return pos;
		}
	}

}
