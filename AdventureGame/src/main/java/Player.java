import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Player {
    private Scanner input = new Scanner(System.in);
    private Inventory inventory;
    private int damage;
    private int health;
    private int money;
    private String name;
    private String charName;
    private int originalHealth;
    private BattleLoc battleLoc;
    private List<String> list=new ArrayList<String>();

    public BattleLoc getBattleLoc() {
        return battleLoc;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public void setBattleLoc(BattleLoc battleLoc) {
        this.battleLoc = battleLoc;
    }

    public Player(String name)
    {
        this.name=name;
        this.inventory= new Inventory();
    }

    public int getTotalDamage() {
        return damage + this.getInventory().getWeapon().getDamage();
    }

    public int getOriginalHealth() {
        return originalHealth;
    }

    public void setOriginalHealth(int originalHealth) {
        this.originalHealth = originalHealth;
    }


    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage=damage;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health=health;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money=money;
    }

    public String getName() {
        return name;
    }

    public void setName(int money){
        this.name=name;
    }

    public String getCharName() {
        return charName;
    }

    public void setCharName(String charName){
        this.charName=charName;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }




    public void selectChar() {
        Samurai samurai = new Samurai();
        Archer archer = new Archer();
        Knight knight = new Knight();


        GameChar[] charList = {new Samurai(), new Archer(), new Knight()};
        System.out.println("--------------------------------------------------------------------");
        System.out.println("Karakterler:");
        System.out.println("--------------------------------------------------------------------");
        for (GameChar gameChar : charList) {
            System.out.println("ID: "+ gameChar.getId()+" \t Karakter: "+gameChar.getName() + "\t Hasar: "+ gameChar.getDamage() + "\t Sağlık: "+gameChar.getHealth()+"\t Para: "+gameChar.getMoney());
        }

        System.out.println("--------------------------------------------------------------------");
        System.out.println("Lütfen oyuna başlamak için bir karakter seçiniz!");
        System.out.println("--------------------------------------------------------------------");
        int selectChar = input.nextInt();
        switch (selectChar) {
            case 1:
                initPlayer(new Samurai());
                break;
            case 2:
                initPlayer(new Archer());
                break;
            case 3:
            {
                    initPlayer(new Knight());
                    break;
                }

            default:
                initPlayer(new Samurai());
        }
        System.out.println("Karakter : "+ this.getCharName() +
                ", Hasar: "+ this.getDamage()+
                ", Sağlık: "+ this.getHealth()+
                ", Para: "+ this.getMoney());
    }

    public void initPlayer(GameChar gameChar) {
        this.setDamage(gameChar.getDamage());
        this.setHealth(gameChar.getHealth());
        this.setOriginalHealth(gameChar.getHealth());
        this.setMoney(gameChar.getMoney());
        this.setCharName(gameChar.getName());
    }

    public void printInfo() {
        System.out.println("Silahınız : "+this.getInventory().getWeapon().getName() +
                ", Zırhınız : " + this.getInventory().getArmor().getName() +
                ", Bloklama : " + this.getInventory().getArmor().getBlock() +
                ", Hasarınız : " + this.getDamage() +
                ", Sağlık : " + this.getHealth() +
                ", Para : " + this.getMoney() +
                ", Envanter : " + this.getList().toString()


        );
    }


}
