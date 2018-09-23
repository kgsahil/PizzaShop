import java.util.*;

class Toppings{
    String[] listOfVegIngredients = {"Black Olive","Onion","Crisp Capsicum","Paneer","Golden Corn","Fresh Tomato","Grilled Mushroom","Jalapeno","Red peprika"};
    String[] listOfNonVegIngredients = {"Pepper Barbecue Chicken","Peri-Peri Chicken","Grilled Chicken Raster","Chicken Sausage","Chicken Tikka","Chicken Pepperoni"};
    String currentIngredients; 
    Toppings(String[] c){
        
        this.currentIngredients = Arrays.toString(c);         
    }
    Toppings(){}
}
class Bread{
    String[] typesOfBread  = {"New Hand Tossed","Wheat Thin Crust","Cheese Brust","Fresh Pan Pizza","Classic Hand Tossed"};
    String currentBread = "New Hand Tossed";

    void changeBread(){
        int i;
        System.out.println("\n");
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
abstract class Pizza{       // abstract class implementation
    String name;
    Integer category;
    String[] size = {"small","medium","large"};
    Integer[] price;
    Bread b;
    Toppings t;         //association relationship

    Pizza(String name,Integer category,String[] ind){
        this.name =name;
        this.category = category;
        this.b = new Bread();
        this.t = new Toppings(ind);
        this.price = new Integer[3];
    }
    void setPrice(String[] s,Integer[] p){          //method overloading
        int i;
        System.out.println("set price for "+this.name);
        for(i=0;i<s.length;i++){
            System.out.print(s[i]+":");
            Scanner scan = new Scanner(System.in);
            p[i] = scan.nextInt();
        }
    }
    void setPrice(Integer sp,Integer mp,Integer lp,Integer[] p){
        p[0] = sp;
        p[1] = mp;
        p[2] = lp;
    }
    void selectBread(){
        this.b.changeBread();
    }

    abstract void show(); //abstract method for Dynamic Polymorphism(method overriding)
}
class VegPizza extends Pizza{
    VegPizza(String name,String[] ind){
       super(name,1,ind);
        super.setPrice(super.size,super.price);
    }
    VegPizza(String name,String[] ind,Integer sp,Integer mp,Integer lp){
        super(name,1,ind);
         super.setPrice(sp,mp,lp,super.price);
     }
 
    void show(){
        int i;
        System.out.println("\n");
        System.out.println("name: "+this.name);
        System.out.println(this.t.currentIngredients);
        for(i=0;i<this.size.length;i++){
            System.out.println(this.size[i]+":"+this.price[i]);
        }
    }
    
}

class NVegPizza extends Pizza{
    NVegPizza(String name,String[] ind){
       super(name,2,ind);
        super.setPrice(super.size,super.price);
    }
    NVegPizza(String name,String[] ind,Integer sp,Integer mp,Integer lp){
        super(name,2,ind);
         super.setPrice(sp,mp,lp,super.price);
     }
 
    void show(){
        int i;
        System.out.println("\n");
        System.out.println("name: "+this.name);
        System.out.println(this.t.currentIngredients);
        for(i=0;i<this.size.length;i++){
            System.out.println(this.size[i]+":"+this.price[i]);
        }
    }
    
}
class PizzaShop{
    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        int selectedpizza, ch=0,cat;
        ArrayList<VegPizza> VPlist = new ArrayList<VegPizza>();         //ArrayList Implementation
        ArrayList<NVegPizza> NVPlist = new ArrayList<NVegPizza>();

        String[] inds = new String[]{"cheese","Fresh Tomato","Crisp Capsicum","onion"};
        VegPizza v1 = new VegPizza("Delux Veggie",inds,136,250,445);
        inds = new String[]{"cheese","Paneer"};
        VegPizza v2 = new VegPizza("Paneer Cheese",inds,100,180,360);
        VPlist.add(v1);
        VPlist.add(v2);
        do{
            System.out.println("\nEnter choice 1.Order pizza 2.Add pizza 3.Exit");
            ch = scan.nextInt();
            switch(ch){
                case 1:  System.out.println("\nEnter choice 1.Veg pizza 2.Non-Veg pizza : ");
                         cat  = scan.nextInt();
                         if (cat == 1) {
                            if(VPlist.isEmpty()){
                                System.out.println("pizza for this category is not available");   
                                break;
                             }
                            for(VegPizza i:VPlist) i.show();
                            System.out.println("Enter Choice or press 99 to go back ");
                            selectedpizza = scan.nextInt(); 
                            if(selectedpizza > VPlist.size()) break;
                            System.out.println("\nSelected Pizza ");
                            VPlist.get(selectedpizza-1).show();
                            VPlist.get(selectedpizza-1).selectBread();
                            System.out.println("\nselect size");
                            int selectsize = scan.nextInt();
                            //String size = VPList.get(selectedpizza-1).size[selectsize-1];
                            System.out.println("\nYour order is confirmed for "+VPlist.get(selectedpizza-1).name+" \nTotal amount: "+VPlist.get(selectedpizza-1).price[selectsize-1]);
                            

                         } else if(cat == 2) {
                             if(NVPlist.isEmpty()){
                                System.out.println("pizza for this category is not available");   
                                break;
                             }
                            for(NVegPizza i:NVPlist) i.show();
                            System.out.println("Enter Choice or press 99 to go back ");
                            selectedpizza = scan.nextInt();
                            if(selectedpizza > NVPlist.size()) break;
                            System.out.println("\nSelected Pizza ");
                            NVPlist.get(selectedpizza-1).show();
                            NVPlist.get(selectedpizza-1).selectBread();
                            System.out.println("\nselect size");
                            int selectsize = scan.nextInt();
                            //String size = VPList.get(selectedpizza-1).size[selectsize-1];
                            System.out.println("\nYour order is confirmed for "+NVPlist.get(selectedpizza-1).name+" \nTotal amount: "+NVPlist.get(selectedpizza-1).price[selectsize-1]);

                         }
                       
                break;
                case 2: System.out.println("Creating your own pizza is fun, just few steps and it's done ");
                        System.out.println("Enter category: 1.Veg pizza 2.Non-Veg pizza");
                        cat = scan.nextInt();
                        System.out.println("name of your pizza: ");
                        String customPizzaName = scan.next();
                        System.out.println("Select Toppings:");
                        System.out.println("(Input: eg. 1-6-3-7-2) ");
                        Toppings t = new Toppings();
                        int  i;
                        List<String> toppingsList = new ArrayList<String>();
                        for(i=0;i<t.listOfVegIngredients.length;i++){
                            System.out.println(i+1 +" : "+t.listOfVegIngredients[i]);
                        }
                        String[] topindexes = scan.next().split("-");
                        for(String index:topindexes){toppingsList.add(t.listOfVegIngredients[Integer.parseInt(index)-1]);}
                        if(cat == 2){
                            for(i=0;i<t.listOfNonVegIngredients.length;i++){
                                System.out.println(i+1 +" : "+t.listOfNonVegIngredients[i]);
                            }
                            topindexes = scan.next().split("-");
                            for(String index:topindexes){toppingsList.add(t.listOfNonVegIngredients[Integer.parseInt(index)-1]);}
                        }
                        System.out.println("Selected toppings are "+toppingsList);
                        if(cat  == 1){
                           VegPizza v = new VegPizza(customPizzaName,toppingsList.toArray(new String[] {}));
                           v.selectBread();
                           VPlist.add(v);
                           System.out.println("pizza added to Veg pizza list");
                        }else if(cat == 2){
                            NVegPizza n = new NVegPizza(customPizzaName,toppingsList.toArray(new String[] {}));
                           n.selectBread();
                           NVPlist.add(n);
                           System.out.println("pizza added to Non-Veg pizza list");
                        }else System.out.println("pizza rejected");

                         
                break;
                case 3: System.exit(1);
                break;
            }
            
        }while(ch != 3);
       
       
    }

    
}