package rs.ac.bg.etf.pp1;
import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.my.ConstructorIterator;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;

public class CodeGenerator extends VisitorAdaptor {

	@Override
	public void visit(Mulop Mulop) {
		// TODO Auto-generated method stub
		super.visit(Mulop);
	}

	@Override
	public void visit(Relop Relop) {
		// TODO Auto-generated method stub
		super.visit(Relop);
	}

	@Override
	public void visit(ConstDeclComma ConstDeclComma) {
		// TODO Auto-generated method stub
		super.visit(ConstDeclComma);
	}

	@Override
	public void visit(StaticVarDeclComma StaticVarDeclComma) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclComma);
	}

	@Override
	public void visit(VarDeclLineList VarDeclLineList) {
		// TODO Auto-generated method stub
		super.visit(VarDeclLineList);
	}

	@Override
	public void visit(VarDeclComma VarDeclComma) {
		// TODO Auto-generated method stub
		super.visit(VarDeclComma);
	}

	@Override
	public void visit(StatementList StatementList) {
		// TODO Auto-generated method stub
		super.visit(StatementList);
	}

	@Override
	public void visit(NamespaceList NamespaceList) {
		// TODO Auto-generated method stub
		super.visit(NamespaceList);
	}

	@Override
	public void visit(ClassName ClassName) {
		// TODO Auto-generated method stub
		super.visit(ClassName);
	}

	@Override
	public void visit(Addop Addop) {
		// TODO Auto-generated method stub
		super.visit(Addop);
	}

	@Override
	public void visit(ConditionTerm ConditionTerm) {
		// TODO Auto-generated method stub
		super.visit(ConditionTerm);
	}

	@Override
	public void visit(ForCondition ForCondition) {
		// TODO Auto-generated method stub
		super.visit(ForCondition);
	}

	@Override
	public void visit(Factor Factor) {
		// TODO Auto-generated method stub
		super.visit(Factor);
	}

	@Override
	public void visit(DeclList DeclList) {
		// TODO Auto-generated method stub
		super.visit(DeclList);
	}

	@Override
	public void visit(Designator Designator) {
		// TODO Auto-generated method stub
		super.visit(Designator);
	}

	@Override
	public void visit(MethodName MethodName) {
		// TODO Auto-generated method stub
		super.visit(MethodName);
	}

	@Override
	public void visit(Term Term) {
		// TODO Auto-generated method stub
		super.visit(Term);
	}

	@Override
	public void visit(StaticVarDecl StaticVarDecl) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDecl);
	}

	@Override
	public void visit(Condition Condition) {
		// TODO Auto-generated method stub
		super.visit(Condition);
	}

	@Override
	public void visit(FormalParamComma FormalParamComma) {
		// TODO Auto-generated method stub
		super.visit(FormalParamComma);
	}

	@Override
	public void visit(MethodList MethodList) {
		// TODO Auto-generated method stub
		super.visit(MethodList);
	}

	@Override
	public void visit(StaticMemberList StaticMemberList) {
		// TODO Auto-generated method stub
		super.visit(StaticMemberList);
	}

	@Override
	public void visit(Methods Methods) {
		// TODO Auto-generated method stub
		super.visit(Methods);
	}

	@Override
	public void visit(FormalParam FormalParam) {
		// TODO Auto-generated method stub
		super.visit(FormalParam);
	}

	@Override
	public void visit(Expr Expr) {
		// TODO Auto-generated method stub
		super.visit(Expr);
	}

	@Override
	public void visit(ActPars ActPars) {
		// TODO Auto-generated method stub
		super.visit(ActPars);
	}

	@Override
	public void visit(DesignatorStatement DesignatorStatement) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatement);
	}

	@Override
	public void visit(StaticMember StaticMember) {
		// TODO Auto-generated method stub
		super.visit(StaticMember);
	}

	@Override
	public void visit(Decl Decl) {
		// TODO Auto-generated method stub
		super.visit(Decl);
	}

	@Override
	public void visit(Statement Statement) {
		// TODO Auto-generated method stub
		super.visit(Statement);
	}

	@Override
	public void visit(VarDecl VarDecl) {
		// TODO Auto-generated method stub
		super.visit(VarDecl);
	}

	@Override
	public void visit(Type Type) {
		// TODO Auto-generated method stub
		super.visit(Type);
	}

	@Override
	public void visit(ConstDecl ConstDecl) {
		// TODO Auto-generated method stub
		super.visit(ConstDecl);
	}

	@Override
	public void visit(ConditionFactor ConditionFactor) {
		// TODO Auto-generated method stub
		super.visit(ConditionFactor);
	}

	@Override
	public void visit(DesignatorStatementComma DesignatorStatementComma) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementComma);
	}

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
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementCommaEmpty);
	}

	@Override
	public void visit(DesignatorStatementCommaEnd DesignatorStatementCommaEnd) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementCommaEnd);
	}

	@Override
	public void visit(DesignatorStatementCommaItem DesignatorStatementCommaItem) {
		// TODO Auto-generated method stub
		super.visit(DesignatorStatementCommaItem);
	}

	@Override
	public void visit(ForExit ForExit) {
		// TODO Auto-generated method stub
		super.visit(ForExit);
	}

	@Override
	public void visit(ForConditionEmpty ForConditionEmpty) {
		// TODO Auto-generated method stub
		super.visit(ForConditionEmpty);
	}

	@Override
	public void visit(ForConditionNonempty ForConditionNonempty) {
		// TODO Auto-generated method stub
		super.visit(ForConditionNonempty);
	}

	@Override
	public void visit(ForEntry ForEntry) {
		// TODO Auto-generated method stub
		super.visit(ForEntry);
	}

	@Override
	public void visit(IfStatement IfStatement) {
		// TODO Auto-generated method stub
		super.visit(IfStatement);
	}

	@Override
	public void visit(IfCondition IfCondition) {
		// TODO Auto-generated method stub
		super.visit(IfCondition);
	}

	@Override
	public void visit(StatementFor StatementFor) {
		// TODO Auto-generated method stub
		super.visit(StatementFor);
	}

	@Override
	public void visit(StatementMatchedIf StatementMatchedIf) {
		// TODO Auto-generated method stub
		super.visit(StatementMatchedIf);
	}

	@Override
	public void visit(StatementBlock StatementBlock) {
		// TODO Auto-generated method stub
		super.visit(StatementBlock);
	}

	@Override
	public void visit(StatementPrintNum StatementPrintNum) {
		// TODO Auto-generated method stub
		super.visit(StatementPrintNum);
	}

	@Override
	public void visit(StatementPrint StatementPrint) {
		// TODO Auto-generated method stub
		super.visit(StatementPrint);
	}

	@Override
	public void visit(StatementRead StatementRead) {
		// TODO Auto-generated method stub
		super.visit(StatementRead);
	}

	@Override
	public void visit(StatementReturnExpr StatementReturnExpr) {
		// TODO Auto-generated method stub
		super.visit(StatementReturnExpr);
	}

	@Override
	public void visit(StatementReturn StatementReturn) {
		// TODO Auto-generated method stub
		super.visit(StatementReturn);
	}

	@Override
	public void visit(StatementContinue StatementContinue) {
		// TODO Auto-generated method stub
		super.visit(StatementContinue);
	}

	@Override
	public void visit(StatementBreak StatementBreak) {
		// TODO Auto-generated method stub
		super.visit(StatementBreak);
	}

	@Override
	public void visit(StatementDesignatorStatement StatementDesignatorStatement) {
		// TODO Auto-generated method stub
		super.visit(StatementDesignatorStatement);
	}

	@Override
	public void visit(StatementUnmatchedIf StatementUnmatchedIf) {
		// TODO Auto-generated method stub
		super.visit(StatementUnmatchedIf);
	}

	@Override
	public void visit(StatementListEnd StatementListEnd) {
		// TODO Auto-generated method stub
		super.visit(StatementListEnd);
	}

	@Override
	public void visit(StatementListItem StatementListItem) {
		// TODO Auto-generated method stub
		super.visit(StatementListItem);
	}

	@Override
	public void visit(ActPar ActPar) {
		// TODO Auto-generated method stub
		super.visit(ActPar);
	}

	@Override
	public void visit(ActParsEmpty ActParsEmpty) {
		// TODO Auto-generated method stub
		super.visit(ActParsEmpty);
	}

	@Override
	public void visit(ActParsEnd ActParsEnd) {
		// TODO Auto-generated method stub
		super.visit(ActParsEnd);
	}

	@Override
	public void visit(ActParsItem ActParsItem) {
		// TODO Auto-generated method stub
		super.visit(ActParsItem);
	}

	@Override
	public void visit(MethodInvocation MethodInvocation) {
		// TODO Auto-generated method stub
		super.visit(MethodInvocation);
	}

	@Override
	public void visit(VarDeclArray VarDeclArray) {
		// TODO Auto-generated method stub
		super.visit(VarDeclArray);
	}

	@Override
	public void visit(VarDeclScalar VarDeclScalar) {
		// TODO Auto-generated method stub
		super.visit(VarDeclScalar);
	}

	@Override
	public void visit(VarDeclCommaEnd VarDeclCommaEnd) {
		// TODO Auto-generated method stub
		super.visit(VarDeclCommaEnd);
	}

	@Override
	public void visit(VarDeclCommaItem VarDeclCommaItem) {
		// TODO Auto-generated method stub
		super.visit(VarDeclCommaItem);
	}

	@Override
	public void visit(VarDeclLineStart VarDeclLineStart) {
		// TODO Auto-generated method stub
		super.visit(VarDeclLineStart);
	}

	@Override
	public void visit(VarDeclLine VarDeclLine) {
		// TODO Auto-generated method stub
		super.visit(VarDeclLine);
	}

	@Override
	public void visit(VarDeclLineListEnd VarDeclLineListEnd) {
		// TODO Auto-generated method stub
		super.visit(VarDeclLineListEnd);
	}

	@Override
	public void visit(VarDeclLineListItem VarDeclLineListItem) {
		// TODO Auto-generated method stub
		super.visit(VarDeclLineListItem);
	}

	@Override
	public void visit(FormalParamArray FormalParamArray) {
		// TODO Auto-generated method stub
		super.visit(FormalParamArray);
	}

	@Override
	public void visit(FormalParamScalar FormalParamScalar) {
		// TODO Auto-generated method stub
		super.visit(FormalParamScalar);
	}

	@Override
	public void visit(FormalParamCommaEmpty FormalParamCommaEmpty) {
		// TODO Auto-generated method stub
		super.visit(FormalParamCommaEmpty);
	}

	@Override
	public void visit(FormalParamCommaEnd FormalParamCommaEnd) {
		// TODO Auto-generated method stub
		super.visit(FormalParamCommaEnd);
	}

	@Override
	public void visit(FormalParamCommaItem FormalParamCommaItem) {
		// TODO Auto-generated method stub
		super.visit(FormalParamCommaItem);
	}

	@Override
	public void visit(MethodNameCons MethodNameCons) {
		// TODO Auto-generated method stub
		super.visit(MethodNameCons);
	}

	@Override
	public void visit(MethodNameVoid MethodNameVoid) {
		// TODO Auto-generated method stub
		super.visit(MethodNameVoid);
	}

	@Override
	public void visit(MethodNameRet MethodNameRet) {
		// TODO Auto-generated method stub
		super.visit(MethodNameRet);
	}

	@Override
	public void visit(MethodDeclaration MethodDeclaration) {
		// TODO Auto-generated method stub
		super.visit(MethodDeclaration);
	}

	@Override
	public void visit(Method Method) {
		// TODO Auto-generated method stub
		super.visit(Method);
	}

	@Override
	public void visit(MethodListEnd MethodListEnd) {
		// TODO Auto-generated method stub
		super.visit(MethodListEnd);
	}

	@Override
	public void visit(MethodListItem MethodListItem) {
		// TODO Auto-generated method stub
		super.visit(MethodListItem);
	}

	@Override
	public void visit(StaticInitializerStart StaticInitializerStart) {
		// TODO Auto-generated method stub
		super.visit(StaticInitializerStart);
	}

	@Override
	public void visit(StaticInitializer StaticInitializer) {
		// TODO Auto-generated method stub
		super.visit(StaticInitializer);
	}

	@Override
	public void visit(StaticVarDeclArray StaticVarDeclArray) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclArray);
	}

	@Override
	public void visit(StaticVarDeclScalar StaticVarDeclScalar) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclScalar);
	}

	@Override
	public void visit(StaticVarDeclCommaEnd StaticVarDeclCommaEnd) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclCommaEnd);
	}

	@Override
	public void visit(StaticVarDeclCommaItem StaticVarDeclCommaItem) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclCommaItem);
	}

	@Override
	public void visit(StaticVarDeclLineStart StaticVarDeclLineStart) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclLineStart);
	}

	@Override
	public void visit(StaticVarDeclLine StaticVarDeclLine) {
		// TODO Auto-generated method stub
		super.visit(StaticVarDeclLine);
	}

	@Override
	public void visit(MethodsEmpty MethodsEmpty) {
		// TODO Auto-generated method stub
		super.visit(MethodsEmpty);
	}

	@Override
	public void visit(MethodsNonempty MethodsNonempty) {
		// TODO Auto-generated method stub
		super.visit(MethodsNonempty);
	}

	@Override
	public void visit(StaticMemberVar StaticMemberVar) {
		// TODO Auto-generated method stub
		super.visit(StaticMemberVar);
	}

	@Override
	public void visit(StaticMemberInitializer StaticMemberInitializer) {
		// TODO Auto-generated method stub
		super.visit(StaticMemberInitializer);
	}

	@Override
	public void visit(StaticMemberListEnd StaticMemberListEnd) {
		// TODO Auto-generated method stub
		super.visit(StaticMemberListEnd);
	}

	@Override
	public void visit(StaticMemberListItem StaticMemberListItem) {
		// TODO Auto-generated method stub
		super.visit(StaticMemberListItem);
	}

	@Override
	public void visit(ClassNameExtend ClassNameExtend) {
		super.visit(ClassNameExtend);
	}
	@Override
	public void visit(ClassNameNoExtend ClassNameNoExtend) {
		super.visit(ClassNameNoExtend);
	}
	@Override
	public void visit(ClassDecl ClassDecl) {
		super.visit(ClassDecl);
		
		Obj obj = ClassDecl.obj;
		ConstructorIterator iter = new ConstructorIterator(obj.getType());
		Obj cons0 = iter.next();
		if (cons0.getAdr() == -1) {
			//podrazumevani konstruktor
			cons0.setAdr(Code.pc);
			Code.put(Code.enter); Code.put(1); Code.put(1);
			Code.put(Code.load);
		}
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
