import java.util.*;

class Toppings{
    String[] listOfVegIngredients = {"Black Olive","Onion","Crisp Capsicum","Paneer","Golden Corn","Fresh Tomato","Grilled Mushroom","Jalapeno","Red peprika"};
    String[] listOfNonVegIngredients = {"Pepper Barbecue Chicken","Peri-Peri Chicken","Grilled Chicken Raster","Chicken Sausage","Chicken Tikka","Chicken Pepperoni"};
    String currentIngredients; 
    Toppings(String c){
        
        this.currentIngredients = c;         
    }
}
class Bread{
    String[] typesOfBread  = {"New Hand Tossed","Wheat Thin Crust","Cheese Brust","Fresh Pan Pizza","Classic Hand Tossed"};
    String currentBread = "New Hand Tossed";

    void changeBread(){
        int i;
        System.out.println("Current Bread : "+this.currentBread);
        System.out.println("Select Bread:");
        for(i = 0 ; i<typesOfBread.length;i++){
            System.out.println(i+1 + ":"+" "+typesOfBread[i]);
        }
        Scanner scan = new Scanner(System.in);
        this.currentBread = typesOfBread[scan.nextInt()-1];
        System.out.println("Bread changed to: "+this.currentBread);
    }
}
abstract class Pizza{
    String name;
    Integer category;
    String[] size = {"small","medium","large"};
    Integer[] price;
    Bread b;
    Toppings t;

    Pizza(String name,Integer category,String ind){
        this.name =name;
        this.category = category;
        this.b = new Bread();
        this.t = new Toppings(ind);
        this.price = new Integer[3];
    }
    void setPrice(String[] s,Integer[] p){
        int i;
        for(i=0;i<s.length;i++){
            System.out.print(s[i]+":");
            Scanner scan = new Scanner(System.in);
            p[i] = scan.nextInt();
        }
    }
    void selectBread(){this.b.changeBread();}
}
class VegPizza extends Pizza{
    VegPizza(String name,Integer category,String ind){
       super(name,category,ind);
        super.setPrice(super.size,super.price);
    }
 
    void show(){
        System.out.println("name: "+this.name);
        System.out.println(this.t.currentIngredients);
    }
    
}


class PizzaShop{
    public static void main(String args[]) {

        String inds = Arrays.toString(new String[]{"cheese","Fresh Tomato","Crisp Capsicum","onion"});
        VegPizza v = new VegPizza("Delux Veggie",1,inds);
       
    }
}