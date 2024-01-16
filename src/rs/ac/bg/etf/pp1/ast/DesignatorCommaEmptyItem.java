// generated with ast extension for cup
// version 0.8
// 16/0/2024 3:41:51


package rs.ac.bg.etf.pp1.ast;

public class DesignatorCommaEmptyItem extends DesignatorComma {

    private DesignatorComma DesignatorComma;

    public DesignatorCommaEmptyItem (DesignatorComma DesignatorComma) {
        this.DesignatorComma=DesignatorComma;
        if(DesignatorComma!=null) DesignatorComma.setParent(this);
    }

    public DesignatorComma getDesignatorComma() {
        return DesignatorComma;
    }

    public void setDesignatorComma(DesignatorComma DesignatorComma) {
        this.DesignatorComma=DesignatorComma;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorComma!=null) DesignatorComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorComma!=null) DesignatorComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorCommaEmptyItem(\n");

        if(DesignatorComma!=null)
            buffer.append(DesignatorComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorCommaEmptyItem]");
        return buffer.toString();
    }
}
