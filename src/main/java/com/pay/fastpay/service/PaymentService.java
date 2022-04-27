package com.pay.fastpay.service;

import com.pay.fastpay.entity.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

@Service
public class PaymentService {
    LocalDateTime localDateTime=LocalDateTime.now().plusDays(20);
   private    Instant instant=localDateTime.atZone(ZoneId.systemDefault()).toInstant();
private  Set<Card>cards=new HashSet<>(Arrays.asList(
        new Card(1,"visa","123456",50000, Date.from(instant),true),
        new Card(1,"mastercard","2453235",45000,Date.from(instant),true),
        new Card(3,"humo","4823345403",150000,Date.from(instant),true),
        new Card(4,"uzcard","3204885023",200000,Date.from(instant),true)
));
    private  Set<Card>cards2=new HashSet<>(Arrays.asList(
            new Card(1,"visa","123456",50000, Date.from(instant),true),
            new Card(1,"mastercard","2453235",45000,Date.from(instant),true),
            new Card(3,"humo","4823345403",150000,Date.from(instant),true),
            new Card(4,"uzcard","3204885023",200000,Date.from(instant),true)
    ));
    private  Set<Card>cards3=new HashSet<>(Arrays.asList(
            new Card(1,"visa","123456",50000, Date.from(instant),true),
            new Card(1,"mastercard","2453235",45000,Date.from(instant),true),
            new Card(3,"humo","4823345403",150000,Date.from(instant),true),
            new Card(4,"uzcard","3204885023",200000,Date.from(instant),true)
    ));
private Set<User> users=new HashSet<>(Arrays.asList(
        new User("shukhratjon","1201",new HashSet<>(cards),new ArrayList<>(),new ArrayList<>()),
        new User("Tim","1234",new HashSet<>(cards2),new ArrayList<>(),new ArrayList<>()),
        new User("Dominik","5678",new HashSet<>(cards3),new ArrayList<>(),new ArrayList<>())
));

    public Response pay(PaymentDto paymentDto){
        int counter=0;
        if (check(paymentDto.getUser(),paymentDto.getFromCard()) &&cards.contains(paymentDto.getToCard())) {
        if (paymentDto.getFromCard().isActive() && paymentDto.getToCard().isActive()) {
            if (paymentDto.getFromCard().getBalance()>paymentDto.getAmount()+paymentDto.getCommissionAmount()) {
                paymentDto.getFromCard().setBalance(paymentDto.getFromCard().getBalance()-paymentDto.getAmount()-paymentDto.getCommissionAmount());
                paymentDto.getToCard().setBalance(paymentDto.getToCard().getBalance()+ paymentDto.getAmount());
                counter++;
                paymentDto.getUser().setOutcomes(new ArrayList<>(Arrays.asList(
                        new Outcome(counter,paymentDto.getFromCard().getNumber(),paymentDto.getToCard().getNumber(),
                                paymentDto.getAmount(),new Date(),paymentDto.getCommissionAmount())
                )) );
                return new Response("Transaction successfully done !!!  ",paymentDto.getFromCard().getNumber(),paymentDto.getToCard().getNumber(),paymentDto.getAmount(),true);
            }else{
                return new Response("Not enough money in your balance",false);
            }
        }else{
            return  new Response("Your card is not active currently",false);
        }
      }else{
            return new Response("This card did not found in your card list",false);
        }
    }

    public boolean check(User user,Card card){
        return user.getCards().contains(card);
    }

    public Response paid(PaymentDto paymentDto){
        int counter=0;
        if (cards.contains(paymentDto.getFromCard())&& check(paymentDto.getUser(),paymentDto.getFromCard())){
            if (paymentDto.getFromCard().isActive()&&paymentDto.getToCard().isActive()) {
            if (paymentDto.getFromCard().getBalance()>paymentDto.getAmount()+paymentDto.getCommissionAmount()) {
                paymentDto.getToCard().setBalance(paymentDto.getAmount());
                paymentDto.getFromCard().setBalance(paymentDto.getFromCard().getBalance()-paymentDto.getAmount()-paymentDto.getCommissionAmount());
                counter++;
              paymentDto.getUser().setIncomes(new ArrayList<>(Arrays.asList(
                      new Income(counter,paymentDto.getFromCard().getNumber(),
                              paymentDto.getToCard().getNumber(),paymentDto.getAmount(),new Date())
              )));
             }else{
                return new Response("Not enough money in this card!!!",false);
            }
            }else{
                return new Response("Card is not active",false);
            }

        }
        return new Response("Cards did not found !!!",false);
    }

    public List<Income> getIncome(ReportDto reportDto){
        for (User user : users) {
            if (user.getUsername().equals(reportDto.getUsername())) {
                if (user.getPassword().equals(reportDto.getPassword())) {
                   return user.getIncomes();
                }
            }
        }
        return null;
    }

    public List<Outcome> getOutcome(ReportDto reportDto){
        for (User user : users) {
            if (user.getUsername().equals(reportDto.getUsername())) {
                if (user.getPassword().equals(reportDto.getPassword())) {
                    return user.getOutcomes();
                }
            }
        }
        return null;
    }
}
