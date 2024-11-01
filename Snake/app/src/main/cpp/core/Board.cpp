//
// Created by rottem on 01/11/2024.
//
#include "Board.h"
#include <cstdlib>

Board::Board(int width, int height) : width(width), height(height) {}

bool Board::isOutOfBounds(const std::pair<int, int>& position) const {
    return position.first < 0 || position.first >= width ||
           position.second < 0 || position.second >= height;
}

const int Board::getWidth() const {
    return width;
}

const int Board::getHeight() const {
    return height;
}

std::pair<int, int> Board::generateRandomPosition() const {
    return {std::rand() % width, std::rand() % height};
}

