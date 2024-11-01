//
// Created by rottem on 01/11/2024.
//

#ifndef SNAKE_GAME_H
#define SNAKE_GAME_H

#include "Snake.h"
#include "Food.h"
#include "Board.h"

class SnakeGame {
public:
    SnakeGame();
    void initialize();
    void update();
    void reset();
    bool isGameOver() const;

    // New getters to access Snake and Food instances
    const Snake& getSnake() const;
    const Food& getFood() const;

private:
    Snake snake;
    Food food;
    Board board;
    bool gameOver;
};

#endif // SNAKE_GAME_H



