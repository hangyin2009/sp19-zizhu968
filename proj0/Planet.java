import java.lang.Math;

public class Planet{
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private final static double G = 6.67e-11;

	public Planet(double xP, double yP, double xV,
              double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}

    public Planet(Planet b){
    	xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
    }

    public double calcDistance(Planet b){
		double distance;
		distance = Math.sqrt(Math.pow(b.xxPos-xxPos,2)+
		Math.pow(b.yyPos-yyPos,2));
		return distance;
	}
	public double calcForceExertedBy(Planet b){
		double force;
		force = G * mass * b.mass / Math.pow(calcDistance(b), 2);
		return force;
	}

	public double calcForceExertedByX(Planet b){
		double forceX;
		forceX = (b.xxPos-xxPos)/calcDistance(b)*calcForceExertedBy(b);
		return forceX;
	}

	public double calcForceExertedByY(Planet b){
		double forceY;
		forceY = (b.yyPos-yyPos)/calcDistance(b)*calcForceExertedBy(b);
		return forceY;
	}


	public double calcNetForceExertedByX(Planet[] b){
		double netforceX = 0;
		for(int i = 0; i < b.length; i++){
			if (this.equals(b[i])){
				continue;
			}
			else
			{
				netforceX = netforceX + calcForceExertedByX(b[i]);
			}
		}
		return netforceX;
	}

	public double calcNetForceExertedByY(Planet[] b){
		double netforceY = 0;
		for(int i = 0; i < b.length; i++){
			if (this.equals(b[i])){
				continue;
			}
			else
			{
				netforceY = netforceY + calcForceExertedByY(b[i]);
			}
		}
		return netforceY;
	}

	public void update(double dt, double fX, double fY){
		double a_netx = fX/mass;
		double a_nety = fY/mass;
		xxVel = xxVel + dt * a_netx;
		yyVel = yyVel + dt * a_nety;
		xxPos = xxPos + dt * xxVel;
		yyPos = yyPos + dt * yyVel;
	}

	public void draw(){
		String PlanetToDraw = "images/" + imgFileName;
		StdDraw.enableDoubleBuffering();
		StdDraw.picture(xxPos, yyPos, PlanetToDraw);

		//StdDraw.show();
		//StdDraw.pause(2000);

	}
}

