#ifndef BOARD_H
#define BOARD_H

#include <utility>

class Board {
public:
    // Provide default width and height, but allow customization if needed
    Board(int width = 20, int height = 20);

    bool isOutOfBounds(const std::pair<int, int>& position) const;
    const int getWidth() const;
    const int getHeight() const;

    std::pair<int, int> generateRandomPosition() const;

private:
    int width;
    int height;
};

#endif // BOARD_H
