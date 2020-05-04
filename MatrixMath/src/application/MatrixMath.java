package application;

import java.io.*;
import java.util.ArrayList;

public class MatrixMath {
	private int[][] adjacencyMatrix;
	private int[][] distanceMatrix;
	private int[][] wegMatrix;
	private int[] eccentricities;
	private int components;
	private ArrayList<Integer> articulations;
	
	public MatrixMath() throws Exception{
		 adjacencyMatrix = new int[4][4];
		 adjacencyMatrix[0][0] = 0;
		 adjacencyMatrix[0][1] = 1;
		 adjacencyMatrix[0][2] = 0;
		 adjacencyMatrix[0][3] = 0;
		 adjacencyMatrix[1][0] = 1;
		 adjacencyMatrix[1][1] = 0;
		 adjacencyMatrix[1][2] = 1;
		 adjacencyMatrix[1][3] = 0;
		 adjacencyMatrix[2][0] = 0;
		 adjacencyMatrix[2][1] = 1;
		 adjacencyMatrix[2][2] = 0;
		 adjacencyMatrix[2][3] = 1;
		 adjacencyMatrix[3][0] = 0;
		 adjacencyMatrix[3][1] = 0;
		 adjacencyMatrix[3][2] = 1;
		 adjacencyMatrix[3][3] = 0;
		 
		 setDistanceMatrix();
		 setEccentricities();
		 setComponents();
		 setArticulations();
	}
	
	public MatrixMath(String filename) throws Exception {
		setAdjacencyMatrix(filename);
		setDistanceMatrix();
		setWegMatrix(adjacencyMatrix);
		setEccentricities();
		setComponents();
		setArticulations();
	}
	
	
	public int[][] importMatrix(String filename) throws Exception {
		try {
			FileReader fr = new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			String[] elements = line.split(";");
			int length = elements.length;
			adjacencyMatrix = new int[length][length];
			int row = 0;
			int col;
			boolean isSymetric = true;
			while(line != null) {
				elements = line.split(";");
				if(elements.length == length) {
					if(row < length) {
						for(col = 0; col < length; col++) {
							if(elements[col].equals("0") || elements[col].equals("1")) {	
								if(elements[row].equals("0")) {
									adjacencyMatrix[row][col] = Integer.parseInt(elements[col]);	
								} else {
									throw new Exception("No loops are allowed.");
								}
							} else {
								throw new Exception("Imported Matrix has to only contain 0's and 1's." + elements[col]);
							}
						}
					line = br.readLine();
					} else {
						throw new Exception("Matrix is not a square.");
					}
				} else {
					throw new Exception("Rows in file are of different length.");
				}
				row++;
			}
			br.close();
			fr.close();
			for(row = 0; row < length; row ++) {
				for(col = row; col < length; col ++) {
					if(adjacencyMatrix[row][col] != adjacencyMatrix[col][row]) {
						isSymetric = false;
					}
				}
			}
			if(isSymetric) {
				return adjacencyMatrix;
			} else {
				throw new Exception("Imported matrix is not a adjacency matrix");
			}
			
		} catch (FileNotFoundException e) {
			throw new Exception("File not found " + e.getMessage());
		} catch (IOException e) {
			throw new Exception("File could not be read " + e.getMessage());
		}
		
	}

	public int[][] getadjacencyMatrix() {
		return adjacencyMatrix;
	}
	
	public void setAdjacencyMatrix(String filename) throws Exception {
		if(filename != null) {
			this.adjacencyMatrix = importMatrix(filename);
		} else {
			throw new Exception("No file selected.");
		}
	}
	
	public int[][] getWegMatrix() {
		return wegMatrix;
	}
	
	public void setWegMatrix(int[][] adjacencyMatrix) throws Exception {
		if(adjacencyMatrix != null) {
			this.wegMatrix = wegMatrix(adjacencyMatrix);
		} else {
			throw new Exception("Matrix is null.");
		}
	}

	public int[][] getDistanceMatrix(){
		return distanceMatrix;
	}
	
	public void setDistanceMatrix() throws Exception {
		if(adjacencyMatrix != null) {
			this.distanceMatrix = distanceMatrix(adjacencyMatrix);
		} else {
			throw new Exception("Adjacencymatrix is null.");
		}
	}
	
	public int[] getEccentricities() {
		return eccentricities;
	}
	
	public void setEccentricities() throws Exception {
		this.eccentricities = eccentricities();
	}
	
	public int getComponents() {
		return components;
	}
	
	public void setComponents() throws Exception {
		if(adjacencyMatrix != null) {
			this.components = components(adjacencyMatrix);
		} else {
			throw new Exception("Adjacencymatrix is null.");
		}
	}
	
	public ArrayList<Integer> getArticulations(){
		return articulations;
	}
	
	public void setArticulations() throws Exception {
		this.articulations = articulations();
	}
	
