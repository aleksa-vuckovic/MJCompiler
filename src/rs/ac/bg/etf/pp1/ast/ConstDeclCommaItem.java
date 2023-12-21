// generated with ast extension for cup
// version 0.8
// 21/11/2023 0:33:38


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclCommaItem extends ConstDeclComma {

    private ConstDeclComma ConstDeclComma;
    private ConstDecl ConstDecl;

    public ConstDeclCommaItem (ConstDeclComma ConstDeclComma, ConstDecl ConstDecl) {
        this.ConstDeclComma=ConstDeclComma;
        if(ConstDeclComma!=null) ConstDeclComma.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstDeclComma getConstDeclComma() {
        return ConstDeclComma;
    }

    public void setConstDeclComma(ConstDeclComma ConstDeclComma) {
        this.ConstDeclComma=ConstDeclComma;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDeclComma!=null) ConstDeclComma.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclComma!=null) ConstDeclComma.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclComma!=null) ConstDeclComma.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclCommaItem(\n");

        if(ConstDeclComma!=null)
            buffer.append(ConstDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclCommaItem]");
        return buffer.toString();
    }
}
