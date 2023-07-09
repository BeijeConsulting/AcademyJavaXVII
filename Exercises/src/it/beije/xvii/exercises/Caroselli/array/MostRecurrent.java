package it.beije.xvii.exercises.Caroselli.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import it.beije.xvii.exercises.Caroselli.utils.TestCase;

public class MostRecurrent {

    /*
        Viene utilizzato il tipo di ritorno Integer per poter restituire null nel caso
        l'array e' vuoto.

        Se ci sono piu' numeri con la stessa occorrenza vengono restituiti in una lista.
     */

    //Scrivere il metodo: “public int mostRecurrent(int [] array)” ,
    // che trova l’elemento più ricorrente in un array. Il metodo restituisce l’elemento trovato.


	public List<Integer> mostRecurrent(int[] array) {
        if (array.length == 0) {
            return null;
        }

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int i = 0; i < array.length; i++) {
            int current = array[i];
            Integer frequency = frequencyMap.get(current);
            if (frequency == null) {
                frequencyMap.put(current, 1);
            } else {
                frequencyMap.put(current, frequency + 1);
            }
        }
        List<Map.Entry<Integer, Integer>> frequencyList = frequencyMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        int maxFrequency = frequencyList.get(frequencyList.size() - 1).getValue();
        List<Integer> frequencyResult = new ArrayList<>();

        // Aggiungo l'ultimo elemento perche' sicuramente va incluso nel risultato
        // in quanto ha la frequenza maggiore.
        frequencyResult.add(frequencyList.get(frequencyList.size() - 1).getKey());

        // frequencyList.size() - 2 perche' scarto l'ultimo elemento, e' stato gia' aggiunto
        for (int i = frequencyList.size() - 2; i >= 0; i--) {
            Map.Entry<Integer, Integer> current = frequencyList.get(i);
            if (current.getValue() == maxFrequency) {
                frequencyResult.add(current.getKey());
            } else {
                return frequencyResult;
            }
        }

        return frequencyResult;
    }

    public void runTests() {
        for (TestCase<int[], List<Integer>> test : testCases) {
            List<Integer> mostFrequent = mostRecurrent(test.getValue());
            if (mostFrequent != null) {
                System.out.println(
                        "Valore atteso: " + mostFrequent + " valore restituito: " + test.getExpectedValue()
                );
            } else {
                System.out.println("Array vuoto");
            }
        }
    }

    private final List<TestCase<int[], List<Integer>>> testCases = Arrays.asList(
            new TestCase<>(new int[]{1, 2, 3, 4, 4, 2, 2}, Arrays.asList(2)),
            new TestCase<>(new int[]{1, 2, 3, 4, 4, 2}, Arrays.asList(2, 4)),
            new TestCase<>(new int[]{}, null),
            new TestCase<>(new int[]{-1, 0, -1}, Arrays.asList(-1))
    );


}
