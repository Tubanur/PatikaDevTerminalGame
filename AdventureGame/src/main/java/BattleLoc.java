import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BattleLoc extends Location{
    private Obstacle obstacle;
    private String award;
    private int maxObstacle;


    public BattleLoc(Player player, String name, Obstacle obstacle, String award, int maxObstacle) {
        super(player, name);
        this.maxObstacle= maxObstacle;
        this.obstacle=obstacle;
        this.award=award;

    }


    @Override
    public boolean onLocation() {

        int obsNumber = this.randomObstacleNumber();
        System.out.println("Şuan buradasınız: "+ this.getName());
        System.out.println("Dikkatli ol. Burada " + obsNumber + " tane " + this.getObstacle().getName() +" yaşıyor !");
        System.out.println("<S>avaş yada <K>aç");
        String selectCase = input.nextLine();
        selectCase = selectCase.toUpperCase();
        if(selectCase.equals("S"))
        {
            if(combat(obsNumber)) {
                System.out.println(this.getName() + " tüm düşmanları yendiniz !");
                return true;
            }
        }

        if(this.getPlayer().getHealth() <= 0) {
            System.out.println("Öldünüz");
            return false;
        }

        return true;
    }

    public boolean combat(int obsNumber) {
        for(int i=1; i<= obsNumber; i++) {
            this.getObstacle().setHealth(this.getObstacle().getOriginalHealth());
            playerStats();
            obstacleStats(i);
            System.out.println("Bol Şans. Kimin başlayacağı belirleniyor.");
            while (this.getPlayer().getHealth() > 0 && this.getObstacle().getHealth() > 0) {
                Random r=new Random();
                int a=r.nextInt(2);
                if(a==1)
                {
                    System.out.println("İlk Siz başlayacaksınız : ");
                    System.out.println("<V>ur veya <K>aç : ");
                    String selectCombat = input.nextLine().toUpperCase();
                    if (selectCombat.equals("V")) {
                        System.out.println("Siz vurdunuz!");
                        this.getObstacle().setHealth(this.getObstacle().getHealth()-this.getPlayer().getTotalDamage());
                        afterHit();
                        if(this.getObstacle().getHealth() > 0)
                        {
                            System.out.println();
                            System.out.println("Canavar size vurdu!");
                            int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                            if(obstacleDamage <0 ) {
                                obstacleDamage = 0;
                            }
                            this.getPlayer().setHealth(this.getPlayer().getHealth() -obstacleDamage);
                            afterHit();
                        }
                    }
                    else {
                        return false;
                    }
                }
                else if(a==2)
                {
                    System.out.println("İlk Canavar Başlayacak : ");
                    if(this.getObstacle().getHealth() > 0)
                    {
                        System.out.println();
                        System.out.println("Canavar size vurdu!");
                        int obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                        if(obstacleDamage <0 ) {
                            obstacleDamage = 0;
                        }
                        this.getPlayer().setHealth(this.getPlayer().getHealth() -obstacleDamage);
                        afterHit();
                        System.out.println("Sıra Sizde : ");
                        System.out.println("<V>ur veya <K>aç : ");
                        String selectCombat = input.nextLine().toUpperCase();
                        if (selectCombat.equals("V")) {
                            System.out.println("Siz vurdunuz!");
                            this.getObstacle().setHealth(this.getObstacle().getHealth() - this.getPlayer().getTotalDamage());
                            afterHit();
                            if (this.getObstacle().getHealth() > 0) {
                                System.out.println();
                                System.out.println("Canavar size vurdu!");
                                obstacleDamage = this.getObstacle().getDamage() - this.getPlayer().getInventory().getArmor().getBlock();
                                if (obstacleDamage < 0) {
                                    obstacleDamage = 0;
                                }
                                this.getPlayer().setHealth(this.getPlayer().getHealth() - obstacleDamage);
                                afterHit();
                            }
                        }
                    }
                }

            }

            if(this.getObstacle().getHealth() <  this.getPlayer().getHealth()) {
                System.out.println("Düşmanı Yendiniz !");
                System.out.println(this.getObstacle().getAward() + " para kazandınız !");
                this.getPlayer().setMoney(this.getPlayer().getMoney()+getObstacle().getAward());
                System.out.println("Güncel Paranız : "+getPlayer().getMoney());
                System.out.println("Kazanılan envanter : " + this.award);
                this.getPlayer().getList().add(this.award);

            }
        }
        return false;
    }

    public void afterHit() {
        System.out.println("Canınız : " + this.getPlayer().getHealth());
        System.out.println(this.getObstacle().getName()+" Canı : " + this.getObstacle().getHealth());
    }


    public void playerStats() {
        System.out.println("Oyuncu Değerleri");
        System.out.println("----------------------------------");
        System.out.println("Sağlık : "+this.getPlayer().getHealth());
        System.out.println("Silah : " + this.getPlayer().getInventory().getWeapon().getName());
        System.out.println("Zırh : " + this.getPlayer().getInventory().getArmor().getName());
        System.out.println("Bloklama : " + getPlayer().getInventory().getArmor().getBlock());
        System.out.println("Hasar : "+this.getPlayer().getTotalDamage());
        System.out.println("Para : "+this.getPlayer().getMoney());

    }

    public void obstacleStats(int i) {
        System.out.println(i +"." + this.getObstacle().getName() + " Değerleri");
        System.out.println("-------------------------------------------");
        System.out.println("Sağlık : " + this.getObstacle().getHealth());
        System.out.println("Hasar : " + this.getObstacle().getDamage());
        System.out.println("Ödül : " + this.getObstacle().getAward());

    }

    public int randomObstacleNumber() {
        Random r = new Random();
        return r.nextInt(this.getMaxObstacle())+1;
    }

    public Obstacle getObstacle() {
        return obstacle;
    }

    public void setObstacle(Obstacle obstacle) {
        this.obstacle = obstacle;
    }

    public String getAward() {
        return award;
    }

    public int getMaxObstacle() {
        return maxObstacle;
    }

    public void setMaxObstacle(int maxObstacle) {
        this.maxObstacle = maxObstacle;
    }

    public void setAward(String award) {
        this.award = award;
    }
}
