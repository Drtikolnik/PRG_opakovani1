public class E621 implements IStreamingService {
    private boolean prehravani;
    private String nazev = "e621";
    private int spusteno;
    @Override
    public void prehrat(String nazevTitulu) {
        prehravani = true;
        System.out.println("Přehrávání na e621: " + nazevTitulu);
        spusteno++;
    }

    @Override
    public void stop() {
        prehravani = false;
        System.out.println("e621 přehrávání ukončeno.");
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


