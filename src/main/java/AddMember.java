package main.java;

import java.io.PrintWriter;

public class AddMember extends Command{
    String [] arg;
    public AddMember(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }
    public void execute(PrintWriter pw){
        Database database=Database.getInstance();
        try {
            //IP existence check
            String ipAddress = arg[1];
            if (ipAddress.isEmpty())
                throw new MissingIpAddressException();
            //check the existence of name, role, email, department
            if(arg[2].isEmpty() || arg[3].isEmpty()|| arg[4].isEmpty() || arg[5].isEmpty())
                return;
            String userName=arg[2];
            String userRole=arg[3];
            String userEmail=arg[4];
            String department=arg[5];
            User user=null;
            //checking the user type and creating it
            if(userRole.equals("Admin")){
                if(arg[6].isEmpty())
                    return ;
                int clearanceLevel=Integer.parseInt(arg[6]);
                user=new Admin(userName,userRole,userEmail,department,clearanceLevel);
            }
            else if(userRole.equals("Operator")){
                user=new Operator(userName,userRole,userEmail,department);
            }
            //searching for the group with the requested IP
            ResourceGroup group=null;
            for(ResourceGroup aux: database.getResourceGroups()){
                if(aux.getIpAddress().equals(ipAddress)){
                    group=aux;
                    break;
                }
            }
            if(group!=null){
                group.addMember(user);
            }
            pw.println(arg[0]+": "+ipAddress+": name = "+userName+" && role = "+userRole);
        }catch (MissingIpAddressException e){
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }
}
