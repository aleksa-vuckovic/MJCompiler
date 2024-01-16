// generated with ast extension for cup
// version 0.8
// 16/0/2024 3:41:51


package rs.ac.bg.etf.pp1.ast;

public class DeclConst extends Decl {

    private ConstDeclLine ConstDeclLine;

    public DeclConst (ConstDeclLine ConstDeclLine) {
        this.ConstDeclLine=ConstDeclLine;
        if(ConstDeclLine!=null) ConstDeclLine.setParent(this);
    }

    public ConstDeclLine getConstDeclLine() {
        return ConstDeclLine;
    }

    public void setConstDeclLine(ConstDeclLine ConstDeclLine) {
        this.ConstDeclLine=ConstDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclLine!=null) ConstDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclLine!=null) ConstDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclLine!=null) ConstDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclConst(\n");

        if(ConstDeclLine!=null)
            buffer.append(ConstDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclConst]");
        return buffer.toString();
    }
}
