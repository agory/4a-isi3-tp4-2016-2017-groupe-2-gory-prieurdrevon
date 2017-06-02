**GORY / Alexandre Etudiant 1 :**

**PRIEUR-DREVON / Thomas Etudiant 2 :**

# Rapport TP4

## Question 1
*Exprimer les données de test*

![diagram](images/diagram1.png)

Probléme lié au code :

    - Plusieurs classes par fichier : 
        + **Tortue.class** -> 2 classe Tortue and segment dans le même fichier (à séparer)
    - Methode trop longue : 
        + SimpleLogo logoInit (à diviser)
        + SimpleLogo actionPerformed if imbriqué remplacement possible switch case
    
Probléme lié à la structure de code :

    - Responsabilité unique :
        +  Les classes gérent à la fois la logique metier et d'affichage
            -> Solution utilisation du model MVC
        + Simple Logo jouent le rôle de controller et de view
   
   
     

## Question 2
*Rien à rédiger*

## Question 3
*Rien à rédiger*

## Question 4
*Rien à rédiger*


## Question 5
Pour implementer les agents aléatoires, j'ai choisi que l'agent soit une entité indépendante, c'est à dire il est dans sa propre thread et agit tous les certains laps de temps. Il s'agit de la classe Abstrait Agent qui ne gère que la partie Thread. Cette classe dispose d'une méthode abstrait compute afin de pouvoir implémenter le comportement de l'agent.
![diagram](images/agent.png)

Par la suite j'ai crée une classe RandomAgent qui implémente les décisions aléatoire de l'agent.
```java 
 protected List<Action> compute() {
        Random random = new Random();
        List<Action> actions = new ArrayList<>();
        int number = random.nextInt(3);
        if(number > 3)
            number = 0;
        switch (number) {
            case 0:
                actions.add(new MoveForward(1));
                break;
            case 1:
                actions.add(new TurnLeft(3));
                actions.add(new MoveForward(1));
                break;
            case 2:
                actions.add(new TurnRight(3));
                actions.add(new MoveForward(1));
                break;
            default:
                actions.add(new MoveForward(0));
                break;
        }
        return actions;
    }
```

## Question 6


Pour réaliser le flocking, j'ai réalisé un héritage avec  RandomAgent pour garder le comportement précédent lorsqu'il y a aucune tortue proche. Cette nouvelle classe FlockingAgent a un comportement qui ce modifie si des tortues sont proches. Lorsque l'agent détecte des tortues voisins, elle met à jour ça direction pour avoir la moyenne de la direction de tous les voisins.

Pour détecter les voisin, j'ai crée un classe TurtleMinkfields qui est du coups un composant de FlockingAgent. Ce composant permet de recupérer les tortues proches. 

![diagram](images/diagramFlock.png)


Pour récupérer les tortues proches je calcule la distance euclidienne en prennant en compte les envirronements toroidal. 


## Implémentation des obstacles

Pour implémenter l'évitement d'obstacle, j'ai crée une classe ObstacleMinkfields qui permet de détecter les obstacles.

De plus, j'ai crée une classe AvoidingAgent qui ajoute les comportements d'évitement d'obstacle. Lorsqu'il n'y a pas d'obstacle, le comportement flocking est appliquer.
 
 ![diagram](images/avoidingAgent.png)

 ## Fonctionnalités bonus implémentées bugger
 
 Nous avons essayer d'implémenter un angle vision pour éviter que les agents soit influencé par les agents derrière lui. Cependant, il y a de nombreux bug la concernat, elle est donc désactivée dans le projet
 




