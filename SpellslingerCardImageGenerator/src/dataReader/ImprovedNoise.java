package dataReader;

import java.util.Random;

/**
 * @author Raine
 * created 8/23/2022
 * @project Crowns v2
 */

public final class ImprovedNoise {

    private static int[][] savedPoints;
    int width;
    int height;

    public ImprovedNoise(int width, int height) {
    	savedPoints = new int[width + 4][height + 4];
    	this.width = width;
    	this.height = height;
    }
    
    static public double noise(double x, double y, double seed, int layers) {
        double sum = interpolate(x, y, seed, (int)Math.pow(2,0))/Math.pow(2,layers);
        for (int i = 0; i < layers; i++) {
            sum += interpolate(x, y, seed, (int)Math.pow(2,i))/Math.pow(2,layers-i);
        }
        return sum;
    }

    private static double interpolate(double x, double y, double seed, int scale){
        double internalX = x % scale;   // from 0-SCALE
        double internalY = y % scale;
        internalX /= scale; // from 0-1
        internalY /= scale;

        int leftBound = (int)(x/scale);         // bound locations to generate random heights
        int rightBound = leftBound - 1;
        int topBound = (int)(y/scale);
        int bottomBound = topBound - 1;

        double xHeightDeltaScalar = Math.sin(internalX * 3.14d/2);    // calculate locations between corners
        double yHeightDeltaScalar = Math.sin(internalY * 3.14d/2);

        double topMin = value(rightBound, topBound, seed);
        double bottomMin = value(rightBound, bottomBound, seed);
        double topSideHeight = ((value(leftBound, topBound, seed) - topMin) * xHeightDeltaScalar) + topMin;
        double bottomSideHeight = ((value(leftBound, bottomBound, seed) - bottomMin) * xHeightDeltaScalar) + bottomMin;

        double deltaTopBottom = topSideHeight - bottomSideHeight;
        return bottomSideHeight + deltaTopBottom * yHeightDeltaScalar;
    }

    private static double value(double x, double y, double seed){
        if(savedPoints[(int)x+2][(int)y+2] != 0) return savedPoints[(int)x+2][(int)y+2];

        Random random = new Random((long) (seed * 34598732654L * (234 * (int)x + (int)x + 786) * (int)y + ((int)y + 98765421)));
                   // magic numbers. doesnt actually matter what they are.
                    // should just be *some* large number
        double value = random.nextDouble();
        savedPoints[(int)x+2][(int)y+2] = (int)value;
        return value;
    }
}