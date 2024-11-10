**Konfiguracja testów**: Testy są uruchamiane w kolejności określonej przez adnotacje `@Order` - są od siebie zależne wewnątrz klasy. Wszystkie testy są przeprowadzane w sesji przeglądarki (Chrome, Edge lub FireFox).

---

# GoogleSeleniumTest

### Test 1: Sprawdzenie tytułu strony (siteTitleTest)
**Cel**: Upewnienie się, że tytuł strony po załadowaniu to „Google”.
1. Pobierz tytuł strony po otwarciu URL.
2. Porównaj go z wartością „Google”.

**Oczekiwany wynik**: Tytuł strony jest równy „Google”.

---

### Test 2: Sprawdzenie tekstu przycisku odrzucenia ciasteczek (rejectButtonTextTest)
**Cel**: Sprawdzenie, że wartość tekstowa przycisku „Odrzuć wszystko” jest poprawna.
1. Odszukaj przycisk „Odrzuć wszystko” i poczekaj, aż będzie możliwy do kliknięcia.
2. Sprawdź, czy tekst przycisku to „Odrzuć wszystko”.

**Oczekiwany wynik**: Tekst przycisku to „Odrzuć wszystko”.

---

### Test 3: Ukrycie przycisku odrzucenia ciasteczek po kliknięciu (rejectButtonNotVisibleTest)
**Cel**: Zweryfikowanie, że przycisk „Odrzuć wszystko” nie jest widoczny po kliknięciu.
1. Znajdź przycisk „Odrzuć wszystko”.
2. Kliknij go.
3. Sprawdź, czy przycisk nie jest już widoczny.

**Oczekiwany wynik**: Przycisk „Odrzuć wszystko” znika po kliknięciu.

---

### Test 4: Widoczność pola wyszukiwania (searchBoxVisibilityTest)
**Cel**: Sprawdzenie, czy pole wyszukiwania jest widoczne na stronie.
1. Znajdź pole wyszukiwania.
2. Zweryfikuj, czy jest widoczne.

**Oczekiwany wynik**: Pole wyszukiwania jest widoczne.

---

### Test 5: Wprowadzenie tekstu do pola wyszukiwania (searchBoxValueTest)
**Cel**: Sprawdzenie, że wpisany tekst pojawia się w polu wyszukiwania.
1. Wprowadź tekst „askew” w pole wyszukiwania.
2. Sprawdź, czy wartość w polu wynosi „askew”.

**Oczekiwany wynik**: W polu wyszukiwania widoczny jest tekst „askew”.

---

### Test 6: Zmiana adresu URL po wyszukaniu (differentUrlAddressTest)
**Cel**: Sprawdzenie, że adres URL zmienia się po wyszukaniu frazy.
1. Zapisz początkowy URL strony.
2. Wprowadź tekst w pole wyszukiwania i wyślij formularz.
3. Sprawdź, czy adres URL po wyszukaniu różni się od początkowego.

**Oczekiwany wynik**: Adres URL zmienia się po wyszukaniu.

---

### Test 7: Zmiana klasy przycisku odtwarzania (triggerClassChangeClassTest)
**Cel**: Upewnienie się, że kliknięcie przycisku odtwarzania tekstu zmienia jego klasę/stan.
1. Znajdź przycisk odtwarzania.
2. Zapisz początkową wartość odpowiadającego atrybutu.
3. Kliknij przycisk.
4. Sprawdź, czy wartość atrybutu różni się po kliknięciu.

**Oczekiwany wynik**: Atrybut „class” przycisku zmienia się po kliknięciu.

---

### Test 8: Czyszczenie pola wyszukiwania (screenKeyboardTest)
**Cel**: Zweryfikowanie, że kliknięcie przycisku czyszczenia usuwa tekst z pola wyszukiwania.
1. Znajdź i kliknij przycisk czyszczenia. 
2. Sprawdź, czy wartość w polu wyszukiwania jest pusta.

**Oczekiwany wynik**: Pole wyszukiwania jest puste po kliknięciu przycisku czyszczenia.

---

# GoogleTranslateSeleniumTest

### Test 1: Sprawdzenie tytułu strony po otwarciu (siteTitleTest)
**Cel:** Zweryfikowanie, że tytułem strony po załadowaniu nie jest „Tłumacz Google”.

- Pobierz tytuł strony po otwarciu URL.
- Sprawdź, czy tytuł strony jest różny od „Tłumacz Google”.

**Oczekiwany wynik:** Tytuł strony różni się od „Tłumacz Google”.

---

### Test 2: Tytuł strony po akceptacji ciasteczek (siteTitleCorrectTest)
**Cel:** Sprawdzenie, że po akceptacji ciasteczek tytuł strony to „Tłumacz Google”.

- Kliknij przycisk „Odrzuć wszystko”.
- Pobierz aktualny tytuł i porównaj go do „Tłumacz Google”.

