package main.java.entity;
//design pattern builder
public class Location {
    //mandatory
    private String country;
    //Features
    private String city;
    private String address;
    private Double latitude;
    private Double longitude;
    private Location(Builder builder){
        this.country=builder.country;
        this.city=builder.city;
        this.address=builder.address;
        this.latitude= builder.latitude;;
        this.longitude=builder.longitude;
    }
    public static class Builder{
        //mandatory
        private String country;
        //Features
        private String city;
        private String address;
        private Double latitude;
        private Double longitude;
        public Builder(String country){
            this.country=country;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setAddress(String address) {
            this.address = address;
            return this;
        }

        public Builder setLatitude(Double latitude) {
            this.latitude = latitude;
            return this;
        }

        public Builder setLongitude(Double longitude) {
            this.longitude = longitude;
            return this;
        }
        public Location build(){
            return new Location(this);
        }
    }


}
