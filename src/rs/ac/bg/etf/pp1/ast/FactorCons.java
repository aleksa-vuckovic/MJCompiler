// generated with ast extension for cup
// version 0.8
// 16/0/2024 3:41:51


package rs.ac.bg.etf.pp1.ast;

public class FactorCons extends Factor {

    private ConstructorInvocation ConstructorInvocation;
    private ActPars ActPars;

    public FactorCons (ConstructorInvocation ConstructorInvocation, ActPars ActPars) {
        this.ConstructorInvocation=ConstructorInvocation;
        if(ConstructorInvocation!=null) ConstructorInvocation.setParent(this);
        this.ActPars=ActPars;
        if(ActPars!=null) ActPars.setParent(this);
    }

    public ConstructorInvocation getConstructorInvocation() {
        return ConstructorInvocation;
    }

    public void setConstructorInvocation(ConstructorInvocation ConstructorInvocation) {
        this.ConstructorInvocation=ConstructorInvocation;
    }

    public ActPars getActPars() {
        return ActPars;
    }

    public void setActPars(ActPars ActPars) {
        this.ActPars=ActPars;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstructorInvocation!=null) ConstructorInvocation.accept(visitor);
        if(ActPars!=null) ActPars.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstructorInvocation!=null) ConstructorInvocation.traverseTopDown(visitor);
        if(ActPars!=null) ActPars.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstructorInvocation!=null) ConstructorInvocation.traverseBottomUp(visitor);
        if(ActPars!=null) ActPars.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FactorCons(\n");

        if(ConstructorInvocation!=null)
            buffer.append(ConstructorInvocation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ActPars!=null)
            buffer.append(ActPars.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FactorCons]");
        return buffer.toString();
    }
}
