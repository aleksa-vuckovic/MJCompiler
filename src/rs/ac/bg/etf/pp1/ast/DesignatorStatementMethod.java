// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class DesignatorStatementMethod extends DesignatorStatement {

    private MethodInvocation MethodInvocation;

    public DesignatorStatementMethod (MethodInvocation MethodInvocation) {
        this.MethodInvocation=MethodInvocation;
        if(MethodInvocation!=null) MethodInvocation.setParent(this);
    }

    public MethodInvocation getMethodInvocation() {
        return MethodInvocation;
    }

    public void setMethodInvocation(MethodInvocation MethodInvocation) {
        this.MethodInvocation=MethodInvocation;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodInvocation!=null) MethodInvocation.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodInvocation!=null) MethodInvocation.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodInvocation!=null) MethodInvocation.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorStatementMethod(\n");

        if(MethodInvocation!=null)
            buffer.append(MethodInvocation.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorStatementMethod]");
        return buffer.toString();
    }
}
