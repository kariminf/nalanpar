package kariminf.nalanpar.stanford;

import kariminf.nalanpar.ParseHandler;
import kariminf.nalanpar.Types.Phrase;
import kariminf.nalanpar.UnivParser;

public class StanfordParser extends UnivParser {

	public StanfordParser(ParseHandler handler) {
		super(handler);
	}

	@Override
	protected boolean prepare(String text) {
		return false;
	}

	@Override
	protected boolean found() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected boolean isPhrase() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected Phrase phraseType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected boolean isElement() {
		// TODO Auto-generated method stub
		return false;
	}

}
