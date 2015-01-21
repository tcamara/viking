package com.timcamara.viking;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class World {
	public int[][] map_heights;
	
	public World() {
		map_heights = generate_map_heights();
	}
	
	public Vector2 get_map_dimensions() {
		int longest_y = 0;
		
		for(int x = 0; x < map_heights.length; x++){
			if(map_heights[x].length > longest_y){
				longest_y = map_heights[x].length;
			}
		}
		
		return new Vector2(map_heights.length, longest_y);
	}
	
	private int[][] generate_map_heights() {
		int n = 7;
		int wmult = 6;
		int hmult = 4;
		float smoothness = 1.8f;
		
		float[][] map;
		int[][] return_map;
		float sum;
		int count;
		float h = 1;
		float max = Float.MIN_VALUE;
		float min = Float.MAX_VALUE;
		
		// Thresholds
		float water_deep_threshold = 0.50f;
		float water_shallow_threshold = 0.55f;
		float beach_threshold = 0.60f;
//		float grass_threshold = 0.62f;
		
		int power = (int) Math.pow(2, n);
		int width = wmult * power + 1;
		int height = hmult * power + 1;
		int step = power/2;
		
		map = new float[width][height];
		return_map = new int[width][height];
		
		// Initialize the grid points
		for (int i = 0; i < width; i += 2 * step) {
			for (int j = 0; j < height; j += 2 * step) {
				map[i][j] = MathUtils.random(2*h);
			}
		}
		
		// Do the rest of the magic
		while (step > 0) {   
			// Diamond step
			for (int x = step; x < width; x+=2*step) {
				for (int y = step; y < height; y+=2*step) {
					sum = map[x-step][y-step] + //down-left
					map[x-step][y+step] + //up-left
					map[x+step][y-step] + //down-right
					map[x+step][y+step];  //up-right
					map[x][y] = sum/4 + MathUtils.random(-h,h);
				}
			}
			
			// Square step
			for (int x = 0; x < width; x+=step) {
				for (int y = step*(1-(x/step)%2); y<height; y+=2*step) {
					sum = 0;
					count = 0;
					if (x-step >= 0) {
						sum+=map[x-step][y];
						count++;
					}
					if (x+step < width) {
						sum+=map[x+step][y];
						count++;
					}
					if (y-step >= 0) {
						sum+=map[x][y-step];
						count++;
					}
					if (y+step < height) {
						sum+=map[x][y+step];
						count++;
					}
					if (count > 0) {
						map[x][y] = sum/count + MathUtils.random(-h,h);
					}
					else {
						map[x][y] = 0;
					}
				}
			}
			h /= smoothness;
			step /= 2;
		}
		
		// Normalize the map
		for (float[] row : map) {
			for (float d : row) {
				if (d > max) max = d;
				if (d < min) min = d;
			}
		}
		
		// Use the thresholds to fill in the return map
		for(int row = 0; row < map.length; row++){
			for(int col = 0; col < map[row].length; col++){
				map[row][col] = (map[row][col]-min)/(max-min);
				if (map[row][col] < water_deep_threshold)
					return_map[row][col] = 0;
				else if (map[row][col] < water_shallow_threshold)
					return_map[row][col] = 1;
				else if (map[row][col] < beach_threshold)
					return_map[row][col] = 2;
				else
					return_map[row][col] = 3;
			}
		}
		
		return return_map;
	}
	
}
