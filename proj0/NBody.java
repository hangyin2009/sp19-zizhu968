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
		int num = (int) secondItemInFile;
		Planet[] planets = new Planet[num];

		for(int i = 0; i < num; i++){
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

	private static String imageToDraw = "images/starfield.jpg";

	private static void drawBackground(){
		
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
		Planet[] planets = readPlanets("./data/planets.txt");

		StdDraw.enableDoubleBuffering();
						
		for(int time = 0; time < T; time += dt){
			double[] xForces = new double[planets.length];
			double[] yForces = new double[planets.length];

			for(int i = 0; i < planets.length; i++){
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);

				planets[i].update(dt, xForces[i], yForces[i]);
		}
			drawBackground();

			for(int j = 0; j < planets.length; j++){
				planets[j].draw();
			}
			

			StdDraw.show();
			StdDraw.pause(10);

		}

		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    	StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                  planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                  planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
}	
	}

}















