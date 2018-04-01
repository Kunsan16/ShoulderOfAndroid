package com.example.administrator.glidetest.inter;

/**
 * Created by Administrator on 2018/3/19.
 */

public class BuyProxy implements Buy{

    Buy buy ;

   public BuyProxy(Buy buy ){
       this.buy=buy;
   }

    @Override
    public void buyHouse(long money) {

        buy.buyHouse(money);
    }
}
