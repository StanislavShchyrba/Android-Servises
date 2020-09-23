cmake_minimum_required(VERSION 3.4.1)

add_library(
        native-sort-lib
        SHARED
        native-sort-lib.h
        native-sort-lib.cpp)

find_library(
        log-lib
        log)

target_link_libraries(
        native-sort-lib
        ${log-lib})