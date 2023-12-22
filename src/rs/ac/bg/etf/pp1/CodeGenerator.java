package rs.ac.bg.etf.pp1;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.my.ConstructorIterator;
import rs.ac.bg.etf.pp1.my.MethodIterator;
import rs.ac.bg.etf.pp1.my.MyTab;
import rs.ac.bg.etf.pp1.my.Utils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {
	
	
	Logger log = Logger.getLogger(getClass());
	public boolean errorDetected = false;
	
	private void report_error(String message, SyntaxNode node) {
		log.error("Greska pri generisanju koda na liniji " + node.getLine() + ": " + message);
		errorDetected = true;
	}
	
	Obj currentClass = null;
	Obj currentMethod = null;
	
	int mainPc = -1;
	int initializerPc = -1;
	
	List<Integer> initializerAddresses = new LinkedList<>();
	Stack<List<Integer>> breakFixups = new Stack<>();
	Stack<List<Integer>> continueFixups = new Stack<>();

	@Override
	public void visit(DesignatorSimple DesignatorSimple) {
		// TODO Auto-generated method stub
		super.visit(DesignatorSimple);
	}

	@Override
	public void visit(DesignatorScoped DesignatorScoped) {
		// TODO Auto-generated method stub
		super.visit(DesignatorScoped);
	}

	@Override
	public void visit(DesignatorElem DesignatorElem) {
		// TODO Auto-generated method stub
		super.visit(DesignatorElem);
	}

	@Override
	public void visit(DesignatorField DesignatorField) {
		// TODO Auto-generated method stub
		super.visit(DesignatorField);
	}

	@Override
	public void visit(MulopMod MulopMod) {
		// TODO Auto-generated method stub
		super.visit(MulopMod);
	}

	@Override
	public void visit(MulopDiv MulopDiv) {
		// TODO Auto-generated method stub
		super.visit(MulopDiv);
	}

	@Override
	public void visit(MulopMul MulopMul) {
		// TODO Auto-generated method stub
		super.visit(MulopMul);
	}

	@Override
	public void visit(AddopSub AddopSub) {
		// TODO Auto-generated method stub
		super.visit(AddopSub);
	}

	@Override
	public void visit(AddopAdd AddopAdd) {
		// TODO Auto-generated method stub
		super.visit(AddopAdd);
	}

	@Override
	public void visit(RelopLe RelopLe) {
		// TODO Auto-generated method stub
		super.visit(RelopLe);
	}

	@Override
	public void visit(RelopLt RelopLt) {
		// TODO Auto-generated method stub
		super.visit(RelopLt);
	}

	@Override
	public void visit(RelopGe RelopGe) {
		// TODO Auto-generated method stub
		super.visit(RelopGe);
	}

	@Override
	public void visit(RelopGt RelopGt) {
		// TODO Auto-generated method stub
		super.visit(RelopGt);
	}

	@Override
	public void visit(RelopNe RelopNe) {
		// TODO Auto-generated method stub
		super.visit(RelopNe);
	}

	@Override
	public void visit(RelopEq RelopEq) {
		// TODO Auto-generated method stub
		super.visit(RelopEq);
	}

	@Override
	public void visit(Assignop Assignop) {
		// TODO Auto-generated method stub
		super.visit(Assignop);
	}

	@Override
	public void visit(TypeScoped TypeScoped) {
		// TODO Auto-generated method stub
		super.visit(TypeScoped);
	}

	@Override
	public void visit(TypeSimple TypeSimple) {
		// TODO Auto-generated method stub
		super.visit(TypeSimple);
	}

	@Override
	public void visit(ConstructorInvocation ConstructorInvocation) {
		// TODO Auto-generated method stub
		super.visit(ConstructorInvocation);
	}

	@Override
	public void visit(FactorCons FactorCons) {
		// TODO Auto-generated method stub
		super.visit(FactorCons);
	}

	@Override
	public void visit(FactorExpr FactorExpr) {
		// TODO Auto-generated method stub
		super.visit(FactorExpr);
	}

	@Override
	public void visit(FactorArray FactorArray) {
		// TODO Auto-generated method stub
		super.visit(FactorArray);
	}

	@Override
	public void visit(FactorMethod FactorMethod) {
		// TODO Auto-generated method stub
		super.visit(FactorMethod);
	}

	@Override
	public void visit(FactorDesignator FactorDesignator) {
		// TODO Auto-generated method stub
		super.visit(FactorDesignator);
	}

	@Override
	public void visit(FactorBool FactorBool) {
		// TODO Auto-generated method stub
		super.visit(FactorBool);
	}

	@Override
	public void visit(FactorChar FactorChar) {
		// TODO Auto-generated method stub
		super.visit(FactorChar);
	}

	@Override
	public void visit(FactorNum FactorNum) {
		// TODO Auto-generated method stub
		super.visit(FactorNum);
	}

	@Override
	public void visit(TermSingle TermSingle) {
		// TODO Auto-generated method stub
		super.visit(TermSingle);
	}

	@Override
	public void visit(TermMul TermMul) {
		// TODO Auto-generated method stub
		super.visit(TermMul);
	}

	@Override
	public void visit(ExprSingleNeg ExprSingleNeg) {
		// TODO Auto-generated method stub
		super.visit(ExprSingleNeg);
	}

	@Override
	public void visit(ExprSingle ExprSingle) {
		// TODO Auto-generated method stub
		super.visit(ExprSingle);
	}

	@Override
	public void visit(ExprAdd ExprAdd) {
		// TODO Auto-generated method stub
		super.visit(ExprAdd);
	}

	@Override
	public void visit(ConditonFactorExpr ConditonFactorExpr) {
		// TODO Auto-generated method stub
		super.visit(ConditonFactorExpr);
	}

	@Override
	public void visit(ConditionFactorRel ConditionFactorRel) {
		// TODO Auto-generated method stub
		super.visit(ConditionFactorRel);
	}

	@Override
	public void visit(ConditionTermSingle ConditionTermSingle) {
		// TODO Auto-generated method stub
		super.visit(ConditionTermSingle);
	}

	@Override
	public void visit(ConditionTermAnd ConditionTermAnd) {
		// TODO Auto-generated method stub
		super.visit(ConditionTermAnd);
	}

	@Override
	public void visit(ConditionSingle ConditionSingle) {
		// TODO Auto-generated method stub
		super.visit(ConditionSingle);
	}

	@Override
	public void visit(ConditionOr ConditionOr) {
		// TODO Auto-generated method stub
		super.visit(ConditionOr);
	}

	@Override
	public void visit(DesignatorStatementDec DesignatorStatementDec) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementDec);
	}

	@Override
	public void visit(DesignatorStatementInc DesignatorStatementInc) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementInc);
	}

	@Override
	public void visit(DesignatorStatementMethod DesignatorStatementMethod) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementMethod);
	}

	@Override
	public void visit(DesignatorStatementAssign DesignatorStatementAssign) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementAssign);
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

	/*
	 * Statements
	 */
	@Override
	public void visit(ForExit ForExit) {
		super.visit(ForExit);
		Code.put(Code.jmp); Code.put2(0);
		ForExit.myint.val = Code.pc;
	}
	@Override
	public void visit(ForConditionEmpty ForConditionEmpty) {
		super.visit(ForConditionEmpty);
		
		Code.put(Code.const_1);
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		Code.put(Code.jmp); Code.put2(0);
		
		ForConditionEmpty.myint.val = Code.pc;
	}
	@Override
	public void visit(ForConditionNonempty ForConditionNonempty) {
		super.visit(ForConditionNonempty);
		
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		Code.put(Code.jmp); Code.put2(0);
		
		ForConditionNonempty.myint.val = Code.pc;
	}
	@Override
	public void visit(ForEntry ForEntry) {
		super.visit(ForEntry);
		ForEntry.myint.val = Code.pc;
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
			Code.put(Code.print);
		}
		else {
			Code.put(Code.const_4);
			Code.put(Code.bprint);
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
			IfStatement.myint.val = Code.pc;
		}
	}
	@Override
	public void visit(IfCondition IfCondition) {
		super.visit(IfCondition);
		Code.put(Code.const_1);
		Code.put(Code.jcc + Code.ne); Code.put2(0);
		IfCondition.myint.val = Code.pc;
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
			Code.put(Code.call); Code.put2(meth.getAdr());
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
	}
	@Override
	public void visit(MethodDeclaration MethodDeclaration) {
		super.visit(MethodDeclaration);
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
	}
	@Override
	public void visit(StaticInitializer StaticInitializer) {
		super.visit(StaticInitializer);
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
	}

	@Override
	public void visit() {
		super.visit();
	}
	
	
	
}
