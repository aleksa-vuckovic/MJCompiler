// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class Method implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private MethodDeclaration MethodDeclaration;
    private VarDeclLineList VarDeclLineList;
    private StatementList StatementList;

    public Method (MethodDeclaration MethodDeclaration, VarDeclLineList VarDeclLineList, StatementList StatementList) {
        this.MethodDeclaration=MethodDeclaration;
        if(MethodDeclaration!=null) MethodDeclaration.setParent(this);
        this.VarDeclLineList=VarDeclLineList;
        if(VarDeclLineList!=null) VarDeclLineList.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodDeclaration getMethodDeclaration() {
        return MethodDeclaration;
    }

    public void setMethodDeclaration(MethodDeclaration MethodDeclaration) {
        this.MethodDeclaration=MethodDeclaration;
    }

    public VarDeclLineList getVarDeclLineList() {
        return VarDeclLineList;
    }

    public void setVarDeclLineList(VarDeclLineList VarDeclLineList) {
        this.VarDeclLineList=VarDeclLineList;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDeclaration!=null) MethodDeclaration.accept(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDeclaration!=null) MethodDeclaration.traverseTopDown(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDeclaration!=null) MethodDeclaration.traverseBottomUp(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Method(\n");

        if(MethodDeclaration!=null)
            buffer.append(MethodDeclaration.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclLineList!=null)
            buffer.append(VarDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Method]");
        return buffer.toString();
    }
}
