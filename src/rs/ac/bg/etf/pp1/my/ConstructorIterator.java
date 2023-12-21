package rs.ac.bg.etf.pp1.my;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class ConstructorIterator {
	
	Struct cls;
	int i = 0;
	public ConstructorIterator(Struct cls) {
		this.cls = cls;
	}
	public void reset() {
		i = 0;
	}
	public Obj next() {
		String name = Utils.CONSPREFIX + i;
		Obj res = cls.getMembersTable().searchKey(name);
		if (res == null) return null; 
		i++;
		return res;
	}

}
