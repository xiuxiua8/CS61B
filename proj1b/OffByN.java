public class OffByN implements CharacterComparator {
    private int number;
    public OffByN(int N) {
        number = N;
    }

    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == number || y - x == number) {
            return true;
        }
        return false;
    }

}
