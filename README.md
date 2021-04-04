# PAOProiect

Student, Materie, Profesor

** Serviciu**

Va mentine datele despre studenti, profesori, materii si clase
```
public void adaugaClasa(String clasa_numa,String diriginte_nume)  -- creaza o clasa cu numele dat si diriginte. **ATENTIE**: Dirigintele e un profesor si trenuit creat dinainte
public void adaugaProfesor(String nume,String materie)            -- creeaza un profesor cu numele dat si care preda materia. Materia e un string: Romana, Matematica, Fizica, Engleza
public void adaugaStudent(String student_nume,String clasa_nume)  -- Creeaza un student cu nume. Clasa trebuie sa existe deja
getProfesor(), getClasa(), getMaterie(), getStudent() cauta in liste obiectele cerute dupa nume
```

**Interfata: Nameable**  
```
setName()
getName()
```


**Student**:
```
public Clasa getSchoolClass()   -- Returneaza clasa la care participa studentul
mediaPeSemestru(int)            -- Returneaza media luata dupa catalog
public void setSchoolClass(Clasa clasa) -- Seteaza clasa

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
