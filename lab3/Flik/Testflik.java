import static org.junit.Assert.*;

import org.junit.Test;


public class Testflik {
    @Test
    /** test the Flik library.*/
    public void testisthesamenumber() {
        assertTrue("is a type error!", Flik.isSameNumber(127, 127));
        assertTrue("126 != 127", Flik.isSameNumber(126, 127));
        assertTrue("is a type error!", Flik.isSameNumber(128, 128));
        assertTrue("is a type error!", Flik.isSameNumber(129, 129));
    }

}
