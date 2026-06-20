# Simulation de feu de forêt

Simule la propagation d'un feu sur une grille 2D. À chaque étape, une case en feu
s'éteint (devient cendre) et peut propager le feu à ses 4 voisines avec une
probabilité `p`. La simulation s'arrête quand il ne reste plus aucune case en feu.

## Prérequis

- Java 17+
- Maven 3.x

## Lancer le projet

Depuis la racine du projet:

```bash
mvn compile
java -cp target/classes com.ciril.test.App
```

À l'affichage : `T` = forêt, `F` = feu, `.` = cendre.

## Paramètres (`simulation.properties`)

| Clé | Description | Exemple |
|-----|-------------|---------|
| `grid.height` | Hauteur de la grille (entier > 0) | `10` |
| `grid.width` | Largeur de la grille (entier > 0) | `10` |
| `fire.propagation.probability` | Probabilité de propagation à chaque voisin, entre `0` et `1` | `0.5` |
| `fire.initial` | Cases initialement en feu, au format `row,column` séparées par `;` (au moins une, dans la grille) | `5,5` ou `0,0;5,5` |
```