**Oczekiwany wynik:** Tytuł strony zmienia się na „Tłumacz Google”.

---

### Test 3: Sprawdzenie wpisanego tekstu w polu tłumaczenia (translateTest)
**Cel:** Sprawdzenie, czy pole tekstowe przyjmuje wpisane wartości.

- Znajdź pole „Tekst źródłowy” i kliknij w nie.
- Wpisz tekst „I am prepared” do pola.
- Sprawdź, czy wartość pola to „I am prepared”.

**Oczekiwany wynik:** W polu tekstowym znajduje się tekst „I am prepared”.

---

### Test 4: Zmiana wartości w polu tekstowym po zamianie języków (correctTranslationTest)
**Cel:** Sprawdzenie, czy tekst zmienia się w polu tekstowym po zamianie języka źródłowego i docelowego.

- Kliknij przycisk zamiany języków.
- Sprawdź, czy tekst w polu „Tekst źródłowy” różni się od „I am prepared”.

**Oczekiwany wynik:** Wartość pola różni się od „I am prepared” po zamianie języków.

---

### Test 5: Sprawdzenie bannera logowania po kliknięciu przycisku „Historia” (needToSignInAfterClickingAtHistoryButtonTest)
**Cel:** Sprawdzenie, czy po kliknięciu przycisku „Historia” pojawia się alert z wymogiem logowania.

- Znajdź i kliknij przycisk „Historia”.
- Sprawdź, czy alert o konieczności logowania pojawi się.

**Oczekiwany wynik:** Alert logowania jest widoczny po kliknięciu przycisku „Historia”.

---

### Test 6: Zamknięcie okna alertu logowania (closeAlertWindowTest)
**Cel:** Zweryfikowanie, że po kliknięciu przycisku „Cancel” alert logowania znika i nie można go ponownie kliknąć.

- Znajdź alert logowania oraz przycisk „Cancel”.
- Kliknij przycisk „Cancel”, by zamknąć alert.
- Sprawdź, czy próba ponownego kliknięcia alertu jest możliwa.

**Oczekiwany wynik:** Po kliknięciu „Cancel” alert logowania jest zamknięty i nie można go ponownie kliknąć.

---

### Test 7: Wybranie opcji „Powoli” w panelu Ustawień (settingsBannerRadioButtonTest)
**Cel:** Sprawdzenie, czy można zaznaczyć opcję „Powoli” w ustawieniach i czy jest ona prawidłowo zaznaczona.

- Kliknij przycisk „Ustawienia”.
- Wybierz opcję „Powoli” z panelu.
- Sprawdź, czy przycisk „Powoli” jest zaznaczony.

**Oczekiwany wynik:** Opcja „Powoli” jest zaznaczona po jej wybraniu.

---

### Test 8: Przejście do "wszystko o Google" z panelu menu głównego Google (googleMainMenuBarTest)
**Cel:** Zweryfikowanie, czy możliwe jest przejście do "wszystko o Google" z panelu aplikacji Google.

- Kliknij główne menu aplikacji Google.
- Wybierz opcję „Wszystko o Google”.
- Sprawdź, czy adres URL zmienił się z „translate.google.pl” na inną stronę.

**Oczekiwany wynik:** Po kliknięciu w opcję „Wszystko o Google” użytkownik jest przekierowany na inną stronę niż „translate.google.pl”.

---

# OtomotoSeleniumTest

### Test 1: Sprawdzenie tytułu strony po załadowaniu (siteTitleTest)
**Cel:** Zweryfikowanie, że strona załadowała się poprawnie poprzez sprawdzenie tytułu.

- Pobierz tytuł strony po otwarciu URL.
- Sprawdź, czy tytuł strony to „OTOMOTO - nowe i używane samochody i motocykle oraz części samochodowe. Ogłoszenia motoryzacyjne.”

**Oczekiwany wynik:** Tytuł strony jest zgodny z oczekiwaną wartością.

---

### Test 2: Akceptacja ciasteczek (cookiesTest)
**Cel:** Sprawdzenie, że po zaakceptowaniu ciasteczek powiadomienie o nich znika.

- Znajdź baner ciasteczek.
- Kliknij przycisk „Akceptuj wszystkie”.
- Sprawdź, czy baner ciasteczek nie jest już widoczny.

**Oczekiwany wynik:** Baner ciasteczek znika po kliknięciu „Akceptuj wszystkie”.

---

### Test 3: Otwarcie formularza logowania (loginSiteTest)
**Cel:** Sprawdzenie, że kliknięcie przycisku „Zaloguj” otwiera formularz logowania.

- Znajdź i kliknij przycisk „Zaloguj”.
- Sprawdź, czy URL zmienia się na stronę logowania.

**Oczekiwany wynik:** Użytkownik jest przekierowany na stronę logowania.

---

