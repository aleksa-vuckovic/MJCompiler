// generated with ast extension for cup
// version 0.8
// 21/11/2023 0:33:38


package rs.ac.bg.etf.pp1.ast;

public class VarDeclLineListItem extends VarDeclLineList {

    private VarDeclLineList VarDeclLineList;
    private VarDeclLine VarDeclLine;

    public VarDeclLineListItem (VarDeclLineList VarDeclLineList, VarDeclLine VarDeclLine) {
        this.VarDeclLineList=VarDeclLineList;
        if(VarDeclLineList!=null) VarDeclLineList.setParent(this);
        this.VarDeclLine=VarDeclLine;
        if(VarDeclLine!=null) VarDeclLine.setParent(this);
    }

    public VarDeclLineList getVarDeclLineList() {
        return VarDeclLineList;
    }

    public void setVarDeclLineList(VarDeclLineList VarDeclLineList) {
        this.VarDeclLineList=VarDeclLineList;
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
        if(VarDeclLineList!=null) VarDeclLineList.accept(visitor);
        if(VarDeclLine!=null) VarDeclLine.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclLineList!=null) VarDeclLineList.traverseTopDown(visitor);
        if(VarDeclLine!=null) VarDeclLine.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclLineList!=null) VarDeclLineList.traverseBottomUp(visitor);
        if(VarDeclLine!=null) VarDeclLine.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclLineListItem(\n");

        if(VarDeclLineList!=null)
            buffer.append(VarDeclLineList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDeclLine!=null)
            buffer.append(VarDeclLine.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclLineListItem]");
        return buffer.toString();
    }
}
