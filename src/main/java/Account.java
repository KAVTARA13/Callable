public class Account {
    String address;
    float amount;
    public Account(String address, float amount){
        this.address = address;
        this.amount = amount;
    }

    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address = address;
    }
    public float getAmount(){
        return this.amount;
    }
    public void setAmount(float amount){
        this.amount = amount;
    }

    public String toString(){
        return this.address+" : "+this.amount;
    }
}
