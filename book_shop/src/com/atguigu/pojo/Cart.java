package com.atguigu.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;


public class Cart {


    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public void addItem(CartItem cartItem){
        //先查找map集合中是否已经存在待添加的商品
       CartItem item = items.get(cartItem.getId());
       //如果集合中没有该商品
       if(item==null){
           items.put(cartItem.getId(),cartItem);
       }else{
           //如果已存在，商品数量加一
           item.setCount(item.getCount()+1);
           //商品价格累加
           item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
       }

    }


    public void deleteItem(Integer id){
      items.remove(id);
    }


    public void clear(){
      items.clear();
    }


    public void updateCount(Integer id,Integer count){
        CartItem item = items.get(id);
        if(item!=null){
            item.setCount(count);
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount =0;
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()){
            totalCount+=entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for(Map.Entry<Integer,CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }


}
