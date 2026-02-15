package main.java;
//factory
public class CommandFactory {
    public Command create(String command,String []arg,int nrLine){
        switch (command){
            case "ADD SERVER":
                return new AddServer(arg,nrLine);
            case "ADD GROUP":
                return new AddGroup(arg,nrLine);
            case "ADD MEMBER":
                return new AddMember(arg,nrLine);
            case "FIND MEMBER":
                return new FindMember(arg,nrLine);
            case "REMOVE MEMBER":
                return new RemoveMember(arg,nrLine);
            case "FIND GROUP":
                return new FindGroup(arg,nrLine);
            case "REMOVE GROUP":
                return new RemoveGroup(arg,nrLine);
            case "ADD EVENT":
                return new AddEvent(arg,nrLine);
            default:
                return null;
        }
    }
}
