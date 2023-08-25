public class NBody {
    public static double readRadius(String filename){
		In in = new In(filename);
        /* The input format is a text file that contains the information for a particular universe (in SI units). 
        * The first value is an integer N which represents the number of planets. 
        * The second value is a real number R which represents the radius of the universe, 
        * used to determine the scaling of the drawing window.
        * Finally, there are N rows, and each row contains 6 values. 
        * The first two values are the x- and y-coordinates of the initial position; 
        * the next pair of values are the x- and y-components of the initial velocity; 
        * the fifth value is the mass; 
        * the last value is a String that is the name of an image file used to display the planets. 
        * Image files can be found in the images directory. 
        * The file above contains data for our own solar system (up to Mars).*/
        int numBodys = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Body[] readBodies(String filename){
        In in = new In(filename);
        int numBodys = in.readInt();
        Body[] Bodies = new Body[numBodys];
        double radius = in.readDouble();
        for (int i=0 ; i < numBodys; i++){
            Bodies[i] = new Body(in.readDouble(),in.readDouble(),in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
        }
        return Bodies;
    }

    public static void main(String[] arg){
        double T = Double.parseDouble(arg[0]);
        double dt = Double.parseDouble(arg[1]);
        String filename = arg[2];
        String background = "images/starfield.jpg";
        StdDraw.setScale(-readRadius(filename), readRadius(filename));
        Body[] allBodys = readBodies(filename); 
        double[] xForces = new double[allBodys.length];
        double[] yForces = new double[allBodys.length];
      

        //Creating an Animation
        StdDraw.enableDoubleBuffering();

        for (double time = 0; time <= T; time += dt){
            for (int i = 0; i < allBodys.length; i+=1){
                xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
                yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
            }

            for (int i = 0; i < allBodys.length; i+=1){
                allBodys[i].update(dt, xForces[i], yForces[i]);
            }
            //Drawing the Background
            //Drawing More than One Body
            StdDraw.clear();
            StdDraw.picture(0, 0,background);
            for (Body everybody : allBodys){
                StdDraw.picture(everybody.xxPos,everybody.yyPos,"images/"+everybody.imgFileName);
            }
            StdDraw.show();
            StdDraw.pause(10);
        }


    }


    
}