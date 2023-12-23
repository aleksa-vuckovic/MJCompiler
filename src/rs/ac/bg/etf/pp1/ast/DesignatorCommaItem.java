// generated with ast extension for cup
// version 0.8
// 23/11/2023 16:18:18


package rs.ac.bg.etf.pp1.ast;

public class DesignatorCommaItem extends DesignatorComma {

    private DesignatorComma DesignatorComma;
    private Designator Designator;

    public DesignatorCommaItem (DesignatorComma DesignatorComma, Designator Designator) {
        this.DesignatorComma=DesignatorComma;
        if(DesignatorComma!=null) DesignatorComma.setParent(this);
        this.Designator=Designator;
        if(Designator!=null) Designator.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.accept(visitor);
        if(Designator!=null) Designator.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorComma!=null) DesignatorComma.traverseTopDown(visitor);
        if(Designator!=null) Designator.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.traverseBottomUp(visitor);
        if(Designator!=null) Designator.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorCommaItem(\n");

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

        buffer.append(tab);
        buffer.append(") [DesignatorCommaItem]");
        return buffer.toString();
    }
}
