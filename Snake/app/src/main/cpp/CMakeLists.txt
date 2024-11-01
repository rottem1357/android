cmake_minimum_required(VERSION 3.22.1)

project("snake")

# Define the target library
add_library(snake SHARED
        main.cpp
        AndroidOut.cpp
        Renderer.cpp
        Shader.cpp
        TextureAsset.cpp
        Utility.cpp
        core/Snake.cpp
        core/SnakeGame.cpp
        core/Food.cpp
        core/Board.cpp
        renderer/SnakeRenderer.cpp
)

# Link native_app_glue (necessary for using android_native_app_glue.h)
add_library(native_app_glue STATIC
        ${ANDROID_NDK}/sources/android/native_app_glue/android_native_app_glue.c)

# Link GameActivity library
find_package(game-activity REQUIRED CONFIG)

# Associate include directories with the `snake` target
target_include_directories(snake PRIVATE
        ${CMAKE_SOURCE_DIR}/core
        ${CMAKE_SOURCE_DIR}/renderer
        ${ANDROID_NDK}/sources/android/native_app_glue
)

# Link libraries to the target
target_link_libraries(snake
        game-activity::game-activity
        native_app_glue
        EGL
        GLESv3
        jnigraphics
        android
        log
)
