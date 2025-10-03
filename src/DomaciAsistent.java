import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Třída DomaciAsistent spravuje seznam chytrých zařízení a streamovacích služeb.
 * Umožňuje přidávat zařízení a služby, zapínat a vypínat všechna zařízení,
 * a přehrávat obsah na všech streamovacích službách.
 */
public class DomaciAsistent {
    private List<ISmartDevice> zarizeni = new ArrayList<>();
    private List<IStreamingService> sluzby = new ArrayList<>();
    private Scanner scanner;

    public DomaciAsistent(Scanner scanner){
        this.scanner = scanner;
        sluzby.add(new Netflix());
        sluzby.add(new Spotify());
    }

    /**
     * Přidá chytré zařízení do seznamu spravovaných zařízení.
     *
     */
    public void pridejZarizeni() {
        System.out.println("Vyberte typ zařízení:");
        System.out.println("1. SmartLight");
        System.out.println("2. SmartThermostat");
        int typ = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Zadejte název zařízení: ");
        String nazev = scanner.nextLine();

        switch (typ) {
            case 1:
                zarizeni.add(new SmartLight(nazev));
                break;
            case 2:
                System.out.print("Zadejte počáteční teplotu: ");
                double teplota = scanner.nextDouble();
                scanner.nextLine(); // consume newline
                zarizeni.add(new SmartThermostat(nazev, teplota));
                break;
            default:
                System.out.println("Neplatný typ zařízení.");
        }
    }

    /**
     * Odebere chytré zařízení ze seznamu spravovaných zařízení.
     */
    public void odeberZarizeni() {
        System.out.println("Jaké zařízení chceš odebrat?");
        for (ISmartDevice z : zarizeni) {
            System.out.println(z.toString());
        }
        String nazev = scanner.nextLine();
        zarizeni.removeIf(z -> z.getNazev().equals(nazev));

    }

    /**
     * Vypíše všechna spravovaná chytrá zařízení.
     */
    public void vypisZarizeni() {
        System.out.println("Seznam spravovaných zařízení:");
        for (ISmartDevice z : zarizeni) {
            System.out.println(z.toString());
        }
    }

        /**
     * Zapne všechna spravovaná chytrá zařízení.
     */
    public void zapniVse() {
        for (ISmartDevice z : zarizeni) {
            z.zapni();
        }
    }

    /**
     * Vypne všechna spravovaná chytrá zařízení.
     */
    public void vypniVse() {
        for (ISmartDevice z : zarizeni) {
            z.vypni();
        }
    }

    /**
     * Přehrává zadaný obsah na všech spravovaných streamovacích službách.
     */
    public void prehratNaVsechSluzbach() {
        System.out.print("Zadejte název obsahu, který chcete přehrát: ");
        String nazev = scanner.nextLine();
        for (IStreamingService s : sluzby) {
            s.prehrat(nazev);
        }
    }

    /**
     * Ovládá chytrý termostat podle zadaného názvu a umožňuje nastavit novou teplotu.
     * Pokud termostat s daným názvem není nalezen, zobrazí chybovou zprávu.
     */
    public void ovladaniTermostatu() {
        System.out.println("Seznam Termostatů:");
        for (ISmartDevice z : zarizeni) {
            if (z instanceof SmartThermostat) {
                System.out.println(z.toString());
            }
        }

        System.out.println("Zadejte název termostatu, který chcete ovládat:");
        String nazev = scanner.nextLine();
        for (ISmartDevice z : zarizeni) {
            if ((z instanceof SmartThermostat) && (((SmartThermostat) z).getNazev().equals(nazev))) {
                System.out.print("Zadejte novou teplotu: ");
                double teplota = scanner.nextDouble();
                scanner.nextLine();
                ((SmartThermostat) z).nastavTeplotu(teplota);
                return;
            }
        }
        System.out.println("Termostat s názvem " + nazev + " nebyl nalezen.");
    }

    public void vypisVsechnyZaple() {
        System.out.println("Seznam zaplých zařízení:");
        for (ISmartDevice z : zarizeni) {
            if (z.isZapnuto()) {
                System.out.println(z.toString());
            }
        }
        System.out.println("Seznam zaplých služeb:");
        for (IStreamingService s : sluzby) {
            if (s.isPrehravani()) {
                System.out.println(s.getNazev());
            }
        }
    }

    public void zmenNazev() {
        System.out.println("Jaké zařízení chceš CHANGED?");
        for (ISmartDevice z : zarizeni) {
            System.out.println(z.toString());
        }
        String nazev = scanner.nextLine();
        System.out.println("Na jaké jméno chceš CHANGED?");
        String novyNazev = scanner.nextLine();
        for (ISmartDevice z : zarizeni) {
            if (z.getNazev().equals(nazev)) {
                z.setNazev(novyNazev);
            }
        }
    }


    public void statistika(){
        int nejviceSpusteni = 0;
        ISmartDevice nejviceSpusteniZ = null;
        for (ISmartDevice z : zarizeni) {
            if (nejviceSpusteniZ == null || z.getSpusteno() > nejviceSpusteniZ.getSpusteno()) {
                nejviceSpusteniZ = z;
            }
        }
        IStreamingService nejviceSpusteniS = null;
        for (IStreamingService s : sluzby) {
            if (nejviceSpusteniS == null || s.getSpusteno() > nejviceSpusteniS.getSpusteno()) {
                nejviceSpusteniS = s;
            }
        }

        if(nejviceSpusteniZ.getSpusteno() > nejviceSpusteniS.getSpusteno()) {
            System.out.println("Nejvíce bylo spuštěno " +nejviceSpusteniZ.getNazev()+ ", a bylo spuštěno " +nejviceSpusteniZ.getSpusteno()+ "x");
        }else if(nejviceSpusteniZ.getSpusteno() < nejviceSpusteniS.getSpusteno()){
            System.out.println("Nejvíce bylo spuštěno " +nejviceSpusteniS.getNazev()+ ", a bylo spuštěno " +nejviceSpusteniS.getSpusteno()+ "x");
        }else if(nejviceSpusteniZ.getSpusteno() == nejviceSpusteniS.getSpusteno()) {
            System.out.println("Nejvíce byla spuštěna " +nejviceSpusteniZ.getNazev()+ " a " +nejviceSpusteniS.getNazev()+ ", a byla spuštěna " +nejviceSpusteniS.getSpusteno()+ "x");
        }


        //----SPUSTENO DOHROMADY
        int spustenoDohromady = 0;
        for (ISmartDevice z : zarizeni) {
            if (z.isZapnuto()) {
                spustenoDohromady++;
            }
        }
        for (IStreamingService s : sluzby) {
            if (s.isPrehravani()) {
                spustenoDohromady++;
            }
        }
        System.out.println("Zařízení byla puštěna dohromady " +spustenoDohromady+ "x");



    }


}

