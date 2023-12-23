// generated with ast extension for cup
// version 0.8
// 23/11/2023 16:18:18


package rs.ac.bg.etf.pp1.ast;

public class MethodListItem extends MethodList {

    private MethodList MethodList;
    private Method Method;

    public MethodListItem (MethodList MethodList, Method Method) {
        this.MethodList=MethodList;
        if(MethodList!=null) MethodList.setParent(this);
        this.Method=Method;
        if(Method!=null) Method.setParent(this);
    }

    public MethodList getMethodList() {
        return MethodList;
    }

    public void setMethodList(MethodList MethodList) {
        this.MethodList=MethodList;
    }

    public Method getMethod() {
        return Method;
    }

    public void setMethod(Method Method) {
        this.Method=Method;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodList!=null) MethodList.accept(visitor);
        if(Method!=null) Method.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodList!=null) MethodList.traverseTopDown(visitor);
        if(Method!=null) Method.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodList!=null) MethodList.traverseBottomUp(visitor);
        if(Method!=null) Method.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodListItem(\n");

        if(MethodList!=null)
            buffer.append(MethodList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Method!=null)
            buffer.append(Method.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodListItem]");
        return buffer.toString();
    }
}
