/* Copyright (c) 2013 the authors listed at the following URL, and/or
   the authors of referenced articles or incorporated external code:
   http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?action=history&offset=20081113161332
   
   Permission is hereby granted, free of charge, to any person obtaining
   a copy of this software and associated documentation files (the
   "Software"), to deal in the Software without restriction, including
   without limitation the rights to use, copy, modify, merge, publish,
   distribute, sublicense, and/or sell copies of the Software, and to
   permit persons to whom the Software is furnished to do so, subject to
   the following conditions:
  
   The above copyright notice and this permission notice shall be
   included in all copies or substantial portions of the Software.
  
   THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
   EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
   MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
   IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
   CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
   TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
   SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
  
   Retrieved from: http://en.literateprograms.org/Dijkstra's_algorithm_(Java)?oldid=15444
*/

package td.map;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;

import td.Game;

class Vertex implements Comparable<Vertex> {
	public final String name;
	public List<Edge> adjacencies;
	public double minDistance = Double.POSITIVE_INFINITY;
	public Vertex previous;

	public Vertex(String argName) {
		name = argName;
	}

	public String toString() {
		return name;
	}

	public int compareTo(Vertex other) {
		return Double.compare(minDistance, other.minDistance);
	}
}

class Edge {
	public final Vertex target;
	public final double weight;

	public Edge(Vertex argTarget, double argWeight) {
		target = argTarget;
		weight = argWeight;
	}
}

public class Pathfinder {
	private List<Vertex> vertices;
	private List<Vertex> modVert;
	public List<Vertex> path;
	
	public static void computePaths(Vertex source) {
		source.minDistance = 0.;
		PriorityQueue<Vertex> vertexQueue = new PriorityQueue<Vertex>();
		vertexQueue.add(source);

		while (!vertexQueue.isEmpty()) {
			Vertex u = vertexQueue.poll();

			// Visit each edge exiting u
			for (Edge e : u.adjacencies) {
				Vertex v = e.target;
				double weight = e.weight;
				double distanceThroughU = u.minDistance + weight;
				if (distanceThroughU < v.minDistance) {
					vertexQueue.remove(v);

					v.minDistance = distanceThroughU;
					v.previous = u;
					vertexQueue.add(v);
				}
			}
		}
	}

	public static List<Vertex> getShortestPathTo(Vertex target) {
		List<Vertex> path = new ArrayList<Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous)
			path.add(vertex);

		Collections.reverse(path);
		return path;
	}
	
	// I've written everything in init()
	public void init() {
		for (int i = 0; i < Game.map.getWidth(); i++) {
			for (int k = 0; k < Game.map.getHeight(); k++) {
				Vertex v = new Vertex(new Integer(Game.map.getTile(i, k).getID()).toString());
				vertices.add(v);
			}
		}
		
		Iterator<Vertex> it = vertices.iterator();
		for (int i = 0; i < Game.map.getWidth(); i++) {
			for (int k = 0; k < Game.map.getHeight(); k++) {
				Vertex v = it.next();
				
				// Check to see if there's a tile on the left
				if (i > 0){ // using a nested if because the second if can throw undefined
					if (Game.map.getTile(i - 1, k).canPass()) { 
						int leftTileID = ((i - 1) * Game.map.getHeight()) + k;
						v.adjacencies.add(new Edge(vertices.get(leftTileID), 1));
					}
				}
				
				// Check to see if there's a tile below
				if (k > 0) {
					if (Game.map.getTile(i, k-1).canPass()) {
						int belowTileID = (i * Game.map.getHeight() + (k - 1));
						v.adjacencies.add(new Edge(vertices.get(belowTileID), 1));
					}
				}
				
				// Check to see if there's a tile on the right
				if (i < Game.map.getWidth() - 1) {
					if (Game.map.getTile(i + 1, k).canPass()) {
						int rightTileID = ((i + 1) * Game.map.getHeight() + k);
						v.adjacencies.add(new Edge(vertices.get(rightTileID), 1));
					}
				}
				
				// Check to see if there's a tile above
				if (k < Game.map.getHeight() - 1) {
					if (Game.map.getTile(i, k+1).canPass()) {
						int aboveTileID = (i * Game.map.getHeight() + (k + 1));
						v.adjacencies.add(new Edge(vertices.get(aboveTileID), 1));
					}
				}
				
				// replace the vertex already in the List with the updated one
				vertices.set((i * Game.map.getHeight()) + k, v);
			}
		}
		
		// Remove each vertex that doesn't have an edge tied to it
		// 	by adding good ones to a new List
		for (Vertex v : vertices) {
			if (v.adjacencies != null) {
				modVert.add(v);
			}
		}
		
		computePaths(modVert.get(0));
		for (Vertex v : modVert) {
			System.out.println("Distance to " + v + ": " + v.minDistance);
			path = getShortestPathTo(v);
			System.out.println("Path: " + path);
		}
	}
}