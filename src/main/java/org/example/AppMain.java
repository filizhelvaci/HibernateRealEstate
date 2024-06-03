package org.example;

import org.example.dao.AgentDAO;
import org.example.dao.BuyerDAO;
import org.example.dao.PropertyDAO;
import org.example.dao.SellerDAO;
import org.example.model.Agent;
import org.example.model.Buyer;
import org.example.model.Property;
import org.example.model.Seller;

import java.math.BigDecimal;
import java.util.List;


public class AppMain {
    public static void main(String[] args) {

        AgentDAO agentDAO=new AgentDAO();
        PropertyDAO propertyDAO=new PropertyDAO();
        SellerDAO sellerDAO=new SellerDAO();
        BuyerDAO buyerDAO=new BuyerDAO();

        Agent agent=new Agent();
        agent.setAgentName("Doğu Emlak");
        agent.setEmail("doguemlak@gmail.com");
        agent.setPhone("3214568596");
        agent.setOfficeAddress("İzmir / Karşıyaka");
        agentDAO.saveOrUpdate(agent);

        Agent agent1=new Agent();
        agent1.setAgentName("Batı Emlak");
        agent1.setEmail("batıemlak@gmail.com");
        agent1.setPhone("2224568222");
        agent1.setOfficeAddress("Eskişehir / Tepebaşı");
        agentDAO.saveOrUpdate(agent1);


        Seller seller=new Seller();
        seller.setFirstName("Ayşe");
        seller.setLastName("Kiraz");
        seller.setEmail("aysekiraz@gmail.com");
        seller.setPhone("05462354123");
        sellerDAO.saveOrUpdate(seller);

        Seller seller1=new Seller();
        seller1.setFirstName("Meltem");
        seller1.setLastName("Kısakol");
        seller1.setEmail("mltmkskl@gmail.com");
        seller1.setPhone("05325896541");
        sellerDAO.saveOrUpdate(seller1);

        Buyer buyer1=new Buyer();
        buyer1.setFirstName("Selim");
        buyer1.setLastName("Sarp");
        buyer1.setEmail("selimsarp@gmail.com");
        buyer1.setPhone("05321478596");
        buyerDAO.saveOrUpdate(buyer1);

        Buyer buyer=new Buyer();
        buyer.setFirstName("Mehmet");
        buyer.setLastName("Kanar");
        buyer.setEmail("mhmtknr@gmail.com");
        buyer.setPhone("05423216541");
        buyerDAO.saveOrUpdate(buyer);

        Property property=new Property();
        property.setTitle("Dublex Ev");
        property.setType("Kiralık");
        property.setPropertyName("Daire");
        property.setDescription("Karşıyakada temiz 4+1 kralık dublex daire");
        property.setPrice(BigDecimal.valueOf(10_000_000.75));
        property.setM2(450.50f);
        property.setSeller(seller);
        property.getBuyerSet().add(buyer);
        property.setAgent(agent1);
        propertyDAO.saveOrUpdate(property);


        Property property1=new Property();
        property1.setTitle("Mustakil Ev");
        property1.setType("Kiralık");
        property1.setPropertyName("Mustakil ev");
        property1.setDescription("Eskişehirde temiz 2+1 kiralık mustakil bahçeli ev");
        property1.setPrice(BigDecimal.valueOf(32000000));
        property1.getBuyerSet().add(buyer1);
        property1.getBuyerSet().add(buyer);
        property1.setSeller(seller1);
        property1.setAgent(agent);
        propertyDAO.saveOrUpdate(property1);


        List<Seller> allSeller=sellerDAO.getSellerAllFind();
        for(Seller s:allSeller){
            System.out.println(s.getFirstName() +" "+s.getLastName()+"  tel: "+s.getPhone());
        }















    }
}