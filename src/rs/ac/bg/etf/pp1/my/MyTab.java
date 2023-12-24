package rs.ac.bg.etf.pp1.my;

import org.apache.log4j.Logger;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.DumpSymbolTableVisitor;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MyTab extends Tab {
	public static Struct boolType = new Struct(Struct.Bool);
	
	private static Logger log = Logger.getLogger(MyTab.class);
	
	public static boolean insert(Obj obj) {
		int adr = obj.getAdr();
		boolean ret = currentScope.addToLocals(obj);
		obj.setAdr(adr);
		return ret;
		//Scope.addToLocals iz nekog razloga menja adresu
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
		
		Tab.lenObj.setLevel(0); Tab.lenObj.setFpPos(0); Tab.lenObj.setAdr(Obj.NO_VALUE);
		Tab.chrObj.setLevel(0); Tab.chrObj.setFpPos(0); Tab.chrObj.setAdr(Obj.NO_VALUE);
		Tab.ordObj.setLevel(0); Tab.ordObj.setFpPos(0); Tab.ordObj.setAdr(Obj.NO_VALUE);
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
	
	public static Obj find(String name) {
		
		String msg = "Trazi se " + name + " - ";
		Obj ret = Tab.find(name);
		if (ret == Tab.noObj) msg += "Pretraga neuspesna.";
		else msg += Utils.objToString(ret);
		log.info(msg);
		
		return ret;
	}
}
