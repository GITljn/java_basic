package serialize;

import java.io.Serializable;

public class Actor implements Serializable {
    private static final long serialVersionUID = 1l;
    private String name;
    private String partner;

    public Actor(String name, String partner) {
        this.name = name;
        this.partner = partner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }

    public String getName() {
        return name;
    }

    public String getPartner() {
        return partner;
    } 
}
