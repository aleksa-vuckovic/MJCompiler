// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class DeclVar extends Decl {

    private VarDeclLine VarDeclLine;

    public DeclVar (VarDeclLine VarDeclLine) {
        this.VarDeclLine=VarDeclLine;
        if(VarDeclLine!=null) VarDeclLine.setParent(this);
    }

    public VarDeclLine getVarDeclLine() {
        return VarDeclLine;
    }

    public void setVarDeclLine(VarDeclLine VarDeclLine) {
        this.VarDeclLine=VarDeclLine;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclLine!=null) VarDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLine!=null) VarDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLine!=null) VarDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DeclVar(\n");

        if(VarDeclLine!=null)
            buffer.append(VarDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DeclVar]");
        return buffer.toString();
    }
}
