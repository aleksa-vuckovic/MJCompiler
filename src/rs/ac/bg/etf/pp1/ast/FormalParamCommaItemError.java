// generated with ast extension for cup
// version 0.8
// 23/11/2023 21:49:6


package rs.ac.bg.etf.pp1.ast;

public class FormalParamCommaItemError extends FormalParamComma {

    private FormalParamComma FormalParamComma;

    public FormalParamCommaItemError (FormalParamComma FormalParamComma) {
        this.FormalParamComma=FormalParamComma;
        if(FormalParamComma!=null) FormalParamComma.setParent(this);
    }

    public FormalParamComma getFormalParamComma() {
        return FormalParamComma;
    }

    public void setFormalParamComma(FormalParamComma FormalParamComma) {
        this.FormalParamComma=FormalParamComma;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamComma!=null) FormalParamComma.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamCommaItemError(\n");

        if(FormalParamComma!=null)
            buffer.append(FormalParamComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamCommaItemError]");
        return buffer.toString();
    }
}
