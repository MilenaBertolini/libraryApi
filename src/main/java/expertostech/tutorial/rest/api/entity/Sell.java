package expertostech.tutorial.rest.api.entity;

public class Sell {
    private Client client;
    private Buys buy;
    
    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }
    public Buys getBuy() {
        return buy;
    }
    public void setBuy(Buys buy) {
        this.buy = buy;
    }
}
