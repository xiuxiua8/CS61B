public class SpeedTest {
	public static void main(String[] args) {
		//LinkedListDeque<Integer> L = new LinkedListDeque<>();//10000000
        ArrayDeque<Integer> L = new ArrayDeque<>();//100000
		int i = 0;
		while (i < 100000) {
			L.addFirst(i);
			i = i + 1;
		}
	}
} 