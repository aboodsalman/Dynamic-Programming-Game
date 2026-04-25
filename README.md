# Dynamic Programming Number Picking Game

A JavaFX game where two players take turns choosing a number from either the left or right side of an array.
The player with the higher total score at the end wins.
The project includes two modes: Player vs Computer and Player vs Player.

---

## Features

* Built with Java and JavaFX
* Two game modes:

  * Human vs Computer
  * Human vs Human
* Uses Dynamic Programming to calculate the optimal strategy
* Interactive graphical interface
* Displays scores and winner at the end of the game

---

## Game Idea

Given an array of numbers, two players alternately choose one number from either end of the array.

Example:

```
[4, 7, 2, 9]
```

A player can choose either `4` or `9`.

The goal is to maximize the total score collected by the player.

---

## Dynamic Programming Approach

The game is solved using Dynamic Programming by finding the maximum score difference a player can guarantee from a subarray.

Let:

```
dp[i][j]
```

represent the maximum score difference the current player can achieve over the opponent from the subarray `arr[i...j]`.

### DP Formula

```
dp[i][j] = max(
    arr[i] - dp[i + 1][j],
    arr[j] - dp[i][j - 1]
)
```

### Explanation

* If the player chooses `arr[i]`, the opponent will play optimally on `arr[i+1...j]`.
* If the player chooses `arr[j]`, the opponent will play optimally on `arr[i...j-1]`.
* The player chooses the move that gives the maximum score difference.

### Base Case

```
dp[i][i] = arr[i]
```

When only one number remains, the current player takes it.

---

## Technologies Used

* Java
* JavaFX
* Dynamic Programming

---

## How to Run

1. Clone the repository:

```
git clone https://github.com/your-username/your-repo-name.git
```

2. Open the project in your Java IDE (IntelliJ IDEA / Eclipse).

3. Make sure JavaFX is configured.

4. Run the main JavaFX application file.

---

## Project Structure

```
src/
 ├── Main.java
 ├── GameController.java
 ├── ComputerPlayer.java
 ├── DPStrategy.java
 └── views/
```

---

## CV Description

Developed a JavaFX dynamic programming game where players choose numbers from both ends of an array to maximize their final score.
Implemented Player vs Player and Player vs Computer modes with an optimal computer strategy based on DP.
Applied the recurrence `dp[i][j] = max(arr[i] - dp[i+1][j], arr[j] - dp[i][j-1])` to determine the best move.
