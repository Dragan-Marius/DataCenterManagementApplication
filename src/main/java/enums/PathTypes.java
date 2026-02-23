package main.java.enums;

public enum PathTypes {
    GROUPS("groups"),
    LISTENER("listener"),
    SERVERS("servers");
    private final String value;
    PathTypes(String value){
        this.value=value;
    }
    public String getValue(){
        return value;
    }
}

