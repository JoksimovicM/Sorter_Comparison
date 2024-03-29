package Java;

/**
 * @author Jonas Rhbary
 * @version 1.0
 * @date 26.01.22
 */

public class Shakersort implements SorterInterface {

    int k;
    private long duration;
    private int comparisons;
    private long schreibzugriffe;
    private int memory;

    Runtime rt = Runtime.getRuntime();

    /**
     * Sort() sortiert die Zahlen mit hilfe vom Shakesort aka. Cocktailsort
     * Das zu sortierende Feld wird abwechselnd nach oben und nach unten durchlaufen.
     * Dabei werden jeweils zwei benachbarte Elemente verglichen und gegebenenfalls vertauscht.
     * Durch diese Bidirektionalität kommt es zu einem schnelleren Absetzen von großen bzw. kleinen Elementen.
     * Anhand des Sortierverfahrens lässt sich auch der Name erklären, denn der Sortiervorgang erinnert an das Schütteln des Arrays oder eines Barmixers.
   
     * Best-Case: θ(n)
     * Average-Case: θ(n^2)
     * Worst-Case: θ(n^2)
     * @param zahlen
     */
    
    @Override
    public void sort(int[] zahlen) {
        comparisons = 0;
        schreibzugriffe = 0;
        memory = 0;

        long startTime = System.nanoTime();

        int i = 0;
        int l = zahlen.length;

        while (i < l) {
            for (int j = i; j < l - 1; j++) {
                comparisons++;
                if (zahlen[j] > zahlen[j + 1]) {
                    schreibzugriffe++;
                    k = zahlen[j];
                    zahlen[j] = zahlen[j + 1];
                    zahlen[j + 1] = k;
                }
            }
            l--;
            for (int j = l - 1; j >= i; j--) {
                comparisons++;
                if (zahlen[j] > zahlen[j + 1]) {
                    schreibzugriffe++;
                    k = zahlen[j];
                    zahlen[j] = zahlen[j + 1];
                    zahlen[j + 1] = k;
                }
            }
            i++;
        }
        memory = (int)rt.totalMemory() - (int)rt.freeMemory();

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        setTime(duration);
    }

    @Override
    public int getSpeicherbedarf() {
        return memory;
    }

    @Override
    public long getTime() {
        return duration;
    }

    public void setTime(long duration) {
        this.duration = duration;
    }

    @Override
    public int anzVergleiche() {
        return comparisons;
    }

    @Override
    public long anzahlSchreibzugriffe() {
        return schreibzugriffe;
    }
}
