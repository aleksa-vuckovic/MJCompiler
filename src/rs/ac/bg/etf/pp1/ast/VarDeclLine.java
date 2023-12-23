// generated with ast extension for cup
// version 0.8
// 23/11/2023 16:18:18


package rs.ac.bg.etf.pp1.ast;

public class VarDeclLine implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private VarDeclLineStart VarDeclLineStart;
    private VarDeclComma VarDeclComma;

    public VarDeclLine (VarDeclLineStart VarDeclLineStart, VarDeclComma VarDeclComma) {
        this.VarDeclLineStart=VarDeclLineStart;
        if(VarDeclLineStart!=null) VarDeclLineStart.setParent(this);
        this.VarDeclComma=VarDeclComma;
        if(VarDeclComma!=null) VarDeclComma.setParent(this);
    }

    public VarDeclLineStart getVarDeclLineStart() {
        return VarDeclLineStart;
    }

    public void setVarDeclLineStart(VarDeclLineStart VarDeclLineStart) {
        this.VarDeclLineStart=VarDeclLineStart;
    }

    public VarDeclComma getVarDeclComma() {
        return VarDeclComma;
    }

    public void setVarDeclComma(VarDeclComma VarDeclComma) {
        this.VarDeclComma=VarDeclComma;
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
        if(VarDeclLineStart!=null) VarDeclLineStart.accept(visitor);
        if(VarDeclComma!=null) VarDeclComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLineStart!=null) VarDeclLineStart.traverseTopDown(visitor);
        if(VarDeclComma!=null) VarDeclComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLineStart!=null) VarDeclLineStart.traverseBottomUp(visitor);
        if(VarDeclComma!=null) VarDeclComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclLine(\n");

        if(VarDeclLineStart!=null)
            buffer.append(VarDeclLineStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclComma!=null)
            buffer.append(VarDeclComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclLine]");
        return buffer.toString();
    }
}
