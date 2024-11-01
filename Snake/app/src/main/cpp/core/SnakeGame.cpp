//
// Created by rottem on 01/11/2024.
//

#include "SnakeGame.h"

SnakeGame::SnakeGame() : board(), food(board), gameOver(false) {
    initialize();
}

void SnakeGame::initialize() {
    snake = Snake();
    food = Food(board);  // Pass the board to Food for valid positioning
    gameOver = false;
}

void SnakeGame::update() {
    if (gameOver) return;

    snake.move();

    // Check for collision with food
    if (snake.collidesWith(food.getPosition())) {
        snake.grow();
        food.reposition(board);  // Use board for repositioning
    }

    // Check for collision with walls or self
    if (board.isOutOfBounds(snake.getHead()) || snake.collidesWithSelf()) {
        gameOver = true;
    }
}

void SnakeGame::reset() {
    initialize();
}

bool SnakeGame::isGameOver() const {
    return gameOver;
}

const Snake& SnakeGame::getSnake() const {
    return snake;
}

const Food& SnakeGame::getFood() const {
    return food;
}
