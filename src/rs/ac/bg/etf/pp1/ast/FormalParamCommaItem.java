// generated with ast extension for cup
// version 0.8
// 21/11/2023 0:33:38


package rs.ac.bg.etf.pp1.ast;

public class FormalParamCommaItem extends FormalParamComma {

    private FormalParamComma FormalParamComma;
    private FormalParam FormalParam;

    public FormalParamCommaItem (FormalParamComma FormalParamComma, FormalParam FormalParam) {
        this.FormalParamComma=FormalParamComma;
        if(FormalParamComma!=null) FormalParamComma.setParent(this);
        this.FormalParam=FormalParam;
        if(FormalParam!=null) FormalParam.setParent(this);
    }

    public FormalParamComma getFormalParamComma() {
        return FormalParamComma;
    }

    public void setFormalParamComma(FormalParamComma FormalParamComma) {
        this.FormalParamComma=FormalParamComma;
    }

    public FormalParam getFormalParam() {
        return FormalParam;
    }

    public void setFormalParam(FormalParam FormalParam) {
        this.FormalParam=FormalParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.accept(visitor);
        if(FormalParam!=null) FormalParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormalParamComma!=null) FormalParamComma.traverseTopDown(visitor);
        if(FormalParam!=null) FormalParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormalParamComma!=null) FormalParamComma.traverseBottomUp(visitor);
        if(FormalParam!=null) FormalParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormalParamCommaItem(\n");

        if(FormalParamComma!=null)
            buffer.append(FormalParamComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormalParam!=null)
            buffer.append(FormalParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormalParamCommaItem]");
        return buffer.toString();
    }
}
