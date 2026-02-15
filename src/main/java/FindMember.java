package main.java;

import java.io.PrintWriter;

public class FindMember extends Command {
    String [] arg;
    public FindMember(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }

    @Override
    public void execute(PrintWriter pw) {
        try{
            //IP existence check
            String ipAddress=arg[1];
            if(ipAddress.isEmpty())
                throw new MissingIpAddressException();
            String userName=arg[2];
            String userRole=arg[3];
            //verify the existence of the role and name
            if(userRole.length()==0 || userName.isEmpty())
                throw new UserException();
            //search for group with requested IP
            ResourceGroup group=null;
            Database database=Database.getInstance();
            for(ResourceGroup aux: database.getResourceGroups()){
                if(aux.getIpAddress().equals(ipAddress))
                    group=aux;
            }
            if(group==null){
                pw.println(arg[0]+": Group not found: ipAddress = "+ipAddress);
                return;
            }
            //searching for a user with the same name and role
            User user=null;
            for(User u:group.getMembers()){
                if(u.getName().equals(userName) && u.getRole().equals(userRole))
                    user=u;
            }
            if(user==null){
                pw.println(arg[0]+": Member not found: ipAddress = "+ipAddress+": name = "+userName+" && role = "+userRole);
            }
            else {
                pw.println(arg[0]+": "+ipAddress+": name = "+userName+" && role = "+userRole);
            }
        }catch (MissingIpAddressException | UserException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
