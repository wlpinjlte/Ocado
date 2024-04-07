# Basket Splitter

Basket Splitter jest aplikacją Java, która dzieli koszyk produktów na dostawy na podstawie dostępnej konfiguracji.

## Opis kodu

Klasa `BasketSplitter` zawiera metodę `split`, która przyjmuje listę produktów i dzieli je na dostawy zgodnie z konfiguracją. Oto krótki opis poszczególnych części kodu:

### Metoda `split`

- Metoda `split` jest główną metodą klasy `BasketSplitter`, która wykonuje proces dzielenia koszyka na dostawy.
- Wykorzystuje ona metody prywatne klasy do parsowania pliku konfiguracyjnego, znajdowania najlepszego rozwiązania dostawy oraz ostatecznego tworzenia rozwiązania.

### Metoda `praseConfigFile`

- Metoda `praseConfigFile` służy do parsowania pliku konfiguracyjnego zawierającego mapowanie produktów do dostaw.
- Wykorzystuje klasę `ConfigParser` do parsowania pliku JSON.

### Metoda `getDeliversList`

- Metoda `getDeliversList` pobiera listę dostaw, które muszą być wykonane na podstawie listy produktów.
- Wykorzystuje mapowanie z pliku konfiguracyjnego.

### Metoda `recursionFunction`

- Metoda `recursionFunction` jest funkcją rekurencyjną używaną do znalezienia najlepszego rozwiązania dostawy.
- Próbując różnych kombinacji dostaw, znajduje optymalne rozwiązanie.

### Metoda `getFinalSolution`

- Metoda `getFinalSolution` tworzy ostateczne rozwiązanie, grupując produkty według dostaw.

### Metoda `getDeliversSolution`

- Metoda `getDeliversSolution` znajduje optymalną listę dostaw na podstawie listy produktów i mapowania z pliku konfiguracyjnego.

## Wymagania

- Projekt korzysta z biblioteki `org.json.simple.parser.ParseException` oraz `java.io.IOException` do obsługi plików JSON.
- Wymagane jest zbudowanie pliku JAR z wszystkimi zależnościami (fat JAR).

## Użycie

Aby użyć klasy `BasketSplitter`, należy utworzyć instancję obiektu tej klasy i wywołać metodę `split`, przekazując listę produktów jako argument.