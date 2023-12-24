package rs.ac.bg.etf.pp1.my;

import java.util.Collection;
import java.util.Iterator;

import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class Utils {
	public static final String CONSPREFIX = "__constructor";
	public static final int Namespace = 10;

	public static boolean assignableTo(Struct from, Struct to) {
		return from.assignableTo(to) || subclassOf(from, to);
	}
	public static boolean subclassOf(Struct sub, Struct base) {
		if (sub.getKind() != Struct.Class || base.getKind() != Struct.Class) return false;
		Struct cur = sub.getElemType();
		while (cur != null) {
			if (cur.equals(base)) return true;
			cur = cur.getElemType();
		}
		return false;
	}
	
	public static Obj clone(Obj obj) {
		Obj ret = new Obj(obj.getKind(), obj.getName(), obj.getType(), obj.getAdr(), obj.getLevel());
		ret.setFpPos(obj.getFpPos());
		SymbolDataStructure locals = new HashTableDataStructure();
		for (Obj o: obj.getLocalSymbols()) locals.insertKey(o);
		ret.setLocals(locals);
		return ret;
	}
	
	public static boolean overrideCompatible(Obj methBase, Obj methDerived) {
		if (!assignableTo(methDerived.getType(), methBase.getType())) return false;
		FormalParamIterator paramsBase = new FormalParamIterator(methBase);
		FormalParamIterator paramsDerived = new FormalParamIterator(methDerived);
		paramsBase.next(); paramsDerived.next(); //first argument (this) should be skipped
		while(true) {
			Obj paramBase = paramsBase.next();
			Obj paramDerived = paramsDerived.next();
			if (paramBase == null && paramDerived != null) return false;
			if (paramBase != null && paramDerived == null) return false;
			if (paramBase == null && paramDerived == null) return true;
			if (!assignableTo(paramBase.getType(), paramDerived.getType())) return false;
		}
	}
	
	public static boolean invokable(Obj meth, TypeList args) {
		FormalParamIterator params = new FormalParamIterator(meth);
		Iterator<Struct> argTypes = args.list.iterator();
		while(true) {
			Obj param = params.next();
			Struct type = argTypes.hasNext() ? argTypes.next() : null;
			
			if (param == null && type != null) return false;
			if (param != null && type == null) return false;
			if (param == null && type == null) return true;
			if (!assignableTo(type, param.getType())) return false;
		}
	}
	
	public static Struct firstParam(Obj method) {
		Obj first = new FormalParamIterator(method).next();
		return first != null ? first.getType() : null;
	}
	
	public static Obj findConstructor(Struct cls, TypeList args) {
		ConstructorIterator iter = new ConstructorIterator(cls);
		args.list.add(0, cls);
		Obj cons = iter.next();
		while(cons != null) {
			if (invokable(cons, args)) break;
			cons = iter.next();
		}
		args.list.remove(0);
		return cons;
	}
	
	public static boolean identicalArguments(Obj meth1, Obj meth2) {
		FormalParamIterator params1 = new FormalParamIterator(meth1);
		FormalParamIterator params2 = new FormalParamIterator(meth2);
		while(true) {
			Obj param1 = params1.next();
			Obj param2 = params2.next();
			if (param1 == null && param2 != null) return false;
			if (param1 != null && param2 == null) return false;
			if (param1 == null && param2 == null) return true;
			if (!param1.getType().equals(param2.getType())) return false;
		}
	}
	
	public static boolean isConstructor(Obj meth) {
		return meth.getName().startsWith(CONSPREFIX);
	}
	
	public static int getSize(Struct cls) {
		cls.setMembers(cls.getMembersTable());
		return (cls.getNumberOfFields() + 1)*4;
	}
	
	public static int localVariableCount(Obj meth) {
		return meth.getLocalSymbols().size();
	}
	
	public static void codeFixup(int patchAdr, int destination) {
		int curPc = Code.pc;
		Code.pc = destination;
		Code.fixup(patchAdr);
		Code.pc = curPc;
	}
	
	public static void generateCall(int adr) {
		int pc = Code.pc;
		Code.put(Code.call);
		Code.put2(adr - pc);
	}
	
	public static String objToString(Obj obj) {
		StringBuilder output = new StringBuilder();
		int kind = obj.getKind();
		Struct type = obj.getType();
		switch (kind) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Prog: output.append("Prog "); break;
		case Utils.Namespace: output.append("Namespace "); break;
		}
		
		output.append(obj.getName());
		output.append(": ");
		output.append(structToString(type));
		output.append(", ");
		output.append(obj.getAdr());
		output.append(", ");
		output.append(obj.getLevel());
		output.append(", ");
		output.append(obj.getFpPos());
		
		return output.toString();
	}
	public static String structToString(Struct struct) {
		StringBuilder output = new StringBuilder();
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
		return output.toString();
	}
}
