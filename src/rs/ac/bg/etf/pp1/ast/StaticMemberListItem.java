// generated with ast extension for cup
// version 0.8
// 22/11/2023 2:5:12


package rs.ac.bg.etf.pp1.ast;

public class StaticMemberListItem extends StaticMemberList {

    private StaticMemberList StaticMemberList;
    private StaticMember StaticMember;

    public StaticMemberListItem (StaticMemberList StaticMemberList, StaticMember StaticMember) {
        this.StaticMemberList=StaticMemberList;
        if(StaticMemberList!=null) StaticMemberList.setParent(this);
        this.StaticMember=StaticMember;
        if(StaticMember!=null) StaticMember.setParent(this);
    }

    public StaticMemberList getStaticMemberList() {
        return StaticMemberList;
    }

    public void setStaticMemberList(StaticMemberList StaticMemberList) {
        this.StaticMemberList=StaticMemberList;
    }

    public StaticMember getStaticMember() {
        return StaticMember;
    }

    public void setStaticMember(StaticMember StaticMember) {
        this.StaticMember=StaticMember;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StaticMemberList!=null) StaticMemberList.accept(visitor);
        if(StaticMember!=null) StaticMember.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StaticMemberList!=null) StaticMemberList.traverseTopDown(visitor);
        if(StaticMember!=null) StaticMember.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StaticMemberList!=null) StaticMemberList.traverseBottomUp(visitor);
        if(StaticMember!=null) StaticMember.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StaticMemberListItem(\n");

        if(StaticMemberList!=null)
            buffer.append(StaticMemberList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StaticMember!=null)
            buffer.append(StaticMember.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StaticMemberListItem]");
        return buffer.toString();
    }
}
