**NoteService**

*Methode: get(String id)*  
**Abfrage** einer Notiz zu einer Person, die gelöscht wurde oder nicht mehr existiert.  
**Note:** Sichert die Datenintegrität und gibt eine aussagekräftige Fehlermeldung zurück.

*Methode: create(Notiz Notiz)*  
**Begründung:** Erstellung einer Notiz mit zu großem Inhalt.  
**Überprüft** die Größe der Notiz und gibt eine aussagekräftige Fehlermeldung zurück, wenn das Limit überschritten wird.

*Methode: queryByContent(String query)*  
**Begründung:** Performance-Probleme bei großen Suchanfragen.  
**Reflection:** Optimierung der Abfrage-Performance durch effiziente Indizierung und Abfrage-Optimierung.

---

**PersonService**

*Methode: getAll()*  
**Überlegung:** Verarbeitung großer Datenmengen.  
**Paging:** Einführung von Paging zur Reduzierung der Datenbanklast und Vermeidung von Speicherproblemen.

*Methode: get(String id)*  
**Fehler:** Anfrage nach einer nicht existierenden ID.  
**Rückgabe** eines benutzerfreundlichen Fehlers, der anzeigt, dass die Ressource nicht gefunden wurde.

*Methode: create(Person Person)*  
**Überlegung:** Anlegen einer Person mit bereits vorhandenen eindeutigen Informationen.  
**Reflection:** Implementierung einer Dublettenprüfung, um die Datenkonsistenz zu gewährleisten.

*Methode: findByName(String Vorname, String Nachname)*  
**Überlegung:** Berücksichtigung von Groß-/Kleinschreibung und Teiltreffer.  
**Implementierung:** Die Implementierung einer flexiblen Suchlogik, um unterschiedliche Schreibweisen und Teile von Namen zu berücksichtigen.