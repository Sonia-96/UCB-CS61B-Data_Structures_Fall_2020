public class TestBody {
	public static void main(String[] args) {
		Body Samh = new Body(1, 0, 0, 0, 7e5, "jupiter.gif");
		Body Aegir = new Body(3, 3, 0, 0, 8e5, "neptune.gif");
		Body Rocinante = new Body(5, -3, 0, 0, 9e6, "mars.gif");
		Body[] allBodys = {Samh, Aegir, Rocinante};

		double fX = Samh.calcNetForceExertedByX(allBodys);
		double fY = Samh.calcNetForceExertedByY(allBodys);
		System.out.println("Net force exerted on Samh in X direction: " + fX);
		System.out.println("Net force exerted on Samh in Y direction: " + fY);

		Samh.update(2, fX, fY);
		System.out.println("Samh's new velocity in X direction: " + Samh.xxVel);
		System.out.println("Samh's new velocity in Y direction: " + Samh.yyVel);
		System.out.println("Samh's new position in X direction: " + Samh.xxPos);
		System.out.println("Samh's new position in Y direction: " + Samh.yyPos);
	}
}