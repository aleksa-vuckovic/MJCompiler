// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class StaticVarDeclLine implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private StaticVarDeclLineStart StaticVarDeclLineStart;
    private StaticVarDeclComma StaticVarDeclComma;

    public StaticVarDeclLine (StaticVarDeclLineStart StaticVarDeclLineStart, StaticVarDeclComma StaticVarDeclComma) {
        this.StaticVarDeclLineStart=StaticVarDeclLineStart;
        if(StaticVarDeclLineStart!=null) StaticVarDeclLineStart.setParent(this);
        this.StaticVarDeclComma=StaticVarDeclComma;
        if(StaticVarDeclComma!=null) StaticVarDeclComma.setParent(this);
    }

    public StaticVarDeclLineStart getStaticVarDeclLineStart() {
        return StaticVarDeclLineStart;
    }

    public void setStaticVarDeclLineStart(StaticVarDeclLineStart StaticVarDeclLineStart) {
        this.StaticVarDeclLineStart=StaticVarDeclLineStart;
    }

    public StaticVarDeclComma getStaticVarDeclComma() {
        return StaticVarDeclComma;
    }

    public void setStaticVarDeclComma(StaticVarDeclComma StaticVarDeclComma) {
        this.StaticVarDeclComma=StaticVarDeclComma;
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
        if(StaticVarDeclLineStart!=null) StaticVarDeclLineStart.accept(visitor);
        if(StaticVarDeclComma!=null) StaticVarDeclComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticVarDeclLineStart!=null) StaticVarDeclLineStart.traverseTopDown(visitor);
        if(StaticVarDeclComma!=null) StaticVarDeclComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticVarDeclLineStart!=null) StaticVarDeclLineStart.traverseBottomUp(visitor);
        if(StaticVarDeclComma!=null) StaticVarDeclComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticVarDeclLine(\n");

        if(StaticVarDeclLineStart!=null)
            buffer.append(StaticVarDeclLineStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticVarDeclComma!=null)
            buffer.append(StaticVarDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticVarDeclLine]");
        return buffer.toString();
    }
}
