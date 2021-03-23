/**
 * TODO: Fill in the add and floor methods.
 */
public class AListFloorSet implements Lab5FloorSet {
    AList<Double> items;

    public AListFloorSet() {
        items = new AList<>();
    }

    public void add(double x) {
        for (int i = 0; i < items.size(); i++) {
            if(items.get(i) == x) {
                return ;
            }
        }
        items.addLast(x);
    }

    public double floor(double x) {
        double res = Double.NEGATIVE_INFINITY;
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i) > res && items.get(i) <= x) {
                res = items.get(i);
            }
        }
        return res;
    }
}
