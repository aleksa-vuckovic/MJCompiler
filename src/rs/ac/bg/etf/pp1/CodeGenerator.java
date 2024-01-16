package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.my.ConstructorIterator;
import rs.ac.bg.etf.pp1.my.DesignatorList;
import rs.ac.bg.etf.pp1.my.MethodIterator;
import rs.ac.bg.etf.pp1.my.MyInt;
import rs.ac.bg.etf.pp1.my.MyTab;
import rs.ac.bg.etf.pp1.my.TypeList;
import rs.ac.bg.etf.pp1.my.Utils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class CodeGenerator extends VisitorAdaptor {
	
	
	//Logger log = Logger.getLogger(getClass());
	
	Obj currentClass = null;
	Obj currentMethod = null;
	
	int mainPc = -1;
	int initializerPc = -1;
	
	public int getStartPc() {
		return initializerPc;
	}
	
	List<Integer> initializerAddresses = new LinkedList<>();
	Stack<List<Integer>> breakFixups = new Stack<>();
	Stack<List<Integer>> continueFixups = new Stack<>();

	@Override
	public void visit(DesignatorSimple DesignatorSimple) {
		super.visit(DesignatorSimple);
		Obj leftObj = DesignatorSimple.obj;
		if (leftObj.getLevel() == 1 && leftObj.getKind() != Obj.Var) {
			//pristup preko podrazumevanog this
			Code.put(Code.load_n);
		}
	}
	@Override
	public void visit(DesignatorScoped DesignatorScoped) {
		super.visit(DesignatorScoped);
	}
	@Override
	public void visit(DesignatorElem DesignatorElem) {
		super.visit(DesignatorElem);
		Obj designator = DesignatorElem.getDesignator().obj;
		int pstackTop = currentMethod == null ? 0 : Utils.localVariableCount(currentMethod);
		//indeks treba sacuvati sa strane da bi se vrednost prethodnog
		//designator objekta ucitala na estek
		Code.put(Code.store); Code.put(pstackTop);
		Code.load(designator);
		Code.put(Code.load); Code.put(pstackTop);
	}
	@Override
	public void visit(DesignatorField DesignatorField) {
		super.visit(DesignatorField);
		Obj designator = DesignatorField.getDesignator().obj;
		int kind = designator.getKind();
		
		if (kind == Obj.Con || kind == Obj.Meth) {
			//semanticki neispravno
		}
		else if (kind == Obj.Type || kind == Obj.Prog || kind == Utils.Namespace) {
			//ispravno ali ne generise se nista
		}
		else {
			Code.load(designator);
		}
	}

	/*
	 * Operators
	 */
	@Override
	public void visit(MulopMod MulopMod) {
		super.visit(MulopMod);
	}
	@Override
	public void visit(MulopDiv MulopDiv) {
		super.visit(MulopDiv);
	}
	@Override
	public void visit(MulopMul MulopMul) {
		super.visit(MulopMul);
	}
	@Override
	public void visit(AddopSub AddopSub) {
		super.visit(AddopSub);
	}
	@Override
	public void visit(AddopAdd AddopAdd) {
		super.visit(AddopAdd);
	}
	@Override
	public void visit(RelopLe RelopLe) {
		super.visit(RelopLe);
	}
	@Override
	public void visit(RelopLt RelopLt) {
		super.visit(RelopLt);
	}
	@Override
	public void visit(RelopGe RelopGe) {
		super.visit(RelopGe);
	}
	@Override
	public void visit(RelopGt RelopGt) {
		super.visit(RelopGt);
	}
	@Override
	public void visit(RelopNe RelopNe) {
		super.visit(RelopNe);
	}
	@Override
	public void visit(RelopEq RelopEq) {
		super.visit(RelopEq);
	}
	@Override
	public void visit(Assignop Assignop) {
		super.visit(Assignop);
	}

	/*
	 * Type
	 */
	@Override
	public void visit(TypeScoped TypeScoped) {
		super.visit(TypeScoped);
	}
	@Override
	public void visit(TypeSimple TypeSimple) {
		super.visit(TypeSimple);
	}
	
	/*
	 * Expr
	 */
	@Override
	public void visit(ConstructorInvocation ConstructorInvocation) {
		super.visit(ConstructorInvocation);
		Struct cls = ConstructorInvocation.getType().struct;
		int size = Utils.getSize(cls);
		
		Code.put(Code.new_);
		Code.put2(size);
		Code.put(Code.dup);
	}
	@Override
	public void visit(FactorCons FactorCons) {
		super.visit(FactorCons);
		Struct cls = FactorCons.getConstructorInvocation().struct;
		TypeList args = FactorCons.getActPars().typelist;
		
		Obj cons = Utils.findConstructor(cls, args);
		Utils.generateCall(cons.getAdr());
	}
	@Override
	public void visit(FactorExpr FactorExpr) {
		super.visit(FactorExpr);
	}
	@Override
	public void visit(FactorArray FactorArray) {
		super.visit(FactorArray);
		Struct type = FactorArray.getType().struct;
		Code.put(Code.newarray);
		if (type == MyTab.charType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
	@Override
	public void visit(FactorMethod FactorMethod) {
		super.visit(FactorMethod);
	}
	@Override
	public void visit(FactorDesignator FactorDesignator) {
		super.visit(FactorDesignator);
		Obj designator = FactorDesignator.getDesignator().obj;
		Code.load(designator);
	}
	@Override
	public void visit(FactorBool FactorBool) {
		super.visit(FactorBool);
		int val = FactorBool.getVal();
		Code.loadConst(val);
	}
	@Override
	public void visit(FactorChar FactorChar) {
		super.visit(FactorChar);
		int val = FactorChar.getVal();
		Code.loadConst(val);
	}
	@Override
	public void visit(FactorNum FactorNum) {
		super.visit(FactorNum);
		int val = FactorNum.getVal();
		Code.loadConst(val);
	}
	@Override
	public void visit(TermSingle TermSingle) {
		super.visit(TermSingle);
	}
	@Override
	public void visit(TermMul TermMul) {
		super.visit(TermMul);
		int op = TermMul.getMulop().myint.val;
		Code.put(op);
	}
	@Override
	public void visit(ExprSingleNeg ExprSingleNeg) {
		super.visit(ExprSingleNeg);
		Code.put(Code.neg);
	}
	@Override
	public void visit(ExprSingle ExprSingle) {
		super.visit(ExprSingle);
	}
	@Override
	public void visit(ExprAdd ExprAdd) {
		super.visit(ExprAdd);
		int op = ExprAdd.getAddop().myint.val;
		Code.put(op);
	}

	/*
	 * Condition
	 */
	@Override
	public void visit(ConditonFactorExpr ConditonFactorExpr) {
		super.visit(ConditonFactorExpr);
		Code.put(Code.const_n);
		Code.put(Code.jcc + Code.ne); Code.put2(7);
		Code.put(Code.const_n);
		Code.put(Code.jmp); Code.put2(4);
		Code.put(Code.const_1);
	}
	@Override
	public void visit(ConditionFactorRel ConditionFactorRel) {
		super.visit(ConditionFactorRel);
		int op = ConditionFactorRel.getRelop().myint.val;
		Code.put(Code.jcc + op); Code.put2(7);
		Code.put(Code.const_n);
		Code.put(Code.jmp); Code.put2(4);
		Code.put(Code.const_1);
	}
	@Override
	public void visit(ConditionTermSingle ConditionTermSingle) {
		super.visit(ConditionTermSingle);
		if (ConditionTermSingle.getParent() instanceof ConditionTermAnd) {
			Code.put(Code.dup);
			Code.put(Code.const_n);
			Code.put(Code.jcc + Code.eq);
			ConditionTermSingle.myint = new MyInt(Code.pc);
			Code.put2(0);
		}
	}
	@Override
	public void visit(ConditionTermAnd ConditionTermAnd) {
		super.visit(ConditionTermAnd);
		Code.put(Code.mul);
		
		int fixupAdr = ConditionTermAnd.getConditionTerm().myint.val;
		Utils.codeFixup(fixupAdr, Code.pc);
		
		if (ConditionTermAnd.getParent() instanceof ConditionTermAnd) {
			Code.put(Code.dup);
			Code.put(Code.const_n);
			Code.put(Code.jcc + Code.eq);
			ConditionTermAnd.myint = new MyInt(Code.pc);
			Code.put2(0);
		}
	}
	@Override
	public void visit(ConditionSingle ConditionSingle) {
		super.visit(ConditionSingle);
		if (ConditionSingle.getParent() instanceof ConditionOr) {
			Code.put(Code.dup);
			Code.put(Code.const_1);
			Code.put(Code.jcc + Code.eq);
			ConditionSingle.myint = new MyInt(Code.pc);
			Code.put2(0);
		}
	}
	@Override
	public void visit(ConditionOr ConditionOr) {
		super.visit(ConditionOr);
		Code.put(Code.add);
		
		int fixupAdr = ConditionOr.getCondition().myint.val;
		Utils.codeFixup(fixupAdr, Code.pc);
		
		if (ConditionOr.getParent() instanceof ConditionOr) {
			Code.put(Code.dup);
			Code.put(Code.const_1);
			Code.put(Code.jcc + Code.eq);
			ConditionOr.myint = new MyInt(Code.pc);
			Code.put2(0);
		}
	}

	/*
	 * Designator statement
	 */
	@Override
	public void visit(DesignatorStatementMultipleAssign DesignatorStatementMultipleAssign) {
		super.visit(DesignatorStatementMultipleAssign);
		Obj leftArrObj = DesignatorStatementMultipleAssign.getDesignator().obj;
		Obj rightArrObj = DesignatorStatementMultipleAssign.getDesignator1().obj;
		List<Obj> designatorList = DesignatorStatementMultipleAssign.getDesignatorComma().designatorlist.list;
		
		int varCount = designatorList.size();
		int pstackTop = currentMethod == null ? 0 : Utils.localVariableCount(currentMethod);
		int rightArr = pstackTop;
		int rightIndex = pstackTop + 1;
		int leftIndex = pstackTop + 2;
		
		int arrload = Code.aload;
		int arrstore = Code.astore;
		if (rightArrObj.getType().getElemType() == MyTab.charType) {
			arrload = Code.baload;
			arrstore = Code.bastore;
		}
		
		Code.load(rightArrObj);
		Code.put(Code.store); Code.put(rightArr);
		Code.load(leftArrObj);
		Code.put(Code.dup);
		Code.put(Code.arraylength);
		Code.put(Code.load); Code.put(rightArr);
		Code.put(Code.arraylength);
		Code.put(Code.dup);
		//estek: leftarr, len(leftarr), len(rightarr) x 2
		Code.put(Code.const_); Code.put4(varCount);
		Code.put(Code.jcc + Code.gt); Code.put2(5);
		Code.put(Code.trap); Code.put(2);
		
		Code.put(Code.const_); Code.put4(varCount);
		Code.put(Code.sub);
		//estek: leftarr, len(leftarr), visakcnt
		Code.put(Code.dup2);
		//nalazimo min(len(leftarr), visakcnt) i smestamo u leftIndex
		Code.put(Code.jcc + Code.lt); Code.put2(9);
		Code.put(Code.store); Code.put(leftIndex);
		Code.put(Code.pop);
		Code.put(Code.jmp); Code.put2(6);
		Code.put(Code.pop);
		Code.put(Code.store); Code.put(leftIndex);
		
		//rightIndex = leftIndex + varCount
		Code.put(Code.load); Code.put(leftIndex);
		Code.put(Code.const_); Code.put4(varCount);
		Code.put(Code.add);
		Code.put(Code.store); Code.put(rightIndex);
		
		//petlja while(leftIndex != 0)
		Code.put(Code.load); Code.put(leftIndex);
		Code.put(Code.const_n);
		Code.put(Code.jcc + Code.eq); Code.put2(21);//leftIndex == 0
		//leftArr[--leftIndex] = rightArr[--rightIndex]
		Code.put(Code.inc); Code.put(rightIndex); Code.put(-1);
		Code.put(Code.inc); Code.put(leftIndex); Code.put(-1);
		Code.put(Code.dup); //estek: leftarr, leftarr
		Code.put(Code.load); Code.put(leftIndex);
		Code.put(Code.load); Code.put(rightArr);
		Code.put(Code.load); Code.put(rightIndex);
		//estek: leftarr, leftarr, leftIndex, rightArr, rightIndex
		Code.put(arrload);
		Code.put(arrstore);
		Code.put(Code.jmp); Code.put2(-21);
		
		Code.put(Code.pop);
		//Sada su na esteku samo identifikatori iz designatorList
		for (int i = designatorList.size() - 1; i >= 0; i--) {
			Code.put(Code.inc); Code.put(rightIndex); Code.put(-1);
			Obj designator = designatorList.get(i);
			if (designator != null) {
				Code.put(Code.load); Code.put(rightArr);
				Code.put(Code.load); Code.put(rightIndex);
				Code.put(arrload);
				Code.store(designator);
			}
		}
		//KRAAAAAAAAAAAAAAAAAAAAAJ
	}
	@Override
	public void visit(DesignatorStatementDec DesignatorStatementDec) {
		super.visit(DesignatorStatementDec);
		Obj designator = DesignatorStatementDec.getDesignator().obj;
		generateIncDec(designator, Code.sub);
	}
	@Override
	public void visit(DesignatorStatementInc DesignatorStatementInc) {
		super.visit(DesignatorStatementInc);
		Obj designator = DesignatorStatementInc.getDesignator().obj;
		generateIncDec(designator, Code.add);
	}
	void generateIncDec(Obj designator, int op) {
		int kind = designator.getKind();
		if (kind == Obj.Var) {
		}
		else if (kind == Obj.Fld) {
			Code.put(Code.dup);
		}
		else if (kind == Obj.Elem) {
			Code.put(Code.dup2);
		}
		Code.load(designator);
		Code.put(Code.const_1);
		Code.put(op);
		Code.store(designator);
	}
	@Override
	public void visit(DesignatorStatementMethod DesignatorStatementMethod) {
		super.visit(DesignatorStatementMethod);
		Struct retType = DesignatorStatementMethod.getMethodInvocation().struct;
		if (retType != MyTab.noType) {
			Code.put(Code.pop);
		}
	}
	@Override
	public void visit(DesignatorStatementAssignError DesignatorStatementAssignError) {
		super.visit(DesignatorStatementAssignError);
	}
	@Override
	public void visit(DesignatorStatementAssign DesignatorStatementAssign) {
		super.visit(DesignatorStatementAssign);
		Obj obj = DesignatorStatementAssign.getDesignator().obj;
		Code.store(obj);
	}
	@Override
	public void visit(DesignatorStatementCommaEmpty DesignatorStatementCommaEmpty) {
		super.visit(DesignatorStatementCommaEmpty);
	}
	@Override
	public void visit(DesignatorStatementCommaEnd DesignatorStatementCommaEnd) {
		super.visit(DesignatorStatementCommaEnd);
	}
	@Override
	public void visit(DesignatorStatementCommaItem DesignatorStatementCommaItem) {
		super.visit(DesignatorStatementCommaItem);
	}
	@Override
	public void visit(DesignatorCommaEnd DesignatorCommaEnd) {
		super.visit(DesignatorCommaEnd);
	}
	@Override
	public void visit(DesignatorCommaEmptyItem DesignatorCommaEmptyItem) {
		super.visit(DesignatorCommaEmptyItem);
	}
	@Override
	public void visit(DesignatorCommaItem DesignatorCommaItem) {
		super.visit(DesignatorCommaItem);
	}
	/*
	 * Statements
	 */
	@Override
	public void visit(ForExit ForExit) {
		super.visit(ForExit);
		Code.put(Code.jmp); Code.put2(0);
		ForExit.myint = new MyInt(Code.pc);
	}
	@Override
	public void visit(ForConditionEmpty ForConditionEmpty) {
		super.visit(ForConditionEmpty);
		
		Code.put(Code.const_1);
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		Code.put(Code.jmp); Code.put2(0);
		
		ForConditionEmpty.myint = new MyInt(Code.pc);
	}
	@Override
	public void visit(ForConditionNonempty ForConditionNonempty) {
		super.visit(ForConditionNonempty);
		
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		Code.put(Code.jmp); Code.put2(0);
		
		ForConditionNonempty.myint = new MyInt(Code.pc);
	}
	@Override
	public void visit(ForEntry ForEntry) {
		super.visit(ForEntry);
		ForEntry.myint = new MyInt(Code.pc);
		breakFixups.push(new ArrayList<>());
		continueFixups.push(new ArrayList<>());
	}
	@Override
	public void visit(StatementFor StatementFor) {
		super.visit(StatementFor);
		Code.put(Code.jmp); Code.put2(0);
		
		int forconditionAdr = StatementFor.getForEntry().myint.val;
		int forexitAdr = StatementFor.getForCondition().myint.val;
		int forstatementAdr = StatementFor.getForExit().myint.val;
		int forendAdr = Code.pc;
		
		int forendFixupAdr = forexitAdr - 5;
		Utils.codeFixup(forendFixupAdr, forendAdr);
		
		int forstatementFixupAdr = forexitAdr - 2;
		Utils.codeFixup(forstatementFixupAdr, forstatementAdr);
		
		int forconditionFixupAdr = forstatementAdr - 2;
		Utils.codeFixup(forconditionFixupAdr, forconditionAdr);
		
		int forexitFixupAdr= forendAdr - 2;
		Utils.codeFixup(forexitFixupAdr, forexitAdr);
		
		List<Integer> breaks = breakFixups.pop();
		for (Integer fixup: breaks) {
			Utils.codeFixup(fixup, forendAdr);
		}
		
		List<Integer> continues = continueFixups.pop();
		for (Integer fixup: continues) {
			Utils.codeFixup(fixup, forexitAdr);
		}
		
	}
	@Override
	public void visit(StatementBlock StatementBlock) {
		super.visit(StatementBlock);
	}
	@Override
	public void visit(StatementPrintNum StatementPrintNum) {
		super.visit(StatementPrintNum);
		int width = StatementPrintNum.getNum();
		Struct type = StatementPrintNum.getExpr().struct;
		Code.put(Code.const_); Code.put4(width);
		if (type == MyTab.charType) {
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.print);
		}
	}
	@Override
	public void visit(StatementPrint StatementPrint) {
		super.visit(StatementPrint);
		Struct type = StatementPrint.getExpr().struct;
		if (type == MyTab.charType) {
			Code.put(Code.const_1);
			Code.put(Code.bprint);
		}
		else {
			Code.put(Code.const_4);
			Code.put(Code.print);
		}
	}
	@Override
	public void visit(StatementRead StatementRead) {
		super.visit(StatementRead);
		Obj designator = StatementRead.getDesignator().obj;
		if (designator.getType() == MyTab.charType) {
			Code.put(Code.bread);
		}
		else {
			Code.put(Code.read);
		}
		Code.store(designator);
	}
	@Override
	public void visit(StatementReturnExpr StatementReturnExpr) {
		super.visit(StatementReturnExpr);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	@Override
	public void visit(StatementReturn StatementReturn) {
		super.visit(StatementReturn);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	@Override
	public void visit(StatementContinue StatementContinue) {
		super.visit(StatementContinue);
		Code.put(Code.jmp);
		continueFixups.peek().add(Code.pc);
		Code.put2(0);
	}
	@Override
	public void visit(StatementBreak StatementBreak) {
		super.visit(StatementBreak);
		Code.put(Code.jmp);
		breakFixups.peek().add(Code.pc);
		Code.put2(0);
	}
	@Override
	public void visit(StatementDesignatorStatement StatementDesignatorStatement) {
		super.visit(StatementDesignatorStatement);
	}
	@Override
	public void visit(IfStatement IfStatement) {
		super.visit(IfStatement);
		if (IfStatement.getParent() instanceof StatementMatchedIf) {
			Code.put(Code.jmp); Code.put2(0);
			IfStatement.myint = new MyInt(Code.pc);
		}
	}
	@Override
	public void visit(IfConditionError IfConditionError) {
		super.visit(IfConditionError);
	}
	@Override
	public void visit(IfConditionOk IfConditionOk) {
		super.visit(IfConditionOk);
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		IfConditionOk.myint = new MyInt(Code.pc);
	}
	@Override
	public void visit(StatementMatchedIf StatementMatchedIf) {
		super.visit(StatementMatchedIf);
		int ifstatementAdr = StatementMatchedIf.getIfCondition().myint.val;
		int elseAdr = StatementMatchedIf.getIfStatement().myint.val;
		int ifendAdr = Code.pc;
		
		int elseFixupAdr = ifstatementAdr - 2;
		Utils.codeFixup(elseFixupAdr, elseAdr);
		
		int ifendFixupAdr = elseAdr - 2;
		Utils.codeFixup(ifendFixupAdr, ifendAdr);
	}
	@Override
	public void visit(StatementUnmatchedIf StatementUnmatchedIf) {
		super.visit(StatementUnmatchedIf);
		int ifstatementAdr = StatementUnmatchedIf.getIfCondition().myint.val;
		int ifendFixupAdr = ifstatementAdr - 2;
		int ifendAdr = Code.pc;
		
		Utils.codeFixup(ifendFixupAdr, ifendAdr);
	}
	@Override
	public void visit(StatementListEnd StatementListEnd) {
		super.visit(StatementListEnd);
	}
	@Override
	public void visit(StatementListItem StatementListItem) {
		super.visit(StatementListItem);
	}

	/*
	 * Actual params, Method invocation
	 */
	@Override
	public void visit(ActPar ActPar) {
		super.visit(ActPar);
	}
	@Override
	public void visit(ActParsEmpty ActParsEmpty) {
		super.visit(ActParsEmpty);
	}
	@Override
	public void visit(ActParsEnd ActParsEnd) {
		super.visit(ActParsEnd);
	}
	@Override
	public void visit(ActParsItem ActParsItem) {
		super.visit(ActParsItem);
	}
	@Override
	public void visit(MethodInvocation MethodInvocation) {
		super.visit(MethodInvocation);
		Obj meth = MethodInvocation.getDesignator().obj;
		if (meth == MyTab.chrObj || meth == MyTab.ordObj) {
			//nije potreban kod
		}
		else if (meth == MyTab.lenObj) {
			Code.put(Code.arraylength);
		}
		else if (meth.getLevel() == 0) {
			//static method
			Utils.generateCall(meth.getAdr());
		}
		else {
			//virtual method
			
			//this je sada "zakopano" ispod argumenata na esteku,
			//i jedini nacin da se dodje do njega je ponovno
			//generisanje koda za Designator)
			MethodInvocation.getDesignator().traverseBottomUp(this);
			//sada je this na vrhu esteka
			Code.put(Code.getfield); Code.put2(0);
			Code.put(Code.invokevirtual);
			String name = meth.getName();
			for (int i = 0; i < name.length(); i++) {
				Code.put4(name.charAt(i));
			}
			Code.put4(-1);
		}
	}
	
	/*
	 * Var declaration
	 */
	@Override
	public void visit(VarDeclArray VarDeclArray) {
		super.visit(VarDeclArray);
	}
	@Override
	public void visit(VarDeclScalar VarDeclScalar) {
		super.visit(VarDeclScalar);
	}
	@Override
	public void visit(VarDeclCommaEndError VarDeclCommaEndError) {
		super.visit(VarDeclCommaEndError);
	}
	@Override
	public void visit(VarDeclCommaItemError VarDeclCommaItemError) {
		super.visit(VarDeclCommaItemError);
	}
	@Override
	public void visit(VarDeclCommaEnd VarDeclCommaEnd) {
		super.visit(VarDeclCommaEnd);
	}
	@Override
	public void visit(VarDeclCommaItem VarDeclCommaItem) {
		super.visit(VarDeclCommaItem);
	}
	@Override
	public void visit(VarDeclLineStart VarDeclLineStart) {
		super.visit(VarDeclLineStart);
	}
	@Override
	public void visit(VarDeclLine VarDeclLine) {
		super.visit(VarDeclLine);
	}
	@Override
	public void visit(VarDeclLineListEnd VarDeclLineListEnd) {
		super.visit(VarDeclLineListEnd);
	}
	@Override
	public void visit(VarDeclLineListItem VarDeclLineListItem) {
		super.visit(VarDeclLineListItem);
	}
	
	/*
	 * Formal param declaration
	 */
	@Override
	public void visit(FormalParamArray FormalParamArray) {
		super.visit(FormalParamArray);
	}
	@Override
	public void visit(FormalParamScalar FormalParamScalar) {
		super.visit(FormalParamScalar);
	}
	@Override
	public void visit(FormalParamCommaItemError FormalParamCommaItemError) {
		super.visit(FormalParamCommaItemError);
	}
	public void visit(FormalParamCommaEndError FormalParamCommaEndError) {
		super.visit(FormalParamCommaEndError);
	}
	@Override
	public void visit(FormalParamCommaEmpty FormalParamCommaEmpty) {
		super.visit(FormalParamCommaEmpty);
	}
	@Override
	public void visit(FormalParamCommaEnd FormalParamCommaEnd) {
		super.visit(FormalParamCommaEnd);
	}
	@Override
	public void visit(FormalParamCommaItem FormalParamCommaItem) {
		super.visit(FormalParamCommaItem);
	}

	/*
	 * Method declaration
	 */
	@Override
	public void visit(MethodNameCons MethodNameCons) {
		super.visit(MethodNameCons);
		Obj obj = MethodNameCons.obj;
		generateMethodEntry(obj);
		if (currentClass != null) {
			//all constructors should set the vtable pointer as field 0
			Code.put(Code.load_n);
			Code.put(Code.const_); Code.put4(currentClass.getAdr());
			Code.put(Code.putfield); Code.put2(0);
		}
	}
	@Override
	public void visit(MethodNameVoid MethodNameVoid) {
		super.visit(MethodNameVoid);
		Obj obj = MethodNameVoid.obj;
		generateMethodEntry(obj);
	}
	@Override
	public void visit(MethodNameRet MethodNameRet) {
		super.visit(MethodNameRet);
		Obj obj = MethodNameRet.obj;
		generateMethodEntry(obj);
	}
	private void generateMethodEntry(Obj obj) {
		obj.setAdr(Code.pc);
		int argCount = obj.getFpPos();
		int localCount = Utils.localVariableCount(obj);
		Code.put(Code.enter); Code.put(argCount); Code.put(localCount);
		currentMethod = obj;
	}
	@Override
	public void visit(MethodDeclaration MethodDeclaration) {
		super.visit(MethodDeclaration);
		Obj obj = MethodDeclaration.obj;
		//Ako je main pamti se
		if (obj.getLevel() == 0 && obj.getName().equals("main") && obj.getFpPos() == 0 && obj.getType() == MyTab.noType) {
			mainPc = obj.getAdr();
		}
	}
	@Override
	public void visit(Method Method) {
		super.visit(Method);
		Obj obj = Method.obj;
		if (obj.getType() == MyTab.noType) {
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		else {
			Code.put(Code.trap); Code.put(1);
		}
		currentMethod = null;
	}
	@Override
	public void visit(MethodListEnd MethodListEnd) {
		super.visit(MethodListEnd);
	}
	@Override
	public void visit(MethodListItem MethodListItem) {
		super.visit(MethodListItem);
	}

	/*
	 * Static members
	 */
	@Override
	public void visit(StaticInitializerStart StaticInitializerStart) {
		super.visit(StaticInitializerStart);
		initializerAddresses.add(Code.pc);
		Code.put(Code.enter); Code.put(0); Code.put(0);
	}
	@Override
	public void visit(StaticInitializer StaticInitializer) {
		super.visit(StaticInitializer);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	@Override
	public void visit(StaticVarDeclArray StaticVarDeclArray) {
		super.visit(StaticVarDeclArray);
	}
	@Override
	public void visit(StaticVarDeclScalar StaticVarDeclScalar) {
		super.visit(StaticVarDeclScalar);
	}
	@Override
	public void visit(StaticVarDeclCommaEnd StaticVarDeclCommaEnd) {
		super.visit(StaticVarDeclCommaEnd);
	}
	@Override
	public void visit(StaticVarDeclCommaItem StaticVarDeclCommaItem) {
		super.visit(StaticVarDeclCommaItem);
	}
	@Override
	public void visit(StaticVarDeclLineStart StaticVarDeclLineStart) {
		super.visit(StaticVarDeclLineStart);
	}
	@Override
	public void visit(StaticVarDeclLine StaticVarDeclLine) {
		super.visit(StaticVarDeclLine);
	}
	
	/*
	 * Class decl fillers
	 */
	@Override
	public void visit(MethodsEmpty MethodsEmpty) {
		super.visit(MethodsEmpty);
	}
	@Override
	public void visit(MethodsNonempty MethodsNonempty) {
		super.visit(MethodsNonempty);
	}
	@Override
	public void visit(StaticMemberVar StaticMemberVar) {
		super.visit(StaticMemberVar);
	}
	@Override
	public void visit(StaticMemberInitializer StaticMemberInitializer) {
		super.visit(StaticMemberInitializer);
	}
	@Override
	public void visit(StaticMemberListEnd StaticMemberListEnd) {
		super.visit(StaticMemberListEnd);
	}
	@Override
	public void visit(StaticMemberListItem StaticMemberListItem) {
		super.visit(StaticMemberListItem);
	}

	/*
	 * Class
	 */
	@Override
	public void visit(ClassNameExtend ClassNameExtend) {
		super.visit(ClassNameExtend);
		currentClass = ClassNameExtend.obj;
		//Kopiranje adresa virtuelnih metoda osnovne klase
		//(objektni cvorovi nisu isti)
		
		Struct baseCls = ClassNameExtend.getType().struct;
		Collection<Obj> baseMembers = baseCls.getMembers();
		SymbolDataStructure derivedMembers = currentClass.getType().getMembersTable();
		
		for (Obj member: baseMembers) {
			if (member.getKind() != Obj.Meth || member.getLevel() != 1) continue;
			String name = member.getName();
			int adr = member.getAdr();
			
			derivedMembers.searchKey(name).setAdr(adr);
		}
		
	}
	@Override
	public void visit(ClassNameNoExtend ClassNameNoExtend) {
		super.visit(ClassNameNoExtend);
		currentClass = ClassNameNoExtend.obj;
	}
	@Override
	public void visit(ClassDecl ClassDecl) {
		super.visit(ClassDecl);
		
		Obj obj = ClassDecl.obj;
		int vtadr = obj.getAdr();
		ConstructorIterator iter = new ConstructorIterator(obj.getType());
		Obj cons0 = iter.next();
		if (cons0.getAdr() == -1) {
			//podrazumevani konstruktor
			cons0.setAdr(Code.pc);
			Code.put(Code.enter); Code.put(1); Code.put(1);
			Code.put(Code.load_n);
			Code.put(Code.const_); Code.put4(vtadr);
			Code.put(Code.putfield); Code.put2(0);
			Code.put(Code.exit);
			Code.put(Code.return_);
		}
		
		//inicijalizacioni kod za vtable
		initializerAddresses.add(0, Code.pc);
		int currentStaticAdr = vtadr;
		MethodIterator methods = new MethodIterator(obj);
		Obj cur = methods.next();
		while (cur != null) {
			String name = cur.getName();
			for (int i = 0; i < name.length(); i++) {
				Code.put(Code.const_); Code.put4(name.charAt(i));
				Code.put(Code.putstatic); Code.put2(currentStaticAdr++);
			}
			Code.put(Code.const_m1);
			Code.put(Code.putstatic); Code.put2(currentStaticAdr++);
			Code.put(Code.const_); Code.put4(cur.getAdr());
			Code.put(Code.putstatic); Code.put2(currentStaticAdr++);
			cur = methods.next();
		}
		Code.put(Code.const_); Code.put4(-2);
		Code.put(Code.putstatic); Code.put2(currentStaticAdr++);
		Code.put(Code.return_);
		
		currentClass = null;
	}
	
	@Override
	public void visit(ConstDeclBool ConstDeclBool) {
		super.visit(ConstDeclBool);
	}
	@Override
	public void visit(ConstDeclChar ConstDeclChar) {
		super.visit(ConstDeclChar);
	}
	@Override
	public void visit(ConstDeclNum ConstDeclNum) {
		super.visit(ConstDeclNum);
	}
	@Override
	public void visit(ConstDeclCommaEnd ConstDeclCommaEnd) {
		super.visit(ConstDeclCommaEnd);
	}
	@Override
	public void visit(ConstDeclCommaItem ConstDeclCommaItem) {
		super.visit(ConstDeclCommaItem);
	}
	@Override
	public void visit(ConstDeclLineStart ConstDeclLineStart) {
		super.visit(ConstDeclLineStart);
	}
	@Override
	public void visit(ConstDeclLine ConstDeclLine) {
		super.visit(ConstDeclLine);
	}
	@Override
	public void visit(DeclClass DeclClass) {
		super.visit(DeclClass);
	}
	@Override
	public void visit(DeclVar DeclVar) {
		super.visit(DeclVar);
	}
	@Override
	public void visit(DeclConst DeclConst) {
		super.visit(DeclConst);
	}
	@Override
	public void visit(DeclListEnd DeclListEnd) {
		super.visit(DeclListEnd);
	}
	@Override
	public void visit(DeclListItem DeclListItem) {
		// TODO Auto-generated method stub
		super.visit(DeclListItem);
	}
	
	/*
	 * Namespace
	 */
	@Override
	public void visit(NamespaceName NamespaceName) {
		super.visit(NamespaceName);
	}
	@Override
	public void visit(Namespace Namespace) {
		super.visit(Namespace);
	}
	@Override
	public void visit(NamespaceListEnd NamespaceListEnd) {
		super.visit(NamespaceListEnd);
	}
	@Override
	public void visit(NamespaceListItem NamespaceListItem) {
		super.visit(NamespaceListItem);
	}

	/*
	 * Program
	 */
	@Override
	public void visit(ProgramName ProgramName) {
		super.visit(ProgramName);
	}
	@Override
	public void visit(Program Program) {
		super.visit(Program);
		

		initializerPc = Code.pc;
		Code.put(Code.enter); Code.put(0); Code.put(0);
		for (Integer initBlock: initializerAddresses) {
			Utils.generateCall(initBlock);
		}
		Utils.generateCall(mainPc);
		Code.put(Code.exit);
		Code.put(Code.return_);
	}

	@Override
	public void visit() {
		super.visit();
	}
	
	
	
}
