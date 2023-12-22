// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementCommaItem extends DesignatorStatementComma {

    private DesignatorStatementComma DesignatorStatementComma;
    private DesignatorStatement DesignatorStatement;

    public DesignatorStatementCommaItem (DesignatorStatementComma DesignatorStatementComma, DesignatorStatement DesignatorStatement) {
        this.DesignatorStatementComma=DesignatorStatementComma;
        if(DesignatorStatementComma!=null) DesignatorStatementComma.setParent(this);
        this.DesignatorStatement=DesignatorStatement;
        if(DesignatorStatement!=null) DesignatorStatement.setParent(this);
    }

    public DesignatorStatementComma getDesignatorStatementComma() {
        return DesignatorStatementComma;
    }

    public void setDesignatorStatementComma(DesignatorStatementComma DesignatorStatementComma) {
        this.DesignatorStatementComma=DesignatorStatementComma;
    }

    public DesignatorStatement getDesignatorStatement() {
        return DesignatorStatement;
    }

    public void setDesignatorStatement(DesignatorStatement DesignatorStatement) {
        this.DesignatorStatement=DesignatorStatement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorStatementComma!=null) DesignatorStatementComma.accept(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorStatementComma!=null) DesignatorStatementComma.traverseTopDown(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorStatementComma!=null) DesignatorStatementComma.traverseBottomUp(visitor);
        if(DesignatorStatement!=null) DesignatorStatement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementCommaItem(\n");

        if(DesignatorStatementComma!=null)
            buffer.append(DesignatorStatementComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DesignatorStatement!=null)
            buffer.append(DesignatorStatement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementCommaItem]");
        return buffer.toString();
    }
}
