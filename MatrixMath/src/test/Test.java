package test;

import application.MatrixMath;
import test.RandomMatrix;

public class Test {
	private static int[][] testMatrix1 = {{0,1,0,0},
	   		{1,0,1,0},
	   		{0,1,0,1},
	   		{0,0,1,0}};

	private static int[][] testMatrix2 = {{0,1,1,1},
			{1,0,1,1},
			{1,1,0,0},
			{1,1,0,0}};

	private static int[][] testMatrix3 = {{0,1,1,1,0,0,0},
			  {1,0,1,0,0,0,0},
			  {1,1,0,0,1,1,0},
			  {1,0,0,0,0,0,0},
			  {0,0,1,0,0,1,0},
			  {0,0,1,0,1,0,0},
			  {0,0,0,0,0,0,0}};


	private void testMultiply() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(g.multiply(testMatrix1, testMatrix1));
		} catch (Exception e) {
			System.out.println("Error testMultiply: " + e.getMessage());
		}
	}

	public void testDistanceMatrix() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(g.distanceMatrix(testMatrix1));
		} catch (Exception e){
			System.out.println("Error testDistanceMatrix: " + e.getMessage());
		}
	}

	public void testWegMatrix() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(g.wegMatrix(testMatrix1));
		} catch (Exception e) {
			System.out.println("Error testWegMatrix: " + e.getMessage());
		}
	}

	public void testEccentricities() {
		try {
			MatrixMath g = new MatrixMath();
			g.print1d(g.eccentricities());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testRemoveEdge: " + e.getMessage());
		}
	}

	public void testCenter() {
		try {
			MatrixMath g = new MatrixMath();
			g.center();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testCenter: " + e.getMessage());
		}
	}

	public void testPrintAm() {
		try {
			MatrixMath g = new MatrixMath();
			g.print();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testPrint adjacencyMatrix : " + e.getMessage());
		}
	}

	public void testPrint() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(testMatrix1);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testPrint with parameter: " + e.getMessage());
		}
	}

	public void testComponents() {
		try {
			MatrixMath g = new MatrixMath();
			g.components(testMatrix3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testComponents: " + e.getMessage());
		}
	}

	public void testRadiusDiameter() {
		try {
			MatrixMath g = new MatrixMath();
			g.radiusDiameter();
			g.print(g.distanceMatrix(testMatrix1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testComponents: " + e.getMessage());
		}
	}

	public void testRemoveNode() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(testMatrix1);
			g.print(g.removeVertex(testMatrix1, 4));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testRemoveNode: " + e.getMessage());
		}
	}

	public void testRemoveEdge() {
		try {
			MatrixMath g = new MatrixMath();
			g.print(testMatrix1);
			g.print(g.removeEdge(testMatrix1, 0, -1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testRemoveEdge: " + e.getMessage());
		}
	}

	public void testIsBridge() {
		try {
			MatrixMath g = new MatrixMath();
			System.out.println(g.isBridge(0, 1));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testIsBridge: " + e.getMessage());
		}
	}

	private void testIsArticulation() {
		try {
			MatrixMath g = new MatrixMath();
			System.out.println(g.isArticulation(2));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testMultiply: " + e.getMessage());
		}
	}

	private void testBridges() {
		try {
			MatrixMath g = new MatrixMath();
			g.bridges();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testMultiply: " + e.getMessage());
		}
	}

	private void testArticulations() {
		try {
			MatrixMath g = new MatrixMath();
			g.articulations();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error testMultiply: " + e.getMessage());
		}
	}

	private void testImport() {
		try {
			MatrixMath g = new MatrixMath("matrix1.txt");
			g.print(g.getadjacencyMatrix());
		} catch (Exception e) {
			System.out.println("Error testImport: " + e.getMessage());		
		}

}

	private void testToString() {
		try {
			MatrixMath g = new MatrixMath("matrix1.txt");
			System.out.println(g.toString());
		} catch (Exception e) {
			System.out.println("Error testImport: " + e.getMessage());		
		}
	}

	private void randomMatrixMath() {
		RandomMatrix r = new RandomMatrix(150);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Test t = new Test();
		//t.testMultiply();
		//t.testWegMatrix();
		//t.testDistanceMatrix();
		//t.testEccentricities();
		//t.testComponents();
		//t.testRadiusDiameter();
		//t.testPrint();
		//t.testCenter();
		//t.testRemoveNode();
		//t.testRemoveEdge();
		//t.testIsArtikulation();
		//t.testBridges();
		//t.testIsBridge();
		//t.testArticulations();
		//t.testImport();
		//t.testToString();
		//t.randomMatrixMath();
	}
}
