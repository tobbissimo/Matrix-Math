package application;

import java.io.File;
import java.util.ArrayList;

import application.MatrixMath;

public class Main {
	private static int[][] distanceMatrix;
	private static int components;
	private static int[] eccentricities;
	private static ArrayList<Integer> articulations;
	
	private static void printDistanceMatrix() {
		int length = distanceMatrix.length;
		System.out.println("Distance Matrix");
		System.out.println("----------------");
		for(int row = 0; row < length; row++) {
			if(row < 10) {
				System.out.print(" " + row + " |  ");
			} else {
				System.out.print(row + " |  ");
			}
			for(int col = 0; col < length; col++) {
				if(distanceMatrix[row][col] != -1) {
					System.out.print(" ");
				}
				System.out.print(distanceMatrix[row][col]);
				
			}
			
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	private static void printEccentricities() {
		System.out.println("Eccentricities");
		System.out.println("----------------");
		for(int i = 0; i < eccentricities.length; i++) {
			System.out.print("Vertex " + i + ": ");
			if(eccentricities[i] != -1) {
				System.out.println(eccentricities[i]);
			} else {
				System.out.println("Infinite");
			}
		}
		System.out.println();
		System.out.println();
	}
	
	private static void printArticulations(ArrayList<Integer> a) {
		System.out.println("Articulations");
		System.out.println("----------------");
		for(int i : articulations) {
			System.out.println("Vertex " + i);
		}
		System.out.println();
		System.out.println();
	}
	
	private static void printCenter(ArrayList<Integer> a) {
		System.out.println("Center");
		System.out.println("----------------");
		for(int i : a) {
			System.out.println("Vertex " + i);
		}
		System.out.println();
		System.out.println();
	}
	
	private static void printRadiusDiameter(int[] a) {
		if(a[0] != 999) {
			System.out.println("Radius: " + a[0]);
		} else {
			System.out.println("Radius: Infinite");
		}
		if(a[1] != -1) {
			System.out.println("Diameter: " + a[1]);
		} else {
			System.out.println("Diameter: Infinite");
		}
		System.out.println();
	}
	
	private static void printBridges(ArrayList<String> a) {
		System.out.println("Bridges");
		System.out.println("-------------");
		for(String s : a) {
			System.out.println(s);
		}
		System.out.println();
		System.out.println();
	}
	
	private static void printComponents() {
		System.out.println("Number of Components: " + components);
		System.out.println();
	}
	
	public static void main(String[] args) throws Exception {
		try {
			MatrixMath m = new MatrixMath("100x100.txt");
			distanceMatrix = m.getDistanceMatrix();
			components = m.getComponents();
			eccentricities = m.getEccentricities();
			articulations = m.getArticulations();
			printDistanceMatrix();
			printEccentricities();
			printArticulations(m.articulations());
			printBridges(m.bridges());
			printCenter(m.center());
			printRadiusDiameter(m.radiusDiameter());
			printComponents();
		} catch(Exception e) {
			System.out.println("Error in execution: " + e.getMessage());
		}
	}
}
