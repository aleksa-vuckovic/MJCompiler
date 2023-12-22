package rs.ac.bg.etf.pp1;

import org.apache.log4j.Logger;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.my.ConstructorIterator;
import rs.ac.bg.etf.pp1.my.MyInt;
import rs.ac.bg.etf.pp1.my.MyTab;
import rs.ac.bg.etf.pp1.my.TypeList;
import rs.ac.bg.etf.pp1.my.Utils;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class SemanticAnalyzer extends VisitorAdaptor {
	
	Logger log = Logger.getLogger(getClass());
	public boolean errorDetected = false;
	
	private void report_error(String message, SyntaxNode node) {
		log.error("Semanticka greska na liniji " + node.getLine() + ": " + message);
		errorDetected = true;
	}
	
	Obj currentClass = null;
	Obj currentMethod = null;
	
	Struct declLineType = null;
	int totalStatic = 0;
	int fors = 0;
	int constructorCount = 0;
	
	int allocateStatic(int size) {
		int ret = totalStatic;
		totalStatic += size;
		return ret;
	}
	
	public SemanticAnalyzer() {
		MyTab.init();
	}

	/*
	 * Designator
	 */
	@Override
	public void visit(DesignatorSimple DesignatorSimple) {
		super.visit(DesignatorSimple);
		String name = DesignatorSimple.getName();
		Obj obj = MyTab.find(name);
		if (obj == MyTab.noObj) {
			report_error("Identifikator " + name + " nije deklarisan.", DesignatorSimple);
			DesignatorSimple.obj = MyTab.noObj;
		}
		else if (currentMethod == null && obj.getLevel() == 1) {
			report_error("Pristup nestatickom objektu " + name + " iz statickog kontektsta.", DesignatorSimple);
			DesignatorSimple.obj = MyTab.noObj;
			//Ovo se desava samo u statickom inicijalizatoru
		}
		else {
			DesignatorSimple.obj = obj;
		}
	}
	@Override
	public void visit(DesignatorScoped DesignatorScoped) {
		super.visit(DesignatorScoped);
		String namespace = DesignatorScoped.getNamespace();
		Obj namespaceObj = MyTab.find(namespace);
		if (namespaceObj.getKind() != Utils.Namespace) {
			report_error("Identifikator " + namespace + " ne oznacava prostor imena.", DesignatorScoped);
			DesignatorScoped.obj = MyTab.noObj;
		}
		else {
			String name = DesignatorScoped.getName();
			Obj obj = namespaceObj.getType().getMembersTable().searchKey(name);
			if (obj == null) {
				report_error("Identifikator " + namespace + "::" + name + " nije deklarisan.", DesignatorScoped);
				DesignatorScoped.obj = MyTab.noObj;
			}
			else if (currentMethod == null && obj.getLevel() == 1) {
				report_error("Pristup nestatickom objektu " + namespace + "::" + name + " iz statickog kontektsta.", DesignatorScoped);
				DesignatorScoped.obj = MyTab.noObj;
			}
			else {
				DesignatorScoped.obj = obj;
			}
		}
	}
	@Override
	public void visit(DesignatorElem DesignatorElem) {
		super.visit(DesignatorElem);
		Struct exprType = DesignatorElem.getExpr().struct;
		Obj designator = DesignatorElem.getDesignator().obj;
		
		if (exprType != MyTab.intType) {
			report_error("Indeksiranje je moguce samo tipom int.", DesignatorElem);
			DesignatorElem.obj = MyTab.noObj;
		}
		else if (designator.getType().getKind() != Struct.Array) {
			report_error("Indeksiranje je moguce samo nad nizovima.", DesignatorElem);
			DesignatorElem.obj = MyTab.noObj;
		}
		else {
			DesignatorElem.obj = new Obj(Obj.Elem, "dummy", designator.getType().getElemType());
		}
	}
	@Override
	public void visit(DesignatorField DesignatorField) {
		super.visit(DesignatorField);
		Obj designator = DesignatorField.getDesignator().obj;
		String field = DesignatorField.getName();
		int kind = designator.getKind();
		
		if (designator == MyTab.noObj) {
			DesignatorField.obj = MyTab.noObj;
		}
		else if (kind == Obj.Con || kind == Obj.Meth) {
			String msg = kind == Obj.Con ? "Konstante" : "Metode";
			report_error(msg + " nemaju polja.", DesignatorField);
			DesignatorField.obj = MyTab.noObj;
		}
		else if (kind == Obj.Type || kind == Obj.Prog || kind == Utils.Namespace) {
			Obj obj = designator.getType().getMembersTable().searchKey(field);
			if (obj == null) {
				report_error("Tip " + designator.getName() + " ne sadrzi ime " + field + ".", DesignatorField);
				DesignatorField.obj = MyTab.noObj;
			}
			else if (obj.getLevel() == 1) {
				report_error("Clan " + field + " klase " + designator.getName() + " nije staticki.", DesignatorField);
				DesignatorField.obj = MyTab.noObj;
			}
			else {
				DesignatorField.obj = obj;
			}
		}
		else {
			Obj obj = designator.getType().getMembersTable().searchKey(field);
			if (obj == null) {
				report_error("Clan " + field + " nije pronadjen.", DesignatorField);
				DesignatorField.obj = MyTab.noObj;
			}
			else if (obj.getLevel() == 0) {
				report_error("Pristup statickom clanu preko reference.", DesignatorField);
				DesignatorField.obj = MyTab.noObj;
			}
			else {
				DesignatorField.obj = obj;
			}
		}
	}

	/*
	 * Operators
	 */
	@Override
	public void visit(MulopMod MulopMod) {
		super.visit(MulopMod);
		MulopMod.myint = new MyInt(Code.rem);
	}
	@Override
	public void visit(MulopDiv MulopDiv) {
		super.visit(MulopDiv);
		MulopDiv.myint = new MyInt(Code.div);
	}
	@Override
	public void visit(MulopMul MulopMul) {
		super.visit(MulopMul);
		MulopMul.myint = new MyInt(Code.mul);
	}
	@Override
	public void visit(AddopSub AddopSub) {
		super.visit(AddopSub);
		AddopSub.myint = new MyInt(Code.sub);
	}
	@Override
	public void visit(AddopAdd AddopAdd) {
		super.visit(AddopAdd);
		AddopAdd.myint = new MyInt(Code.add);
	}
	@Override
	public void visit(RelopLe RelopLe) {
		super.visit(RelopLe);
		RelopLe.myint = new MyInt(Code.le);
	}
	@Override
	public void visit(RelopLt RelopLt) {
		super.visit(RelopLt);
		RelopLt.myint = new MyInt(Code.lt);
	}
	@Override
	public void visit(RelopGe RelopGe) {
		super.visit(RelopGe);
		RelopGe.myint = new MyInt(Code.ge);
	}
	@Override
	public void visit(RelopGt RelopGt) {
		super.visit(RelopGt);
		RelopGt.myint = new MyInt(Code.gt);
	}
	@Override
	public void visit(RelopNe RelopNe) {
		super.visit(RelopNe);
		RelopNe.myint = new MyInt(Code.ne);
	}
	@Override
	public void visit(RelopEq RelopEq) {
		super.visit(RelopEq);
		RelopEq.myint = new MyInt(Code.eq);
	}
	@Override
	public void visit(Assignop Assignop) {
		super.visit(Assignop);
		Assignop.myint = new MyInt(sym.ASSIGN);
	}

	/*
	 * Type
	 */
	@Override
	public void visit(TypeScoped TypeScoped) {
		super.visit(TypeScoped);
		String namespace = TypeScoped.getNamespace();
		String name = TypeScoped.getName();
		Obj namespaceObj = MyTab.find(namespace);
		if (namespaceObj.getKind() != Utils.Namespace) {
			report_error("Identifikator " + namespace + " ne oznacava prostor imena.", TypeScoped);
			TypeScoped.struct = MyTab.noType;
		}
		else {
			Obj obj = namespaceObj.getType().getMembersTable().searchKey(name);
			if (obj == null) {
				report_error("Identifikator " + name + " ne postoji u prostoru imena " + namespace + ".", TypeScoped);
				TypeScoped.struct = MyTab.noType;
			}
			else if (obj.getKind() != Obj.Type) {
				report_error("Identifikator " + namespace + "::" + name + " ne oznacava tip.", TypeScoped);
				TypeScoped.struct = MyTab.noType;
			}
			else {
				TypeScoped.struct = obj.getType();
			}
		}
	}
	@Override
	public void visit(TypeSimple TypeSimple) {
		super.visit(TypeSimple);
		String name = TypeSimple.getName();
		Obj obj = MyTab.find(name);
		if (obj == null || obj.getKind() != Obj.Type) {
			report_error("Identifikator " + name + " ne oznacava tip.", TypeSimple);
			TypeSimple.struct = MyTab.noType;
		}
		else {
			TypeSimple.struct = obj.getType();
		}
	}

	/*
	 * Expr
	 */
	@Override
	public void visit(ConstructorInvocation ConstructorInvocation) {
		super.visit(ConstructorInvocation);
		Struct cls = ConstructorInvocation.getType().struct;
		if (cls.getKind() != Struct.Class) {
			report_error("Poziv konstruktora je moguc samo za korisnicke tipove.", ConstructorInvocation);
			ConstructorInvocation.struct = MyTab.noType;
		}
		else {
			ConstructorInvocation.struct = cls;
		}
	}
	@Override
	public void visit(FactorCons FactorCons) {
		super.visit(FactorCons);
		Struct cls = FactorCons.getConstructorInvocation().struct;
		TypeList args = FactorCons.getActPars().typelist;
		
		Obj cons = Utils.findConstructor(cls, args);
		if (cons == null) {
			report_error("Ne postoji konstruktor kome odgovaraju prosledjeni tipovi argumenata!", FactorCons);
			FactorCons.struct = MyTab.noType;
		}
		else {
			FactorCons.struct = cls;
		}
	}
	@Override
	public void visit(FactorExpr FactorExpr) {
		super.visit(FactorExpr);
		FactorExpr.struct = FactorExpr.getExpr().struct;
	}
	@Override
	public void visit(FactorArray FactorArray) {
		super.visit(FactorArray);
		Struct arrType = FactorArray.getType().struct;
		Struct exprType = FactorArray.getExpr().struct;
		if (exprType != MyTab.intType) {
			report_error("Velicina niza se zadaje samo int tipom.", FactorArray);
			FactorArray.struct = MyTab.noType;
		}
		else {
			FactorArray.struct = new Struct(Struct.Array, arrType);
		}
	}
	@Override
	public void visit(FactorMethod FactorMethod) {
		super.visit(FactorMethod);
		Struct res = FactorMethod.getMethodInvocation().struct;
		if (res == MyTab.noType) {
			report_error("Poziv void metode u izrazu nije dozvoljen!", FactorMethod);
			FactorMethod.struct = MyTab.noType;
		}
		else {
			FactorMethod.struct = res;
		}
	}
	@Override
	public void visit(FactorDesignator FactorDesignator) {
		super.visit(FactorDesignator);
		Obj designator = FactorDesignator.getDesignator().obj;
		if (hasValue(designator)) {
			FactorDesignator.struct = designator.getType();
		}
		else {
			report_error("Identifikator bez vrednosti u izrazu.", FactorDesignator);
			FactorDesignator.struct = MyTab.noType;
		}
	}
	private boolean hasValue(Obj obj) {
		int kind = obj.getKind();
		return (kind == Obj.Con || kind == Obj.Elem || kind == Obj.Fld || kind == Obj.Var);
	}
	@Override
	public void visit(FactorBool FactorBool) {
		super.visit(FactorBool);
		FactorBool.struct = MyTab.boolType;
	}
	@Override
	public void visit(FactorChar FactorChar) {
		super.visit(FactorChar);
		FactorChar.struct = MyTab.charType;
	}
	@Override
	public void visit(FactorNum FactorNum) {
		super.visit(FactorNum);
		FactorNum.struct = MyTab.intType;
	}
	@Override
	public void visit(TermSingle TermSingle) {
		super.visit(TermSingle);
		TermSingle.struct = TermSingle.getFactor().struct;
	}
	@Override
	public void visit(TermMul TermMul) {
		super.visit(TermMul);
		Struct type1 = TermMul.getTerm().struct;
		Struct type2 = TermMul.getFactor().struct;
		if (type1 != MyTab.intType || type2 != MyTab.intType) {
			report_error("Aritmeticki operatori se primenljuju samo na tip int.", TermMul);
			TermMul.struct = MyTab.noType;
		}
		else {
			TermMul.struct = MyTab.intType;
		}
	}
	@Override
	public void visit(ExprSingleNeg ExprSingleNeg) {
		super.visit(ExprSingleNeg);
		Struct type = ExprSingleNeg.getTerm().struct;
		if (type != MyTab.intType) {
			report_error("Aritmeticki operatori se primenljuju samo na tip int.", ExprSingleNeg);
			ExprSingleNeg.struct = MyTab.noType;
		}
		else {
			ExprSingleNeg.struct = MyTab.intType;
		}
	}
	@Override
	public void visit(ExprSingle ExprSingle) {
		super.visit(ExprSingle);
		ExprSingle.struct = ExprSingle.getTerm().struct;
	}
	@Override
	public void visit(ExprAdd ExprAdd) {
		super.visit(ExprAdd);
		Struct type1 = ExprAdd.getExpr().struct;
		Struct type2 = ExprAdd.getTerm().struct;
		if (type1 != MyTab.intType || type2 != MyTab.intType) {
			report_error("Aritmeticki operatori se primenljuju samo na tip int.", ExprAdd);
			ExprAdd.struct = MyTab.noType;
		} else {
			ExprAdd.struct =  MyTab.intType;
		}
	}
	
	/*
	 * Condition
	 */
	@Override
	public void visit(ConditonFactorExpr ConditonFactorExpr) {
		super.visit(ConditonFactorExpr);
	}
	@Override
	public void visit(ConditionFactorRel ConditionFactorRel) {
		super.visit(ConditionFactorRel);
		Struct type1 = ConditionFactorRel.getExpr().struct;
		Struct type2 = ConditionFactorRel.getExpr1().struct;
		int relop = ConditionFactorRel.getRelop().myint.val;
		if (!type1.compatibleWith(type2)) {
			report_error("Tipovi izraza relacionog operatora nisu kompatibilni.", ConditionFactorRel);
		}
		else if (type1.isRefType() && relop != Code.eq && relop != Code.ne) {
			report_error("Referencijalni tipovi mogu da se uporedjuju samo relacionim operatorima == i != .", ConditionFactorRel);
		}
	}
	@Override
	public void visit(ConditionTermSingle ConditionTermSingle) {
		super.visit(ConditionTermSingle);
	}
	@Override
	public void visit(ConditionTermAnd ConditionTermAnd) {
		super.visit(ConditionTermAnd);
	}
	@Override
	public void visit(ConditionSingle ConditionSingle) {
		super.visit(ConditionSingle);
	}
	@Override
	public void visit(ConditionOr ConditionOr) {
		super.visit(ConditionOr);
	}

	/*
	 * Designator statement
	 */
	@Override
	public void visit(DesignatorStatementDec DesignatorStatementDec) {
		super.visit(DesignatorStatementDec);
		Obj designator = DesignatorStatementDec.getDesignator().obj;
		if (checkAssignable(designator, DesignatorStatementDec)) {
			if (designator.getType() != MyTab.intType) {
				report_error("Dekrement operator zahteva int.", DesignatorStatementDec);
			}
		}
	}
	@Override
	public void visit(DesignatorStatementInc DesignatorStatementInc) {
		super.visit(DesignatorStatementInc);
		Obj designator = DesignatorStatementInc.getDesignator().obj;
		if (checkAssignable(designator, DesignatorStatementInc)) {
			if (designator.getType() != MyTab.intType) {
				report_error("Inkrement operator zahteva int.", DesignatorStatementInc);
			}
		}
	}
	@Override
	public void visit(DesignatorStatementMethod DesignatorStatementMethod) {
		super.visit(DesignatorStatementMethod);
	}
	@Override
	public void visit(DesignatorStatementAssign DesignatorStatementAssign) {
		super.visit(DesignatorStatementAssign);
		Obj designator = DesignatorStatementAssign.getDesignator().obj;
		if (checkAssignable(designator, DesignatorStatementAssign)) {
			if (!Utils.assignableTo(DesignatorStatementAssign.getExpr().struct, designator.getType())) {
				report_error("Tip izraza nije dodeljiv entitetu sa leve strane dodele.", DesignatorStatementAssign);
			}
		}
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
		fors++;
	}
	@Override
	public void visit(ForConditionEmpty ForConditionEmpty) {
		super.visit(ForConditionEmpty);
	}
	@Override
	public void visit(ForConditionNonempty ForConditionNonempty) {
		super.visit(ForConditionNonempty);
	}
	@Override
	public void visit(ForEntry ForEntry) {
		super.visit(ForEntry);
	}
	@Override
	public void visit(IfStatement IfStatement) {
		super.visit(IfStatement);
	}
	@Override
	public void visit(IfCondition IfCondition) {
		super.visit(IfCondition);
	}
	@Override
	public void visit(StatementFor StatementFor) {
		super.visit(StatementFor);
		fors--;
	}
	@Override
	public void visit(StatementMatchedIf StatementMatchedIf) {
		super.visit(StatementMatchedIf);
	}
	@Override
	public void visit(StatementBlock StatementBlock) {
		super.visit(StatementBlock);
	}
	@Override
	public void visit(StatementPrintNum StatementPrintNum) {
		super.visit(StatementPrintNum);
		Struct type = StatementPrintNum.getExpr().struct;
		if (type != MyTab.intType && type != MyTab.charType && type != MyTab.boolType) {
			report_error("Print naredba kao argument moze da ima samo tipove int, bool ili char.", StatementPrintNum);
		}
	}
	@Override
	public void visit(StatementPrint StatementPrint) {
		super.visit(StatementPrint);
		Struct type = StatementPrint.getExpr().struct;
		if (type != MyTab.intType && type != MyTab.charType && type != MyTab.boolType) {
			report_error("Print naredba kao argument moze da ima samo tipove int, bool ili char.", StatementPrint);
		}
	}
	@Override
	public void visit(StatementRead StatementRead) {
		super.visit(StatementRead);
		Obj designator = StatementRead.getDesignator().obj;
		if (checkAssignable(designator, StatementRead)) {
			Struct type = designator.getType();
			if (type != MyTab.intType && type != MyTab.charType && type != MyTab.boolType) {
				report_error("Read naredba kao argument moze da ima samo tipove int, bool ili char.", StatementRead);
			}
		}
	}
	@Override
	public void visit(StatementReturnExpr StatementReturnExpr) {
		super.visit(StatementReturnExpr);
		if (currentMethod == null || !Utils.assignableTo(StatementReturnExpr.getExpr().struct, currentMethod.getType())) {
			report_error("Tip izraza u return naredbi ne odgovara povratnom tipu metode.", StatementReturnExpr);
		}
	}
	@Override
	public void visit(StatementReturn StatementReturn) {
		super.visit(StatementReturn);
		if (currentMethod != null && currentMethod.getType() != MyTab.noType) {
			report_error("Return naredba mora da vrati vrednost koja odgovara povratnom tipu metode.", StatementReturn);
		}
		//return naredba moze da se javi i u static bloku, tada je currentMethod == null
	}
	@Override
	public void visit(StatementContinue StatementContinue) {
		super.visit(StatementContinue);
		if (fors == 0) {
			report_error("Continue naredba nije dozvoljena van for petlje.", StatementContinue);
		}
	}
	@Override
	public void visit(StatementBreak StatementBreak) {
		super.visit(StatementBreak);
		if (fors == 0) {
			report_error("Break naredba nije dozvoljena van for petlje.", StatementBreak);
		}
	}
	@Override
	public void visit(StatementDesignatorStatement StatementDesignatorStatement) {
		super.visit(StatementDesignatorStatement);
	}
	@Override
	public void visit(StatementUnmatchedIf StatementUnmatchedIf) {
		super.visit(StatementUnmatchedIf);
	}
	@Override
	public void visit(StatementListEnd StatementListEnd) {
		super.visit(StatementListEnd);
	}
	@Override
	public void visit(StatementListItem StatementListItem) {
		super.visit(StatementListItem);
	}
	private boolean checkAssignable(Obj obj, SyntaxNode node) {
		int kind = obj.getKind();
		if (kind != Obj.Var && kind != Obj.Fld && kind != Obj.Elem) {
			report_error("Dodela je moguca samo promenljivoj, polju ili elementu niza.", node);
			return false;
		}
		return true;
	}

	/*
	 * Actual parameters, Method invocation
	 */
	@Override
	public void visit(ActPar ActPar) {
		super.visit(ActPar);
		ActPar.struct = ActPar.getExpr().struct;
	}
	@Override
	public void visit(ActParsEmpty ActParsEmpty) {
		super.visit(ActParsEmpty);
		ActParsEmpty.typelist = new TypeList();
	}
	@Override
	public void visit(ActParsEnd ActParsEnd) {
		super.visit(ActParsEnd);
		ActParsEnd.typelist = new TypeList();
		ActParsEnd.typelist.list.add(ActParsEnd.getActPar().struct);
	}
	@Override
	public void visit(ActParsItem ActParsItem) {
		super.visit(ActParsItem);
		TypeList list = ActParsItem.getActPars().typelist;
		list.list.add(ActParsItem.getActPar().struct);
		ActParsItem.typelist = list;
	}
	@Override
	public void visit(MethodInvocation MethodInvocation) {
		super.visit(MethodInvocation);
		Obj designator = MethodInvocation.getDesignator().obj;
		TypeList args = MethodInvocation.getActPars().typelist;
		if (designator.getKind() != Obj.Meth) {
			report_error("Poziv metode nad objektom koji nije metoda.", MethodInvocation);
		}
		else {
			if (designator.getLevel() == 1) { //metoda clan
				//prvi argument je sam designator i on je ispravan
				//jer u suprotnom semanticka provera designator-a ne bi uspela
				args.list.add(0, Utils.firstParam(designator));
			}
			
			if (!Utils.invokable(designator, args)) {
				report_error("Stvarni argumenti ne odgovaraju tipovima formalnih argumenata.", MethodInvocation);
			}
		}
		MethodInvocation.struct = designator.getType();
	}

	/*
	 * Var declaration
	 */
	@Override
	public void visit(VarDeclArray VarDeclArray) {
		super.visit(VarDeclArray);
		String name = VarDeclArray.getName();
		Struct type = new Struct(Struct.Array, declLineType);
		addVarDecl(name, type, VarDeclArray);
	}
	@Override
	public void visit(VarDeclScalar VarDeclScalar) {
		super.visit(VarDeclScalar);
		String name = VarDeclScalar.getName();
		Struct type = declLineType;
		addVarDecl(name, type, VarDeclScalar);	
	}
	private void addVarDecl(String name, Struct type, SyntaxNode node) {
		Obj obj;
		if (currentMethod != null) {
			//Kontekst metode
			obj = new Obj(Obj.Var, name, type, Obj.NO_VALUE, 1); //stek promenljiva
			obj.setFpPos(1); //lokalna promenljiva (nije argument)
			if (!MyTab.insert(obj)) {
				report_error("Lokalna promenljiva sa imenom " + name + " je vec deklarisana.", node);
			}
			else {
				int adr = Utils.localVariableCount(currentMethod) -1; //broj lokalnih promeljivih, ujedno i pomeraj na steku
				obj.setAdr(adr);
			}
		}
		else if (currentClass == null) {
			//Globalni staticki kontekst
			obj = new Obj(Obj.Var, name, type, allocateStatic(1), 0); //staticka promenljiva
			obj.setFpPos(Obj.NO_VALUE);
			if (!MyTab.insert(obj)) {
				report_error("Ime " + name + " je vec deklarisano.", node);
			}
		}
		else {
			//Klasni kontekst polja
			obj = new Obj(Obj.Fld, name, type, Obj.NO_VALUE, 1);
			obj.setFpPos(Obj.NO_VALUE);
			if (!MyTab.insert(obj)) {
				report_error("Clan klase sa imenom " + name + " je vec deklarisan.", node);
			}
			else {
				int adr = currentClass.getFpPos(); //broj polja, ne racunajuci vtable pokazivac
				obj.setAdr(++adr);
				currentClass.setFpPos(adr);
			}
		}
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
		declLineType = VarDeclLineStart.getType().struct;
	}
	@Override
	public void visit(VarDeclLine VarDeclLine) {
		super.visit(VarDeclLine);
		declLineType = null;
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
	 * FORMAL PARAM DECALRATION
	 */
	@Override
	public void visit(FormalParamArray FormalParamArray) {
		super.visit(FormalParamArray);
		String name = FormalParamArray.getName();
		Struct type  = new Struct(Struct.Array, FormalParamArray.getType().struct);
		addFormalParam(name, type, FormalParamArray);
	}
	@Override
	public void visit(FormalParamScalar FormalParamScalar) {
		super.visit(FormalParamScalar);
		String name = FormalParamScalar.getName();
		Struct type = FormalParamScalar.getType().struct;
		addFormalParam(name, type, FormalParamScalar);
	}
	private void addFormalParam(String name, Struct type, SyntaxNode node) {
		Obj obj = new Obj(Obj.Var, name, type, Obj.NO_VALUE, 1); //stek promenljiva
		obj.setFpPos(0); //argument
		if (!MyTab.insert(obj)) {
			report_error("Formalni parametar sa imenom " + name + " je vec deklarisan.", node);
		}
		else {
			int adr = currentMethod.getFpPos();
			obj.setAdr(adr++);
			currentMethod.setFpPos(adr);
		}
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
	 * METHOD DECLARATION
	 */
	@Override
	public void visit(MethodNameCons MethodNameCons) {
		super.visit(MethodNameCons);
		String name = MethodNameCons.getName();
		Struct type = MyTab.noType;
		if (currentClass == null) {
			report_error("Deklaracija metode mora da sadrzi povratni tip.", MethodNameCons);
		}
		else {
			if (!name.equals(currentClass.getName())) {
				report_error("Ime konstruktora mora da bude isto kao ime klase.", MethodNameCons);
			}
			name = Utils.CONSPREFIX + constructorCount;
		}
		MethodNameCons.obj = methodDeclared(name, type);
	}
	@Override
	public void visit(MethodNameVoid MethodNameVoid) {
		super.visit(MethodNameVoid);
		String name = MethodNameVoid.getName();
		Struct type = MyTab.noType;
		MethodNameVoid.obj = methodDeclared(name, type);
	}
	@Override
	public void visit(MethodNameRet MethodNameRet) {
		super.visit(MethodNameRet);
		String name = MethodNameRet.getName();
		Struct type = MethodNameRet.getType().struct;
		MethodNameRet.obj = methodDeclared(name, type);
		
	}
	private Obj methodDeclared(String name, Struct type) {
		Obj obj = new Obj(Obj.Meth, name, type, Obj.NO_VALUE, 0); //staticka metoda
		MyTab.openScopeAndChain(obj);
		if (currentClass == null) {
			//globalni kontekst
			obj.setFpPos(0); // -> 0 argumenata i lokalnih promenljivih
		}
		else {
			//kontekst klase
			if (!Utils.isConstructor(obj)) obj.setLevel(1); // -> metoda clan
			obj.setFpPos(1); // -> podrazumavani this
			Obj thisArg = new Obj(Obj.Var, "this", currentClass.getType(), 0, 1); //level = 1 -> stek promenljiva
			thisArg.setFpPos(0); // -> argument
			MyTab.insert(thisArg);
		}
		currentMethod = obj;
		return obj;
	}
	@Override
	public void visit(MethodDeclaration MethodDeclaration) {
		super.visit(MethodDeclaration);
		Obj obj = MethodDeclaration.getMethodName().obj;
		if (currentClass == null) {
			//globalni kontekst
			if (!MyTab.insertParentScope(obj)) {
				report_error("Ime " + obj.getName() + " je vec deklarisano.", MethodDeclaration);
			}
		} else {
			if (Utils.isConstructor(obj)) {
				//provera da li vec postoji konstruktor sa istim parametrima
				ConstructorIterator iter = new ConstructorIterator(currentClass.getType());
				Obj cur = iter.next();
				boolean ok = true;
				while (cur != null) {
					if (Utils.identicalArguments(cur, obj)) {
						ok = false;
						break;
					}
					cur = iter.next();
				}
				if (ok) {
					MyTab.insertParentScope(obj);
					constructorCount++;
				}
				else {
					report_error("Konstruktor sa istim parametrima je vec deklarisan.", MethodDeclaration);
				}
			}
			else {
				if (!MyTab.insertParentScope(obj)) {
					Obj base = MyTab.parentScope().findSymbol(obj.getName());
					if (base.getKind() != Obj.Meth || base.getLevel() != 1) {
						report_error("Ime " + obj.getName() + " je vec deklarisano.", MethodDeclaration);
					}
					else if (Utils.overrideCompatible(base, obj)) {
						//base.setType(obj.getType())
						base.setFpPos(obj.getFpPos());
						MyTab.chainLocalSymbols(base);
						obj = base;
					} else {
						report_error("Override nije moguc jer argumenti ili povratna vrednost nisu kompatibilni!", MethodDeclaration);
					}
				}
			}
		}
		MethodDeclaration.getMethodName().obj = obj;
		MethodDeclaration.obj = obj;
	}
	@Override
	public void visit(Method Method) {
		super.visit(Method);
		MyTab.closeScope();
		currentMethod = null;
		Method.obj = Method.getMethodDeclaration().obj;
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
	 * STATIC DECLARATIONS
	 */
	@Override
	public void visit(StaticInitializerStart StaticInitializerStart) {
		super.visit(StaticInitializerStart);
	}
	@Override
	public void visit(StaticInitializer StaticInitializer) {
		super.visit(StaticInitializer);
	}
	@Override
	public void visit(StaticVarDeclArray StaticVarDeclArray) {
		super.visit(StaticVarDeclArray);
		String name = StaticVarDeclArray.getName();
		int adr = allocateStatic(1);
		Struct type = new Struct(Struct.Array, declLineType);
		Obj obj = new Obj(Obj.Var, name, type, adr, 0); //level == 0 -> static
		obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", StaticVarDeclArray);
		}
	}
	@Override
	public void visit(StaticVarDeclScalar StaticVarDeclScalar) {
		super.visit(StaticVarDeclScalar);
		String name = StaticVarDeclScalar.getName();
		int adr = allocateStatic(1);
		Struct type = declLineType;
		Obj obj = new Obj(Obj.Var, name, type, adr, 0); //level == 0 -> static
		obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", StaticVarDeclScalar);
		}
	}
	@Override
	public void visit(StaticVarDeclCommaEnd StaticVarDeclListEnd) {
		super.visit(StaticVarDeclListEnd);
	}
	@Override
	public void visit(StaticVarDeclCommaItem StaticVarDeclListItem) {
		super.visit(StaticVarDeclListItem);
	}
	@Override
	public void visit(StaticVarDeclLineStart StaticVarDeclLineStart) {
		super.visit(StaticVarDeclLineStart);
		declLineType = StaticVarDeclLineStart.getType().struct;
	}
	@Override
	public void visit(StaticVarDeclLine StaticVarDeclLine) {
		super.visit(StaticVarDeclLine);
		declLineType = null;
	}
	
	/*
	 * CLASS DECLARATION FILLERS
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
	 * CLASS DECLARATION
	 */
	@Override
	public void visit(ClassNameExtend ClassNameExtend) {
		super.visit(ClassNameExtend);
		
		String name = ClassNameExtend.getName();
		Struct struct = new Struct(Struct.Class);
		Obj obj = new Obj(Obj.Type, name, struct, Obj.NO_VALUE, 0);
		obj.setFpPos(0);
		currentClass = obj;
		ClassNameExtend.obj = obj;
		constructorCount = 0;
		
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", ClassNameExtend);
		}
		MyTab.openScopeAndChain(struct);
		
		Struct baseType = ClassNameExtend.getType().struct;
		if (baseType.getKind() != Struct.Class) {
			report_error("Nedozvoljeno izvodjenje klase iz nekorisnickog tipa.", ClassNameExtend);
		}
		else {
			struct.setElementType(baseType);
			for (Obj member: baseType.getMembers()) {
				if (member.getLevel() == 0) continue; //staticki clanovi se prekacu (ovo ukljucuje i konstruktore)
				MyTab.insert(Utils.clone(member)); //kopija, jer se zbog override moze menjati metoda
				if (member.getKind() == Obj.Fld) obj.setFpPos(obj.getFpPos() + 1); //fpPos predstavlja broj polja
			}
		}
		
	}
	@Override
	public void visit(ClassNameNoExtend ClassNameNoExtend) {
		super.visit(ClassNameNoExtend);
		String name = ClassNameNoExtend.getName();
		Struct struct = new Struct(Struct.Class);
		Obj obj = new Obj(Obj.Type, name, struct, Obj.NO_VALUE, 0);
		obj.setFpPos(0);
		currentClass = obj;
		ClassNameNoExtend.obj = obj;
		constructorCount = 0;
		
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", ClassNameNoExtend);
		}
		MyTab.openScopeAndChain(struct);
	}
	@Override
	public void visit(ClassDecl ClassDecl) {
		super.visit(ClassDecl);
		if (constructorCount == 0) {
			//podrazumevani konstruktor
			String name = Utils.CONSPREFIX + 0;
			Struct type = MyTab.noType;
			Obj cons = methodDeclared(name, type);
			cons.setAdr(-1);
			MyTab.insertParentScope(cons);
			MyTab.closeScope();
			currentMethod = null;
		}
		
		MyTab.closeScope();
		currentClass = null;
		ClassDecl.obj = ClassDecl.getClassName().obj;
		
		int vtableSize = 0;
		for (Obj obj: ClassDecl.obj.getType().getMembers()) {
			if (obj.getKind() == Obj.Meth && obj.getLevel() == 1) { //virtuelna metoda
				vtableSize += obj.getName().length() + 2; //karakteri + -1 + adresa
			}
		}
		vtableSize += 1; //-2
		ClassDecl.obj.setAdr(allocateStatic(vtableSize));
		
	}

	/*
	 * CONST DECLARATION
	 */
	@Override
	public void visit(ConstDeclBool ConstDeclBool) {
		super.visit(ConstDeclBool);
		if (declLineType != MyTab.boolType) {
			report_error("Tip dodeljene konstante (bool) ne odgovara deklarisanom tipu.", ConstDeclBool);
		}
		String name = ConstDeclBool.getName();
		Obj obj = new Obj(Obj.Con, name, MyTab.boolType, ConstDeclBool.getVal(), 0); obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", ConstDeclBool);
		}
	}
	@Override
	public void visit(ConstDeclChar ConstDeclChar) {
		super.visit(ConstDeclChar);
		if (declLineType != MyTab.charType) {
			report_error("Tip dodeljene konstante (char) ne odgovara deklarisanom tipu.", ConstDeclChar);
		}
		String name = ConstDeclChar.getName();
		Obj obj = new Obj(Obj.Con, name, MyTab.charType, ConstDeclChar.getVal(), 0); obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", ConstDeclChar);
		}
	}
	@Override
	public void visit(ConstDeclNum ConstDeclNum) {
		super.visit(ConstDeclNum);
		if (declLineType != MyTab.intType) {
			report_error("Tip dodeljene konstante (int) ne odgovara deklarisanom tipu.", ConstDeclNum);
		}
		String name = ConstDeclNum.getName();
		Obj obj = new Obj(Obj.Con, name, MyTab.intType, ConstDeclNum.getVal(), 0); obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " je vec deklarisano.", ConstDeclNum);
		}
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
		declLineType = ConstDeclLineStart.getType().struct;
	}
	@Override
	public void visit(ConstDeclLine ConstDeclLine) {
		super.visit(ConstDeclLine);
		declLineType = null;
	}

	/*
	 * PROGRAM DECLARATION FILLERS
	 */
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
		super.visit(DeclListItem);
	}

	/*
	 * NAMESPACE
	 */
	@Override
	public void visit(NamespaceName NamespaceName) {
		super.visit(NamespaceName);
		String name = NamespaceName.getName();
		Struct struct = new Struct(Struct.None);
		Obj obj = new Obj(Utils.Namespace, name, struct, Obj.NO_VALUE, 0); obj.setFpPos(Obj.NO_VALUE);
		if (!MyTab.insert(obj)) {
			report_error("Ime " + name + " vec postoji.", NamespaceName);
		}
		MyTab.openScopeAndChain(struct);
	}
	@Override
	public void visit(Namespace Namespace) {
		super.visit(Namespace);
		Namespace.obj = Namespace.getNamespaceName().obj;
		MyTab.closeScope();
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
	 * PROGRAM
	 */
	@Override
	public void visit(ProgramName ProgramName) {
		super.visit(ProgramName);
		String name = ProgramName.getName();
		Struct struct = new Struct(Struct.None);
		Obj program = new Obj(Obj.Prog, name, struct, Obj.NO_VALUE, 0); program.setFpPos(Obj.NO_VALUE);
		ProgramName.obj = program;
		
		if (!MyTab.insert(program)) {
			report_error("Ime " + name + " je vec deklarisano.", ProgramName);
		}
		MyTab.openScopeAndChain(struct);
	}
	@Override
	public void visit(Program Program) {
		super.visit(Program);
		Program.obj = Program.getProgramName().obj;
		MyTab.closeScope();
	}

	@Override
	public void visit() {
		super.visit();
	}
	
}
