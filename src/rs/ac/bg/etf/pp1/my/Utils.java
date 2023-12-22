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
		return cls.getNumberOfFields() + 1;
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
}
