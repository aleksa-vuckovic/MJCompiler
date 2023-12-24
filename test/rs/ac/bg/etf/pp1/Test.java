package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.my.MyTab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class Test {

	public static String getInputFile(String[] args) {
		return "test/code/reader.mj";
	}
	public static String getOutputFile(String[] args) {
		return "test/output.obj";
	}
	
	public static void main(String[] args) {
		MyTab.init();
		Struct struct = new Struct(Struct.Bool);
		MyTab.openScopeAndChain(struct);
		
		SymbolDataStructure s1 = MyTab.currentScope.getLocals();
		SymbolDataStructure s2 = struct.getMembersTable();
		if (s1 != null && s1.symbols().size() == 0 && s1 == s2) System.out.println("SUCCESS");
		else System.out.println("FAIL");
	}
}
