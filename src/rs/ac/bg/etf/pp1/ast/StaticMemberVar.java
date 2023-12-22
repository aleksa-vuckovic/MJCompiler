// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class StaticMemberVar extends StaticMember {

    private StaticVarDeclLine StaticVarDeclLine;

    public StaticMemberVar (StaticVarDeclLine StaticVarDeclLine) {
        this.StaticVarDeclLine=StaticVarDeclLine;
        if(StaticVarDeclLine!=null) StaticVarDeclLine.setParent(this);
    }

    public StaticVarDeclLine getStaticVarDeclLine() {
        return StaticVarDeclLine;
    }

    public void setStaticVarDeclLine(StaticVarDeclLine StaticVarDeclLine) {
        this.StaticVarDeclLine=StaticVarDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticVarDeclLine!=null) StaticVarDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticVarDeclLine!=null) StaticVarDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticVarDeclLine!=null) StaticVarDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticMemberVar(\n");

        if(StaticVarDeclLine!=null)
            buffer.append(StaticVarDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticMemberVar]");
        return buffer.toString();
    }
}
