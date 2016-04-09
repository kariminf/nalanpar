package kariminf.nalanpar;

import kariminf.nalanpar.Types.Phrasal;
import kariminf.nalanpar.Types.Posable;
import kariminf.nalanpar.Types.Terminal;

public interface POSTransformer {
	
	public Posable getType(String pos);
	
	public Terminal getTerminal(String pos);
	
	public Phrasal getPhrasal(String pos);

}
