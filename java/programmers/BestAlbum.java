package programmers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class BestAlbum {
    public int[] solution(String[] genres, int[] plays) {
        // Build the map: genre -> (index -> play count), with 10000 as total play count
        Map<String, Map<Integer, Integer>> map = new HashMap<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            map.putIfAbsent(genre, new HashMap<>());
            map.get(genre).put(i, plays[i]);
            map.get(genre).put(10000, map.get(genre).getOrDefault(10000, 0) + plays[i]);
        }

        // Sort genres by total play count (key 10000) descending
        List<Integer> result = map.entrySet().stream()
                .sorted((e1, e2) -> e2.getValue().get(10000) - e1.getValue().get(10000))
                .flatMap(e ->
                // For each genre, get up to 2 indices with highest play counts
                e.getValue().entrySet().stream().filter(entry -> entry.getKey() != 10000)
                        .sorted((a, b) -> b.getValue() - a.getValue() != 0
                                ? b.getValue() - a.getValue()
                                : a.getKey() - b.getKey())
                        .limit(2).map(Map.Entry::getKey))
                .collect(Collectors.toList());

        // Convert to int[]
        return result.stream().mapToInt(i -> i).toArray();
    }
}
