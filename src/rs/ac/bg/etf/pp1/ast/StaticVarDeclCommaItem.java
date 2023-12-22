// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class StaticVarDeclCommaItem extends StaticVarDeclComma {

    private StaticVarDeclComma StaticVarDeclComma;
    private StaticVarDecl StaticVarDecl;

    public StaticVarDeclCommaItem (StaticVarDeclComma StaticVarDeclComma, StaticVarDecl StaticVarDecl) {
        this.StaticVarDeclComma=StaticVarDeclComma;
        if(StaticVarDeclComma!=null) StaticVarDeclComma.setParent(this);
        this.StaticVarDecl=StaticVarDecl;
        if(StaticVarDecl!=null) StaticVarDecl.setParent(this);
    }

    public StaticVarDeclComma getStaticVarDeclComma() {
        return StaticVarDeclComma;
    }

    public void setStaticVarDeclComma(StaticVarDeclComma StaticVarDeclComma) {
        this.StaticVarDeclComma=StaticVarDeclComma;
    }

    public StaticVarDecl getStaticVarDecl() {
        return StaticVarDecl;
    }

    public void setStaticVarDecl(StaticVarDecl StaticVarDecl) {
        this.StaticVarDecl=StaticVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticVarDeclComma!=null) StaticVarDeclComma.accept(visitor);
        if(StaticVarDecl!=null) StaticVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticVarDeclComma!=null) StaticVarDeclComma.traverseTopDown(visitor);
        if(StaticVarDecl!=null) StaticVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticVarDeclComma!=null) StaticVarDeclComma.traverseBottomUp(visitor);
        if(StaticVarDecl!=null) StaticVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticVarDeclCommaItem(\n");

        if(StaticVarDeclComma!=null)
            buffer.append(StaticVarDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticVarDecl!=null)
            buffer.append(StaticVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticVarDeclCommaItem]");
        return buffer.toString();
    }
}