	public int[][] copy(int[][] m) throws Exception {
		if(m != null) {
			int length = m.length;
			int[][] copy = new int[length][length];
			for(int row = 0; row < length; row++) {
				for(int col = row; col < length; col++) {
					copy[row][col] = m[row][col];
					copy[col][row] = m[col][row];
				}
			}
			return copy;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int[][] multiply(int[][] a, int[][] b) throws Exception {
		if(a != null && b != null) {
			if(a.length == b.length) {
				int length = a.length;
				int[][] product = new int[length][length];
				int row, col, i;
				for(row = 0; row < length; row++) {
					for(col = row; col < length; col++) {
						int sum = 0;
						for(i = 0; i < length; i++) {
							sum += a[row][i] * b[i][col];
						}
						product[row][col] = sum;
						product[col][row] = sum;
					}
				}
				//print(product);
				return product;
			} else {
				throw new Exception("Matrixes are of different length.");
			}
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int[][] wegMatrix(int[][] m) throws Exception {
		if(m != null) {
			int length = m.length;
			int[][] wm = copy(m);
			int[][] multiplied = copy(m);
			int[][] am = copy(m);
			int row, col;
			boolean routeAdded = true;
			for(row = 0; row < length; row++) {
				wm[row][row] = 1;
			}
			while(routeAdded) {
				routeAdded = false;
				multiplied = multiply(am, multiplied);
				for(row = 0; row < length; row++) {
					for(col = row; col < length; col++) {
						if(wm[row][col] == 0 && multiplied[row][col] != 0) {
							wm[row][col] = 1;
							wm[col][row] = 1;
							routeAdded = true;
						}
					}
				}
			}
			//print(wm);
			return wm;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int[][] distanceMatrix(int[][] m) throws Exception {
		if(m != null) {
			int length = m.length;
			int[][] dm = copy(m);
			int[][] am = copy(m);
			int[][] wm = wegMatrix(am);
			int[][] multiplied = copy(am);
			int row, col;
			boolean routeAdded = true;
			int k = 2;
			while(routeAdded) {
				routeAdded = false;
				multiplied = multiply(am, multiplied);
				for(row = 0; row < length; row++) {
					for(col = row; col < length; col++) {
						if(wm[row][col] != 0) {
							if(row !=  col) {
								if(dm[row][col] == 0 && multiplied[row][col] != 0) {
									dm[row][col] = k;
									dm[col][row] = k;
									routeAdded = true;
								}
							}
						} else {
							dm[row][col] = -1;
							dm[col][row] = -1;
						}
					}
				}
				k++;
			}
			//print(dm);
			return dm;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int[] eccentricities() throws Exception {
		if(distanceMatrix != null) {
			int length = distanceMatrix.length;
			int[] e = new int[length];
			int[][] dm = copy(distanceMatrix);
			//dm = distanceMatrix(m);
			int ecc;
			int row, col;
			ecc = -1;
			for(row = 0; row < length; row++) {
				ecc = -1;
				for(col = 0; col < length; col++) {
					if(row != col) {
						if(dm[row][col]  > ecc) {
							ecc = dm[row][col];
						}
					}
				}
				e[row] = ecc;
			}
			return e;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public ArrayList<Integer> center() throws Exception {
		if(eccentricities != null) {
			ArrayList<Integer> center = new ArrayList<>();
			//int[] e = eccentricities;
			int ecc = 999;
			for(int i = 0; i < eccentricities.length; i++) {
				if(eccentricities[i] < ecc && eccentricities[i] != 0 && eccentricities[i] != -1) {
					ecc = eccentricities[i];
				}
			}
			for(int i = 0; i < eccentricities.length; i++) {
				if(eccentricities[i] == ecc) {
					center.add(i);
				}
			}
			/*for(int i: center) {
				System.out.println(i);
			}*/
			return center;
		} else {
			throw new Exception("Eccentricities is null.");
		}
	}
	
	public int[] radiusDiameter() throws Exception {
		if(eccentricities != null) {
			int [] rd = new int[2];
			int radius, diameter;
			radius = 999;
			diameter = 0;
			boolean hasInfinite = false;
			for(int i = 0; i < eccentricities.length; i++) {
				if(radius > eccentricities[i] && eccentricities[i] != 0 && eccentricities[i] != -1) {
					radius = eccentricities[i];
				}
				if(diameter < eccentricities[i]) {
					diameter = eccentricities[i];
				}
				if(eccentricities[i] == -1) {
					hasInfinite = true;
				}
			}
			rd[0] = radius;
			if(hasInfinite) {
				rd[1] = -1;
			} else {
				rd[1] = diameter;
			}
			return rd;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int components(int[][] m) throws Exception {
		if(m != null) {
			int length = m.length;
			int[][] wm = copy(m);
			wm = wegMatrix(wm);
			ArrayList<String> components = new ArrayList<>();
			int anz = 0;
			for(int i = 0; i < length; i++) {
				String line = "";
				for(int j = 0; j < length; j++) {
					line += Integer.toString(wm[i][j]);
				}
				if(!components.contains(line)) {
					components.add(line);
				}
			}
			anz = components.size();
			/*for(String s: components) {
				System.out.println(s);
			}
			System.out.println(anz);*/
			return anz;
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public int[][] removeVertex(int[][] m, int vertex) throws Exception {
		if(m != null) {
			int rows =m.length-1;
			int columns = m.length-1;
			int[][] removing = copy(m);
			int[][] removed = new int[rows][columns];
			if(vertex < m.length && vertex >= 0) {
				for(int row = 0; row < m.length; row++) {
					for(int col = vertex; col < m.length-1; col++) {
						removing[row][col] = removing[row][col+1];
					}
				}
				for(int col = 0; col < m.length; col++) {
					for(int row = vertex; row < m.length-1; row++) {
						removing[row][col] = removing[row+1][col];
					}
				}
				for(int row = 0; row < rows; row++) {
					for(int col = row; col < columns; col++) {
						removed[row][col] = removing[row][col];
						removed[col][row] = removing[col][row];
					}
				}
				//print(am);
				//print(removed);
				return removed;
			} else {
				throw new Exception("Vertex does not exist in this graph.");
			}
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	public boolean isArticulation(int vertex) throws Exception {
		if(adjacencyMatrix != null) {
			int[][] removed = copy(adjacencyMatrix);
			int[][] a = copy(adjacencyMatrix);
			removed = removeVertex(removed, vertex);
			int comp = components(wegMatrix(removed));
			boolean artikulation = false;
			if(vertex < a.length && vertex >= 0) {
				if(comp > components) {
					artikulation = true;
				} 
			} else {
				throw new Exception("Vertex does not exist in this graph.");
			}
			//System.out.println(artikulation);
			return artikulation;
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	public ArrayList<Integer> articulations() throws Exception {
		if(adjacencyMatrix != null) {
			ArrayList<Integer> articulations = new ArrayList<>();
			for(int vertex = 0; vertex < adjacencyMatrix.length; vertex++) {
				if(isArticulation(vertex)) {
					articulations.add(vertex);
				}	
			}
			/*for(int i: articulations) {
				System.out.println(i);
			}*/
			return articulations;
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	public int[][] removeEdge(int[][] m, int from, int to) throws Exception {
		if(m != null) {
			int[][] removed = copy(m);
			if(from < removed.length && to < removed.length && from >= 0 && to >= 0) {
				if(m[from][to] == 1) {
					if(from != to) {
						removed[from][to] = 0;
						removed[to][from] = 0;
					} 
				}
			} else {
				throw new Exception("Vertex does not exist in this graph.");
			}
			//print(removed);
			return removed;
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	public boolean isBridge(int from, int to) throws Exception {
		if(adjacencyMatrix != null) {
			int length = adjacencyMatrix.length;
			if(from < length && to < length && from >= 0 && to >= 0) {
				int[][] removed = copy(adjacencyMatrix);
				//int[][] am = copy(adjacencyMatrix);
				boolean isBridge = false;
				removed = removeEdge(removed, from, to);
				removed = wegMatrix(removed); 
				int comp = components(removed);
				if(comp == components) {
					isBridge = false;
				} else {
					isBridge = true;
				}
				//System.out.println(isBridge);
				return isBridge;
			} else {
				throw new Exception("One or both Vertex's do not exist in this graph.");
			}
			
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	public ArrayList<String> bridges() throws Exception {
		if(adjacencyMatrix != null) {
			ArrayList<String> bridges = new ArrayList<>();
			int length = adjacencyMatrix.length;
			for(int row = 0; row < length; row++) {
				for(int col = row; col < length; col++) {
					if(articulations.contains(row) || articulations.contains(col)) {
						if(isBridge(row, col)) {
							String bridge = "";
							bridge = "From vertex " + row + " to " + col;
							bridges.add(bridge);
						}
					}
				}
			}
			/*for(int[] i: bridges) {
				print1d(i);
				System.out.println();
			}*/
			return bridges;
		} else {
			throw new Exception("Matrix is null");
		}
	}
	
	@Override
	public String toString() {
		String s = "";
		int length = adjacencyMatrix.length;
		for(int row = 0; row < length; row++) {
			for(int col = 0; col < length; col++) {
				s += adjacencyMatrix[row][col];
			}
			s+= "\n";
		}
		return s;
	}
	
	public void print() {
		System.out.println(this);
	}
	
	public void print(int[][] m) throws Exception {
		if(m != null) {
			for(int row = 0; row < m.length; row++) {
				for(int col = 0; col < m.length; col++) {
					System.out.print(m[row][col]);
					System.out.print(" ");
				}
				System.out.println();
			}
			System.out.println("-----------");
		} else {
			throw new Exception("Matrix is null.");
		}
	}
	
	public void print1d(int[] a) {
		for(int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
}
