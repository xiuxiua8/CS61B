public class Body {
	public double xxPos;
	public double yyPos; 
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	public static double G = 6.67e-11;

	public Body(double xP, double yP, double xV,double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

	public Body(Body b){
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b){
		double dx = this.xxPos - b.xxPos;
		double dy = this.yyPos - b.yyPos;
		double r = Math.sqrt(dx*dx + dy*dy);
		return r;
	}

	public double calcForceExertedBy(Body b){
		double distance = this.calcDistance(b);
		double Force  = G * this.mass * b.mass/(distance*distance);
		return Force ;
	}

	public double calcForceExertedByX(Body b){
		double Force = this.calcForceExertedBy(b);
		double distance = this.calcDistance(b);
		double dx = b.xxPos - this.xxPos;
		double Fx = Force * dx / distance;
		return Fx ;
	}

	public double calcForceExertedByY(Body b){
		double Force = this.calcForceExertedBy(b);
		double distance = this.calcDistance(b);
		double dy = b.yyPos - this.yyPos;
		double Fy = Force * dy / distance;
		return Fy ;
	}

	public double calcNetForceExertedByX(Body[] allBodys){
		double NFx = 0.0;
		for (int i = 0; i < allBodys.length; i++){
			if (this == allBodys[i]){
				continue;
			}
			NFx = NFx + this.calcForceExertedByX(allBodys[i]);
		}
		return NFx ;
	}
	public double calcNetForceExertedByY(Body[] allBodys){
		double NFy = 0.0;
		for (int i = 0; i < allBodys.length; i++){
			if (this == allBodys[i]){
				continue;
			}
			NFy = NFy + this.calcForceExertedByY(allBodys[i]);
		}
		return NFy ;
	}

	public void update (double dt, double xforce, double yforce){
		double Ax = xforce / this.mass;
		double Ay = yforce / this.mass;
		this.xxVel += Ax * dt;
		this.yyVel += Ay * dt;
		this.xxPos = this.xxPos + dt * xxVel;
		this.yyPos = this.yyPos + dt * yyVel;	
	}

	public void draw (){
		StdDraw.picture(this.xxPos, this.yyPos , this.imgFileName);
	}
}