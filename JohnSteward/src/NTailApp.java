/*
 * Class: CSCI 2410 – Data Structures and Algorithms
 * Name: John Stewart
 * Description: Solving the N tail problem in N^3 time
 */

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;
import javax.swing.border.LineBorder;

public class NTailApp extends JApplet {
  // Create the initial board

  private static final int N = 6; // Gridsize
  // Invalid for N = 4, 5, 9, 11, 14, 16, 17, 19, 23, 24, 29

  /** An inner class for displaying a node on a panel */
  static final class NodePanel extends JPanel {

    MultiSolve multisolve = new MultiSolve("NTailSolutionSet" + N + ".txt"); // MultiSolve variation
    ClickableCell[][] clickableCells = new ClickableCell[N][N];

    public NodePanel() {
      this.setLayout(new GridLayout(N, N));
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          add(clickableCells[i][j] = new ClickableCell("T"));
        }
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          List<ClickableCell> neighbors = new ArrayList<ClickableCell>();
          neighbors.add(clickableCells[i][j]);
          if (i > 0) {
            neighbors.add(clickableCells[i - 1][j]);
          }
          if (j > 0) {
            neighbors.add(clickableCells[i][j - 1]);
          }
          if (i < N - 1) {
            neighbors.add(clickableCells[i + 1][j]);
          }
          if (j < N - 1) {
            neighbors.add(clickableCells[i][j + 1]);
          }
          clickableCells[i][j].assignNeighbors(neighbors);
        }
      }
      updateNodePanel();
    }

    public void updateNodePanel() {
      int[] solution = multisolve.solve(getNode()); // MultiSolve variation
      if (solution == null) {
        System.out.println("No Solution");
      }
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          clickableCells[i][j].setColor(solution[N * i + j]);
        }
      }
      repaint();
    }

    public char[] getNode() {
      char[] node = new char[N * N];
      int k = 0;
      for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
          node[k++] = clickableCells[i][j].getText().charAt(0);
        }
      }

      return node;
    }

    /** An inner class for displaying a cell */
    static class Cell extends JLabel {

      public Cell(String s) {
        this.setOpaque(true);
        this.setBorder(new LineBorder(Color.black, 1)); // Cell border
        this.setBackground(Color.WHITE);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setFont(new Font("TimesRoman", Font.BOLD, 20));
        this.setPreferredSize(new Dimension(20, 20));
        setText(s);
      }

      public void setColor(int value) {
        Color color1, color2;
//		if (value == 1) {
//		    if (getText().charAt(0) == 'H') {
//			color1 = new Color(239, 239, 127);
//		    } else color1 = new Color(15, 239, 127);
//		} else {
//		    if (getText().charAt(0) == 'H') {
//			color1 = new Color(239, 15, 127);
//		    } else color1 = new Color(15, 15, 127);
//		}
//		color2 = color1.darker();
        if (value == 0) {
          color1 = Color.WHITE;
          color2 = Color.GRAY;
        } else {
          color1 = Color.LIGHT_GRAY;
          color2 = Color.BLACK;
        }
        this.setForeground(color2);
        this.setBorder(new LineBorder(color2, 1));
        this.setBackground(color1);
      }
    }

    /** An inner class for displaying a clickable cell */
    class ClickableCell extends Cell {

      List<ClickableCell> adjacents;

      public ClickableCell(String s) {
        super(s);
        addMouseListener(new MouseAdapter() {

          @Override
          public void mouseClicked(MouseEvent e) {
            if (e.getButton() == 1) {
              for (ClickableCell adj : adjacents) {
                adj.flipCell();
              }
              updateNodePanel();
            } else if (e.getButton() == 3) {
              flipCell();
              updateNodePanel();
            }
          }
        });
      }

      public void flipCell() {
        if (getText().equals("H")) {
          setText("T"); // Flip from H to T
        } else {
          setText("H"); // Flip from T to H
        }
      }

      public void assignNeighbors(List<ClickableCell> neighbors) {
        adjacents = neighbors;
      }
    }
  }

  static class MultiSolve {

    int[][] set = new int[N][N * N];
    private NTailModel solver = new NTailModel(N);

    public MultiSolve() {
      findSet();
    }

    public MultiSolve(String filename) {
      File file = new File(filename);
      if (file.exists()) {
        try {
          Scanner input = new Scanner(file);
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N * N; j++) {
              set[i][j] = input.nextInt();
            }
          }
        } catch (FileNotFoundException ex) {
          System.out.println("Exception: " + filename + " not found");
          findSet();
        }
      } else {
        findSet();
        try {
          PrintWriter output = new PrintWriter(file);
          for (int i = 0; i < N; i++) {
            for (int j = 0; j < N * N; j++) {
              output.print(set[i][j] + " ");
              if (j % N == N - 1) {
                output.println();
              }
            }
            output.println();
          }
          output.close();
        } catch (FileNotFoundException ex) {
          System.out.println("Exception: " + filename + " not found");
          findSet();
        }
      }
    }

    private void findSet() {
      char[] problem = new char[set[0].length];
      for (int k = 0; k < N * N; k++) {
        problem[k] = 'T';
      }
      for (int i = 0; i <= (N - 1) / 2; i++) {
        int k = N * (N - 1) + i;
        problem[k - 1] = 'T';
        problem[k] = 'H';
        set[i] = solver.solve(problem);
        if (set[i] == null) {
          System.out.println("Invalid Dimension: " + N);
          System.exit(0);
        }
        for (int m = 0; m < N; m++) {
          for (int n = 0; n < N; n++) {
            set[N - 1 - i][N * m + n] = set[i][N * m + N - 1 - n]; // Symetries
          }
        }
      }
    }

    public int[] solve(char[] problem) {
      int[] solution = new int[set[0].length];
      presolve(problem, solution);
      for (int i = 0; i < set.length; i++) {
        int k = N * (N - 1) + i;
        if (problem[k] == 'H') {
          for (int j = 0; j < set[i].length; j++) {
            solution[j] += set[i][j];
          }
        }
        for (int j = 0; j < set[i].length; j++) {
          solution[j] %= 2;
        }
      }
      return solution;
    }

    // Reduces the problem down to the last row
    private void presolve(char[] problem, int[] solution) {
      for (int i = 0; i < N * (N - 1); i++) {
        if (problem[i] == 'H') {
          solution[i + N] = 1;
          flip(problem, i);
          flip(problem, i + N);
          if (i % N > 0) {
            flip(problem, i + N - 1);
          }
          if (i % N < N - 1) {
            flip(problem, i + N + 1);
          }
          if (i / N < N - 2) {
            flip(problem, i + 2 * N);
          }
        }
      }
    }

    private void flip(char[] problem, int i) {
      if (problem[i] == 'H') {
        problem[i] = 'T';
      } else {
        problem[i] = 'H';
      }
    }
  }

  /** This main method enables the applet to run as an application */
  public static void main(String[] args) {
    // Create a frame
    JFrame frame = new JFrame("N Tail Problem");

    // Add the applet instance to the frame
    frame.add(new NodePanel(), BorderLayout.CENTER);

    // Display the frame
    frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
  }
}

