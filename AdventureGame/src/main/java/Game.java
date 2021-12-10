import java.util.ArrayList;
import java.util.Scanner;

public class Game {
    private Scanner input = new Scanner(System.in);
    private Player player;
    private int numberOfEnterCave=0;
    private int numberOfEnterForest=0;
    private int numberOfEnterRiver=0;



    public void start() {

        System.out.println("Macera Oyununa Hoşgeldiniz !");
        System.out.println("Lütfen bir isim giriniz: ");
        String playerName = input.nextLine();
        Player player = new Player(playerName);
        System.out.println("Sayın "+ player.getName()+" bu karanlık ve sisli adaya hoşgeldiniz !");
        player.selectChar();

        Location location = null;
        while (true) {
            player.printInfo();
            System.out.println();
            System.out.println("################Bölgeler################");
            System.out.println();
            System.out.println("1 - Güvenli Ev --> Burası sizin için güvenli bir ev, düşman yoktur!");
            System.out.println("2 - Eşya Dükkanı --> Silah veya zırh satın alabilirsiniz.");
            System.out.println("3 - Mağara --> Ödül <Yemek> Dikkatli ol zombi çıkabilir!");
            System.out.println("4 - Orman --> Ödül <Odun>  Dikkatli ol vampir çıkabilir!");
            System.out.println("5 - Nehir --> Ödül <Su>  Dikkatli ol ayı çıkabilir!");
            System.out.println("0 - Çıkış Yap --> Oyunu sonalndır.");
            System.out.println("------------------------------------------------------");
            System.out.println("Lütfen gitmek istediğiniz bölgeyi seçiniz: ");
            System.out.println("------------------------------------------------------");
            int selectLoc = input.nextInt();

            switch (selectLoc) {
                case 0:
                    location = null;
                    break;
                case 1:
                    location = new SafeHouse(player);
                    break;
                case 2:
                    location = new ToolStore(player);
                    break;
                case 3:
                    numberOfEnterCave++;
                    if(numberOfEnterCave==2)
                    {
                        System.out.println("Bu bölgedeki ödüller toplandı !");
                        break;
                    }
                    else
                    {
                        location = new Cave(player);
                        break;
                    }

                case 4:
                    numberOfEnterForest++;
                    if(numberOfEnterForest==2)
                    {
                        System.out.println("Bu bölgedeki ödüller toplandı !");
                        break;
                    }
                    else
                    {
                        location = new Forest(player);
                        break;
                    }
                case 5:
                    numberOfEnterRiver++;
                    if(numberOfEnterRiver==2)
                    {
                        System.out.println("Bu bölgedeki ödüller toplandı !");
                        break;
                    }
                    else
                    {
                        location = new River(player);
                        break;
                    }
                default:
                    location = new SafeHouse(player);
            }

            if(location == null)
            {
                System.out.println("Oyun sona erdi.");
                break;
            }

            if(!location.onLocation())
            {
                System.out.println("GAME OVER!");
                break;
            }
        }
    }
}
