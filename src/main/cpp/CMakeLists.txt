cmake_minimum_required(VERSION 3.4.1)

add_library(
        native-sort-lib
        SHARED
        native-sort-lib.cpp
        num_generator.cpp
        bubblesort.cpp
        quicksort.cpp
        countingsort.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        native-sort-lib
        ${log-lib})