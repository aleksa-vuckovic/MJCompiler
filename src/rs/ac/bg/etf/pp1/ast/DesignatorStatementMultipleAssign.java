// generated with ast extension for cup
// version 0.8
// 23/11/2023 16:18:18


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementMultipleAssign extends DesignatorStatement {

    private DesignatorComma DesignatorComma;
    private Designator Designator;
    private Designator Designator1;

    public DesignatorStatementMultipleAssign (DesignatorComma DesignatorComma, Designator Designator, Designator Designator1) {
        this.DesignatorComma=DesignatorComma;
        if(DesignatorComma!=null) DesignatorComma.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
        this.Designator1=Designator1;
        if(Designator1!=null) Designator1.setParent(this);
    }

    public DesignatorComma getDesignatorComma() {
        return DesignatorComma;
    }

    public void setDesignatorComma(DesignatorComma DesignatorComma) {
        this.DesignatorComma=DesignatorComma;
    }

    public Designator getDesignator() {
        return Designator;
    }

    public void setDesignator(Designator Designator) {
        this.Designator=Designator;
    }

    public Designator getDesignator1() {
        return Designator1;
    }

    public void setDesignator1(Designator Designator1) {
        this.Designator1=Designator1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
        if(Designator1!=null) Designator1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorComma!=null) DesignatorComma.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
        if(Designator1!=null) Designator1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        if(Designator1!=null) Designator1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementMultipleAssign(\n");

        if(DesignatorComma!=null)
            buffer.append(DesignatorComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator!=null)
            buffer.append(Designator.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Designator1!=null)
            buffer.append(Designator1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementMultipleAssign]");
        return buffer.toString();
    }
}
