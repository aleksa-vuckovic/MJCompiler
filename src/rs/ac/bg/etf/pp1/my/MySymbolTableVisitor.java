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
		switch (kind) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Prog: output.append("Prog "); break;
		case Utils.Namespace: output.append("Namespace "); break;
		}
		
		output.append(objToVisit.getName());
		output.append(": ");
		printStructShort(type);
		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel());
		output.append(", ");
		output.append(objToVisit.getFpPos() + " ");
		
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
		printStructShort(structToVisit);

	}
	public void printStructShort(Struct struct) {
		switch (struct.getKind()) {
		case Struct.None:
			output.append("notype");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Bool:
			output.append("bool");
			break;
		case Struct.Array:
			output.append("Arr of ");
			
			switch (struct.getElemType().getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Class:
				output.append("Class");
				break;
			}
			break;
		case Struct.Class:
			output.append("Class");
			break;
		}
	}

	public String getOutput() {
		return output.toString();
	}
	
	
}