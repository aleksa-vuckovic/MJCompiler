// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class ForEntry implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.ac.bg.etf.pp1.my.MyInt myint = null;

    private DesignatorStatementComma DesignatorStatementComma;

    public ForEntry (DesignatorStatementComma DesignatorStatementComma) {
        this.DesignatorStatementComma=DesignatorStatementComma;
        if(DesignatorStatementComma!=null) DesignatorStatementComma.setParent(this);
    }

    public DesignatorStatementComma getDesignatorStatementComma() {
        return DesignatorStatementComma;
    }

    public void setDesignatorStatementComma(DesignatorStatementComma DesignatorStatementComma) {
        this.DesignatorStatementComma=DesignatorStatementComma;
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
        if(DesignatorStatementComma!=null) DesignatorStatementComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementComma!=null) DesignatorStatementComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementComma!=null) DesignatorStatementComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ForEntry(\n");

        if(DesignatorStatementComma!=null)
            buffer.append(DesignatorStatementComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ForEntry]");
        return buffer.toString();
    }
}
