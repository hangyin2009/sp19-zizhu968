public class NBody{
	public static double readRadius(String s){
		In in = new In("./data/planets.txt");

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();

		return secondItemInFile;
	}

	public static Planet[] readPlanets(String s){
		In in = new In(s);

		int firstItemInFile = in.readInt();
		double secondItemInFile = in.readDouble();
		Planet[] planets = new Planet[5];

		for(int i = 0; i < 5; i++){
		planets[i] = new Planet(in.readDouble(),in.readDouble(),
			in.readDouble(),in.readDouble(),in.readDouble(),in.readString());
		
		/** dont know why this is not working???
		for(int i = 0; i < 5; i++){
			planets[i].xxPos = in.readDouble();
			planets[i].yyPos = in.readDouble();
			planets[i].xxVel = in.readDouble();
			planets[i].yyVel = in.readDouble();
			planets[i].mass = in.readDouble();
			planets[i].imgFileName = in.readString();
		}
		*/
		}
		return planets;
	}

	public static String imageToDraw = "images/starfield.jpg";

	public static void drawBackground(){
		
		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-6e11, 6e11);
		StdDraw.clear();

		StdDraw.picture(0, 0, imageToDraw);

		//StdDraw.show();
		//StdDraw.pause(2000);

		}

	public static void main(String[] args) {
		Double T = Double.parseDouble(args[0]);
		Double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Double radius = readRadius("./data/planets.txt");
		Planet[] bodies = readBodies("./data/planets.txt");

		StdDraw.enableDoubleBuffering();
						
		for(int time = 0; time < T; time += dt){
			double[] xForces = new double[bodies.length];
			double[] yForces = new double[bodies.length];

			for(int i = 0; i < bodies.length; i++){
				xForces[i] = bodies[i].calcNetForceExertedByX(bodies);
				yForces[i] = bodies[i].calcNetForceExertedByY(bodies);

				bodies[i].update(dt, xForces[i], yForces[i]);
		}
			drawBackground();

			for(int j = 0; j < bodies.length; j++){
				bodies[j].draw();
			}
			

			StdDraw.show();
			StdDraw.pause(10);

		}

		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel,
                  bodies[i].yyVel, bodies[i].mass, bodies[i].imgFileName);   
}	
	}

}















