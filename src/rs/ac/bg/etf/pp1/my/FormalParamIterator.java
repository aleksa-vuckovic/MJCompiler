package rs.ac.bg.etf.pp1.my;

import rs.etf.pp1.symboltable.concepts.Obj;

public class FormalParamIterator {

	Obj meth;
	int i = 0;
	public FormalParamIterator(Obj meth) {
		this.meth = meth;
	}
	
	void reset() {
		i = 0;
	}
	
	Obj next() {
		for (Obj obj: meth.getLocalSymbols()) {
			if (obj.getFpPos() == 0 && obj.getAdr() == i) {
				i++;
				return obj;
			}
		}
		return null;
	}
}
