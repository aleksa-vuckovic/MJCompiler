// generated with ast extension for cup
// version 0.8
// 23/11/2023 16:18:18


package rs.ac.bg.etf.pp1.ast;

public class StatementFor extends Statement {

    private ForEntry ForEntry;
    private ForCondition ForCondition;
    private ForExit ForExit;
    private Statement Statement;

    public StatementFor (ForEntry ForEntry, ForCondition ForCondition, ForExit ForExit, Statement Statement) {
        this.ForEntry=ForEntry;
        if(ForEntry!=null) ForEntry.setParent(this);
        this.ForCondition=ForCondition;
        if(ForCondition!=null) ForCondition.setParent(this);
        this.ForExit=ForExit;
        if(ForExit!=null) ForExit.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public ForEntry getForEntry() {
        return ForEntry;
    }

    public void setForEntry(ForEntry ForEntry) {
        this.ForEntry=ForEntry;
    }

    public ForCondition getForCondition() {
        return ForCondition;
    }

    public void setForCondition(ForCondition ForCondition) {
        this.ForCondition=ForCondition;
    }

    public ForExit getForExit() {
        return ForExit;
    }

    public void setForExit(ForExit ForExit) {
        this.ForExit=ForExit;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ForEntry!=null) ForEntry.accept(visitor);
        if(ForCondition!=null) ForCondition.accept(visitor);
        if(ForExit!=null) ForExit.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ForEntry!=null) ForEntry.traverseTopDown(visitor);
        if(ForCondition!=null) ForCondition.traverseTopDown(visitor);
        if(ForExit!=null) ForExit.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ForEntry!=null) ForEntry.traverseBottomUp(visitor);
        if(ForCondition!=null) ForCondition.traverseBottomUp(visitor);
        if(ForExit!=null) ForExit.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementFor(\n");

        if(ForEntry!=null)
            buffer.append(ForEntry.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForCondition!=null)
            buffer.append(ForCondition.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ForExit!=null)
            buffer.append(ForExit.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementFor]");
        return buffer.toString();
    }
}
