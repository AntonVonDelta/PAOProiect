# PAOProiect

Student, Materie, Profesor, Clasa, Catalog, Situatie
```
Optiuni posibile:
0) Iesire program
1) Creeaza clasa
2) Creeaza profesor
3) Creeaza student
4) Noteaza student
5) Pune absenta
6) Afiseaza clasele
7) Afiseaza profesorii
8) Afiseaza materiile
9) Afiseaza studentii
10) Afiseaza note student
11) Afiseaza absente student
12) Adauga materie
13) Adauga materii default
14) Editeaza profesor
15) Editeaza clasa
16) Editeaza student
17) Editeaza materii
20) Scrie in CSV
21) Scrie in baza de date
```
Se recomanda executarea comenzilor in ordinea:
 - 13\) Adauga materii default - pentru crearea automata a materiilor
 - 2\) Creeaza profesor
 - 1\) Creeaza clasa
 - 3\) Creeaza student

**Serviciu**

Va mentine datele despre studenti, profesori, materii si clase
```
public void adaugaMaterii(List<Materie> materii_in)               -- adauga materiile la toate clasele
public void adaugaMaterie(String materie_nume)                    -- adauga o singura materie
public void adaugaClasa(String clasa_numa,String diriginte_nume)  -- creaza o clasa cu numele dat si diriginte. **ATENTIE**: Dirigintele e un profesor si trenuit creat dinainte
public void adaugaProfesor(String nume,String materie)            -- creeaza un profesor cu numele dat si care preda materia. Materia e un string: Romana, Matematica, Fizica, Engleza
public void adaugaStudent(String student_nume,String clasa_nume)  -- Creeaza un student cu nume. Clasa trebuie sa existe deja


getProfesor(), getClasa(), getMaterie(), getStudent()              -- cauta in liste obiectele cerute dupa nume
getProfesori(), getMaterii(), getClase(), getStudenti()            -- returneaza toate referintele catre obiectele cerute

loadSavedData()                                                    -- incarca datele din fisere csv
loadFromDatabase()                                                 -- incarca datele din baza de date
```

**Interfata: Nameable**  
```
setName()
getName()
```


**Student**:
```
public Clasa getSchoolClass()                -- Returneaza clasa la care participa studentul
public void setSchoolClass(Clasa clasa)      -- Seteaza clasa
public Situatie getSituatie(Materie materie) -- Returneaza situatie elevului

setName()
getName()
```

**Materie**:
```
setName()
getName()
```


**Profesor**:
```
public void noteazaStudent(int semestru,Student student,int nota)       -- Noteaza studentul pe semestrul dat. Data notei e cea curenta
public void absenteazaStudent(int semestru,Student student) -- Pune abstenta elevului
public void setMaterie(Materie materie)                     -- Seteaza materia predata
public void getMaterie(Materie materie)                     -- Analog
setName()
getName()
```


**Clasa**
```
protected Situatie getSituatie(int semestru,Profesor profesor,Student student)  -- Folosita de profesori pentru a accesa catalogul pe semestru
public void addStudent(Student student)     -- Adauga student in clasa curenta
public Profesor getDiriginte(Profesor diriginte)  -- Ia dirigintele
setName()
getName()
```
**Catalog**:
Contine lista de materii
```
public void initSituatie(Student student)   -- Initiaza catalogul si situatia scolara pentru student
getSem1(),getSem2()                         -- Returneaza situatia tuturor studentilor aka tot catalogul
```

**Situatie**:
```
public void noteaza(Date data,int nota)     -- Ofera o nota studentului
public void absenteaza(Date data)           -- Pune o absenta
public void motiveaza(Date data)            -- Motiveaza absenta
```
