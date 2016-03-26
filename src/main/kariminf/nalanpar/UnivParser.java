package kariminf.nalanpar;

import kariminf.nalanpar.Types.Phrase;

public abstract class UnivParser {
	
	protected ParseHandler handler;
	
	public UnivParser(ParseHandler handler){
		this.handler = handler;
	}
	
	public void parse(String text){
		if (! prepare(text))
			return;
		while (found()){
			if (isPhrase()){
				phraseProcess();
				continue;
			}
			
		}
	}
	
	private void phraseProcess(){
		
	}
	
	protected abstract boolean prepare(String text);
	protected abstract boolean found();
	protected abstract boolean isPhrase();
	protected abstract Phrase phraseType();
	protected abstract boolean isElement();

}
