package synthesizer;

//Make sure this class is public
public class GuitarString {
    /** Constants. Do not change. In case you're curious, the keyword final means
     * the values cannot be changed at runtime. We'll discuss this and other topics
     * in lecture on Friday. */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private BoundedQueue<Double> buffer;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new ArrayRingBuffer<Double>((int) Math.round(SR / frequency));
        for (int i = 0; i < buffer.capacity(); i += 1) {
            buffer.enqueue(.0);
        }
    }


    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        int existcount = buffer.fillCount();
        //for (int i = 0; i < buffer.fillCount(); i += 1){
        for (int i = 0; i < existcount; i += 1) {
            buffer.dequeue();
        }
        for (int i = 0; i < buffer.capacity(); i += 1) {
            double r = Math.random() - 0.5;
            buffer.enqueue(r);
        }

    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm. 
     */
    public void tic() {
        buffer.enqueue((buffer.dequeue() + buffer.peek()) * DECAY / 2.0);

    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.peek();
    }
}
