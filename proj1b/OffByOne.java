public class OffByOne implements CharacterComparator {
    @Override
    public boolean equalChars(char x, char y) {
        return  y - x == 1 ||  y - x == -1;
    }
}
