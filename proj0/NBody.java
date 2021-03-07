public class NBody {
	public static double readRadius(String planetsTxtPath) {
		In in = new In(planetsTxtPath);
		int num = in.readInt();
		double universe_radius = in.readDouble();
		return universe_radius;
	}

	public static Body[] readBodies(String planetsTxtPath) {
		In in = new In(planetsTxtPath);
		int num = in.readInt();
		double universe_radius = in.readDouble();
		Body[] allBodys = new Body[num];
		for (int i = 0; i < num; i++) {
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = "images/" + in.readString();
			allBodys[i] = new Body(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return allBodys;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String fileName = args[2];
		double universe_radius = readRadius(fileName);
		Body[] allBodys = readBodies(fileName);
		String universe = "images/starfield.jpg";

		
		StdDraw.setScale(-universe_radius, universe_radius);
		StdDraw.picture(0, 0, universe);
		for (Body body : allBodys) {
			body.draw();
		}

		StdDraw.enableDoubleBuffering();
		StdAudio.play("audio/2001.mid");
		double t = 0;
		while (t <= T) {
			double[] xForces = new double[allBodys.length];
			double[] yForces = new double[allBodys.length];
			for (int i = 0; i < allBodys.length; i++) {
				xForces[i] = allBodys[i].calcNetForceExertedByX(allBodys);
				yForces[i] = allBodys[i].calcNetForceExertedByY(allBodys);
			}
			for (int i = 0; i < allBodys.length; i++) {
				allBodys[i].update(dt, xForces[i], yForces[i]);
			}
			StdDraw.picture(0, 0, universe);
			for (Body body : allBodys) {
				body.draw();
			}
			StdDraw.show();
			StdDraw.pause(10);
			t += dt;
		}

		StdOut.printf("%d\n", allBodys.length);
		StdOut.printf("%.2e\n", universe_radius);
		for (Body body: allBodys) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
				body.xxPos, body.yyPos, body.xxVel, body.yyVel, body.mass, body.imgFileName);
		}
	}
}