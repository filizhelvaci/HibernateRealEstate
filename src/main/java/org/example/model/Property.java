package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="PROPERTIES")
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @Column(name="PROPERTY_NAME",length = 80)
    private String propertyName;

    @Column(name="TITLE",length = 150)
    private String title;

    @Column(name="DESCRITION",length = 200)
    private String description;

    @Column(name="TYPE",length = 50)
    private String type;

    private BigDecimal price;
    //private int bedroom;
    //private int bathroom;
    private float m2;
    private String location;

    //     M         1
    // Property    Agent
    @ManyToOne
    @JoinColumn(name="agent_id")
    private Agent agent;

    //    M         1
    //  Property   Seller

    @ManyToOne
    @JoinColumn(name="seller_id")
    private Seller seller;


    //    M         M
    // Property    Buyer
    @ManyToMany
    @JoinTable(name="Property_Buyer",joinColumns = @JoinColumn(name="Property_id"),
            inverseJoinColumns = @JoinColumn(name="Buyer_id"))
    private Set<Buyer> buyerSet=new HashSet<>();


}