### Test 4: Walidacja błędnego formatu adresu e-mail (wrongEmailFormatTest)
**Cel:** Sprawdzenie, że podanie niepoprawnego formatu adresu e-mail wyświetla odpowiedni komunikat.

- Wprowadź niepoprawny adres e-mail, np. „test@test”.
- Sprawdź, czy pojawia się komunikat „To nie wygląda jak adres mailowy...”.

**Oczekiwany wynik:** Wyświetla się komunikat o niepoprawnym formacie adresu e-mail.

---

### Test 5: Walidacja zbyt krótkiego hasła (wrongPasswordTest)
**Cel:** Sprawdzenie, że wpisanie zbyt krótkiego hasła wyświetla ostrzeżenie.

- Wprowadź zbyt krótkie hasło, np. „test”.
- Sprawdź, czy pojawia się komunikat „Masz pewność co do hasła? Jest zbyt krótkie”.

**Oczekiwany wynik:** Wyświetla się komunikat o zbyt krótkim haśle.

---

### Test 6: Walidacja niepoprawnych danych logowania (wrongCredentialsTest)
**Cel:** Sprawdzenie, że przy podaniu nieistniejących danych logowania pojawia się komunikat o braku konta.

- Wprowadź nieistniejący adres e-mail i hasło.
- Kliknij przycisk „Zaloguj się”.
- Sprawdź, czy pojawia się komunikat o błędnych danych logowania.

**Oczekiwany wynik:** Wyświetla się komunikat o braku konta dla wprowadzonych danych.

---

### Test 7: Wybór modelu pojazdu bez wyboru marki (cantFindOnlyByCarModelTest)
**Cel:** Sprawdzenie, że niemożliwe jest wybranie modelu pojazdu bez uprzedniego wyboru marki.

- Kliknij w pole wyboru „Model pojazdu”.
- Sprawdź, czy menu rozwijane pozostaje zamknięte, co oznacza, że model nie może być wybrany bez marki.

**Oczekiwany wynik:** Wybór modelu jest niemożliwy bez wyboru marki.

---

### Test 8: Wyszukiwanie pojazdu na podstawie marki i modelu (findCarByBrandAndModelTest)
**Cel:** Sprawdzenie, że po wyborze marki i modelu oraz kliknięciu „Szukaj” adres URL zmienia się.

- Wybierz markę, np. „Toyota”.
- Wybierz model, np. „Supra”.
- Kliknij przycisk „Szukaj”.
- Sprawdź, czy URL zmienia się na stronę z wynikami wyszukiwania.

**Oczekiwany wynik:** Adres URL zmienia się po wyszukiwaniu, wskazując na nowe wyniki.

---


# YouTubeSeleniumTest

### Test 1: Sprawdzenie tekstu przycisku „Odrzuć wszystko” (rejectButtonTextTest)
**Cel:** Zweryfikowanie, czy przycisk „Odrzuć wszystko” wyświetla poprawny tekst.

- Znajdź przycisk „Odrzuć wszystko” na stronie.
- Sprawdź, czy tekst na przycisku to „Odrzuć wszystko”.

**Oczekiwany wynik:** Tekst przycisku jest „Odrzuć wszystko”.

---

### Test 2: Sprawdzenie tytułu strony głównej YouTube (siteTitleTest)
**Cel:** Sprawdzenie tytułu strony głównej YouTube.

- Pobierz tytuł strony głównej YouTube.
- Sprawdź, czy tytuł zawiera frazę „YouTube”.

**Oczekiwany wynik:** Tytuł strony zawiera „YouTube”.

---

### Test 3: Walidacja poprawności wybranego filmu (videoSelectionTest)
**Cel:** Sprawdzenie, że po kliknięciu na film wideo użytkownik zostaje przekierowany na stronę z filmem.

- Wybierz film z listy sugerowanych.
- Sprawdź, czy użytkownik jest przekierowany na stronę z filmem.

**Oczekiwany wynik:** Użytkownik zostaje przekierowany na stronę wybranego filmu.

---

### Test 4: Sprawdzenie reakcji na kliknięcie w przycisk „Zaloguj się” (loginButtonTest)
**Cel:** Sprawdzenie, czy kliknięcie przycisku „Zaloguj się” otwiera formularz logowania.

- Kliknij przycisk „Zaloguj się” na stronie.
- Sprawdź, czy otwiera się formularz logowania.

**Oczekiwany wynik:** Formularz logowania pojawia się po kliknięciu przycisku „Zaloguj się”.

---

### Test 5: Reakcja na kliknięcie w ikonkę profilu (profileIconTest)
**Cel:** Sprawdzenie, czy po kliknięciu na ikonę profilu wyświetla się menu użytkownika.

- Kliknij na ikonę profilu w prawym górnym rogu.
- Sprawdź, czy wyświetla się menu użytkownika.

**Oczekiwany wynik:** Menu użytkownika pojawia się po kliknięciu na ikonę profilu.
