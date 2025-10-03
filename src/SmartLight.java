/**
 * Třída SmartLight implementuje rozhraní ISmartDevice a poskytuje
 * konkrétní implementace metod pro zapnutí, vypnutí a zjištění stavu chytrého světla.
 */
class SmartLight implements ISmartDevice {
    private String nazev;
    private boolean zapnuto;
    private int spusteno;

    /**
     * Vytvoří nové chytré světlo s daným názvem.
     *
     * @param nazev Název chytrého světla.
     */
    public SmartLight(String nazev) {
        this.nazev = nazev;
        this.zapnuto = false;
    }

    /**
     * Zapne chytré světlo.
     */
    @Override
    public void zapni() {
        zapnuto = true;
        System.out.println(nazev + " je zapnuto.");
        spusteno++;
    }

    /**
     * Vypne chytré světlo.
     */
    @Override
    public void vypni() {
        zapnuto = false;
        System.out.println(nazev + " je vypnuto.");
    }

    /**
     * Vrátí aktuální stav chytrého světla.
     *
     * @return Řetězec reprezentující aktuální stav světla.
     */
    @Override
    public String stav() {
        if (zapnuto) {
            return " zapnuto";
        } else {
            return " vypnuto";
        }
    }

    public String toString(){
        return nazev + stav();
    }

    public String getNazev() {
        return nazev;
    }

    @Override
    public boolean isZapnuto() {
        return zapnuto;
    }

    public void setNazev(String nazev) {
        this.nazev = nazev;
    }
    public void setZapnuto(boolean zapnuto) {
        this.zapnuto = zapnuto;
    }
    public int getSpusteno() {
        return spusteno;
    }

}
