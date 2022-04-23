/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;
import java.util.*;
import model.Fruit;
import model.Order;

/**
 *
 * @author nghie
 */
public class Control {
    private List<Fruit> fruitLst = new ArrayList<>();
    private Hashtable<String , List <Fruit> > shopping = new Hashtable<>();
    
    // Thêm vào list fruit. Nếu x là null return tránh lỗi (Lúc người dùng đã nhập tạo fruit nhưng k muốn lưu)
    public void addFruit(Fruit x){
        if(x == null)
            return;
        for (Fruit fruit : fruitLst) {
            // Nếu fruit truyền vào có name, price, orgin giống nhau thì tăng số lượng quả đó lên
            if(x.getName().equals(fruit.getName()) && x.getPrice() == fruit.getPrice() && x.getOrigin().equals(fruit.getOrigin())){
                fruit.setQuanity(fruit.getQuanity() + x.getQuanity()); return;
            }
        }
        fruitLst.add(x);
    }
    
    // Thêm 1 đơn hàng thành công vào hệ thống vào hệ thống (HashTable)
    // Nếu o là null thì return không làm gì (Trong trường hợp không có fruit nào - fruitLst.isEmpty)
    public void addOrder(Order o){
        if (o == null) {
            return;
        }
        shopping.put(o.getNameCus(), o.getCart());
    }
    
    // Khi đã mua hết hàng thì thì xóa khỏi listFruit để người dùng không chọn nữa
    // Cạnh đó đảo lại ID của fruit trong list 
    // (Chú ý id chỉ để hiện thị người dùng. Để nhận biết fruit có hay chưa trong giỏ hàng ta so sánh ID và tên)
    public void delSoldItProduct(Fruit fr){
        fruitLst.remove(fr);
        int i = 1;
        for (Fruit fruit : fruitLst) {
            fruit.setId(i); i++;
        }
    }
    
    // cho bên view truy cập lấy các loại fruit trong cửa hàng
    public List<Fruit> myFruits(){
        return fruitLst;
    }
    
    // cho bên view truy cập lấy các đơn hàng thành công trong cửa hàng
    public Hashtable <String ,  List <Fruit>> myShopping(){
        return shopping;
    }
    
    // phục vụ cắt bỏ date trả về tên người dùng để hiển thị
    public String getCusName(String k){
        String [] tokens = k.split("[$]");
        return tokens[0].trim();
    }
    
    // phục vụ lấy ra các mặt hàng mà customer đã mua
    public List <Fruit> getCusCart(String k){
        return myShopping().get(k);
    }
}
