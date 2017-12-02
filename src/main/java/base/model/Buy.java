package base.model;

import javax.persistence.*;

@Entity
@Table(name = "Buys")
public class Buy {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "chat_id")
    private long chat_id;

    @Column(name = "product")
    private String name_product;

    @Column(name = "count")
    private String count_product;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getChat_id() {
        return chat_id;
    }

    public void setChat_id(long chat_id) {
        this.chat_id = chat_id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public String getCount_product() {
        return count_product;
    }

    public void setCount_product(String count_product) {
        this.count_product = count_product;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "id=" + id +
                ", chat_id=" + chat_id +
                ", name_product='" + name_product + '\'' +
                ", count_product='" + count_product + '\'' +
                '}';
    }
}
