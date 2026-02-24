package main.java.entity;
//observer
import java.io.PrintWriter;
import java.util.*;

public class ResourceGroup {
    private List<User> members;
    private String ipAddress;
    private Server subject;
    public ResourceGroup(String ipAddress){
        members=new ArrayList<>();
        this.ipAddress=ipAddress;
    }
    public void observer(Server subject){
        this.subject =subject;
        subject.attach(this);
    }
    public String getIpAddress(){
        return this.ipAddress;
    }
    public void update(Alert alert, PrintWriter pw){
        pw.println("ADD EVENT: "+alert.getIpAddress()+": type = "+alert.getType()+" && severity = "+alert.getSeverity()+" && message = "+alert.getMessage());
    }
    public void addMember(User m){
        members.add(m);
    }
    public List<User> getMembers(){
        return this.members;
    }
    public void removeMember(User membru){
        this.members.remove(membru);
    }

}
