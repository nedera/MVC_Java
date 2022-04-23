/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

public class Order {
    private String nameCus;
    private ArrayList<Fruit> cart = new ArrayList<>();

    public Order() {
    }
    
    public Order(String nameCus, ArrayList<Fruit> cart) {
        this.nameCus = nameCus;
        this.cart = cart;
    }

    public void addFruit(Fruit f){
//        if(this.cart.isEmpty()){
//            this.cart.add(f); return;
//        }
        for (Fruit fruit : cart) {
            if(fruit.getId() == f.getId() && fruit.getName().equals(f.getName())){
                fruit.setQuanity(fruit.getQuanity() + f.getQuanity()); return;
            }
                
        }
        this.cart.add(f);
    }
    

    
    public String getNameCus() {
        return nameCus;
    }

    public void setNameCus(String nameCus) {
        this.nameCus = nameCus;
    }

    public ArrayList<Fruit> getCart() {
        return cart;
    }

    public void setCart(ArrayList<Fruit> cart) {
        this.cart = cart;
    }
    
    
}
