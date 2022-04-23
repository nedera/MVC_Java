package view;

import control.Control;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import model.Fruit;
import model.Order;

public class Main {

    Control ctrl = new Control();

    public static void main(String[] args) {
        Main v = new Main();
        v.displayMenu();

    }

    // Chứa switch - case và gọi các hàm ở dưới
    public void displayMenu() {
        addFruit(new Fruit(ctrl.myFruits().size() + 1, "Apple", 2, 300, "Vn"));
        addFruit(new Fruit(ctrl.myFruits().size() + 1, "Kiwi", 3, 150, "Us"));
        addFruit(new Fruit(ctrl.myFruits().size() + 1, "Kiwi", 3, 150, "Us"));
        addFruit(new Fruit(ctrl.myFruits().size() + 1, "Apple", 4, 150, "USA"));

        int choice;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println();
            System.out.println("FRUIT SHOP SYSTEM\n"
                    + "1. Create Fruit\n"
                    + "2. View orders\n"
                    + "3. Shopping (for buyer)\n"
                    + "4. Exit");
            choice = CheckInput.getInt(sc, ">> Enter your choice: ", 1, 4);
            System.out.println();
            switch (choice) {
                case 1:
                    addFruit(createFruit());
                    printFruitInShop();
                    break;
                case 2:
                    viewOrders();
                    break;
                case 3:
                    addOrder(shopping());
                    break;
                case 4:
                    break;
            }
        } while (choice != 4);

    }

    // Gọi qua controller thêm fruit vừa tạo.
    public void addFruit(Fruit fr) {
        ctrl.addFruit(fr);
    }

    // Gọi qua controller thêm 1 đơn hàng. Sau khi khách hàng nhập tên
    public void addOrder(Order o) {
        ctrl.addOrder(o);
    }

    // Hàm cho người dùng nhập fruit các thuộc tính và trả về Fruit
    // Nếu người dùng chọn "N" không muốn lưu thì trả về null
    public Fruit createFruit() {
        Scanner sc = new Scanner(System.in);
        Fruit f = new Fruit();
        f.setId(ctrl.myFruits().size() + 1);
        f.setName(CheckInput.getsString(sc, "Enter fruit's name: ", false, ""));
        f.setPrice(CheckInput.getIntNoMax(sc, "Enter price: ", 1));
        f.setQuanity(CheckInput.getIntNoMax(sc, "Enter quanity: ", 1));
        f.setOrigin(CheckInput.getsString(sc, "Enter fruit's origin: ", false, ""));
        String save = CheckInput.getsString(sc, "Do you want to continue (Y/N)? ", false, "[YNyn]");
        if (save.toUpperCase().equals("Y")) {
            return f;
        }
        return null;
    }

    //  Khách hàng chọn sản phẩm 
    public Order shopping() {
        Scanner sc = new Scanner(System.in);
        Order o = new Order();
        // Hiển thị danh sách các sản phẩm
        String choice = null;
        if (ctrl.myFruits().isEmpty()) {
            System.out.println("No product! Please come back later.");
            return null;
        }

        int itemInput, quantity, price;
        do {
            // In tất cả hoa quả có trong shop cho khách hàng dễ chọn
            printFruits();
            //Nhập
            itemInput = CheckInput.getInt(sc, "Enter the item number: ", 1, ctrl.myFruits().size());

            //Con trỏ vào vị trí vừa chọn
            Fruit pointer = ctrl.myFruits().get(itemInput - 1);
            System.out.println("You selected: " + pointer.getName());
            quantity = CheckInput.getInt(sc, String.format("Enter the quantity(%d - %d): ", 0, pointer.getQuanity()), 0, pointer.getQuanity());
            pointer.setQuanity(pointer.getQuanity() - quantity);

            // Xóa khỏi danh sách sản phẩm đã bán hết
            if (pointer.getQuanity() == 0) {
                ctrl.delSoldItProduct(pointer);
            }
            price = pointer.getPrice();
            // Thêm fruit vào list order chỉ khi quatity người dùng nhập khác 0
            if (quantity != 0) {
                o.addFruit(new Fruit(itemInput, pointer.getName(), price, quantity, pointer.getOrigin()));
            }

            //Hiển thị những thứ người dùng đã chọn. Đã thêm vào giỏ
            printFrInCart(o.getCart());

            // Check xem cái sản phẩm mà người dùng vừa thêm đã là cuối cùng chưa?
            // Nghĩa là sau khi trừ số lượng nếu cái đã hết không còn gì thì bắt buộc người dùng phải thanh toán
            if (ctrl.myFruits().isEmpty()) {
                System.out.println("No product! Please come back later.");
                break;
            }
            //Hỏi người dùng thanh toán hay không hay tiếp tục mua
            choice = CheckInput.getsString(sc, "Do you want to order now (Y/N)", false, "[YNyn]");
        } while (choice.compareToIgnoreCase("n") == 0);
        // Nếu giỏ hàng không có gì return null;
        if (o.getCart().isEmpty()) {
            System.err.println("No product in your cart");
            return null;
        }
        // Tên người dùng + date để tránh trùng key trong hashTable
        Date date = new Date();
        o.setNameCus(CheckInput.getsString(sc, "Input your name: ", false, "") + "$" + date);
        return o;
    }

    // Hàm trợ giúp hiển thị trong phần order
    public void printFruits() {
        System.out.println("List of Fruit:\n"
                + "| ++ Item ++ | ++ Fruit Name ++ | ++ Origin ++ | ++ Price ++ |");
        for (Fruit f : ctrl.myFruits()) {

            System.out.printf("       %-12s%-20s%-14s%s$\n", f.getId(), f.getName(), f.getOrigin(), f.getPrice());
        }
    }

    // Hàm trợ giúp hiển thị những thứ đã đc khác thêm vào giỏ
    public void printFrInCart(List<Fruit> frLst) {
        System.out.println("Product | Quantity | Price | Amount ");
        int i = 1;
        for (Fruit fr : frLst) {
            System.out.printf("%-12s%-11s%-6s%s$\n", (i + ". " + fr.getName()), fr.getQuanity(), fr.getPrice(), (fr.getQuanity() * fr.getPrice()));
            i++;
        }
    }

    // Hàm trợ giúp hiển thị lúc người dùng create xong 1 fruit
    public void printFruitInShop() {
        System.out.println("=== Fruits in Shop ===");
        System.out.printf("%-16s%-16s%-16s%-16s%-16s\n", "Fruit Id", "Fruit Name", "Price", "Quantity", "Origin");
        for (Fruit fr : ctrl.myFruits()) {
            System.out.printf("%-16s%-16s%-16s%-16s%-16s\n", fr.getId(), fr.getName(), fr.getPrice(), fr.getQuanity(), fr.getOrigin());
        }
    }

    // Hàm trợ giúp hiển thị in các đơn hàng thành công
    public void viewOrders() {
        Set<String> keySet = ctrl.myShopping().keySet();
        for (String key : keySet) {
            System.out.println("Customer: " + ctrl.getCusName(key));
            printFrInCart(ctrl.myShopping().get(key));
        }
    }
}
