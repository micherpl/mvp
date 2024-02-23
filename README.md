Uruchomienie aplikacji:
1. Sklonuj repozytorium do lokalnego katalogu lub pobierz
2. Upewnij sie, że masz jave w wersji 17 lub wyzszej
3. Przejdź do katalogu głównego projektu za pomocą wiersza poleceń i wykonaj polecenie
   mvn spring-boot:run

Jeśli chcesz uruchomic na dockerze:
1. docker build --tag=mvp-server:latest .
2. docker run -p 8080:8080 mvp-server:latest
