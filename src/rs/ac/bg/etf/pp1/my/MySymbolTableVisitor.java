package rs.ac.bg.etf.pp1.my;

import java.util.Collection;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.visitors.SymbolTableVisitor;

public class MySymbolTableVisitor extends SymbolTableVisitor {

	protected StringBuilder output = new StringBuilder();
	protected final String indent = "   ";
	protected StringBuilder currentIndent = new StringBuilder();
	
	protected void nextIndentationLevel() {
		currentIndent.append(indent);
	}
	
	protected void previousIndentationLevel() {
		if (currentIndent.length() > 0)
			currentIndent.setLength(currentIndent.length()-indent.length());
	}
	
	
	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitObjNode(symboltable.Obj)
	 */
	@Override
	public void visitObjNode(Obj objToVisit) {
		//output.append("[");
		int kind = objToVisit.getKind();
		Struct type = objToVisit.getType();
		
		output.append(Utils.objToString(objToVisit));
		
		Collection<Obj> nested = null;
		if (kind == Obj.Prog || kind == Obj.Type || kind == Utils.Namespace) {
			nested = type.getMembers();
		}
		else if (kind == Obj.Meth) {
			nested = objToVisit.getLocalSymbols();
		}
		
		if (nested != null) {
			output.append("\n");
			nextIndentationLevel();
			for (Obj o : nested) {
				output.append(currentIndent.toString());
				o.accept(this);
				output.append("\n");
			}
			previousIndentationLevel();
		}

		//output.append("]");
		
	}

	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitScopeNode(symboltable.Scope)
	 */
	@Override
	public void visitScopeNode(Scope scope) {
		for (Obj o : scope.values()) {
			o.accept(this);
			output.append("\n");
		}
	}

	/* (non-Javadoc)
	 * @see rs.etf.pp1.symboltable.test.SymbolTableVisitor#visitStructNode(symboltable.Struct)
	 */
	@Override
	public void visitStructNode(Struct structToVisit) {
		output.append(Utils.structToString(structToVisit));
	}

	public String getOutput() {
		return output.toString();
	}
	
	
}
