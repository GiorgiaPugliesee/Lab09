package it.polito.tdp.borders.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graph;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {

		private BordersDAO dao;
		private List<Country> allCountries;
		private Map<Integer, Country> idMap;
		private Graph<Country, DefaultEdge> grafo;
		
		public Model() {
			this.dao = new BordersDAO();
			this.allCountries = this.dao.loadAllCountries();
			this.idMap = new HashMap<>();
		}
		
		public void creaGrafo(int anno) {
			this.grafo = new SimpleGraph<>(DefaultEdge.class);
			
			if(this.idMap.isEmpty()) {
				for(Country c : this.allCountries) {
					idMap.put(c.getcCode(), c);
				}
			}
			
			List<Border> edges = this.dao.getCountryPairs(anno, idMap);
			for(Border bo : edges) {
				Country a = this.idMap.get(bo.getState1no());
				Country b = this.idMap.get(bo.getState2no());
				
				this.grafo.addVertex(a);
				this.grafo.addVertex(b);
				this.grafo.addEdge(a, b);
			}
			
			System.out.println("Il grafo ha " + this.grafo.vertexSet().size() +" vertici.");
			System.out.println("Il grafo ha " + this.grafo.edgeSet().size() +" archi.");
			
			for(Country c : grafo.vertexSet()) {
				System.out.println("Vertice " + c + " ha grado " + grafo.degreeOf(c));
			}
		}
		
		public Graph<Country, DefaultEdge> getGrafo() {
			return grafo;
		}

		public Integer calcolaConnessa() {
			ConnectivityInspector<Country, DefaultEdge> inspector = new ConnectivityInspector<>(this.grafo);
			return inspector.connectedSets().size();
		}
}
