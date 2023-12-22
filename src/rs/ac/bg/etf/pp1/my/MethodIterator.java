package rs.ac.bg.etf.pp1.my;

import java.util.Collection;
import java.util.Iterator;

import rs.etf.pp1.symboltable.concepts.Obj;

public class MethodIterator {
	
	Collection<Obj> members;
	Iterator<Obj> iter;
	
	public MethodIterator(Obj cls) {
		members = cls.getType().getMembers();
		iter = members.iterator();
	}
	
	public void reset() {
		iter = members.iterator();
	}
	
	public Obj next() {
		Obj res = null;
		while (iter.hasNext()) {
			Obj tmp = iter.next();
			if (tmp.getKind() == Obj.Meth && tmp.getLevel() == 1) {
				res = tmp;
				break;
			}
		}
		return res;
	}

}
