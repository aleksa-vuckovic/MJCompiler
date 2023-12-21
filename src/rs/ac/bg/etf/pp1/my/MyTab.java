package rs.ac.bg.etf.pp1.my;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MyTab extends Tab {
	public static Struct boolType = new Struct(Struct.Bool);
	
	public static boolean insert(Obj obj) {
		return currentScope.addToLocals(obj);
	}
	public static boolean insertParentScope(Obj obj) {
		if (currentScope.getOuter() != null) return currentScope.getOuter().addToLocals(obj);
		else return false;
	}
	public static Scope parentScope() {
		return currentScope.getOuter();
	}
	public static void init() {
		Tab.init();
		currentScope.addToLocals(new Obj(Obj.Type, "bool", boolType, 0, 0));
	}
	public static void openScopeAndChain(Obj obj) {
		openScope();
		try {
			currentScope.addToLocals(null);
		} catch(NullPointerException e) {}
		chainLocalSymbols(obj);
	}
	public static void openScopeAndChain(Struct struct) {
		openScope();
		try {
			currentScope.addToLocals(null);
		} catch(NullPointerException e) {}
		chainLocalSymbols(struct);
	}
	
	
	public static void dump(SymbolTableVisitor stv) {
		System.out.println("=====================SYMBOL TABLE DUMP=========================");
		if (stv == null)
			stv = new MySymbolTableVisitor();
		for (Scope s = currentScope; s != null; s = s.getOuter()) {
			s.accept(stv);
		}
		System.out.println(stv.getOutput());
	}
	public static void dump() {
		MyTab.dump(null);
	}
}
