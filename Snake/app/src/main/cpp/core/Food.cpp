//
// Created by rottem on 01/11/2024.
//

#include "Food.h"
#include <cstdlib>
#include <ctime>

Food::Food(const Board& board) {
    std::srand(std::time(nullptr)); // Seed random number generator
    reposition(board);
}

const std::pair<int, int>& Food::getPosition() const {
    return position;
}

void Food::reposition(const Board& board) {
    position = board.generateRandomPosition();
}
