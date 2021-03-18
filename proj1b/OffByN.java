public class OffByN implements CharacterComparator {
    private int N;

    public OffByN(int n) {
        N = n;
    }

    @Override
    public boolean equalChars(char x, char y) {
        x = Character.toLowerCase(x);
        y = Character.toLowerCase(y);
        return  x - y == N ||  x - y == -N;
    }
}
