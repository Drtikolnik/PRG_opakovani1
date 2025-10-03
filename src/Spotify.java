class Spotify implements IStreamingService {
    private boolean prehravani;
    private String nazev = "Spotify";
    private int spusteno;

    /**
     * Přehrává zadaný titul na Spotify.
     *
     * @param nazevTitulu Název titulu, který má být přehrán.
     */
    @Override
    public void prehrat(String nazevTitulu) {
        prehravani = true;
        System.out.println("Přehrávání na Spotify: " + nazevTitulu);
        spusteno++;
    }

    /**
     * Zastaví aktuálně přehrávaný obsah na Spotify.
     */
    @Override
    public void stop() {
        prehravani = false;
        System.out.println("Spotify přehrávání ukončeno.");
    }

    @Override
    public boolean prehrava() {
        return prehravani;
    }

    public boolean isPrehravani() {
        return prehravani;
    }

    public String getNazev() {
        return nazev;
    }
    public int getSpusteno() {
        return spusteno;
    }

}