public class OffByOne implements CharacterComparator {
    // off by one 例如,'%' 和 '&' 相差 1
    @Override
    public boolean equalChars(char x, char y) {
        if (x - y == 1 || y - x == 1) {
            return true;
        }
        return false;
    }

}

