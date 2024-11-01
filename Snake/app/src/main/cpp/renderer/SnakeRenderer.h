#ifndef SNAKE_RENDERER_H
#define SNAKE_RENDERER_H

#include "core/SnakeGame.h"
#include <android_native_app_glue.h>
#include <GLES3/gl3.h>

class SnakeRenderer {
public:
    explicit SnakeRenderer(android_app* app);
    ~SnakeRenderer();

    void render();
    void handleInput(); // Placeholder for input handling

private:
    void initOpenGL();
    void drawSquare(float x, float y, float size);
    void drawSnake(const Snake& snake);
    void drawFood(const Food& food);

    android_app* app;
    int screenWidth;
    int screenHeight;
    float cellSize; // Size of each cell on the board
};

#endif // SNAKE_RENDERER_H
