package main.java;
//observer si builder
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//design pattern builder de facut
public class Server {
    //mandatory
    private String ipAddress;
    private Location location;
    private User owner;
    //features
    private String hostname;
    private ServerStatus status;
    private Integer cpuCores;
    private Integer ramGb;
    private Integer storageGb;
    private Server(Builder builder){
        this.ipAddress=builder.ipAddress;
        this.location= builder.location;;
        this.owner=builder.owner;
        this.hostname= builder.hostname;;
        this.status=builder.status;
        this.cpuCores=builder.cpuCores;
        this.ramGb=builder.ramGb;
        this.storageGb=builder.storageGb;
    }

    public String getIpAddress() {
        return ipAddress;
    }

    public Location getLocation() {
        return location;
    }

    public User getOwner() {
        return owner;
    }

    public String getHostname() {
        return hostname;
    }

    public ServerStatus getStatus() {
        return status;
    }

    public Integer getCpuCores() {
        return cpuCores;
    }

    public Integer getRamGb() {
        return ramGb;
    }

    public Integer getStorageGb() {
        return storageGb;
    }

    public Alert getAlert() {
        return alert;
    }

    public static class Builder{
        //mandatory
        private String ipAddress;
        private Location location;
        private User owner;
        //features
        private String hostname;
        private ServerStatus status;
        private Integer cpuCores;
        private Integer ramGb;
        private Integer storageGb;
        public Builder(String ipAddress,Location location,User owner){
            this.ipAddress=ipAddress;
            this.location=location;
            this.owner=owner;
        }
        public Builder setHostname(String hostname) {
            this.hostname = hostname;
            return this;
        }

        public Builder setStatus(ServerStatus status) {
            this.status = status;
            return this;
        }

        public Builder setCpuCores(Integer cpuCores) {
            this.cpuCores = cpuCores;
            return this;
        }

        public Builder setRamGb(Integer ramGb) {
            this.ramGb = ramGb;
            return this;
        }

        public Builder setStorageGb(Integer storageGb) {
            this.storageGb = storageGb;
            return this;
        }
        public Server build(){
            return new Server(this);
        }
    }
    //observer
    //alert added to server
    private Alert alert;
    public void addAlert(Alert alert,PrintWriter pw){
        this.alert=alert;
        notifyGroups(pw);
    }
    //creating groups that are connected to the server
    private Set<ResourceGroup> groups=new HashSet<>();
    public void attach(ResourceGroup group){
        if(group.getIpAddress().equals(ipAddress))
            groups.add(group);
    }
    public void notifyGroups(PrintWriter pw){
        for(ResourceGroup rs:groups){
                rs.update(this.alert,pw);
        }
    }
}