class NTailModel {

  public static int N;
  UnweightedGraph<Integer> graph;

  /** Construct a model */
  public NTailModel(int n) {
    N = n;
    // Create edges
    List<AbstractGraph<Integer>.Edge> edges = getEdges();

    // Create a graph
    graph = new UnweightedGraph<Integer>(edges, N * N);
  }

  /** Create all edges for the graph */
  private List<AbstractGraph<Integer>.Edge> getEdges() {
    List<AbstractGraph<Integer>.Edge> edges = new ArrayList<AbstractGraph<Integer>.Edge>(); // Store edges

    UnweightedGraph x = new UnweightedGraph();
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N - 1; j++) {
        // Creates edges linking each node to the adjacent horizontal and vertical squares
        edges.add(x.new Edge(N * i + j, N * i + j + 1));
        edges.add(x.new Edge(N * i + j + 1, N * i + j));
        edges.add(x.new Edge(N * j + i, N * (j + 1) + i));
        edges.add(x.new Edge(N * (j + 1) + i, N * j + i));
      }
    }
    for (int i = 0; i < N * N; i++) {
      edges.add(x.new Edge(i, i));
    }

    return edges;
  }

  public int[] solve(char[] problem) {
    setProblem(problem);

    // The squares to be flipped; -1: uninitialized, 0: unflipped, 1: flipped
    int[] moves = new int[graph.size()];
    for (int i = 0; i < graph.size(); i++) {
      moves[i] = -1;
    }
    return A(moves, 0);
  }

  // Changes the graph to the current setup
  private void setProblem(char[] problem) {
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        int k = N * i + j;
        if (problem[k] == 'T') {
          graph.setVertex(k, 1);
        } else {
          graph.setVertex(k, 0);
        }
      }
    }
  }

  // First stage of recursion: sets stage and the termination condition
  private int[] A(int[] moves, int i) {
    if (i >= moves.length) {
      return moves;
    }
    List<Integer> neighbors = graph.getNeighbors(i);
    int[] sides = new int[neighbors.size()];
    for (int j = 0; j < neighbors.size(); j++) {
      sides[j] = neighbors.get(j);
    }
    return B(moves, i, sides, 0);
  }

  // Second stage of recursion: Finds all permutations, and checks validity while in the loop
  private int[] B(int[] moves, int i, int[] sides, int j) {
    if (j < sides.length) {
      if (moves[sides[j]] < 0) { // checks if the current cell is uninitiallized
        int[] newMoves = moves.clone();
        newMoves[sides[j]] = 1;
        int[] solution = B(newMoves, i, sides, j + 1);
        // Shifts for the correct solution
        if (solution != null) {
          return solution;
        } else {
          newMoves[sides[j]] = 0;
          return B(newMoves, i, sides, j + 1);
        }
      } else {
        return B(moves, i, sides, j + 1);
      }
    } else if (equate(moves, i, sides)) {
      return A(moves, i + 1); // Repeats
    } else {
      return null;
    }
  }

  // Checks the current cell for consistancy with the solution
  private boolean equate(int[] moves, int i, int[] sides) {
    int sum = graph.getVertex(i);
    for (int j = 0; j < sides.length; j++) {
      sum += moves[sides[j]];
    }
    return sum % 2 == 1;
  }

  interface Graph<V> {

    /** Return the number of vertices in the graph */
    public int getSize();

    /** Return the vertices in the graph */
    public java.util.List<V> getVertices();

    /** Return the object for the specified vertex index */
    public V getVertex(int index);

    /** Return the index for the specified vertex object */
    public int getIndex(V v);

    /** Return the neighbors of vertex with the specified index */
    public java.util.List<Integer> getNeighbors(int index);

    /** Return the degree for a specified vertex */
    public int getDegree(int v);

    /** Print the edges */
    public void printEdges();

    /** Clear graph */
    public void clear();

    /** Add a vertex to the graph */
    public void addVertex(V vertex);

    /** changes the value stored in a vertex */
    public void setVertex(int k, V i);

    /** Add an edge to the graph */
    public void addEdge(int u, int v);

    /** Returns the number of vertexes */
    public int size();

    /** Obtain a depth-first search tree */
    public AbstractGraph<V>.Tree dfs(int v);

    /** Obtain a breadth-first search tree */
    public AbstractGraph<V>.Tree bfs(int v);

    /** Determine if the graph is connected */
    public boolean isConnected();
  }

  abstract class AbstractGraph<V> implements Graph<V> {

    protected List<V> vertices = new ArrayList<V>(); // Store vertices
    protected List<List<Integer>> neighbors = new ArrayList<List<Integer>>(); // Adjacency lists

    /** Construct an empty graph */
    protected AbstractGraph() {
    }

    /** Construct a graph from edges and vertices stored in arrays */
    protected AbstractGraph(int[][] edges, V[] vertices) {
      this.vertices.addAll(Arrays.asList(vertices));

      createAdjacencyLists(edges, vertices.length);
    }

    /** Construct a graph from edges and vertices stored in List */
    protected AbstractGraph(List<Edge> edges, List<V> vertices) {
      for (int i = 0; i < vertices.size(); i++) {
        this.vertices.add(vertices.get(i));
      }

      createAdjacencyLists(edges, vertices.size());
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
      for (int i = 0; i < numberOfVertices; i++) {
        vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}
      }
      createAdjacencyLists(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    protected AbstractGraph(int[][] edges, int numberOfVertices) {
      for (int i = 0; i < numberOfVertices; i++) {
        vertices.add((V) (new Integer(i))); // vertices is {0, 1, ...}
      }
      createAdjacencyLists(edges, numberOfVertices);
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
            int[][] edges, int numberOfVertices) {
      // Create a linked list
      for (int i = 0; i < numberOfVertices; i++) {
        neighbors.add(new ArrayList<Integer>());
      }

      for (int i = 0; i < edges.length; i++) {
        int u = edges[i][0];
        int v = edges[i][1];
        neighbors.get(u).add(v);
      }
    }

    /** Create adjacency lists for each vertex */
    private void createAdjacencyLists(
            List<Edge> edges, int numberOfVertices) {
      // Create a linked list for each vertex
      for (int i = 0; i < numberOfVertices; i++) {
        neighbors.add(new ArrayList<Integer>());
      }

      for (Edge edge : edges) {
        neighbors.get(edge.u).add(edge.v);
      }
    }

    @Override
    /** Return the number of vertices in the graph */
    public int getSize() {
      return vertices.size();
    }

    @Override
    /** Return the vertices in the graph */
    public List<V> getVertices() {
      return vertices;
    }

    @Override
    /** Return the object for the specified vertex */
    public V getVertex(int index) {
      return vertices.get(index);
    }

    @Override
    /** Return the index for the specified vertex object */
    public int getIndex(V v) {
      return vertices.indexOf(v);
    }

    @Override
    /** Return the neighbors of the specified vertex */
    public List<Integer> getNeighbors(int index) {
      return neighbors.get(index);
    }

    @Override
    /** Return the degree for a specified vertex */
    public int getDegree(int v) {
      return neighbors.get(v).size();
    }

    @Override
    /** Print the edges */
    public void printEdges() {
      for (int u = 0; u < neighbors.size(); u++) {
        System.out.print(getVertex(u) + " (" + u + "): ");
        for (int j = 0; j < neighbors.get(u).size(); j++) {
          System.out.print("(" + u + ", "
                  + neighbors.get(u).get(j) + ") ");
        }
        System.out.println();
      }
    }

    @Override
    /** Clear graph */
    public void clear() {
      vertices.clear();
      neighbors.clear();
    }

    @Override
    /** Add a vertex to the graph */
    public void addVertex(V vertex) {
      vertices.add(vertex);
      neighbors.add(new ArrayList<Integer>());
    }

    @Override
    /** changes the value stored in a vertex */
    public void setVertex(int k, V i) {
      vertices.set(k, i);
    }

    @Override
    /** Add an edge to the graph */
    public void addEdge(int u, int v) {
      neighbors.get(u).add(v);
      neighbors.get(v).add(u);
    }

    /** Edge inner class inside the AbstractGraph class */
    public class Edge {

      public int u; // Starting vertex of the edge
      public int v; // Ending vertex of the edge

      /** Construct an edge for (u, v) */
      public Edge(int u, int v) {
        this.u = u;
        this.v = v;
      }
    }

    /** Returns the number of vertexes */
    @Override
    public int size() {
      return vertices.size();
    }

    @Override
    /** Obtain a DFS tree starting from vertex v */
    /** To be discussed in Section 27.6 */
    public Tree dfs(int v) {
      List<Integer> searchOrder = new ArrayList<Integer>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      // Mark visited vertices
      boolean[] isVisited = new boolean[vertices.size()];

      // Recursively search
      dfs(v, parent, searchOrder, isVisited);

      // Return a search tree
      return new Tree(v, parent, searchOrder);
    }

    /** Recursive method for DFS search */
    private void dfs(int v, int[] parent, List<Integer> searchOrder,
            boolean[] isVisited) {
      // Store the visited vertex
      searchOrder.add(v);
      isVisited[v] = true; // Vertex v visited

      for (int i : neighbors.get(v)) {
        if (!isVisited[i]) {
          parent[i] = v; // The parent of vertex i is v
          dfs(i, parent, searchOrder, isVisited); // Recursive search
        }
      }
    }

    @Override
    /** Starting bfs search from vertex v */
    /** To be discussed in Section 27.7 */
    public Tree bfs(int v) {
      List<Integer> searchOrder = new ArrayList<Integer>();
      int[] parent = new int[vertices.size()];
      for (int i = 0; i < parent.length; i++) {
        parent[i] = -1; // Initialize parent[i] to -1
      }
      java.util.LinkedList<Integer> queue =
              new java.util.LinkedList<Integer>(); // list used as a queue
      boolean[] isVisited = new boolean[vertices.size()];
      queue.offer(v); // Enqueue v
      isVisited[v] = true; // Mark it visited

      while (!queue.isEmpty()) {
        int u = queue.poll(); // Dequeue to u
        searchOrder.add(u); // u searched
        for (int w : neighbors.get(u)) {
          if (!isVisited[w]) {
            queue.offer(w); // Enqueue w
            parent[w] = u; // The parent of w is u
            isVisited[w] = true; // Mark it visited
          }
        }
      }

      return new Tree(v, parent, searchOrder);
    }

    /** Tree inner class inside the AbstractGraph class */
    /** To be discussed in Section 27.5 */
    public class Tree {

      private int root; // The root of the tree
      private int[] parent; // Store the parent of each vertex
      private List<Integer> searchOrder; // Store the search order

      /** Construct a tree with root, parent, and searchOrder */
      public Tree(int root, int[] parent, List<Integer> searchOrder) {
        this.root = root;
        this.parent = parent;
        this.searchOrder = searchOrder;
      }

      /** Return the root of the tree */
      public int getRoot() {
        return root;
      }

      /** Return the parent of vertex v */
      public int getParent(int v) {
        return parent[v];
      }

      /** Return an array representing search order */
      public List<Integer> getSearchOrder() {
        return searchOrder;
      }

      /** Return number of vertices found */
      public int getNumberOfVerticesFound() {
        return searchOrder.size();
      }

      /** Return the path of vertices from a vertex to the root */
      public List<V> getPath(int index) {
        ArrayList<V> path = new ArrayList<V>();

        do {
          path.add(vertices.get(index));
          index = parent[index];
        } while (index != -1);

        return path;
      }

      /** Print a path from the root to vertex v */
      public void printPath(int index) {
        List<V> path = getPath(index);
        System.out.print("A path from " + vertices.get(root) + " to "
                + vertices.get(index) + ": ");
        for (int i = path.size() - 1; i >= 0; i--) {
          System.out.print(path.get(i) + " ");
        }
      }

      /** Print the whole tree */
      public void printTree() {
        System.out.println("Root is: " + vertices.get(root));
        System.out.print("Edges: ");
        for (int i = 0; i < parent.length; i++) {
          if (parent[i] != -1) {
            // Display an edge
            System.out.print("(" + vertices.get(parent[i]) + ", "
                    + vertices.get(i) + ") ");
          }
        }
        System.out.println();
      }
    }

    @Override
    /** Determine if the graph is connected */
    public boolean isConnected() {
      return vertices.size() == dfs(0).getNumberOfVerticesFound();
    }
  }

  class UnweightedGraph<V> extends AbstractGraph<V> {

    /** Construct an empty graph */
    public UnweightedGraph() {
    }

    /** Construct a graph from edges and vertices stored in arrays */
    public UnweightedGraph(int[][] edges, V[] vertices) {
      super(edges, vertices);
    }

    /** Construct a graph from edges and vertices stored in List */
    public UnweightedGraph(List<Edge> edges, List<V> vertices) {
      super(edges, vertices);
    }

    /** Construct a graph for integer vertices 0, 1, 2 and edge list */
    public UnweightedGraph(List<Edge> edges, int numberOfVertices) {
      super(edges, numberOfVertices);
    }

    /** Construct a graph from integer vertices 0, 1, and edge array */
    public UnweightedGraph(int[][] edges, int numberOfVertices) {
      super(edges, numberOfVertices);
    }
  }
}
