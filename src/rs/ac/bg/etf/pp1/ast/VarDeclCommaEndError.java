// generated with ast extension for cup
// version 0.8
// 23/11/2023 21:49:6


package rs.ac.bg.etf.pp1.ast;

public class VarDeclCommaEndError extends VarDeclComma {

    public VarDeclCommaEndError () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclCommaEndError(\n");

        buffer.append(tab);
        buffer.append(") [VarDeclCommaEndError]");
        return buffer.toString();
    }
}