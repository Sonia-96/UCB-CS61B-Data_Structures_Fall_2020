import java.util.ArrayList;
import java.util.List;

/**
 * Class that collects timing information about SLList getLast method.
 */
public class TimeSLList {
    private static void printTimingTable(List<Integer> Ns, List<Double> times, List<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        List<Integer> Ns = new ArrayList<>();
        List<Double> times = new ArrayList<>();
        List<Integer> opCounts = new ArrayList<>();
        for (int i = 1000; i <= 128000; i *= 2) {
            SLList<Integer> L = new SLList<>();

            for (int j = 0; j < i; j++) {
                L.addLast(j);
                j += 1;
            }

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < 10000; j ++) {
                L.getLast();
            }
            double timeInSeconds = sw.elapsedTime();

            Ns.add(i);
            times.add(timeInSeconds);
            opCounts.add(10000);
        }
        printTimingTable(Ns, times, opCounts);
    }

}
