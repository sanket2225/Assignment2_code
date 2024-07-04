import java.util.*;

public class TravelRouteFinder {

    static Map<String, List<String>> graph;
    static List<String> route;
    static Set<String> visited;

    public static List<String> findTravelRoute(List<String[]> tickets) {
        // Step 1: Build the graph
        graph = new HashMap<>();
        for (String[] ticket : tickets) {
            String fromCity = ticket[0];
            String toCity = ticket[1];
            graph.putIfAbsent(fromCity, new ArrayList<>());
            graph.putIfAbsent(toCity, new ArrayList<>());
            graph.get(fromCity).add(toCity);
        }

        // Step 2: Initialize variables
        route = new ArrayList<>();
        visited = new HashSet<>();

        // Step 3: Find the route using DFS starting from Kiev
        String startCity = "Kiev";
        dfs(startCity);

        return route;
    }

    private static boolean dfs(String city) {
        visited.add(city);
        route.add(city);

        // Check if all cities have been visited exactly once
        if (route.size() == graph.size() + 1) {
            return true;
        }

        // Explore neighbors
        for (String neighbor : graph.getOrDefault(city, new ArrayList<>())) {
            if (!visited.contains(neighbor)) {
                if (dfs(neighbor)) {
                    return true;
                }
            }
        }

        // Backtrack
        visited.remove(city);
        route.remove(route.size() - 1);
        return false;
    }

    public static void main(String[] args) {
        List<String[]> tickets = new ArrayList<>();
        tickets.add(new String[]{"Paris", "Skopje"});
        tickets.add(new String[]{"Zurich", "Amsterdam"});
        tickets.add(new String[]{"Prague", "Zurich"});
        tickets.add(new String[]{"Barcelona", "Berlin"});
        tickets.add(new String[]{"Kiev", "Prague"});
        tickets.add(new String[]{"Skopje", "Paris"});
        tickets.add(new String[]{"Amsterdam", "Barcelona"});
        tickets.add(new String[]{"Berlin", "Kiev"});
        tickets.add(new String[]{"Berlin", "Amsterdam"});

        List<String> route = findTravelRoute(tickets);

        if (route != null) {
            System.out.println("The route your son traveled is:");
            for (String city : route) {
                System.out.print(city + " -> ");
            }
            System.out.println("End");
        } else {
            System.out.println("No valid route found.");
        }
    }
}
