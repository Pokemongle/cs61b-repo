public class NBody {
    public static double readRadius(String filepath){
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filepath){
        In in = new In(filepath);
        int num = in.readInt();
        double radius = in.readDouble();
        Planet[] p = new Planet[num];

        for(int i = 0;i<num;i++) {
            double xx = in.readDouble();
            double yy = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xx, yy, vx, vy, m, img);
        }

        /* the ./data/planets.txt is not empty after the data of 5 planets, so code below cannot work well*/
        /*
        int i = 0;
        while(!in.isEmpty()){
            double xx = in.readDouble();
            double yy = in.readDouble();
            double vx = in.readDouble();
            double vy = in.readDouble();
            double m = in.readDouble();
            String img = in.readString();
            p[i] = new Planet(xx, yy, vx, vy, m, img);
            i = i + 1;
        }
        */
        return p;
    }

    public static void main(String[] args){
        /* 1. Collecting all the needed input */
        double T, dt;
        String filename;
        T = Double.parseDouble(args[0]);
        dt = Double.parseDouble(args[1]);
        filename = args[2];
        double radius = NBody.readRadius(filename);
        Planet[] p = NBody.readPlanets(filename);
        /* 2. Drawing the background */
        /*
        String imgToDraw = "./images/starfield.jpg";
        StdDraw.setXscale(-radius,radius);
        StdDraw.setYscale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,imgToDraw);
        StdDraw.show();
        StdDraw.pause(1000);
         */
        /* 3. Draw all the planets */
        /*
        for(int i = 0; i < p.length; i++){
            p[i].draw();
        }
        */
        /* 4. Animation */
        StdDraw.enableDoubleBuffering();
        double t = 0;
        for(t = 0;t<T;t+=dt){
            double[] xForces = new double[p.length];
            double[] yForces = new double[p.length];
            for(int i = 0;i < p.length;i++){
                xForces[i] = p[i].calcNetForceExertedByX(p);
                yForces[i] = p[i].calcNetForceExertedByY(p);
            }
            for(int i = 0;i < p.length;i++){
                p[i].update(t,xForces[i],yForces[i]);
            }
            String imgToDraw = "./images/starfield.jpg";
            StdDraw.setXscale(-radius,radius);
            StdDraw.setYscale(-radius,radius);
            StdDraw.clear();
            StdDraw.picture(0,0,imgToDraw);
            for(int i = 0; i < p.length; i++){
                p[i].draw();
            }
            StdDraw.show();
            int waitTimeMilliseconds = 200;
            StdDraw.pause(waitTimeMilliseconds);
        }
        StdOut.printf("%d\n", p.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < p.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    p[i].xxPos, p[i].yyPos, p[i].xxVel,
                    p[i].yyVel, p[i].mass, p[i].imgFileName);
        }

    }
}
