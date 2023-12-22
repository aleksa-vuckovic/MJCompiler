// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class ClassDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Obj obj = null;

    private ClassName ClassName;
    private StaticMemberList StaticMemberList;
    private VarDeclLineList VarDeclLineList;
    private Methods Methods;

    public ClassDecl (ClassName ClassName, StaticMemberList StaticMemberList, VarDeclLineList VarDeclLineList, Methods Methods) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.StaticMemberList=StaticMemberList;
        if(StaticMemberList!=null) StaticMemberList.setParent(this);
        this.VarDeclLineList=VarDeclLineList;
        if(VarDeclLineList!=null) VarDeclLineList.setParent(this);
        this.Methods=Methods;
        if(Methods!=null) Methods.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public StaticMemberList getStaticMemberList() {
        return StaticMemberList;
    }

    public void setStaticMemberList(StaticMemberList StaticMemberList) {
        this.StaticMemberList=StaticMemberList;
    }

    public VarDeclLineList getVarDeclLineList() {
        return VarDeclLineList;
    }

    public void setVarDeclLineList(VarDeclLineList VarDeclLineList) {
        this.VarDeclLineList=VarDeclLineList;
    }

    public Methods getMethods() {
        return Methods;
    }

    public void setMethods(Methods Methods) {
        this.Methods=Methods;
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
        if(ClassName!=null) ClassName.accept(visitor);
        if(StaticMemberList!=null) StaticMemberList.accept(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.accept(visitor);
        if(Methods!=null) Methods.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(StaticMemberList!=null) StaticMemberList.traverseTopDown(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseTopDown(visitor);
        if(Methods!=null) Methods.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(StaticMemberList!=null) StaticMemberList.traverseBottomUp(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseBottomUp(visitor);
        if(Methods!=null) Methods.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDecl(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticMemberList!=null)
            buffer.append(StaticMemberList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclLineList!=null)
            buffer.append(VarDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Methods!=null)
            buffer.append(Methods.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDecl]");
        return buffer.toString();
    }
}
