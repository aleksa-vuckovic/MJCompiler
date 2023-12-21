// generated with ast extension for cup
// version 0.8
// 21/11/2023 0:33:38


package rs.ac.bg.etf.pp1.ast;

public class ConstDeclNum extends ConstDecl {

    private String name;
    private Integer val;

    public ConstDeclNum (String name, Integer val) {
        this.name=name;
        this.val=val;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name=name;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val=val;
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
        buffer.append("ConstDeclNum(\n");

        buffer.append(" "+tab+name);
        buffer.append("\n");

        buffer.append(" "+tab+val);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDeclNum]");
        return buffer.toString();
    }
}
