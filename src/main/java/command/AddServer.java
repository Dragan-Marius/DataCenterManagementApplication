package main.java.command;

import java.io.PrintWriter;
import main.java.database.*;
import main.java.exception.*;
import main.java.entity.*;
import main.java.enums.*;
public class AddServer extends Command {
    String [] arg;
    public AddServer(String [] arg,int nrLine){
        super(nrLine);
        this.arg=arg;
    }
    public void execute(PrintWriter pw){
        Database database=Database.getInstance();
        try{
            //IP existence check
            String ipAddress=this.arg[2];
            if(ipAddress.length()==0){
                throw new MissingIpAddressException();
            }
            //verify the existence of the role and name
            String userName=arg[9];
            String userRole=arg[10];
            if(userRole.length()==0 || userName.length()==0){
                throw new UserException();
            }
            //country existence verification
            String country=this.arg[4];
            if(country.length()==0){
                throw new LocationException();
            }
            User user;
            String userEmail=arg[11];
            //role type verification
            if(!arg[11].isEmpty() && !arg[12].isEmpty() &&!arg[13].isEmpty() && userRole.equals("Admin")){
                int clearanceLevel=Integer.parseInt(arg[13]);
                String userDepartament=arg[12];
                user=new Admin(userName,userRole,userEmail,userDepartament,clearanceLevel);
            }
            else if(!arg[11].isEmpty() && !arg[12].isEmpty() &&userRole.equals("Operator")){
                String userDepartament=arg[12];
                user=new Operator(userName,userRole,userEmail,userDepartament);
            } else {
                user= new User(userName,userRole,userEmail);
            }
            //creating the location and with optional parameters if present
            Location.Builder temp=new Location.Builder(country);
            if(arg[5].length()!=0){
                temp=temp.setCity(arg[5]);
            }
            if(arg[6].length()!=0){
                temp=temp.setAddress(arg[6]);
            }
            if(arg[7].length()!=0){
                Double latitude=Double.parseDouble(arg[7]);
                temp=temp.setLatitude(latitude);
            }
            if(arg[8].length()!=0){
                Double longitude=Double.parseDouble(arg[8]);
                temp=temp.setLongitude(longitude);
            }
            Location location=temp.build();
            Server.Builder aux=new Server.Builder(ipAddress,location,user);
            if(arg[14].length()!=0){
                Integer cpuCores=Integer.parseInt(arg[14]);
                aux=aux.setCpuCores(cpuCores);
            }
            if(arg[15].length()!=0){
                Integer ramGb=Integer.parseInt(arg[15]);
                aux=aux.setRamGb(ramGb);
            }
            if(arg[16].length()!=0){
                Integer storageGb=Integer.parseInt(arg[16]);
                aux=aux.setStorageGb(storageGb);
            }
            if(arg[3].length()!=0){
                ServerStatus status=ServerStatus.valueOf(arg[3]);
                aux=aux.setStatus(status);
            }
            if(arg[1].length()!=0){
                aux=aux.setHostname(arg[1]);
            }
            //adding the server to the database and displaying the success message
            Server server=aux.build();
            database.addServer(server);
            pw.println("ADD SERVER: "+ipAddress+": "+server.getStatus());
        } catch (MissingIpAddressException | UserException | LocationException e){
            //afisarea mesajului pentru exceptii
            pw.println(arg[0]+": "+e.getMessage()+" ## line no: "+nrLine);
        }
    }

}
