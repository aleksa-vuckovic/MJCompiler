// generated with ast extension for cup
// version 0.8
// 16/0/2024 3:41:51


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclLine implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private ConstDeclLineStart ConstDeclLineStart;
    private ConstDeclComma ConstDeclComma;

    public ConstDeclLine (ConstDeclLineStart ConstDeclLineStart, ConstDeclComma ConstDeclComma) {
        this.ConstDeclLineStart=ConstDeclLineStart;
        if(ConstDeclLineStart!=null) ConstDeclLineStart.setParent(this);
        this.ConstDeclComma=ConstDeclComma;
        if(ConstDeclComma!=null) ConstDeclComma.setParent(this);
    }

    public ConstDeclLineStart getConstDeclLineStart() {
        return ConstDeclLineStart;
    }

    public void setConstDeclLineStart(ConstDeclLineStart ConstDeclLineStart) {
        this.ConstDeclLineStart=ConstDeclLineStart;
    }

    public ConstDeclComma getConstDeclComma() {
        return ConstDeclComma;
    }

    public void setConstDeclComma(ConstDeclComma ConstDeclComma) {
        this.ConstDeclComma=ConstDeclComma;
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
        if(ConstDeclLineStart!=null) ConstDeclLineStart.accept(visitor);
        if(ConstDeclComma!=null) ConstDeclComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDeclLineStart!=null) ConstDeclLineStart.traverseTopDown(visitor);
        if(ConstDeclComma!=null) ConstDeclComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDeclLineStart!=null) ConstDeclLineStart.traverseBottomUp(visitor);
        if(ConstDeclComma!=null) ConstDeclComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDeclLine(\n");

        if(ConstDeclLineStart!=null)
            buffer.append(ConstDeclLineStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDeclComma!=null)
            buffer.append(ConstDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclLine]");
        return buffer.toString();
    }
}
